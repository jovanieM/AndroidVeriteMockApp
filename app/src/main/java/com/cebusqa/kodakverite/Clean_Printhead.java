package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class Clean_Printhead extends Activity{

    Button btn_clean, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clean_printhead);

        btn_clean = (Button) findViewById(R.id.btn_clean);
        btnBack = (Button) findViewById(R.id.back);

        btn_clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    RingDialog ringDialog = new RingDialog(Clean_Printhead.this, "", "Printhead Cleaning...", true);
                    ringDialog.run();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Clean_Printhead.this, PU00_0000.class);
                            startActivity(intent);
                        }
                    }, 4000);
               }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}