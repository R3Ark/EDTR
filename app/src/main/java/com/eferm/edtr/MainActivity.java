package com.eferm.edtr;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.eferm.edtr.Controller.MainController;
import com.eferm.edtr.Fragments.DatePickerFragment;
import com.eferm.edtr.Utils.BaseUtils;
import com.eferm.edtr.Utils.Constants;
import com.eferm.edtr.View.MainView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private MainView mainView;
    private MainController mainController;

    private Handler handler;
    private Runnable runnable;
    private boolean mStopHandler = false;

    Calendar calendar;
    String currentDate;
    String currentViewDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainView = new MainView(this);
        mainController = new MainController(this, mainView);

        handler = new Handler();
        runnable = timeRunnable();
        handler.post(runnable);

        mainController.RealmRun(currentDate);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = new GregorianCalendar(year, month, dayOfMonth);
        SetDate(cal, year, month, dayOfMonth);
    }

    public void Btn_Click(View view) {
        mainView.Click(view);
    }

    public void DatePicker(View view) {
        if((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED)) {
            try {
                DatePickerFragment fragment = new DatePickerFragment();
                fragment.show(getFragmentManager(), "DATE");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            if (Build.VERSION.SDK_INT >= 23) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                Manifest.permission.READ_CALENDAR,
                                Manifest.permission.WRITE_CALENDAR
                        },
                        1);
            }
            else {
                // Permission Automatically Granted on SDK < 23 upon Installation
                Log.v(Constants.LOG_TAG, Constants.GRANTED);
            }
        }
    }

    private void SetDate(final Calendar calendar, int year, int month, int day) {
        final String date = Constants.DATE_FORMAT.format(calendar.getTime());
        int dateInt = (year * 10000) + ((month + 1) * 100) + (day);
        Log.i("==DATEINT==", dateInt + "");
        currentViewDate = date;
        mainView.SetViewDate(date);
        mainController.RealmRun(currentViewDate);
    }

    private Runnable timeRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                calendar = Calendar.getInstance();
                currentDate = Constants.DATE_FORMAT.format(new Date());
                String currentTime = Constants.TIME_FORMAT.format(calendar.getTime());
                String dateTime = new StringBuilder()
                        .append(currentDate)
                        .append(" : ")
                        .append(currentTime)
                        .toString();
                try {
                    if(currentViewDate == null) {
                        currentViewDate = currentDate;
                        mainView.SetViewDate(currentViewDate);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    mainView.SetDateTime(dateTime);
                    mainView.SetDTRStatus(BaseUtils.IsAutoTime(getApplicationContext()));
                }

                if (!mStopHandler) {
                    handler.postDelayed(this, 1000);
                }
            }
        };
    }
}
