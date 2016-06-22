package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by SQA Cebu on 6/21/2016.
 */
public class PaperSetup_000 extends Activity{

    Button btnBack, btnSave;
    TextView paper_size, paper_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.papersetup_000);

        paper_size = (TextView) findViewById(R.id.paper_size);
        paper_type = (TextView) findViewById(R.id.paper_type);
        btnBack = (Button) findViewById(R.id.back);
        btnSave = (Button) findViewById(R.id.btnSave);

                    RingDialog ringDialog = new RingDialog(PaperSetup_000.this, "", "Getting Printer Setting...", true);
                    ringDialog.run();


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {

        //        RingDialog ringDialog = new RingDialog(PaperSetup_000.this, "", "Setting...", true);
        //        ringDialog.run();

                RingDialog ringDialog1 = new RingDialog(PaperSetup_000.this, "", "Setting is saved...", true);
                ringDialog1.run();
                startActivity(new Intent(PaperSetup_000.this, PU00_0000.class));

               }
        });

   /*     paper_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(PaperSetup_000.this);
                LayoutInflater inflater = getLayoutInflater();
                View convertView = (View) inflater.inflate(R.layout.listview, null);
                alertDialog.setView(convertView);
                alertDialog.setTitle("List");
                ListView lv = (ListView) convertView.findViewById(R.id.listView1);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
                lv.setAdapter(adapter);
                alertDialog.show();
            }
        });

        */


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaperSetup_000.this, PU00_0000.class));
            }
        });
    }


}
