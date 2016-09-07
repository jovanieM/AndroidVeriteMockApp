package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Cebu SQA on 27/06/2016.
 */
public class GcpStatus extends Activity {
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gcp_status);
        back = (Button) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GcpStatus.this, GoogleCloudPrint.class));
                finish();
            }
        });

        final ProgressDialog pd = new ProgressDialog(GcpStatus.this, ProgressDialog.THEME_HOLO_LIGHT);
        pd.setMessage("GCP Status Loading...");
        pd.setCancelable(false);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(GcpStatus.this, GoogleCloudPrint.class));
                pd.dismiss();
                //isCanceled = false;
                finish();
            }
        });
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pd.dismiss();
            }
        }).start();

        /* final DialogFragment gcpStatusLoadingDialog = GcpStatusLoadingDialog.newInstance("GCP Status Loading...");
        gcpStatusLoadingDialog.show(getFragmentManager(),"my tag");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }gcpStatusLoadingDialog.dismiss();

            }
        }).start(); */


    }
}
