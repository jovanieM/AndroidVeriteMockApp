package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DS_scan extends AppCompatActivity {

    public ImageButton scan_document_settings, scan_photo_settings;
    Fragment fr;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_scan);

  //      scan_document_settings = (ImageButton)findViewById(R.id.scan_document_settings);


  //      scan_document_settings.setOnClickListener(new View.OnClickListener() {

  //          @Override
 //           public void onClick(View v) {


        fr = new FragmentScanSettings();
        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place_scan,fr);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

//            }
//        });



    }
}
