package com.eferm.edtr.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by R. R. Reyes on 3/26/2018.
 */

public class SharedData {

    SharedPreferences sp;

    public SharedData(Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean GetFirstTime() {
        return sp.getBoolean(Constants.KEY_FIRST_RUN, true);
    }

    public void SetFirstTime(boolean set) {
        sp.edit().putBoolean(Constants.KEY_FIRST_RUN, set).apply();
    }

    /*public int GetBranchID() {
        return sp.getInt(Constants.KEY_BRANCH_ID, 0);
    }

    public void SetBranchID(int set) {
        sp.edit().putInt(Constants.KEY_BRANCH_ID, set).apply();
    }*/

    public int GetCompanyID() {
        return sp.getInt(Constants.KEY_COMPANY_ID, 0);
    }

    public void SetCompanyID(int set) {
        sp.edit().putInt(Constants.KEY_COMPANY_ID, set).apply();
    }

    public String GetToken() {
        return sp.getString(Constants.KEY_TOKEN, null);
    }

    public void SetToken(String set) {
        sp.edit().putString(Constants.KEY_TOKEN, set).apply();
    }

    public int GetUserID() {
        return sp.getInt(Constants.KEY_USER_ID, 0);
    }

    public void SetUserID(int set) {
        sp.edit().putInt(Constants.KEY_USER_ID, set).apply();
    }

    public double GetLongitude() {
        return sp.getFloat(Constants.KEY_LONGITUDE, 0.0f);
    }

    public void SetLongitude(double set) {
        sp.edit().putFloat(Constants.KEY_LONGITUDE, (float)set).apply();
    }

    public double GetLatitude() {
        return sp.getFloat(Constants.KEY_LATITUDE, 0.0f);
    }

    public void SetLatitude(double set) {
        sp.edit().putFloat(Constants.KEY_LATITUDE, (float)set).apply();
    }

    public double GetCurrentLongitude() {
        return sp.getFloat(Constants.KEY_CURRENT_LONGITUDE, 0.0f);
    }

    public void SetCurrentLongitude(double set) {
        sp.edit().putFloat(Constants.KEY_CURRENT_LONGITUDE, (float)set).apply();
    }

    public double GetCurrentLatitude() {
        return sp.getFloat(Constants.KEY_CURRENT_LATITUDE, 0.0f);
    }

    public void SetCurrentLatitude(double set) {
        sp.edit().putFloat(Constants.KEY_CURRENT_LATITUDE, (float)set).apply();
    }

    public int GetLastDTRCount() {
        return sp.getInt(Constants.KEY_LAST_SENT_DATA_COUNT, 0);
    }

    public void SetLastDTRCount(int set) {
        sp.edit().putInt(Constants.KEY_LAST_SENT_DATA_COUNT, set).apply();
    }
}
