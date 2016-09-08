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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Arvin on 6/22/2016.
 */
public class WS00_014 extends Activity {

    EditText etSSID, etPassword;
    String[] items;
    ArrayAdapter<String> adapter;
    Spinner spinner;
    RelativeLayout relativeLayout;
    CheckBox cbox;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_014);

        etSSID = (EditText) findViewById(R.id.et_ssid1);
        etPassword = (EditText) findViewById(R.id.et_password3);
        spinner = (Spinner) findViewById(R.id.sp_security1);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl_password1);
        cbox = (CheckBox) findViewById(R.id.checkBox4);
        btnBack = (Button) findViewById(R.id.back);

        cbox.setChecked(true);
        items = getResources().getStringArray(R.array.security);
        adapter = new ArrayAdapter<>(this, R.layout.spinner_wifi_item, items);
        adapter.setDropDownViewResource(R.layout.spinner_wifi_dropdown);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        relativeLayout.setVisibility(View.INVISIBLE);
                        checkBox();
                        showSoftKeyboard(etSSID);
                        etSSID.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                                if (i == EditorInfo.IME_ACTION_GO) {
                                    startActivity(new Intent(WS00_014.this, WS00_013.class));
                                    finish();
                                    hideSoftKeyboard(etSSID);
                                }
                                return false;
                            }
                        });
                        break;

                    case 1:
                        relativeLayout.setVisibility(View.VISIBLE);
                        checkBox();
                        etPassword.requestFocus();
                        showSoftKeyboard(etPassword);
                        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                                if (i == EditorInfo.IME_ACTION_GO) {
                                    startActivity(new Intent(WS00_014.this, WS00_013.class));
                                    finish();
                                    hideSoftKeyboard(etPassword);
                                }
                                return false;
                            }
                        });
                        break;

                    case 2:
                        relativeLayout.setVisibility(View.VISIBLE);
                        checkBox();
                        etPassword.requestFocus();
                        showSoftKeyboard(etPassword);
                        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                                if (i == EditorInfo.IME_ACTION_GO) {
                                    startActivity(new Intent(WS00_014.this, WS00_013.class));
                                    finish();
                                    hideSoftKeyboard(etPassword);
                                }
                                return false;
                            }
                        });
                        break;

                    case 3:
                        relativeLayout.setVisibility(View.VISIBLE);
                        checkBox();
                        etPassword.requestFocus();
                        showSoftKeyboard(etPassword);
                        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                                if (i == EditorInfo.IME_ACTION_GO) {
                                    startActivity(new Intent(WS00_014.this, WS00_013.class));
                                    finish();
                                    hideSoftKeyboard(etPassword);
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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WS00_014.this, WS00_011.class));
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
        cbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    etPassword.setInputType(129);
                } else {
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(WS00_014.this, WS00_011.class));
        finish();
    }
}
