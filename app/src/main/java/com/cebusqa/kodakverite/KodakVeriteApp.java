package com.cebusqa.kodakverite;

import android.app.Application;

/**
 * Created by Cebu SQA on 27/06/2016.
 */
public class KodakVeriteApp extends Application {
    boolean gcpStatusToggler = false;


    public static KodakVeriteApp getInstance(){
        return new KodakVeriteApp();
    }



}
