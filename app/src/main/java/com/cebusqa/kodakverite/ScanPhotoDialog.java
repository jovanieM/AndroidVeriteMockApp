package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
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
