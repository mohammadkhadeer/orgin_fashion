package com.fashion.rest.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Country {

    private static final String COUNTRY = "COUNTRY";


    public static String getCountryFromSP(Context context) {
        String language;
        SharedPreferences shared = context.getSharedPreferences(COUNTRY, MODE_PRIVATE);

        language = (shared.getString("country_name_en", ""));

        return language;
    }

    public static String getCountryLocalFromSP(Context context) {
        String language;
        SharedPreferences shared = context.getSharedPreferences(COUNTRY, MODE_PRIVATE);

        language = (shared.getString("country_name_en", ""));

        return language;
    }

    public static String getCountryIdFromSP(Context context) {
        String language;
        SharedPreferences shared = context.getSharedPreferences(COUNTRY, MODE_PRIVATE);

        language = (shared.getString("country_id", ""));

        return language;
    }

    public static String getCountryUrlFromSP(Context context) {
        String language;
        SharedPreferences shared = context.getSharedPreferences(COUNTRY, MODE_PRIVATE);

        language = (shared.getString("country_url", ""));

        return language;
    }

    public static void saveCountryInSP(Context context, SharedPreferences SharedPreferences
            , SharedPreferences.Editor Editor, String country,String country_local,String country_id,String Url) {
        SharedPreferences = context.getSharedPreferences(COUNTRY, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("country_name_en",country);
        Editor.putString("country_name_local",country_local);
        Editor.putString("country_id",country_id);
        Editor.putString("country_url",Url);

        Editor.commit();
    }

    public static void cleanCountry(Context context,SharedPreferences SharedPreferences,
                                                SharedPreferences.Editor editor) {
        SharedPreferences = context.getSharedPreferences(COUNTRY, MODE_PRIVATE);
        editor = SharedPreferences.edit();
        editor.putString("language","");

        editor.commit();
    }

}
