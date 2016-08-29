package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arvin on 6/22/2016.
 */
public class WS00_015 extends Activity {

    Button btnFindRouter, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_015);

        btnFindRouter = (Button) findViewById(R.id.btnFindRouter1);
        btnBack = (Button) findViewById(R.id.back);

        btnFindRouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WS00_015.this, WS00_016.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WS00_015.this, WS00_011.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(WS00_015.this, WS00_011.class));
        finish();
    }
}
