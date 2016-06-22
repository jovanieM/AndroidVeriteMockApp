package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by SQA Cebu on 6/14/2016.
 */
public class Fragment_ES30_050 extends Fragment {

    Button btnDone;
    Handler updateHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_es30_050, container, false);

        btnDone = (Button) view.findViewById(R.id.btn_done);

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
        return view;
    }
}

