package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button mBack;
    KodakVeriteApp kodakVeriteApp;

    TextView docQuality, docColor, docDocument, docSaveAsType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_scan);
        dScanRl = (RelativeLayout) findViewById(R.id.dscan);
        dScanTv = (TextView) findViewById(R.id.dtouchScan2);
        dScanIb = (ImageButton) findViewById(R.id.dscanSettingsIcon);
        kodakVeriteApp = new KodakVeriteApp();


        docQuality = (TextView) findViewById(R.id.doc_quality);
        docColor = (TextView) findViewById(R.id.doc_color);
        docDocument = (TextView) findViewById(R.id.doc_type);
        docSaveAsType = (TextView) findViewById(R.id.doc_save_as);


        mBack = (Button) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dScanTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dtest = false;
                dExec();
            }
        });
        dExec();
    }

    public void dtouchScan(View v) {

        if (v.getId() == R.id.dscan) {
            dtest = false;
            dExec();
        }
    }

    public void dExec() {

        final ScanPhotoDialog dscanDialog = ScanPhotoDialog.newInstance("Scan Document");
        dscanDialog.setCancelable(true);

//        finish();
//        startActivity(new Intent(getApplicationContext(), DocumentScan2.class));


        new Thread(new Runnable() {
            @Override
            public void run() {
                dscanDialog.show(getFragmentManager(), "scan");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (dtest) {

                    new ScanCanceledAlert().newInstance("Scan Canceled").show(getFragmentManager(), "dialog");

                    // dscanDialog.dismiss();
                } else {
                    finish();
                    startActivity(new Intent(getApplicationContext(), DocumentScan2.class));
                }
            }
        }).start();
    }


    public void dsettingsIcon(View v) {
        startActivity(new Intent(getApplicationContext(), Scan_Doc_Settings.class));
    }

    @Override
    public void onBackPressed() {

        ScanPhotoDialog dial = (ScanPhotoDialog) getFragmentManager().findFragmentByTag("scan");
        if (dial != null && dial.getDialog().isShowing()) {
            dial.getDialog().dismiss();
            finish();
        } else {

            super.onBackPressed();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        docQuality.setText(kodakVeriteApp.getScanSettingQuality());
        docColor.setText(kodakVeriteApp.getScanSettingColor());
        docDocument.setText(kodakVeriteApp.getScanDocSettingDocument());
        docSaveAsType.setText(kodakVeriteApp.getScanDocSettingSaveAsType());
    }
}
