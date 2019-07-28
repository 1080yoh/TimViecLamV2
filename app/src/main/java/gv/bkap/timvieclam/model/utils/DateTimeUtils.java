package gv.bkap.timvieclam.model.utils;

import android.util.Log;

import java.util.HashMap;

public class DateTimeConverter {
    public static String YEAR = "YEAR";
    public static String MONTH = "MONTH";
    public static String DAY_OF_MONTH = "DAY_OF_MONTH";

    public static String HOURS = "HOUR";
    public static String MINUTES = "MINUTES";
    public static String SECONDS = "SECONDS";

    public static HashMap<String, Integer> MySqlDateTime_To_ArrDate(String data) {
        HashMap<String, Integer> lstDate = new HashMap<>();

        if (data == null) {
            Log.e("Debug xxx", "Date converter null");
            return lstDate;
        }

        data = data.trim();
        if (data.split(" ").length != 2) {
            Log.e("Debug xxx", "Date error:[" + data + "]");
            return lstDate;
        }

        String[] date = data.split(" ")[0].split("-");
        String[] time = data.split(" ")[1].split(":");

        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int dayOfMonth = Integer.parseInt(date[2]);

        int hours = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);
        int seconds = Integer.parseInt(time[2]);

        lstDate.put(YEAR, year);
        lstDate.put(MONTH, month);
        lstDate.put(DAY_OF_MONTH, dayOfMonth);

        lstDate.put(HOURS, hours);
        lstDate.put(MINUTES, minutes);
        lstDate.put(SECONDS, seconds);

        return lstDate;
    }

}
