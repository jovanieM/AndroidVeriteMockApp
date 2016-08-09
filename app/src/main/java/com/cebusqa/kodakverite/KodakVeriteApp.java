package com.cebusqa.kodakverite;

import android.app.Application;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
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
    int counter = 0;
    static String fName;

    @Override
    public void onCreate() {
        super.onCreate();

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.DATA,MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        bucketName= new ArrayList<>();
        bucketData= new ArrayList<>();
        dirs = new ArrayList<>();
        noOfFiles = new ArrayList<>();
        fName = null;


        Cursor cursor = getContentResolver().query(uri, projection, null,null,null);


        if (cursor!=null){

            while(cursor.moveToNext()){

                if(!bucketName.contains(cursor.getString(1)))
                {
                    if (cursor.getString(0).toLowerCase().endsWith(".jpg")||cursor.getString(0).toLowerCase().endsWith(".jpeg")||cursor.getString(0).toLowerCase().endsWith(".png"))
                    {


                        if(!bucketName.contains(cursor.getString(1))){
                            bucketName.add(cursor.getString(1));
                            bucketData.add(cursor.getString(0));
                        }
//                        if(dirs.contains(cursor.getString(1))){
//                            counter++;
//                        }


//                        bucketName.add(cursor.getString(0));
//                        bucketData.add(cursor.getString(2));

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
            //Toast.makeText(this, st, Toast.LENGTH_SHORT).show();
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
}
