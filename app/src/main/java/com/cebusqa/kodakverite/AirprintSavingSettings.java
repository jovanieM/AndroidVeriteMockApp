package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Cebu SQA on 29/06/2016.
 */
public class AirprintSavingSettings extends DialogFragment {




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
        final LayoutInflater inflater =getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.gcp_loading, null);
        TextView tv = (TextView) view.findViewById(R.id.message);
        if(getActivity() instanceof PhotoScanMain
                || getActivity() instanceof SP_000
                || getActivity() instanceof DocumentScan
                || getActivity() instanceof DocumentScan2)tv.setText("Canceling...");
        if(getActivity() instanceof AirPrint || getActivity() instanceof GcpEnableDisable)tv.setText("Setting...");
        builder.setView(view);
        return builder.create();
//        return new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT)
//                //.setMessage(message)
//                .setView(view)
//                .create();

    }

    @Override
    public void onDestroy() {
        dismiss();
        super.onDestroy();
    }

}
