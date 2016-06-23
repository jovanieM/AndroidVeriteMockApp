package com.cebusqa.kodakverite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class Scan_Photo_Settings extends AppCompatActivity {


    public Button back;
    public Spinner spinner_quality, spinner_color, spinner_photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_photo_settings);

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
                            }
        });


        Spinner spinner_quality = (Spinner) findViewById(R.id.spinner_quality);
        Spinner spinner_color = (Spinner) findViewById(R.id.spinner_color);
        Spinner spinner_photo = (Spinner) findViewById(R.id.spinner_photo);

        ArrayAdapter<CharSequence> adapter_quality = ArrayAdapter.createFromResource(this, R.array.Quality_scan,R.layout.spinner_item);
        adapter_quality.setDropDownViewResource(R.layout.spinner_dropdown_item);
        assert spinner_quality != null;
        spinner_quality.setAdapter(adapter_quality);


        ArrayAdapter<CharSequence> adapter_color = ArrayAdapter.createFromResource(this, R.array.Color_scan,R.layout.spinner_item);
        adapter_color.setDropDownViewResource(R.layout.spinner_dropdown_item);
        assert spinner_color != null;
        spinner_color.setAdapter(adapter_color);


        ArrayAdapter adapter_photo = ArrayAdapter.createFromResource(this, R.array.Photo_scan,R.layout.spinner_item);
        adapter_photo.setDropDownViewResource(R.layout.spinner_dropdown_item);
        assert spinner_photo != null;
        spinner_photo.setAdapter(adapter_photo);



    }
}
