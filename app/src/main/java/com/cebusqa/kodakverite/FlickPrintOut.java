package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by Cebu SQA on 30/06/2016.
 */
public class FlickPrintOut extends Activity {

    Button mCancel;
    Thread t;

    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flick_print_out);
        mCancel = (Button) findViewById(R.id.cancel);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar_1);


        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.interrupt();
                finish();
            }
        });

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus < 100){
                    progressStatus+=1;

                    try{
                        Thread.sleep(30);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }

                    });
                } finish();
            }
        }); t.start();
        /* t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
        t.start(); */
    }
}
