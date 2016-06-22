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
 * Created by SQA Cebu on 6/22/2016.
 */
public class WS00_012 extends Activity {

    TextView tvSSID;
    String ssid;
    CheckBox cBox;
    EditText etPassword;
    Button btnHelp, btnOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_012);

        tvSSID = (TextView) findViewById(R.id.tv_ssid1);
        cBox = (CheckBox) findViewById(R.id.checkBox4);
        etPassword = (EditText) findViewById(R.id.et_pass1);
        btnHelp = (Button) findViewById(R.id.btn_help2);
        btnOther = (Button) findViewById(R.id.btn_other1);


        ssid = WS00_011.ssid_item;
        tvSSID.setText(ssid);

        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_GO)
                {
                    checkBox();
                    startActivity(new Intent(WS00_012.this, WS00_013.class));

                    hideSoftKeyboard();
                }
                return false;
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WS00_012.this, WS00_012H.class));
            }
        });

        btnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WS00_012.this, WS00_011.class));
            }
        });


    }

    public void hideSoftKeyboard(){
        if(this.getCurrentFocus()!=null){
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(imm.HIDE_IMPLICIT_ONLY, 0);
        }
    }

    public void checkBox(){
        cBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b)
                    etPassword.setInputType(129);
                else
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });
    }
}
