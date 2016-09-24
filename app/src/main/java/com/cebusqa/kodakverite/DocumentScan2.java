package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ShareCompat;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Cebu SQA on 24/06/2016.
 */
public class DocumentScan2 extends Activity implements View.OnClickListener {

    private Button mback;
    RelativeLayout scan, crop, send;

    CustomImgView civ2;
    ImageView save2, email2, drive2,skyDrive2;

    static final int GET_BITMAP_REQUEST2 = 2;
    ImageButton settings2;

    boolean dtest2;
    Intent intent, chooser;
    boolean visible2;
    TextView docQuality, docColor, docDocument, docSaveAsType, counter, prv, nxt;
    KodakVeriteApp kodakVeriteApp;
    boolean saved;
    int cntr2 = 1;
    int keyStrings = 1;
    Handler handler;


    ArrayList<String> imagesUri;
    public static LruCache<String, Bitmap> mMemoryCache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_scan_2);
        Log.v("myApp", "onCreate Doc2");

        kodakVeriteApp = new KodakVeriteApp();
        saved = false;

        imagesUri = new ArrayList<>();

        final int maxMemorySize = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemorySize / 10;

        mMemoryCache = new LruCache<>(cacheSize);

        civ2 = (CustomImgView) findViewById(R.id.customImgView);

        loadBitmap(R.drawable.docu, keyStrings);

        handler = new Handler();

        mback = (Button) findViewById(R.id.back);
        this.scan = (RelativeLayout) findViewById(R.id.scan2);
        this.crop = (RelativeLayout) findViewById(R.id.crop2);
        this.send = (RelativeLayout) findViewById(R.id.send2);
        this.save2 = (ImageView) findViewById(R.id.save2);
        this.email2 = (ImageView) findViewById(R.id.email2);
        this.drive2 = (ImageView) findViewById(R.id.drive2);
        this.skyDrive2 = (ImageView) findViewById(R.id.one_box2);
        settings2 = (ImageButton) findViewById(R.id.dscanSettingsIcon);

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

        prv.setOnClickListener(this);
        nxt.setOnClickListener(this);

        String st = String.valueOf(keyStrings);
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
                if (cntr2 < 20) {

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

                                new ScanCanceledAlert().newInstance("Scan Canceled").show(getFragmentManager(), "dialog");
                            } else {


                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        cntr2++;
                                        keyStrings = cntr2;

                                        if (cntr2 > 1) {
                                            prv.setVisibility(View.VISIBLE);
                                        }
                                        nxt.setVisibility(View.INVISIBLE);
                                        counter.setText(String.valueOf((cntr2) + "/" + (cntr2)));

                                        loadBitmap(R.drawable.docu, cntr2);
                                        //ImageLoader.getInstance().displayImage("drawable://" + resID[0], civ2, dim);

                                    }
                                });
                                //scanPhotoDialog3.dismiss();


                                scanPhotoDialog3.dismiss();

                            }
                            //
                        }
                    }).start();

                }


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
                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                chooser = Intent.createChooser(intent, "Send Image");
                startActivity(chooser);
            }
        });

        skyDrive2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri imageUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.docu);
                intent = ShareCompat.IntentBuilder.from(DocumentScan2.this).setType("image/*").getIntent().setPackage("com.microsoft.live");
                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                chooser = Intent.createChooser(intent, "Send Image");
                startActivity(chooser);
            }
        });

        crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ImageCropper2.class);
                intent.putExtra("left", civ2.getLeft());
                intent.putExtra("top", civ2.getTop());
                intent.putExtra("right", civ2.getRight());
                intent.putExtra("bottom", civ2.getBottom());
                intent.putExtra("key", keyStrings);
                Log.v("crop b", "put byte array");
                Log.v("crop a", "put byte array");
                startActivityForResult(intent, GET_BITMAP_REQUEST2);
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
        // super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 2) {
            BitmapRegionDecoderTask task = new BitmapRegionDecoderTask(civ2, data);
            task.execute(getBitmapFromMemoryCache(String.valueOf(keyStrings)));
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
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.previousSD:

                if (keyStrings > 1) {
                    keyStrings--;
                    if (nxt.getVisibility() == View.INVISIBLE) {
                        nxt.setVisibility(View.VISIBLE);

                    }
                    if (keyStrings == 1) {
                        prv.setVisibility(View.INVISIBLE);

                    }
                    counter.setText(String.valueOf(keyStrings + "/" + cntr2));

                    civ2.setImageBitmap(getBitmapFromMemoryCache(String.valueOf(keyStrings)));


//                    ImageLoader.getInstance().displayImage("drawable://" + resID[(cntr2-1) % 3], civ2, new DisplayImageOptions.Builder()
//                            .cacheInMemory(true)
//                            .cacheOnDisk(true)
//                            .displayer(new SimpleBitmapDisplayer())
//                            .build());
                    //ImageLoader.getInstance().displayImage("drawable://" + resID[(cntr - 1) % 3], civ2, dim);
                    //Picasso.with(getApplicationContext()).load(resID[(cntr - 1) % 3]).memoryPolicy(MemoryPolicy.NO_STORE, MemoryPolicy.NO_CACHE).into(civ2);
                    //civ2.setImageURI(bitmapArrayList.get((cntr - 1)% 3));
                    //imv1.setImageResource(imgID[(cntr-1) % 3]);
                }

                break;
            case R.id.nextSD:

                if (keyStrings < cntr2) {
                    keyStrings++;
                    //keyStrings++;
                    if (prv.getVisibility() == View.INVISIBLE) {
                        prv.setVisibility(View.VISIBLE);
                    }
                    if (keyStrings == cntr2) {
                        nxt.setVisibility(View.INVISIBLE);
                    }

                    counter.setText(String.valueOf(keyStrings + "/" + cntr2));
                    civ2.setImageBitmap(getBitmapFromMemoryCache(String.valueOf(keyStrings)));


                }

                break;
        }
    }

    /**
     * ---------------------------------------------------
     * this class will decode the reqion given by activity result
     * ---------------------------------------------------
     */

    class BitmapRegionDecoderTask extends AsyncTask<Bitmap, Void, Bitmap> {

        KodakVeriteApp kodakVeriteApp1;
        BitmapRegionDecoder bitmapRegionDecoder;
        ByteArrayOutputStream stream;
        CustomImgView customImgView;
        Intent intent;
        int left;
        int top;
        int right;
        int bottom;

        BitmapRegionDecoderTask(CustomImgView customImgView, Intent intent) {

            kodakVeriteApp1 = new KodakVeriteApp();
            this.customImgView = customImgView;
            stream = new ByteArrayOutputStream();
            this.intent = intent;

        }

        @Override
        protected void onPreExecute() {
            left = intent.getIntExtra("left", 0);
            top = intent.getIntExtra("top", 0);
            right = intent.getIntExtra("right", 0);
            bottom = intent.getIntExtra("bottom", 0);


        }

        @Override
        protected Bitmap doInBackground(Bitmap... params) {

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmap = params[0];
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            try {
                bitmapRegionDecoder = BitmapRegionDecoder.newInstance(byteArray, 0, byteArray.length, false);

            } catch (IOException e) {
                e.printStackTrace();
            }


            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Log.v("Activity result", "before region decoder");

            try {
                return bitmapRegionDecoder.decodeRegion(new Rect(left, top, right, bottom), options);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            customImgView.setImageBitmap(bitmap);
            replaceBitmapFromMemoryCache(String.valueOf(keyStrings), bitmap);


        }
    }


    public Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    public void setBitmapToMemoryCache(String key, Bitmap bmp) {

        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bmp);

        }
    }

    public void replaceBitmapFromMemoryCache(String key, Bitmap bitmap) {
        mMemoryCache.remove(key);
        setBitmapToMemoryCache(key, bitmap);

    }
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {

        private int data = 0;
        private String keyString;

        BitmapWorkerTask(int k) {
            keyString = String.valueOf(k);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            data = params[0];

            return BitmapFactory.decodeResource(getResources(), data);

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            setBitmapToMemoryCache(keyString, bitmap);
            civ2.setImageBitmap(bitmap);

        }
    }

    public void loadBitmap(int resId, int keyStrings) {
        BitmapWorkerTask task = new BitmapWorkerTask(keyStrings);
        task.execute(resId);
    }


}



