package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by SQA Cebu on 6/23/2016.
 */
public class WS00_040 extends Activity {

    Button btnBack, btnSaveSetting;
    EditText etHostname;
    SharedPreferences saved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_040);

        btnBack = (Button) findViewById(R.id.back);
        btnSaveSetting = (Button) findViewById(R.id.btn_save_setting2);
        etHostname = (EditText) findViewById(R.id.et_hostname);
        saved = getSharedPreferences("notes", MODE_PRIVATE);

        //etHostname.setText("Kodak-Verite55 Plus");
        etHostname.setText(saved.getString("hostname", "Kodak-Verite55 Plus"));
        etHostname.setImeOptions(EditorInfo.IME_ACTION_DONE);

        final ProgressDialog pd = new ProgressDialog(WS00_040.this, ProgressDialog.THEME_HOLO_LIGHT);
        pd.setMessage("Getting network information...");
        pd.setCancelable(false);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(WS00_040.this, WS00_000.class));
                pd.dismiss();
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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WS00_040.this, WS00_000.class));
                finish();
            }
        });

        btnSaveSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RingDialog ringDialog = new RingDialog(WS00_040.this, "", "Setting", true);
                ringDialog.run();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder ad = new AlertDialog.Builder(WS00_040.this);
                        ad.setMessage("Setting saved");
                        AlertDialog adc = ad.create();
                        adc.show();

                        if (etHostname.getText().length() > 0) {
                            makeTag(etHostname.getText().toString());
                        }
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(etHostname.getWindowToken(), 0);
                        startActivity(new Intent(WS00_040.this, WS00_000.class));
                        finish();
                    }
                }, 4000);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(WS00_040.this, WS00_000.class));
        finish();
    }

    private void makeTag(String tag) {
        String or = saved.getString(tag, null);
        SharedPreferences.Editor editor = saved.edit();
        editor.putString("hostname", tag);
        editor.commit();
    }
}
