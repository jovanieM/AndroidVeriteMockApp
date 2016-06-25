package com.cebusqa.kodakverite;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.security.SecurityPermission;


public class HM10_000 extends AppCompatActivity {

    ImageButton inklevel, copy, scanphoto, scandocument, photoprint, ecomode, setting_icon, printer, search_icon, printer_name;
    private ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private int currentImage = 0;
    int[] images = {R.mipmap.ecomode_off, R.mipmap.ecomode1, R.mipmap.ecomode2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hm10_000);

        inklevel =(ImageButton)findViewById(R.id.inklevel);
        copy =(ImageButton)findViewById(R.id.copy);
        ecomode =(ImageButton)findViewById(R.id.ecomode);
        scanphoto =(ImageButton)findViewById(R.id.scanphoto);
        scandocument =(ImageButton)findViewById(R.id.scandocument);
        photoprint =(ImageButton)findViewById(R.id.photoprint);
        setting_icon =(ImageButton)findViewById(R.id.setting_icon);
        printer = (ImageButton)findViewById(R.id.printer);
        search_icon= (ImageButton)findViewById(R.id.search_icon);
        printer_name= (ImageButton)findViewById(R.id.printer_name);

        scandocument.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HM10_000.this, DocumentScan.class));
            }
        });
        

        inklevel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HM10_000.this, IL10_000.class));
            }
        });

        copy.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HM10_000.this, CP10_000.class));
            }
        });

        setting_icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HM10_000.this, DS10_000.class));
            }
        });


        scanphoto.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HM10_000.this, PhotoScanMain.class));
            }
        });


        ecomode.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


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


        search_icon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                printer.setImageResource(R.mipmap.searching_for_printer);
                printer_name.setImageResource(R.mipmap.addnewprinter);
            }
        });

    }
}
