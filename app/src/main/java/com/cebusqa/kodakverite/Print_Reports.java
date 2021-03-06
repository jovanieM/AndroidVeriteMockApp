package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class Print_Reports extends Activity {

    Button btn_report, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.print_reports);

        btn_report = (Button) findViewById(R.id.btn_report);
        btnBack = (Button) findViewById(R.id.back);

        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd = new ProgressDialog(Print_Reports.this, ProgressDialog.THEME_HOLO_LIGHT);
                pd.setMessage("Printing...");
                pd.setCancelable(false);
                pd.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Print_Reports.this, PU00_0000.class);
                        startActivity(intent);
                        finish();
                    }
                }, 4000);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Print_Reports.this, PU00_0000.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Print_Reports.this, PU00_0000.class));
        finish();
    }
}
