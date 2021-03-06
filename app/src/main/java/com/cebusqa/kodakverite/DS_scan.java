package com.cebusqa.kodakverite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class DS_scan extends AppCompatActivity {

    RelativeLayout scan_document_settings, scan_photo_settings;
    public Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_scan);

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(DS_scan.this, DS10_000.class));
                finish();
                    }
        });

        scan_document_settings = (RelativeLayout)findViewById(R.id.scan_document_settings);
        scan_document_settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(DS_scan.this, Scan_Doc_Settings.class));
            }
        });

        scan_photo_settings = (RelativeLayout)findViewById(R.id.scan_photo_settings);
        scan_photo_settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(DS_scan.this, Scan_Photo_Settings.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(DS_scan.this, DS10_000.class));
        finish();
    }
}
