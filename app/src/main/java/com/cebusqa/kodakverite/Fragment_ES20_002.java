package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by SQA Cebu on 6/10/2016.
 */
public class Fragment_ES20_002 extends Fragment {

    Button btnScan, btnHelp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_es20_002, container, false);
        btnScan = (Button) view.findViewById(R.id.btn_start_scan);
        btnHelp = (Button) view.findViewById(R.id.btn_help);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_ES20_003 frag = new Fragment_ES20_003();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.my_layout, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_ES20_002_00 frag = new Fragment_ES20_002_00();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.my_layout, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}