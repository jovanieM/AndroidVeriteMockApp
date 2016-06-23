package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class PaperSetup_000 extends Activity{

    Button btnBack, bnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.papersetup_000);

        //btnCancel = (Button) findViewById(R.id.btnCancel);
        btnBack = (Button) findViewById(R.id.back);

                    RingDialog ringDialog = new RingDialog(PaperSetup_000.this, "", "Getting Setup Information...", true);
                    ringDialog.run();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(PaperSetup_000.this, PU00_0000.class);
                            startActivity(intent);
                        }
                    }, 4000);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaperSetup_000.this, PU00_0000.class));
            }
        });
    }


}
