package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentTwo extends Fragment {

    ArrayAdapter<String> adColor;
    ComponentAdapter adPaperSize;
    ArrayAdapter<String> adPaperType;
    ArrayAdapter<String> adQuality;
    ArrayAdapter<String> adResize;
    ArrayAdapter<String> adBrightness;
    String[] colorItems, paperSizeItems, paperTypeItems, qualityItems, resizeItems, brightnessItems, pagesPerSideItems;
    LinearLayout llCustomResize, onePage, twoL, twoP, fourL, fourP;
    EditText etCustomResize;
    Button btnIncrement, btnDecrement;
    ImageButton btnCopy;
    ListView lv_item_item;
    TextView spColor, spPaperSize, spPaperType, spQuality, spResize, spBrightness, pagesPerSide_btn;
    KodakVeriteApp kodakVeriteApp;
    Resources res;
    Dialog list_dialog;
    ComponentAdapter array_adapter;
    Context context;
    Bitmap image = null;
    String item_selected;

    int ctr = 100;

//    public static final Integer[] pagesPerSide = {R.mipmap.onepage, R.mipmap.two_l, R.mipmap.two_p, R.mipmap.four_l, R.mipmap.four_p  };

<<<<<<< .mine



=======

    String[] pagesPerSide = {"1", "2", "3"};

>>>>>>> .theirs
    private Handler repeatUpdateHandler = new Handler();
    private boolean mAutoIncrement = false;
    private boolean mAutoDecrement = false;
    private final long REP_DELAY = 50;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setRetainInstance(true);
        final View view = inflater.inflate(R.layout.fragment_two, container, false);
        kodakVeriteApp = new KodakVeriteApp();
        res = getResources();
    //    context = this;

        onePage = (LinearLayout) view.findViewById(R.id.onePage);
        twoL = (LinearLayout) view.findViewById(R.id.twoL);
        twoP = (LinearLayout) view.findViewById(R.id.twoP);
        fourL = (LinearLayout) view.findViewById(R.id.fourL);
        fourP = (LinearLayout) view.findViewById(R.id.fourP);

        //init
        //   listViewPagesPerSide = (ListView)view.findViewById(R.id.listViewPagesPerSide);
        spColor = (TextView) view.findViewById(R.id.sp_color);
        spPaperSize = (TextView) view.findViewById(R.id.sp_paper_size);
        spPaperType = (TextView) view.findViewById(R.id.sp_paper_type);
        spQuality = (TextView) view.findViewById(R.id.sp_quality);
        spResize = (TextView) view.findViewById(R.id.sp_resize);
        pagesPerSide_btn = (TextView) view.findViewById(R.id.pagesPerSide_btn);

        //spBrightness = (Spinner) view.findViewById(R.id.sp_brightness);
        llCustomResize = (LinearLayout) view.findViewById(R.id.ll_custom_resize);
        etCustomResize = (EditText) view.findViewById(R.id.editText3);
        btnIncrement = (Button) view.findViewById(R.id.btn_custom_resize_increment);
        btnDecrement = (Button) view.findViewById(R.id.btn_custom_resize_decrement);
        btnCopy = (ImageButton) view.findViewById(R.id.copy_btn);

        //resources
        colorItems = getResources().getStringArray(R.array.Color_copy);
        paperSizeItems = getResources().getStringArray(R.array.Paper_size_copy);
        paperTypeItems = getResources().getStringArray(R.array.Paper_type);
        qualityItems = getResources().getStringArray(R.array.Quality_copy);
        resizeItems = getResources().getStringArray(R.array.Copy_Resize);
        pagesPerSideItems = getResources().getStringArray(R.array.Pages_per_side);

<<<<<<< .mine
        llCustomResize.setVisibility(View.INVISIBLE);
        etCustomResize.setText(Integer.toString(ctr));




=======
        //init adapter
        adColor = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, colorItems);
        adPaperSize = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, paperSizeItems);
        adPaperType = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, paperTypeItems);
        adQuality = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, qualityItems);
        adResize = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, resizeItems);
>>>>>>> .theirs

        spColor.setText(kodakVeriteApp.getCopyColor());

<<<<<<< .mine
        spColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

=======




