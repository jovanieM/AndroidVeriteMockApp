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
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SP_000 extends AppCompatActivity {

    Button back;
    ImageButton settings;
    RelativeLayout rl_scan, rl_crop, rl_send, save, email, drive, skyDrive, ivContain, mRoot;
    Intent intent, chooser;
    Context context;
    static final int GET_BITMAP_REQUEST = 2;
    CustomImgView iv;

    Bitmap bm, bm2;
    Bundle bundle;
    boolean visible = false;
    float measuredWidth, measuredHeigtt, width, height;
    Thread t;
    int finalW, finalH;
    Uri resultUri;
    int containerWidth;
    boolean test2;
    TextView photoQuality, photoColor, photoDocument;
    KodakVeriteApp kodakVeriteApp;
    boolean saved;
    CustomImgView civ;
    static final double CONTAINER_RATIO = 0.7132;
    BitmapRegionDecoder bitmapRegionDecoder = null;
    static byte[] byteArray;
    Intent intent2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = savedInstanceState;
        setContentView(R.layout.activity_sp_000);
        saved = false;

        kodakVeriteApp = new KodakVeriteApp();
        //myImageView = new MyImageView(this);

        this.back = (Button) findViewById(R.id.back);
        this.rl_scan = (RelativeLayout) findViewById(R.id.scan);
        this.rl_crop = (RelativeLayout) findViewById(R.id.crop);
        this.rl_send = (RelativeLayout) findViewById(R.id.send);
        this.save = (RelativeLayout) findViewById(R.id.save);
        this.email = (RelativeLayout) findViewById(R.id.email);
        this.drive = (RelativeLayout) findViewById(R.id.drive);
        this.skyDrive = (RelativeLayout) findViewById(R.id.one_box);
        ivContain = (RelativeLayout) findViewById(R.id.ivContainer);
        mRoot = (RelativeLayout) findViewById(R.id.root);
        settings = (ImageButton) findViewById(R.id.pscanSettingsIcon);
        civ = (CustomImgView) findViewById(R.id.customImgView);
        context = getApplicationContext();
        test2 = new PhotoScanMain().test;
        Resources res = getResources();
        email.bringToFront();
        save.bringToFront();
        intent2 = new Intent(SP_000.this, ImageCropper.class);

//        ViewTreeObserver vto = ivContain.getViewTreeObserver();
//        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//
//                ivContain.getViewTreeObserver().removeOnPreDrawListener(this);
//                Log.v("onResume", String.valueOf(ivContain.getHeight()));
//                Log.v("onResume", String.valueOf(ivContain.getMeasuredWidth()));
//                iv.getLayoutParams().width = (int) (ivContain.getHeight() * 0.6);
//                iv.getLayoutParams().height = ivContain.getHeight();
//                iv.requestLayout();
//                iv.invalidate();
//
//                containerWidth = ivContain.getMeasuredWidth();
//
//                return true;
//            }
//        });

     /*   ivContain.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {



                //containerRatio = (double) ivContain.getWidth() / ivContain.getHeight();
            }
        });
*/
        photoQuality = (TextView) findViewById(R.id.photo_quality);
        photoColor = (TextView) findViewById(R.id.photo_color);
        photoDocument = (TextView) findViewById(R.id.photo_type);


//        Resources resources = getResources();
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(resources, R.drawable.sample, options);
//        int imageHeight = options.outHeight;
//        int imageWidth = options.outWidth;
//
//        Toast.makeText(this, String.valueOf(imageHeight), Toast.LENGTH_SHORT).show();
//
//        final ArrayList<Uri> arraylist = new ArrayList<>();
//        File pictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//        String[] listOfPictures = pictures.list();
//        for(String picture : listOfPictures){
//            uri = Uri.parse("file://" + pictures.toString() + "/" + picture);
//            arraylist.add(uri);
//            //Toast.makeText(SP_000.this, uri.toString(),Toast.LENGTH_SHORT).show();
//        }
//
        bm = BitmapFactory.decodeResource(res, R.drawable.sample);


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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        scanPhotoDialog2.show(getFragmentManager(), "My Progress Dialog");
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (test2) {

                            new ScanCanceledAlert().newInstance("Scan Canceled").show(getFragmentManager(), "dialog");

                        }
                        scanPhotoDialog2.dismiss();
                    }
                }).start();


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

                // intent = new Intent(Intent.ACTION_SEND);
                //intent.setType("image/*");
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

