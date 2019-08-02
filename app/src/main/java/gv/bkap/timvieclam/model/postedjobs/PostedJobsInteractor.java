package gv.bkap.timvieclam.model.postedjobs;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;

import gv.bkap.timvieclam.model.entity.PostedJobItem;
import gv.bkap.timvieclam.model.entity.PostedJobItemMapper;
import gv.bkap.timvieclam.model.server.ServerInteractor;
import gv.bkap.timvieclam.presenter.postedjobs.IOnPostedJobsLoaded;

public class PostedJobsInteractor implements IPostedJobsInteractor {

    IOnPostedJobsLoaded onPostedJobsLoaded;

    public PostedJobsInteractor(IOnPostedJobsLoaded onPostedJobsLoaded) {
        this.onPostedJobsLoaded = onPostedJobsLoaded;
    }

    @Override
    public void loadPostedJobs(int id_account) {
        LoadPostedJobsTask loadPostedJobsTask = new LoadPostedJobsTask();
        loadPostedJobsTask.execute(id_account + "");
    }

    private class LoadPostedJobsTask extends AsyncTask<String, Void, ArrayList<PostedJobItem>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<PostedJobItem> doInBackground(String... strings) {

            String sendData[][] = {
                    {"id_account", strings[0]},
            };

            final String result = new ServerInteractor().sendRequest(
                    1, ServerInteractor.HOSTING_API + ServerInteractor.PAGE_LOAD_POSTED_JOBS, sendData);
            try {
                Log.e("debug xxx", "Result got: " + result);
                return PostedJobItemMapper.getPostedJobsItem(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<PostedJobItem> lstPostedJobs) {
            onPostedJobsLoaded.onPostedJobsLoaded(lstPostedJobs);
        }
    }
}
