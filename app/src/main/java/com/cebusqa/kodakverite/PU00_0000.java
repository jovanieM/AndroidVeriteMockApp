package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class PU00_0000 extends Activity {

    ListView lvPrinterUtilityList;
    String[] items;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pu00_000);

        lvPrinterUtilityList = (ListView) findViewById(R.id.lv_print_util_list);
        items = getResources().getStringArray(R.array.printer_utility);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        lvPrinterUtilityList.setAdapter(adapter);
        lvPrinterUtilityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        break;
                    case 1:
                        Intent intent1 = new Intent (PU00_0000.this, Clean_Printhead.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent = new Intent (PU00_0000.this, CS00_000.class);
                        startActivity(intent);
                        break;
                    case 3:
                        Intent intent3 = new Intent (PU00_0000.this, Print_Reports.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        break;

                }
            }
        });
    }
}
