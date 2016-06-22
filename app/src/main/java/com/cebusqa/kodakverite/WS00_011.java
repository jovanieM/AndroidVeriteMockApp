package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by SQA Cebu on 6/22/2016.
 */
public class WS00_011 extends Activity {

    ListView listView;
    Button btnManual, btnWPS;
    String[] items;
    ArrayAdapter<String> adapter;
    static public String ssid_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_011);

        listView = (ListView) findViewById(R.id.lv_connections);
        btnManual = (Button) findViewById(R.id.btn_manual1);
        btnWPS = (Button) findViewById(R.id.btn_wps1);

        items = getResources().getStringArray(R.array.routers_printer);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        ssid_item = listView.getItemAtPosition(i).toString();
                        toWS00_012();
                        break;
                    case 1:
                        ssid_item = listView.getItemAtPosition(i).toString();
                        toWS00_012();
                        break;
                    case 2:
                        ssid_item = listView.getItemAtPosition(i).toString();
                        toWS00_012();
                        break;
                    case 3:
                        ssid_item = listView.getItemAtPosition(i).toString();
                        toWS00_012();
                        break;
                    case 4:
                        ssid_item = listView.getItemAtPosition(i).toString();
                        toWS00_012();
                        break;
                    case 5:
                        ssid_item = listView.getItemAtPosition(i).toString();
                        toWS00_012();
                        break;
                    case 6:
                        ssid_item = listView.getItemAtPosition(i).toString();
                        toWS00_012();
                        break;
                    case 7:
                        ssid_item = listView.getItemAtPosition(i).toString();
                        toWS00_012();
                        break;
                    case 8:
                        ssid_item = listView.getItemAtPosition(i).toString();
                        toWS00_012();
                        break;
                    case 9:
                        ssid_item = listView.getItemAtPosition(i).toString();
                        toWS00_012();
                        break;
                    case 10:
                        ssid_item = listView.getItemAtPosition(i).toString();
                        toWS00_012();
                        break;
                    case 11:
                        ssid_item = listView.getItemAtPosition(i).toString();
                        toWS00_012();
                        break;
                    default:
                        break;
                }
            }
        });

        btnWPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WS00_011.this, WS00_015.class));
            }
        });

        btnManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WS00_011.this, WS00_014.class));
            }
        });
    }

    public void toWS00_012(){
        startActivity(new Intent(WS00_011.this, WS00_012.class));
    }
}
