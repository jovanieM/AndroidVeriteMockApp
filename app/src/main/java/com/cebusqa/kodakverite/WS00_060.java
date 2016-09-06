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
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Arvin on 6/23/2016.
 */
public class WS00_060 extends Activity {

    EditText etFriendlyName;
    Button btnBack, btnSaveSetting;
    SharedPreferences savedNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_060);

        etFriendlyName = (EditText) findViewById(R.id.et_friendly_name);
        btnBack = (Button) findViewById(R.id.back);
        btnSaveSetting = (Button) findViewById(R.id.btn_save_setting3);
        savedNotes = getSharedPreferences("notes", MODE_PRIVATE);

        final ProgressDialog pd = new ProgressDialog(WS00_060.this);
        pd.setMessage("Getting network information...");
        pd.setCancelable(false);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(WS00_060.this, WS00_000.class));
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

        // etFriendlyName.append("Kodak Verite 101");
        etFriendlyName.setText(savedNotes.getString("tag", "Kodak Verite 101"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WS00_060.this, WS00_000.class));
                finish();
            }
        });

        btnSaveSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RingDialog ringDialog = new RingDialog(WS00_060.this, "", "Setting", true);
                ringDialog.run();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder ad = new AlertDialog.Builder(WS00_060.this);
                        ad.setMessage("Setting saved");
                        AlertDialog adc = ad.create();
                        adc.show();

                        if (etFriendlyName.getText().length() > 0) {
                            makeTag(etFriendlyName.getText().toString());
                        }
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(etFriendlyName.getWindowToken(), 0);

                        startActivity(new Intent(WS00_060.this, WS00_000.class));
                        finish();
                    }
                }, 4000);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(WS00_060.this, WS00_000.class));
        finish();
    }

    private void makeTag(String tag) {
        String or = savedNotes.getString(tag, null);
        SharedPreferences.Editor preferenceEditor = savedNotes.edit();
        preferenceEditor.putString("tag", tag);
        preferenceEditor.commit();
    }
}
