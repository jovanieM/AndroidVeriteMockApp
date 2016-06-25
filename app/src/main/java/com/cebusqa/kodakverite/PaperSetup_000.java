package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class PaperSetup_000 extends Activity implements DialogInterface.OnClickListener {

    Button btnBack, btnSave;
    TextView paper_type, paper_size;
    String[] items, items1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.papersetup_000);

        //btnCancel = (Button) findViewById(R.id.btnCancel);
        paper_size = (TextView) findViewById(R.id.paper_size);
        paper_type = (TextView) findViewById(R.id.paper_type);

        btnBack = (Button) findViewById(R.id.back);
        btnSave = (Button) findViewById(R.id.btnSave);

        RingDialog ringDialog = new RingDialog(PaperSetup_000.this, "", "Getting Printer Setting...", true);
        ringDialog.run();







        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {

        //        RingDialog ringDialog = new RingDialog(PaperSetup_000.this, "", "Setting...", true);
        //        ringDialog.run();

                RingDialog ringDialog = new RingDialog(PaperSetup_000.this, "", "Setting is saved...", true);
                ringDialog.run();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(PaperSetup_000.this, PU00_0000.class);
                        startActivity(intent);
                    }
                }, 4000);

               }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        paper_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(PaperSetup_000.this);
                builder.setTitle("Paper Sizes");
                items = getResources().getStringArray(R.array.Paper_size_print);
                builder.setItems(items,PaperSetup_000.this);
                AlertDialog alertDialogObject = builder.create();
                alertDialogObject.show();

            }



        });

        paper_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builders=new AlertDialog.Builder(PaperSetup_000.this);
                builders.setTitle("Paper Sizes");
                items1 = getResources().getStringArray(R.array.Paper_type);
                builders.setItems(items1,PaperSetup_000.this);
                AlertDialog alertDialogObject = builders.create();
                alertDialogObject.show();

            }
        });


    }

    @Override
    public void onClick(DialogInterface dialog, int pos) {

        String selectedItem = items[pos];
        paper_size.setText(selectedItem);

        String selectedItems = items1[pos];
        paper_type.setText(selectedItems);

    }



/*
    @Override
    public void onClick(DialogInterface dialog, int pos){

        if(paper_size.isSelected()){

            AlertDialog.Builder builder=new AlertDialog.Builder(PaperSetup_000.this);
            builder.setTitle("Paper Size");
            items = getResources().getStringArray(R.array.Paper_size_print);
            builder.setItems(items,PaperSetup_000.this);
            AlertDialog alertDialogObject = builder.create();
            alertDialogObject.show();

            String selectedItem = items[pos];
            paper_size.setText(selectedItem);
        }

        if(paper_type.isSelected()){

            AlertDialog.Builder builders=new AlertDialog.Builder(PaperSetup_000.this);
            builders.setTitle("Paper Type");
            items = getResources().getStringArray(R.array.Paper_type);
            builders.setItems(items,PaperSetup_000.this);
            AlertDialog alertDialogObject = builders.create();
            alertDialogObject.show();


            String selectedItem = items[pos];
            paper_size.setText(selectedItem);
        }


    }
*/


}






