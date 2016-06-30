package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.transition.TransitionManager;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
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
    private GestureDetectorCompat mDetector;
    boolean moving = false;
    float prevY;
    float y = 0.0f;
    float displayYcoor;
    boolean threshold;
    float last;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flick_print);
        view = findViewById(R.id.kodakToolbar);
        view.bringToFront();



        imDisplay = (ImageView) findViewById(R.id.image_displayer);
        //bitmap = BitmapFactory.decodeFile(fullImage);

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
        imDisplay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDetector.onTouchEvent(event);
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        moving = true;
                        prevY = event.getRawY();
                        displayYcoor = imDisplay.getY();

                        //Toast.makeText(getApplicationContext(), String.valueOf(event.getRawY()), Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (moving){
                            y = event.getRawY()- prevY;

                            imDisplay.setY(displayYcoor+y);
                            last = imDisplay.getY();

                        }
                        break;
                    case MotionEvent.ACTION_UP:

                        if(prevY-event.getRawY()>200){
                            imDisplay.setY(0-imDisplay.getHeight());
                            startActivity(new Intent(getApplication(), PhotoScanMain.class));
                            finish();
                        }else{
                            imDisplay.setY(displayYcoor);
                        }


                           // height = imDisplay.getY();
                           // Toast.makeText(getApplicationContext(), String.valueOf(height), Toast.LENGTH_SHORT).show();
//                        if(threshold){
//                            startActivity(new Intent(getApplication(), PhotoScanMain.class));
//                        }else{
//
//
//                        }


                        break;
                }
                return true;
            }
        });
        ImageLoader.getInstance().displayImage("file:///"+fullImage, imDisplay, new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new SimpleBitmapDisplayer())
                .build());



    }


    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getY() - e2.getY() > 100 && Math.abs(velocityY) > 50)
            {
                threshold = true;
                //Toast.makeText(getApplicationContext(), "You have swipped top side", Toast.LENGTH_SHORT).show();
              //  startActivity(new Intent(getApplication(), PhotoScanMain.class));
           /* Code that you want to do on swiping left side*/
                return true;
            }
            else if (e2.getY() - e1.getY() > 50 && Math.abs(velocityY) > 50)
            {
                Toast.makeText(getApplicationContext(), "You have swipped right side", Toast.LENGTH_SHORT).show();
          /* Code that you want to do on swiping right side*/
                return false;
            }
            return true;


        }
    }


}
