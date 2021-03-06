package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;


public class FragmentOne extends Fragment implements View.OnClickListener {

    ImageButton copybutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);

        copybutton = (ImageButton) view.findViewById(R.id.copybutton);

            copybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), AlertDialog.THEME_HOLO_LIGHT);
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.copying_dialog, null);
                builder.setView(view);

                     builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {

                                            new Handler().postDelayed(new Runnable() {
                                    @Override
                                                public void run() {

                                                        AlertDialog.Builder builders = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
                                                        builders.setMessage("Copy Canceled...")
                                                            .setCancelable(false)
                                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                                                    public void onClick(DialogInterface dialog, int id) {

                                                                    }
                                                        });

                                                        AlertDialog alert = builders.create();
                                                        alert.show();

                                                }
                                            }, 3000);

                            }

                     }

                     );


                final AlertDialog alert = builder.create();
                alert.show();

                final Timer timer2 = new Timer();
                timer2.schedule(new TimerTask() {
                    public void run() {
                        alert.dismiss();
                        timer2.cancel(); //this will cancel the timer of the system
                    }
                }, 5000);

        }

        });

        return view;
    }

    @Override
    public void onClick(View v) {

    }}



