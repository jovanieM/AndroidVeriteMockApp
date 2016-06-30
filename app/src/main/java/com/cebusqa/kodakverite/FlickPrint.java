package com.cebusqa.kodakverite;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/**
 * Created by Cebu SQA on 29/06/2016.
 */
public class FlickPrint extends Activity{

    ImageView imDisplay;
    static String fullImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flick_print);


        imDisplay = (ImageView) findViewById(R.id.image_displayer);
        //bitmap = BitmapFactory.decodeFile(fullImage);




        ImageLoader.getInstance().displayImage("file:///"+fullImage, imDisplay, new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new SimpleBitmapDisplayer())
                .build());


    }

}
