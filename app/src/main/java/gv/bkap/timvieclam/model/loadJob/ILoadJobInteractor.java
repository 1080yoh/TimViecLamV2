package gv.bkap.timvieclam.model.loadJob;

import android.content.Context;

import gv.bkap.timvieclam.presenter.loadjob.ILoadJobPresenter;

public interface ILoadJobInteractor {
    void loadJob(ILoadJobPresenter iLoadJobPresenter, Context context);
}
