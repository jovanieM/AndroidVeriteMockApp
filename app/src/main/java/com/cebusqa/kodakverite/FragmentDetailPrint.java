package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.transition.Transition;

public class FragmentDetailPrint extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    public ImageButton incre, decre;
    public TextView num_copies, color_output_print, envelope_print, print_quality_print;
    public Spinner spin_papersize;
    public int num = 0;
    public String val;
    boolean flag = true;
    public Button back;


//    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_print, container, false);

        num_copies = (TextView) view.findViewById(R.id.num_copies);
        num_copies.setOnClickListener(this);

        incre = (ImageButton) view.findViewById(R.id.incre);
        incre.setOnClickListener(this);

        decre = (ImageButton) view.findViewById(R.id.decre);
        decre.setOnClickListener(this);

        Spinner spin_papersize = (Spinner) view.findViewById(R.id.spin_papersize);
        Spinner spin_color_output = (Spinner) view.findViewById(R.id.spin_color_output);
        Spinner spin_paper_type = (Spinner) view.findViewById(R.id.spin_paper_type);
        Spinner spin_print_quality = (Spinner) view.findViewById(R.id.spin_print_quality);


        ArrayAdapter<CharSequence> adapter_papertype = ArrayAdapter.createFromResource(this.getActivity(), R.array.Paper_type, R.layout.spinner_item_print);
        adapter_papertype.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin_paper_type.setAdapter(adapter_papertype);

        ArrayAdapter<CharSequence> adapter_papersize = ArrayAdapter.createFromResource(this.getActivity(), R.array.Paper_size_print, R.layout.spinner_item_print);
        adapter_papersize.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin_papersize.setAdapter(adapter_papersize);

        ArrayAdapter<CharSequence> adapter_color = ArrayAdapter.createFromResource(this.getActivity(), R.array.Color_print, R.layout.spinner_item_print);
        adapter_color.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin_color_output.setAdapter(adapter_color);

        ArrayAdapter<CharSequence> adapter_quality = ArrayAdapter.createFromResource(this.getActivity(), R.array.Print_quality, R.layout.spinner_item_print);
        adapter_quality.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin_print_quality.setAdapter(adapter_quality);


        incre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (num < 99) {
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

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}








