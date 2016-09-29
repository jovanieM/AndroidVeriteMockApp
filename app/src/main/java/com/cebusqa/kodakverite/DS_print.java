package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class DS_print extends FragmentActivity implements View.OnClickListener {

    //public ImageButton quick, detail;
    public Button back;
    TextView quick, detail;
    RelativeLayout quick_detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_print);

        //quick = (ImageButton) findViewById(R.id.quick);
        //detail = (ImageButton) findViewById(R.id.detail);
        quick = (TextView) findViewById(R.id.tv_quick);
        detail = (TextView) findViewById(R.id.tv_detail);
        quick_detail = (RelativeLayout) findViewById(R.id.quick_detail);
        back = (Button)findViewById(R.id.back);

        back.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(DS_print.this, DS10_000.class));
                finish();
            }
        });

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        final FragmentQuickPrint quick1 = new FragmentQuickPrint();
        ft.replace(R.id.view, quick1);
        ft.addToBackStack(null);
        ft.commit();

        quick.setOnClickListener(new OnClickListener(){

            public void onClick (View v){

                //detail.setImageResource(R.mipmap.detail_white);
                //quick.setImageResource(R.mipmap.quick_yellow);

                quick_detail.setBackgroundResource(R.mipmap.settingtab_left);
                quick.setTextColor(getResources().getColor(R.color.yellow));
                detail.setTextColor(getResources().getColor(R.color.gray));

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

                //quick.setImageResource(R.mipmap.quick_white);
                //detail.setImageResource(R.mipmap.detail_yellow);

                quick_detail.setBackgroundResource(R.mipmap.settingtab_right);
                quick.setTextColor(getResources().getColor(R.color.gray));
                detail.setTextColor(getResources().getColor(R.color.yellow));

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
        startActivity(new Intent(DS_print.this, DS10_000.class));
        finish();
        //super.onBackPressed();
    }
}
