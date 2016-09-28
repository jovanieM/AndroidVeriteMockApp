package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Arrays;

/**
 * Created by Arvin on 6/23/2016.
 */
public class WS00_050 extends Activity {

    Spinner spinner;
    String[] items;
    ArrayAdapter<String> adapter;
    Button btnBack, btnSaveSetting;
    int directPos;
    KodakVeriteApp kodakVeriteApp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_050);

        spinner = (Spinner) findViewById(R.id.spinner);
        btnBack = (Button) findViewById(R.id.back);
        btnSaveSetting = (Button) findViewById(R.id.btn_save_setting4);
        kodakVeriteApp = new KodakVeriteApp();

        items = getResources().getStringArray(R.array.direct_time);
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_wifi_item, items);
        adapter.setDropDownViewResource(R.layout.spinner_wifi_dropdown);
        spinner.setAdapter(adapter);

        spinner.setSelection(Arrays.asList(items).indexOf(kodakVeriteApp.getDirectTime()));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                directPos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final ProgressDialog pd = new ProgressDialog(WS00_050.this, ProgressDialog.THEME_HOLO_LIGHT);
        pd.setMessage("Getting network information...");
        pd.setCancelable(false);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(WS00_050.this, WS00_000.class));
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

        //Back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WS00_050.this, WS00_000.class));
                finish();
            }
        });

        //Save setting button
        btnSaveSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                RingDialog ringDialog = new RingDialog(WS00_050.this, "", "Setting", true);
//                ringDialog.run();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder ad = new AlertDialog.Builder(WS00_050.this);
                        ad.setMessage("Setting saved");
                        AlertDialog adc = ad.create();
                        adc.show();

                        kodakVeriteApp.setDirectTime(items[directPos]);
                        startActivity(new Intent(WS00_050.this, WS00_000.class));
                        finish();
                    }
                }, 4000);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(WS00_050.this, WS00_000.class));
        finish();
    }
}
