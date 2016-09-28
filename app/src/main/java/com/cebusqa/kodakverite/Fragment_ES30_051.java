package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
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

        View view = inflater.inflate(R.layout.fragment_es30_051, container, false);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                close();

                /* startActivity(new Intent(getActivity(), HM10_000.class));
                getActivity().onBackPressed();

                 AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                ad.setMessage("Connection complete");
                ad.setCancelable(false);
                AlertDialog adc = ad.create();
                adc.show(); */
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

    public void close() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
        builder.setMessage("Connection complete.");
        builder.setCancelable(false);
        final Dialog dialog = builder.create();
        dialog.show();
        final Handler handler = new Handler();
        final Runnable run = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getActivity(), HM10_000.class));
                getActivity().onBackPressed();
                //getFragmentManager().findFragmentByTag("tag").onDestroy();
                dialog.dismiss();
            }
        };
        handler.postDelayed(run, 4000);
    }
}
