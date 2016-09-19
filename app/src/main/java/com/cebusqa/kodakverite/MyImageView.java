package com.cebusqa.kodakverite;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by jmolas on 13/09/2016.
 */
public class MyImageView extends ImageView {

    boolean mAdjustViewBounds;
    static int l, t, r, b;

    int x, y, right,bottom;

    int upLX,upLY;

    Drawable drawable;

    int prevX,prevY,prevR, prevB;
    //int deltaX, deltaY, deltaR, deltaB;
    int newX, newY, newR, newB;

    Paint paint;
    Path path;
    PathEffect pe;
    boolean pressed;
    Resources resources;
    Bitmap bitmap;
    Rect rect, rbl,rtl,rtr,rbr;
    Bitmap bl,tl,tr,br;
    int mWidth, mHeight;


    public MyImageView(Context context) {
        super(context);

        init(null, 0);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }


    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs,defStyleAttr);
    }
    public int getB() {
        return b;
    }

    public void setB(int bb) {
        b = bb;
    }

    public int getR() {
        return r;
    }

    public void setR(int rr) {
        r = rr;
    }

    public int getT() {
        return t;
    }

    public void setT(int tt) {
        t = tt;
    }

    public int getL() {
        return l;
    }

    public void setL(int ll) {
        l = ll;
    }


    private void init(AttributeSet attrs, int defStyleAttr) {

        rect = new Rect();
        //rtl = new Rect(upLX,upLY,60,60);


        Log.v("init", String.valueOf(getT()));
        tl= BitmapFactory.decodeResource(getResources(), R.drawable.scale_tl);
        tr= BitmapFactory.decodeResource(getResources(), R.drawable.scale_ur);
        bl = BitmapFactory.decodeResource(getResources(), R.drawable.scale_bl);
        br= BitmapFactory.decodeResource(getResources(), R.drawable.scale_br);

        pressed = false;
        path = new Path();
        paint = new Paint();
        pe = new DashPathEffect(new float[] {10, 10, 10, 10}, 5);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.TRANSPARENT);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        paint.getStrokeJoin();
        paint.setPathEffect(pe);

        Log.v("init", "init" );

    }


    @Override
    public void setAdjustViewBounds(boolean adjustViewBounds) {

        mAdjustViewBounds = adjustViewBounds;

        super.setAdjustViewBounds(adjustViewBounds);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.v("MyView","onMeasure method");
        drawable = getDrawable();
        //mDrawableBounds = drawable.getBounds();
        int mDrawableWidth = drawable.getIntrinsicWidth();
        int mDrawableHeight = drawable.getIntrinsicHeight();
        int mWidth = MeasureSpec.getSize(widthMeasureSpec);
        int mHeight = MeasureSpec.getSize(heightMeasureSpec);

        float containerRatio = (float)mWidth / mHeight;
        float drawableRatio = (float)mDrawableWidth / mDrawableHeight;

        if(drawableRatio > containerRatio && mDrawableWidth < mDrawableHeight){
            //fix the width and adjust the height if image is portrait
            float ratio = (float)mDrawableHeight / mDrawableWidth;
            int newHeight = (int) (mWidth * ratio);
            Log.v("1st newWidth", String.valueOf(newHeight));
            setMeasuredDimension(mWidth, newHeight);
        }else if(drawableRatio < containerRatio && mDrawableWidth < mDrawableHeight){
            // fix height and adjust width if image is portrait
            int newWidth = (int)((double) mHeight * drawableRatio);
            Log.v("2nd newWidth", String.valueOf(newWidth));
            setMeasuredDimension(newWidth, mHeight);
        }else{
            //fix the width if image is landscape
            float ratio = (float)mDrawableHeight / mDrawableWidth;
            int newHeight = (int) (mWidth * ratio);
            Log.v("3rd newWidth", String.valueOf(ratio));
            setMeasuredDimension(mWidth, newHeight);
        }
        Log.v("onMeasure",String.valueOf(mDrawableWidth)+", "+ String.valueOf(mDrawableHeight)+", "+String.valueOf(mWidth)
                + ", "+ String.valueOf(mHeight)+", "+ String.valueOf(containerRatio)+ ", "+String.valueOf(drawableRatio));


        Log.v("onMeasure", "init" );
    }

    /*@Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            Drawable mDrawable = getDrawable();

            if(mDrawable == null){
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                return;
            }

            if(mAdjustViewBounds){
                int mDrawableWidth = mDrawable.getIntrinsicWidth();
                int mDrawableHeight = mDrawable.getIntrinsicHeight();
                int heightSize = MeasureSpec.getSize(heightMeasureSpec);
                int widthSize = MeasureSpec.getSize(widthMeasureSpec);
                int heightMode = MeasureSpec.getMode(heightMeasureSpec);
                int widthMode = MeasureSpec.getMode(widthMeasureSpec);

                if(heightMode == MeasureSpec.EXACTLY && widthMode != MeasureSpec.EXACTLY){
                    // fixed height and adjustable width
                    int height = heightSize;
                    int width = height * mDrawableWidth / mDrawableHeight;
                    setMeasuredDimension(width, height);
                }else if( widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY){
                    // fixed width and adjustable height
                    int width = widthSize;
                    int height = width * mDrawableHeight / mDrawableWidth;
                    setMeasuredDimension(width, height);
                }else {
                    setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
                }

            }else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }
    */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if(!pressed){
            x = getL();
            y = getT();
            right = getR();
            bottom = getB();
        }



        canvas.drawBitmap(tl, x,y, null);
        canvas.drawBitmap(tr, right-tl.getWidth(),y, null);
        canvas.drawBitmap(bl, x,bottom-tl.getWidth(), null);
        canvas.drawBitmap(br, right-tl.getWidth(),bottom-tl.getWidth(), null);



        //Rect rect = new Rect();
        // rect.set(x,y, (getWidth()-10),(getHeight())/2);

        //path.addRect(getLeft(),getTop(),getRight(),getBottom(), Path.Direction.CW);

        //canvas.drawPath(path, paint);
        Log.v("onDraw", String.valueOf(tl.getWidth()));
        //canvas.drawBitmap(bitmap, null, rect, null);




        canvas.drawRect(x, y, right, bottom, paint);
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


        int X = (int) event.getX();
        int Y = (int) event.getY();


        switch (eventAction){
            case MotionEvent.ACTION_DOWN:
                pressed = true;
                dimenStore(x,y,right,bottom);


                break;
            case MotionEvent.ACTION_MOVE:
                // rezise using the top left corner of the rect
                if(((X > (x-tl.getWidth()) ) && (X < (x+tl.getWidth()))) && (( Y > (y-tl.getWidth()) ) && ( Y < (y+tl.getWidth()))) ){
                    if (X < (right - tl.getWidth()) && Y <(bottom - 45)){
                        x= X;
                        y = Y;
                        requestLayout();
                        invalidate();
                        //Toast.makeText(getContext(),String.valueOf(X)+"/"+String.valueOf(Y), Toast.LENGTH_SHORT).show();
                        Log.v("top left", String.valueOf(X));
                        Log.v("top left", String.valueOf(Y));
                        Log.v("prevX", String.valueOf(prevX));
                    }
                }
                // rezise using the top right corner of the rect
                if(((X > (right - tl.getWidth()) ) && (X < (right + tl.getWidth()))) && (( Y > (y - tl.getWidth()) ) && ( Y < (y + tl.getWidth()))) ){

                    if (X > (x + 45) && Y < (bottom - 45)){
                        right= X;
                        y = Y;
                        requestLayout();
                        invalidate();
                    }
                }
                // rezise using the lower left corner of the rect
                if(((X > (x - tl.getWidth()) ) && (X < (x + tl.getWidth()))) && (( Y > (bottom - tl.getWidth()) ) && ( Y < (bottom + tl.getWidth()))) ){

                    if (X < (right - 45) && Y > (y + 45)){
                        x= X;
                        bottom = Y;
                        requestLayout();
                        invalidate();
                    }
                }
                // rezise using the lower right corner of the rect
                if(((X > (right - tl.getWidth()) ) && (X < (right + tl.getWidth()))) && (( Y > (bottom - tl.getWidth()) ) && ( Y < (bottom + tl.getWidth()))) ){

                    if (X > (x + 45)&& Y > (y + 45)){
                        right= X;
                        bottom = Y;
                        requestLayout();
                        invalidate();
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                pressed = false;


                newX = x;
                newY = y;
                newR = right;
                newB = bottom;


                break;


        }
        return true;

    }
}
