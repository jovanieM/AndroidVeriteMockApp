package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Cebu SQA on 22/06/2016.
 */
public class PhotoScanMain extends Activity {
    TextView scanTv;
    RelativeLayout scanRelOut;
    Thread thread;
    int MY_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_scan_main);
        scanTv = (TextView) findViewById(R.id.touchScan2);
        scanRelOut = (RelativeLayout) findViewById(R.id.scan);
        scanTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exec();
            }
        });
        exec();

    }
    public void touchScan(View v){

        if (v.getId()==R.id.scan){
            exec();
        }
    }

    public void exec (){

        final ScanPhotoDialog scanDialog = new ScanPhotoDialog();
        scanDialog.setCancelable(true);


        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                scanDialog.show(getFragmentManager(),"scan");
                scanDialog.setTargetFragment(scanDialog,MY_CODE);
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    thread.interrupt();
                }
                if(!isCancelled()){
                    startActivity(new Intent(PhotoScanMain.this, SP_000.class));
                }

            }
        });

        thread.start();

    }


    /**
     * This method will be invoked when the dialog is dismissed.
     *
     * @param dialog The dialog that was dismissed will be passed into the
     *               method.
     */

    public boolean isCancelled(){

        return thread.isInterrupted();
    }
}
