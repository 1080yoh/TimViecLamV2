package gv.bkap.timvieclam.presenter.login;

import android.content.Context;

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
    public void login(String username, String password) {
        loginView.resetErrors();
        loginView.showProgressDialog();
        loginInteractor.login(username, password, this, this.context);
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
        applicationContext.setAccount(account);
        loginView.hideProgressDialog();

        loginView.navigateToMainActivity();
    }

}
