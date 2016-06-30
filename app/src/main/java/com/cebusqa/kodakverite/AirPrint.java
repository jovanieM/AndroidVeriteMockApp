package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by Cebu SQA on 28/06/2016.
 */
public class AirPrint  extends Activity implements CompoundButton.OnCheckedChangeListener{
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
        prevState = KodakVeriteApp.airprintPrvState;
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
                    KodakVeriteApp.airprintPrvState = apStatusChanger.isChecked();
                    //Toast.makeText(getApplicationContext(), "yes", Toast.LENGTH_SHORT).show();
                    /**Recylce Unregistration complete view**/

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AirprintSavingSettings.newInstance("Setting...").show(getFragmentManager(), "tag2");
                            try {
                                Thread.sleep(4000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            getFragmentManager().findFragmentByTag("tag2").onDestroy();
                            //Thread.currentThread().interrupt();

                            try {
                                UnregistrationComplete.newInstance("Settings is saved").show(getFragmentManager(), "tag3");
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
        t = new Thread(new Runnable() {

            @Override
            public void run() {
                cancel = false;
                AirprintDialog.newInstance("Getting Network information").show(getFragmentManager(),"tag4");
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
        if(isChecked){
            textView.setText("enable");
        }else{
            textView.setText("disable");
        }
    }

    @Override
    protected void onDestroy() {
        t.interrupt();

        super.onDestroy();
    }
}
