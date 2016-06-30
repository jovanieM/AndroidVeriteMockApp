package com.cebusqa.kodakverite;

import android.app.Application;

/**
 * Created by Cebu SQA on 27/06/2016.
 */
public class KodakVeriteApp extends Application {
    static int  currentStatusValue;
    static boolean airprintPrvState;


    public static KodakVeriteApp getInstance(){
        airprintPrvState = false;
        currentStatusValue = 0;
        return new KodakVeriteApp();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
