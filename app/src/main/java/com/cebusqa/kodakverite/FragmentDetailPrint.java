package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

public class FragmentDetailPrint extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    public ImageButton incre, decre;
    public TextView num_copies, color_output_print, envelope_print, print_quality_print;
    public Spinner spin_papersize;
    public int num = 0;
    public String val;
    boolean flag = true;
    public Button back;

    private Handler repeatUpdateHandler = new Handler();
    private boolean mAutoIncrement = false;
    private boolean mAutoDecrement = false;
    private final long REP_DELAY = 50;

    Resources res ;
    String[] paperSize, paperType,printQuality;
    KodakVeriteApp kodakVeriteApp;

//    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_print, container, false);

        res = getResources();
        paperSize = res.getStringArray(R.array.Paper_size_print);
        paperType = res.getStringArray(R.array.Paper_type);
        printQuality = res.getStringArray(R.array.Print_quality);
        kodakVeriteApp = new KodakVeriteApp();

        num_copies = (TextView) view.findViewById(R.id.num_copies);
        num_copies.setOnClickListener(this);

        incre = (ImageButton) view.findViewById(R.id.incre);
        incre.setOnClickListener(this);

        decre = (ImageButton) view.findViewById(R.id.decre);
        decre.setOnClickListener(this);

        num_copies.setText("1");

        Spinner spin_papersize = (Spinner) view.findViewById(R.id.spin_papersize);
        Spinner spin_color_output = (Spinner) view.findViewById(R.id.spin_color_output);
        Spinner spin_paper_type = (Spinner) view.findViewById(R.id.spin_paper_type);
        Spinner spin_print_quality = (Spinner) view.findViewById(R.id.spin_print_quality);


        ArrayAdapter<CharSequence> adapter_papertype = ArrayAdapter.createFromResource(this.getActivity(), R.array.Paper_type, R.layout.spinner_item_print);
        adapter_papertype.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin_paper_type.setAdapter(adapter_papertype);

        spin_paper_type.setSelection(Arrays.asList(paperType).indexOf(kodakVeriteApp.getPaperType()));

        spin_paper_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0 ; i< paperType.length;i++){
                    kodakVeriteApp.setPaperType(paperType[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter_papersize = ArrayAdapter.createFromResource(this.getActivity(), R.array.Paper_size_print, R.layout.spinner_item_print);
        adapter_papersize.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin_papersize.setAdapter(adapter_papersize);

        spin_papersize.setSelection(Arrays.asList(paperSize).indexOf(kodakVeriteApp.getPaperSize()));

        spin_papersize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0 ; i< paperSize.length;i++){
                    kodakVeriteApp.setPaperSize(paperSize[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter_color = ArrayAdapter.createFromResource(this.getActivity(), R.array.Color_print, R.layout.spinner_item_print);
        adapter_color.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin_color_output.setAdapter(adapter_color);

        ArrayAdapter<CharSequence> adapter_quality = ArrayAdapter.createFromResource(this.getActivity(), R.array.Print_quality, R.layout.spinner_item_print);
        adapter_quality.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin_print_quality.setAdapter(adapter_quality);

        spin_print_quality.setSelection(Arrays.asList(printQuality).indexOf(kodakVeriteApp.getPrintQuality()));

        spin_print_quality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0 ; i< printQuality.length;i++){
                    kodakVeriteApp.setPrintQuality(printQuality[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        incre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (num < 100) {
                    num= num+1;
                    val = Integer.toString(num);
                    num_copies.setText(val);

                }
            }
        });


        decre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(num_copies.getText().equals("0")){
                    num_copies.setText("1");
                }else if(num>1) {
                    num = num - 1;
                    val = Integer.toString(num);
                    num_copies.setText(val);
                }
            }
        });



        class RptUpdater implements Runnable {
            public void run() {
                if (mAutoIncrement) {
                    increment();
                    repeatUpdateHandler.postDelayed(new RptUpdater(), REP_DELAY);
                } else if (mAutoDecrement) {
                    decrement();
                    repeatUpdateHandler.postDelayed(new RptUpdater(), REP_DELAY);
                }
            }
        }


        incre.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View arg0) {
                        mAutoIncrement = true;
                        repeatUpdateHandler.post(new RptUpdater());
                        return false;
                    }
                }
        );


        incre.setOnTouchListener(new View.OnTouchListener() {
                                     public boolean onTouch(View v, MotionEvent event) {
                                         if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
                                                 && mAutoIncrement) {
                                             mAutoIncrement = false;
                                         }
                                         return false;
                                     }
                                 }
        );

        decre.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View arg0) {
                        mAutoDecrement = true;
                        repeatUpdateHandler.post(new RptUpdater());
                        return false;
                    }
                }
        );


        decre.setOnTouchListener(new View.OnTouchListener() {
                                     public boolean onTouch(View v, MotionEvent event) {
                                         if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
                                                 && mAutoDecrement) {
                                             mAutoDecrement = false;
                                         }
                                         return false;
                                     }
                                 }
        );


        return view;
    }

    public void increment() {
        if (num < 100) {
            num++;
            num_copies.setText(String.valueOf(num));
        }

    }

    public void decrement() {
        if (num > 1) {
            num--;
            num_copies.setText(String.valueOf(num));
        }

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








