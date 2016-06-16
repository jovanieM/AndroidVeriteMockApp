package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by SQA Cebu on 6/16/2016.
 */
public class Fragment_ES30_040 extends Fragment {

    String ssid;
    static public TextView tvSSID;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_es30_040, container, false);

        tvSSID = (TextView) view.findViewById(R.id.tv_ssid);

        ssid = Fragment_ES30_001.itemSSID;
        tvSSID.setText(ssid);

        return view;
    }
}
