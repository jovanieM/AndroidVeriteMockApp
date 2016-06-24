package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by SQA Cebu on 6/23/2016.
 */
public class WS00_020 extends Activity {

    private boolean isCanceled;
    LinearLayout llipAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_020);

        llipAddress = (LinearLayout) findViewById(R.id.ll_ip_address);

        final ProgressDialog pd = new ProgressDialog(WS00_020.this);
        pd.setMessage("Getting network information...");
        pd.setCancelable(true);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                startActivity(new Intent(WS00_020.this, WS00_000.class));
                pd.dismiss();
                isCanceled = true;
                finish();
            }
        });
        pd.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                llipAddress.setVisibility(View.INVISIBLE);
            }
        }, 4000);

    }
}
