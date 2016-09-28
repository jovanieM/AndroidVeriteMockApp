package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by Arvin on 6/23/2016.
 */
public class WS00_050 extends Activity {

    Spinner spinner;
    String[] items;
    ArrayAdapter<String> adapter;
    Button btnBack, btnSaveSetting;
    int directPos;
    KodakVeriteApp kodakVeriteApp;
    RelativeLayout relativeLayout_direct;
    TextView tv_min;
    Context context;
    String item_selected;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws00_050);

        context = this;
        //spinner = (Spinner) findViewById(R.id.spinner);
        btnBack = (Button) findViewById(R.id.back);
        btnSaveSetting = (Button) findViewById(R.id.btn_save_setting4);
        kodakVeriteApp = new KodakVeriteApp();
        relativeLayout_direct = (RelativeLayout) findViewById(R.id.rl_direct);
        tv_min = (TextView) findViewById(R.id.tv_minutes);

        items = getResources().getStringArray(R.array.direct_time);
        //adapter = new ArrayAdapter<String>(this, R.layout.spinner_wifi_item, items);
        //adapter.setDropDownViewResource(R.layout.spinner_wifi_dropdown);
        //spinner.setAdapter(adapter);

        tv_min.setText(kodakVeriteApp.getDirectTime());

        relativeLayout_direct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog builder = new AlertDialog.Builder(WS00_050.this, AlertDialog.THEME_HOLO_LIGHT).create();

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = (View) inflater.inflate(R.layout.listview_layout, null);

                builder.setView(v);
                //builder.setTitle("Paper Type");
                final ListView list = (ListView) v.findViewById(R.id.selection_list);

                ComponentAdapter array_adapter = new ComponentAdapter(getApplicationContext(), R.layout.component, R.id.content, items);

                list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                list.setAdapter(array_adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                        item_selected = items[pos];
                        tv_min.setText(item_selected);
                        kodakVeriteApp.setDirectTime(item_selected);
                        builder.dismiss();
                    }
                });
                builder.show();
            }
        });

        /* spinner.setSelection(Arrays.asList(items).indexOf(kodakVeriteApp.getDirectTime()));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                directPos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        final ProgressDialog pd = new ProgressDialog(WS00_050.this, ProgressDialog.THEME_HOLO_LIGHT);
        pd.setMessage("Getting network information...");
        pd.setCancelable(false);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(WS00_050.this, WS00_000.class));
                pd.dismiss();
                finish();
            }
        });
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pd.dismiss();
            }
        }).start();

        //Back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WS00_050.this, WS00_000.class));
                finish();
            }
        });

        //Save setting button
        btnSaveSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd = new ProgressDialog(WS00_050.this, ProgressDialog.THEME_HOLO_LIGHT);
                pd.setMessage("Setting...");
                pd.setCancelable(false);
                pd.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                        close();
                    }
                }, 4000);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(WS00_050.this, WS00_000.class));
        finish();
    }

    public void close() {
        final UnregistrationComplete unregistrationComplete = new UnregistrationComplete();
        unregistrationComplete.show(getFragmentManager(), "tag");
        unregistrationComplete.setCancelable(false);
        final Handler handler = new Handler();
        final Runnable run = new Runnable() {
            @Override
            public void run() {
                kodakVeriteApp.setDirectTime(items[directPos]);
                startActivity(new Intent(WS00_050.this, WS00_000.class));
                getFragmentManager().findFragmentByTag("tag").onDestroy();
                finish();
            }
        };

        handler.postDelayed(run, 4000);
    }
}