>>>>>>> .theirs
                final AlertDialog builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT).create();
                LayoutInflater inflater = getActivity().getLayoutInflater();
                v = (View) inflater.inflate(R.layout.listview_layout, null);

                builder.setView(v);
                builder.setTitle("Color");
                final ListView list = (ListView)v.findViewById(R.id.selection_list);

                ComponentAdapter array_adapter = new ComponentAdapter(getActivity().getApplicationContext(), R.layout.component, R.id.content, colorItems);
                //    ArrayAdapter<String> array_adapter = new ArrayAdapter<String>(FragmentTwo.this.getActivity(), R.layout.component, R.id.content, paperSizeItems);

                list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                list.setAdapter(array_adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

<<<<<<< .mine
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {




=======
        spColor.setSelection(Arrays.asList(colorItems).indexOf(kodakVeriteApp.getCopyColor()));
        /*spColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kodakVeriteApp.setCopyColor(colorItems[position]);
            }
>>>>>>> .theirs

                        item_selected = colorItems[pos].toString();
                        spColor.setText(item_selected);
                        kodakVeriteApp.setCopyColor(item_selected);
                        builder.dismiss();

                    }

                });

                builder.show();
            }
        });*/


        spPaperSize.setText(kodakVeriteApp.getCopyPaperSize());
        spPaperSize.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                final AlertDialog builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT).create();
                LayoutInflater inflater = getActivity().getLayoutInflater();
                v = (View) inflater.inflate(R.layout.listview_layout, null);

                builder.setView(v);
                builder.setTitle("Paper Size");
                final ListView list = (ListView)v.findViewById(R.id.selection_list);

                ComponentAdapter array_adapter = new ComponentAdapter(getActivity().getApplicationContext(), R.layout.component, R.id.content, paperSizeItems);

                list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                list.setAdapter(array_adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                        item_selected = paperSizeItems[pos].toString();
                        spPaperSize.setText(item_selected);
                        kodakVeriteApp.setCopyPaperSize(item_selected);
                        builder.dismiss();

                    }

                });

                builder.show();

            }
        });

        /*
                list_dialog = new Dialog(getActivity());
                list_dialog.setContentView(R.layout.listview_layout);
                list_dialog.setTitle("Paper Size");

                ListView list = (ListView) list_dialog.findViewById(R.id.selection_list);
                ArrayAdapter<String> adapter_papersize = new ArrayAdapter<String>(FragmentTwo.this.getActivity(), R.layout.component, R.id.content, paperSizeItems);
                list.setAdapter(adapter_papersize);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String value = paperSizeItems[position].toString();
                        spPaperSize.setText(value);
                        //   Toast.makeText(FragmentTwo.this.getActivity(), value, Toast.LENGTH_LONG).show();
                        list_dialog.dismiss();

                    }

                });
                list_dialog.show();
        */





        spPaperType.setText(kodakVeriteApp.getCopyPaperType());
        spPaperType.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                final AlertDialog builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT).create();
                LayoutInflater inflater = getActivity().getLayoutInflater();
                v = (View) inflater.inflate(R.layout.listview_layout, null);

                builder.setView(v);
                builder.setTitle("Paper Type");
                final ListView list = (ListView)v.findViewById(R.id.selection_list);

                ComponentAdapter array_adapter = new ComponentAdapter(getActivity().getApplicationContext(), R.layout.component, R.id.content, paperTypeItems);
                //    ArrayAdapter<String> array_adapter = new ArrayAdapter<String>(FragmentTwo.this.getActivity(), R.layout.component, R.id.content, paperSizeItems);

                list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                list.setAdapter(array_adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                        item_selected = paperTypeItems[pos].toString();
                        spPaperType.setText(item_selected);
                        kodakVeriteApp.setCopyPaperType(item_selected);
                        builder.dismiss();

                    }

                });

                builder.show();

            }
        });

        spQuality.setText(kodakVeriteApp.getCopyQuality());
        spQuality.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                final AlertDialog builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT).create();
                LayoutInflater inflater = getActivity().getLayoutInflater();
                v = (View) inflater.inflate(R.layout.listview_layout, null);

                builder.setView(v);
                builder.setTitle("Paper Size");
                final ListView list = (ListView)v.findViewById(R.id.selection_list);

                ComponentAdapter array_adapter = new ComponentAdapter(getActivity().getApplicationContext(), R.layout.component, R.id.content, qualityItems);

                list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                list.setAdapter(array_adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                        item_selected = qualityItems[pos].toString();
                        spQuality.setText(item_selected);
                        kodakVeriteApp.setCopyQuality(item_selected);
                        builder.dismiss();

                    }

                });

                builder.show();

            }
        });


        pagesPerSide_btn.setText(kodakVeriteApp.getPagesPerSide());
        pagesPerSide_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

