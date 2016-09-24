package com.cebusqa.kodakverite;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


public class HM10_000 extends AppCompatActivity implements Communicator {

    ImageButton  scanphoto, scandocument, photoprint, ecomode, setting_icon, search_icon, printer;
    LinearLayout photo_print, ink_level, copy_icon, scan_document, scan_photo;
    private int currentImage = 0;
    int[] images = {R.mipmap.ecomode_off, R.mipmap.ecomode1, R.mipmap.ecomode2};
    TextView printer_name, printer_selected;
    ImageView copy, checkmark;
    ProgressBar progressbar;
    Drawable drawable;
    private static final String ADDPRINTER = "Add new printer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hm10_000);

        drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.rectangular_bg);


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

        photo_print.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(HM10_000.this, PhotoPrintDirs.class));
            }
        });

        ecomode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                //Increase Counter to move to next Image
                currentImage++;
                currentImage = currentImage % images.length;

                RingDialog ringDialog = new RingDialog(HM10_000.this, "", "Confirming...", true);

                ringDialog.run();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ecomode.setImageResource(images[currentImage]);
                    }
                }, 4000);


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
                                new SelectPrinterDialog().show(getFragmentManager(), "select");
                            }
                        }, 3700);

                    }
                });


            }
        });

    }

    @Override
    public void respond(String printer) {
        printer_name.setText(printer);
        printer_name.setOnClickListener(null);
        printer_selected.setText("Selected Printer");
        progressbar.setVisibility(View.INVISIBLE);
        checkmark.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
            super.onBackPressed();
        }
    }
}
