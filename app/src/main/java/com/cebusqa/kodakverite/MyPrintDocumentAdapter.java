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
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jmolas on 17/08/2016.
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class MyPrintDocumentAdapter extends PrintDocumentAdapter {
    KodakVeriteApp kodakVeriteApp;
    Context context ;
    PrintedPdfDocument mPdfDocument;
    ArrayList<String> uri;
    int mPages;
    Rect mRect;
    Bitmap bitmap;
    PaperSizeComputation paperSizeComputation;

    int lHeight = 792;
    int lWidth = 612;

    MyPrintDocumentAdapter(Context context){
        this.context = context;
        kodakVeriteApp = new KodakVeriteApp();
        uri = kodakVeriteApp.getThumbData();
    }

    @Override
    public void onStart() {
        super.onStart();

        bitmap = null;

        mPages = kodakVeriteApp.getThumbData().size();
        paperSizeComputation = new PaperSizeComputation();

    }

    @Override
    public void onLayout(PrintAttributes oldAttributes,
                         PrintAttributes newAttributes,
                         CancellationSignal cancellationSignal,
                         LayoutResultCallback callback,
                         Bundle extras) {

        mPdfDocument = new PrintedPdfDocument(context, newAttributes);

        if (cancellationSignal.isCanceled()){
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
    public void onWrite(PageRange[] pages,
                        ParcelFileDescriptor destination,
                        CancellationSignal cancellationSignal,
                        WriteResultCallback callback) {

        for (int i = 0; i < mPages; i++){
            PdfDocument.Page page = mPdfDocument.startPage(i);

            if(cancellationSignal.isCanceled()){
                callback.onWriteCancelled();
                mPdfDocument.close();
                mPdfDocument = null;
                return;
            }
            Log.v("my Activity", "loop");
            bitmap = BitmapFactory.decodeFile(uri.get(i));


            mRect = new Rect (36, 36, 400, 300);//paperSizeComputation.getRect(bitmap.getWidth(), bitmap.getHeight(), lWidth, lHeight);

            drawPage(page, bitmap, mRect);

            mPdfDocument.finishPage(page);

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

    }

    private void drawPage(PdfDocument.Page page, Bitmap bitmap, Rect rect) {
        Canvas canvas = page.getCanvas();



        canvas.drawBitmap(bitmap, null, rect, null);
        //Toast.makeText(kodakVeriteApp, String.valueOf(width), Toast.LENGTH_SHORT).show();
        //Log.v("my Activity", String.valueOf(width) + " " +  String.valueOf(height) );

    }

    @Override
    public void onFinish() {
        super.onFinish();
        mPdfDocument =null;
        bitmap = null;
        kodakVeriteApp.clearData();

    }
}
