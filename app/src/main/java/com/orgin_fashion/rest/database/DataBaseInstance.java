package com.orgin_fashion.rest.database;

import android.content.Context;

public class DataBaseInstance {

    public static DBHelper getDataBaseInstance(Context context) {
        DBHelper database = new DBHelper(context);
        return  database;
    }
}
