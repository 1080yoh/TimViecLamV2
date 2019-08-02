package gv.bkap.timvieclam.presenter.postedjobs;

import java.util.ArrayList;

import gv.bkap.timvieclam.model.entity.PostedJobItem;
import gv.bkap.timvieclam.model.postedjobs.IPostedJobsInteractor;
import gv.bkap.timvieclam.model.postedjobs.PostedJobsInteractor;
import gv.bkap.timvieclam.view.postedjobs.IPostedJobsView;

public class PostedJobsPresneter implements IPostedJobsPresenter, IOnPostedJobsLoaded {

    IPostedJobsView View;
    IPostedJobsInteractor interactor;

    public PostedJobsPresneter(IPostedJobsView View) {
        this.View = View;
        interactor = new PostedJobsInteractor(this);
    }

    @Override
    public void loadDataPostedJobs(int id_account) {
        interactor.loadPostedJobs(id_account);
    }

    @Override
    public void onPostedJobsLoaded(ArrayList<PostedJobItem> lstPostedJobs) {
        ArrayList<Object> lstDataPostedJobs = new ArrayList<>();
        String firstMonth = lstPostedJobs.get(0).getPosted_date();
        String firstHeader = firstMonth.substring(0, firstMonth.lastIndexOf("-"));
        lstDataPostedJobs.add(firstHeader);
        lstDataPostedJobs.add(lstPostedJobs.get(0));

        for (int i = 0; i < lstPostedJobs.size() - 1; i++) {
            String date1 = lstPostedJobs.get(i).getPosted_date();
            String date2 = lstPostedJobs.get(i + 1).getPosted_date();

            String month1 = date1.substring(0, date1.lastIndexOf("-"));
            String month2 = date2.substring(0, date2.lastIndexOf("-"));

            if (!month1.equals(month2)) {
                lstDataPostedJobs.add(month2);
            }
            lstDataPostedJobs.add(lstPostedJobs.get(i + 1));
        }
        View.loadPostedJobs(lstDataPostedJobs);
    }
}
