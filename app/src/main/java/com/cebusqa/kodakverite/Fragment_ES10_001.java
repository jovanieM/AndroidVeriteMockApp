package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by SQA Cebu on 6/9/2016.
 */
public class Fragment_ES10_001 extends Fragment {

    Button btnBack;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_es10_001, container, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EasySetupAppBar appBar = new EasySetupAppBar();
                Fragment_ES10_002 frag = new Fragment_ES10_002();
                FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();

                transaction.replace(R.id.layout_app_bar, appBar);
                transaction.replace(R.id.my_layout, frag);
                transaction.disallowAddToBackStack();
                transaction.commit();
            }
        }, 4000);

        //disable Back key
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_BACK){
                        return true;
                    }
                }
                return false;
            }
        });
        return view;
    }
}
