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
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/**
 * Created by Cebu SQA on 29/06/2016.
 */
public class FlickPrint extends Activity implements CompoundButton.OnCheckedChangeListener {

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
    ImageView flickImage;
    ToggleButton toggleButton;
    TextView tv, flickMessage;
    RelativeLayout relativeLayout;
    ImageView settingFlick;
    Button backIcon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flick_print);
        view = findViewById(R.id.kodakToolbar);
        view.bringToFront();
        flickImage = (ImageView) findViewById(R.id.flick_image);
        backIcon = (Button) findViewById(R.id.back);
        toggleButton = (ToggleButton) findViewById(R.id.check_image);
        tv = (TextView) findViewById(R.id.flick_text);
        flickMessage = (TextView) findViewById(R.id.flick_message);
        settingFlick = (ImageView) findViewById(R.id.scanSettingsIcon);
        imDisplay = (ImageView) findViewById(R.id.image_displayer);
        flickImage.bringToFront();
        toggleButton.bringToFront();
        tv.bringToFront();
        toggleButton.setChecked(true);
        toggleButton.setOnCheckedChangeListener(this);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //bitmap = BitmapFactory.decodeFile(fullImage);
        settingFlick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DS_print.class));
            }
        });

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
                            startActivity(new Intent(getApplication(), FlickPrintOut.class));
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

    /**
     * Called when the checked state of a compound button has changed.
     *
     * @param buttonView The compound button view whose state has changed.
     * @param isChecked  The new checked state of buttonView.
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            flickImage.setVisibility(View.VISIBLE);
            tv.setVisibility(View.VISIBLE);
            flickMessage.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.VISIBLE);
        }else{
            flickImage.setVisibility(View.GONE);
            tv.setVisibility(View.GONE);
            flickMessage.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.GONE);
        }
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
