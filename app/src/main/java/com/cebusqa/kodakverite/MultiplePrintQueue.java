package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.StringTokenizer;

/**
 * Created by Cebu SQA on 05/08/2016.
 */
public class MultiplePrintQueue extends Activity implements View.OnClickListener {
    ListView listViewPrintQueue;
    KodakVeriteApp kodakVeriteApp;
    static ArrayList<String> thmbs;
    static int progressStatus = 0;
    Thread t;
    Handler handler;
    PrintQueueAdapter adapter;
    String date;
    int cntr;
    ArrayList<ImageView> imList;

    //ProgressBar progressBar;

    //ProgressBar progressBar;
    ProgressBar  progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiple_print_queue);
        listViewPrintQueue = (ListView) findViewById(R.id.lvQueue);
        kodakVeriteApp = new KodakVeriteApp();
        thmbs = kodakVeriteApp.getThumbData();
        handler = new Handler();
        adapter = new PrintQueueAdapter(this, thmbs);
        listViewPrintQueue.setAdapter(adapter);
        imList = new ArrayList<>();



        //if(thmbs.isEmpty()) {thmbs = kodakVeriteApp.getThumbData();}


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        date = simpleDateFormat.format(calendar.getTime());
/*
        if(!thmbs.isEmpty()){

            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (progressStatus < 100){
                        progressStatus += 1;

                        try {
                            Thread.sleep(40);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar = (ProgressBar) listViewPrintQueue.getChildAt(0).findViewById(R.id.progressBar_2);

                                progressBar.setProgress(progressStatus);
                            }
                        });

                        t.interrupt();

                    }
                }
            });
            t.start();
            if(t.isInterrupted()){
                thmbs.remove(0);
                adapter.notifyDataSetChanged();
            }
        }
//*/
//        for(int i = 0; i<thmbs.size(); i++){
//            //ImageView imageView  = (ImageView) listViewPrintQueue.getChildAt(i).findViewById(R.id.cancelPrint);
//            //imList.add(imageView);
//           // imList.get(i).setOnClickListener(this);
//            Log.v("my activity", "anu");
//            Toast.makeText(getApplicationContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
//
//        }



        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;

                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.v("my activity", "top");
                            if(!thmbs.isEmpty()){
                                ProgressBar  progressBar = (ProgressBar) listViewPrintQueue.getChildAt(0).findViewById(R.id.progressBar_2);
                                progressBar.setProgress(progressStatus);

                                if (progressStatus == 100 ) {
                                    progressStatus = 0;
                                    thmbs.remove(0);
                                    adapter.notifyDataSetChanged();

                                }

                            }else {
                                t.interrupt();
                            }

                            Log.v("my activity", "bottom");
                        }

                    });

                }

                //t.interrupt();
                // finish();


                //finish();
//                Toast.makeText(getApplication(),"yes",Toast.LENGTH_SHORT).show();
//                progressStatus = 0;
//                t.interrupt();
                //t.interrupt();
                //Thread.currentThread().interrupt();
            }
        });
        t.start();

//        for (int i = 0; i<listViewPrintQueue.getCount(); i++){
//            final ProgressBar progressBar = (ProgressBar) listViewPrintQueue.getChildAt(i).findViewById(R.id.progressBar_2);
//            t = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while(progressStatus < 100){
//                        progressStatus+=1;
//
//                        try{
//                            Thread.sleep(30);
//                        }catch(InterruptedException e){
//                            e.printStackTrace();
//                        }
//
//                        new Handler().post(new Runnable() {
//                            @Override
//                            public void run() {
//                                progressBar.setProgress(progressStatus);
//                            }
//
//                        });
//                    } t.interrupt();
//                }
//            }); t.start();
        //ProgressBar progressBar = (ProgressBar) listViewPrintQueue.getChildAt(0).findViewById(R.id.progressBar_2);


//        for(int i = 0; i<thmbs.size(); i++){
//            Toast.makeText(getApplicationContext(), String.valueOf(thmbs.get(i)), Toast.LENGTH_SHORT).show();
//        }


    }

/*

    @Override
    protected void onResume() {
        super.onResume();



       */
