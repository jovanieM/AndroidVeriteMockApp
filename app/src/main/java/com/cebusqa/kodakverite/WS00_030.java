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
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by SQA Cebu on 6/23/2016.
 */
public class WS00_030 extends Activity {

    TextView tvOffOn;
    Switch swOffOn;
    LinearLayout llAddressPort;
    Button btnBack, btnSaveSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_030);

        tvOffOn = (TextView) findViewById(R.id.tv_on_off);
        swOffOn = (Switch) findViewById(R.id.sw_on_off);
        llAddressPort = (LinearLayout) findViewById(R.id.ll_address_port);
        btnBack = (Button) findViewById(R.id.back);
        btnSaveSetting = (Button) findViewById(R.id.btn_save_setting1);

        llAddressPort.setVisibility(View.INVISIBLE);
        tvOffOn.setText("Off");

        final ProgressDialog pd = new ProgressDialog(WS00_030.this);
        pd.setMessage("Getting network information...");
        pd.setCancelable(true);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                startActivity(new Intent(WS00_030.this, WS00_000.class));
                pd.dismiss();
                finish();
            }
        });
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    Thread.sleep(4000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                pd.dismiss();
            }
        }).start();

        swOffOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    tvOffOn.setText("On");
                    llAddressPort.setVisibility(View.VISIBLE);
                }else{
                    tvOffOn.setText("Off");
                    llAddressPort.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSaveSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RingDialog ringDialog = new RingDialog(WS00_030.this, "", "Setting", true);
                ringDialog.run();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder ad = new AlertDialog.Builder(WS00_030.this);
                        ad.setMessage("Setting saved");
                        AlertDialog adc = ad.create();
                        //adc.show();

                        startActivity(new Intent(WS00_030.this, WS00_000.class));
                        finish();
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
}
