package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Cebu SQA on 29/06/2016.
 */
public class PhotoPrintPics extends Activity {
    GridView gridView;
    static String folderPath;
    static String mFolder;
    ArrayList<String> picPaths = new ArrayList<>();
    //static int index;
    TextView mFolderDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_print_pics);
        mFolderDir = (TextView) findViewById(R.id.folder);
        gridView = (GridView) findViewById(R.id.gridView);
        mFolderDir.setText(mFolder);
        //Toast.makeText(this, folderPath,Toast.LENGTH_SHORT).show();


        File dir = new File(folderPath);
        File[] picFiles = dir.listFiles();
        for(File sFile : picFiles){
            if(!sFile.isDirectory()&& !sFile.isHidden()){
                String sFilePath = sFile.getAbsolutePath();
                if(sFilePath.toLowerCase().endsWith(".jpg")||sFilePath.toLowerCase().endsWith(".jpeg")||sFilePath.toLowerCase().endsWith(".png")) {
                    picPaths.add(sFilePath);
                }
                //Toast.makeText(this, sFilePath,Toast.LENGTH_SHORT).show();
            }
        }
        ImageAdapter adapter  = new ImageAdapter(this, picPaths);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0; i<picPaths.size() ; i++){
                    if(i==position){
                        FlickPrint.fullImage = picPaths.get(position);

                    }
                }
                Intent intent = new Intent(getApplication(), FlickPrint.class);
                startActivity(intent);
            }
        });
/*        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0; i<picPaths.size() ; i++){
                    if(i==position){
                        FullScreen.fullImage = picPaths.get(position);

                    }
                }
                Intent intent = new Intent(PicturesGrid.this, FullScreen.class);
                startActivity(intent);
            }
        });*/

    }
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        DisplayImageOptions options;
        ImageLoader imageLoader = ImageLoader.getInstance();
        ArrayList<String>list;

        ImageAdapter(Context c, ArrayList<String> paths) {
            mContext = c;
            list = paths;
            options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .displayer(new SimpleBitmapDisplayer())
                    .build();
            imageLoader = ImageLoader.getInstance();

        }

        public int getCount() {
            return picPaths.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        class ViewHolder{
            ImageView pictures;
            ViewHolder(View v){
                pictures = (ImageView) v.findViewById(R.id.picture);
            }
        }



        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder;


            if (row == null) {

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_picture, parent, false);
                holder = new ViewHolder(row);
                row.setTag(holder);

            } else {
                holder = (ViewHolder) row.getTag();

            }


            imageLoader.displayImage("file:///"+picPaths.get(position), holder.pictures, options);
            //imageView.setImageBitmap(ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(picPaths.get(position)), 200, 150));
            return row;
        }
    }
}
