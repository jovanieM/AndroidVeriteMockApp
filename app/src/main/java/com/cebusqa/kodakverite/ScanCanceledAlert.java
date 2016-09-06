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

    public ScanCanceledAlert newInstance(String title) {
        ScanCanceledAlert frag = new ScanCanceledAlert();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");

        return new AlertDialog.Builder(getActivity())
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //((SP_000)getActivity()).test2 = true;

                                dialog.dismiss();

                            }
                        }
                )
                .setMessage(title)
                .create();
    }

}