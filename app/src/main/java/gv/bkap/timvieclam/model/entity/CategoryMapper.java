package gv.bkap.timvieclam.model.entity;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import gv.bkap.timvieclam.model.server.ServerInteractor;

public class CategoryMapper {
    public static ArrayList<Category> getCategories(String data) throws JSONException {
        JSONArray rootJSON = new JSONArray(data);
        ArrayList<Category> lstCategories = new ArrayList<>();
        if (rootJSON != null && rootJSON.length() > 0) {
            for (int i = 0; i < rootJSON.length(); i++) {
                JSONObject jsonObject = rootJSON.getJSONObject(i);

                int id = Integer.parseInt(jsonObject.getString("id"));
                String name = jsonObject.getString("name");
                String icon_link = ServerInteractor.HOSTING_IMAGES + jsonObject.getString("icon_link");
                lstCategories.add(new Category(id, name, icon_link));
            }
        }
        Log.e("Debug xxx", "got " + lstCategories.size());
        return lstCategories;
    }
}
