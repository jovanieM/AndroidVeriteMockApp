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

    Context mContext;
    String[] result;
    int[] imgid;
    private static LayoutInflater inflater = null;
    KodakVeriteApp kodakVeriteApp;


    public QuickPrintAdapter (Context context, String[] itemName, int[] itemCheck){
        result = itemName;
        mContext = context;
        imgid = itemCheck;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    public class ItemHolder{
        TextView tv;
        ImageView iv;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ItemHolder itemHolder;
        View rowView = convertView;

        if(rowView == null){
        rowView = inflater.inflate(R.layout.quick_print_item, null);
            itemHolder = new ItemHolder();
            itemHolder.tv = (TextView) rowView.findViewById(R.id.tv_list);
            itemHolder.iv = (ImageView) rowView.findViewById(R.id.iv_check);
        rowView.setTag(itemHolder);}
        else {
            itemHolder = (ItemHolder) rowView.getTag();
        }

        itemHolder.tv.setText(result[position]);
        //itemHolder.iv.setImageResource(imgid[position]);

        if(result[position].equals(kodakVeriteApp.getQuickPrintItem())){
            itemHolder.iv.setSelected(true);
        }else{
            itemHolder.iv.setSelected(false);
        }

        //itemHolder.iv.setVisibility(View.INVISIBLE);

        //if(result[position].equals(kodakVeriteApp.getQuickPrintItem()))
        //    itemHolder.iv.setSelected(true);


        /*rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "You Clicked "+result[position], Toast.LENGTH_SHORT).show();
                //itemHolder.iv.setImageResource(imgid[position]);
                switch (position){
                    case 0:
                        itemHolder.iv.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        itemHolder.iv.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        });*/

        return rowView;
    }
}