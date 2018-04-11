package com.eferm.edtr.View;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eferm.edtr.Adapters.MainLogAdapter;
import com.eferm.edtr.Model.DTRData;
import com.eferm.edtr.R;
import com.eferm.edtr.ScanActivity;
import com.eferm.edtr.Utils.BaseUtils;
import com.eferm.edtr.Utils.Constants;
import com.eferm.edtr.Utils.DTRDataSorter;
import com.eferm.edtr.Utils.SharedData;

import java.util.List;

/**
 * Created by R. R. Reyes on 3/26/2018.
 */

public class MainView {

    private Activity activity;

    private ImageView Btn_Settings;
    private LinearLayout LL_Screen, LL_DeviceStatus, Btn_TimeIn, Btn_TimeOut, Btn_BreakIn, Btn_BreakOut;
    private TextView TV_DateTime, Btn_CurrentDate;
    private RecyclerView RV_DTRView;

    MainLogAdapter MLAdapter;

    SharedData sd;

    public MainView(Activity activity) {
        this.activity = activity;

        InitViews();
        SetupViews();
    }

    public void Click(View view) {
        if (view == Btn_Settings) {
            BaseUtils.GetPermissions(activity);
            if((ContextCompat.checkSelfPermission(activity, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)
                    && (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                    && (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                    && (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED)
                    && (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    ) {
                ShowSettings();
            }
        } else {
            if(BaseUtils.IsAutoTime(activity)) {
                Intent i = new Intent(activity, ScanActivity.class);
                if (view == Btn_TimeIn) {
                    i.putExtra(Constants.TIME_TYPE, 1);
                }
                if (view == Btn_BreakOut) {
                    i.putExtra(Constants.TIME_TYPE, 2);
                }
                if (view == Btn_BreakIn) {
                    i.putExtra(Constants.TIME_TYPE, 3);
                }
                if (view == Btn_TimeOut) {
                    i.putExtra(Constants.TIME_TYPE, 4);
                }
                activity.startActivity(i);
            }
        }
    }

    public void SetDTRStatus(boolean status) {
        if(status) {
            LL_DeviceStatus.setBackgroundColor(activity.getResources().getColor(R.color.tabTextColorB));
        } else {
            LL_DeviceStatus.setBackgroundColor(activity.getResources().getColor(R.color.tabTextColorA));
        }
    }

    public void SetDateTime(String dateTime) {
        TV_DateTime.setText(dateTime);
    }

    public void SetViewDate(String date) {
        Btn_CurrentDate.setText(date);
    }

    public void SetupLogAdapter(List<DTRData> dataList) {
        DTRDataSorter dtrSorter = new DTRDataSorter();
        MLAdapter = new MainLogAdapter(activity.getApplicationContext(), dtrSorter.SortData(dataList));
        LinearLayoutManager LLManager = new LinearLayoutManager(activity.getApplicationContext());
        LLManager.setOrientation(LinearLayoutManager.VERTICAL);
        LLManager.setStackFromEnd(false);
        RV_DTRView.setLayoutManager(LLManager);
        RV_DTRView.setAdapter(MLAdapter);
        RV_DTRView.scrollToPosition(MLAdapter.getItemCount() - 1);
        MLAdapter.notifyDataSetChanged();
    }

    void InitViews() {
        LL_Screen = activity.findViewById(R.id.LL_Screen);
        LL_DeviceStatus = activity.findViewById(R.id.LL_DeviceStatus);
        Btn_CurrentDate = activity.findViewById(R.id.Btn_CurrentDate);
        Btn_Settings = activity.findViewById(R.id.Btn_Settings);
        Btn_TimeIn = activity.findViewById(R.id.Btn_TimeIn);
        Btn_BreakOut = activity.findViewById(R.id.Btn_BreakOut);
        Btn_BreakIn = activity.findViewById(R.id.Btn_BreakIn);
        Btn_TimeOut = activity.findViewById(R.id.Btn_TimeOut);

        RV_DTRView = activity.findViewById(R.id.RV_DTRView);
        TV_DateTime = activity.findViewById(R.id.TV_DateTime);
    }

    void SetupViews() {
        if(sd.GetFirstTime()) {
            LL_Screen.setVisibility(View.VISIBLE);
            Btn_TimeIn.setVisibility(View.INVISIBLE);
            Btn_BreakOut.setVisibility(View.INVISIBLE);
            Btn_BreakIn.setVisibility(View.INVISIBLE);
            Btn_TimeOut.setVisibility(View.INVISIBLE);
        } else {
            LL_Screen.setVisibility(View.GONE);
        }
    }

    void ShowSettings() {

    }
}
