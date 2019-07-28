package gv.bkap.timvieclam.model.utils;

import android.util.Log;

import java.util.Calendar;
import java.util.HashMap;

public class DateTimeUtils {
    public static String YEAR = "YEAR";
    public static String MONTH = "MONTH";
    public static String DAY_OF_MONTH = "DAY_OF_MONTH";

    public static String HOUR = "HOUR";
    public static String MINUTE = "MINUTE";
    public static String SECOND = "SECOND";

    public static HashMap<String, Integer> DateTime_Separate(String data) {
        HashMap<String, Integer> mapDateTime = new HashMap<>();

        if (data == null) {
            Log.e("Debug xxx", "Date converter null");
            return mapDateTime;
        }

        data = data.trim();
        if (data.split(" ").length != 2) {
            Log.e("Debug xxx", "Date error:[" + data + "]");
            return mapDateTime;
        }

        String[] date = data.split(" ")[0].split("-");
        String[] time = data.split(" ")[1].split(":");

        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int dayOfMonth = Integer.parseInt(date[2]);

        int hours = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);
        int seconds = Integer.parseInt(time[2]);

        mapDateTime.put(YEAR, year);
        mapDateTime.put(MONTH, month);
        mapDateTime.put(DAY_OF_MONTH, dayOfMonth);

        mapDateTime.put(HOUR, hours);
        mapDateTime.put(MINUTE, minutes);
        mapDateTime.put(SECOND, seconds);

        return mapDateTime;
    }

    public static String timeUpToNow(String dateTimeMySql) {
        HashMap<String, Integer> mapDateTime = DateTime_Separate(dateTimeMySql);
        if (mapDateTime.size() == 0) {
            return "Không tính được thời gian";
        }

        Calendar calendar = Calendar.getInstance();

        int years = calendar.get(Calendar.YEAR) - mapDateTime.get(YEAR);
        if (years > 0) {
            return years + " năm trước";
        }

        int months = calendar.get(Calendar.MONTH) + 1 - mapDateTime.get(MONTH);
        if (months > 0) {
            return months + " tháng trước";
        }

        int days = calendar.get(Calendar.DAY_OF_MONTH) - mapDateTime.get(DAY_OF_MONTH);
        if (days > 0) {
            return days + " ngày trước";
        }

        // 24h
        int hours = calendar.get(Calendar.HOUR_OF_DAY) - mapDateTime.get(HOUR);
        if (hours > 0) {
            return hours + " giờ trước";
        }

        int minutes = calendar.get(Calendar.MINUTE) - mapDateTime.get(MINUTE);
        return minutes + " phút trước";
    }

}
