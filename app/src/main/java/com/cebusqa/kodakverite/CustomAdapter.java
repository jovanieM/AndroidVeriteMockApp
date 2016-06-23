package com.cebusqa.kodakverite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class CustomAdapter extends BaseAdapter {

    String[] result;
    Context context;
    int[] imgs;
    private static LayoutInflater inflater = null;

    public CustomAdapter(WS00_000 ws00_000, String[] wifiSetupItemList){
        result = wifiSetupItemList;
        //imgs = img;
        context = ws00_000;
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
        rowView = inflater.inflate(R.layout.wifi_setup_list, null);
        holder.tv= (TextView) rowView.findViewById(R.id.tv_wifi_setup_item);
        //holder.img = (ImageView) rowView.findViewById(R.id.iv_arrow);
        holder.tv.setText(result[i]);
        //holder.img.setImageResource(imgs[i]);

        return rowView;
    }
}
