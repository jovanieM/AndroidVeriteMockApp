package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by Cebu SQA on 27/06/2016.
 */
public class GoogleCloudPrint extends Activity implements View.OnClickListener{
    private RelativeLayout gcp, gcpReg,gcpEnDis;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_cloud_print_main);
        gcp = (RelativeLayout) findViewById(R.id.gcp_status_id);
        gcpReg = (RelativeLayout) findViewById(R.id.gcp_reg_unreg);
        gcpEnDis = (RelativeLayout) findViewById(R.id.gcp_enable_disable);
        back = (Button) findViewById(R.id.back);
        gcp.setOnClickListener(this);
        gcpReg.setOnClickListener(this);
        gcpEnDis.setOnClickListener(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gcp_status_id:
                startActivity(new Intent(getApplicationContext(), GcpStatus.class));
                break;
            case R.id.gcp_reg_unreg:
               startActivity(new Intent(getApplicationContext(), GcpRegisterUnregister.class));
                break;
            case R.id.gcp_enable_disable:
                startActivity(new Intent(getApplicationContext(), GcpEnableDisable.class));
                break;
        }
    }

}
