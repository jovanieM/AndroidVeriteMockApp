package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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
    InputMethodManager imm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_es30_001, container, false);

        //hideSoftKeyboard();
        //imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        //imm.hideSoftInputFromWindow()
        //imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);

         //.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
                switch (i) {
                    case 0:
                        //itemPos = adapterView.getSelectedItemPosition();
                        //itemSSID = adapterView.getSelectedItem().toString();
                        fragment_ES30_040();
                        itemSSID = (String) listView.getItemAtPosition(i).toString();
                        break;

                    case 1:
                        fragment_ES30_040();
                        itemSSID = (String) listView.getItemAtPosition(i).toString();
                        break;

                    case 2:
                        fragment_ES30_040();
                        itemSSID = (String) listView.getItemAtPosition(i).toString();
                        break;

                    case 3:
                        fragment_ES30_040();
                        itemSSID = (String) listView.getItemAtPosition(i).toString();
                        break;

                    case 4:
                        fragment_ES30_040();
                        itemSSID = (String) listView.getItemAtPosition(i).toString();
                        break;

                    case 5:
                        fragment_ES30_040();
                        itemSSID = (String) listView.getItemAtPosition(i).toString();
                        break;

                    case 6:
                        fragment_ES30_040();
                        itemSSID = (String) listView.getItemAtPosition(i).toString();
                        break;

                    case 7:
                        fragment_ES30_040();
                        itemSSID = (String) listView.getItemAtPosition(i).toString();
                        break;

                    case 8:
                        fragment_ES30_040();
                        itemSSID = (String) listView.getItemAtPosition(i).toString();
                        break;

                    case 9:
                        fragment_ES30_040();
                        itemSSID = (String) listView.getItemAtPosition(i).toString();
                        break;

                    case 10:
                        fragment_ES30_040();
                        itemSSID = (String) listView.getItemAtPosition(i).toString();
                        break;

                    case 11:
                        fragment_ES30_040();
                        itemSSID = (String) listView.getItemAtPosition(i).toString();
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
                Fragment_ES30_050_A frag = new Fragment_ES30_050_A();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.my_layout, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //disable Back key
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                }
                return false;
            }
        });

        return view;
    }

    public void fragment_ES30_040() {
        Fragment_ES30_040 frag = new Fragment_ES30_040();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.my_layout, frag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void hideSoftKeyboard() {
        if (getActivity().getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}
