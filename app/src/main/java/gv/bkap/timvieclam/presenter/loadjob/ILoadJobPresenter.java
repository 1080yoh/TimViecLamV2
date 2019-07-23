package gv.bkap.timvieclam.presenter.loadjob;

import gv.bkap.timvieclam.model.entity.Job;

public interface ILoadJobPresenter {
    void onJobError(String message);
    void onLoadSucessfull(Job job);
}
