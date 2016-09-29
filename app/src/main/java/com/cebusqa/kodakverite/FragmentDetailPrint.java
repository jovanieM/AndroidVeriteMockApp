package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentDetailPrint extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    public ImageButton incre, decre;
    public TextView num_copies, color_output_print, envelope_print, print_quality_print;
    public TextView spin_papersize, spin_color_output, spin_paper_type, spin_print_quality;
    public int num = 0;
    public String val;
    boolean flag = true;
    public Button back;

    private Handler repeatUpdateHandler = new Handler();
    private boolean mAutoIncrement = false;
    private boolean mAutoDecrement = false;
    private final long REP_DELAY = 50;
    String item_selected;

    Resources res;
    String[] paperSize, paperType, printQuality, printColor;
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
        printColor = res.getStringArray(R.array.Color_print);
        kodakVeriteApp = new KodakVeriteApp();
        num_copies = (TextView) view.findViewById(R.id.num_copies);

        //num_copies.setOnClickListener(this);

        incre = (ImageButton) view.findViewById(R.id.incre);
        incre.setOnClickListener(this);

        decre = (ImageButton) view.findViewById(R.id.decre);
        decre.setOnClickListener(this);

        num_copies.setText(kodakVeriteApp.getPrintCopies());

        spin_papersize = (TextView) view.findViewById(R.id.spin_papersize);
        spin_color_output = (TextView) view.findViewById(R.id.spin_color_output);
        spin_paper_type = (TextView) view.findViewById(R.id.spin_paper_type);
        spin_print_quality = (TextView) view.findViewById(R.id.spin_print_quality);


        spin_color_output.setText(kodakVeriteApp.getPrintColor());

        spin_color_output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT).create();
                LayoutInflater inflater = getActivity().getLayoutInflater();
                v = (View) inflater.inflate(R.layout.listview_layout, null);

                builder.setView(v);
                builder.setTitle("Color");
                final ListView list = (ListView) v.findViewById(R.id.selection_list);

                PrintComponentAdapter array_adapter = new PrintComponentAdapter(getActivity().getApplicationContext(), R.layout.component, R.id.content, printColor);
                list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                list.setAdapter(array_adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                        item_selected = printColor[pos].toString();
                        spin_color_output.setText(item_selected);
                        kodakVeriteApp.setPrintColor(item_selected);
                        builder.dismiss();

                    }

                });

                builder.show();
            }
        });


        spin_paper_type.setText(kodakVeriteApp.getPaperType());

        spin_paper_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT).create();
                LayoutInflater inflater = getActivity().getLayoutInflater();
                v = (View) inflater.inflate(R.layout.listview_layout, null);

                builder.setView(v);
                builder.setTitle("Paper Type");
                final ListView list = (ListView) v.findViewById(R.id.selection_list);

                PrintComponentAdapter array_adapter = new PrintComponentAdapter(getActivity().getApplicationContext(), R.layout.component, R.id.content, paperType);
                list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                list.setAdapter(array_adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                        item_selected = paperType[pos].toString();
                        spin_paper_type.setText(item_selected);
                        kodakVeriteApp.setPaperType(item_selected);
                        builder.dismiss();

                    }

                });

                builder.show();
            }
        });


        spin_papersize.setText(kodakVeriteApp.getPaperSize());

        spin_papersize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT).create();
                LayoutInflater inflater = getActivity().getLayoutInflater();
                v = (View) inflater.inflate(R.layout.listview_layout, null);

                builder.setView(v);
                builder.setTitle("Paper Size");
                final ListView list = (ListView) v.findViewById(R.id.selection_list);

                //PrintComponentAdapter array_adapter = new PrintComponentAdapter(getActivity().getApplicationContext(), R.layout.component, R.id.content, paperSize);
                //    ArrayAdapter<String> array_adapter = new ArrayAdapter<String>(FragmentTwo.this.getActivity(), R.layout.component, R.id.content, paperSizeItems);

                ComponentAdapter array_adapter = new ComponentAdapter(getActivity().getApplicationContext(), R.layout.component, R.id.content, paperSize);
                list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                list.setAdapter(array_adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                        item_selected = paperSize[pos].toString();
                        spin_papersize.setText(item_selected);
                        kodakVeriteApp.setPaperSize(item_selected);
                        builder.dismiss();
                    }

                });

                builder.show();
            }
        });


        spin_print_quality.setText(kodakVeriteApp.getPrintQuality());

        spin_print_quality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT).create();
                LayoutInflater inflater = getActivity().getLayoutInflater();
                v = (View) inflater.inflate(R.layout.listview_layout, null);

                builder.setView(v);
                builder.setTitle("Quality");
                final ListView list = (ListView) v.findViewById(R.id.selection_list);

                PrintComponentAdapter array_adapter = new PrintComponentAdapter(getActivity().getApplicationContext(), R.layout.component, R.id.content, printQuality);
                //    ArrayAdapter<String> array_adapter = new ArrayAdapter<String>(FragmentTwo.this.getActivity(), R.layout.component, R.id.content, paperSizeItems);

                list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                list.setAdapter(array_adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                        item_selected = printQuality[pos].toString();
                        spin_print_quality.setText(item_selected);
                        kodakVeriteApp.setPrintQuality(item_selected);
                        builder.dismiss();

                    }

                });

                builder.show();
            }
        });


        incre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (num < 99) {
                    num = num + 1;
                    val = Integer.toString(num);
                    num_copies.setText(val);
                    kodakVeriteApp.setPrintCopies(val);
                }
            }
        });


        decre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (num_copies.getText().equals("0")) {
                    num_copies.setText("1");
                } else if (num > 1) {
                    num = num - 1;
                    val = Integer.toString(num);
                    num_copies.setText(val);
                    kodakVeriteApp.setPrintCopies(val);
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
        if (num < 99) {
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








