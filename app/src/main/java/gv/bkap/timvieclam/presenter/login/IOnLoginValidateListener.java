package gv.bkap.timvieclam.presenter.login;

import gv.bkap.timvieclam.model.entity.Account;

public interface IOnLoginValidateListener {
    void onPasswordError(String message);

    void onUsernameError(String message);

    void onLoginSuccess(Account account);
}
