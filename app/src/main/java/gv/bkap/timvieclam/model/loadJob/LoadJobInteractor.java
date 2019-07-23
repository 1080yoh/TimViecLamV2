package gv.bkap.timvieclam.model.loadJob;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import gv.bkap.timvieclam.R;
import gv.bkap.timvieclam.model.entity.Job;
import gv.bkap.timvieclam.model.entity.JobMapper;
import gv.bkap.timvieclam.model.server.ServerInteractor;
import gv.bkap.timvieclam.model.utils.MD5Encoder;
import gv.bkap.timvieclam.presenter.loadjob.ILoadJobPresenter;

public class LoadJobInteractor implements ILoadJobInteractor {

    private final String TAG = "LoadJobInteractor";

   ILoadJobPresenter iLoadJobPresenter;
    Context context;

    @Override
    public void loadJob(ILoadJobPresenter iLoadJobPresenter, Context context) {
        this.iLoadJobPresenter = iLoadJobPresenter;
        this.context = context;
    }
    private void validated(Job job) {
        Boolean error = false;

        if (job == null) {
            Log.e("debug xxx", "LoadJob unsuccessfully");
            iLoadJobPresenter.onJobError(context.getString(R.string.load_error));
            error = true;
        }

        if (!error) {
            iLoadJobPresenter.onLoadSucessfull(job);
            Log.e("debug xxx", "Login successfully");
        }
    }
    private class LoadTask extends AsyncTask<String, Void, Job> {

        @Override
        protected Job doInBackground(String... strings) {
            if (strings.length != 2)
                return null;
            String sendData[][] = {
                    {"username", strings[0]},
                    {"password", MD5Encoder.encode(strings[1])}
            };

            final String result = new ServerInteractor().sendRequest(
                    1, ServerInteractor.HOSTING_API + ServerInteractor.PAGE_LOGIN, sendData);
            Log.e(TAG, "Result: " + result);
            try {
                Log.e("debug xxx", "Result got: "+result);
                return JobMapper.getJob(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
