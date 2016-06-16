package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class FragmentTwo extends Fragment {

    public Button color_btn;
    public TextView papersize_txtview;
    Fragment fr;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.fragment_two, container, false);

        View view = inflater.inflate(R.layout.fragment_two, container, false);


        color_btn = (Button)view.findViewById(R.id.color_btn);



            color_btn.setOnClickListener(new OnClickListener() {

        @Override
            public void onClick(View v) {


            fr = new ColorDialogFragment();
            fm = getFragmentManager();
            fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_place,fr);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

            }
        });


        return view;
    }


}


