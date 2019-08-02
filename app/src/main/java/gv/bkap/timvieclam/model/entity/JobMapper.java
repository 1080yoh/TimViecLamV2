package gv.bkap.timvieclam.model.entity;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JobMapper {
    private static final String TAG = "AccountMapper";

    public static Job getJob(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        if (rootJSON != null && rootJSON.length() > 0) {
            JSONObject jsonObject = rootJSON.getJSONObject(0);
            int idJob = Integer.parseInt(jsonObject.getString("id_job"));
            int idCustormer = Integer.parseInt(jsonObject.getString("id_custormer"));
            int idListword = Integer.parseInt(jsonObject.getString("id_listword"));
            String nameJob = jsonObject.getString("name_job");
            String request = jsonObject.getString("request");
            String salary = jsonObject.getString("salary");
            String describe = jsonObject.getString("describe");
            int amount = Integer.parseInt(jsonObject.getString("amount"));

            Log.e(TAG, "idJob=" + idJob + ", idCustomer= " + idCustormer + ", idListword= " + idListword + ", nameJob=" + nameJob);
            Log.e(TAG, "request=" + request);
            Log.e(TAG, "salary=" + salary + ", describe= " + describe + ", số lượng tuyển = " + amount);

            return new Job(idJob, idCustormer, idListword, nameJob, request, salary, describe, amount);
        }
        return null;
    }

    public static ArrayList<Job> getDetailJob(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        ArrayList<Job> lstJob = new ArrayList<>();
        if (rootJSON != null && rootJSON.length() > 0) {
            JSONObject jsonObject = rootJSON.getJSONObject(0);
            int idJob = Integer.parseInt(jsonObject.getString("id_job"));
            int idListword = Integer.parseInt(jsonObject.getString("id_category"));
            String nameJob = jsonObject.getString("Name");
            String request = jsonObject.getString("Request");
            String salary = jsonObject.getString("salary");
            String describe = jsonObject.getString("describe");
            int numberPerson = Integer.parseInt(jsonObject.getString("number"));
            String exp = jsonObject.getString("exp");
            String location = jsonObject.getString("location");
            String interest = jsonObject.getString("interest");


            Log.e(TAG, "idJob=" + idJob + ", idcategory= " + idListword + ", exp= " + exp + ", nameJob=" + nameJob + ", request=" + request + ", salary=" + salary + ", describe= " + describe + ", số lượng tuyển = " + numberPerson);


            lstJob.add(new Job(idJob, idListword, nameJob, request, salary, describe, location, numberPerson, exp, interest));
        }
        return lstJob;
    }

}
