package gv.bkap.timvieclam.presenter.main;

import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;

import gv.bkap.timvieclam.R;
import gv.bkap.timvieclam.model.ApplicationContext;
import gv.bkap.timvieclam.model.entity.Account;
import gv.bkap.timvieclam.model.entity.Category;
import gv.bkap.timvieclam.model.main.IMainInteractor;
import gv.bkap.timvieclam.model.main.MainInteractor;
import gv.bkap.timvieclam.view.main.IMainView;

public class MainPresenter implements IMainPresenter, IOnReceivedCategories {

    private final String TAG = this.getClass().getSimpleName();
    private IMainView mainView;
    private ApplicationContext applicationContext;
    private IMainInteractor iMainInteractor;

    public MainPresenter(IMainView mainView, Context applicationContext) {
        this.mainView = mainView;
        this.applicationContext = (ApplicationContext) applicationContext;
        iMainInteractor = new MainInteractor(this);
    }

    @Override
    public void processOptionItemClick(int id) {

    }

    @Override
    public void loadCategories() {
        mainView.showProgressDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iMainInteractor.loadCategories();
            }
        }, 1000);
    }

    @Override
    public void processNavMenuClick(int id) {
        switch (id) {
            case R.id.nav_me:
                Account account = applicationContext.getAccount();
                if (account != null && account.getId() != -1)
                    mainView.navigateToDetailCustomerActivity();
                else
                    mainView.navigateToLoginActivity();
                break;
            case R.id.nav_logout:
                applicationContext.removeAccount();
                mainView.loadData();
                mainView.showToast("Đăng xuất thành công!");
            default:
                break;
        }
    }

    @Override
    public void onReceivedCategories(ArrayList<Category> lstCategories) {
        mainView.loadCategories(lstCategories);
        mainView.dismissProgressDialog();
    }
}
