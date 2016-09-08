package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Arvin on 6/22/2016.
 */
public class WS00_012 extends Activity {

    TextView tvSSID;
    String ssid;
    CheckBox cBox;
    EditText etPass1;
    Button btnHelp, btnOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_012);

        tvSSID = (TextView) findViewById(R.id.tv_ssid1);
        cBox = (CheckBox) findViewById(R.id.cb_et_pass);
        etPass1 = (EditText) findViewById(R.id.et_pass10);
        btnHelp = (Button) findViewById(R.id.btn_help2);
        btnOther = (Button) findViewById(R.id.btn_other1);

        ssid = WS00_011.ssid_item;
        tvSSID.setText(ssid);

        checkBox();
        cBox.setChecked(true);
        etPass1.requestFocus();
        showSoftKeyboard(etPass1);
        etPass1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO) {
                    etPass1.requestFocus();
                    showSoftKeyboard(etPass1);
                    startActivity(new Intent(WS00_012.this, WS00_013.class));
                    finish();
                    hideSoftKeyboard(etPass1);
                }
                return false;
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WS00_012.this, WS00_012H.class));
                finish();
            }
        });

        btnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WS00_012.this, WS00_011.class));
                finish();
            }
        });
    }

    public void showSoftKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public void hideSoftKeyboard(EditText editText) {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public void checkBox() {
        cBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b)
                    etPass1.setInputType(129);
                else
                    etPass1.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
