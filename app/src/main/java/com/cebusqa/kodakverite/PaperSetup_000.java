package com.cebusqa.kodakverite;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Arrays;


/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class PaperSetup_000 extends AppCompatActivity implements View.OnClickListener {

    private boolean isCanceled;
    Button btnBack, btnSave;
    Spinner spin_paper_type, spin_paper_size;
    public boolean clicked = false;
    int pos_size, pos_type;

    Resources res;
    String[] paper_setup_size, paper_setup_type;
    KodakVeriteApp kodakVeriteApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.papersetup_000);

        res = getResources();
        paper_setup_size = res.getStringArray(R.array.Paper_size_print);
        paper_setup_type = res.getStringArray(R.array.Paper_type);
        kodakVeriteApp = new KodakVeriteApp();

        spin_paper_size = (Spinner) findViewById(R.id.spin_paper_size);
        spin_paper_type = (Spinner) findViewById(R.id.spin_paper_type);

        btnBack = (Button) findViewById(R.id.back);
        btnSave = (Button) findViewById(R.id.btnSave);

        final ProgressDialog pd = new ProgressDialog(PaperSetup_000.this, ProgressDialog.THEME_HOLO_LIGHT);
        pd.setMessage("Getting printer setting...");
        pd.setCancelable(false);
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

        spin_paper_type.setSelection(Arrays.asList(paper_setup_type).indexOf(kodakVeriteApp.getPaperType()));

        spin_paper_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos_type = position;
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this, R.array.Paper_size_print, R.layout.spinner_item_print);
        adapter_size.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin_paper_size.setAdapter(adapter_size);

        spin_paper_size.setSelection(Arrays.asList(paper_setup_size).indexOf(kodakVeriteApp.getPaperSize()));

        spin_paper_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos_size = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                        kodakVeriteApp.setPaperType(paper_setup_type[pos_type]);
                        kodakVeriteApp.setPaperSize(paper_setup_size[pos_size]);
                        Intent intent = new Intent(PaperSetup_000.this, PU00_0000.class);
                        startActivity(intent);
                        finish();
                    }
                }, 4000);

            }

        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaperSetup_000.this, PU00_0000.class));
                finish();
            }
        });
        return;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PaperSetup_000.this, PU00_0000.class));
        finish();
    }
}













