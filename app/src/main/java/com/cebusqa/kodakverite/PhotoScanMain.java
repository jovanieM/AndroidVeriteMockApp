package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Cebu SQA on 22/06/2016.
 */
public class PhotoScanMain extends Activity{
    TextView scanTv;
    RelativeLayout scanRelOut;
    boolean test = false;
    ImageButton settingsIcon;
    private Button mBack;
    boolean cancelOK = false;
    KodakVeriteApp kodakVeriteApp;
    TextView photoQuality, photoColor, photoDocument;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_scan_main);

        kodakVeriteApp= new KodakVeriteApp();

        mBack = (Button) findViewById(R.id.back);
        scanTv = (TextView) findViewById(R.id.touchScan);
        scanRelOut = (RelativeLayout) findViewById(R.id.scan);
        settingsIcon = (ImageButton) findViewById(R.id.pscanSettingsIcon);


        photoQuality = (TextView) findViewById(R.id.photo_quality);
        photoColor = (TextView) findViewById(R.id.photo_color);
        photoDocument = (TextView) findViewById(R.id.photo_type);

        settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Scan_Photo_Settings.class));
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        scanTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test = false;
                exec();
            }
        });
        exec();
    }
    public void touchScan(View v){

        if (v.getId()==R.id.scan){
            test = false;
            exec();
        }
    }

    public void exec (){
        final ScanPhotoDialog scanDialog = ScanPhotoDialog.newInstance("Scan Photo");
        scanDialog.setCancelable(false);
        scanDialog.show(getFragmentManager(),"scan");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(test){
                    Thread.currentThread().interrupt();
                    //
                    new ScanCanceledAlert().newInstance("Scan Canceled").show(getFragmentManager(),"dialog");
                    //scanDialog.dismiss();
                }else{
                    finish();
                    startActivity(new Intent(PhotoScanMain.this, SP_000.class));
                }
            }
        },4000);
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                scanDialog.show(getFragmentManager(),"scan");
//                try {
//                    Thread.sleep(4000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                if(test){
//                    new ScanCanceledAlert().newInstance("Scan Canceled").show(getFragmentManager(),"dialog");
//
//                    scanDialog.dismiss();
//                }else{
//                    startActivity(new Intent(PhotoScanMain.this, SP_000.class));
//                }
//            }
//        });
//        t.start();
//
    }

    @Override
    protected void onResume() {
        super.onResume();

        photoQuality.setText(kodakVeriteApp.getScanPhotoSettingQuality());
        photoColor.setText(kodakVeriteApp.getScanPhotoSettingColor());
        photoDocument.setText(kodakVeriteApp.getScanPhotoSettingDocument());
    }
}
