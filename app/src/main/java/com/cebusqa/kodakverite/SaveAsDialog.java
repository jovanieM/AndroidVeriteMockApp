package com.cebusqa.kodakverite;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Cebu SQA on 20/06/2016.
 */
public class SaveAsDialog extends DialogFragment implements View.OnClickListener{
    Communicator communicator;
    Button yes, no;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (Communicator) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.save_as_dialog, null);
        no = (Button) view.findViewById(R.id.no);
        yes = (Button) view.findViewById(R.id.yes);
        no.setOnClickListener(this);
        yes.setOnClickListener(this);
        setCancelable(false);
        return view;
        // super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.no){
            dismiss();
        }
        if(v.getId()==R.id.yes){

        }
    }
    interface Communicator{
        public void onDialogMessage(String message);
    }
}
