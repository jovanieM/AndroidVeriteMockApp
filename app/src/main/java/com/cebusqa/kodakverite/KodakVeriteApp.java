package com.cebusqa.kodakverite;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Cebu SQA on 27/06/2016.
 */
public class KodakVeriteApp extends Application {
    static int currentStatusValue = 0;
    static boolean airprintPrvState = false;
    static boolean gcpPrevState = false;

    static ArrayList<String> thumbData;

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

    private String TAG = "PermisssionDemo";
    final private int RECORD_REQUEST_CODE = 123;
    static byte[] bitmapData;

    private static String quickPrint;

    @Override
    public void onCreate() {
        super.onCreate();
        fName = null;


//        int readPermissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE);
//
//        if(readPermissionCheck!= PackageManager.PERMISSION_GRANTED){
//            Toast.makeText(getApplicationContext(), "reading external storage is not permitted", Toast.LENGTH_LONG).show();
//        }else{
//            ActivityCompat.requestPermissions(this.getApplicationContext(), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_READ_STORAGE);
//        }


//


        //Toast.makeText(getApplicationContext(), String.valueOf(noOfFiles.get(0)), Toast.LENGTH_SHORT).show();
    }


    public ArrayList<String> getThumbData() {
        return thumbData;
    }

    public void setThumbData(ArrayList<String> thumbData2) {
        thumbData = thumbData2;
    }

    public void clearData() {
        if (thumbData != null)
            thumbData.clear();

    }

    public void clearByteData() {
        if (bitmapData != null) bitmapData = null;
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
            setPrintQuality("Normal");
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
//-----------------------------------------------------------------------------------------------------
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
//----------------------------------------------------------------------------------------------------------------------------
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

    public String getCopyPaperSize() {
        if (copyPaperSize == null) {
            setCopyPaperSize("Letter");
        }
        return copyPaperSize;
    }

    public void setCopyPaperType(String copyPaperType) {
        KodakVeriteApp.copyPaperType = copyPaperType;
    }

    public String getCopyPaperType() {
        if (copyPaperType == null) {
            setCopyPaperType("Plain");
        }
        return copyPaperType;
    }

    public void setCopyQuality(String copyQuality) {
        KodakVeriteApp.copyQuality = copyQuality;
    }

    public String getCopyQuality() {
        if (copyQuality == null) {
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

    public byte[] getBitmapData() {
        return bitmapData;
    }

    public void setBitmapData(byte[] bitmap) {
        bitmapData = bitmap;
    }

    public void setQuickPrintItem(String quickPrintItem) {
        KodakVeriteApp.quickPrint = quickPrintItem;
    }

    public String getQuickPrintItem() {
        if (quickPrint == null) {
            setQuickPrintItem("Photo 4x6 in. Borderless");
        }
        return quickPrint;
    }
}
