package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * Created by SQA Cebu on 6/10/2016.
 */
public class Fragment_ES20_001 extends Fragment {


    @Nullable
    @Override


    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            Fragment_ES20_002 frag = new Fragment_ES20_002();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.replace(R.id.my_layout, frag);
            transaction.addToBackStack(null);
            transaction.commit();
            }
        }, 4000);

        return inflater.inflate(R.layout.fragment_es20_001, container, false);
    }


}
