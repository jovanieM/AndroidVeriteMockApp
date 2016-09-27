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
public class CustomPagesPerSideAdapter extends ArrayAdapter {

    private final Context context;
    private final Integer [] textViewResourceId;
    private final String[] objects;
    KodakVeriteApp kodakVeriteApp;
    String item_selected;




    public CustomPagesPerSideAdapter(Context context, Integer [] textViewResourceId, String[] objects)
    {
        super(context, R.layout.items_copy_pages_per_side, objects);
        this.context = context;
        this.textViewResourceId = textViewResourceId;
        this.objects = objects;
        kodakVeriteApp = new KodakVeriteApp();


    }


    public class List_holder{
        TextView content;
        ImageView check_mark;
        ImageView icon;
        //     public Boolean isItemSelected;

        List_holder(View v){
            content = (TextView) v.findViewById(R.id.itemname);
            icon = (ImageView) v.findViewById(R.id.icon);
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
            v = vi.inflate(R.layout.items_copy_pages_per_side, null);

            holder = new List_holder(v);
            v.setTag(holder);

        }
        else{
            holder= (List_holder) v.getTag();
        }

        holder.content.setText(objects[position]);
        holder.icon.setImageResource(textViewResourceId[position]);


        if (objects[position].equals(kodakVeriteApp.getPagesPerSide())){

            holder.check_mark.setSelected(true);
        }
        else{
            holder.check_mark.setSelected(false);
        }

        return v;

    }

}
