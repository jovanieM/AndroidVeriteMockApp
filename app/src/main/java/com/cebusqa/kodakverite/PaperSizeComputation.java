package com.cebusqa.kodakverite;

import android.content.Loader;
import android.graphics.Rect;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jmolas on 18/08/2016.
 */
public class PaperSizeComputation {

    private int left;
    private int top;
    private int right;
    private int bottom;
    public static final int MARGIN = 36;
    Rect rect;

    PaperSizeComputation(){
        rect = new Rect();
    }






    public Rect getRect(int picWidth, int picHeight, int paperWidth, int paperHeight){

        double resizeRatio = (double) picHeight / picWidth;
        int outPutHeight = (int) Math.ceil((double) paperWidth * resizeRatio);
        int space = (paperHeight - outPutHeight)/2;

        rect = new Rect(MARGIN, space + MARGIN, paperWidth - MARGIN, space - MARGIN);

        Log.v("my class", String.valueOf(space));
        return rect;


    }
}
