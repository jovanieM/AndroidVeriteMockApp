package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;

/**
 * Created by Cebu SQA on 29/06/2016.
 */
public class PhotoPrintDirs extends Activity {
    ListView mlistView;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_print_dirs);
        mlistView = (ListView) findViewById(R.id.pListView);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //KodakVeriteApp.getInstance();
//        int size = KodakVeriteApp.bucketName.size();
//        Toast.makeText(this, String.valueOf(size), Toast.LENGTH_SHORT).show();
        //listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, KodakVeriteApp.noOfFiles));
        MyAdapter adapter = new MyAdapter(this, KodakVeriteApp.bucketName, KodakVeriteApp.bucketData);

        mlistView.setAdapter(adapter);

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 0; i < KodakVeriteApp.bucketName.size(); i++) {
                    if (i == position) {
                        PhotoPrintPics.folderPath = KodakVeriteApp.dirs.get(position);
                        PhotoPrintPics.mFolder = KodakVeriteApp.bucketName.get(position);
                    }


                }
                startActivity(new Intent(PhotoPrintDirs.this, PhotoPrintPics.class));
            }
        });


    }

    class MyAdapter extends ArrayAdapter<String>{

        //fields
        private Context context;
        ArrayList<String> images;
        ArrayList<String> folderArray;
        private DisplayImageOptions options;
        ImageLoader imageLoader;

        //constructor
        MyAdapter(Context c, ArrayList<String> folders,  ArrayList<String>image){
            super(c, R.layout.photo_print_row,R.id.pFoldername,folders);
            this.context = c;
            this.images = image;
            this.folderArray = folders;
            options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .decodingOptions(new BitmapFactory.Options())
                    .displayer(new SimpleBitmapDisplayer())
                    .build();
            imageLoader = ImageLoader.getInstance();


        }
        //inner class
        class MyViewHolder{
            ImageView myImage;
            TextView myTextView;
            MyViewHolder(View v){
                myImage = (ImageView) v.findViewById(R.id.pImageView);
                myTextView = (TextView) v.findViewById(R.id.pFoldername);
            }
        }

        //method
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row =convertView;
            MyViewHolder holder;

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.photo_print_row, parent, false);
                holder = new MyViewHolder(row);
                row.setTag(holder);
            }else{
                holder = (MyViewHolder) row.getTag();
            }

            // Toast.makeText(Print.this, images.get(position), Toast.LENGTH_LONG).show();
            imageLoader.displayImage("file:///"+images.get(position), holder.myImage, options);
            holder.myTextView.setText(folderArray.get(position));
            return row;

        }
    }
}
