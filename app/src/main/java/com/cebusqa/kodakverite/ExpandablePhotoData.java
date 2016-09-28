package com.cebusqa.kodakverite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by anarte on 28/09/2016.
 */

public class ExpandablePhotoData {

    public static HashMap<String, List<String>> getData(){
        HashMap<String, List<String>> expandablePhotoDetail = new HashMap<String, List<String>>();

        List<String> quality = new ArrayList<String>();
        quality.add("Low (Fast)");
        quality.add("Normal");
        quality.add("High");

        List<String> color = new ArrayList<String>();
        color.add("Color");
        color.add("Gray Scale");
        color.add("Black & White");

        List<String> document = new ArrayList<String>();
        document.add("Photo");

        expandablePhotoDetail.put("Color", color);
        expandablePhotoDetail.put("Document", document);
        expandablePhotoDetail.put("Quality", quality);

        return expandablePhotoDetail;
    }
}
