package com.cebusqa.kodakverite;


import android.app.ProgressDialog;
import android.content.DialogInterface;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class HM10_000 extends AppCompatActivity implements Communicator {

    ImageButton scanphoto, scandocument, photoprint, ecomode, setting_icon, search_icon, printer;
    LinearLayout photo_print, ink_level, copy_icon, scan_document, scan_photo, a3b4scan;
    private int currentImage = 0;
    int[] images = {R.mipmap.ecomode_off, R.mipmap.ecomode1, R.mipmap.ecomode2};
    TextView printer_name, printer_selected;
    ImageView copy, checkmark;
    ProgressBar progressbar;
    Drawable drawable;
    private static final String ADDPRINTER = "Add new printer";
    KodakVeriteApp kodakVeriteApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hm10_000);

        drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.rectangular_bg);

        kodakVeriteApp = new KodakVeriteApp();
        copy = (ImageView) findViewById(R.id.copy);
        ecomode = (ImageButton) findViewById(R.id.ecomode);
        scanphoto = (ImageButton) findViewById(R.id.scanphoto);
        scandocument = (ImageButton) findViewById(R.id.scandocument);
        photoprint = (ImageButton) findViewById(R.id.photoprint);
        setting_icon = (ImageButton) findViewById(R.id.setting_icon);
        printer = (ImageButton) findViewById(R.id.printer);
        search_icon = (ImageButton) findViewById(R.id.search_icon);
        printer_name = (TextView) findViewById(R.id.printer_name);
        photo_print = (LinearLayout) findViewById(R.id.photo_print);
        ink_level = (LinearLayout) findViewById(R.id.ink_level);
        copy_icon = (LinearLayout) findViewById(R.id.copy_icon);
        scan_document = (LinearLayout) findViewById(R.id.scan_document);
        scan_photo = (LinearLayout) findViewById(R.id.scan_photo);
        printer_selected = (TextView) findViewById(R.id.printer_selected);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        checkmark = (ImageView) findViewById(R.id.checkmark);
        a3b4scan = (LinearLayout) findViewById(R.id.a3b4_scan);

        scan_document.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(HM10_000.this, DocumentScan.class));
            }
        });


        ink_level.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(HM10_000.this, IL10_000.class));
            }
        });

        copy_icon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(HM10_000.this, CP10_000.class));
            }
        });

        setting_icon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(HM10_000.this, DS10_000.class));
            }
        });


        scan_photo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(HM10_000.this, PhotoScanMain.class));
            }
        });

        a3b4scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HM10_000.this, A3B4Scan.class));
            }
        });

        photo_print.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int myVersion = Build.VERSION.SDK_INT;
                if (myVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {

                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissionToReadStorage();
                    }else{

                        startActivity(new Intent(HM10_000.this, PhotoPrintDirs.class));
                    }
                }else{

                    startActivity(new Intent(HM10_000.this, PhotoPrintDirs.class));
                }


            }
        });

        ecomode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Increase Counter to move to next Image
                currentImage++;
                currentImage = currentImage % images.length;

//                RingDialog ringDialog = new RingDialog(HM10_000.this, "", "Confirming...", true);
//                ringDialog.run();

                final ProgressDialog pd = new ProgressDialog(HM10_000.this, ProgressDialog.THEME_HOLO_LIGHT);
                pd.setMessage("Confirming...");
                pd.setCancelable(false);
                pd.show();
                ecomode.setImageResource(images[currentImage]);
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


                /* new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ecomode.setImageResource(images[currentImage]);
                    }
                }, 4000); */
            }


        });

        search_icon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                printer_selected.setText("Searching for printer");
                checkmark.setVisibility(View.INVISIBLE);
                progressbar.setVisibility(View.VISIBLE);
                printer_name.setText(ADDPRINTER);
                printer_name.setBackground(drawable);


                printer_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        printer_name.setBackground(null);
                        DummyWirelessFragment dummyWirelessFragment = new DummyWirelessFragment();
                        dummyWirelessFragment.show(getFragmentManager(), "this");
                        dummyWirelessFragment.run();
                        //    printer.setImageResource(R.mipmap.printer1);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                SelectPrinterDialog sp = new SelectPrinterDialog();
                                sp.setCancelable(false);
                                sp.show(getFragmentManager(), "select");
                            }
                        }, 3700);

                    }
                });


            }
        });

    }

    private void requestPermissionToReadStorage() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET}, 101);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){


                    startActivity(new Intent(HM10_000.this, PhotoPrintDirs.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Read/Write external storage and Internet permissions are needed to be allowed for viewing images", Toast.LENGTH_LONG).show();
                }
        }

    }

    @Override
    public void respond(String printer, boolean cancel) {

        if (cancel) {
            progressbar.setVisibility(View.INVISIBLE);
            checkmark.setVisibility(View.VISIBLE);
            checkmark.setImageResource(R.drawable.notfound);
            printer_name.setText(ADDPRINTER);
            printer_name.setBackground(drawable);
            ink_level.findViewById(R.id.ink_level_tv).setVisibility(View.INVISIBLE);
            ink_level.findViewById(R.id.inks).setVisibility(View.INVISIBLE);
            ink_level.findViewById(R.id.ok_tv).setVisibility(View.INVISIBLE);
            ink_level.setClickable(false);
            photo_print.setClickable(false);
            copy_icon.setClickable(false);
            scan_document.setClickable(false);
            scan_photo.setClickable(false);

        } else {
            checkmark.setVisibility(View.VISIBLE);
            checkmark.setImageResource(R.mipmap.checkmark_large);
            printer_name.setText(printer);
            printer_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            printer_selected.setText("Selected Printer");
            progressbar.setVisibility(View.INVISIBLE);
            ink_level.findViewById(R.id.ink_level_tv).setVisibility(View.VISIBLE);
            ink_level.findViewById(R.id.inks).setVisibility(View.VISIBLE);
            ink_level.findViewById(R.id.ok_tv).setVisibility(View.VISIBLE);
            ink_level.setClickable(true);
            photo_print.setClickable(true);
            copy_icon.setClickable(true);
            scan_document.setClickable(true);
            scan_photo.setClickable(true);


        }

    }

    @Override
    public void onBackPressed() {

    }
}
