package com.mile.android.entities.db;

import android.provider.BaseColumns;

/**
 * Created by yacovyitzhak on 19/06/2017.
 */

public class UserTable {


    /* Inner class that defines the table contents */
    public static class UserTableEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USER_ID = "UserId";
        public static final String COLUMN_NAME_IDFB = "IdFB";
        public static final String COLUMN_NAME_PHONENUM = "PhoneNum";
        public static final String COLUMN_NAME_NICKNAME = "NickName";
        public static final String COLUMN_NAME_MILEAGE = "Mileage";
        public static final String COLUMN_NAME_ADDRESS = "Address";

    }
}
