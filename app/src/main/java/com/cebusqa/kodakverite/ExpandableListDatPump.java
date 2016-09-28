package com.cebusqa.kodakverite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by anarte on 28/09/2016.
 */

public class ExpandableListDatPump {

    public static HashMap<String, List<String>> getData(){
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> quality = new ArrayList<String>();
        quality.add("Normal");
        quality.add("Low (Fast)");
        quality.add("High");

        List<String> color = new ArrayList<String>();
        color.add("Color");
        color.add("Gray Scale");
        color.add("Black & White");

        List<String> document = new ArrayList<String>();
        document.add("Text");
        document.add("Text/Graphics");

        List<String> saveAsType = new ArrayList<String>();
        saveAsType.add("PDF");
        saveAsType.add("JPG");

        expandableListDetail.put("Save as Type", saveAsType);
        expandableListDetail.put("Color", color);
        expandableListDetail.put("Document", document);
        expandableListDetail.put("Quality", quality);

        return expandableListDetail;
    }
}
