package gv.bkap.timvieclam.model.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import gv.bkap.timvieclam.model.entity.Account;

public class MyLocalDatabase {
    private Context context;
    private SQLiteDatabase mDB;

    public MyLocalDatabase(Context context) {
        this.context = context;
        mDB = new SQLiteHelper(context).getWritableDatabase();
    }

    public ArrayList<String> getUsernameSaved() {
        ArrayList<String> lstUsername = new ArrayList<>();

        Cursor cursor = mDB.rawQuery("select * from " + SQLiteHelper.TB_USERNAME_SAVED, null);
        while (cursor.moveToNext()) {
            lstUsername.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COL_USERNAME)));
        }
        return lstUsername;
    }

    public Account getAccount(String username, String password) {
        Account account = null;

        Cursor cursor = mDB.query(SQLiteHelper.TB_ACCOUNT, null,
                "username = ? and password = ?", new String[]{username, password},
                null, null, null);
        if (cursor.moveToNext()) {
            account = new Account();
            account.setId(cursor.getInt(cursor.getColumnIndex("id")));
            account.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            account.setPassword(cursor.getString(cursor.getColumnIndex("password")));
        }
        return account;
    }

    public void disconnect() {
        if (mDB.isOpen())
            mDB.close();
    }
}
