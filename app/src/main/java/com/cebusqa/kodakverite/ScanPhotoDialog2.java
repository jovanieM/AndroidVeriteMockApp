package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by Cebu SQA on 21/06/2016.
 */
public class ScanPhotoDialog2 extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.scan_photo_dialog, null);
        builder.setTitle("Scan Photo");
        builder.setView(view);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((SP_000)getActivity()).test2 = true;
                new RingDialog(getActivity(),"Canceling...", null, true).run();
                dialog.cancel();


                //super.onCancel(dialog);
            }
        });


        Dialog scanDialog = builder.create();


        return scanDialog;
    }


}