<<<<<<< .mine
                final AlertDialog builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT).create();
                LayoutInflater inflater = getActivity().getLayoutInflater();
                v = (View) inflater.inflate(R.layout.listview_layout, null);

                builder.setView(v);
                builder.setTitle("Pages Per Side");
                final ListView list = (ListView)v.findViewById(R.id.selection_list);

                ComponentAdapter array_adapter = new ComponentAdapter(getActivity().getApplicationContext(), R.layout.component, R.id.content, pagesPerSideItems);

                list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                list.setAdapter(array_adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                        item_selected = pagesPerSideItems[pos].toString();
                        pagesPerSide_btn.setText(item_selected);
                        kodakVeriteApp.setPagesPerSide(item_selected);

                        if (item_selected != pagesPerSideItems[0]) {
                            spResize.setText(resizeItems[0]);
                            Toast.makeText(getActivity(), "Copy Resize will be returned to 100% Default", Toast.LENGTH_SHORT ).show();
                        }
                        kodakVeriteApp.setCopyResize(resizeItems[0]);
                        builder.dismiss();

                    }

                });

                builder.show();

=======
                alert();


































>>>>>>> .theirs
            }
        });



        btnCopy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog dialog = new ProgressDialog(getActivity());
                dialog.setMessage("Copying");
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(4000);
                        } catch (Exception e) {

                        }
                        dialog.dismiss();
                    }
                }).start();
            }
        });

<<<<<<< .mine
        spResize.setText(kodakVeriteApp.getCopyResize());





















=======
        spResize.setSelection(Arrays.asList(resizeItems).indexOf(kodakVeriteApp.getCopyResize()));
        spResize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        llCustomResize.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        llCustomResize.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        llCustomResize.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        llCustomResize.setVisibility(View.INVISIBLE);
                        break;
                    case 4:
                        llCustomResize.setVisibility(View.INVISIBLE);
                        break;
                    case 5:
                        llCustomResize.setVisibility(View.VISIBLE);
>>>>>>> .theirs

        if((spResize.getText().equals(resizeItems[5]))){
            llCustomResize.setVisibility(View.VISIBLE);
            customized();
        }

<<<<<<< .mine
        spResize.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT).create();
                LayoutInflater inflater = getActivity().getLayoutInflater();
                v = (View) inflater.inflate(R.layout.listview_layout, null);



=======
                        btnIncrement.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (ctr < 400) {
                                    ctr += 1;
                                    etCustomResize.setText(Integer.toString(ctr));
                                }
                            }
                        });
>>>>>>> .theirs

<<<<<<< .mine
                builder.setView(v);
                builder.setTitle("Resize");
                final ListView list = (ListView) v.findViewById(R.id.selection_list);






=======
                        btnDecrement.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (ctr > 25) {
                                    ctr -= 1;
                                    etCustomResize.setText(Integer.toString(ctr));
                                }
                            }
                        });
>>>>>>> .theirs

                ComponentAdapter array_adapter = new ComponentAdapter(getActivity().getApplicationContext(), R.layout.component, R.id.content, resizeItems);

                list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                list.setAdapter(array_adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

<<<<<<< .mine
                        item_selected = resizeItems[pos].toString();
                        spResize.setText(item_selected);
                        kodakVeriteApp.setCopyResize(item_selected);







=======
                        btnIncrement.setOnTouchListener(new View.OnTouchListener() {
                                                            public boolean onTouch(View v, MotionEvent event) {
                                                                if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
                                                                        && mAutoIncrement) {
                                                                    mAutoIncrement = false;
                                                                }
                                                                return false;
                                                            }
                                                        }
                        );
