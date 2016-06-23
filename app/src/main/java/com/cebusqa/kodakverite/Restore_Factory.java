package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class Restore_Factory extends Activity{

    Button btn_restore, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restore_factory);

        btn_restore = (Button) findViewById(R.id.btn_restore);
        btnBack = (Button) findViewById(R.id.back);

        btn_restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Restore_Factory.this);
                builder.setMessage("Are you sure you want to restore factory default?")
                        .setCancelable(false)
                        .setPositiveButton("Restore", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                RingDialog ringDialog = new RingDialog(Restore_Factory.this, "", "Please wait...", true);
                                ringDialog.run();

                                AlertDialog.Builder builder = new AlertDialog.Builder(Restore_Factory.this);
                                builder.setTitle("Printer rebooting...");
                                builder.setMessage("Press [OK] to close this app. Please restart this app after rebooting the printer.")
                                .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {


                                                      Intent intent = new Intent(Restore_Factory.this, Splash.class);
                                                     startActivity(intent);
                                                    }
                                                });


                               //             };
                              //          });



                                AlertDialog alert = builder.create();
                                alert.show();






                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


               }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Restore_Factory.this, PU00_0000.class));
            }
        });
    }


}
