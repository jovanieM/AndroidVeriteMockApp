package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
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

                AlertDialog.Builder builder = new AlertDialog.Builder(Restore_Factory.this, AlertDialog.THEME_HOLO_LIGHT);
                builder.setMessage("Are you sure you want to restore factory default?")
                        .setCancelable(false)
                        .setPositiveButton("Restore", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

//                                RingDialog ringDialog = new RingDialog(Restore_Factory.this, "", "Please wait...", true);
//                                ringDialog.run();

                                AlertDialog.Builder builder = new AlertDialog.Builder(Restore_Factory.this, AlertDialog.THEME_HOLO_LIGHT);
                                builder.setTitle("Printer rebooting...");
                                builder.setMessage("Press [OK] to close this app. Please restart this app after rebooting the printer.")
                                .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
    //                                            new KodakVeriteApp().onTerminate();
    //                                            moveTaskToBack(true);
    //                                            android.os.Process.killProcess(android.os.Process.myPid());
//                                                System.exit(1);
                                                startActivity(new Intent(Restore_Factory.this, Splash.class));

                                            }
                                                });


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
                finish();
            }
        });
    }


}
