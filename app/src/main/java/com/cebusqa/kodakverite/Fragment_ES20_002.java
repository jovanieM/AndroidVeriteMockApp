package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by SQA Cebu on 6/10/2016.
 */
public class Fragment_ES20_002 extends Fragment {

    Button btnScan, btnHelp, btnBack;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_es20_002, container, false);

        btnScan = (Button) view.findViewById(R.id.btn_start_scan);
        btnHelp = (Button) view.findViewById(R.id.btn_help);
        btnBack = (Button) view.findViewById(R.id.back);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasySetupAppBar appBar = new EasySetupAppBar();
                Fragment_ES20_003 frag = new Fragment_ES20_003();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                //transaction.replace(R.id.layout_app_bar, appBar);
                transaction.replace(R.id.my_layout, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasySetupAppBarBack appBarBack = new EasySetupAppBarBack();
                Fragment_ES20_002_00 frag = new Fragment_ES20_002_00();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                //transaction.replace(R.id.layout_app_bar, appBarBack);
                transaction.replace(R.id.my_layout, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_ES20_000 frag = new Fragment_ES20_000();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.my_layout, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //disable Back key
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_BACK){

                        Fragment_ES20_000 frag = new Fragment_ES20_000();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();

                        transaction.replace(R.id.my_layout, frag);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                }
                return true; //false;
            }
        });

        return view;
    }
}