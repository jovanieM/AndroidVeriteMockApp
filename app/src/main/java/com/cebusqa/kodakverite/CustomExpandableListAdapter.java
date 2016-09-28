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
import java.util.TreeMap;

/**
 * Created by anarte on 28/09/2016.
 */

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableTitle;
    private HashMap<String, List<String>> expandableList;
    KodakVeriteApp kodakVeriteApp;

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

<<<<<<< HEAD

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_scan_document_group, null);

        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.list_scan_document_title);
        //TextView selectedQuality = (TextView) convertView.findViewById(R.id.selected_quality);

//        if(expandableTitle.get(0).equals(String.valueOf(getGroup(groupPosition))))
//            selectedQuality.setText(kodakVeriteApp.getScanSettingQuality());
//        if(expandableTitle.get(1).equals(String.valueOf(getGroup(groupPosition))))
//            selectedQuality.setText(kodakVeriteApp.getScanDocSettingSaveAsType());
//        if(expandableTitle.get(3).equals(String.valueOf(getGroup(groupPosition))))
//            selectedQuality.setText(kodakVeriteApp.getScanDocSettingDocument());
//        if(expandableTitle.get(4).equals(String.valueOf(getGroup(groupPosition))))
//            selectedQuality.setText(kodakVeriteApp.getScanPhotoSettingColor());


=======
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_scan_document_group, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.list_scan_document_title);

        listTitleTextView.setTypeface(null, Typeface.BOLD);
>>>>>>> f329655456eed3d80ee1867da253af13ae00a2af
        listTitleTextView.setText(listTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(groupPosition, childPosition);

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_scan_document_item, null);
        if(String.valueOf(getChild(groupPosition, childPosition)).equals(kodakVeriteApp.getScanSettingQuality())){
            convertView.findViewById(R.id.iv_checkmark).setSelected(true);
        }

        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.expanded_list_document_item);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
