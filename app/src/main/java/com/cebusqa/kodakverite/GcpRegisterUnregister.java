package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        final String[] status = getResources().getStringArray(R.array.gcp_reg_status);
        tvDescription = (TextView) findViewById(R.id.description);
        regUnreg = (TextView) findViewById(R.id.start_reg_unreg);
        back = (Button) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GcpRegisterUnregister.this, GoogleCloudPrint.class));
                finish();
            }
        });

        final ProgressDialog pd = new ProgressDialog(GcpRegisterUnregister.this, ProgressDialog.THEME_HOLO_LIGHT);
        pd.setMessage("GCP Status Loading...");
        pd.setCancelable(false);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(GcpRegisterUnregister.this, GoogleCloudPrint.class));
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


        regUnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test = true;
                Canceled = false;
                if (KodakVeriteApp.currentStatusValue == 1) {

                    KodakVeriteApp.currentStatusValue = 0;
                    final UnregistrationComplete unregistrationComplete = new UnregistrationComplete();
                    unregistrationComplete.setCancelable(false);
                    unregistrationComplete.show(getFragmentManager(), "tag3");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 4000);
                    //finish();
                } else {
                    final DialogFragment gcpStatusLoadingDialog3 = GcpStatusLoadingDialog.newInstance("GCP Printer Registration with Google in progress...");
                    gcpStatusLoadingDialog3.show(getFragmentManager(), "my tag");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(4000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (!Canceled) {
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
