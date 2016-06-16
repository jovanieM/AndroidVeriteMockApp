package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by SQA Cebu on 6/13/2016.
 */
public class Fragment_ES30_012 extends Fragment {

    TextView securityName;
    Bundle bundle;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_es30_012, container, false);
        securityName = (TextView) view.findViewById(R.id.tvSecurityName);
        bundle = this.getArguments();
        String myString = bundle.getString("security");
        securityName.setText(myString);
        return view;
    }
}
