package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class DS_scan extends AppCompatActivity {

    public ImageButton scan_document_settings, scan_photo_settings;
    public Button back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_scan);

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(DS_scan.this, DS10_000.class));
            }
        });

        scan_document_settings = (ImageButton)findViewById(R.id.scan_document_settings);
        scan_document_settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(DS_scan.this, Scan_Doc_Settings.class));
            }
        });

        scan_photo_settings = (ImageButton)findViewById(R.id.scan_photo_settings);
        scan_photo_settings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(DS_scan.this, Scan_Photo_Settings.class));
            }
        });


    }
}
