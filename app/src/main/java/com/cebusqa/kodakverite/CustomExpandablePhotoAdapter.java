package com.cebusqa.kodakverite;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by anarte on 29/09/2016.
 */

public class CustomExpandablePhotoAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> scanPhotoTitle;
    private HashMap<String, List<String>> scanPhotoList;
    KodakVeriteApp kodakVeriteApp;

    public CustomExpandablePhotoAdapter(Context context, List<String> scanPhotoTitle, HashMap<String, List<String>> scanPhotoList){
        this.context = context;
        this.scanPhotoTitle = scanPhotoTitle;
        this.scanPhotoList = scanPhotoList;
        kodakVeriteApp = new KodakVeriteApp();
    }

    @Override
    public int getGroupCount() {
        return scanPhotoTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.scanPhotoList.get(this.scanPhotoTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.scanPhotoTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.scanPhotoList.get(this.scanPhotoTitle.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(groupPosition);

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_scan_photo_group, null);

        TextView text = (TextView) convertView.findViewById(R.id.list_scan_photo_title);
        text.setTypeface(null, Typeface.BOLD);
        text.setText(listTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String listText = (String) getChild(groupPosition, childPosition);

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_scan_photo_item, null);

        TextView textList = (TextView) convertView.findViewById(R.id.list_scan_photo_title);
        textList.setText(listText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
