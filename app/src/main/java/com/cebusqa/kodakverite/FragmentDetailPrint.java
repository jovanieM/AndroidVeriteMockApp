package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.transition.Transition;

public class FragmentDetailPrint extends Fragment {


    public ImageButton incre, decre;
    public TextView num_copies;
    public int num = 0;
    public String val;
    boolean flag = true;
    public Button back;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_print, container, false);

        num_copies = (TextView) view.findViewById(R.id.num_copies);
        incre = (ImageButton) view.findViewById(R.id.incre);
        decre = (ImageButton) view.findViewById(R.id.decre);


        incre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (num < 101) {
                    num = num + 1;
                    val = Integer.toString(num);
                    num_copies.setText(val);

                }
            }
        });


        decre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (num > 1)
                    num = num - 1;
                val = Integer.toString(num);
                num_copies.setText(val);

            }
        });

    return view;

    }


}


