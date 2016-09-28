package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class DeviceSleepTime_000 extends Activity implements View.OnClickListener {

    private boolean isCanceled;
    Button btnBack, btnSave;
    TextView paper_size, paper_type;
    EditText num_min;
    Button incre, decre;
    int num = 10;
    public int min;
    public String val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_sleep_time);

        //paper_size = (TextView) findViewById(R.id.paper_size);
        //paper_type = (TextView) findViewById(R.id.paper_type);
        num_min = (EditText) findViewById(R.id.num_min);

        btnBack = (Button) findViewById(R.id.back);
        btnBack.setOnClickListener(this);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        incre = (Button) findViewById(R.id.incre);
        incre.setOnClickListener(this);

        decre = (Button) findViewById(R.id.decre);
        decre.setOnClickListener(this);


        //    RingDialog ringDialog = new RingDialog(DeviceSleepTime_000.this, "", "Getting Printer Setting...", true);
        //    ringDialog.run();

        final ProgressDialog pd = new ProgressDialog(DeviceSleepTime_000.this, ProgressDialog.THEME_HOLO_LIGHT);
        pd.setMessage("Getting network information...");
        pd.setCancelable(false);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(DeviceSleepTime_000.this, PU00_0000.class));
                pd.dismiss();
                isCanceled = false;
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

        num_min.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String value = s.toString();
                if (value.isEmpty() || value.length() == 0 || value.equals("") || value == null) {
                    num_min.setText("1");
                } else if (!value.equals("0")) {
                    Integer value1 = Integer.parseInt(value);
                    if (value1 > 120) {
                        num_min.setText("120");
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        String minutes = num_min.getText().toString();
        int min = Integer.parseInt(minutes);

        if (min > 120) {
            num_min.setText("120");
        }

        switch (v.getId()) {
            case R.id.btnSave:
//                RingDialog ringDialog = new RingDialog(DeviceSleepTime_000.this, "", "Setting is saved...", true);
//                ringDialog.run();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(DeviceSleepTime_000.this, PU00_0000.class));
                        finish();
                    }
                }, 4000);
                break;

            case R.id.back:
                startActivity(new Intent(DeviceSleepTime_000.this, PU00_0000.class));
                finish();
                break;

            case R.id.incre:
                if (min < 120) {
                    num = min + 1;
                    val = Integer.toString(num);
                    num_min.setText(val);
                }
                break;

            case R.id.decre:
                if (num_min.getText().equals("0")) {
                    num_min.setText("1");
                } else if (min > 1) {
                    num = min - 1;
                    val = Integer.toString(num);
                    num_min.setText(val);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(DeviceSleepTime_000.this, PU00_0000.class));
        finish();
    }
}
