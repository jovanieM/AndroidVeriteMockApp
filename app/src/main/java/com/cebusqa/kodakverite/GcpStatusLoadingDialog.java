package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Cebu SQA on 27/06/2016.
 */
public class GcpStatusLoadingDialog extends DialogFragment {


    public static GcpStatusLoadingDialog newInstance(String message){

       GcpStatusLoadingDialog gcpStatusLoadingDialog = new GcpStatusLoadingDialog();
        Bundle args = new Bundle();
        args.putString("message", message);
        gcpStatusLoadingDialog.setArguments(args);
        return gcpStatusLoadingDialog;

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String message = getArguments().getString("message");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.gcp_loading, null);
        TextView tv = (TextView) view.findViewById(R.id.message);
        tv.setText(message);

        return new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT)
                //.setMessage(message)
                .setView(view)

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if(getActivity() instanceof GcpStatus) {
                            getActivity().finish();
                        }
                        if(getActivity() instanceof GcpRegisterUnregister) {
                            ((GcpRegisterUnregister) getActivity()).Canceled = true;
                            if (!((GcpRegisterUnregister) getActivity()).test) {
                                getActivity().finish();
                            }
                            dialog.dismiss();
                        }


                    }
                }).create();

    }
}
