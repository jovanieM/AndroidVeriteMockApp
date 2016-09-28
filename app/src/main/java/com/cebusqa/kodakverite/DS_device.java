package com.cebusqa.kodakverite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class DS_device extends AppCompatActivity {

    Button back;
    LinearLayout wifi, networkstat, google_cloud, air_print, update, printer_utility;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_device);

        //init
        back = (Button)findViewById(R.id.back);

        wifi = (LinearLayout)findViewById(R.id.wifi);
        networkstat = (LinearLayout)findViewById(R.id.networkstat);
        google_cloud = (LinearLayout)findViewById(R.id.google_cloud);
        air_print = (LinearLayout)findViewById(R.id.air_print);
        update = (LinearLayout)findViewById(R.id.update);
        printer_utility = (LinearLayout)findViewById(R.id.printer_utility);

        //back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(DS_device.this, DS10_000.class));
                finish();
            }
        });

        //printer utility button
        printer_utility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(DS_device.this, PU00_0000.class));
                //finish();
            }
        });

        //wifi setup button
        wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DS_device.this, WS00_000.class));
                //finish();
            }
        });

        //network status button
        networkstat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DS_device.this, NetworkStatus_details.class));
                //finish();
            }
        });

        //google cloud print button
        google_cloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DS_device.this, GoogleCloudPrint.class));
                //finish();
            }
        });

        //airprint button
        air_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DS_device.this,AirPrint.class);
                startActivity(intent);

            }
        });

        //software update
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DS_device.this, SoftwareUpdate.class));
                //finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //startActivity(new Intent(DS_device.this, DS10_000.class));
        finish();
    }
}
