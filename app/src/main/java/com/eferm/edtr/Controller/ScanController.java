package com.eferm.edtr.Controller;

import android.app.Activity;

import io.realm.Realm;

/**
 * Created by R. R. Reyes on 3/26/2018.
 */

public class ScanController {

    private Activity activity;

    Realm realm;

    public ScanController(Activity activity) {
        this.activity = activity;

        InitController();
    }

    void InitController() {
        Realm.init(activity);

        realm = Realm.getDefaultInstance();
    }
}
