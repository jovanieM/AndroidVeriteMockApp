package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;


/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class PaperSetup_000 extends AppCompatActivity implements View.OnClickListener {

    private boolean isCanceled;
    Button btnBack, btnSave;
    TextView spin_paper_type, spin_paper_size;
    public boolean clicked = false;
    int pos_size, pos_type;

    Resources res;
    String[] paper_setup_size, paper_setup_type;
    KodakVeriteApp kodakVeriteApp;
    ComponentAdapter array_adapter;
    Context context;
    String item_selected_size, item_selected_type;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.papersetup_000);

        context = this;
        res = getResources();
        paper_setup_size = res.getStringArray(R.array.Paper_size_print);
        paper_setup_type = res.getStringArray(R.array.Paper_type);
        kodakVeriteApp = new KodakVeriteApp();

        spin_paper_size = (TextView) findViewById(R.id.spin_paper_size);
        spin_paper_type = (TextView) findViewById(R.id.spin_paper_type);

        btnBack = (Button) findViewById(R.id.back);
        btnSave = (Button) findViewById(R.id.btnSave);

        final ProgressDialog pd = new ProgressDialog(PaperSetup_000.this, ProgressDialog.THEME_HOLO_LIGHT);
        pd.setMessage("Getting printer setting...");
        pd.setCancelable(false);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(PaperSetup_000.this, PU00_0000.class));
                pd.dismiss();
                isCanceled = true;
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

        spin_paper_type.setText(kodakVeriteApp.getPaperType());
        spin_paper_type.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                final AlertDialog builder = new AlertDialog.Builder(PaperSetup_000.this, AlertDialog.THEME_HOLO_LIGHT).create();

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = (View) inflater.inflate(R.layout.listview_layout, null);

                builder.setView(v);
                builder.setTitle("Paper Type");
                final ListView list = (ListView) v.findViewById(R.id.selection_list);

                ComponentAdapter array_adapter = new ComponentAdapter(getApplicationContext(), R.layout.component, R.id.content, paper_setup_type);

                list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                list.setAdapter(array_adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                        item_selected_type = paper_setup_type[pos].toString();
                        spin_paper_type.setText(item_selected_type);
                        //    kodakVeriteApp.setPaperType(item_selected_type);
                        builder.dismiss();

                    }

                });

                builder.show();

            }
        });


        spin_paper_size.setText(kodakVeriteApp.getPaperSize());
        spin_paper_size.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                final AlertDialog builder = new AlertDialog.Builder(PaperSetup_000.this, AlertDialog.THEME_HOLO_LIGHT).create();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = (View) inflater.inflate(R.layout.listview_layout, null);

                builder.setView(v);
                builder.setTitle("Paper Size");
                final ListView list = (ListView) v.findViewById(R.id.selection_list);

                ComponentAdapter array_adapter = new ComponentAdapter(getApplicationContext().getApplicationContext(), R.layout.component, R.id.content, paper_setup_size);

                list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                list.setAdapter(array_adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                        item_selected_size = paper_setup_size[pos].toString();
                        spin_paper_size.setText(item_selected_size);
                        builder.dismiss();
                    }

                });
                builder.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd = new ProgressDialog(PaperSetup_000.this, ProgressDialog.THEME_HOLO_LIGHT);
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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaperSetup_000.this, PU00_0000.class));
                finish();
            }
        });
        return;
     /*   // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();*/
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PaperSetup_000.this, PU00_0000.class));
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
                kodakVeriteApp.setPaperType(item_selected_type);
                kodakVeriteApp.setPaperSize(item_selected_size);
                Intent intent = new Intent(PaperSetup_000.this, PU00_0000.class);
                getFragmentManager().findFragmentByTag("tag").onDestroy();
                startActivity(intent);
                finish();
            }
        };

        handler.postDelayed(run, 4000);
    }

}













