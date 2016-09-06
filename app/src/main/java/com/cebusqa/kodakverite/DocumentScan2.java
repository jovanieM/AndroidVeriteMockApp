package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Cebu SQA on 24/06/2016.
 */
public class DocumentScan2 extends Activity {

    private Button mback;
    RelativeLayout scan, crop, send, save2, email2, drive2, skyDrive2;
    ImageView iv2;
    //Context context;
    static final int GET_BITMAP_REQUEST2 = 2;
    ImageButton settings2;
    Bitmap bm2;
    boolean dtest2;
    Intent intent, chooser;
    boolean visible2;
    TextView docQuality, docColor, docDocument, docSaveAsType;
    KodakVeriteApp kodakVeriteApp;
    WebView webView;
    boolean saved;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_scan_2);

        kodakVeriteApp = new KodakVeriteApp();
        saved = false;

        mback = (Button) findViewById(R.id.back);
        this.scan = (RelativeLayout) findViewById(R.id.scan2);
        this.crop = (RelativeLayout) findViewById(R.id.crop2);
        this.send = (RelativeLayout) findViewById(R.id.send2);
        this.save2 = (RelativeLayout) findViewById(R.id.save2);
        this.email2 = (RelativeLayout) findViewById(R.id.email2);
        this.drive2 = (RelativeLayout) findViewById(R.id.drive2);
        this.skyDrive2 = (RelativeLayout) findViewById(R.id.one_box2);
        settings2 = (ImageButton) findViewById(R.id.dscanSettingsIcon);
        iv2 = (ImageView) findViewById(R.id.doc_image);
        webView = (WebView) findViewById(R.id.webView);

        docQuality = (TextView) findViewById(R.id.doc_quality);
        docColor = (TextView) findViewById(R.id.doc_color);
        docDocument = (TextView) findViewById(R.id.doc_type);
        docSaveAsType = (TextView) findViewById(R.id.doc_save_as);

        Resources res = getResources();
        email2.bringToFront();
        save2.bringToFront();
        dtest2 = new DocumentScan().dtest;

        bm2 = BitmapFactory.decodeResource(res, R.drawable.docu);
        iv2.setImageBitmap(bm2);

        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveDocumentDialog.newInstance("Save image to Download").show(getFragmentManager(), "document");

            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dtest2 = false;
                final ScanPhotoDialog2 scanPhotoDialog3 = new ScanPhotoDialog2();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        scanPhotoDialog3.show(getFragmentManager(), "My Progress Dialog");
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (dtest2) {

                            new ScanCanceledAlert().newInstance("Scan Canceled").show(getFragmentManager(), "dialog");

                        }
                        scanPhotoDialog3.dismiss();
                    }
                }).start();


            }

        });

        save2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saved = true;
                SaveAsDialog saveAs = new SaveAsDialog();

                saveAs.show(getFragmentManager(), "My dialog");

            }
        });


        //working code
        email2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri imageUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.docu);
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                chooser = Intent.createChooser(intent, "Send Image");
                startActivity(chooser);
            }
        });
        drive2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri imageUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.docu);
                intent = ShareCompat.IntentBuilder.from(DocumentScan2.this).setType("image/*").getIntent().setPackage("com.google.android.apps.docs");//("com.google.android.apps.cloudprint");//"com.google.android.apps.docs");
                //intent = new Intent(Intent.ACTION_SEND);
                //intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                chooser = Intent.createChooser(intent, "Send Image");
                startActivity(chooser);
            }
        });

        skyDrive2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                webView.setVisibility(View.VISIBLE);
//                String url = "https://login.live.com";
//                webView.getSettings().setJavaScriptEnabled(true);
//                webView.loadUrl(url);

                //          intent = new Intent(Intent.ACTION_SEND);
//                intent.setData(Uri.parse(url));
//                startActivity(intent);

                Uri imageUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.docu);
                intent = ShareCompat.IntentBuilder.from(DocumentScan2.this).setType("image/*").getIntent().setPackage("com.microsoft.live");
//
//                // intent = new Intent(Intent.ACTION_SEND);
//                //intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                chooser = Intent.createChooser(intent, "Send Image");
                startActivity(chooser);
            }
        });

        crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DocumentScan2.this, ImageCropper.class);
                intent.putExtra("width", bm2.getWidth() + 80);
                intent.putExtra("height", bm2.getHeight() + 80);
                Toast.makeText(getApplicationContext(), String.valueOf(bm2.getWidth()), Toast.LENGTH_SHORT).show();
                startActivityForResult(intent, GET_BITMAP_REQUEST2);
                //startActivity(intent);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!visible2) {
                    save2.setVisibility(View.VISIBLE);
                    email2.setVisibility(View.VISIBLE);
                    drive2.setVisibility(View.VISIBLE);
                    skyDrive2.setVisibility(View.VISIBLE);
                    visible2 = true;
                } else {
                    save2.setVisibility(View.GONE);
                    email2.setVisibility(View.GONE);
                    drive2.setVisibility(View.GONE);
                    skyDrive2.setVisibility(View.GONE);
                    visible2 = false;
                }


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            int l = data.getIntExtra("left", 0);
            int t = data.getIntExtra("top", 0);
            int r = data.getIntExtra("right", 300);
            int b = data.getIntExtra("bottom", 500);


            Bitmap mBitmap = Bitmap.createBitmap(bm2, l, t, r, b);
            iv2.setImageBitmap(mBitmap);
        } else {
            Toast.makeText(this, "no change", Toast.LENGTH_SHORT).show();
        }
    }

    public void dsettingsIcon(View v) {
        startActivity(new Intent(DocumentScan2.this, Scan_Doc_Settings.class));
    }

    @Override
    public void onBackPressed() {
        if (!saved) {
            SaveDocumentDialog.newInstance("Save image to Download").show(getFragmentManager(), "document");
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        docQuality.setText(kodakVeriteApp.getScanSettingQuality());
        docColor.setText(kodakVeriteApp.getScanSettingColor());
        docDocument.setText(kodakVeriteApp.getScanDocSettingDocument());
        docSaveAsType.setText(kodakVeriteApp.getScanDocSettingSaveAsType());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
