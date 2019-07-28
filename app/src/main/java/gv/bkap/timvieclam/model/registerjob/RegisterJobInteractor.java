package gv.bkap.timvieclam.model.registerjob;

import android.os.AsyncTask;
import android.util.Log;

import gv.bkap.timvieclam.model.entity.Job;
import gv.bkap.timvieclam.model.server.ServerInteractor;
import gv.bkap.timvieclam.presenter.registerjob.RegisterJobPresenter;

public class RegisterJobInteractor implements IRegisterJobInteractor {
    private final String TAG = "RegisterJobInteractor";

    private RegisterJobPresenter registerJobPresenter;

    public RegisterJobInteractor(RegisterJobPresenter registerJobPresenter) {
        this.registerJobPresenter = registerJobPresenter;
    }

    @Override
    public void registerJob(String title, String numberPerson, String description, String requestJob, String exp, String salary, String skill, String interest, String contact) {
        RegisterJobTask registerJobTask = new RegisterJobTask();
        registerJobTask.execute(new String[]{title, numberPerson, description, requestJob, exp, salary, skill, interest, contact});
    }

    public class RegisterJobTask extends AsyncTask<String, Void, Job> {

        @Override
        protected Job doInBackground(String... strings) {
            if (strings.length < 2)
                return null;
            String sendData[][] = {
                    {"title", strings[0]},
                    {"numberPerson", strings[1]},
                    {"description", strings[2]},
                    {"requestJob", strings[3]},
                    {"exp", strings[4]},
                    {"salary", strings[5]},
                    {"skill", strings[6]},
                    {"interest", strings[7]},
                    {"contact", strings[8]},
            };
            final String result = new ServerInteractor().sendRequest(1,
                    ServerInteractor.HOSTING_API + ServerInteractor.PAGE_INSERTJOB, sendData);

            Log.e(TAG, "reselt = " + result);
            return null;
        }
    }
}
