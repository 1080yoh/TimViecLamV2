package gv.bkap.timvieclam.model.login;

import android.content.Context;

import gv.bkap.timvieclam.presenter.login.IOnLoginValidateListener;

public interface ILoginInteractor {
    void login(String username, String password, IOnLoginValidateListener onLoginValidated, Context context);
}
