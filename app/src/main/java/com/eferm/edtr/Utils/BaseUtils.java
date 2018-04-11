package com.eferm.edtr.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by R. R. Reyes on 3/26/2018.
 */

public class BaseUtils {

    public static void GetPermissions(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(
                    activity,
                    new String[]{
                            Manifest.permission.INTERNET,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_CALENDAR,
                            Manifest.permission.WRITE_CALENDAR,
                            Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.CHANGE_WIFI_STATE,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    1);
        }
        else {
            // Permission automatically granted on sdk<23 upon installation
            Log.v(Constants.LOG_TAG, Constants.GRANTED);
        }
    }

    public static Date GetYesterday(String date) {
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        Log.e("==DT==", year + " - " + month + " - " + day);
        cal.set(year, month - 1, day);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public static Date GetTomorrow(String date) {
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        Log.e("==DT==", year + " - " + month + " - " + day);
        cal.set(year, month - 1, day);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    public static int GetHour(String time) {
        return Integer.parseInt(time.substring(0, 2));
    }

    public static boolean IsAutoTime(Context context) {
        int auto_time = 0;
        try {
            if(Build.VERSION.SDK_INT >= 17) {
                auto_time = Settings.Global.getInt(context.getContentResolver(), Settings.Global.AUTO_TIME, 0);
            } else {
                auto_time = Settings.System.getInt(context.getContentResolver(), Settings.System.AUTO_TIME, 0);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(auto_time != 0) {
            return true;
        } else {
            return false;
        }
    }
}
