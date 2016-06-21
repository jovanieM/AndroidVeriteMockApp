package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class CS00_002 extends Activity {

    Button btnScan, btnHelp, btnBack;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs00_002);

        btnScan = (Button) findViewById(R.id.btn_start_scan1);
        btnHelp = (Button) findViewById(R.id.btn_help1);
        btnBack = (Button) findViewById(R.id.back);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CS00_002.this, CS00_003.class));
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CS00_002.this, CS00_002_Help.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CS00_002.this, CS00_000.class));
            }
        });
    }
}
