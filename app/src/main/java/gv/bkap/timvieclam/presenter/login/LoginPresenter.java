package gv.bkap.timvieclam.presenter.login;

import android.content.Context;
import android.os.Handler;

import gv.bkap.timvieclam.model.ApplicationContext;
import gv.bkap.timvieclam.model.entity.Account;
import gv.bkap.timvieclam.model.login.ILoginInteractor;
import gv.bkap.timvieclam.model.login.LoginInteractor;
import gv.bkap.timvieclam.view.login.ILoginView;

public class LoginPresenter implements IOnLoginValidateListener, ILoginPresenter {

    private Context context;
    private ILoginView loginView;
    private ILoginInteractor loginInteractor;

    public LoginPresenter(ILoginView loginView, Context context) {
        this.loginView = loginView;
        this.context = context;
        loginInteractor = new LoginInteractor();
    }

    @Override
    public void login(final String username, final String password) {
        loginView.resetErrors();
        boolean error = false;
        if (username.length() == 0) {
            loginView.setUsernameError("Tài khoản không được để trống!");
            error = true;
        }
        if (password.length() == 0) {
            loginView.setPasswordError("Mật khẩu không được để trống!");
            error = true;
        }
        if (!error) {
            loginView.showProgressDialog();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loginInteractor.login(username, password, LoginPresenter.this, context);
                }
            }, 1000);
        }
    }

    @Override
    public void onPasswordError(String message) {
        loginView.setPasswordError(message);
        loginView.hideProgressDialog();
    }

    @Override
    public void onUsernameError(String message) {
        loginView.hideProgressDialog();
        loginView.setUsernameError(message);
    }

    @Override
    public void onLoginSuccess(Account account) {
        ApplicationContext applicationContext = (ApplicationContext) context.getApplicationContext();
        applicationContext.saveAccount(account);

        loginView.hideProgressDialog();
        loginView.makeToast("Đăng nhập thành công!");

        loginView.navigateToMainActivity();
    }

}