>>>>>>> .theirs

                        if (item_selected != resizeItems[0]) {
                            pagesPerSide_btn.setText(pagesPerSideItems[0]);
                            Toast.makeText(getActivity(), "Pages Per Side will be returned to One", Toast.LENGTH_SHORT ).show();
                        }
                        kodakVeriteApp.setPagesPerSide(pagesPerSideItems[0]);

                        builder.dismiss();

<<<<<<< .mine
                        if((item_selected.equals(resizeItems[5]))){
                            llCustomResize.setVisibility(View.VISIBLE);
                            customized();
                        }else{
                            llCustomResize.setVisibility(View.INVISIBLE);
                        }




=======
                        btnDecrement.setOnTouchListener(new View.OnTouchListener() {
                                                            public boolean onTouch(View v, MotionEvent event) {
                                                                if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
                                                                        && mAutoDecrement) {
                                                                    mAutoDecrement = false;
                                                                }
                                                                return false;
                                                            }
                                                        }
                        );
>>>>>>> .theirs


<<<<<<< .mine

                    }

                });

                builder.show();
            }
        });
        return view;
    }

    public void customized(){
        btnIncrement.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ctr < 400){
                    ctr += 1;
                    etCustomResize.setText(Integer.toString(ctr));
=======
                        break;
                    default:
                        break;















>>>>>>> .theirs
                }
<<<<<<< .mine






=======

                if (spResize.getSelectedItem() != resizeItems[0]) {
                    pagesPerSide_btn.setText("One");
                }
                kodakVeriteApp.setCopyResize(resizeItems[i]);

>>>>>>> .theirs
            }
        });

        btnDecrement.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ctr>25){
                    ctr -= 1;
                    etCustomResize.setText(Integer.toString(ctr));
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


        btnIncrement.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View arg0) {
                        mAutoIncrement = true;
                        repeatUpdateHandler.post(new RptUpdater());
                        return false;
                    }
                }
        );


        btnIncrement.setOnTouchListener(new View.OnTouchListener() {
                                            public boolean onTouch(View v, MotionEvent event) {
                                                if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
                                                        && mAutoIncrement) {
                                                    mAutoIncrement = false;
                                                }
                                                return false;
                                            }
                                        }
        );

<<<<<<< .mine
        btnDecrement.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View arg0) {
                        mAutoDecrement = true;
                        repeatUpdateHandler.post(new RptUpdater());
                        return false;
                    }
                }
        );
=======
    public Dialog alert() {
        AlertDialog.Builder alertPages = new AlertDialog.Builder(getActivity());
        alertPages.setTitle("Select Pages Per Side");
        alertPages.setItems(R.array.Pages_per_side, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //    int position = Arrays.asList(R.array.Pages_per_side).indexOf(which);



>>>>>>> .theirs

<<<<<<< .mine


=======
                kodakVeriteApp.setPagesPerSide(pages[which]);
                pagesPerSide_btn.setText(pages[which]);
>>>>>>> .theirs

<<<<<<< .mine
        btnDecrement.setOnTouchListener(new View.OnTouchListener() {
                                            public boolean onTouch(View v, MotionEvent event) {
                                                if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
                                                        && mAutoDecrement) {
                                                    mAutoDecrement = false;
                                                }
                                                return false;
                                            }
                                        }
        );
=======
                if (pagesPerSide_btn.getText() != "One") {
                    spResize.setSelection(0);
                    kodakVeriteApp.setCopyResize(resizeItems[0]);
                }






>>>>>>> .theirs

    }

<<<<<<< .mine

=======
            }
>>>>>>> .theirs

    public void increment() {
        if (ctr < 400) {
            ctr++;
            etCustomResize.setText(String.valueOf(ctr));
        }

<<<<<<< .mine
    }
=======
        });
>>>>>>> .theirs

    public void decrement() {
        if (ctr > 25) {
            ctr--;
            etCustomResize.setText(String.valueOf(ctr));
        }

<<<<<<< .mine
    }

=======
        alertPages.show();
        Dialog dialog = alertPages.create();
>>>>>>> .theirs

<<<<<<< .mine

=======
        return dialog;
>>>>>>> .theirs

<<<<<<< .mine

=======
    }
>>>>>>> .theirs


}

