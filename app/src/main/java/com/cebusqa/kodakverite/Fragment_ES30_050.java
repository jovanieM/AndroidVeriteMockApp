package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * Created by SQA Cebu on 6/14/2016.
 */
public class Fragment_ES30_050 extends Fragment {

    Button btnDone, btnBack;
    CheckBox cbAgreement;
    Handler updateHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_es30_050, container, false);

        btnDone = (Button) view.findViewById(R.id.btn_done);
        btnBack = (Button) view.findViewById(R.id.back);
        cbAgreement = (CheckBox) view.findViewById(R.id.checkBox);
        cbAgreement.setChecked(true);
        updateHandler = new Handler();

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RingDialog ringDialog = new RingDialog(getActivity(), "", "Setting", true);
                ringDialog.run();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Fragment_ES30_051 frag = new Fragment_ES30_051();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();

                        transaction.replace(R.id.my_layout, frag);
                        transaction.commit();
                    }
                }, 4000);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        //disable Back key
        /* view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_BACK){
                        return true;
                    }
                }
                return false;
            }
        }); */

        return view;
    }
}

