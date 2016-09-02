package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.BaseDiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.utils.L;

import java.io.File;
import java.util.ArrayList;
import java.util.jar.Manifest;

/**
 * Created by Cebu SQA on 27/06/2016.
 */
public class KodakVeriteApp extends Application {
    static int  currentStatusValue = 0;
    static boolean airprintPrvState = false;
    static boolean gcpPrevState = false;
    static ArrayList<String> bucketName;
    static ArrayList<String> bucketData;
    static ArrayList<String> dirs;
    static ArrayList<String> thumbData;
    static ArrayList<String> noOfFiles;
    static String fName;
    ArrayList<Integer> imagePerFolder;
    static final int MY_PERMISSION_REQUEST_READ_STORAGE  = 123;

    private static String scanSettingQuality;
    private static String scanSettingColor;
    private static String scanDocSettingDocument;
    private static String scanDocSettingSaveAsType;
    private static String scanPhotoSettingDocument;
    private String paperSize;
    private String paperType;
    private String printQuality;



    @Override
    public void onCreate() {
        super.onCreate();

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.DATA,MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        bucketName= new ArrayList<>();
        bucketData= new ArrayList<>();
        dirs = new ArrayList<>();
        noOfFiles = new ArrayList<>();
        imagePerFolder = new ArrayList<>();
        fName = null;


        
//        int readPermissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE);
//
//        if(readPermissionCheck!= PackageManager.PERMISSION_GRANTED){
//            Toast.makeText(getApplicationContext(), "reading external storage is not permitted", Toast.LENGTH_LONG).show();
//        }else{
//            ActivityCompat.requestPermissions(this.getApplicationContext(), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_READ_STORAGE);
//        }

        Cursor cursor = getContentResolver().query(uri, projection, null,null,null);



        if (cursor!=null){

            while(cursor.moveToNext()){

                if(!bucketName.contains(cursor.getString(1)))
                {
                    if(imagePerFolder.isEmpty()){}

                    if (cursor.getString(0).toLowerCase().endsWith(".jpg")||
                            cursor.getString(0).toLowerCase().endsWith(".jpeg")||
                            cursor.getString(0).toLowerCase().endsWith(".png"))
                    {


                        if(!bucketName.contains(cursor.getString(1))){
                            bucketName.add(cursor.getString(1));
                            bucketData.add(cursor.getString(0));
                        }
                    }
                }


            }
            cursor.close();
        }

        for(int i = 0; i<bucketData.size(); i++)
        {
            File file = new File(bucketData.get(i));
            String st = file.getParent();
            dirs.add(st);
            //Toast.makeText(this, bucketName.get(i), Toast.LENGTH_SHORT).show();
        }





        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .threadPoolSize(3)
                .diskCache(new UnlimitedDiskCache(getCacheDir()))
                .diskCacheExtraOptions(480, 320, null)
                //.tasksProcessingOrder(QueueProcessingType.LIFO)
                //.memoryCache(new WeakMemoryCache())
                .imageDecoder(new BaseImageDecoder(true))
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .build();
        ImageLoader.getInstance().init(configuration);


        //Toast.makeText(getApplicationContext(), String.valueOf(noOfFiles.get(0)), Toast.LENGTH_SHORT).show();
    }



    public ArrayList<String> getThumbData() {
        return thumbData;
    }

    public void setThumbData(ArrayList<String> thumbData2) {
        thumbData = thumbData2;
    }
    public void clearData(){
        thumbData.clear();
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getPaperSize() {
        if(paperSize == null){
            setPrintQuality("Best");
        }
        return paperSize;
    }

    public void setPaperSize(String paperSize) {
        this.paperSize = paperSize;
    }

    public String getPrintQuality() {
        if(printQuality == null){
            setPrintQuality("Best");
        }
        return printQuality;
    }

    public void setPrintQuality(String printQuality) {
        this.printQuality = printQuality;
    }

    public String getScanDocSettingSaveAsType() {
        return scanDocSettingSaveAsType;
    }

    public void setScanDocSettingSaveAsType(String scanDocSettingSaveAsType) {
        this.scanDocSettingSaveAsType = scanDocSettingSaveAsType;
    }

    public String getScanSettingQuality() {
        if(scanSettingQuality == null){
            setScanSettingQuality("High");
        }
        return scanSettingQuality;
    }

    public void setScanSettingQuality(String scanSettingQuality) {
        this.scanSettingQuality = scanSettingQuality;
    }

    public String getScanSettingColor() {
        return scanSettingColor;
    }

    public void setScanSettingColor(String scanSettingColor) {
        this.scanSettingColor = scanSettingColor;
    }

    public String getScanDocSettingDocument() {
        return scanDocSettingDocument;
    }

    public void setScanDocSettingDocument(String scanDocSettingDocument) {
        this.scanDocSettingDocument = scanDocSettingDocument;
    }

    public String getScanPhotoSettingDocument() {
        return scanPhotoSettingDocument;
    }

    public void setScanPhotoSettingDocument(String scanPhotoSettingDocument) {
        this.scanPhotoSettingDocument = scanPhotoSettingDocument;
    }
}
