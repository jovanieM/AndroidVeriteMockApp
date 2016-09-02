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


public class Scan_Photo_Settings extends AppCompatActivity {


    public Button back;
    public Spinner spinner_quality, spinner_color, spinner_photo;
    Resources res ;
    String[] document, quality1, color;
    KodakVeriteApp kodakVeriteApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_photo_settings);
        kodakVeriteApp = new KodakVeriteApp();

        res = getResources();
        document = res.getStringArray(R.array.Photo_scan);
        quality1 = res.getStringArray(R.array.Quality_scan);
        color = res.getStringArray(R.array.Color_scan);

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
        });

    }
}
