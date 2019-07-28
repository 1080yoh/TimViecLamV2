package gv.bkap.timvieclam.model.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DB_NAME = "TimViecDB.sqlite";

    public static final String TB_USERNAME_SAVED = "username_saved";
    public static final String TB_ACCOUNT = "account";
    public static final String TB_PERSONAL = "personal";
    public static final String TB_COMPANY = "company";
    public static final String COL_IDACCOUNT = "id";
    public static final String COL_USERNAME = "username";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;


        // Bảng lưu các tài khoản đã đăng nhập
        sql = "create table " + TB_USERNAME_SAVED + " (" + COL_USERNAME + " varchar(20) unique)";
        db.execSQL(sql);


        // Bảng tài khoản
        // Quy ước 0 là cá nhân, 1 là công ty
        sql = "CREATE TABLE "+TB_ACCOUNT+" (\n" +
                "    id       INTEGER      PRIMARY KEY AUTOINCREMENT,\n" +
                "    username VARCHAR (20) UNIQUE\n" +
                "                          NOT NULL,\n" +
                "    password VARCHAR (20) NOT NULL,\n" +
                "    type     INTEGER\n" +
                ");\n";
        db.execSQL(sql);


        // Bảng chi tiết cá nhân
        sql = "CREATE TABLE "+TB_PERSONAL+" (\n" +
                "    id         INTEGER        PRIMARY KEY AUTOINCREMENT,\n" +
                "    id_account INTEGER        NOT NULL\n" +
                "                              UNIQUE,\n" +
                "    avatar     VARCHAR (128),\n" +
                "    name       VARCHAR (32),\n" +
                "    address    VARCHAR (128),\n" +
                "    longitude  DOUBLE,\n" +
                "    latitude   DOUBLE,\n" +
                "    info       VARCHAR (1024),\n" +
                "    number     VARCHAR (20),\n" +
                "    FOREIGN KEY (\n" +
                "        id_account\n" +
                "    )\n" +
                "    REFERENCES account (id) \n" +
                ");\n";
        db.execSQL(sql);

        // Bảng chi tiết công ty
        sql = "CREATE TABLE "+TB_COMPANY+" (\n" +
                "    id         INTEGER        PRIMARY KEY AUTOINCREMENT,\n" +
                "    id_account                REFERENCES account (id),\n" +
                "    logo       VARCHAR (128),\n" +
                "    name       VARCHAR (64)   NOT NULL,\n" +
                "    address    VARCHAR (128)  NOT NULL,\n" +
                "    longitude  DOUBLE,\n" +
                "    latitude   DOUBLE,\n" +
                "    email      VARCHAR (64),\n" +
                "    number     VARCHAR (20),\n" +
                "    website    VARCHAR (32),\n" +
                "    info       VARCHAR (1024) NOT NULL\n" +
                ");\n";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
