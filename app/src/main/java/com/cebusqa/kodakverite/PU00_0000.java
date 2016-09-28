package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class PU00_0000 extends Activity {

    RelativeLayout device_sleep_time, clean_printhead, cartridge_setup, print_reports, paper_setup, restore_default;
    String[] items;
    ArrayAdapter<String> adapter;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pu00_000);

        btnBack = (Button) findViewById(R.id.back);
        device_sleep_time = (RelativeLayout) findViewById(R.id.device_sleep_time);
        clean_printhead = (RelativeLayout) findViewById(R.id.clean_printhead);
        cartridge_setup = (RelativeLayout) findViewById(R.id.cartridge_setup);
        print_reports = (RelativeLayout) findViewById(R.id.print_reports);
        paper_setup = (RelativeLayout) findViewById(R.id.paper_setup);
        restore_default = (RelativeLayout) findViewById(R.id.restore_default);
        items = getResources().getStringArray(R.array.printer_utility);

        device_sleep_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PU00_0000.this, DeviceSleepTime_000.class));
                finish();
            }
        });

        clean_printhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PU00_0000.this, Clean_Printhead.class));
                finish();
            }
        });

        cartridge_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PU00_0000.this, CS00_000.class));
                finish();
            }
        });

        print_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PU00_0000.this, Print_Reports.class));
                finish();
            }
        });

        paper_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PU00_0000.this, PaperSetup_000.class));
                finish();
            }
        });

        restore_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PU00_0000.this, Restore_Factory.class));
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PU00_0000.this, DS_device.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PU00_0000.this, DS_device.class));
        finish();
    }
}
