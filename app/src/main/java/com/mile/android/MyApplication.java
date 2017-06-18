package com.mile.android;

import android.app.Application;
import android.util.Log;
import android.util.Patterns;

import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by yacovyitzhak on 04/02/2017.
 */

public class MyApplication extends Application {
    private static final String LOG_TAG = "Application";
    //init hashtable for globals vars
    private Hashtable hashtable = new Hashtable();

    private Hashtable<Integer,Integer> pendingVoucherHashtable = new Hashtable();

    public static final String KEY_HALL = "KEY_HALL";
    public static final String KEY_PARTY_POS = "KEY_PARTY_POS";

    public Hashtable getHashtable() {
        return hashtable;
    }

    public Hashtable<Integer,Integer> getPendingVoucherHashtable() {
        return pendingVoucherHashtable;
    }

    @Override
    public void onCreate() {

        super.onCreate();


    }

}