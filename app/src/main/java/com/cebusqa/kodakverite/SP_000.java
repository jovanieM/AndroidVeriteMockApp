package  com.cebusqa.kodakverite;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SP_000 extends AppCompatActivity{

    Button back;
    ImageButton settings;
    RelativeLayout rl_scan, rl_crop, rl_send, save,email,drive,skyDrive;
    Intent intent, chooser;
    Context context;
    static final int GET_BITMAP_REQUEST = 2;
    ImageView iv;
    Bitmap bm;
    Bundle bundle;
    boolean visible = false;
    float measuredWidth, measuredHeigtt, width, height;
    Thread t;
    int finalW, finalH;
    Uri resultUri;
    boolean test2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = savedInstanceState;
        setContentView(R.layout.activity_sp_000);

        this.back = (Button)findViewById(R.id.back);
        this.rl_scan = (RelativeLayout) findViewById(R.id.scan);
        this.rl_crop = (RelativeLayout) findViewById(R.id.crop);
        this.rl_send = (RelativeLayout) findViewById(R.id.send);
        this.save = (RelativeLayout) findViewById(R.id.save);
        this.email = (RelativeLayout) findViewById(R.id.email);
        this.drive = (RelativeLayout) findViewById(R.id.drive);
        this.skyDrive = (RelativeLayout) findViewById(R.id.one_box);
        settings = (ImageButton) findViewById(R.id.scanSettingsIcon);
        iv = (ImageView) findViewById(R.id.imageView);
        context = getApplicationContext();
        test2 = new PhotoScanMain().test;
        Resources res = getResources();
        email.bringToFront();
        save.bringToFront();


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

        iv.setImageBitmap(bm);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SP_000.this, Scan_Photo_Settings.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(SP_000.this, HM10_000.class));
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
                        if(test2){

                            new ScanCanceledAlert().newInstance("Scan Canceled").show(getFragmentManager(),"dialog");

                        }
                        scanPhotoDialog2.dismiss();
                    }
                }).start();


            }

        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveAsDialog saveAs = new SaveAsDialog();

                saveAs.show(getFragmentManager(), "My dialog");

            }
        });

        //Toast.makeText(this, String.valueOf(maxW), Toast.LENGTH_SHORT).show();
        // working code
        drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri imageUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.drawable.sample);
                intent = ShareCompat.IntentBuilder.from(SP_000.this).setType("image/*").getIntent().setPackage("com.google.android.apps.docs");

               // intent = new Intent(Intent.ACTION_SEND);
                //intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                chooser=Intent.createChooser(intent, "Send Image");
                startActivity(chooser);
            }
        });

        //working code
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri imageUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.drawable.sample);
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                chooser=Intent.createChooser(intent, "Send Image");
                startActivity(chooser);
            }
        });

        skyDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri imageUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.drawable.sample);
                intent = ShareCompat.IntentBuilder.from(SP_000.this).setType("image/*").getIntent().setPackage("com.microsoft.skydrive");

                // intent = new Intent(Intent.ACTION_SEND);
                //intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                chooser=Intent.createChooser(intent, "Send Image");
                startActivity(chooser);
            }
        });

        rl_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SP_000.this, ImageCropper.class);
                intent.putExtra("width", bm.getWidth()+80);
                intent.putExtra("height", bm.getHeight()+80);
                startActivityForResult(intent,GET_BITMAP_REQUEST);
                //startActivity(intent);
            }
        });

        rl_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!visible) {
                    save.setVisibility(View.VISIBLE);
                    email.setVisibility(View.VISIBLE);
                    drive.setVisibility(View.VISIBLE);
                    skyDrive.setVisibility(View.VISIBLE);
                    visible = true;
                }else{
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
        if(resultCode == 2){
            int l = data.getIntExtra("left",0);
            int t = data.getIntExtra("top", 0);
            int r = data.getIntExtra("right", 300);
            int b = data.getIntExtra("bottom", 500);


            Bitmap mBitmap = Bitmap.createBitmap(bm, l,t,r,b);
            iv.setImageBitmap(mBitmap);
        }else{
            Toast.makeText(this, "no change", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
       // startActivity(new Intent(SP_000.this, HM10_000.class));
    }
}
