package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Created by SQA Cebu on 6/29/2016.
 */
public class SoftwareUpdate extends Activity implements View.OnClickListener {

    Button btnBack, btnUpdate, btnConfirmation;
    CheckBox cbAllow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.software_update);

        //init
        btnBack = (Button) findViewById(R.id.back);
        btnUpdate = (Button) findViewById(R.id.btn_update_printer);
        btnConfirmation = (Button) findViewById(R.id.btn_confirmation);
        cbAllow = (CheckBox) findViewById(R.id.cb_allow);

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Checking software version...");
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(4000);
                }catch (Exception e){

                }
                dialog.dismiss();
            }
        }).start();

        btnBack.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnConfirmation.setOnClickListener(this);

        cbAllow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cbAllow.isChecked()){
                    RingDialog dialog = new RingDialog(SoftwareUpdate.this, "", "Setting", true);
                    dialog.run();
                }else{
                    RingDialog dialog = new RingDialog(SoftwareUpdate.this, "", "Setting", true);
                    dialog.run();
                }
            }
        });
    }

    public void onClick(View v){
        if(v == btnBack){
            finish();
        }else
        if (v == btnUpdate){
            startActivity(new Intent(SoftwareUpdate.this, UpdatePrinterSoftware.class));
        }else
            if(v == btnConfirmation){
                final ProgressDialog dialog = new ProgressDialog(SoftwareUpdate.this);
                dialog.setMessage("Checking Software version...");
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(4000);
                        }catch (Exception e){

                        }
                        dialog.dismiss();
                    }
                }).start();


            }
    }
}