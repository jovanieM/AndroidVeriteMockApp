package  com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.ByteArrayOutputStream;

/**
 * Created by Cebu SQA on 10/06/2016.
 */
public class ImageCropper extends Activity implements View.OnClickListener{

    MyImageView mv;
    Button ok, cancel;

    Bundle extras1;
    Bitmap crop;
    KodakVeriteApp kodak;
    //MyImageView mv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_crop);

        Log.v("onCreate","start");

        //mv2 = new MyImageView(this);

        extras1 = getIntent().getExtras();

        ok = (Button) findViewById(R.id.button);
        cancel = (Button) findViewById(R.id.button2);
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
        kodak = new KodakVeriteApp();

        //bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sample);
        mv = (MyImageView) findViewById(R.id.my_view);

        Log.v("mv left", String.valueOf(mv.getLeft()));
        Log.v("mv top", String.valueOf(mv.getTop()));
        Log.v("mv right", String.valueOf(mv.getRight()));
        Log.v("mv bottom", String.valueOf(mv.getBottom()));

        int left1, top1, right1, bottom1;
        left1 = extras1.getInt("left");
        top1 = extras1.getInt("top");
        right1 = extras1.getInt("right");
        bottom1 = extras1.getInt("bottom");

        byte[] byteArray = kodak.getBitmapData();

        crop = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        mv.setL(0);
        mv.setT(0);
        mv.setR(right1 - left1);
        mv.setB(bottom1 - top1);

//        if(width > height){
//            double viewAR = (double) height / width;
//            mv.getLayoutParams().width = width;
//            mv.getLayoutParams().height = (int) (width * viewAR);
//
//        }else{
//            double viewAR2 = (double) width /height;
//            if(viewAR2 < 0.7132) {
//                mv.getLayoutParams().height = height;
//                mv.getLayoutParams().width = (int) (height * viewAR2);
//            }
//            else{
//                double temp = (double) height / width;
//                mv.getLayoutParams().width = width;
//                mv.getLayoutParams().height = (int) (width * temp);
//
//            }
//        }
        mv.setImageBitmap(crop);



        Log.v("mv dimension", String.valueOf(mv.getWidth()));
        Log.v("mv dimension", String.valueOf(mv.getHeight()));



        /*if( width > height && viewAR > 0.7132){
            mv.getLayoutParams().width = View.MeasureSpec.EXACTLY;
            mv.getLayoutParams().height = View.MeasureSpec.AT_MOST;
            mv.setImageBitmap(bitmap);
            Log.v("test AR", "true");
        }else{
//            mv.getLayoutParams().width = 360;
//            mv.getLayoutParams().height = 600;
            mv.setImageBitmap(bitmap);
            Log.v("test AR", "false");
        }*/

//        cv.rect = new Rect(cv.x,cv.y,cv.right,cv.bottom);
//        le = cv.newX;
//        ri = cv.newY;
        Log.v("onCreate","end");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("onStart","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("onResume","onResume");
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

        if(v.getId() == R.id.button){
            int left = mv.x;
            int top = mv.y;
            int right = mv.right;
            int bottom = mv.bottom;

            if(left > 0 && left < mv.getWidth()){
                double ratio = (double)left / mv.getWidth();
                newLeft = (int) (crop.getWidth() * ratio);
            }else{
                newLeft = 0;
            }

            if(top > 0 && top < mv.getHeight()){
                double ratio = (double) top / mv.getHeight();
                newTop = (int) (crop.getHeight() * ratio);
            }else{
                newTop = 0;
            }

            if (right < mv.getWidth() && right > 0 ){
                double ratio = (double) right / mv.getWidth();
                newRight = (int) (crop.getWidth() * ratio);
            }else{
                newRight =crop.getWidth();
            }

            if (bottom < mv.getHeight() && bottom > 0 ){
                double ratio = (double) bottom / mv.getHeight();
                newBottom = (int) (crop.getHeight() * ratio);
            }else{
                newBottom = crop.getHeight();
            }
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] byteArray = stream.toByteArray();


            Log.v("mv dimension", String.valueOf(mv.getWidth()));
            Log.v("mv dimension", String.valueOf(mv.getHeight()));
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

        if (v.getId() == R.id.button2){
//            Intent intent = new Intent();
//            setResult(1,intent);

            finish();
        }
    }

    @Override
    protected void onDestroy() {
        Log.v("ImageCropper", "after finish/ onDestroy");
        super.onDestroy();
    }
}
