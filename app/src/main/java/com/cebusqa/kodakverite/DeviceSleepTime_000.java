package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class DeviceSleepTime_000 extends Activity{

    Button btnBack, btnSave;
    TextView paper_size, paper_type;
    EditText num_min;
    ImageButton incre, decre;
    static int num = 10;
    public String val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_sleep_time);

        paper_size = (TextView) findViewById(R.id.paper_size);
        paper_type = (TextView) findViewById(R.id.paper_type);
        btnBack = (Button) findViewById(R.id.back);
        btnSave = (Button) findViewById(R.id.btnSave);
        num_min = (EditText) findViewById(R.id.num_min);
        incre = (ImageButton) findViewById(R.id.incre);
        decre = (ImageButton) findViewById(R.id.decre);

                    RingDialog ringDialog = new RingDialog(DeviceSleepTime_000.this, "", "Getting Printer Setting...", true);
                    ringDialog.run();


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {



                RingDialog ringDialog = new RingDialog(DeviceSleepTime_000.this, "", "Setting is saved...", true);
                ringDialog.run();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(DeviceSleepTime_000.this, PU00_0000.class);
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


        incre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (num < 101) {
                    num= num+1;
                    val = Integer.toString(num);
                    num_min.setText(val);

                }
            }
        });


        decre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (num>1)
                    num= num-1;
                val = Integer.toString(num);
                num_min.setText(val);

            }
        });


    }


}
