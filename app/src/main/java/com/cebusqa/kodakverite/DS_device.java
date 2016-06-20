package com.cebusqa.kodakverite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DS_device extends AppCompatActivity {

    Button back;
    ImageButton network_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_device);

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DS_device.this, DS10_000.class));
            }
        });


        network_status = (ImageButton)findViewById(R.id.network_status);
        network_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DS_device.this, NetworkStatus_details.class));
            }
        });

    }
}
