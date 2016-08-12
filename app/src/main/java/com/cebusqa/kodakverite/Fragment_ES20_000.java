package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.webkit.WebView;

/**
 * Created by SQA Cebu on 6/10/2016.
 */
public class Fragment_ES20_000 extends Fragment {

    Button btnNext, btnDefault;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_es20_000, container, false);
        btnNext = (Button) view.findViewById(R.id.btn_next);
        btnDefault = (Button) view.findViewById(R.id.btn_default);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasySetupAppBar appBar = new EasySetupAppBar();
                Fragment_ES20_001 frag = new Fragment_ES20_001();
                android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.layout_app_bar, appBar);
                transaction.replace(R.id.my_layout, frag, "Tag");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_ES20_005 frag = new Fragment_ES20_005();
                android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.my_layout, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

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
