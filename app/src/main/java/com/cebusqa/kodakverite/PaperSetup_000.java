package com.cebusqa.kodakverite;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class PaperSetup_000 extends AppCompatActivity implements View.OnClickListener {

    private boolean isCanceled;
    Button btnBack, btnSave;
    Spinner spin_paper_type, spin_paper_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.papersetup_000);

        spin_paper_size = (Spinner) findViewById(R.id.spin_paper_size);
        spin_paper_type = (Spinner) findViewById(R.id.spin_paper_type);

        btnBack = (Button) findViewById(R.id.back);
        btnSave = (Button) findViewById(R.id.btnSave);

        final ProgressDialog pd = new ProgressDialog(PaperSetup_000.this, ProgressDialog.THEME_HOLO_LIGHT);
        pd.setMessage("Getting printer setting...");
        pd.setCancelable(true);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(PaperSetup_000.this, PU00_0000.class));
                pd.dismiss();
                isCanceled = true;
                finish();
            }
        });
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


        ArrayAdapter<CharSequence> adapter_type = ArrayAdapter.createFromResource(this, R.array.Paper_type, R.layout.spinner_item_print);
        adapter_type.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin_paper_type.setAdapter(adapter_type);
    //    spin_paper_type.setPopupBackgroundResource(R.drawable.spinner_background);

        ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this, R.array.Paper_size_print, R.layout.spinner_item_print);
        adapter_size.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin_paper_size.setAdapter(adapter_size);
    //    spin_paper_size.setPopupBackgroundResource(R.drawable.spinner_background);


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


        return;



    }

    @Override
    public void onClick(View v) {

    }
}


/*
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

        paper_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PaperSetup_000.this, AlertDialog.THEME_HOLO_LIGHT);
                builder.setTitle("Paper Sizes");
                items = getResources().getStringArray(R.array.Paper_size_print);
                builder.setItems(items, PaperSetup_000.this);
                AlertDialog alertDialogObject = builder.create();
                alertDialogObject.show();

            }


        });


    @Override
    public void onClick(DialogInterface dialog, int pos) {

            String selectedItem = items[pos];
            paper_size.setText(selectedItem);

    }

*/











