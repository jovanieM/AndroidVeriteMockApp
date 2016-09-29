package com.cebusqa.kodakverite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * Created by anarte on 28/09/2016.
 */

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private String[] expandableTitle;
    private String[][] expandableList;
    KodakVeriteApp kodakVeriteApp;

    public CustomExpandableListAdapter(Context context, String[] expandableTitle, String[][] expandableList){
        this.context = context;
        this.expandableTitle = expandableTitle;
        this.expandableList = expandableList;
        kodakVeriteApp = new KodakVeriteApp();
    }

    @Override
    public int getGroupCount() {
        return expandableTitle.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return expandableList[groupPosition].length;
       // return expandableList.get(expandableTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return expandableTitle[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return expandableList[groupPosition][childPosition];
       // return expandableList.get(expandableTitle.get(groupPosition)).get(childPosition);
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
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_scan_document_group, null);
        }
        //Toast.makeText(context, expandableTitle.get(groupPosition), Toast.LENGTH_SHORT).show();
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.list_scan_document_title);
        TextView selectedChild = (TextView) convertView.findViewById(R.id.sub_menu_selected);


        listTitleTextView.setText(listTitle);

        //TextView selectedQuality = (TextView) convertView.findViewById(R.id.selected_quality);

        if(groupPosition == 0) selectedChild.setText(kodakVeriteApp.getScanSettingQuality());
        if(groupPosition == 2) selectedChild.setText(kodakVeriteApp.getScanDocSettingDocument());
        if(groupPosition == 3) selectedChild.setText(kodakVeriteApp.getScanDocSettingSaveAsType());
        if(groupPosition == 1) selectedChild.setText(kodakVeriteApp.getScanSettingColor());


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(groupPosition, childPosition);

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_scan_document_item, null);

        if(groupPosition == 0 && String.valueOf(getChild(groupPosition, childPosition)).equals(kodakVeriteApp.getScanSettingQuality()))
            convertView.findViewById(R.id.iv_checkmark).setSelected(true);

        if(groupPosition == 2 && String.valueOf(getChild(groupPosition, childPosition)).equals(kodakVeriteApp.getScanDocSettingDocument()))
            convertView.findViewById(R.id.iv_checkmark).setSelected(true);

        if(groupPosition == 3 && String.valueOf(getChild(groupPosition, childPosition)).equals(kodakVeriteApp.getScanDocSettingSaveAsType()))
            convertView.findViewById(R.id.iv_checkmark).setSelected(true);

        if(groupPosition == 1 && String.valueOf(getChild(groupPosition, childPosition)).equals(kodakVeriteApp.getScanSettingColor()))
            convertView.findViewById(R.id.iv_checkmark).setSelected(true);

        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.expanded_list_document_item);
        expandedListTextView.setText(expandedListText);

        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
