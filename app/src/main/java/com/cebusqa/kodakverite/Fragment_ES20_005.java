package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by SQA Cebu on 6/10/2016.
 */
public class Fragment_ES20_005 extends Fragment {

    Button btnOk;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_es20_005, container, false);
        btnOk = (Button) view.findViewById(R.id.btnOk_1);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_ES30_000 frag = new Fragment_ES30_000();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.my_layout, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}
