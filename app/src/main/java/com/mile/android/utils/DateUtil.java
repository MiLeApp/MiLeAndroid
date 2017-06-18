package com.mile.android.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yacovyitzhak on 04/02/2017.
 */

public class DateUtil {
    public static final SimpleDateFormat formatter_dd_MM_yyyy_HH_mm_ss = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public static final SimpleDateFormat timeStampFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS");
    public static final SimpleDateFormat formatter_HH_mm = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat formatter_HH_mm_ss = new SimpleDateFormat("HH:mm:ss");
    public static Date parse(String dateStr,SimpleDateFormat formatter) throws ParseException {
        return formatter.parse(dateStr);
    }

}
