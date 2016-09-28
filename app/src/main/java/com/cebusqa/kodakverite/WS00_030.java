package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by SQA Cebu on 6/23/2016.
 */
public class WS00_030 extends Activity {

    TextView tvOffOn;
    ToggleButton toggleButtonOnOff;
    RelativeLayout relativeLayoutAddressPort;
    Button btnBack, btnSaveSetting;
    EditText editTextAddress;
    String off = "OFF";
    String on = "ON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_030);

        tvOffOn = (TextView) findViewById(R.id.tv_on_off);
        toggleButtonOnOff = (ToggleButton) findViewById(R.id.toggleButton_on_off);
        relativeLayoutAddressPort = (RelativeLayout) findViewById(R.id.relativeLayout_address_port);
        btnBack = (Button) findViewById(R.id.back);
        btnSaveSetting = (Button) findViewById(R.id.btn_save_setting1);
        editTextAddress = (EditText) findViewById(R.id.editText_address);

        relativeLayoutAddressPort.setVisibility(View.GONE);
        tvOffOn.setText(off);

        final ProgressDialog pd = new ProgressDialog(WS00_030.this, ProgressDialog.THEME_HOLO_LIGHT);
        pd.setMessage("Getting network information...");
        pd.setCancelable(false);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(WS00_030.this, WS00_000.class));
                pd.dismiss();
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

        toggleButtonOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tvOffOn.setText(on);
                    relativeLayoutAddressPort.setVisibility(View.VISIBLE);
                } else {
                    tvOffOn.setText(off);
                    relativeLayoutAddressPort.setVisibility(View.GONE);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WS00_030.this, WS00_000.class));
                finish();
            }
        });

        btnSaveSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                RingDialog ringDialog = new RingDialog(WS00_030.this, "", "Setting", true);
//                ringDialog.run();

                final ProgressDialog pd = new ProgressDialog(WS00_030.this, ProgressDialog.THEME_HOLO_LIGHT);
                pd.setMessage("Setting...");
                pd.setCancelable(false);
                pd.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                        close();
                    }
                }, 4000);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(WS00_030.this, WS00_000.class));
        finish();
    }

    public void close() {
        final UnregistrationComplete unregistrationComplete = new UnregistrationComplete();
        unregistrationComplete.show(getFragmentManager(), "tag");
        unregistrationComplete.setCancelable(false);
        final Handler handler = new Handler();
        final Runnable run = new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(WS00_030.this, WS00_000.class));
                getFragmentManager().findFragmentByTag("tag").onDestroy();
                finish();
            }
        };

        handler.postDelayed(run, 4000);
    }
}
