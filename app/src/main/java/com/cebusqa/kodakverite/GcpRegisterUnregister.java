package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Cebu SQA on 27/06/2016.
 */
public class GcpRegisterUnregister extends Activity {
    boolean test = false;
    boolean Canceled;
    Button back;
    TextView regUnreg;
    TextView tvDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gcp_registration);
        final String [] status = getResources().getStringArray(R.array.gcp_reg_status);
        tvDescription = (TextView) findViewById(R.id.description);
        regUnreg = (TextView) findViewById(R.id.start_reg_unreg);
        back = (Button)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        new Thread( new Runnable() {
            @Override
            public void run() {
                final DialogFragment gcpStatusLoadingDialog2 = GcpStatusLoadingDialog.newInstance("GCP Status Loading...");
                gcpStatusLoadingDialog2.show(getFragmentManager(),"my tag");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                gcpStatusLoadingDialog2.dismiss();
                if(test){
                    Thread.currentThread().interrupt();
                    finish();
                }else{
                   regUnreg.post(new Runnable() {
                       @Override
                       public void run() {
                           regUnreg.setText("Start "+status[KodakVeriteApp.currentStatusValue]);
                           tvDescription.setText("If [Start "+status[KodakVeriteApp.currentStatusValue]+"] is touched, the registration home page is opened.");
                           Thread.currentThread().interrupt();
                       }
                   });
                }
            }
        }).start();


        //regUnreg = (TextView) findViewById(R.id.start_reg_unreg);
        //regUnreg.setText("hello");
        regUnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test = true;
                Canceled = false;
                if (KodakVeriteApp.currentStatusValue == 1){

                    KodakVeriteApp.currentStatusValue=0;
                    UnregistrationComplete.newInstance("Unregistration complete").show(getFragmentManager(), "tag");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 4000);
                    //finish();
                }else{
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
                                KodakVeriteApp.currentStatusValue = 1;
                                finish();

                            }

                        }
                    }).start();
                }

            }
        });




    }
}
