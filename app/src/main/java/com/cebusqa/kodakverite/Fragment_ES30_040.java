package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by SQA Cebu on 6/16/2016.
 */
public class Fragment_ES30_040 extends Fragment {

    String ssid;
    static public TextView tvSSID;
    EditText etPass;
    Button btnHelp, btnOther;
    CheckBox cbPassword;
    RelativeLayout rlPass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_es30_040, container, false);

        tvSSID = (TextView) view.findViewById(R.id.tv_ssid);
        etPass = (EditText) view.findViewById(R.id.et_pass1);
        cbPassword = (CheckBox) view.findViewById(R.id.checkBox3);
        btnHelp = (Button) view.findViewById(R.id.btn_help1);
        btnOther = (Button) view.findViewById(R.id.btn_other);
        rlPass = (RelativeLayout) view.findViewById(R.id.rl_password1);

        ssid = Fragment_ES30_001.itemSSID;
        tvSSID.setText(ssid);
        etPass.setCursorVisible(true);
        etPass.requestFocus();
        showInputMethod();
        checkBox();

        etPass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    //showInputMethod();
                    checkBox();

                    Fragment_ES30_050 frag = new Fragment_ES30_050();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    transaction.replace(R.id.my_layout, frag);
                    transaction.commit();

                    hideSoftKeyboard();
                }
                return false;
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_ES30_041 frag = new Fragment_ES30_041();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.my_layout, frag);
                transaction.addToBackStack(null);
                transaction.commit();

                hideSoftKeyboard();
            }
        });

        btnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_ES30_001 frag = new Fragment_ES30_001();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.my_layout, frag);
                transaction.addToBackStack(null);
                transaction.commit();

                hideSoftKeyboard();
            }
        });

        //disable Back key
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                }
                return false;
            }
        });

        return view;
    }

    public void showInputMethod() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void hideSoftKeyboard() {
        if (getActivity().getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(imm.HIDE_NOT_ALWAYS, 0);
        }
    }

    public void checkBox() {
        cbPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    etPass.setInputType(129);
                } else {
                    etPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }
}
