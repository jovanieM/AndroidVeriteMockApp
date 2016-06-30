package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Cebu SQA on 29/06/2016.
 */
public class AirprintSavingSettings extends DialogFragment {


    public static AirprintSavingSettings newInstance(String message){

        AirprintSavingSettings airprintSavingSettings = new AirprintSavingSettings();
        Bundle args = new Bundle();
        args.putString("message", message);
        airprintSavingSettings.setArguments(args);
        return airprintSavingSettings;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        String message = getArguments().getString("message");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.gcp_loading, null);
        TextView tv = (TextView) view.findViewById(R.id.message);
        tv.setText(message);

        return new AlertDialog.Builder(getActivity())
                //.setMessage(message)
                .setView(view)

                .create();

    }

    @Override
    public void onDestroy() {
        dismiss();
        super.onDestroy();
    }
}
