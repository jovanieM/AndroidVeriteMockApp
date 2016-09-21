package com.cebusqa.kodakverite;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by jmolas on 16/09/2016.
 */
public class CustomImgView extends ImageView {

    Drawable drawable;

    public CustomImgView(Context context) {
        super(context);
    }

    public CustomImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec,heightMeasureSpec);


        Log.v("MyView","onMeasure method");
        drawable = getDrawable();

        if (drawable == null){
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
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
}
