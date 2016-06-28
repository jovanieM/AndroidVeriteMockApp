package com.cebusqa.kodakverite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class FragmentTwo extends Fragment {

    ListView lvCopySetting;
    String color_item;
    TextView tvCopyItem_item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_copy_setting, container, false);
        lvCopySetting = (ListView) view.findViewById(R.id.lv_copy_setting);
        lvCopySetting.setAdapter(new CopyAdapter(getActivity()));
        lvCopySetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        String[] colorItems = getResources().getStringArray(R.array.Color_copy);

                        final AlertDialog.Builder alertColor = new AlertDialog.Builder(getActivity());
                        LayoutInflater inflater = getActivity().getLayoutInflater();
                        View convertView = inflater.inflate(R.layout.listview, null);
                        alertColor.setView(convertView);
                        alertColor.setTitle("Color");
                        final ListView lvColor = (ListView) convertView.findViewById(R.id.lv_item_item);
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, colorItems);
                        lvColor.setAdapter(adapter);
                        lvColor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                view = null;

                                switch (i){
                                    case 0:
                                        color_item = adapterView.getItemAtPosition(i).toString();
                                        tvCopyItem_item = (TextView) view.findViewById(R.id.tv_copy_setting_item);
                                        tvCopyItem_item.setText(color_item);
                                        break;

                                    default:
                                        break;
                                }
                            }

                        });
                        alertColor.show();

                        break;
                    case 1:
                        String[] paperSizeItems = getResources().getStringArray(R.array.Paper_size_copy);

                        AlertDialog.Builder alertPaperSize = new AlertDialog.Builder(getActivity());
                        LayoutInflater inflater_paper_size = getActivity().getLayoutInflater();
                        View viewPaperSize = inflater_paper_size.inflate(R.layout.listview, null);
                        alertPaperSize.setView(viewPaperSize);
                        alertPaperSize.setTitle("Paper Size");
                        ListView lvPaperSize = (ListView) viewPaperSize.findViewById(R.id.lv_item_item);
                        ArrayAdapter<String> adapterPaperSize = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, paperSizeItems);
                        lvPaperSize.setAdapter(adapterPaperSize);
                        alertPaperSize.show();
                        break;

                    case 2:
                        String[] paperTypeItems = getResources().getStringArray(R.array.Paper_type);

                        AlertDialog.Builder alertPaperType = new AlertDialog.Builder(getActivity());
                        LayoutInflater inflater_paper_type = getActivity().getLayoutInflater();
                        View viewPaperType = inflater_paper_type.inflate(R.layout.listview, null);
                        alertPaperType.setView(viewPaperType);
                        alertPaperType.setTitle("Paper Type");
                        ListView lvPaperType = (ListView) viewPaperType.findViewById(R.id.lv_item_item);
                        ArrayAdapter<String> adapterPaperType = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, paperTypeItems);
                        lvPaperType.setAdapter(adapterPaperType);
                        alertPaperType.show();
                        break;

                    case 3:
                       String[] qualityItems = getResources().getStringArray(R.array.Paper_type);

                        AlertDialog.Builder alertQuality = new AlertDialog.Builder(getActivity());
                        LayoutInflater inflater_quality = getActivity().getLayoutInflater();
                        View viewQuality = inflater_quality.inflate(R.layout.listview, null);
                        alertQuality.setView(viewQuality);
                        alertQuality.setTitle("Quality");
                        ListView lvQuality = (ListView) viewQuality.findViewById(R.id.lv_item_item);
                        ArrayAdapter<String> adapterQuality = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, qualityItems);
                        lvQuality.setAdapter(adapterQuality);
                        alertQuality.show();
                        break;

                    case 4:
                        Toast.makeText(getActivity(), "Copy Resize", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(getActivity(), "Pages Per Side", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        Toast.makeText(getActivity(), "Brightness", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }

    class SingleRow{
        String copyDesc, copyItem;

        SingleRow(String copy_Desc, String copy_Items){
            this.copyDesc = copy_Desc;
            this.copyItem = copy_Items;
        }
    }

    class Holder{
        TextView tvCopyDesc;
        TextView tvCopyItem;

        Holder(View v){
            tvCopyDesc = (TextView) v.findViewById(R.id.tv_copy_setting_desc);
            tvCopyItem = (TextView) v.findViewById(R.id.tv_copy_setting_item);
        }

    }

    class CopyAdapter extends BaseAdapter{

        ArrayList<SingleRow> list;
        Context context;

        CopyAdapter(Context c){
            context = c;
            list = new ArrayList<>();
            String[] copyDesc = getResources().getStringArray(R.array.copy_desc);
            String[] copyDescItems = {"Color", "Letter", "Plain", "Text/Graphics", "100%", "One", "3"};

            for(int i = 0; i < copyDesc.length; i++){
                list.add(new SingleRow(copyDesc[i], copyDescItems[i]));
            }
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View row = view;
            Holder holder = null;

            if(row == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.items_copy_settings, viewGroup, false);
                holder = new Holder(row);
                row.setTag(holder);
            }else{
                holder = (Holder) row.getTag();
            }

            SingleRow temp = list.get(i);

            holder.tvCopyDesc.setText(temp.copyDesc);
            holder.tvCopyItem.setText(temp.copyItem);

            return row;
        }
    }
}

