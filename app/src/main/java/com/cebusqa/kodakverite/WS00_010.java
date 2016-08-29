package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arvin on 6/22/2016.
 */
public class WS00_010 extends Activity {

    Button btnBack;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_010);

        btnBack = (Button) findViewById(R.id.back);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WS00_010.this, WS00_011.class));
                finish();
            }
        }, 4000);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WS00_010.this, WS00_000.class));
                handler.removeCallbacksAndMessages(null);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(WS00_010.this, WS00_000.class));
        handler.removeCallbacksAndMessages(null);
        finish();
    }
}
