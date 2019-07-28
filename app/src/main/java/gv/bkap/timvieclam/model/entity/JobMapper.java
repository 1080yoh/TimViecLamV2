package gv.bkap.timvieclam.model.entity;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public static Job JobRegisterManager(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        if (rootJSON != null && rootJSON.length() > 0) {
            JSONObject jsonObject = rootJSON.getJSONObject(0);
            int idJob = Integer.parseInt(jsonObject.getString("id_job"));
            int idCustormer = 11;
            int idListword = 4;
            String nameJob = jsonObject.getString("name_job");
            String request = jsonObject.getString("request");
            String salary = jsonObject.getString("salary");
            String describe = jsonObject.getString("describe");
            int amount = Integer.parseInt(jsonObject.getString("amount"));
            String interest = jsonObject.getString("interest");

            Log.e(TAG, "idJob=" + idJob + ", idCustomer= " + idCustormer + ", idListword= " + idListword + ", nameJob=" + nameJob);
            Log.e(TAG, "request=" + request);
            Log.e(TAG, "salary=" + salary + ", describe= " + describe + ", số lượng tuyển = " + amount);
            Log.e(TAG, "quyền lợi" + interest);

            return new Job(idJob, idCustormer, idListword, nameJob, request, salary, describe, amount, interest);
        }
        return null;
    }

}
