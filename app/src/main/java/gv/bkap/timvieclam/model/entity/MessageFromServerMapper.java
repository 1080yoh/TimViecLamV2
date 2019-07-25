package gv.bkap.timvieclam.model.entity;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageFromServerMapper {

    private static final String TAG = "(MY DEBUG)MessageMapper";

    public static MessageFromServer mapMessageFromServer(String dataJson) throws JSONException {
        dataJson = "[" + dataJson + "]";
        JSONArray rootJSON = new JSONArray(dataJson);
        if (rootJSON != null && rootJSON.length() > 0) {
            JSONObject jsonObject = rootJSON.getJSONObject(0);

            int id = Integer.parseInt(jsonObject.getString("id"));
            String message = jsonObject.getString("message");
            Log.e(TAG, "id = " + id + ", message: " + message);
            return new MessageFromServer(id, message);
        }
        return null;
    }
}