//
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                byte[] byteArray = stream.toByteArray();


                intent2.putExtra("left", civ.getLeft());
                //myImageView.setL(iv.getLeft());
                intent2.putExtra("top", civ.getTop());
                //myImageView.setT(iv.getTop());
                intent2.putExtra("right", civ.getRight());
                //myImageView.setR(iv.getRight() + iv.getLeft());
                intent2.putExtra("bottom", civ.getBottom());
                //myImageView.setB(iv.getBottom());
                intent2.putExtra("image", byteArray);

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
                } else {
                    save.setVisibility(View.GONE);
                    email.setVisibility(View.GONE);
                    drive.setVisibility(View.GONE);
                    skyDrive.setVisibility(View.GONE);
                    visible = false;
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int l, r,t,b;
        byte[] bytes;

        if (resultCode == 2) {

            l = data.getIntExtra("left", 0);
            t = data.getIntExtra("top", 0);
            r = data.getIntExtra("right", 0);
            b = data.getIntExtra("bottom", 0);
            bytes = data.getByteArrayExtra("bitmap");

            //bm2 = bm;
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = true;
//            BitmapFactory.


            Log.v("Activity result", "activity result");
            try {
                bitmapRegionDecoder = BitmapRegionDecoder.newInstance(bytes, 0, bytes.length, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Log.v("Activity result", "before region decoder");
            bm = bitmapRegionDecoder.decodeRegion(new Rect(l,t,r,b), options);
            Log.v("Activity result", "after region decoder");

//            bm2 = Bitmap.createBitmap(bm, l+2, t +2 , r-2, b - 2);
//            bm =bm2;

            int tempWidth = r-l;
            int tempHeight = b-t;

            double imageRatio = (double) tempWidth/tempHeight;
            Log.v("result false", String.valueOf(tempWidth));
            Log.v("result false", String.valueOf(tempHeight));
            Log.v("result false", String.valueOf(r));
            Log.v("result false", String.valueOf(b));
            Log.v("result false", String.valueOf(imageRatio));

        } else {
            Toast.makeText(this, "no change", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onBackPressed() {

        if (!saved) {
            SaveDocumentDialog.newInstance("Save image to ScannedImage").show(getFragmentManager(), "document");
        } else {
            super.onBackPressed();
        }


        // startActivity(new Intent(SP_000.this, HM10_000.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        double bitmapRatio;



//        Log.v("onResumed", String.valueOf(iv.getWidth()));
//        Log.v("onResumed", String.valueOf(iv.getHeight()));

        if (bm.getWidth() > bm.getHeight()) {
            bitmapRatio = (double) bm.getHeight() / bm.getWidth();
        } else {
            bitmapRatio = (double) bm.getWidth() / bm.getHeight();
        }

        Log.v("onResumed", String.valueOf(bitmapRatio));
        Log.v("onResumed", String.valueOf(containerWidth));

        civ.setImageBitmap(bm);

        new AsyncTask< Integer, Void,Bitmap>(){

            @Override
            protected Bitmap doInBackground(Integer... params) {
                Bitmap bitmap =BitmapFactory.decodeResource(getResources(), params[0]);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, stream);

                byteArray = stream.toByteArray();
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap aVoid) {
               bm = aVoid;

            }
        }.execute(R.drawable.sample);

//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
//
//        byteArray = stream.toByteArray();
        Log.v("onResumed bm", String.valueOf(bm));

        photoQuality.setText(kodakVeriteApp.getScanPhotoSettingQuality());
        photoColor.setText(kodakVeriteApp.getScanPhotoSettingColor());
        photoDocument.setText(kodakVeriteApp.getScanPhotoSettingDocument());

    }

    @Override
    public void onDetachedFromWindow() {
        bm = null;
        super.onDetachedFromWindow();
    }
}
