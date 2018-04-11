package com.eferm.edtr.Controller;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.eferm.edtr.Model.DTRData;
import com.eferm.edtr.Utils.BaseUtils;
import com.eferm.edtr.Utils.Constants;
import com.eferm.edtr.View.MainView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by R. R. Reyes on 3/26/2018.
 */

public class MainController {

    private Activity activity;
    private MainView mainView;

    private Handler realmHandler;
    private Runnable realmrun;

    private Realm realm;
    List<DTRData> dataList = new ArrayList<>();

    public MainController(Activity activity, MainView mainView) {
        this.activity = activity;
        this.mainView = mainView;
    }

    public void RealmRun(String date) {
        final String currentDate = date;
        realmHandler = new Handler();
        realmrun = new Runnable() {
            @Override
            public void run() {
                try {
                    realm.beginTransaction();
                    String yesterDate = Constants.DATE_FORMAT.format(BaseUtils.GetYesterday(currentDate));
                    String tomorrDate = Constants.DATE_FORMAT.format(BaseUtils.GetTomorrow(currentDate));
                    Log.e("==DT==", yesterDate + ", " + currentDate +", " + tomorrDate);
                    dataList = realm
                            .where(DTRData.class)
                            .beginGroup()
                            .equalTo("Date", tomorrDate)
                            .or()
                            .equalTo("Date", currentDate)
                            .or()
                            .equalTo("Date", yesterDate)
                            .endGroup()
                            .findAll();
                    mainView.SetupLogAdapter(dataList);
                    realm.commitTransaction();
                } catch (Exception ex) {
                    Log.e("==REALM==", ex.getMessage());
                }
            }
        };
        realmHandler.post(realmrun);
    }
}
