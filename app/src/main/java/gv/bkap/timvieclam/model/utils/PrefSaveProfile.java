package gv.bkap.timvieclam.model.utils;

import android.content.Context;
import android.content.SharedPreferences;

import gv.bkap.timvieclam.model.entity.Account;

public class PrefSaveProfile {
    public static final String ID = "id";
    private static final String SHARED_PREFERENCES_NAME = "Account";
    private static final String FN = "fullname";
    private static final String US = "username";
    private static final String PW = "password";
    private static final String IM = "imagelink";
    private Context context;
    private SharedPreferences sharedPreferences;

    public PrefSaveProfile(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public Account getAccount() {//lấy customer từ file XML trong bộ nhớ trong
        int id;
        String name, username, pass, imagelink;
        id = sharedPreferences.getInt(ID, -1);
        name = sharedPreferences.getString(FN, "");
        username = sharedPreferences.getString(US, "");
        pass = sharedPreferences.getString(PW, "");
        imagelink = sharedPreferences.getString(IM, "");
        return new Account(id, username, pass, name, imagelink);
    }

    public void removeAccount() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void saveAccount(Account account) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(ID, account.getId());
        editor.putString(FN, account.getName_displayed());
        editor.putString(US, account.getUsername());
        editor.putString(PW, account.getPassword());
        editor.putString(IM, account.getAvatar());
        editor.apply();
    }

    public void changePassword(String newPass) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(PW);
        editor.putString(PW, newPass);
        editor.apply();
    }
}

