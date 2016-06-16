package  com.cebusqa.kodakverite;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Cebu SQA on 14/06/2016.
 */
public class CropView extends View {

    int x, y, right,bottom;

    int prevX,prevY,prevR, prevB;
    //int deltaX, deltaY, deltaR, deltaB;
    int newX, newY, newR, newB;

    Paint paint;
    Path path;
    PathEffect pe;
    //Rect rect;
    boolean pressed;
    Resources resources;
    Bitmap bitmap;
    Rect rect;
    Bitmap mBitmap;



    public CropView(Context context) {
        super(context);

        init(null, 0);
    }

    public CropView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }


    public CropView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs,defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {

        //Rect rect = new Rect(0,0,400,600);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sample);

        pressed = false;
        path = new Path();
        paint = new Paint();
        pe = new DashPathEffect(new float[] {10, 5, 5, 5}, 5);

        paint.setStyle(Paint.Style.STROKE);
        //paint.setColor(Color.TRANSPARENT);
        paint.setAntiAlias(true);
        paint.getStrokeJoin();
        paint.setPathEffect(pe);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Rect rect = new Rect();
        // rect.set(x,y, (getWidth()-10),(getHeight())/2);

        //path.addRect(getLeft(),getTop(),getRight(),getBottom(), Path.Direction.CW);

        //canvas.drawPath(path, paint);

        canvas.drawBitmap(bitmap, null, rect, null);
        canvas.drawRect(x,y,right,bottom, paint);
       // Toast.makeText(getContext(), String.valueOf(x)+"/"+String.valueOf(y), Toast.LENGTH_LONG).show();

    }

    public void dimenStore(int x, int y, int right, int bottom){
        if (prevX == 0) {
            prevX = x;
        }
        if(prevY==0){
            prevY = y;
        }
        if(prevR==0){
            prevR = right;
        }
        if(prevB==0){
            prevB = bottom;
        }
        //Toast.makeText(getContext(),String.valueOf(prevX)+"/"+String.valueOf(prevY)+"/"+String.valueOf(prevR)+"/"+String.valueOf(prevB), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {

        final int eventAction = event.getAction();

        if(pressed)dimenStore(x,y,right,bottom);

        int X = (int) event.getX();
        int Y = (int) event.getY();


        switch (eventAction){
            case MotionEvent.ACTION_DOWN:
                pressed = true;
                Toast.makeText(getContext(),String.valueOf(X)+"/"+String.valueOf(Y), Toast.LENGTH_SHORT).show();
                break;

            case MotionEvent.ACTION_MOVE:
                // rezise using the top left corner of the rect
                if(((X > (x-25) ) && (X < (x+25))) && (( Y > (y-25) ) && ( Y < (y+25))) ){
                    if (X < (right - 45) && Y <(bottom - 45)){
                        newX = X;
                        newY = Y;
                        x= X;
                        y = Y;
                        invalidate();
                    }
                }
                // rezise using the top right corner of the rect
                if(((X > (right-25) ) && (X < (right+25))) && (( Y > (y-25) ) && ( Y < (y+25))) ){

                    if (X > (x + 45) && Y < (bottom - 45)){
                        right= X;
                        y = Y;
                        invalidate();
                    }
                }
                // rezise using the lower left corner of the rect
                if(((X > (x-25) ) && (X < (x+25))) && (( Y > (bottom-25) ) && ( Y < (bottom+25))) ){

                    if (X < (right - 45) && Y > (y + 45)){
                        x= X;
                        bottom = Y;
                        invalidate();
                    }
                }
                // rezise using the lower right corner of the rect
                if(((X > (right-25) ) && (X < (right+25))) && (( Y > (bottom-25) ) && ( Y < (bottom+25))) ){

                    if (X > (x + 45)&& Y > (y + 45)){
                        right= X;
                        bottom = Y;
                        invalidate();
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                pressed = false;


                break;

        }
        return true;

    }
}
