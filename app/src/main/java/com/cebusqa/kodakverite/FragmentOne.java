package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class FragmentOne extends Fragment implements View.OnClickListener {

    ImageButton copybutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_one, container, false);
/*
        copybutton = (ImageButton) view.findViewById(R.id.copybutton);

        copybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CP10_000.this);
                builder.setMessage("Copying...")
                        .setCancelable(false)
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                RingDialog ringDialog = new RingDialog(CP10_000.this, "", "Canceling...", true);
                                ringDialog.run();


                            }
                        });


                AlertDialog alert = builder.create();
                alert.show();


            }


        });
*/
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}

