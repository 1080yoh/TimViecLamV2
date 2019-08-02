package gv.bkap.timvieclam.model.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date1 = myFormat.parse(dateTimeMySql);
            Date date2 = new Date();
            final long diff = date2.getTime() - date1.getTime();
            final long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            final long hours = TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
            final long minutes = TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
            // Log.e("Debug time", "Days:" + days + ", hour:" + hours + ", minutes:" + minutes);

            if (days > 365)
                return (days / 365) + " năm trước";
            else if (days > 30)
                return (days / 30) + " tháng trước";
            else if (days > 0)
                return days + " ngày trước";
            else if (days == 0) {
                if (hours > 0)
                    return hours + " giờ trước";
                else
                    return minutes + " phút trước";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "Time error";
    }

//    public static String timeUpToNow(String dateTimeMySql) {
//        HashMap<String, Integer> mapDateTime = DateTime_Separate(dateTimeMySql);
//        if (mapDateTime.size() == 0) {
//            return "Không tính được thời gian";
//        }
//
//        Calendar calendar = Calendar.getInstance();
//        final int years = calendar.get(Calendar.YEAR) - mapDateTime.get(YEAR);
//        final int months = calendar.get(Calendar.MONTH) + 1 - mapDateTime.get(MONTH);
//        final int days = calendar.get(Calendar.DAY_OF_MONTH) - mapDateTime.get(DAY_OF_MONTH);
//        final int hours = calendar.get(Calendar.HOUR_OF_DAY) - mapDateTime.get(HOUR);
//        final int minutes = calendar.get(Calendar.MINUTE) - mapDateTime.get(MINUTE);
//
//
//        if (years > 0) {
//
//            if (years == 1 && months == -11)
//                return (31 )
//
//            return years + " năm trước";
//        }
//        if (months > 0) {
//            return months + " tháng trước";
//        }
//
//        if (days > 0 && days <= 28) {
//            return days + " ngày trước";
//        }
//
//        // 24h
//        if (hours > 0) {
//            return hours + " giờ trước";
//        }
//
//        return minutes + " phút trước";
//    }

}
