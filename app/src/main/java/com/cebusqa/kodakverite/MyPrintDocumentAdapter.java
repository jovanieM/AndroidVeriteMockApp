package com.cebusqa.kodakverite;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jmolas on 17/08/2016.
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class MyPrintDocumentAdapter extends PrintDocumentAdapter {
    KodakVeriteApp kodakVeriteApp;
    Context context;
    PrintedPdfDocument mPdfDocument;
    ArrayList<String> uri;
    int mPages;
    Rect mRect;
    Bitmap bitmap;
    PaperSizeComputation paperSizeComputation;
    BitmapFactory.Options options;
    PdfDocument.Page page;
    int imageWidth, imageHeight;

    PrintAttributes mPrintAttributes;
    PrintAttributes.Margins margins;


    MyPrintDocumentAdapter(Context context) {
        this.context = context;
        kodakVeriteApp = new KodakVeriteApp();
        uri = kodakVeriteApp.getThumbData();
        mPrintAttributes = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v("my Activity", "onStart");

        bitmap = null;

        mPages = kodakVeriteApp.getThumbData().size();
        paperSizeComputation = new PaperSizeComputation();
        margins = PrintAttributes.Margins.NO_MARGINS;
        //PrintAttributes.Margins printAttributes= PrintAttributes.Margins.NO_MARGINS;


    }

    @Override
    public void onLayout(PrintAttributes oldAttributes,
                         PrintAttributes newAttributes,
                         CancellationSignal cancellationSignal,
                         LayoutResultCallback callback,
                         Bundle extras) {
        Log.v("my Activity", "onLayout");
        Log.v("my Activity", String.valueOf(page));
        Log.v("my Activity", String.valueOf(mPdfDocument));

        mPrintAttributes = newAttributes;


        //extras.putBoolean(EXTRA_PRINT_PREVIEW, true);
        mPdfDocument = new PrintedPdfDocument(context, newAttributes);

        Log.v("my Activity", String.valueOf(page));
        Log.v("my Activity", String.valueOf(mPdfDocument));

        if (cancellationSignal.isCanceled()) {
            callback.onLayoutCancelled();
            return;
        }

        PrintDocumentInfo info = new PrintDocumentInfo
                .Builder("print_output.pdf")
                .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                .setPageCount(mPages)

                .build();

        callback.onLayoutFinished(info, true);

    }

    @Override
    public void onWrite(final PageRange[] pages,
                        final ParcelFileDescriptor destination,
                        CancellationSignal cancellationSignal,
                        final WriteResultCallback callback) {

        Log.v("my Activity", "onWrite");
        Log.v("my Activity", String.valueOf(page));
        Log.v("my Activity", String.valueOf(mPdfDocument));

        mPdfDocument = new PrintedPdfDocument(context, mPrintAttributes);

//        if(cancellationSignal.isCanceled()){
//            callback.onWriteCancelled();
//            mPdfDocument.close();
//            mPdfDocument = null;
//            return;
//        }

        for (int i = 0; i < mPages;){
            bitmap = null;
            mRect = null;

            page = mPdfDocument.startPage(i);

            Log.v("my Activity", "after Task");

            Log.v("my Activity", "loop");

            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            bitmap = BitmapFactory.decodeFile(uri.get(i), options);

            imageWidth = options.outWidth;
            imageHeight = options.outHeight;
           // bitmap = convertBitmap(uri.get(i), options);

            options.inJustDecodeBounds = false;
            options.inSampleSize = 1;
            bitmap = BitmapFactory.decodeFile(uri.get(i), options);

           // bitmap = convertBitmap(uri.get(i), options);




            Log.v("loop number", String.valueOf(i));

            if(mPrintAttributes.getMediaSize().isPortrait()) {

                double paperSizeRatio = ((double) mPrintAttributes.getMediaSize().getWidthMils() / (double) mPrintAttributes.getMediaSize().getHeightMils());


                if (imageWidth > imageHeight) {


                    double sRatio = ((double) imageHeight / imageWidth);
                    //double width = ((double) (mPrintAttributes.getMediaSize().getWidthMils() / 1000)) * 72;
                    double width = ((double)mPrintAttributes.getMediaSize().getWidthMils() / 1000.0) * 72.0;
                    double height =  ((double)mPrintAttributes.getMediaSize().getHeightMils() / 1000.0) * 72.0;
                    int final_width = (int) Math.round(width);
                    int final_height = (int) Math.round(height);

                    double outPutHeight = width * sRatio;
                    int marginHeight = (int) Math.round((height - outPutHeight)/2.0);
                    mRect = new Rect(9, marginHeight + 9, final_width - 9, final_height - (marginHeight +9));
                    Log.v("my Activity", String.valueOf(marginHeight));
                    Log.v("my Activity", String.valueOf(sRatio));
                    Log.v("my Activity", String.valueOf(mPrintAttributes.getMediaSize().getWidthMils()));
                    Log.v("my Activity", String.valueOf(height));
                    Log.v("my Activity", String.valueOf(outPutHeight));

                }
                if (imageWidth < imageHeight) {
                    //double paperRatio = mPrintAttributes.getMediaSize().getWidthMils() / mPrintAttributes.getMediaSize().getHeightMils();
                    double sRatio = ((double) imageWidth / imageHeight);
                    double sRatio2 = ((double) imageHeight / imageWidth);
                    double width = ((double)mPrintAttributes.getMediaSize().getWidthMils() / 1000.0) * 72.0;
                    double height =  ((double)mPrintAttributes.getMediaSize().getHeightMils() / 1000.0) * 72.0;
                    int final_width = (int) Math.round(width);
                    int final_height = (int) Math.round(height);

                    if (sRatio <= paperSizeRatio) {
                        double outPutWidth = height * sRatio;
                        int marginWidth = (int)Math.round((width - outPutWidth) / 2.0);
                        mRect = new Rect(9 + marginWidth, 9, final_width - (marginWidth + 9), final_height - 9);
                        Log.v("my Activity", String.valueOf(mPrintAttributes.getMediaSize().getWidthMils()));

                        Log.v("my Activity", String.valueOf(marginWidth));
                        Log.v("my Activity", String.valueOf(sRatio));
                        Log.v("my Activity", String.valueOf(paperSizeRatio));
                        Log.v("my Activity", String.valueOf(height));
                        Log.v("my Activity", String.valueOf(outPutWidth));
                        Log.v("my Activity", String.valueOf(imageHeight));
                        Log.v("my Activity", String.valueOf(imageWidth));
                    }else{
                        double outPutHeight = width * sRatio2;
                        int marginHeight = (int)Math.round((height - outPutHeight) / 2.0);
                        mRect = new Rect(9, marginHeight + 9, final_width - 9, final_height -(marginHeight+9) );
                    }


                }
            }else{
                double paperSizeRatio = ((double) mPrintAttributes.getMediaSize().getHeightMils() / (double) mPrintAttributes.getMediaSize().getWidthMils());

                if(imageWidth > imageHeight){

                    double sRatio = ((double) imageHeight / imageWidth);
                    double sRatio2 = ((double) imageWidth / imageHeight);
                    double width = ((double)mPrintAttributes.getMediaSize().getWidthMils() / 1000.0) * 72.0;
                    double height =  ((double)mPrintAttributes.getMediaSize().getHeightMils() / 1000.0) * 72.0;
                    int final_width = (int) Math.round(width);
                    int final_height = (int) Math.round(height);

                    if (sRatio <= paperSizeRatio) {
                        double outPutHeight = width * sRatio;
                        int marginHeight = (int)Math.round((height - outPutHeight) / 2.0);
                        mRect = new Rect(9 , marginHeight + 9, final_width - 9, final_height - (marginHeight + 9));
                        Log.v("my Activity", String.valueOf(mPrintAttributes.getMediaSize().getWidthMils()));

                        Log.v("my Activity", String.valueOf(marginHeight));
                        Log.v("my Activity", String.valueOf(sRatio));
                        Log.v("my Activity", String.valueOf(paperSizeRatio));
                        Log.v("my Activity", String.valueOf(height));
                        Log.v("my Activity", String.valueOf(outPutHeight));
                        Log.v("my Activity", String.valueOf(imageHeight));
                        Log.v("my Activity", String.valueOf(imageWidth));
                    }else{
                        double outPutWidth = height * sRatio2;
                        int marginWidth = (int)Math.round((width - outPutWidth) / 2.0);
                        mRect = new Rect(9 + marginWidth, 9, final_width - (marginWidth + 9), final_height -9);
                    }

//                    double sRatio = ((double) imageHeight / imageWidth);
//
//                    int outPutHeight = (int) Math.ceil((double) 792 * sRatio);
//                    int marginHeight = (612 - outPutHeight) / 2;
//                    mRect = new Rect(9, 9 + marginHeight, 792 - 9, 612 - (9+marginHeight));

                }
                if (imageWidth < imageHeight) {
                    double sRatio = ((double) imageWidth / imageHeight);

                    //double sRatio2 = ((double) imageHeight / imageWidth);
                    double width = ((double)mPrintAttributes.getMediaSize().getWidthMils() / 1000.0) * 72.0;
                    double height =  ((double)mPrintAttributes.getMediaSize().getHeightMils() / 1000.0) * 72.0;
                    int final_width = (int) Math.round(width);
                    int final_height = (int) Math.round(height);

                    double outPutWidth = height * sRatio;
                    int marginWidth = (int) Math.round((width - outPutWidth)/2.0);
                    mRect = new Rect(marginWidth + 9, 9, final_width - (marginWidth + 9), final_height -9);

                }

            }
            //options.inTempStorage


            //Toast.makeText(context, String.valueOf(mPrintAttributes.getResolution()), Toast.LENGTH_LONG).show();



            drawPage(page, mRect);
            mPdfDocument.finishPage(page);

            bitmap.recycle();

            i++;

            //bitmap = null;
        }


        try {
            mPdfDocument.writeTo(new FileOutputStream(destination.getFileDescriptor()));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            mPdfDocument.close();
            mPdfDocument = null;
        }

        PageRange[] writtenPages = {PageRange.ALL_PAGES};

        callback.onWriteFinished(writtenPages);
        Log.v("my Activity", "onWrite_bottom");

    }



    void drawPage(PdfDocument.Page page, Rect rect){
        Canvas canvas = page.getCanvas();
        canvas.drawBitmap(bitmap, null, rect, null);
    }

    @Override
    public void onFinish() {

        Log.v("my Activity", "onFinish");
        mPdfDocument = null;
        bitmap = null;
        kodakVeriteApp.clearData();
        super.onFinish();

    }


}
