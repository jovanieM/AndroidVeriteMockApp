package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

/**
 * Created by SQA Cebu on 6/22/2016.
 */
public class WS00_010 extends Activity {

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_010);

        btnBack = (Button) findViewById(R.id.back);
        btnBack.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WS00_010.this, WS00_011.class));
                finish();
            }
        }, 4000);
    }

    @Override
    public void onBackPressed() {

    }
}
