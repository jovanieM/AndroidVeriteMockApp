package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by SQA Cebu on 6/8/2016.
 */
public class Fragment_ES10_000 extends Fragment {

    Button btnOk, btnSkip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_es10_000, container, false);

        View view = inflater.inflate(R.layout.fragment_es10_000, container, false);
        btnOk = (Button) view.findViewById(R.id.btnOk);
        btnSkip = (Button) view.findViewById(R.id.btnSkip);


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_ES10_001 newfrag = new Fragment_ES10_001();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.my_layout, newfrag);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HM10_000.class));
                getActivity().finish();
            }
        });


        return view;
    }
}
