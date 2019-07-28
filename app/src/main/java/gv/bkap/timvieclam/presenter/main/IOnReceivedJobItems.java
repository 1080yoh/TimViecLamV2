package gv.bkap.timvieclam.presenter.main;

import java.util.ArrayList;

import gv.bkap.timvieclam.model.entity.JobItem;

public interface IOnReceivedJobItems {
    void onReceivedJobItems(ArrayList<JobItem> lstJobItems);
}
