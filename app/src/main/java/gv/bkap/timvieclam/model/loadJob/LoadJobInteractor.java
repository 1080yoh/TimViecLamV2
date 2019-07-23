package gv.bkap.timvieclam.model.loadJob;

import android.content.Context;

import gv.bkap.timvieclam.presenter.login.IOnLoginValidateListener;

public class LoadJobInteractor implements ILoadJobInteractor {

    private final String TAG = "LoginInteractor";

    IOnLoginValidateListener onLoginValidateListener;
    Context context;

    @Override
    public void loadJob(IOnLoginValidateListener onLoginValidateListener, Context context) {
        this.onLoginValidateListener = onLoginValidateListener;
        this.context = context;
    }
}
