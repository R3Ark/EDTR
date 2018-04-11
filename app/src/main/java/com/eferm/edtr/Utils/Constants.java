package com.eferm.edtr.Utils;

import android.content.Context;

import com.cloudinary.android.MediaManager;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by R. R. Reyes on 3/26/2018.
 */

public class Constants {

    public static String KEY_FIRST_RUN = "FirstRun";
    public static String KEY_COMPANY_ID = "CompanyID";
    public static String KEY_USER_ID = "UserID";
    public static String KEY_TOKEN = "Token";
    public static String KEY_LONGITUDE = "Longitude";
    public static String KEY_LATITUDE = "Latitude";
    public static String KEY_CURRENT_LONGITUDE = "CurrentLongitude";
    public static String KEY_CURRENT_LATITUDE = "CurrentLatitude";
    public static String KEY_LAST_SENT_DATA_COUNT = "LastSentDataCount";

    public static final String LOG_TAG = "==PERMISSION==";
    public static final String GRANTED = "Permission Granted!";
    public static final String DENIED = "Permission Denied!";

    public static final String TIME_TYPE = "TimeType";
    public static final String TAG_BARCODE = "BarcodeID";
    public static final String TAG_DATE = "TagDate";
    public static final String TAG_TIME = "TagTime";
    public static final String TAG_NAME = "TagName";
    public static final String TAG_TYPE = "TagType";

    public static final String WAIT = "PLEASE WAIT...";
    public static final String SUCCESS_LOG = "Log Successful";
    public static final String SUCCESS_SYNC = "Synchronization Successful";
    public static final String CAMERA_TRY = "Camera Error: Please Try Again";
    public static final String ERROR_LOGIN = "Error 001A: Login Failure";
    public static final String FAIL_LOGIN = "Error 001B: Invalid Credentials";
    public static final String FAIL_SYNC = "Error 002A: Invalid BranchID";
    public static final String FAIL_SYNC2 = "Error 002BA: Synchronization Fail";
    public static final String ERROR_SYNC = "Error 002C: Error Synchronization";
    public static final String DATE_ERROR = "Error 003A: End Date cannot be earlier than Start Date";
    public static final String SEND_ERROR = "Error 004A: No Data to Send";

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss", Locale.US);
    public static final SimpleDateFormat TIME2_FORMAT = new SimpleDateFormat("HH:mm", Locale.US);

    public static Map InitCloudinary(Context context) {
        Map config = new HashMap();
        try {
            config.put("cloud_name", "dutnyvgov");
            config.put("api_key", "234532754516521");
            config.put("api_secret", "DegIpobeySfK2B9_FncfzvC06ss");
            MediaManager.init(context, config);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return config;
    }
}
