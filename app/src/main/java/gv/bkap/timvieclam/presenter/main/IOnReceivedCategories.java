package gv.bkap.timvieclam.presenter.main;

import java.util.ArrayList;

import gv.bkap.timvieclam.model.entity.Category;

public interface IOnReceivedCategories {
    void onReceivedCategories(ArrayList<Category> lstCategories);
}
