package gv.bkap.timvieclam.model.entity;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PostedJobItemMapper {
    public static ArrayList<PostedJobItem> getPostedJobsItem(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        ArrayList<PostedJobItem> lstPostedJobs = new ArrayList<>();

        if (rootJSON != null && rootJSON.length() > 0) {
            for (int i = 0; i < rootJSON.length(); i++) {
                JSONObject jsonObject = rootJSON.getJSONObject(i);

                int idJob = Integer.parseInt(jsonObject.getString("id_job"));
                int idCustomer = Integer.parseInt(jsonObject.getString("id_customer"));
                int quantity = Integer.parseInt(jsonObject.getString("number"));
                String jobName = jsonObject.getString("Name");
                String location = jsonObject.getString("location");
                String description = jsonObject.getString("describe");
                String contact = jsonObject.getString("contact");
                String salary = jsonObject.getString("salary");
                String postedDate = jsonObject.getString("date_post");

                lstPostedJobs.add(new PostedJobItem(idJob, idCustomer, jobName, location, quantity, description, contact, salary, postedDate));
            }
        }
        Log.e("Debug xxx", "got " + lstPostedJobs.size());
        return lstPostedJobs;
    }
}
