package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by Cebu SQA on 02/08/2016.
 */
public class GcpEnableDisable extends Activity implements CompoundButton.OnCheckedChangeListener {

    private ToggleButton apStatusChanger;
    private TextView textView;
    private TextView saveSettings;
    boolean prevState;
    Button back;
    static boolean cancel = false;
    Thread t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gcp_enable_disable);
        prevState = KodakVeriteApp.gcpPrevState;
        apStatusChanger = (ToggleButton) findViewById(R.id.toggleButton);
        apStatusChanger.setOnCheckedChangeListener(this);
        textView = (TextView) findViewById(R.id.tvState);
        saveSettings = (TextView) findViewById(R.id.save_setting);
        back = (Button) findViewById(R.id.back);
        apStatusChanger.setChecked(prevState);

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prevState != apStatusChanger.isChecked()){
                    KodakVeriteApp.gcpPrevState = apStatusChanger.isChecked();
                    //Toast.makeText(getApplicationContext(), "yes", Toast.LENGTH_SHORT).show();
                    /**Reusing "Unregistration complete" view**/

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final AirprintSavingSettings airprintSavingSettings = new AirprintSavingSettings();
                            airprintSavingSettings.show(getFragmentManager(), "settingGCP");
                            airprintSavingSettings.setCancelable(false);
                            try {
                                Thread.sleep(4000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            getFragmentManager().findFragmentByTag("settingGCP").onDestroy();
                            //Thread.currentThread().interrupt();

                            try {
                                final UnregistrationComplete unregistrationComplete = new UnregistrationComplete();
                                unregistrationComplete.setCancelable(false);
                                unregistrationComplete.show(getFragmentManager(), "tag3");
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }finally {
                                getFragmentManager().findFragmentByTag("tag3").onDestroy();
                                finish();
                            }

                        }
                    }).start();
                }
            }
        });

        final ProgressDialog pd = new ProgressDialog(GcpEnableDisable.this, ProgressDialog.THEME_HOLO_LIGHT);
        pd.setMessage("GCP Status Loading...");
        pd.setCancelable(false);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(GcpEnableDisable.this, GoogleCloudPrint.class));
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

        /* t = new Thread(new Runnable() {

            @Override
            public void run() {
                cancel = false;
                AirprintDialog.newInstance("GCP Status Loading").show(getFragmentManager(),"tag4");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(!cancel){
                    getFragmentManager().findFragmentByTag("tag4").onDestroy();
                }

            }
        });
        t.start(); */




//       new Thread(new Runnable() {
//           @Override
//           public void run() {
//               AirprintDialog.newInstance("Getting Network information").show(getFragmentManager(), "tag");
//               try {
//                   Thread.sleep(4000);
//               } catch (InterruptedException e) {
//                   e.printStackTrace();
//               }
//               //getFragmentManager().findFragmentByTag("tag").onDestroy();
//              // Thread.currentThread().interrupt();
//           }
//       }).start();

    }

    /**
     * Called when the checked state of a compound button has changed.
     *
     * @param buttonView The compound button view whose state has changed.
     * @param isChecked  The new checked state of buttonView.
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            textView.setText("Enable");
        }else{
            textView.setText("Disable");
        }

    }
}
