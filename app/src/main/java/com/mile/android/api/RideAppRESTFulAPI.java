package com.mile.android.api;

/**
 * Created by yacovyitzhak on 03/02/2017.
 */

public class RideAppRESTFulAPI {

    private static final String URL = "http://104.154.69.54/api";

    private static String EXT_URL;
    public static final String ACTION_GROUP = "group";
    public static final String ACTION_VOUCHER = "voucher";
    public static final String ACTION_HALL = "hall";

    public static String getURL(){
        if (EXT_URL == null){
            return URL;
        }else{
            return EXT_URL;
        }
    }

    public static void setURL(String url){
        EXT_URL = url;
    }
}
