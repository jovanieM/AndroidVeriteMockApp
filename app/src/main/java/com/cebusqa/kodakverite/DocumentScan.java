package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Cebu SQA on 23/06/2016.
 */
public class DocumentScan extends Activity {
    RelativeLayout dScanRl;
    TextView dScanTv;
    ImageButton dScanIb;
    boolean dtest = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_scan);
        dScanRl = (RelativeLayout) findViewById(R.id.dscan);
        dScanTv = (TextView) findViewById(R.id.dtouchScan2);
        dScanIb = (ImageButton) findViewById(R.id.dscanSettingsIcon);
        dScanTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dtest = false;
                dExec();
            }
        });
        dExec();
    }

    public void dtouchScan(View v){

        if (v.getId()==R.id.dscan){
            dtest = false;
            dExec();
        }
    }

    public void dExec (){

        final ScanPhotoDialog dscanDialog = new ScanPhotoDialog();
        dscanDialog.setCancelable(true);


        new Thread(new Runnable() {
            @Override
            public void run() {
                dscanDialog.show(getFragmentManager(),"scan");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(dtest){
                    new ScanCanceledAlert().newInstance("Scan Canceled").show(getFragmentManager(),"dialog");
                    dscanDialog.dismiss();
                }else{
                    //startActivity(new Intent(getApplicationContext(), SP_000.class));
                }
            }
        })
                .start();

    }
    public void dsettingsIcon(View v){
        startActivity(new Intent(getApplicationContext(), Scan_Photo_Settings.class));
    }



}
