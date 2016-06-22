package com.cebusqa.kodakverite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DS_device extends AppCompatActivity {

    Button back;
    ImageButton btnPrintUtility, btnWifiSetup;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_device);

        back = (Button)findViewById(R.id.back);
        btnPrintUtility = (ImageButton) findViewById(R.id.printer_utility);
        btnWifiSetup= (ImageButton) findViewById(R.id.wifi_setup);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DS_device.this, DS10_000.class));
            }
        });

        btnPrintUtility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DS_device.this, PU00_0000.class));
            }
        });

        btnWifiSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DS_device.this, WS00_000.class));
            }
        });



    }
}
