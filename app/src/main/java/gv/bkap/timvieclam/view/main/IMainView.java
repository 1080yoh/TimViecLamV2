package gv.bkap.timvieclam.view.main;

import java.util.ArrayList;

import gv.bkap.timvieclam.model.entity.Category;
import gv.bkap.timvieclam.model.entity.JobItem;

public interface IMainView {
    void navigateToLoginActivity();

    void navigateToDetailCustomerActivity();

    void loadCategories(ArrayList<Category> lstCategories);

    void loadJobItems(ArrayList<JobItem> lstJobItems);

    void navigateToRegisterJobActivity();

    void navigateToPostedJobsActivity();
    void loadData();

    void setNavMenu(int idMenu);

    void showToast(String message);

    void showProgressDialog();

    void dismissProgressDialog();
}
