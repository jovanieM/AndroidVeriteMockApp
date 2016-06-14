package com.cebusqa.kodakverite;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;


public class HM10_000 extends AppCompatActivity {

    ImageButton inklevel, copy, scanphoto, scandocument, photoprint, ecomode;
    private ProgressDialog progressBar;
    private int progressBarStatus = 0;


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

        ecomode.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("Confirming ...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressBar.show();
        //        progressBar.setProgress(10);
                progressBar.setMax(10);

        //        progressBarStatus = 10;
                progressBar.dismiss();

            }
        });



    }
}
