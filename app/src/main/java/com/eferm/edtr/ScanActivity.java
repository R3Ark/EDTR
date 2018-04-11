package com.eferm.edtr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eferm.edtr.Utils.Constants;
import com.eferm.edtr.View.ScanView;

public class ScanActivity extends AppCompatActivity {

    private ScanView scanView;

    private int TimeType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        scanView = new ScanView(this);
        Init();
    }

    void Init() {
        Intent i = getIntent();
        TimeType = i.getIntExtra(Constants.TIME_TYPE, 0);

        scanView.InitViews(TimeType);
    }
}
