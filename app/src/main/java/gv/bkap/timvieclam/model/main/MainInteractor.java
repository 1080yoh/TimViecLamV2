package gv.bkap.timvieclam.model.main;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;

import gv.bkap.timvieclam.model.entity.Category;
import gv.bkap.timvieclam.model.entity.CategoryMapper;
import gv.bkap.timvieclam.model.server.ServerInteractor;
import gv.bkap.timvieclam.presenter.main.IOnReceivedCategories;

public class MainInteractor implements IMainInteractor {

    IOnReceivedCategories iOnReceivedCategories;

    public MainInteractor(IOnReceivedCategories iOnReceivedCategories) {
        this.iOnReceivedCategories = iOnReceivedCategories;
    }

    @Override
    public void loadCategories() {
        LoadCategoriesTask loadCategoriesTask = new LoadCategoriesTask();
        loadCategoriesTask.execute();
    }

    private void receiveCategories(ArrayList<Category> lstCategories) {
        iOnReceivedCategories.onReceivedCategories(lstCategories);
    }

    private class LoadCategoriesTask extends AsyncTask<String, Void, ArrayList<Category>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Category> doInBackground(String... strings) {

            final String result = new ServerInteractor().sendRequest(
                    1, ServerInteractor.HOSTING_API + ServerInteractor.PAGE_LOAD_CATEGORY, null);
            try {
                Log.e("debug xxx", "Result got: " + result);
                return CategoryMapper.getCategories(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Category> lstCategories) {
            receiveCategories(lstCategories);
        }
    }
}
