package com.cebusqa.kodakverite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by SQA Cebu on 6/30/2016.
 */
public class CustomAdapterPrintUtil extends BaseAdapter {

    String[] result;
    Context context;
    int[] imgs;
    private static LayoutInflater inflater = null;

    public CustomAdapterPrintUtil (PU00_0000 pu00_0000, String[] wifiSetupItemList){
        result = wifiSetupItemList;
        //imgs = img;
        context = pu00_0000;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class Holder{
        TextView tv;
        //ImageView img;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        Holder holder = new Holder();

        View rowView;
        rowView = inflater.inflate(R.layout.print_utility_list, null);
        holder.tv= (TextView) rowView.findViewById(R.id.print_util_items);
        //holder.img = (ImageView) rowView.findViewById(R.id.iv_arrow);
        holder.tv.setText(result[i]);
        //holder.img.setImageResource(imgs[i]);

        return rowView;
    }
}

