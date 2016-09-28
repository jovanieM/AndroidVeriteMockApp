package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CP10_000 extends FragmentActivity implements View.OnClickListener {

    public ImageButton incre, decre;
    public RelativeLayout standardcolor, standardbw, custom;
    public TextView num_copies, color_txtview;
    public int num = 0;
    public String val;
    public Button color_btn, back;
    Fragment fr;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;
    Context context;

    private Handler repeatUpdateHandler = new Handler();
    private boolean mAutoIncrement = false;
    private boolean mAutoDecrement = false;
    private final long REP_DELAY = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp10_000);

        context = this;
        num_copies = (TextView) findViewById(R.id.num_copies);
        incre = (ImageButton) findViewById(R.id.incre);
        decre = (ImageButton) findViewById(R.id.decre);
        standardcolor = (RelativeLayout) findViewById(R.id.standardcolor);
        standardbw = (RelativeLayout) findViewById(R.id.standardbw);
        custom = (RelativeLayout) findViewById(R.id.custom);
        //color_btn = (Button)findViewById(R.id.color_btn);
        back = (Button) findViewById(R.id.back);

        num_copies.setText("1");


        incre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (num < 99) {
                    num= num+1;
                    val = Integer.toString(num);
                    num_copies.setText(val);

                }
            }
        });


        decre.setOnClickListener(new OnClickListener() {
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


        back.setOnClickListener(new View.OnClickListener()

                                {
                                    public void onClick(View v) {
                                        finish();
                                    }
                                }

        );

        standardcolor.setOnClickListener(new OnClickListener() {

                                             public void onClick(View v) {

                                                 standardcolor.setBackgroundResource(R.drawable.copysetting_select);
                                                 standardbw.setBackgroundResource(R.drawable.copysetting_unselect);
                                                 custom.setBackgroundResource(R.drawable.copysetting_unselect);

                                                 fr = new FragmentOne();
                                                 fm = getFragmentManager();
                                                 fragmentTransaction = fm.beginTransaction();
                                                 fragmentTransaction.replace(R.id.fragment_place, fr);
                                                 fragmentTransaction.commit();
                                             }
                                         }

        );


        standardbw.setOnClickListener(new OnClickListener() {

                                          public void onClick(View v) {

                                              standardbw.setBackgroundResource(R.drawable.copysetting_select);
                                              standardcolor.setBackgroundResource(R.drawable.copysetting_unselect);
                                              custom.setBackgroundResource(R.drawable.copysetting_unselect);

                                              fr = new FragmentOne();

                                              fm = getFragmentManager();
                                              fragmentTransaction = fm.beginTransaction();
                                              fragmentTransaction.replace(R.id.fragment_place, fr);
                                              fragmentTransaction.commit();


                                          }
                                      }

        );


        custom.setOnClickListener(new OnClickListener() {

                                      public void onClick(View v) {

                                          custom.setBackgroundResource(R.drawable.copysetting_select);
                                          standardcolor.setBackgroundResource(R.drawable.copysetting_unselect);
                                          standardbw.setBackgroundResource(R.drawable.copysetting_unselect);

                                          fr = new FragmentTwo();

                                          fm = getFragmentManager();
                                          fragmentTransaction = fm.beginTransaction();
                                          fragmentTransaction.replace(R.id.fragment_place, fr);
                                          fragmentTransaction.commit();


                                      }

                                  }

        );
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
}
