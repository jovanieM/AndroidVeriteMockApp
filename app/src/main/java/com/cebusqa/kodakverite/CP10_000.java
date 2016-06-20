package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class CP10_000 extends AppCompatActivity  {

    public ImageButton incre, decre, standardcolor, standardbw, custom;
    public TextView num_copies, color_txtview;
    public int num = 0;
    public String val;
    boolean flag = true;
    public Button color_btn, back;
    Fragment fr;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp10_000);

        num_copies = (TextView) findViewById(R.id.num_copies);
        incre = (ImageButton) findViewById(R.id.incre);
        decre = (ImageButton) findViewById(R.id.decre);
        standardcolor = (ImageButton) findViewById(R.id.standardcolor);
        standardbw = (ImageButton) findViewById(R.id.standardbw);
        custom = (ImageButton) findViewById(R.id.custom);
        color_btn = (Button)findViewById(R.id.color_btn);
        back = (Button)findViewById(R.id.back);


        incre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (num < 101) {
                    num= num+1;
                    val = Integer.toString(num);
                    num_copies.setText(val);

                }
            }
        });


        decre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (num>1)
                num= num-1;
                val = Integer.toString(num);
                num_copies.setText(val);

            }
        });


        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(CP10_000.this, HM10_000.class));
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
