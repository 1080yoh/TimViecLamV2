package gv.bkap.timvieclam.view.main;

import java.util.ArrayList;

import gv.bkap.timvieclam.model.entity.Category;

public interface IMainView {
    void navigateToLoginActivity();

    void navigateToDetailCustomerActivity();

    void loadCategories(ArrayList<Category> lstCategories);

    void loadData();

    void setNavMenu(int idMenu);

    void showToast(String message);

    void showProgressDialog();

    void dismissProgressDialog();
}
