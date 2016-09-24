package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/**
 * Created by Cebu SQA on 24/06/2016.
 */
public class DocumentScan2 extends Activity implements View.OnClickListener {

    private Button mback;
    RelativeLayout scan, crop, send, save2, email2, drive2, skyDrive2;

    int imgID[] = {R.id.doc_image, R.id.doc_image2, R.id.doc_image3};
    int res[] = {R.drawable.docu, R.drawable.supermario_03, R.drawable.supermario_06};

    ImageView[] imv1;
    //Context context;
    static final int GET_BITMAP_REQUEST2 = 2;
    boolean dtest2;
    boolean visible2;
    boolean saved;
    int cntr = 0;
    int cntr2 = 1;
    ImageButton settings2;
    Bitmap bm2;
    Intent intent, chooser;
    TextView docQuality, docColor, docDocument, docSaveAsType, counter, prv, nxt;
    KodakVeriteApp kodakVeriteApp;
    WebView webView;
    Handler handler;
    DisplayImageOptions dim;

    ImageView img_send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_scan_2);
        Log.v("myApp", "onCreate Doc2");

        kodakVeriteApp = new KodakVeriteApp();
        saved = false;
        handler = new Handler();

        imv1 = new ImageView[imgID.length];
        dim = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new SimpleBitmapDisplayer())
                .build();

        for (int i = 0; i < imgID.length; i++) {
            imv1[i] = (ImageView) findViewById(imgID[i]);
            ImageLoader.getInstance().displayImage("drawable://" + res[i], imv1[i], dim);

        }


        mback = (Button) findViewById(R.id.back);
        this.scan = (RelativeLayout) findViewById(R.id.scan2);
        this.crop = (RelativeLayout) findViewById(R.id.crop2);
        this.send = (RelativeLayout) findViewById(R.id.send2);
        this.save2 = (RelativeLayout) findViewById(R.id.save2);
        this.email2 = (RelativeLayout) findViewById(R.id.email2);
        this.drive2 = (RelativeLayout) findViewById(R.id.drive2);
        this.skyDrive2 = (RelativeLayout) findViewById(R.id.one_box2);
        this.img_send = (ImageView) findViewById(R.id.send_icon);
        settings2 = (ImageButton) findViewById(R.id.dscanSettingsIcon);


//        imv2 = (ImageView) findViewById(R.id.doc_image2);
//        imv3 = (ImageView) findViewById(R.id.doc_image3);

        webView = (WebView) findViewById(R.id.webView);

        prv = (TextView) findViewById(R.id.previousSD);
        nxt = (TextView) findViewById(R.id.nextSD);
        counter = (TextView) findViewById(R.id.countSD);

        docQuality = (TextView) findViewById(R.id.doc_quality);
        docColor = (TextView) findViewById(R.id.doc_color);
        docDocument = (TextView) findViewById(R.id.doc_type);
        docSaveAsType = (TextView) findViewById(R.id.doc_save_as);

        email2.bringToFront();
        save2.bringToFront();
        dtest2 = new DocumentScan().dtest;
        //bm2 = BitmapFactory.decodeResource(res, R.drawable.docu);
        //imv1.setImageBitmap(bm2);

        imv1[0].setVisibility(View.VISIBLE);

        prv.setOnClickListener(this);
        nxt.setOnClickListener(this);

        String st = String.valueOf(cntr + 1);

        counter.setText(st + "/" + st);

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
                        scanPhotoDialog3.show(getFragmentManager(), "MyProgress");
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //test if the dialog is canceled
                        if (dtest2) {
//                            getFragmentManager().findFragmentByTag("MyProgress").onDestroy();
                            new ScanCanceledAlert().newInstance("Scan Canceled").show(getFragmentManager(), "dialog");
                        } else {

                            scanPhotoDialog3.dismiss();

                            if (cntr2 != 20) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        cntr2++;
                                        cntr = cntr2;

                                        if (cntr2 == 2) {
                                            prv.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gold));
                                        }
                                        counter.setText(String.valueOf((cntr2) + "/" + (cntr2)));

                                        for (int i = 0; i < imv1.length; i++) {
                                            if (i == ((cntr2 - 1) % 3)) {
                                                imv1[i].setVisibility(View.VISIBLE);
                                            } else {
                                                imv1[i].setVisibility(View.GONE);
                                            }
                                        }

                                        //imv1.setImageResource(imgID[(cntr2-1) % 3]);

                                    }
                                });
                            }
                        }
                        //
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
                //Intent intent = new Intent(DocumentScan2.this, ImageCropper.class);
                //intent.putExtra("width", bm2.getWidth() + 80);
                //intent.putExtra("height", bm2.getHeight() + 80);
                Toast.makeText(getApplicationContext(), "Temporarily disabled function", Toast.LENGTH_SHORT).show();
                //startActivityForResult(intent, GET_BITMAP_REQUEST2);
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

                    img_send.setImageResource(R.mipmap.send_icon);
                } else {
                    save2.setVisibility(View.GONE);
                    email2.setVisibility(View.GONE);
                    drive2.setVisibility(View.GONE);
                    skyDrive2.setVisibility(View.GONE);
                    visible2 = false;

                    img_send.setImageResource(R.mipmap.close_icon);
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
            //imv1.setImageBitmap(mBitmap);
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
        for (int i = 0; i < imgID.length; i++) {
            imv1[i] = null;
        }

        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.previousSD:

                if (cntr > 1) {

                    cntr--;
                    if (nxt.getCurrentTextColor() == Color.TRANSPARENT) {
                        nxt.setTextColor(ContextCompat.getColor(getApplication(), R.color.gold));
                    }
                    if (cntr == 1) {
                        prv.setTextColor(Color.TRANSPARENT);

                    }
                    counter.setText(String.valueOf(cntr + "/" + cntr2));

                    for (int i = 0; i < imv1.length; i++) {
                        if (i == ((cntr - 1) % 3)) {
                            imv1[i].setVisibility(View.VISIBLE);
                        } else {
                            imv1[i].setVisibility(View.GONE);
                        }
                    }
                    //imv1.setImageResource(imgID[(cntr-1) % 3]);

                }

                break;
            case R.id.nextSD:

                if (cntr < cntr2) {

                    cntr++;
                    if (prv.getCurrentTextColor() == Color.TRANSPARENT) {
                        prv.setTextColor(ContextCompat.getColor(getApplication(), R.color.gold));
                    }
                    if (cntr == cntr2) {
                        nxt.setTextColor(Color.TRANSPARENT);
                    }

                    counter.setText(String.valueOf(cntr + "/" + cntr2));

                    for (int i = 0; i < imv1.length; i++) {
                        if (i == ((cntr - 1) % 3)) {
                            imv1[i].setVisibility(View.VISIBLE);
                        } else {
                            imv1[i].setVisibility(View.GONE);
                        }
                    }


                    // imv1.setImageResource(imgID[(cntr-1) % 3]);

                }

                break;
        }
    }
}
