package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
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
    private int progressStatus = 0;
    Thread thread;
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_es10_002, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<=4000; i++){
                    final int value = i;
                    try{
                        Thread.sleep(35);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    progressBar.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(value);
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Fragment_ES20_000 frag = new Fragment_ES20_000();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.my_layout, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }, 4000);

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
