package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Cebu SQA on 25/06/2016.
 */
public class SelectPrinterDialog extends DialogFragment {
    Communicator communicator;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        communicator = (Communicator) getActivity();
        final String[]printers = getResources().getStringArray(R.array.printer_select);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Printer");
        builder.setItems(R.array.printer_select, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                communicator.respond(printers[which]);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        Dialog dialog = builder.create();
        return dialog;
    }
}
