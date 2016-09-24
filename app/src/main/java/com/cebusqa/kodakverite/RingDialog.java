package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.ContextThemeWrapper;
import android.view.View;

/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class RingDialog implements Runnable {
    ProgressDialog ringDialog;
    String string, string2;
    boolean bool;
    Context context;

    public RingDialog(Context context, String string, String string2, boolean bool) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB){
            ringDialog = new ProgressDialog(context);
        }else{
            ringDialog = new ProgressDialog(new ContextThemeWrapper(context, android.R.style.Theme_Holo_Light));
        }
        ringDialog = ProgressDialog.show(context, string, string2, bool);

        this.string = string;
        this.string2 = string2;
        this.bool = bool;
        this.context = context;
    }

    //public void a(){
    @Override
    public void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ringDialog.dismiss();
            }
        }).start();
    }

}
