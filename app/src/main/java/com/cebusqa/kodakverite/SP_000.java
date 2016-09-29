package com.cebusqa.kodakverite;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SP_000 extends AppCompatActivity {

    Button back;
    ImageButton settings;
    RelativeLayout rl_scan, rl_crop, rl_send, save, email, drive, skyDrive, ivContain, mRoot;
    Intent intent, chooser;
    Context context;
    static final int GET_BITMAP_REQUEST = 2;

    ImageView img_send;

    Bitmap bm;
    Bundle bundle;
    boolean visible = false;

    Thread t, t2, t3;

    boolean test2;
    TextView photoQuality, photoColor, photoDocument;
    KodakVeriteApp kodakVeriteApp;
    boolean saved;
    CustomImgView civ;
    BitmapRegionDecoder bitmapRegionDecoder = null;
    Intent intent2;
    InputStream is;
    ByteArrayOutputStream stream;
    Handler handler;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = savedInstanceState;
        setContentView(R.layout.activity_sp_000);
        saved = false;

        kodakVeriteApp = new KodakVeriteApp();
        handler = new Handler();
        //myImageView = new MyImageView(this);

        this.back = (Button) findViewById(R.id.back);
        this.rl_scan = (RelativeLayout) findViewById(R.id.scan);
        this.rl_crop = (RelativeLayout) findViewById(R.id.crop);
        this.rl_send = (RelativeLayout) findViewById(R.id.send);
        this.save = (RelativeLayout) findViewById(R.id.save);
        this.email = (RelativeLayout) findViewById(R.id.email);
        this.drive = (RelativeLayout) findViewById(R.id.drive);
        this.skyDrive = (RelativeLayout) findViewById(R.id.one_box);
        this.img_send = (ImageView) findViewById(R.id.send_icon);

        ivContain = (RelativeLayout) findViewById(R.id.ivContainer);
        mRoot = (RelativeLayout) findViewById(R.id.root);
        settings = (ImageButton) findViewById(R.id.pscanSettingsIcon);
        civ = (CustomImgView) findViewById(R.id.customImgView);
        test2 = new PhotoScanMain().test;
        email.bringToFront();
        save.bringToFront();
        stream = new ByteArrayOutputStream();

        photoQuality = (TextView) findViewById(R.id.photo_quality);
        photoColor = (TextView) findViewById(R.id.photo_color);
        photoDocument = (TextView) findViewById(R.id.photo_type);

        //bm = BitmapFactory.decodeResource(getResources(), R.drawable.sample);
        civ.setImageResource(R.drawable.sample);


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SP_000.this, Scan_Photo_Settings.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SaveDocumentDialog.newInstance("Save image to ScannedImage").show(getFragmentManager(), "document");

            }
        });
        rl_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test2 = false;
                final ScanPhotoDialog2 scanPhotoDialog2 = new ScanPhotoDialog2();
                scanPhotoDialog2.setCancelable(false);
                scanPhotoDialog2.show(getFragmentManager(), "scanning");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(!test2){
                            kodakVeriteApp.clearByteData();
                            new BitmapWorker().execute(R.drawable.sample);
                        }

                    }
                }, 4000);

            }

        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saved = true;
                SaveImageDialog.newInstance("Save image to Scanned Image").show(getFragmentManager(), "image");

                t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            if (getFragmentManager().findFragmentByTag("image") != null) {
                                getFragmentManager().findFragmentByTag("image").onDestroy();
                            } else {
                                t.interrupt();
                            }

                        }
                    }
                });
                t.start();

            }
        });


        drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri imageUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.sample);
                intent = ShareCompat.IntentBuilder.from(SP_000.this).setType("image/*").getIntent().setPackage("com.google.android.apps.docs");

                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                chooser = Intent.createChooser(intent, "Send Image");
                startActivity(chooser);
            }
        });

        //working code
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri imageUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.sample);
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                chooser = Intent.createChooser(intent, "Send Image");
                startActivity(chooser);
            }
        });

        skyDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri imageUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.sample);
                intent = ShareCompat.IntentBuilder.from(SP_000.this).setType("image/*").getIntent().setPackage("com.microsoft.skydrive");

                // intent = new Intent(Intent.ACTION_SEND);
                //intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                chooser = Intent.createChooser(intent, "Send Image");
                startActivity(chooser);
            }
        });

        rl_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent2 = new Intent(getApplicationContext(), ImageCropper.class);

                intent2.putExtra("left", civ.getLeft());
                //myImageView.setL(iv.getLeft());
                intent2.putExtra("top", civ.getTop());
                //myImageView.setT(iv.getTop());
                intent2.putExtra("right", civ.getRight());
                //myImageView.setR(iv.getRight() + iv.getLeft());
                intent2.putExtra("bottom", civ.getBottom());
                //myImageView.setB(iv.getBottom());
                Log.v("crop b", "put byte array");
                //intent2.putExtra("image", kodakVeriteApp.getBitmapData());
                Log.v("crop a", "put byte array");

                startActivityForResult(intent2, GET_BITMAP_REQUEST);


                //startActivity(intent);
            }
        });

        rl_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!visible) {
                    save.setVisibility(View.VISIBLE);
                    email.setVisibility(View.VISIBLE);
                    drive.setVisibility(View.VISIBLE);
                    skyDrive.setVisibility(View.VISIBLE);
                    visible = true;
                    img_send.setImageResource(R.mipmap.send_icon);
                } else {
                    save.setVisibility(View.GONE);
                    email.setVisibility(View.GONE);
                    drive.setVisibility(View.GONE);
                    skyDrive.setVisibility(View.GONE);
                    visible = false;
                    img_send.setImageResource(R.mipmap.close_icon);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int l, r, t, b;

        if (resultCode == GET_BITMAP_REQUEST) {

            l = data.getIntExtra("left", 0);
            t = data.getIntExtra("top", 0);
            r = data.getIntExtra("right", 0);
            b = data.getIntExtra("bottom", 0);
            //bytes = data.getByteArrayExtra("bitmap");


            Log.v("Activity result", "activity result");
            try {
                bitmapRegionDecoder = BitmapRegionDecoder.newInstance(kodakVeriteApp.getBitmapData(), 0, kodakVeriteApp.getBitmapData().length, false);


            } catch (IOException e) {
                e.printStackTrace();
            }

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Log.v("Activity result", "before region decoder");

            try {
                bm = bitmapRegionDecoder.decodeRegion(new Rect(l, t, r, b), options);
            } catch (Exception e) {
                e.printStackTrace();
            }

            civ.setImageBitmap(bm);

            new AsyncTask<Void, Void, Bitmap>() {

                @Override
                protected Bitmap doInBackground(Void... params) {

                    Log.e("activity result", "doInBackground");
                    stream = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG, 100, stream);

                    kodakVeriteApp.setBitmapData(stream.toByteArray());


                    return bm;

                }

                @Override
                protected void onPostExecute(Bitmap aVoid) {
                    bm = aVoid;


                }
            }.execute();


        }

    }

    public class BitmapWorker extends AsyncTask<Integer, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(Integer... params) {
            return BitmapFactory.decodeResource(getResources(),params[0]);

        }

        @Override
        protected void onPostExecute(Bitmap aVoid) {
            bm = aVoid;
            civ.setImageBitmap(aVoid);
            getFragmentManager().findFragmentByTag("scanning").onDestroy();
            onResume();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.sample);
    }

    @Override
    public void onBackPressed() {

        if (!saved) {
            SaveDocumentDialog.newInstance("Save image to ScannedImage").show(getFragmentManager(), "document");
            kodakVeriteApp.clearByteData();
        } else {
            super.onBackPressed();
            kodakVeriteApp.clearByteData();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "starts");

        if (kodakVeriteApp.getBitmapData() == null) {
            t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    ByteArrayOutputStream st = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG, 100, st);
                    kodakVeriteApp.setBitmapData(st.toByteArray());
                    Log.d("onResume", String.valueOf(st.toByteArray().length));
                }
            });
            t2.start();
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        photoQuality.setText(kodakVeriteApp.getScanPhotoSettingQuality());
        photoColor.setText(kodakVeriteApp.getScanPhotoSettingColor());
        photoDocument.setText(kodakVeriteApp.getScanPhotoSettingDocument());

        Log.d("onResume", "ends");

    }

    @Override
    public void onDetachedFromWindow() {
        bm = null;
        super.onDetachedFromWindow();
    }

    public void scanCanceled(){
        final AirprintSavingSettings airprintSavingSettings = new AirprintSavingSettings();
        airprintSavingSettings.show(getFragmentManager(), "canceling");
        airprintSavingSettings.setCancelable(false);
        final Handler handler = new Handler();
        final Runnable run = new Runnable() {
            @Override
            public void run() {
                getFragmentManager().findFragmentByTag("canceling").onDestroy();
                final ScanCanceledAlert scanCanceledAlert =new ScanCanceledAlert();
                scanCanceledAlert.setCancelable(false);
                scanCanceledAlert.show(getFragmentManager(), "photo2");
            }
        };
        handler.postDelayed(run, 4000);
    }


}
