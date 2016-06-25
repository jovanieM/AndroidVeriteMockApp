package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by SQA Cebu on 6/23/2016.
 */
public class WS00_050 extends Activity {

    Spinner spinner;
    String[] items;
    ArrayAdapter<String> adapter;
    Button btnBack, btnSaveSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_050);

        spinner = (Spinner) findViewById(R.id.spinner);
        btnBack = (Button) findViewById(R.id.back);
        btnSaveSetting = (Button) findViewById(R.id.btn_save_setting4);

        items = getResources().getStringArray(R.array.direct_time);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        spinner.setAdapter(adapter);

        final ProgressDialog pd = new ProgressDialog(WS00_050.this);
        pd.setMessage("Getting network information...");
        pd.setCancelable(true);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                startActivity(new Intent(WS00_050.this, WS00_000.class));
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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSaveSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RingDialog ringDialog = new RingDialog(WS00_050.this, "", "Setting", true);
                ringDialog.run();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder ad = new AlertDialog.Builder(WS00_050.this);
                        ad.setMessage("Setting saved");
                        AlertDialog adc = ad.create();
                        adc.show();

                        startActivity(new Intent(WS00_050.this, WS00_000.class));
                        finish();
                    }
                }, 4000);
            }
        });


    }
}