/* t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;

                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.v("my activity", "top");
                            if(!thmbs.isEmpty()){
                                ProgressBar  progressBar = (ProgressBar) listViewPrintQueue.getChildAt(0).findViewById(R.id.progressBar_2);
                                progressBar.setProgress(progressStatus);

                                if (progressStatus == 100 ) {
                                    progressStatus = 0;
                                    thmbs.remove(0);
                                    adapter.notifyDataSetChanged();

                                }

                            }else {
                                t.interrupt();
                            }

                            Log.v("my activity", "bottom");
                        }

                    });

                }

                //t.interrupt();
               // finish();


                //finish();
//                Toast.makeText(getApplication(),"yes",Toast.LENGTH_SHORT).show();
//                progressStatus = 0;
//                t.interrupt();
                //t.interrupt();
                //Thread.currentThread().interrupt();
            }
        });
        t.start();*//*

//        for(int i =0 ; i<thmbs.size(); i++){
//            if(!t.isAlive()){
//                t.start();
//
//            }
//            Toast.makeText(getApplication(),String.valueOf(i),Toast.LENGTH_SHORT).show();
//        }
    }
*/

    @Override
    public void onClick(View v) {
        for (int i = 0; i<imList.size();i++){
            if(v.equals(imList.get(i))){
                imList.remove(i);
                thmbs.remove(i);
                adapter.notifyDataSetChanged();
            }
        }
    }

    public class PrintQueueAdapter extends BaseAdapter{

        Context mContext2;
        ArrayList<String> thumbsUri2;
        DisplayImageOptions dio;
        ImageLoader imageLoader2;

        PrintQueueAdapter(Context c, ArrayList<String> thumbsUri) {
            mContext2 = c;
            this.thumbsUri2 = thumbsUri;
            dio = new DisplayImageOptions.Builder()
                    .cacheOnDisk(true)
                    .decodingOptions(new BitmapFactory.Options())
                    .displayer(new SimpleBitmapDisplayer())
                    .build();
            imageLoader2 = ImageLoader.getInstance();


        }

        /**
         * How many items are in the data set represented by this Adapter.
         *
         * @return Count of items.
         */
        @Override
        public int getCount() {
            return thumbsUri2.size();
        }

        /**
         * Get the data item associated with the specified position in the data set.
         *
         * @param position Position of the item whose data we want within the adapter's
         *                 data set.
         * @return The data at the specified position.
         */
        @Override
        public Object getItem(int position) {
            return position;
        }

        /**
         * Get the row id associated with the specified position in the list.
         *
         * @param position The position of the item within the adapter's data set whose row id we want.
         * @return The id of the item at the specified position.
         */
        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * Get a View that displays the data at the specified position in the data set. You can either
         * create a View manually or inflate it from an XML layout file. When the View is inflated, the
         * parent View (GridView, ListView...) will apply default layout parameters unless you use
         * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
         * to specify a root view and to prevent attachment to the root.
         *
         * @param position    The position of the item within the adapter's data set of the item whose view
         *                    we want.
         * @param convertView The old view to reuse, if possible. Note: You should check that this view
         *                    is non-null and of an appropriate type before using. If it is not possible to convert
         *                    this view to display the correct data, this method can create a new view.
         *                    Heterogeneous lists can specify their number of view types, so that this View is
         *                    always of the right type (see {@link #getViewTypeCount()} and
         *                    {@link #getItemViewType(int)}).
         * @param parent      The parent that this view will eventually be attached to
         * @return A View corresponding to the data at the specified position.
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) mContext2.getSystemService(LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.multiple_print_single_row, parent, false);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.thumbs);
            ImageView cancel = (ImageView) convertView.findViewById(R.id.cancelPrint);
            TextView tv = (TextView) convertView.findViewById(R.id.dateOfPrint);

            //TextView textView = (TextView) convertView.findViewById(R.id.dateOfPrint);
            //ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar_2);
            //ImageView imageView1 = (ImageView) findViewById(R.id.cancelPrint);
            //BitmapFactory.decodeResource(getResources(),R.drawable.job_cancel);
            tv.setText(date + " 0" + String.valueOf(position + 1));
            imageLoader2.displayImage("file:///" + thumbsUri2.get(position), imageView, dio);
            cancel.setImageResource(R.drawable.job_cancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position==0){
                        progressStatus = 0;
                        //Toast.makeText(getApplicationContext(), "yes",Toast.LENGTH_SHORT).show();
                    }
                    thumbsUri2.remove(position);
                    adapter.notifyDataSetChanged();

                }
            });

            return convertView;
        }


    }
}
