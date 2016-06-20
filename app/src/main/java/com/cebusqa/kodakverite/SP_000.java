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

public class SP_000 extends AppCompatActivity implements SaveAsDialog.Communicator{

    Button back;
    RelativeLayout rl_scan, rl_crop, rl_send;
    Intent intent, chooser;
    Context context;
    static final int GET_BITMAP_REQUEST = 2;
    ImageView iv;
    Bitmap bm;
    Bundle bundle;
    float measuredWidth, measuredHeigtt, width, height;

    int finalW, finalH;
    Uri resultUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = savedInstanceState;
        setContentView(R.layout.activity_sp_000);

        back = (Button)findViewById(R.id.back);
        rl_scan = (RelativeLayout) findViewById(R.id.scan);
        rl_crop = (RelativeLayout) findViewById(R.id.crop);
        rl_send = (RelativeLayout) findViewById(R.id.send);
        iv = (ImageView) findViewById(R.id.imageView);
        context = getApplicationContext();

        Resources res = getResources();


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

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(SP_000.this, HM10_000.class));
            }
        });

        rl_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String MEDIA_MOUNTED = "mounted";
                String diskState = Environment.getExternalStorageState();
                FragmentManager manager = getFragmentManager();
                SaveAsDialog saveAs = new SaveAsDialog();
                saveAs.show(manager, "save");
                if(diskState.equals(MEDIA_MOUNTED)){

                    File pictureFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    File filePicture = new File(pictureFolder, "file_sample.jpg");
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(filePicture);
                        bm.compress(Bitmap.CompressFormat.JPEG, 100 , fos);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            fos.close();
                            Toast.makeText(SP_000.this, "File is save", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    Toast.makeText(SP_000.this, "External disk is not mounted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Toast.makeText(this, String.valueOf(maxW), Toast.LENGTH_SHORT).show();
        // working code
//        rl_send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Uri imageUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.drawable.sample);
//                intent = ShareCompat.IntentBuilder.from(SP_000.this).setType("image/*").getIntent().setPackage("com.google.android.apps.docs");
//
////                intent = new Intent(Intent.ACTION_SEND);
////                intent.setType("image/*");
//                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
//                chooser=Intent.createChooser(intent, "Send Image");
//                startActivity(chooser);
//            }
//        });

        //working code
//        rl_send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Uri imageUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.drawable.sample);
//                intent = new Intent(Intent.ACTION_SEND);
//                intent.setType("image/*");
//                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
//                chooser=Intent.createChooser(intent, "Send Image");
//                startActivity(chooser);
//            }
//        });

        rl_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SP_000.this, ImageCropper.class);
                intent.putExtra("width", bm.getWidth()+80);
                intent.putExtra("height", bm.getHeight()+80);
                Toast.makeText(getApplicationContext(), String.valueOf(bm.getWidth()), Toast.LENGTH_SHORT).show();
                startActivityForResult(intent,GET_BITMAP_REQUEST);
                //startActivity(intent);
            }
        });
/*
      ViewTreeObserver vto = iv.getViewTreeObserver();

        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                iv.getViewTreeObserver().addOnPreDrawListener(this);
                finalW = iv.getLeft();
                finalH = iv.getRight();
                Toast.makeText(SP_000.this, String.valueOf(finalH),Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        bm = BitmapFactory.decodeFile(resultUri.getPath());

        //Bitmap bmp = Bitmap.createBitmap(bm,20,20,iv.getImageAlpha(),iv.getImageAlpha());
        *//*
*/
/*measuredWidth = iv.getMeasuredWidth();
        measuredHeigtt = iv.getMeasuredHeight();
        width = iv.getWidth();
        height = iv.getHeight();
        iv.setImageBitmap(bm);
*/
        //ImageView iv = new ImageView(this);
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
    public void onDialogMessage(String message) {

    }
}
