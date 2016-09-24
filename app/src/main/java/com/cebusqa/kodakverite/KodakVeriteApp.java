package com.cebusqa.kodakverite;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.support.v4.content.ContextCompat;



import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Cebu SQA on 27/06/2016.
 */
public class KodakVeriteApp extends Application {
    static int currentStatusValue = 0;
    static boolean airprintPrvState = false;
    static boolean gcpPrevState = false;
    static ArrayList<String> bucketName;
    static ArrayList<String> bucketData;
    static ArrayList<String> dirs;
    static ArrayList<String> thumbData;
    static ArrayList<String> noOfFiles;
    static String fName;

    static ArrayList<String> imagePerFolder;
    static int count = 0;
    static final int MY_PERMISSION_REQUEST_READ_STORAGE = 123;

//    ArrayList<Integer> imagePerFolder;


    private static String scanSettingQuality;
    private static String scanSettingColor;
    private static String scanDocSettingDocument;
    private static String scanDocSettingSaveAsType;

    private static String scanPhotoSettingDocument;
    private static String scanPhotoSettingQuality;
    private static String scanPhotoSettingColor;

    //for Print Settings
    private static String paperSize;
    private static String paperType;
    private static String printQuality;
    private static String printCopies;
    private static String printColor;

    //for Copy Settings
    private static String copyResize;
    private static String copyColor;
    private static String copyPaperSize;
    private static String copyPaperType;
    private static String pagesPerSide;
    private static String copyQuality;
    private static String directTime;


<<<<<<< HEAD

=======
>>>>>>> 5b11daa843b3b3b8c36ad201c1a351c175fc472e
    private String TAG = "PermisssionDemo";
    final private int RECORD_REQUEST_CODE = 123;

    public Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    public String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

    private File[] dirPath;
    static byte[] bitmapData;

    public byte[] getBitmapData() {
        return bitmapData;
    }


