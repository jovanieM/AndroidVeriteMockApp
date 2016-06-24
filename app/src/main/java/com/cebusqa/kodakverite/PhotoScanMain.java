package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_scan_main);
        scanTv = (TextView) findViewById(R.id.touchScan2);
        scanRelOut = (RelativeLayout) findViewById(R.id.scan);
        settingsIcon = (ImageButton) findViewById(R.id.scanSettingsIcon);
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

        final ScanPhotoDialog scanDialog = new ScanPhotoDialog();
        scanDialog.setCancelable(true);


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                scanDialog.show(getFragmentManager(),"scan");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(test){
                    new ScanCanceledAlert().newInstance("Scan Canceled").show(getFragmentManager(),"dialog");
                    scanDialog.dismiss();
                }else{
                    startActivity(new Intent(PhotoScanMain.this, SP_000.class));
                }
            }
        });
        t.start();

    }
    public void settingsIcon(View v){
        startActivity(new Intent(getApplicationContext(), Scan_Photo_Settings.class));
    }


}
