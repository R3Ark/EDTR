package com.eferm.edtr.View;

import android.app.Activity;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;

import com.eferm.edtr.R;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

/**
 * Created by R. R. Reyes on 3/26/2018.
 */

public class ScanView {

    private Activity activity;

    SurfaceView QRScanner;
    TextView TV_QRInfo, TV_Status;
    Button Btn_ForgotID;

    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;

    public ScanView(Activity activity) {
        this.activity = activity;

        Init();
    }

    void Init() {
        QRScanner = activity.findViewById(R.id.Camera_QR);
        TV_QRInfo = activity.findViewById(R.id.TV_QRInfo);
        TV_Status = activity.findViewById(R.id.TV_Status);
        Btn_ForgotID = activity.findViewById(R.id.Btn_ForgotID);

        barcodeDetector = new BarcodeDetector.Builder(activity)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        cameraSource = new CameraSource
                .Builder(activity, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .setFacing(CameraSource.CAMERA_FACING_FRONT)
                .build();
    }

    public void InitViews(int TimeType) {
        switch (TimeType) {
            case 1 :
                TV_Status.setText(R.string.timein);
                TV_Status.setTextColor(activity.getResources().getColor(R.color.tabTextColorB));
                break;
            case 2 :
                TV_Status.setText(R.string.breakout);
                TV_Status.setTextColor(activity.getResources().getColor(R.color.tabTextColorA));
                break;
            case 3 :
                TV_Status.setText(R.string.breakin);
                TV_Status.setTextColor(activity.getResources().getColor(R.color.tabTextColorB));
                break;
            case 4 :
                TV_Status.setText(R.string.timeout);
                TV_Status.setTextColor(activity.getResources().getColor(R.color.tabTextColorA));
                break;
        }
    }
}
