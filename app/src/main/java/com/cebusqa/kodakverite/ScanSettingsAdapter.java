package com.cebusqa.kodakverite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by mmolo on 9/21/2016.
 */
public class ScanSettingsAdapter extends ArrayAdapter {

    private final Context context;
    private final int textViewResourceId;
    private final String[] objects;
    KodakVeriteApp kodakVeriteApp;
    String item_selected;




    public ScanSettingsAdapter(Context context, int textViewResourceId, String[] objects)
    {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.textViewResourceId = textViewResourceId;
        this.objects = objects;
        kodakVeriteApp = new KodakVeriteApp();


    }

    public class List_holder{
        TextView content;
        ImageView check_mark;

        List_holder(View v){
            content = (TextView) v.findViewById(R.id.content);
            check_mark = (ImageView) v.findViewById(R.id.check_mark);
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;

        ((ListView)parent).setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        final List_holder holder;
        if(v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.scan_settings_row, null);

            holder = new List_holder(v);
            v.setTag(holder);

        }
        else{
             holder= (List_holder) v.getTag();
        }

            holder.content.setText(objects[position]);

            if (objects[position].equals(kodakVeriteApp.getScanSettingQuality())){

                holder.check_mark.setSelected(true);
            }
            else if (objects[position].equals(kodakVeriteApp.getScanSettingColor())){
                holder.check_mark.setSelected(true);
            }
            else if (objects[position].equals(kodakVeriteApp.getScanDocSettingDocument())) {
                holder.check_mark.setSelected(true);
            }
            else if (objects[position].equals(kodakVeriteApp.getScanDocSettingSaveAsType())) {
                holder.check_mark.setSelected(true);
            }
            else if (objects[position].equals(kodakVeriteApp.getScanPhotoSettingQuality())){
                holder.check_mark.setSelected(true);
            }
            else if (objects[position].equals(kodakVeriteApp.getScanPhotoSettingColor())) {
                holder.check_mark.setSelected(true);
            }
            else if (objects[position].equals(kodakVeriteApp.getScanPhotoSettingDocument())) {
                holder.check_mark.setSelected(true);
            }
            else{
                holder.check_mark.setSelected(false);
            }

        return v;

    }

}
