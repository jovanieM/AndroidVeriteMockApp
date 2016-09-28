package com.cebusqa.kodakverite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by anarte on 26/09/2016.
 */

public class QuickPrintAdapter extends BaseAdapter{

    private Context mContext;
    private String[] result;
    private int[] imgid;
    private static LayoutInflater inflater = null;
    KodakVeriteApp kodakVeriteApp;


    QuickPrintAdapter (Context context, String[] itemName, int[] itemCheck){
        result = itemName;
        mContext = context;
        imgid = itemCheck;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        kodakVeriteApp = new KodakVeriteApp();
    }

    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        //((ListView)parent).setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //((ListView)parent).setItemChecked(position, true);


        convertView = inflater.inflate(R.layout.quick_print_item, null);
        TextView tv = (TextView) convertView.findViewById(R.id.tv_list);
        ImageView iv = (ImageView) convertView.findViewById(R.id.iv_check);
        tv.setText(result[position]);

        if(result[position].equals(kodakVeriteApp.getQuickPrintItem())){
           iv.setSelected(true);
        }else{
            iv.setSelected(false);
        }

        return convertView;
    }
}