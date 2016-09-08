package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class CS00_000 extends Activity {

    Button btnStart, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs00_000);

        btnStart = (Button) findViewById(R.id.btn_start);
        btnBack = (Button) findViewById(R.id.back);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CS00_000.this, CS00_001.class));
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CS00_000.this, PU00_0000.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CS00_000.this, PU00_0000.class));
        finish();
    }
}
