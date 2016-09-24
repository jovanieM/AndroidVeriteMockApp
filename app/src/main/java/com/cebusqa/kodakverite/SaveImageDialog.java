package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by jmolas on 05/09/2016.
 */
public class SaveImageDialog extends DialogFragment{

    public static SaveImageDialog newInstance(String title){
        SaveImageDialog saveImageDialog = new SaveImageDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        saveImageDialog.setArguments(bundle);
        Log.v("DialogFragment", "newInstance run");
        return saveImageDialog;

    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.v("DialogFragment", "onCreateDialog runs");
        String title = getArguments().getString("title");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
        builder.setMessage(title);
        builder.setNeutralButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
           }
        });
        return builder.create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismiss();

    }


}
