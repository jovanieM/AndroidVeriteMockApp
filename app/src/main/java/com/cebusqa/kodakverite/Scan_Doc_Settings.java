package com.cebusqa.kodakverite;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Arrays;


public class Scan_Doc_Settings extends AppCompatActivity {


    public Button back;
    public Spinner spinner_quality, spinner_document, spinner_type;

    Resources res ;
    String[] document, quality, color, saveAsType;
    KodakVeriteApp kodakVeriteApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_doc_settings);

        res = getResources();
        quality = res.getStringArray(R.array.Quality_scan);
        color = res.getStringArray(R.array.Color_scan);
        document = res.getStringArray(R.array.Quality_scan);
        saveAsType = res.getStringArray(R.array.Document_scan);
        kodakVeriteApp  = new KodakVeriteApp();

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
                            }
        });


        Spinner spinner_quality = (Spinner) findViewById(R.id.spinner_quality);
        Spinner spinner_color = (Spinner) findViewById(R.id.spinner_color);
        Spinner spinner_document = (Spinner) findViewById(R.id.spinner_document);
        Spinner spinner_type = (Spinner) findViewById(R.id.spinner_type);


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

    }


}
