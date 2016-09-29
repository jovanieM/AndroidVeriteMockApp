package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import static android.widget.AbsListView.CHOICE_MODE_SINGLE;


public class FragmentQuickPrint extends Fragment {

    //ImageButton photo4x6, photoletter, document;
    boolean isPressed = false;
    KodakVeriteApp kodakVeriteApp;
    ListView quick_print_list;

    QuickPrintAdapter adapter;
    ArrayAdapter<String> arrayAdapter;
    ImageView check;
    String item_selected;
    int item_choice;
    int[] img = {R.drawable.checkmark_list, R.drawable.checkmark_list, R.drawable.checkmark_list};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quick_print, container, false);

        quick_print_list = (ListView) view.findViewById(R.id.quick_print_list);
        kodakVeriteApp = new KodakVeriteApp();

        final String[] quick_list = getResources().getStringArray(R.array.quick_print);

        quick_print_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        quick_print_list.setItemChecked(item_choice, true);


        final QuickPrintAdapter adapter = new QuickPrintAdapter(getActivity(),quick_list,img);
        quick_print_list.setAdapter(adapter);
        quick_print_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item_selected = quick_list[position];
                kodakVeriteApp.setQuickPrintItem(item_selected);
                item_choice = quick_print_list.getSelectedItemPosition();
                adapter.notifyDataSetChanged();
            }
        });

        //adapter.notifyDataSetChanged();
        return view;
    }
}
