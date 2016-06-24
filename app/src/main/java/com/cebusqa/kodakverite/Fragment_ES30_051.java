package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by SQA Cebu on 6/15/2016.
 */
public class Fragment_ES30_051 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getActivity(), HM10_000.class);
                startActivity(intent);
                getActivity().finish();
            }
        }, 4000);

        return inflater.inflate(R.layout.fragment_es30_051, container, false);
    }


}
