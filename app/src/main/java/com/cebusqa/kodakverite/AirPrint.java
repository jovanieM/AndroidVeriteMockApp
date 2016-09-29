package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by Cebu SQA on 28/06/2016.
 */
public class AirPrint extends Activity implements CompoundButton.OnCheckedChangeListener {
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
        setContentView(R.layout.airprint);
        this.setFinishOnTouchOutside(false);
        prevState = KodakVeriteApp.airprintPrvState;
        apStatusChanger = (ToggleButton) findViewById(R.id.toggleButton);
        apStatusChanger.setOnCheckedChangeListener(this);
        textView = (TextView) findViewById(R.id.tvState);
        saveSettings = (TextView) findViewById(R.id.save_setting);
        back = (Button) findViewById(R.id.back);
        apStatusChanger.setChecked(prevState);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AirPrint.this, DS_device.class));
                finish();
            }
        });

        saveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getApplicationContext(), String.valueOf(prevState)+ String.valueOf(apStatusChanger.isChecked()), Toast.LENGTH_SHORT).show();
                if (prevState != apStatusChanger.isChecked()) {
                    KodakVeriteApp.airprintPrvState = apStatusChanger.isChecked();
                    //Toast.makeText(getApplicationContext(), "yes", Toast.LENGTH_SHORT).show();

                    final ProgressDialog pd1 = new ProgressDialog(AirPrint.this, ProgressDialog.THEME_HOLO_LIGHT);
                    pd1.setMessage("Setting...");
                    pd1.setCancelable(false);
                    pd1.show();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(pd1.isShowing())pd1.dismiss();
                            //getFragmentManager().findFragmentByTag("settingAP").onDestroy();
                            close();
                        }
                    }, 4000);
                }
            }
        });

        final ProgressDialog pd = new ProgressDialog(this, ProgressDialog.THEME_HOLO_LIGHT);
        pd.setMessage("Getting network information...");
        pd.setCancelable(false);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //startActivity(new Intent(GcpStatus.this, GoogleCloudPrint.class));
                pd.dismiss();
                //isCanceled = false;
                //finish();
            }
        });
        pd.show();

        t = new Thread(new Runnable() {

            @Override
            public void run() {
                cancel = false;
//                AirprintDialog alertDialog  = AirprintDialog.newInstance("Getting Network information");
//                alertDialog.setCancelable(false);
//                alertDialog.show(getFragmentManager(), "tag4");

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!cancel) {
                    pd.dismiss();
//                    getFragmentManager().findFragmentByTag("tag4").onDestroy();
                }
            }
        });
        t.start();

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
        if (isChecked) {
            textView.setText("Enable");
        } else {
            textView.setText("Disable");
        }
    }

    @Override
    protected void onDestroy() {
        t.interrupt();
        super.onDestroy();
    }

    public void close(){
        final UnregistrationComplete unregistrationComplete = new UnregistrationComplete();
        unregistrationComplete.show(getFragmentManager(), "tag");
        unregistrationComplete.setCancelable(false);
        final Handler handler = new Handler();
        final Runnable run =new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(AirPrint.this, DS_device.class));
                getFragmentManager().findFragmentByTag("tag").onDestroy();
                finish();
            }
        };
        handler.postDelayed(run, 4000);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AirPrint.this, DS_device.class));
        finish();
    }
}
