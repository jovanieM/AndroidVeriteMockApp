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
import android.widget.ImageButton;
import android.widget.TextView;

public class DS_print extends AppCompatActivity {

    public ImageButton quick, detail, photo4x6, photoletter, document;
    public Button back;
    Fragment fr;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_print);



        quick = (ImageButton) findViewById(R.id.quick);
        detail = (ImageButton) findViewById(R.id.detail);
        back = (Button)findViewById(R.id.back);



        back.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(DS_print.this, DS10_000.class));
            }
        });


        quick.setOnClickListener(new OnClickListener(){

            public void onClick (View v){


                detail.setImageResource(R.mipmap.detail_white);
                quick.setImageResource(R.mipmap.quick_yellow);

                FragmentQuickPrint fr = new FragmentQuickPrint();
                fm = getFragmentManager();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_placer, fr);
                fragmentTransaction.commit();



            }


        });


        detail.setOnClickListener(new OnClickListener(){

            public void onClick (View v){

                quick.setImageResource(R.mipmap.quick_white);
                detail.setImageResource(R.mipmap.detail_yellow);

                FragmentDetailPrint fr = new FragmentDetailPrint();

                fm = getFragmentManager();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_placer, fr);
                fragmentTransaction.commit();


            }
        });


    }
}
