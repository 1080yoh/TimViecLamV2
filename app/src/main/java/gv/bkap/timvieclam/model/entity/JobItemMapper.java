package gv.bkap.timvieclam.model.entity;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import gv.bkap.timvieclam.model.server.ServerInteractor;
import gv.bkap.timvieclam.model.utils.DateTimeUtils;

public class JobItemMapper {

    public static ArrayList<JobItem> getJobItems(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        ArrayList<JobItem> lstCategories = new ArrayList<>();
        if (rootJSON != null && rootJSON.length() > 0) {
            for (int i = 0; i < rootJSON.length(); i++) {
                JSONObject jsonObject = rootJSON.getJSONObject(i);

                int id = Integer.parseInt(jsonObject.getString("id_job"));
                int id_category = Integer.parseInt(jsonObject.getString("id_category"));
                String avatar = ServerInteractor.HOSTING_IMAGES + jsonObject.getString("avatar");
                String jobName = jsonObject.getString("Name");
                String name_displayed = jsonObject.getString("name_displayed");
                String location = jsonObject.getString("location");
                String salary = jsonObject.getString("salary");
                String date_post = jsonObject.getString("date_post");

                lstCategories.add(new JobItem(id, avatar, jobName, name_displayed, location, salary, DateTimeUtils.timeUpToNow(date_post), id_category));
            }
        }
        Log.e("Debug xxx", "got itemjob: " + lstCategories.size());
        return lstCategories;
    }
}
