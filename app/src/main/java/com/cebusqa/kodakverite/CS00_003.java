package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class CS00_003 extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs00_003);

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {

               final AlertDialog.Builder dialog = new AlertDialog.Builder(CS00_003.this).setMessage("Cartridge Setup complete");                 final AlertDialog alert = dialog.create();

               alert.show();
               // alert.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);


               final Handler handler = new Handler();
               final Runnable runnable = new Runnable() {
                   @Override
                   public void run() {
                       if (alert.isShowing()){
                           startActivity(new Intent(CS00_003.this, PU00_0000.class));
                           alert.dismiss();
                       }
                   }
               };
               handler.postDelayed(runnable, 4000);
           }
       }, 4000);

    }
}