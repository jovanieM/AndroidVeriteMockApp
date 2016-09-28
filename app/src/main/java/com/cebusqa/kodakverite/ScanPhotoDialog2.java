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
public class ScanPhotoDialog2 extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
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

                //new RingDialog(getActivity(), "Canceling...", null, true).run();
                //AirprintSavingSettings.newInstance("Canceling...").show(getFragmentManager(), "cancel");

                if (getActivity() instanceof SP_000) {
                    ((SP_000) getActivity()).test2 = true;
                    ((SP_000) getActivity()).scanCanceled();



                }
                if (getActivity() instanceof DocumentScan2) {
                    ((DocumentScan2) getActivity()).dtest2 = true;
                    ((DocumentScan2) getActivity()).scanCanceledDoc();
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
