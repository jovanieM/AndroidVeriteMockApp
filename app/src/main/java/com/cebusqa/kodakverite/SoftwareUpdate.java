package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.ToggleButton;

/**
 * Created by SQA Cebu on 6/29/2016.
 */
public class SoftwareUpdate extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    Button btnBack, btnUpdate;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.software_update);

        //init
        btnBack = (Button) findViewById(R.id.back);
        btnUpdate = (Button) findViewById(R.id.btn_update_printer);
        checkBox = (CheckBox) findViewById(R.id.check_image);
        checkBox.setChecked(true);
        checkBox.setOnCheckedChangeListener(this);


        final ProgressDialog dialog = new ProgressDialog(this, AlertDialog.THEME_HOLO_LIGHT);
        dialog.setMessage("Checking software version...");
        dialog.setCancelable(false);
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(SoftwareUpdate.this, DS_device.class));
                finish();
            }
        });
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {

                }
                dialog.dismiss();
            }
        }).start();

        btnBack.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        //btnConfirmation.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == btnBack) {
            startActivity(new Intent(SoftwareUpdate.this, DS_device.class));
            finish();
        } else if (v == btnUpdate) {
            startActivity(new Intent(SoftwareUpdate.this, UpdatePrinterSoftware.class));
            finish();
        }
        // remove confirmation button in layout
        /* else if (v == btnConfirmation) {
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
                    try {
                        Thread.sleep(4000);
                    } catch (Exception e) {

                    }
                    dialog.dismiss();
                }
            }).start();
        }*/
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            final ProgressDialog pd = new ProgressDialog(SoftwareUpdate.this, ProgressDialog.THEME_HOLO_LIGHT);
            pd.setMessage("Setting...");
            pd.setCancelable(false);
            pd.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(4000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pd.dismiss();
                }
            }).start();
        } else {
            final ProgressDialog pd = new ProgressDialog(SoftwareUpdate.this, ProgressDialog.THEME_HOLO_LIGHT);
            pd.setMessage("Setting...");
            pd.setCancelable(false);
            pd.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(4000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pd.dismiss();
                }
            }).start();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SoftwareUpdate.this, DS_device.class));
        finish();
    }
}
