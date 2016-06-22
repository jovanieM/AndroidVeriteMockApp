package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by SQA Cebu on 6/13/2016.
 */
public class Fragment_ES30_010 extends Fragment {

    EditText editText, etPassword;
    Spinner spinner;
    String[] items;
    ArrayAdapter<String> adapter;
    int itemPos;
    String itemSecurity;
    RelativeLayout rlPassword;
    CheckBox checkBox;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_es30_010, container, false);

        editText = (EditText) view.findViewById(R.id.editText);
        etPassword = (EditText) view.findViewById(R.id.et_password);
        spinner = (Spinner) view.findViewById(R.id.sp_security);
        rlPassword = (RelativeLayout) view.findViewById(R.id.rl_password);
        checkBox = (CheckBox) view.findViewById(R.id.checkBox2);

        items = getResources().getStringArray(R.array.security);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch(i){
                    case 0:
                        itemPos = adapterView.getSelectedItemPosition();
                        itemSecurity = adapterView.getSelectedItem().toString();
                        rlPassword.setVisibility(View.INVISIBLE);
                        break;

                    case 1:
                        itemPos = adapterView.getSelectedItemPosition();
                        itemSecurity = adapterView.getSelectedItem().toString();
                        rlPassword.setVisibility(View.VISIBLE);
                        checkBox.setVisibility(View.VISIBLE);

                        checkBox();

                        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                                if(i == EditorInfo.IME_ACTION_DONE){
                                    Fragment_ES30_050 frag = new Fragment_ES30_050();
                                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                                    transaction.replace(R.id.my_layout, frag);
                                    transaction.commit();

                                    hideSoftKeyboard();
                                }
                                return false;
                            }
                        });

                        break;

                    case 2:
                        itemPos = adapterView.getSelectedItemPosition();
                        itemSecurity = adapterView.getSelectedItem().toString();
                        rlPassword.setVisibility(View.VISIBLE);
                        checkBox.setVisibility(View.VISIBLE);

                        break;

                    case 3:
                        itemPos = adapterView.getSelectedItemPosition();
                        itemSecurity = adapterView.getSelectedItem().toString();
                        rlPassword.setVisibility(View.VISIBLE);
                        checkBox.setVisibility(View.VISIBLE);

                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    public void hideSoftKeyboard(){
        if(getActivity().getCurrentFocus()!=null){
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(imm.HIDE_NOT_ALWAYS, 0);
        }
    }

    public void checkBox(){
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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