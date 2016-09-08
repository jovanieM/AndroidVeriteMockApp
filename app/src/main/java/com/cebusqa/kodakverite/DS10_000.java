package com.cebusqa.kodakverite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class DS10_000 extends AppCompatActivity {

    RelativeLayout print_settings, scan_settings, device_settings;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds10_000);

        print_settings =(RelativeLayout)findViewById(R.id.print_settings);
        scan_settings =(RelativeLayout) findViewById(R.id.scan_settings);
        device_settings =(RelativeLayout) findViewById(R.id.device_settings);
        back = (Button)findViewById(R.id.back);

        print_settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(DS10_000.this, DS_print.class));
            }
        });

        scan_settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(DS10_000.this, DS_scan.class));

            }
        });

        device_settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(DS10_000.this, DS_device.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });


        }




    }

