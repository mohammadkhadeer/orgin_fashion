package com.blue_b.rest.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class NumberOfNotification {

    private static final String NOTIFICATIONS = "NOTIFICATIONS";
    static SharedPreferences SharedPreferencesN;
    static SharedPreferences.Editor EditorN;

    public static String getNumberOfItemInCartFromSP(Context context) {
        String numberOfItems;
        SharedPreferences shared = context.getSharedPreferences(NOTIFICATIONS, MODE_PRIVATE);

        numberOfItems = (shared.getString("notification", ""));

        return numberOfItems;
    }

    public static void saveNumberOfItemsInCartInSP(Context context, String numberOfItem) {
        SharedPreferencesN = context.getSharedPreferences(NOTIFICATIONS, MODE_PRIVATE);
        EditorN = SharedPreferencesN.edit();

        EditorN.putString("notification",numberOfItem);

        EditorN.commit();
    }

    public static void cleanNotifications(Context context) {
        SharedPreferencesN = context.getSharedPreferences(NOTIFICATIONS, MODE_PRIVATE);
        EditorN = SharedPreferencesN.edit();
        EditorN.putString("notification","");

        EditorN.commit();
    }

}
