package com.cebusqa.kodakverite;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * Created by anarte on 29/09/2016.
 */

public class CustomExpandablePhotoAdapter extends BaseExpandableListAdapter {

    private Context context;
    private String[] scanPhotoTitle;
    private String[][] scanPhotoList;
    KodakVeriteApp kodakVeriteApp;

    public CustomExpandablePhotoAdapter(Context context, String[] scanPhotoTitle, String[][] scanPhotoList){
        this.context = context;
        this.scanPhotoTitle = scanPhotoTitle;
        this.scanPhotoList = scanPhotoList;
        kodakVeriteApp = new KodakVeriteApp();
    }

    @Override
    public int getGroupCount() {
        return scanPhotoTitle.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.scanPhotoList[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.scanPhotoTitle[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.scanPhotoList[groupPosition][childPosition];
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

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_scan_photo_group, null);
        }
        TextView selectedChild = (TextView) convertView.findViewById(R.id.sub_menu_photo);
        TextView text = (TextView) convertView.findViewById(R.id.list_scan_photo_title);
        text.setTypeface(null, Typeface.BOLD);
        text.setText(listTitle);

        if(groupPosition == 0) selectedChild.setText(kodakVeriteApp.getScanPhotoSettingQuality());
        if(groupPosition == 1) selectedChild.setText(kodakVeriteApp.getScanPhotoSettingColor());
        if(groupPosition == 2) selectedChild.setText(kodakVeriteApp.getScanPhotoSettingDocument());


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String listText = (String) getChild(groupPosition, childPosition);

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_scan_photo_item, null);

        if(groupPosition == 0 && String.valueOf(getChild(groupPosition, childPosition)).equals(kodakVeriteApp.getScanPhotoSettingQuality())){
            convertView.findViewById(R.id.check_mark_photo).setSelected(true);
        }

        if(groupPosition == 1 && String.valueOf(getChild(groupPosition, childPosition)).equals(kodakVeriteApp.getScanPhotoSettingColor()))
            convertView.findViewById(R.id.check_mark_photo).setSelected(true);

        if(groupPosition == 2 )
            convertView.findViewById(R.id.check_mark_photo).setSelected(true);





        TextView textList = (TextView) convertView.findViewById(R.id.expanded_list_photo_item);
        textList.setText(listText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
