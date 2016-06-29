package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Cebu SQA on 28/06/2016.
 */
public class UnregistrationComplete extends DialogFragment {

    public static UnregistrationComplete newInstance(String message){
        UnregistrationComplete unregistrationComplete = new UnregistrationComplete();
        Bundle args = new Bundle();
        args.putString("message", message);
        unregistrationComplete.setArguments(args);
        return unregistrationComplete;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String message = getArguments().getString("message");

      AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.unregistration_complete, null);
        TextView tv = (TextView) view.findViewById(R.id.unreg_comp_alert);
        tv.setText(message);
        builder.setView(view);


        Dialog scanDialog = builder.create();
        return scanDialog;
    }
}
