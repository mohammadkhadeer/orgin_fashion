package com.blue_b.rest.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class NumberOfItemInCart {

    private static final String CART = "CART";


    public static String getNumberOfItemInCartFromSP(Context context) {
        String numberOfItems;
        SharedPreferences shared = context.getSharedPreferences(CART, MODE_PRIVATE);

        numberOfItems = (shared.getString("numberItem", ""));

        return numberOfItems;
    }

    public static void saveNumberOfItemsInCartInSP(Context context, SharedPreferences SharedPreferences
            , SharedPreferences.Editor Editor, String numberOfItem) {
        SharedPreferences = context.getSharedPreferences(CART, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("numberItem",numberOfItem);

        Editor.commit();
    }

    public static void cleanCart(Context context,SharedPreferences SharedPreferences,
                                                SharedPreferences.Editor editor) {
        SharedPreferences = context.getSharedPreferences(CART, MODE_PRIVATE);
        editor = SharedPreferences.edit();
        editor.putString("numberItem","");

        editor.commit();
    }

}
