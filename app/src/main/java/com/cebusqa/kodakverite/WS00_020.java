package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by SQA Cebu on 6/23/2016.
 */
public class WS00_020 extends Activity {

    private boolean isCanceled;
    LinearLayout llipAddress, llipAdd;
    TextView tvAutoManual;
    String auto = "Auto";
    String manual = "Manual";
    Switch swAutoManual;
    Button btnBack, btnSaveSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_020);

        llipAddress = (LinearLayout) findViewById(R.id.ll_ip_address);
        llipAdd = (LinearLayout) findViewById(R.id.ll_ipAdd);
        btnSaveSetting = (Button) findViewById(R.id.btn_save_setting);
        btnBack = (Button) findViewById(R.id.back);
        tvAutoManual = (TextView) findViewById(R.id.tv_auto_manual);
        swAutoManual = (Switch) findViewById(R.id.switch1);

        tvAutoManual.setText(auto);
        llipAddress.setVisibility(View.INVISIBLE);
        // llipAdd.setVisibility(View.INVISIBLE);
        // btnSaveSetting.setVisibility(View.INVISIBLE);


        final ProgressDialog pd = new ProgressDialog(WS00_020.this);
        pd.setMessage("Getting network information...");
        pd.setCancelable(true);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                startActivity(new Intent(WS00_020.this, WS00_000.class));
                pd.dismiss();
                isCanceled = true;
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

        llipAdd.setVisibility(View.VISIBLE);
        btnSaveSetting.setVisibility(View.VISIBLE);

        swAutoManual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    tvAutoManual.setText(manual);
                    llipAddress.setVisibility(View.VISIBLE);
                }else{
                    tvAutoManual.setText(auto);
                    llipAddress.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnSaveSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(WS00_020.this);
                ad.setMessage("If [OK] is touched, IP address setting is modified, and this app is closed.");
                ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                });
                ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog adc = ad.create();
                adc.show();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });








    }
}
