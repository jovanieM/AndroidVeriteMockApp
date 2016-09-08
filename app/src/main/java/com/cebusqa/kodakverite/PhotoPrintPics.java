package com.cebusqa.kodakverite;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.decode.ImageDecoder;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Cebu SQA on 29/06/2016.
 */
public class PhotoPrintPics extends AppCompatActivity {
    GridView gridView;
    static String folderPath;
    static String mFolder;
    ArrayList<String> picPaths = new ArrayList<>();
    ArrayList<String> selectedPic = new ArrayList<>();
    TextView mFolderDir, tvCancel, mCancelPrint, mPrintMulti, tv;
    TextView paperSize, paperType, printQuality;
    Button back, mGcp;
    RelativeLayout rel;
    static int counter = 0;
    static int counter2 = 0;
    boolean changeSettings = false;
    boolean multiplePrint = false;
    ArrayList<String> labelMem = new ArrayList<>();
    String st;
    RelativeLayout popUpLayout;
    ImageView iv_multiple, iv_printer, settingsIcon;
    KodakVeriteApp thumbsData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.photo_print_pics);
        mFolderDir = (TextView) findViewById(R.id.folder);
        gridView = (GridView) findViewById(R.id.gridView);
        mFolderDir.setText(mFolder);
        back = (Button) findViewById(R.id.back);
        mGcp = (Button) findViewById(R.id.gcp);
        iv_multiple = (ImageView) findViewById(R.id.ic_multiple);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvCancel.setVisibility(View.INVISIBLE);
        iv_printer = (ImageView) findViewById(R.id.ic_printer);
        iv_printer.setVisibility(View.INVISIBLE);
        popUpLayout = (RelativeLayout) findViewById(R.id.print_pop_up);
        mCancelPrint = (TextView) findViewById(R.id.cancelPrint);
        mPrintMulti = (TextView) findViewById(R.id.printMulti);
        settingsIcon = (ImageView) findViewById(R.id.scanSettingsIcon);
        thumbsData = new KodakVeriteApp();

        paperSize = (TextView) findViewById(R.id.paper_size);
        paperType = (TextView) findViewById(R.id.paper_type);
        printQuality = (TextView) findViewById(R.id.photo);



        File dir = new File(folderPath);
        File[] picFiles = dir.listFiles();
        for (File sFile : picFiles) {
            if (!sFile.isDirectory() && !sFile.isHidden()) {
                String sFilePath = sFile.getAbsolutePath();
                if (sFilePath.toLowerCase().endsWith(".jpg") ||
                        sFilePath.toLowerCase().endsWith(".jpeg") ||
                        sFilePath.toLowerCase().endsWith(".png") ) {
                    picPaths.add(sFilePath);
                }
                //Toast.makeText(this, sFilePath,Toast.LENGTH_SHORT).show();
            }
        }


        ImageAdapter adapter = new ImageAdapter(this, picPaths);
        gridView.setAdapter(adapter);

        mGcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     doPrint();

            /*    File fileImage = new File(String.valueOf(picPaths));
                Uri uri = Uri.fromFile(fileImage);
                Intent sendIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uri);
                sendIntent.setType("image*//*");
                startActivity(sendIntent);*/

            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridView.setEnabled(true);

                if (multiplePrint) {
                    // Toast.makeText(getApplication(),String.valueOf(view.getId()) , Toast.LENGTH_SHORT).show();
                    rel = (RelativeLayout) view.findViewById(R.id.rl_id);
                    st = String.valueOf(position);
                    if (labelMem.size() == 0) {
                        counter = 0;
                        counter2 = 0;
                    }

                    if (rel.getVisibility() == View.INVISIBLE) {
                        tv = (TextView) view.findViewById(R.id.textViewTemp);
                        selectedPic.add(picPaths.get(position));
                        labelMem.add(st);
                        rel.setVisibility(View.VISIBLE);

                        counter++;
                        tv.setText(String.valueOf(counter));
                    } else {

                        labelMem.remove(st);
                        selectedPic.remove(picPaths.get(position));
                        rel.setVisibility(View.INVISIBLE);


                        for (int i = 0; i < labelMem.size(); i++) {
                            //Toast.makeText(getApplication(), labelMem.get(i), Toast.LENGTH_SHORT).show();

                            counter2++;
                            tv = (TextView) gridView.getChildAt(Integer.parseInt(labelMem.get(i))).findViewById(R.id.textViewTemp);
                            tv.setText(String.valueOf(counter2));
                        }
                        counter = 0;
                        //pass the current number to counter variable
                        counter = counter2;
                        counter2 = 0;
                    }
//               TextView tv  = (TextView) gridView.getChildAt(position).findViewById(R.id.textViewTemp);
//                tv.setText("2");
                    //gridView.getCount();
                    //Toast.makeText(getApplication(),String.valueOf(gridView.getCount()), Toast.LENGTH_SHORT).show();
                }
                /** else if the multiprint is false**/
                else {

                    for (int i = 0; i < picPaths.size(); i++) {
                        if (i == position) {
                            FlickPrint.fullImage = picPaths.get(position);

                        }
                    }
                    Intent intent = new Intent(getApplication(), FlickPrint.class);
                    startActivity(intent);
                }
            }
        });

        /**  temporarily omitted, do not delete !!
         gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        for(int i = 0; i<picPaths.size() ; i++){
        if(i==position){
        FlickPrint.fullImage = picPaths.get(position);
        }
        }
        Intent intent = new Intent(getApplication(), FlickPrint.class);
        startActivity(intent);
        }
        });
         **/
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /* gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        iv_multiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0;
                counter2 = 0;
                iv_multiple.setVisibility(View.INVISIBLE);

                tvCancel.setVisibility(View.VISIBLE);
                iv_printer.setVisibility(View.VISIBLE);
                multiplePrint = true;
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetData();

            }
        });
        iv_printer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!labelMem.isEmpty()) {
                    gridView.setEnabled(false);

                    popUpLayout.setVisibility(View.VISIBLE);
                    popUpLayout.bringToFront();

                    paperSize.setText(thumbsData.getPaperSize());
                    paperType.setText(thumbsData.getPaperType());
                    printQuality.setText(thumbsData.getPrintQuality());

                    thumbsData.setThumbData(selectedPic);
//                    for(int i = 0 ; i < labelMem.size(); i++){
//
//                    }
                }
                //Toast.makeText(getApplication(),"yes", Toast.LENGTH_SHORT).show();

            }
        });
        mCancelPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thumbsData.clearData();
                gridView.setEnabled(true);
                popUpLayout.setVisibility(View.GONE);
            }
        });

        settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSettings = true;
                startActivity(new Intent(getApplicationContext(), DS_print.class));
            }
        });
        mPrintMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for (int i = 0; i< selectedPic.size(); i++){
//                    //
//                    thumbsData.thumbData.add(selectedPic.get(i));
//                }


                startActivity(new Intent(getApplicationContext(), MultiplePrintQueue.class));

                //finish();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void doPrint() {

        PrintManager printManager = (PrintManager) PhotoPrintPics.this.getSystemService(Context.PRINT_SERVICE);

        printManager.print("any", new MyPrintDocumentAdapter(this), null);


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(changeSettings){
            changeSettings = false;
        }else{
            resetData();
        }

    }

    public void resetData() {
        counter = 0;
        counter2 = 0;
        tvCancel.setVisibility(View.INVISIBLE);
        iv_printer.setVisibility(View.INVISIBLE);

        iv_multiple.setVisibility(View.VISIBLE);

        multiplePrint = false;

        if (!labelMem.isEmpty()) {
            for (int i = 0; i < labelMem.size(); i++) {
                //Toast.makeText(getApplication(), labelMem.get(i), Toast.LENGTH_SHORT).show();
                RelativeLayout relativeLayout = (RelativeLayout) gridView.getChildAt(Integer.parseInt(labelMem.get(i))).findViewById(R.id.rl_id);
                relativeLayout.setVisibility(View.GONE);

            }
        }

        gridView.setEnabled(true);
        popUpLayout.setVisibility(View.GONE);
        labelMem.clear();

        selectedPic.clear();
    }


    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        DisplayImageOptions options;
        ImageLoader imageLoader;
        ArrayList<String> list;


        ImageAdapter(Context c, ArrayList<String> paths) {
            mContext = c;
            list = paths;
            options = new DisplayImageOptions.Builder()
                    .cacheOnDisk(true)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                    .displayer(new SimpleBitmapDisplayer())
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .handler(new Handler())
                    .build();
            imageLoader = ImageLoader.getInstance();

        }

        public int getCount() {
            return picPaths.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return 0;
        }

//        class ViewHolder {
//            ImageView pictures;
//            RelativeLayout relativeLayout;
//
//            ViewHolder(View v) {
//                pictures = (ImageView) v.findViewById(R.id.picture);
//                relativeLayout = (RelativeLayout) v.findViewById(R.id.rl_id);
//
//            }
//        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {


            //View row = convertView;
            //ViewHolder holder;

            //if (row == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.single_picture, parent, false);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.picture);
            RelativeLayout relativeLayout = (RelativeLayout) convertView.findViewById(R.id.rl_id);
            //TextView tv = (TextView) convertView.findViewById(R.id.textViewTemp);
            if (!labelMem.isEmpty()) {
                for (int i = 0; i < labelMem.size(); i++) {
                    if (Integer.parseInt(labelMem.get(i)) == position) {
                        relativeLayout.setVisibility(View.VISIBLE);
                        TextView tv2 = (TextView) relativeLayout.findViewById(R.id.textViewTemp);
                        tv2.setText(String.valueOf(i + 1));
                    }
                }
            }
            // relativeLayout.setVisibility(View.VISIBLE);

            //holder = new ViewHolder(row);
            //row.setTag(holder);

            // } else {
            //   holder = (ViewHolder) row.getTag();

            //}
            //Picasso.with(getApplicationContext()).load("file:///"+picPaths.get(position)).into(holder.pictures);
            
            imageLoader.displayImage("file:///" + picPaths.get(position), imageView, options);
            //imageView.setImageBitmap(ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(picPaths.get(position)), 200, 150));
            return convertView;
        }
    }
}
