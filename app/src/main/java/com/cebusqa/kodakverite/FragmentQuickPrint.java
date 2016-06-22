package com.cebusqa.kodakverite;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class FragmentQuickPrint extends Fragment {

    ImageButton photo4x6, photoletter, document;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quick_print, container, false);

        photo4x6 = (ImageButton)view.findViewById(R.id.photo4x6);
        photoletter = (ImageButton)view.findViewById(R.id.photoletter);
        document = (ImageButton)view.findViewById(R.id.document);

        photo4x6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photo4x6.setImageResource(R.mipmap.photo4x6borderless_check);
                photoletter.setImageResource(R.mipmap.photoletterborderless_uncheck);
                document.setImageResource(R.mipmap.documentletter_uncheck);

                }
            });

        photoletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photo4x6.setImageResource(R.mipmap.photo4x6borderless_uncheck);
                photoletter.setImageResource(R.mipmap.photoletterborderless_check);
                document.setImageResource(R.mipmap.documentletter_uncheck);

            }
        });

        document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photo4x6.setImageResource(R.mipmap.photo4x6borderless_uncheck);
                photoletter.setImageResource(R.mipmap.photoletterborderless_uncheck);
                document.setImageResource(R.mipmap.documentletter_check);

            }
        });


        return view;




    }


}