    public void setBitmapData(byte[] bitmap) {
        bitmapData = bitmap;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        int number;
        bucketName = new ArrayList<>();
        bucketData = new ArrayList<>();
        dirs = new ArrayList<>();
        noOfFiles = new ArrayList<>();

        fName = null;

//        int readPermissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE);
//
//        if(readPermissionCheck!= PackageManager.PERMISSION_GRANTED){
//            Toast.makeText(getApplicationContext(), "reading external storage is not permitted", Toast.LENGTH_LONG).show();
//        }else{
//            ActivityCompat.requestPermissions(this.getApplicationContext(), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_READ_STORAGE);
//        }


        int accessStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (accessStoragePermission != PackageManager.PERMISSION_GRANTED) {

            Log.i(TAG, "Permission to read denied");
            return;
        }

        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                if (!bucketName.contains(cursor.getString(1))) {
                    if (cursor.getString(0).toLowerCase().endsWith(".jpg") ||
                            cursor.getString(0).toLowerCase().endsWith(".jpeg") ||
                            cursor.getString(0).toLowerCase().endsWith(".png")) {
                        if (!bucketName.contains(cursor.getString(1))) {
                            bucketName.add(cursor.getString(1));
                            bucketData.add(cursor.getString(0));

                            number = 0;
                            File pathName = new File(cursor.getString(0));
                            String sdPath = pathName.getParent();
                            dirPath = new File(sdPath).listFiles();

                            for (int y = 0; y < dirPath.length; y++) {

                                if (dirPath[y].isFile() && dirPath[y].getName().endsWith(".png") ||
                                        dirPath[y].getName().endsWith(".jpg") ||
                                        dirPath[y].getName().endsWith(".jpeg")) {
                                    number++;
                                }
                            }
                            noOfFiles.add(String.valueOf(number));
                        }

                    }
                }
            }
            cursor.close();
        }


        for (int i = 0; i < bucketData.size(); i++) {
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
        ImageLoader.getInstance().

        init(configuration);
        //Toast.makeText(getApplicationContext(), String.valueOf(noOfFiles.get(0)), Toast.LENGTH_SHORT).show();
    }

    public ArrayList<String> getThumbData() {
        return thumbData;
    }

    public void setThumbData(ArrayList<String> thumbData2) {
        thumbData = thumbData2;
    }

    public void clearData() {
        thumbData.clear();
        bitmapData = null;
    }

    public String getPaperType() {
        if (paperType == null) {
            setPaperType("Glossy Photo");
        }
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getPrintColor() {
        if (printColor == null) {
            setPrintColor("Color");
        }
        return printColor;
    }

    public void setPrintColor(String printColor) {
        this.printColor = printColor;
    }


    public String getPaperSize() {
        if (paperSize == null) {
            setPaperSize("4x6 in. Borderless");
        }
        return paperSize;
    }

    public void setPaperSize(String paperSize) {
        this.paperSize = paperSize;
    }

    public String getPrintQuality() {
        if (printQuality == null) {
            setPrintQuality("Best");
        }
        return printQuality;
    }

    public void setPrintQuality(String printQuality) {
        this.printQuality = printQuality;
    }

    public String getPrintCopies() {
        if (printCopies == null) {
            setPrintCopies("1");
        }
        return printCopies;
    }

    public void setPrintCopies(String printCopies) {
        this.printCopies = printCopies;
    }

    public String getQuickPrint() {
        if (printCopies == null) {
            setPrintCopies("1");
        }
        return printCopies;
    }

    public void setQuickPrint(String printCopies) {
        this.printCopies = printCopies;
    }

    public String getScanDocSettingSaveAsType() {
        if (scanDocSettingSaveAsType == null) {
            setScanDocSettingSaveAsType("PDF");
        }
        return scanDocSettingSaveAsType;
    }

    public void setScanDocSettingSaveAsType(String scanDocSettingSaveAsType) {
        this.scanDocSettingSaveAsType = scanDocSettingSaveAsType;
    }

    public String getScanSettingQuality() {
        if (scanSettingQuality == null) {
            setScanSettingQuality("High");
        }
        return scanSettingQuality;
    }

    public void setScanSettingQuality(String scanSettingQuality) {
        this.scanSettingQuality = scanSettingQuality;
    }


    public String getScanSettingColor() {
        if (scanSettingColor == null) {
            setScanSettingColor("Color");
        }
        return scanSettingColor;
    }

    public void setScanSettingColor(String scanSettingColor) {
        this.scanSettingColor = scanSettingColor;
    }


    public String getScanDocSettingDocument() {
        if (scanDocSettingDocument == null) {
            setScanDocSettingDocument("Text/Graphics");
        }
        return scanDocSettingDocument;
    }

    public void setScanDocSettingDocument(String scanDocSettingDocument) {
        this.scanDocSettingDocument = scanDocSettingDocument;
    }


    public String getScanPhotoSettingDocument() {
        if (scanPhotoSettingDocument == null) {
            setScanPhotoSettingDocument("Photo");
        }
        return scanPhotoSettingDocument;
    }

    public void setScanPhotoSettingDocument(String scanPhotoSettingDocument) {
        this.scanPhotoSettingDocument = scanPhotoSettingDocument;
    }

    public String getScanPhotoSettingColor() {
        if (scanPhotoSettingColor == null) {
            setScanPhotoSettingColor("Color");
        }
        return scanPhotoSettingColor;
    }

    public void setScanPhotoSettingColor(String scanPhotoSettingColor) {
        this.scanPhotoSettingColor = scanPhotoSettingColor;
    }

    public String getScanPhotoSettingQuality() {
        if (scanPhotoSettingQuality == null) {
            setScanPhotoSettingQuality("Normal");
        }
        return scanPhotoSettingQuality;
    }

    public void setScanPhotoSettingQuality(String scanPhotoSettingQuality) {
        this.scanPhotoSettingQuality = scanPhotoSettingQuality;
    }


    public void setPagesPerSide(String pagesPerSide) {
        this.pagesPerSide = pagesPerSide;
    }

    public String getPagesPerSide() {
        if (pagesPerSide == null) {
            setPagesPerSide("One");
        }
        return pagesPerSide;
    }

    public void setCopyResize(String copyResize) {
        KodakVeriteApp.copyResize = copyResize;
    }

    public String getCopyResize() {
        if (copyResize == null) {
            setCopyResize("100% Default");
        }
        return copyResize;
    }

    public void setCopyColor(String copyColor) {
        KodakVeriteApp.copyColor = copyColor;
    }

    public String getCopyColor() {
        if (copyColor == null) {
            setCopyColor("Color");
        }
        return copyColor;
    }

    public void setCopyPaperSize(String copyPaperSize) {
        KodakVeriteApp.copyPaperSize = copyPaperSize;
    }

<<<<<<< HEAD
    public String getCopyPaperSize(){
        if (copyPaperSize == null){
=======
    public String getCopyPaperSize() {
        if (copyPaperSize == "null") {
>>>>>>> 5b11daa843b3b3b8c36ad201c1a351c175fc472e
            setCopyPaperSize("Letter");
        }
        return copyPaperSize;
    }

    public void setCopyPaperType(String copyPaperType) {
        KodakVeriteApp.copyPaperType = copyPaperType;
    }

<<<<<<< HEAD
    public String getCopyPaperType () {
        if (copyPaperType == null){
=======
    public String getCopyPaperType() {
        if (copyPaperType == "null") {
>>>>>>> 5b11daa843b3b3b8c36ad201c1a351c175fc472e
            setCopyPaperType("Plain");
        }
        return copyPaperType;
    }

    public void setCopyQuality(String copyQuality) {
        KodakVeriteApp.copyQuality = copyQuality;
    }

<<<<<<< HEAD
    public String getCopyQuality () {
        if (copyQuality == null){
=======
    public String getCopyQuality() {
        if (copyQuality == "null") {
>>>>>>> 5b11daa843b3b3b8c36ad201c1a351c175fc472e
            setCopyQuality("Text");
        }
        return copyQuality;
    }

    public void setDirectTime(String directTime) {
        this.directTime = directTime;
    }

    public String getDirectTime() {
        if (directTime == null) {
            setDirectTime("5 min");
        }
        return directTime;
    }
}
