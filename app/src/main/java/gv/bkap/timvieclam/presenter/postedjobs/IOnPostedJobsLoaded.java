package gv.bkap.timvieclam.presenter.postedjobs;

import java.util.ArrayList;

import gv.bkap.timvieclam.model.entity.PostedJobItem;

public interface IOnPostedJobsLoaded {
    void onPostedJobsLoaded(ArrayList<PostedJobItem> lstPostedJobs);
}
