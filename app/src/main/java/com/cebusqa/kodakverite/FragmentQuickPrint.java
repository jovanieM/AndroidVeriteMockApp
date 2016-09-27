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


public class FragmentQuickPrint extends Fragment {

    //ImageButton photo4x6, photoletter, document;
    boolean isPressed = false;
    KodakVeriteApp kodakVeriteApp;
    ListView quick_print_list;
    String[] quick_list = {"4x6","Letter","Document"};
    QuickPrintAdapter adapter;
    ArrayAdapter<String> arrayAdapter;
    ImageView check;
    String item_selected;
    int[] img = {R.drawable.checkmark_list, R.drawable.checkmark_list, R.drawable.checkmark_list};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quick_print, container, false);

        //photo4x6 = (ImageButton)view.findViewById(R.id.photo4x6);
        //photoletter = (ImageButton)view.findViewById(R.id.photoletter);
        //document = (ImageButton)view.findViewById(R.id.doc_type);

        //check.setImageResource(R.drawable.checkmark_list);

        //adapter = new QuickPrintAdapter(getActivity(), R.layout.quick_print_item, R.id.tv_list, quick_list);
        // arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, quick_list);

        /* photo4x6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photo4x6.setImageResource(R.mipmap.photo4x6borderless_check);
                photoletter.setImageResource(R.mipmap.photoletterborderless_uncheck);
                document.setImageResource(R.mipmap.documentletter_uncheck);
                kodakVeriteApp.setPaperType("Glossy Photo");
                kodakVeriteApp.setPaperSize("4x6 in. Borderless");
            }
        });

        photoletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photo4x6.setImageResource(R.mipmap.photo4x6borderless_uncheck);
                photoletter.setImageResource(R.mipmap.photoletterborderless_check);
                document.setImageResource(R.mipmap.documentletter_uncheck);
                kodakVeriteApp.setPaperType("Matte Photo");
                kodakVeriteApp.setPaperSize("Letter");
            }
        });

        document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photo4x6.setImageResource(R.mipmap.photo4x6borderless_uncheck);
                photoletter.setImageResource(R.mipmap.photoletterborderless_uncheck);
                document.setImageResource(R.mipmap.documentletter_check);
                kodakVeriteApp.setPaperType("Plain");
                kodakVeriteApp.setPaperSize("Letter");
            }
        });
        ComponentAdapter array_adapter = new ComponentAdapter(getActivity(), R.layout.component, R.id.content, quick_list);
        */

        quick_print_list = (ListView) view.findViewById(R.id.quick_print_list);
        quick_print_list.setSelected(true);
        kodakVeriteApp = new KodakVeriteApp();

        quick_list = getResources().getStringArray(R.array.quick_print);

        quick_print_list.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        final QuickPrintAdapter adapter = new QuickPrintAdapter(getActivity(),quick_list,img);
        quick_print_list.setAdapter(adapter);

        quick_print_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item_selected = quick_list[position];
                kodakVeriteApp.setQuickPrintItem(item_selected);
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}
