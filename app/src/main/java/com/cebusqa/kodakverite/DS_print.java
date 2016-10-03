package com.cebusqa.kodakverite;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class DS_print extends FragmentActivity implements View.OnClickListener {

    public TextView quick, detail;
    public Button back;
    LinearLayout quickDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_print);

        quick = (TextView) findViewById(R.id.quick);
        detail = (TextView) findViewById(R.id.detail);
        back = (Button)findViewById(R.id.back);
        quickDetail = (LinearLayout) findViewById(R.id.quick_detail_layout);



        back.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragmentQuickPrint quick1 = new FragmentQuickPrint();
        ft.replace(R.id.view, quick1);
        ft.addToBackStack(null);
        ft.commit();

        quick.setOnClickListener(new OnClickListener(){

            public void onClick (View v){
                quickDetail.setActivated(false);
                quick.setTextColor(getResources().getColor(R.color.gold));
                detail.setTextColor(getResources().getColor(R.color.gray));


//                detail.setImageResource(R.mipmap.detail_white);
//                quick.setImageResource(R.mipmap.quick_yellow);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                FragmentQuickPrint quick = new FragmentQuickPrint();
                ft.replace(R.id.view, quick);
                ft.addToBackStack(null);
                ft.commit();

                }
        });


        detail.setOnClickListener(new OnClickListener(){

            public void onClick (View v){
                quickDetail.setActivated(true);
                detail.setTextColor(getResources().getColor(R.color.gold));
                quick.setTextColor(getResources().getColor(R.color.gray));

//
// il.setImageResource(R.mipmap.detail_yellow);



                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                FragmentDetailPrint details = new FragmentDetailPrint();
                ft.replace(R.id.view, details);
                ft.addToBackStack(null);
                ft.commit();

             }
        });


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        finish();
        //super.onBackPressed();
    }
}
