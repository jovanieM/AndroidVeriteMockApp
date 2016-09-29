package com.cebusqa.kodakverite;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Scan_Photo_Settings extends AppCompatActivity {


    public Button back;

    String[] color;
    KodakVeriteApp kodakVeriteApp;

    String[] title;
    String[][] children;

    ExpandableListView expandableListViewPhoto;
    ExpandableListAdapter expandablePhotoAdapter;
//    List<String> expandablePhotoTitle;
//    HashMap<String, List<String>> expandablePhotoDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_photo_settings);
        kodakVeriteApp = new KodakVeriteApp();

        title= new String[]{"Quality", "Color", "Document"};
        children = new String[][]{
                getResources().getStringArray(R.array.Quality_scan),
                getResources().getStringArray(R.array.Color_scan),
                getResources().getStringArray(R.array.Photo_scan)};


        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

        expandableListViewPhoto = (ExpandableListView) findViewById(R.id.elv_photo);
//        expandablePhotoDetail = ExpandablePhotoData.getData();
//        expandablePhotoTitle = new ArrayList<>(expandablePhotoDetail.keySet());
        expandablePhotoAdapter = new CustomExpandablePhotoAdapter(this, title, children);
        expandableListViewPhoto.setAdapter(expandablePhotoAdapter);
        expandableListViewPhoto.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int prev_item = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != prev_item){
                    expandableListViewPhoto.collapseGroup(prev_item);
                    prev_item = groupPosition;
                }
            }
        });

        expandableListViewPhoto.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                parent.collapseGroup(groupPosition);
                if(groupPosition == 0) kodakVeriteApp.setScanPhotoSettingQuality(children[groupPosition][childPosition]);

                if(groupPosition == 1) kodakVeriteApp.setScanPhotoSettingColor(children[groupPosition][childPosition]);
                if(groupPosition == 2) kodakVeriteApp.setScanPhotoSettingDocument(children[groupPosition][childPosition]);
               // if(groupPosition == 3) kodakVeriteApp.setScanSettingColor(expandablePhotoDetail.get(expandablePhotoTitle.get(groupPosition)).get(childPosition));
                // tv.setText(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition));
                parent.collapseGroup(groupPosition);

                return false;
            }
        });



        /* res = getResources();
        document = res.getStringArray(R.array.Photo_scan);
        quality1 = res.getStringArray(R.array.Quality_scan);
        color = res.getStringArray(R.array.Color_scan);
        kodakVeriteApp = new KodakVeriteApp();

        back = (Button)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });


        spinner_quality = (Spinner) findViewById(R.id.spinner_quality);
        spinner_color = (Spinner) findViewById(R.id.spinner_color);
        spinner_photo = (Spinner) findViewById(R.id.spinner_photo);

        ArrayAdapter<CharSequence> adapter_quality = ArrayAdapter.createFromResource(this, R.array.Quality_scan,R.layout.spinner_item);
        adapter_quality.setDropDownViewResource(R.layout.spinner_dropdown_item);
        assert spinner_quality != null;
        spinner_quality.setAdapter(adapter_quality);



        spinner_quality.setSelection(Arrays.asList(quality1).indexOf(kodakVeriteApp.getScanPhotoSettingQuality()));


        spinner_quality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0 ; i< quality1.length;i++){
                    kodakVeriteApp.setScanPhotoSettingQuality(quality1[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter_color = ArrayAdapter.createFromResource(this, R.array.Color_scan,R.layout.spinner_item);
        adapter_color.setDropDownViewResource(R.layout.spinner_dropdown_item);
        assert spinner_color != null;
        spinner_color.setAdapter(adapter_color);

        spinner_color.setSelection(Arrays.asList(color).indexOf(kodakVeriteApp.getScanPhotoSettingColor()));

        spinner_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0 ; i< color.length;i++){
                    kodakVeriteApp.setScanPhotoSettingColor(color[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapter_document = ArrayAdapter.createFromResource(this, R.array.Photo_scan,R.layout.spinner_item);
        adapter_document.setDropDownViewResource(R.layout.spinner_dropdown_item);
        assert spinner_photo != null;
        spinner_photo.setAdapter(adapter_document);

        spinner_photo.setSelection(Arrays.asList(document).indexOf(kodakVeriteApp.getScanPhotoSettingDocument()));

        spinner_photo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0 ; i< document.length;i++){
                    kodakVeriteApp.setScanPhotoSettingDocument(document[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

    }
}
