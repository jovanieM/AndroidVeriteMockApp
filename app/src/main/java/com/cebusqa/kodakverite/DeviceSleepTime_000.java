package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class DeviceSleepTime_000 extends Activity implements View.OnClickListener{

    Button btnBack, btnSave;
    TextView paper_size, paper_type;
    EditText num_min;
    Button incre, decre;
     int num = 10;
    public int min ;
    public String val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_sleep_time);

        paper_size = (TextView) findViewById(R.id.paper_size);
        paper_type = (TextView) findViewById(R.id.paper_type);
        num_min = (EditText) findViewById(R.id.num_min);

        btnBack = (Button) findViewById(R.id.back);
        btnBack.setOnClickListener(this);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        incre = (Button) findViewById(R.id.incre);
        incre.setOnClickListener(this);

        decre = (Button) findViewById(R.id.decre);
        decre.setOnClickListener(this);


        RingDialog ringDialog = new RingDialog(DeviceSleepTime_000.this, "", "Getting Printer Setting...", true);
        ringDialog.run();

   }


    @Override
    public void onClick(View v) {

        String minutes = num_min.getText().toString();
        int min = Integer.parseInt(minutes);

        if(min>120){

           num_min.setText("120");
        }

        switch (v.getId()){

            case R.id.btnSave:


                RingDialog ringDialog = new RingDialog(DeviceSleepTime_000.this, "", "Setting is saved...", true);
                ringDialog.run();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(DeviceSleepTime_000.this, PU00_0000.class);
                        startActivity(intent);
                    }
                }, 4000);
                break;

            case R.id.back:

                finish();
                break;

            case R.id.incre:

                if (min < 120) {
                    num= min + 1;
                    val = Integer.toString(num);
                    num_min.setText(val);
                }
                break;

            case R.id.decre:

                 if (min > 1){
                    num=min-1;
                    val=Integer.toString(num);
                    num_min.setText(val);
                }
                break;


        }

    }


}
