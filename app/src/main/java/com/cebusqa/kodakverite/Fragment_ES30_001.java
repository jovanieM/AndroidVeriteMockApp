package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by SQA Cebu on 6/13/2016.
 */
public class Fragment_ES30_001 extends Fragment {

    Button btnManual, btnWPS, btnDirect;
    ListView listView;
    String[] items;
    ArrayAdapter<String> adapter;
    int itemPos;
    static public String itemSSID = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_es30_001, container, false);

        // instantiating buttons
        btnManual = (Button) view.findViewById(R.id.btn_manual);
        btnWPS = (Button) view.findViewById(R.id.btn_wps);
        btnDirect = (Button) view.findViewById(R.id.btn_direct);
        listView = (ListView) view.findViewById(R.id.lv_connections);

        items = getResources().getStringArray(R.array.routers_printer);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //itemSSID = (String) listView.getItemAtPosition(i).toString();
                switch (i){
                    case 0:
                        //itemPos = adapterView.getSelectedItemPosition();
                        //itemSSID = adapterView.getSelectedItem().toString();

                        Fragment_ES30_040 frag = new Fragment_ES30_040();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        itemSSID = (String) listView.getItemAtPosition(i).toString();


                        transaction.replace(R.id.my_layout, frag);
                        transaction.commit();
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            }
        });

        // button manual
        btnManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_ES30_010 frag = new Fragment_ES30_010();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.my_layout, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //button WPS
        btnWPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_ES30_020 frag = new Fragment_ES30_020();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.my_layout, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //button Direct
        btnDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_ES30_051 frag = new Fragment_ES30_051();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.my_layout, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}
