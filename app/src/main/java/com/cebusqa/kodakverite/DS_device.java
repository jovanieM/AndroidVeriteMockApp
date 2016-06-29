package com.cebusqa.kodakverite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DS_device extends AppCompatActivity {

    Button back;
    ImageButton btnPrintUtility, btnWifiSetup, gcp, airPrint, network_status, softwareUpdate;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_device);

        //init
        back = (Button)findViewById(R.id.back);
        btnPrintUtility = (ImageButton) findViewById(R.id.printer_utility);
        btnWifiSetup= (ImageButton) findViewById(R.id.wifi_setup);
        gcp= (ImageButton) findViewById(R.id.gcp);
        airPrint= (ImageButton) findViewById(R.id.airprint);
        network_status = (ImageButton)findViewById(R.id.network_status);
        softwareUpdate = (ImageButton) findViewById(R.id.software_update);

        //back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //printer utility button
        btnPrintUtility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(DS_device.this, PU00_0000.class));
            }
        });

        //wifi setup button
        btnWifiSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DS_device.this, WS00_000.class));
            }
        });

        //network status button
        network_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DS_device.this, NetworkStatus_details.class));
            }
        });

        //google cloud print button
        gcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GoogleCloudPrint.class));
            }
        });

        //airprint button
        airPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AirPrint.class));
            }
        });

        //software update
        softwareUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DS_device.this, SoftwareUpdate.class));
            }
        });

    }
}
