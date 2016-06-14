package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;


public class CP10_000 extends AppCompatActivity {

    public ImageButton incre, decre, standardcolor, standardbw, custom;
    public EditText copies;
    public int num = 0;
    public String val;
    boolean flag = true;
    Fragment fr;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp10_000);

        copies = (EditText) findViewById(R.id.copies);
        incre = (ImageButton) findViewById(R.id.incre);
        decre = (ImageButton) findViewById(R.id.decre);
        standardcolor = (ImageButton) findViewById(R.id.standardcolor);
        standardbw = (ImageButton) findViewById(R.id.standardbw);
        custom = (ImageButton) findViewById(R.id.custom);



        incre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (num < 101) {
                    num= num+1;
                    val = Integer.toString(num);
                    copies.setText(val);

                }
            }
        });


        decre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num>0)
                num= num-1;
                val = Integer.toString(num);
                copies.setText(val);

            }
        });


        standardcolor.setOnClickListener(new OnClickListener(){

            public void onClick (View v){


                           standardcolor.setImageResource(R.mipmap.standardcolor_yellow);
                           standardbw.setImageResource(R.mipmap.standardbw_white);
                           custom.setImageResource(R.mipmap.custom_white);

                fr = new FragmentOne();

                fm = getFragmentManager();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, fr);
                fragmentTransaction.commit();



            }


        });


        standardbw.setOnClickListener(new OnClickListener(){

            public void onClick (View v){

                            standardbw.setImageResource(R.mipmap.standardbw_yellow);
                            standardcolor.setImageResource(R.mipmap.standardcolor_white);
                            custom.setImageResource(R.mipmap.custom_white);

                fr = new FragmentOne();

                fm = getFragmentManager();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, fr);
                fragmentTransaction.commit();


            }
        });


        custom.setOnClickListener(new OnClickListener(){

            public void onClick (View v){

                            custom.setImageResource(R.mipmap.custom_yellow);
                            standardcolor.setImageResource(R.mipmap.standardcolor_white);
                            standardbw.setImageResource(R.mipmap.standardbw_white);

                fr = new FragmentTwo();

                fm = getFragmentManager();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, fr);
                fragmentTransaction.commit();
               }

               });


}


}
