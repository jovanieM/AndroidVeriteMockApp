package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * Created by SQA Cebu on 6/9/2016.
 */
public class Fragment_ES10_002 extends Fragment {

    ProgressBar progressBar;
    int progressStatus = 0;
    Thread thread;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_es10_002, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //EasySetupAppBar appBar = new EasySetupAppBar();
                Fragment_ES20_000 frag = new Fragment_ES20_000();
                android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

                //transaction.replace(R.id.layout_app_bar, appBar);
                transaction.replace(R.id.my_layout, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }, 4000);

        /* thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus<100){
                    progressStatus++;
                    try{
                        Thread.sleep(3000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                }
            }
        });
        thread.start(); */

        //disable Back key
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                }
                return false;
            }
        });

        return view;
    }
}
