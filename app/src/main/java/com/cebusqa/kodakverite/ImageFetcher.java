package com.cebusqa.kodakverite;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by jmolas on 28/09/2016.
 */

class ImageFetcher {

    static ArrayList<String> bucketName;
    static ArrayList<String> bucketData;
    static ArrayList<String> dirs;
    static ArrayList<String> noOfFiles;

    private File[] dirPath;
    Cursor cursor ;

    ImageFetcher(Context c){
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        cursor = c.getContentResolver().query(uri, projection, null, null, null);
        bucketName = new ArrayList<>();
        bucketData = new ArrayList<>();
        dirs = new ArrayList<>();
        noOfFiles = new ArrayList<>();

    }
    public void loadImages() {
        int number;


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
    }


}
