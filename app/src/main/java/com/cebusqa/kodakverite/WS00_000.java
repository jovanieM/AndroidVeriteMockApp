package com.cebusqa.kodakverite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class WS00_000 extends Activity {

    ListView lvWifiSetupItems;
    Context context;
    Button back;

    //public static int [] prgImages ={R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,};
    public static String [] prgItems; //= {"item1","item2","item3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_000);

        context=this;
        prgItems = getResources().getStringArray(R.array.wifi_setup);

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lvWifiSetupItems = (ListView) findViewById(R.id.lv_wifi_setup);
        lvWifiSetupItems.setAdapter(new CustomAdapter(this, prgItems));
        lvWifiSetupItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch(position){
                    case 0:
                        startActivity(new Intent(WS00_000.this, WS00_010.class));
                        break;
                    case 1:
                        startActivity(new Intent(WS00_000.this, WS00_020.class));
                        break;
                    case 2:
                        startActivity(new Intent(WS00_000.this, WS00_030.class));
                        break;
                    case 3:
                        startActivity(new Intent(WS00_000.this, WS00_040.class));
                        break;
                    case 4:
                        startActivity(new Intent(WS00_000.this, WS00_050.class));
                        break;
                    case 5:
                        startActivity(new Intent(WS00_000.this, WS00_060.class));
                        break;
                    default:
                        break;
                }
            }
        });

    }
}
