package com.cebusqa.kodakverite;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.pdf.PrintedPdfDocument;

import java.util.ArrayList;

/**
 * Created by jmolas on 25/08/2016.
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class CustomPrintDocumentAdapter extends PrintDocumentAdapter{

    KodakVeriteApp kodakVeriteApp;
    Context context;
    PrintedPdfDocument mPdfDocument;
    ArrayList<String> uri;
    int mPages;
    Rect mRect;
    Bitmap bitmap, bitmap2;
    PaperSizeComputation paperSizeComputation;
    BitmapFactory.Options options;
    PdfDocument.Page page;

    PrintAttributes mPrintAttributes;
    PrintAttributes.Margins margins;
    Thread thread;


    CustomPrintDocumentAdapter(Context context) {
        this.context = context;
        kodakVeriteApp = new KodakVeriteApp();
        uri = kodakVeriteApp.getThumbData();
        mPrintAttributes = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPages = kodakVeriteApp.getThumbData().size();
    }

    @Override
    public void onLayout(PrintAttributes oldAttributes,
                         PrintAttributes newAttributes,
                         CancellationSignal cancellationSignal,
                         LayoutResultCallback callback,
                         Bundle extras) {

        mPdfDocument = new PrintedPdfDocument(context, newAttributes);



    }

    @Override
    public void onWrite(PageRange[] pages,
                        ParcelFileDescriptor destination,
                        CancellationSignal cancellationSignal,
                        WriteResultCallback callback) {

    }
}
