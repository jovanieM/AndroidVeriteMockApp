package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by SQA Cebu on 6/22/2016.
 */
public class WS00_014 extends Activity {

    EditText etSSID, etPassword;
    String[] items;
    ArrayAdapter<String> adapter;
    Spinner spinner;
    RelativeLayout relativeLayout;
    CheckBox cbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_014);

        etSSID = (EditText) findViewById(R.id.et_ssid1);
        etPassword = (EditText) findViewById(R.id.et_password3);
        spinner = (Spinner) findViewById(R.id.sp_security1);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl_password1);
        cbox = (CheckBox) findViewById(R.id.checkBox4);

        items = getResources().getStringArray(R.array.security);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0:
                        relativeLayout.setVisibility(View.INVISIBLE);
                        checkBox();
                        etSSID.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                                if(i == EditorInfo.IME_ACTION_GO){
                                    startActivity(new Intent(WS00_014.this, WS00_013.class));
                                    hideSoftKeyboard();
                                }

                                return false;
                            }
                        });
                        break;

                    case 1:
                        relativeLayout.setVisibility(View.VISIBLE);
                        checkBox();
                        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                                if(i == EditorInfo.IME_ACTION_GO)
                                {
                                    startActivity(new Intent(WS00_014.this, WS00_013.class));
                                    hideSoftKeyboard();
                                }
                                return false;
                            }
                        });
                        break;

                    case 2:
                        relativeLayout.setVisibility(View.VISIBLE);
                        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                                if(i == EditorInfo.IME_ACTION_GO)
                                {
                                    startActivity(new Intent(WS00_014.this, WS00_013.class));
                                }
                                return false;
                            }
                        });
                        break;

                    case 3:
                        relativeLayout.setVisibility(View.VISIBLE);
                        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                                if(i == EditorInfo.IME_ACTION_GO)
                                {
                                    startActivity(new Intent(WS00_014.this, WS00_013.class));
                                }
                                return false;
                            }
                        });
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void showSoftKeyboard(){
        if(this.getCurrentFocus()!=null){
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(imm.SHOW_IMPLICIT, 0);
        }
    }

    public void hideSoftKeyboard(){
        if(this.getCurrentFocus()!=null){
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(imm.HIDE_NOT_ALWAYS, 0);}
    }

    public void checkBox(){
        cbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b){
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else{
                    etPassword.setInputType(129);
                }
            }
        });
    }
}
