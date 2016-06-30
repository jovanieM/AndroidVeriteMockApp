package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by SQA Cebu on 6/7/2016.
 */
public class Splash extends Activity {

    Handler handler;
    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent intent = new Intent(Splash.this, EasySetupMain.class);
                Splash.this.startActivity(intent);

                Splash.this.finish();
            }
        }, 4000);

        if (getIntent().getBooleanExtra("LOGOUT", false))
        {
            finish();

        }


    }

    @Override
    public void onBackPressed(){

    }


}
