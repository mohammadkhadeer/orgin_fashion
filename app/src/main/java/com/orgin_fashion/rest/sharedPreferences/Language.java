package com.orgin_fashion.rest.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Language {

    private static final String LANGUAGE = "LANGUAGE";


    public static String getLanguageFromSP(Context context) {
        String language;
        SharedPreferences shared = context.getSharedPreferences(LANGUAGE, MODE_PRIVATE);

        language = (shared.getString("language", ""));

        return language;
    }

    public static void saveLanguageInSP(Context context, SharedPreferences SharedPreferences
            , SharedPreferences.Editor Editor, String language) {
        SharedPreferences = context.getSharedPreferences(LANGUAGE, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("language",language);

        Editor.commit();
    }

    public static void cleanLanguage(Context context,SharedPreferences SharedPreferences,
                                                SharedPreferences.Editor editor) {
        SharedPreferences = context.getSharedPreferences(LANGUAGE, MODE_PRIVATE);
        editor = SharedPreferences.edit();
        editor.putString("language","");

        editor.commit();
    }

}
