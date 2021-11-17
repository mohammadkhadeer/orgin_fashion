package com.blue_b.rest.database;

import android.content.Context;

public class DataBaseInstance {

    public static DBHelper getDataBaseInstance(Context context) {
        DBHelper database = new DBHelper(context);
        return  database;
    }
}
