package gv.bkap.timvieclam.model.entity;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gv.bkap.timvieclam.model.server.ServerInteractor;

public class AccountMapper {

    private static final String TAG = "AccountMapper";

    public static Account getAccount(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        if (rootJSON != null && rootJSON.length() > 0) {
            JSONObject jsonObject = rootJSON.getJSONObject(0);
            int id = Integer.parseInt(jsonObject.getString("id_account"));

            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            String name_displayed = jsonObject.getString("name_displayed");
            String avatar = ServerInteractor.HOSTING_IMAGES + jsonObject.getString("avatar");
            String emailContact = jsonObject.getString("email_contact");
            String address = jsonObject.getString("address");
            String phone = jsonObject.getString("phone");

            Log.e(TAG, "id=" + id + ", username=" + username + ", password=" + password + ", name_displayed=" + name_displayed + ", email_contact=" + emailContact + ",avatar=" + avatar + ", phone=" + phone);

            return new Account(id, username, password, name_displayed, emailContact, address, avatar, phone);
        }
        return null;
    }

    public static Account getSimpleAccount(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        if (rootJSON != null && rootJSON.length() > 0) {
            JSONObject jsonObject = rootJSON.getJSONObject(0);
            int id = Integer.parseInt(jsonObject.getString("id_account"));
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            Log.e(TAG, "id=" + id + ", username=" + username + ", password=" + password);

            return new Account(id, username, password);
        }
        return null;
    }
}
