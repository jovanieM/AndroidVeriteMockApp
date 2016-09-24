package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by Cebu SQA on 21/06/2016.
 */
public class ScanPhotoDialog2 extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = null; //new AlertDialog.Builder(getActivity());
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB){
            builder = new AlertDialog.Builder(getActivity());
        }else{
            builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
        }
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.scan_photo_dialog, null);
        if (getActivity() instanceof SP_000) {
            builder.setTitle("Scan Photo");
        }else if(getActivity() instanceof DocumentScan2){
            builder.setTitle("Scan Document");
        }
        builder.setView(view);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                new RingDialog(getActivity(), "Canceling...", null, true).run();


                if (getActivity() instanceof SP_000) {
                    ((SP_000) getActivity()).test2 = true;
                }
                if (getActivity() instanceof DocumentScan2) {
                    ((DocumentScan2) getActivity()).dtest2 = true;
                }


                //super.onCancel(dialog);
            }
        });


        Dialog scanDialog = builder.create();


        return scanDialog;
    }

    @Override
    public void onDestroy() {
        dismiss();
        super.onDestroy();
    }
}
