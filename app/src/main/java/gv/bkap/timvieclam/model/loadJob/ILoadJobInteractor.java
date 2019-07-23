package gv.bkap.timvieclam.model.loadJob;

import android.content.Context;

import gv.bkap.timvieclam.presenter.login.IOnLoginValidateListener;

public interface ILoadJobInteractor {
    void loadJob(IOnLoginValidateListener onLoginValidateListener, Context context);
}
