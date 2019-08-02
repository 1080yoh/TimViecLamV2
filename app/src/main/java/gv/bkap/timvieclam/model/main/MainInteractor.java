package gv.bkap.timvieclam.model.main;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;

import gv.bkap.timvieclam.model.entity.Category;
import gv.bkap.timvieclam.model.entity.CategoryMapper;
import gv.bkap.timvieclam.model.entity.Job;
import gv.bkap.timvieclam.model.entity.JobItem;
import gv.bkap.timvieclam.model.entity.JobItemMapper;
import gv.bkap.timvieclam.model.entity.JobMapper;
import gv.bkap.timvieclam.model.server.ServerInteractor;
import gv.bkap.timvieclam.presenter.main.IOnReceivedCategories;
import gv.bkap.timvieclam.presenter.main.IOnReceivedJobItems;
import gv.bkap.timvieclam.presenter.main.MainPresenter;

public class MainInteractor implements IMainInteractor {

    IOnReceivedCategories iOnReceivedCategories;
    IOnReceivedJobItems iOnReceivedJobItems;

    public MainInteractor(MainPresenter mainPresenter) {
        this.iOnReceivedCategories = mainPresenter;
        this.iOnReceivedJobItems = mainPresenter;
    }

    @Override
    public void loadCategories() {
        LoadCategoriesTask loadCategoriesTask = new LoadCategoriesTask();
        loadCategoriesTask.execute();
    }

    @Override
    public void loadJobItems() {
        LoadItemJob loadItemJob = new LoadItemJob();
        loadItemJob.execute();
    }

    @Override
    public void loadJobItems(int category) {
        LoadItemJob loadItemJob = new LoadItemJob();
        loadItemJob.execute(category + "");
    }

    @Override
    public void detailJobItem(int idJob) {
        LoadDetailItem loadDetailItem = new LoadDetailItem();
        loadDetailItem.execute(idJob + "");
    }

    private void deliverCategories(ArrayList<Category> lstCategories) {
        iOnReceivedCategories.onReceivedCategories(lstCategories);
    }

    private void deliverJobItems(ArrayList<JobItem> lstJobItems) {
        iOnReceivedJobItems.onReceivedJobItems(lstJobItems);

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
            deliverCategories(lstCategories);
        }
    }

    private class LoadDetailItem extends AsyncTask<String, Void, ArrayList<Job>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Job> doInBackground(String... strings) {
            String result;
            String sendData[][] = {
                    {"id_job", strings[0]}
            };
            result = new ServerInteractor().sendRequest(
                    1, ServerInteractor.HOSTING_API + ServerInteractor.PAGE_LOADJOB, sendData);
            try {
                Log.e("debug xxx", "Result got: " + result);
                return JobMapper.getDetailJob(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private class LoadItemJob extends AsyncTask<String, Void, ArrayList<JobItem>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<JobItem> doInBackground(String... strings) {
            String result;
            if (strings != null && strings.length != 0) {
                String sendData[][] = {
                        {"id_category", strings[0]}
                };
                result = new ServerInteractor().sendRequest(
                        1, ServerInteractor.HOSTING_API + ServerInteractor.PAGE_FILTER_JOB, sendData);
            } else {
                result = new ServerInteractor().sendRequest(
                        1, ServerInteractor.HOSTING_API + ServerInteractor.PAGE_LOAD_JOB_ITEM, null);
            }
            try {
                Log.e("debug xxx", "Result got: " + result);
                return JobItemMapper.getJobItems(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<JobItem> lstJobItems) {
            deliverJobItems(lstJobItems);
        }
    }


}
