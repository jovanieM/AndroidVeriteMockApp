package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jmolas on 06/09/2016.
 */
public class SaveDocumentDialog extends DialogFragment{

    public static SaveDocumentDialog newInstance(String title){
        SaveDocumentDialog documentDialog = new SaveDocumentDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        documentDialog.setArguments(bundle);
        Log.v("DialogFragment", "newInstance run");
        return documentDialog;

    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.v("DialogFragment", "onCreateDialog runs");
        String title = getArguments().getString("title");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(title);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getActivity().finish();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getActivity().finish();

            }
        });
        return builder.create();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        Log.v("DialogFragment", "onDestroy");


    }


}
