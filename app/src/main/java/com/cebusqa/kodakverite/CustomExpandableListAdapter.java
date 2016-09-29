package com.cebusqa.kodakverite;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by anarte on 28/09/2016.
 */

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableTitle;
    private HashMap<String, List<String>> expandableList;
    KodakVeriteApp kodakVeriteApp;
    int count = 0;

    public CustomExpandableListAdapter(Context context, List<String> expandableTitle, HashMap<String, List<String>> expandableList){
        this.context = context;
        this.expandableTitle = expandableTitle;
        this.expandableList = expandableList;
        kodakVeriteApp = new KodakVeriteApp();
    }

    @Override
    public int getGroupCount() {
        return this.expandableTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.expandableList.get(this.expandableTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.expandableTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.expandableList.get(this.expandableTitle.get(groupPosition)).get(childPosition);
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
        convertView = inflater.inflate(R.layout.list_scan_document_group, null);
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.list_scan_document_title);
        TextView selectedChild = (TextView) convertView.findViewById(R.id.sub_menu_selected);

        //TextView selectedQuality = (TextView) convertView.findViewById(R.id.selected_quality);

        if(groupPosition == 0) selectedChild.setText(kodakVeriteApp.getScanSettingQuality());
        if(groupPosition == 1) selectedChild.setText(kodakVeriteApp.getScanDocSettingDocument());
        if(groupPosition == 2) selectedChild.setText(kodakVeriteApp.getScanDocSettingSaveAsType());
        if(groupPosition == 3) selectedChild.setText(kodakVeriteApp.getScanSettingColor());

        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(groupPosition, childPosition);

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_scan_document_item, null);

        if(groupPosition == 0 && String.valueOf(getChild(groupPosition, childPosition)).equals(kodakVeriteApp.getScanSettingQuality())){
            convertView.findViewById(R.id.iv_checkmark).setSelected(true);
        }
        if(groupPosition == 1 && String.valueOf(getChild(groupPosition, childPosition)).equals(kodakVeriteApp.getScanDocSettingDocument()))
            convertView.findViewById(R.id.iv_checkmark).setSelected(true);

        if(groupPosition == 2 && String.valueOf(getChild(groupPosition, childPosition)).equals(kodakVeriteApp.getScanDocSettingSaveAsType()))
            convertView.findViewById(R.id.iv_checkmark).setSelected(true);

        if(groupPosition == 3 && String.valueOf(getChild(groupPosition, childPosition)).equals(kodakVeriteApp.getScanSettingColor()))
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
