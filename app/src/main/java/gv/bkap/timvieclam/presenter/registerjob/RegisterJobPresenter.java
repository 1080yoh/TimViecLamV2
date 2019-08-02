package gv.bkap.timvieclam.presenter.registerjob;

import android.os.Handler;

import gv.bkap.timvieclam.model.registerjob.IRegisterJobInteractor;
import gv.bkap.timvieclam.model.registerjob.RegisterJobInteractor;
import gv.bkap.timvieclam.view.registerjob.IRegisterJobView;

public class RegisterJobPresenter implements IRegisterJobPresenter {
    private IRegisterJobView registerJobView;
    private IRegisterJobInteractor registerJobInteractor;

    public RegisterJobPresenter(IRegisterJobView registerJobView) {
        this.registerJobView = registerJobView;
        registerJobInteractor = new RegisterJobInteractor(this);
    }


    @Override
    public void registerJob(final String title, final String numberPerson, final String description, final String requestJob, final String exp, final String salary, final String skill, final String interest, final String contact) {
        registerJobView.resetRegisterError();
        boolean error = false;
        title.trim();
        if (title.length() == 0) {
            registerJobView.setTitleError("Tiêu đề không được chứa khoẳng trắng hoặc để trống");
            error = true;
        }
        if (numberPerson.length() == 0) {
            registerJobView.setNumberError("mời nhập lại số người cần tuyển");
            error = true;
        }

        description.trim();
        requestJob.trim();
        interest.trim();
        contact.trim();
        if (!error) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    registerJobInteractor.registerJob(title, numberPerson, description, requestJob, exp, salary, skill, interest, contact);
                }
            }, 1000);
            registerJobView.registerSucess();
        }
    }


}
