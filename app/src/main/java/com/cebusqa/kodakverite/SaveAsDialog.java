package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by Cebu SQA on 20/06/2016.
 */
public class SaveAsDialog extends DialogFragment{


    String fileName;

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        final Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.sample);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);


        builder.setTitle("Save As");
        final EditText et = new EditText(getActivity());
        et.setTextColor(Color.BLACK);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        et.setLayoutParams(lp);
        builder.setView(et);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Toast.makeText(getActivity(), "No was clicked", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fileName = et.getText().toString();
                SaveToPictures saveToPictures = new SaveToPictures();
                saveToPictures.saveFile(bmp, fileName);


            }
        });
        Dialog dialog = builder.create();
        return dialog;
    }

}
