package com.mile.android.utils;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by yacovyitzhak on 09/06/2017.
 */

public class GooglePlayServices {
    /**
     * Check if correct Play Services version is available on the device.
     *
     */
    public static boolean checkGooglePlayServiceAvailability(Context context) {

        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        return status == ConnectionResult.SUCCESS;
    }
    public static void openGooglePlayService(Activity activity){
        GoogleApiAvailability.getInstance().makeGooglePlayServicesAvailable(activity);
    }

}
