package com.cebusqa.kodakverite;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Cebu SQA on 21/06/2016.
 */
public class SaveToPictures {

    String MEDIA_MOUNTED = "mounted";
    String diskState = Environment.getExternalStorageState();
    FileOutputStream fos = null;
    public void saveFile(Bitmap bitmap, String fileName) {

        if (diskState.equals(MEDIA_MOUNTED)) {

            File pictureFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File filePicture = new File(pictureFolder, fileName+".jpg");

            try {
                fos = new FileOutputStream(filePicture);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
