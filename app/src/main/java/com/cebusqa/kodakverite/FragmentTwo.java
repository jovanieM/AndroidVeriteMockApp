package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
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
import android.widget.Spinner;

import java.util.Arrays;


public class FragmentTwo extends Fragment {

    Spinner spColor, spPaperSize, spPaperType, spQuality, spResize, spBrightness;
    ArrayAdapter<String> adColor, adPaperSize, adPaperType, adQuality, adResize, adBrightness;
    String[] colorItems, paperSizeItems, paperTypeItems, qualityItems, resizeItems, brightnessItems, pages;
    LinearLayout llCustomResize, onePage, twoL, twoP, fourL, fourP;
    EditText etCustomResize;
    Button btnIncrement, btnDecrement, pagesPerSide_btn;
    ImageButton btnCopy;
    ListView lv_item_item;
    KodakVeriteApp kodakVeriteApp;
    Resources res;
    int ctr = 100;

//    public static final Integer[] pagesPerSide = {R.mipmap.onepage, R.mipmap.two_l, R.mipmap.two_p, R.mipmap.four_l, R.mipmap.four_p  };


    String [] pagesPerSide = {"1", "2", "3"};

    private Handler repeatUpdateHandler = new Handler();
    private boolean mAutoIncrement = false;
    private boolean mAutoDecrement = false;
    private final long REP_DELAY = 50;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_two, container, false);
        kodakVeriteApp = new KodakVeriteApp();
        res = getResources();
        pages = res.getStringArray(R.array.Pages_per_side);

        pagesPerSide_btn = (Button) view.findViewById(R.id.pagesPerSide_btn);
        onePage = (LinearLayout)view.findViewById(R.id.onePage);
        twoL = (LinearLayout)view.findViewById(R.id.twoL);
        twoP = (LinearLayout)view.findViewById(R.id.twoP);
        fourL = (LinearLayout)view.findViewById(R.id.fourL);
        fourP = (LinearLayout)view.findViewById(R.id.fourP);

        //init
     //   listViewPagesPerSide = (ListView)view.findViewById(R.id.listViewPagesPerSide);
        spColor = (Spinner) view.findViewById(R.id.sp_color);
        spPaperSize = (Spinner) view.findViewById(R.id.sp_paper_size);
        spPaperType = (Spinner) view.findViewById(R.id.sp_paper_type);
        spQuality = (Spinner) view.findViewById(R.id.sp_quality);
        spResize = (Spinner) view.findViewById(R.id.sp_resize);
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
        //brightnessItems = getResources().getStringArray(R.array.brightness);

        //init adapter
        adColor = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, colorItems);
        adPaperSize = new ArrayAdapter<>(getActivity(),R.layout.spinner_item, paperSizeItems);
        adPaperType = new ArrayAdapter<>(getActivity(),R.layout.spinner_item, paperTypeItems);
        adQuality = new ArrayAdapter<>(getActivity(),R.layout.spinner_item, qualityItems);
        adResize = new ArrayAdapter<>(getActivity(),R.layout.spinner_item, resizeItems);



        //adBrightness = new ArrayAdapter<>(getActivity(),R.layout.spinner_item, brightnessItems);

        adColor.setDropDownViewResource(R.layout.spinner_dropdown_copy);
        adPaperSize.setDropDownViewResource(R.layout.spinner_dropdown_copy);
        adPaperType.setDropDownViewResource(R.layout.spinner_dropdown_copy);
        adQuality.setDropDownViewResource(R.layout.spinner_dropdown_copy);
        adResize.setDropDownViewResource(R.layout.spinner_dropdown_copy);
        //adBrightness.setDropDownViewResource(R.layout.spinner_dropdown_copy);;


        //set adapter
        spColor.setAdapter(adColor);
        spPaperSize.setAdapter(adPaperSize);
        spPaperType.setAdapter(adPaperType);
        spQuality.setAdapter(adQuality);
        spResize.setAdapter(adResize);
        //spBrightness.setAdapter(adBrightness);

        llCustomResize.setVisibility(View.INVISIBLE);
        etCustomResize.setText(Integer.toString(ctr));

        pagesPerSide_btn.setText(kodakVeriteApp.getPagesPerSide());


        pagesPerSide_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

               alert();


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
                        try{
                            Thread.sleep(4000);
                        }catch (Exception e){

                        }
                        dialog.dismiss();
                    }
                }).start();
            }
        });

        spResize.setSelection(Arrays.asList(resizeItems).indexOf(kodakVeriteApp.getCopyResize()));
        spResize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
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


                        btnIncrement.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(ctr < 400){
                                    ctr += 1;
                                    etCustomResize.setText(Integer.toString(ctr));
                                }
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

                        btnDecrement.setOnLongClickListener(
                                new View.OnLongClickListener() {
                                    public boolean onLongClick(View arg0) {
                                        mAutoDecrement = true;
                                        repeatUpdateHandler.post(new RptUpdater());
                                        return false;
                                    }
                                }
                        );


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







                        break;
                    default:
                        break;
                }

                if(spResize.getSelectedItem()!= resizeItems[0]){
                    pagesPerSide_btn.setText("One");
                }
                   kodakVeriteApp.setCopyResize(resizeItems[i]);

            }


            public void increment() {
                if (ctr < 400) {
                    ctr++;
                    etCustomResize.setText(String.valueOf(ctr));
                }

            }

            public void decrement() {
                if (ctr > 25) {
                    ctr--;
                    etCustomResize.setText(String.valueOf(ctr));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return view;
    }

        public Dialog alert(){
            AlertDialog.Builder alertPages = new AlertDialog.Builder(getActivity());
            alertPages.setTitle("Select Pages Per Side");
            alertPages.setItems(R.array.Pages_per_side, new DialogInterface.OnClickListener(){
                public void onClick (DialogInterface dialog, int which){
                //    int position = Arrays.asList(R.array.Pages_per_side).indexOf(which);

                    kodakVeriteApp.setPagesPerSide(pages[which]);
                    pagesPerSide_btn.setText(pages[which]);

                    if(pagesPerSide_btn.getText()!="One"){
                        spResize.setSelection(0);
                        kodakVeriteApp.setCopyResize(resizeItems[0]);
                    }


                }


            });


            alertPages.show();
            Dialog dialog = alertPages.create();

            return dialog;

        }


}

