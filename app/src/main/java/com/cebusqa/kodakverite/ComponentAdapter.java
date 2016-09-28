package com.cebusqa.kodakverite;

import android.content.Context;
import android.graphics.Color;
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
public class ComponentAdapter extends ArrayAdapter {

    private final Context context;
    private final int textViewResourceId;
    private final String[] objects;
    private final int resource;
    KodakVeriteApp kodakVeriteApp;
    String item_selected;




    public ComponentAdapter(Context context, int resource, int textViewResourceId, String[] objects)
    {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.objects = objects;
        kodakVeriteApp = new KodakVeriteApp();


    }

/*    public class StateListItem {
        public String itemTitle;
        public long id;
        public Boolean isItemSelected;

        public StateListItem(String name, long id) {
            this.itemTitle = name;
            this.isItemSelected = false;
            this.id = id;
        }

        @Override
        public String toString() {
            return this.itemTitle;
        }
    }*/

    public class List_holder{
        TextView content;
        ImageView check_mark;
   //     public Boolean isItemSelected;

        List_holder(View v){
            content = (TextView) v.findViewById(R.id.content);
            check_mark = (ImageView) v.findViewById(R.id.check_mark);
    //        this.isItemSelected = false;
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;

        ((ListView)parent).setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        final List_holder holder;
    //    List_holder currItem = getItem(position);

        if(v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.component, null);

            holder = new List_holder(v);
            v.setTag(holder);

        }
        else{
             holder= (List_holder) v.getTag();
        }

            holder.content.setText(objects[position]);


            if (objects[position].equals(kodakVeriteApp.getCopyPaperSize())){

                holder.check_mark.setSelected(true);
            }
            else if (objects[position].equals(kodakVeriteApp.getCopyColor())){
                holder.check_mark.setSelected(true);
            }
            else if (objects[position].equals(kodakVeriteApp.getCopyResize())) {
                holder.check_mark.setSelected(true);
            }
            else if (objects[position].equals(kodakVeriteApp.getCopyQuality())) {
                holder.check_mark.setSelected(true);
            }
            else if (objects[position].equals(kodakVeriteApp.getCopyResize())) {
                holder.check_mark.setSelected(true);
            }
            else if (objects[position].equals(kodakVeriteApp.getPagesPerSide())) {
                holder.check_mark.setSelected(true);
            }
            else if (objects[position].equals(kodakVeriteApp.getCopyPaperType())) {
                holder.check_mark.setSelected(true);
            }
            else if(objects[position].equals(kodakVeriteApp.getQuickPrintItem())) {
                holder.content.setTextColor(Color.WHITE);
                holder.check_mark.setSelected(true);
            }
            else if(objects[position].equals(kodakVeriteApp.getDirectTime())){
                holder.check_mark.setSelected(true);
            }
            else{
                holder.check_mark.setSelected(false);
            }

        return v;

    }

}
