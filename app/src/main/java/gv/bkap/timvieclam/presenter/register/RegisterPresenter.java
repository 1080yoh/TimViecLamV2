package gv.bkap.timvieclam.presenter.register;

import gv.bkap.timvieclam.model.register.RegisterInteractor;
import gv.bkap.timvieclam.view.register.IRegisterView;

public class RegisterPresenter implements IRegisterPresenter {

    private RegisterInteractor RegisterInteractor;
    IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView) {
        RegisterInteractor = new RegisterInteractor(this);
        this.iRegisterView = iRegisterView;
    }


    @Override
    public Boolean checkRegister(String username, String name, String password, String repassword, String email) {
        Boolean error;
        if (username.equals("") && name.equals("") && password.equals("") && repassword.equals("") && email.equals("")) {
            error = false;
        } else {
            error = true;
        }
        return error;
    }

    @Override
    public void AddAccount(String username, String password, String name_display, String email) {
        RegisterInteractor.register(username, password, name_display, email);
    }

    public void onRegisterSuccessfully() {
        iRegisterView.registerSuccess();
    }

    public void onRegisterFailure() {
        iRegisterView.accoutnAlreadyExits();
    }

}
