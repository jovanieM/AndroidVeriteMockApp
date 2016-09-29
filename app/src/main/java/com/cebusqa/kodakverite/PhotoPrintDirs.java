package com.cebusqa.kodakverite;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.jar.Manifest;

/**
 * Created by Cebu SQA on 29/06/2016.
 */
public class PhotoPrintDirs extends Activity {
    ListView mlistView;
    Button back;
    ImageLoaderConfiguration configuration;
    ImageFetcher ifetch;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_print_dirs);
        ifetch = new ImageFetcher(getApplicationContext());

        ifetch.loadImages();

        configuration = new ImageLoaderConfiguration.Builder(getApplicationContext())
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



        mlistView = (ListView) findViewById(R.id.pListView);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        MyAdapter adapter = new MyAdapter(this, ImageFetcher.bucketName,ImageFetcher.bucketData, ImageFetcher.noOfFiles);

        Log.v("Photo",String.valueOf(ImageFetcher.bucketName.size())+"/"+ String.valueOf(ImageFetcher.bucketData.size())+"/"+String.valueOf(ImageFetcher.noOfFiles.size()));


        mlistView.setAdapter(adapter);

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 0; i < ImageFetcher.bucketName.size(); i++) {
                    if (i == position) {
                        PhotoPrintPics.folderPath = ImageFetcher.dirs.get(position);
                        PhotoPrintPics.mFolder = ImageFetcher.bucketName.get(position);
                    }
                }
                startActivity(new Intent(PhotoPrintDirs.this, PhotoPrintPics.class));
            }
        });


    }


    class MyAdapter extends ArrayAdapter<String> {

        //fields
        private Context context;
        ArrayList<String> images;
        ArrayList<String> folderArray;
        ArrayList<String> num;
        private DisplayImageOptions options;
        ImageLoader imageLoader;

        //constructor
        MyAdapter(Context c, ArrayList<String> folders, ArrayList<String> image, ArrayList<String> noOfFiles){
            super(c, R.layout.photo_print_row,R.id.pFoldername,folders);
            this.context = c;
            this.images = image;
            this.folderArray = folders;
            this.num = noOfFiles;

            options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .decodingOptions(new BitmapFactory.Options())
                    .displayer(new SimpleBitmapDisplayer())
                    .build();
            imageLoader = ImageLoader.getInstance();


        }

        //inner class
        class MyViewHolder {
            ImageView myImage;
            TextView myTextView;
            TextView numOfImages;

            MyViewHolder(View v){
                myImage = (ImageView) v.findViewById(R.id.pImageView);
                myTextView = (TextView) v.findViewById(R.id.pFoldername);
                numOfImages = (TextView) v.findViewById(R.id.numOfImages);
            }
        }

        //method
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;
            MyViewHolder holder;

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.photo_print_row, parent, false);
                holder = new MyViewHolder(row);
                row.setTag(holder);
            } else {
                holder = (MyViewHolder) row.getTag();
            }

            // Toast.makeText(Print.this, images.get(position), Toast.LENGTH_LONG).show();
            imageLoader.displayImage("file:///" + images.get(position), holder.myImage, options);
            holder.myTextView.setText(folderArray.get(position));
            holder.numOfImages.setText("(" + num.get(position) + ")");

            return row;
        }
    }

}
