package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * Created by SQA Cebu on 6/9/2016.
 */
public class Fragment_ES10_002 extends Fragment {

    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_es10_002, container, false);

        //Progress bar


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Fragment_ES20_000 frag = new Fragment_ES20_000();
                android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.my_layout, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }, 4000);

        return view;
    }
}
