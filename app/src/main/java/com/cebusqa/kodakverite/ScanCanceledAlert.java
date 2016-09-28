package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Cebu SQA on 23/06/2016.
 */
public class ScanCanceledAlert extends DialogFragment {



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
        if(getActivity() instanceof PhotoScanMain
                || getActivity() instanceof SP_000
                || getActivity() instanceof DocumentScan
                || getActivity() instanceof DocumentScan2)builder.setMessage("Scan Canceled");
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //((SP_000)getActivity()).test2 = true;

                        dialog.dismiss();
                    }
                }
        );


        return builder.create();
    }

}