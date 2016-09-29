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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Scan_Doc_Settings extends AppCompatActivity {


    public Button back;
    public Spinner spinner_quality, spinner_document, spinner_type, spinner_color;

    Resources res;
    String[] document, quality, color, saveAsType;
    KodakVeriteApp kodakVeriteApp;

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    TextView textView;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_doc_settings);
        kodakVeriteApp = new KodakVeriteApp();

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        expandableListView = (ExpandableListView) findViewById(R.id.elv_quality);
        expandableListDetail = ExpandableListDatPump.getData();

        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());

        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int prev = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != prev){
                    expandableListView.collapseGroup(prev);
                    prev = groupPosition;
                }
                //Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition) + "List expanded.", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                parent.collapseGroup(groupPosition);
                if(groupPosition == 0) kodakVeriteApp.setScanSettingQuality(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition));
                if(groupPosition == 1) kodakVeriteApp.setScanDocSettingDocument(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition));
                if(groupPosition == 2) kodakVeriteApp.setScanDocSettingSaveAsType(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition));
                if(groupPosition == 3) kodakVeriteApp.setScanSettingColor(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition));
               // tv.setText(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition));
                parent.collapseGroup(groupPosition);
                //Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition) + "->" + expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }
}

        /* res = getResources();
        quality = res.getStringArray(R.array.Quality_scan);
        color = res.getStringArray(R.array.Color_scan);
        document = res.getStringArray(R.array.Document_scan);
        saveAsType = res.getStringArray(R.array.Type_scan);
        kodakVeriteApp  = new KodakVeriteApp();

        spinner_quality = (Spinner) findViewById(R.id.spinner_quality);
        spinner_color = (Spinner) findViewById(R.id.spinner_color);
        spinner_document = (Spinner) findViewById(R.id.spinner_document);
        spinner_type = (Spinner) findViewById(R.id.spinner_type);

        ArrayAdapter<CharSequence> adapter_quality = ArrayAdapter.createFromResource(this, R.array.Quality_scan,R.layout.spinner_item);
        adapter_quality.setDropDownViewResource(R.layout.spinner_dropdown_item);
        assert spinner_quality != null;
        spinner_quality.setAdapter(adapter_quality);

        spinner_quality.setSelection(Arrays.asList(quality).indexOf(kodakVeriteApp.getScanSettingQuality()));

        spinner_quality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0 ; i< quality.length;i++){
                    kodakVeriteApp.setScanSettingQuality(quality[position]);
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

        spinner_color.setSelection(Arrays.asList(color).indexOf(kodakVeriteApp.getScanSettingColor()));

        spinner_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0 ; i<color.length;i++){
                    kodakVeriteApp.setScanSettingColor(color[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter adapter_document = ArrayAdapter.createFromResource(this, R.array.Document_scan,R.layout.spinner_item);
        adapter_document.setDropDownViewResource(R.layout.spinner_dropdown_item);
        assert spinner_document != null;
        spinner_document.setAdapter(adapter_document);

        spinner_document.setSelection(Arrays.asList(document).indexOf(kodakVeriteApp.getScanDocSettingDocument()));

        spinner_document.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0 ; i< document.length;i++){
                    kodakVeriteApp.setScanDocSettingDocument(document[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapter_type = ArrayAdapter.createFromResource(this, R.array.Type_scan,R.layout.spinner_item);
        adapter_type.setDropDownViewResource(R.layout.spinner_dropdown_item);
        assert spinner_type != null;
        spinner_type.setAdapter(adapter_type);

        spinner_type.setSelection(Arrays.asList(saveAsType).indexOf(kodakVeriteApp.getScanDocSettingSaveAsType()));

        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0 ; i<saveAsType.length;i++){
                    kodakVeriteApp.setScanDocSettingSaveAsType(saveAsType[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }*/



