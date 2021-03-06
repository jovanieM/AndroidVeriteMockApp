package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by SQA Cebu on 6/29/2016.
 */
public class UpdatePrinterSoftware extends Activity {

    Button btnStartUpdate, btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_printer_software);

        btnStartUpdate = (Button) findViewById(R.id.btn_start_update);
        btnBack = (Button) findViewById(R.id.back);

        btnStartUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder abDialog = new AlertDialog.Builder(UpdatePrinterSoftware.this, AlertDialog.THEME_HOLO_LIGHT);
                abDialog.setTitle("Printer Updating...");
                abDialog.setMessage("Press [OK] to close this app. Please restart this app after updating the printer.");
                abDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(UpdatePrinterSoftware.this, Splash.class));
                        finish();

                        /*moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);*/

                    }
                });
                AlertDialog dialog = abDialog.create();
                dialog.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdatePrinterSoftware.this, SoftwareUpdate.class));
                finish();
            }
        });
    }
}
