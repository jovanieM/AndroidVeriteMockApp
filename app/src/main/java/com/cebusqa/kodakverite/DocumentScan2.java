package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Cebu SQA on 24/06/2016.
 */
public class DocumentScan2 extends Activity {

    Button back;
    RelativeLayout scan, crop, send, save2,email2, drive2,skyDrive2;
    ImageView iv2;
    Context context;
    ImageButton settings2;
    Bitmap bm2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_scan_2);

        this.back = (Button)findViewById(R.id.back);
        this.scan = (RelativeLayout) findViewById(R.id.scan2);
        this.crop = (RelativeLayout) findViewById(R.id.crop2);
        this.send = (RelativeLayout) findViewById(R.id.send2);
        this.save2 = (RelativeLayout) findViewById(R.id.save2);
        this.email2 = (RelativeLayout) findViewById(R.id.email2);
        this.drive2 = (RelativeLayout) findViewById(R.id.drive2);
        this.skyDrive2 = (RelativeLayout) findViewById(R.id.one_box2);
        settings2 = (ImageButton) findViewById(R.id.dscanSettingsIcon);
        iv2 = (ImageView) findViewById(R.id.doc_image);
        context = getApplicationContext();
        //test2 = new PhotoScanMain().test;
        Resources res = getResources();
        email2.bringToFront();
        save2.bringToFront();

        bm2 = BitmapFactory.decodeResource(res, R.drawable.sample);
        iv2.setImageBitmap(bm2);

    }
}
