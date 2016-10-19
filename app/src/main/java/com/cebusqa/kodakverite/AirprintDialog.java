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
 * Created by Cebu SQA on 27/06/2016.
 */
public class AirprintDialog extends DialogFragment{


    public static AirprintDialog newInstance(String message){

        AirprintDialog airprintDialog = new AirprintDialog();
        Bundle args = new Bundle();
        args.putString("message", message);
        airprintDialog.setArguments(args);
        return airprintDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        String message = getArguments().getString("message");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.gcp_loading, null);
        TextView tv = (TextView) view.findViewById(R.id.message);
        tv.setText(message);

        return new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT)
                //.setMessage(message)
                .setView(view)
                .setCancelable(false)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AirPrint.cancel = true;
                        GcpEnableDisable.cancel = true;
                        dialog.dismiss();
                        getActivity().finish();

                        //onDestroy();
                    }
                })
           .create();

    }

    @Override
    public void onDestroy() {
        dismiss();
        super.onDestroy();
    }


}
