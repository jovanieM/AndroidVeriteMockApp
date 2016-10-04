package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by Cebu SQA on 28/06/2016.
 */
public class UnregistrationComplete extends DialogFragment{


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
        builder.setMessage("Settings is saved");

        return builder.create();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        dismiss();
    }
}
