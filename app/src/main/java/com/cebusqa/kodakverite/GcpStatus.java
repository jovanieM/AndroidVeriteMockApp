package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Cebu SQA on 27/06/2016.
 */
public class GcpStatus extends Activity {
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gcp_status);
        back = (Button) findViewById(R.id.back);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final DialogFragment gcpStatusLoadingDialog = GcpStatusLoadingDialog.newInstance("GCP Status Loading...");
        gcpStatusLoadingDialog.show(getFragmentManager(),"my tag");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }gcpStatusLoadingDialog.dismiss();

            }
        }).start();


    }
}
