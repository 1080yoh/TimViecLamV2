package gv.bkap.timvieclam.presenter.main;

import android.content.Context;

import gv.bkap.timvieclam.R;
import gv.bkap.timvieclam.model.ApplicationContext;
import gv.bkap.timvieclam.model.entity.Account;
import gv.bkap.timvieclam.view.main.IMainView;

public class MainPresenter implements IMainPresenter {

    private final String TAG = this.getClass().getSimpleName();
    private IMainView mainView;
    private Context applicationContext;

    public MainPresenter(IMainView mainView, Context applicationContext) {
        this.mainView = mainView;
        this.applicationContext = applicationContext;
    }

    @Override
    public void processOptionItemClick(int id) {

    }

    @Override
    public void processNavMenuClick(int id) {
        switch (id) {
            case R.id.nav_me:
                Account account = ((ApplicationContext) applicationContext).getAccount();
                if (account == null)
                    mainView.navigateToLoginActivity();
                else
                    mainView.navigateToInfoActivity();
                break;
            default:
                break;
        }
    }
}
