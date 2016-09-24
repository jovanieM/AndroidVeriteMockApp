package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by jmolas on 22/09/2016.
 */

public class ImageCropper2 extends Activity implements View.OnClickListener {
    MyImageView mv2;
    Button okDocScan, cancelDocScan;

    Bundle extras1;
    Bitmap crop;
    KodakVeriteApp kodak2;
    DocumentScan2 documentScan2;
    //MyImageView mv2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_cropper_2);

        Log.v("onCreate","start");
        documentScan2  = new DocumentScan2();


        //mv2 = new MyImageView(this);

        extras1 = getIntent().getExtras();

        okDocScan = (Button) findViewById(R.id.ok_doc_scan);
        cancelDocScan = (Button) findViewById(R.id.cancel_doc_scan);
        okDocScan.setOnClickListener(this);
        cancelDocScan.setOnClickListener(this);
        kodak2 = new KodakVeriteApp();

        //bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sample);
        mv2 = (MyImageView) findViewById(R.id.my_view2);

        Log.v("mv left", String.valueOf(mv2.getLeft()));
        Log.v("mv top", String.valueOf(mv2.getTop()));
        Log.v("mv right", String.valueOf(mv2.getRight()));
        Log.v("mv bottom", String.valueOf(mv2.getBottom()));

        int left1, top1, right1, bottom1, key;
        left1 = extras1.getInt("left");
        top1 = extras1.getInt("top");
        right1 = extras1.getInt("right");
        bottom1 = extras1.getInt("bottom");
        key = extras1.getInt("key");

        //byte[] byteArray = kodak2.getBitmapData();

        crop = documentScan2.getBitmapFromMemoryCache(String.valueOf(key));

        mv2.setL(0);
        mv2.setT(0);
        mv2.setR(right1 - left1);
        mv2.setB(bottom1 - top1);

        mv2.setImageBitmap(crop);

        Log.v("mv dimension", String.valueOf(mv2.getWidth()));
        Log.v("mv dimension", String.valueOf(mv2.getHeight()));
    }



    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Log.v("onClick_IC","starts");
        int newLeft, newTop, newRight, newBottom;

        if(v.getId() == R.id.ok_doc_scan){
            int left = mv2.x;
            int top = mv2.y;
            int right = mv2.right;
            int bottom = mv2.bottom;

            if(left > 0 && left < mv2.getWidth()){
                double ratio = (double)left / mv2.getWidth();
                newLeft = (int) (crop.getWidth() * ratio);
            }else{
                newLeft = 0;
            }

            if(top > 0 && top < mv2.getHeight()){
                double ratio = (double) top / mv2.getHeight();
                newTop = (int) (crop.getHeight() * ratio);
            }else{
                newTop = 0;
            }

            if (right < mv2.getWidth() && right > 0 ){
                double ratio = (double) right / mv2.getWidth();
                newRight = (int) (crop.getWidth() * ratio);
            }else{
                newRight =crop.getWidth();
            }

            if (bottom < mv2.getHeight() && bottom > 0 ){
                double ratio = (double) bottom / mv2.getHeight();
                newBottom = (int) (crop.getHeight() * ratio);
            }else{
                newBottom = crop.getHeight();
            }
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] byteArray = stream.toByteArray();


            Log.v("mv dimension", String.valueOf(mv2.getWidth()));
            Log.v("mv dimension", String.valueOf(mv2.getHeight()));
            Log.v("mv dimension", String.valueOf(crop.getWidth()));
            Log.v("mv dimension", String.valueOf(crop.getHeight()));
            Log.v("mv dimension", String.valueOf(newLeft));
            Log.v("mv dimension", String.valueOf(newTop));
            Log.v("mv dimension", String.valueOf(newRight));
            Log.v("mv dimension", String.valueOf(newBottom));

            Log.v("mv dimension", String.valueOf(left));
            Log.v("mv dimension", String.valueOf(top));
            Log.v("mv dimension", String.valueOf(right));
            Log.v("mv dimension", String.valueOf(bottom));

            Intent intent = new Intent();
            intent.putExtra("left", newLeft);
            intent.putExtra("top", newTop);
            intent.putExtra("right",newRight);
            intent.putExtra("bottom",newBottom);
            //intent.putExtra("bitmap", byteArray);
            setResult(2, intent);
            Log.v("ImageCropper", "before finish");
            finish();
            Log.v("ImageCropper", "after finish");
            Log.v("onClick_IC","ends");
        }

        if (v.getId() == R.id.cancel_doc_scan){
            finish();
        }
    }

}
