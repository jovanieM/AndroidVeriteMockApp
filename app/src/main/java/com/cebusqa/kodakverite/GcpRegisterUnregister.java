package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Cebu SQA on 27/06/2016.
 */
public class GcpRegisterUnregister extends Activity {
    boolean test = false;
    boolean Canceled;
    Button regUnreg;
    TextView tvDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gcp_registration);
        tvDescription = (TextView) findViewById(R.id.description);
        final DialogFragment gcpStatusLoadingDialog2 = GcpStatusLoadingDialog.newInstance("GCP Status Loading...");
        gcpStatusLoadingDialog2.show(getFragmentManager(),"my tag");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }gcpStatusLoadingDialog2.dismiss();
                if(test){
                    Thread.currentThread().interrupt();
                    finish();


                }
            }
        }).start();


        regUnreg = (Button) findViewById(R.id.start_reg_unreg);
        //regUnreg.setText();
        regUnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test = true;
                Canceled = false;
                final DialogFragment gcpStatusLoadingDialog3 = GcpStatusLoadingDialog.newInstance("GCP Printer Registration with Google in progress...");
                gcpStatusLoadingDialog3.show(getFragmentManager(),"my tag");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(!Canceled){

                            finish();
                        }

                    }
                }).start();
            }
        });




    }
}
