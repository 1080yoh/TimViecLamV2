package gv.bkap.timvieclam.model.entity;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AccountMapper {

    private static final String TAG = "AccountMapper";

    public static Account getAccount(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        if (rootJSON != null && rootJSON.length() > 0) {
            JSONObject jsonObject = rootJSON.getJSONObject(0);
            int id = Integer.parseInt(jsonObject.getString("id_account"));
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            String email_restore = jsonObject.getString("email_restore");
            String name_displayed = jsonObject.getString("name_displayed");

            Log.e(TAG, "id=" + id + ", username=" + username + ", password=" + password + ",email_restore=" + email_restore + ",name_displayed=" + name_displayed);

            return new Account(id, username, password, name_displayed, email_restore);
        }
        return null;
    }
}
