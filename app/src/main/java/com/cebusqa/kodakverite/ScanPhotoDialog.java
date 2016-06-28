package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by Cebu SQA on 21/06/2016.
 */
public class ScanPhotoDialog extends DialogFragment{
    boolean cancel;


    public static ScanPhotoDialog newInstance(String title){
        ScanPhotoDialog scanPhotoDialog = new ScanPhotoDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        scanPhotoDialog.setArguments(args);
        return scanPhotoDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.scan_photo_dialog, null);
        builder.setTitle(title);
        builder.setView(view);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                new RingDialog(getActivity(), "Canceling", null, true).run();

                if(getActivity() instanceof PhotoScanMain) {
                    ((PhotoScanMain) getActivity()).test = true;
                }
                if(getActivity()instanceof DocumentScan){
                    ((DocumentScan) getActivity()).dtest = true;
                }



        }});


        Dialog scanDialog = builder.create();
        return scanDialog;

        }


}
