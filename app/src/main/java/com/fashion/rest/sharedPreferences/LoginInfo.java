package com.fashion.rest.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class LoginInfo {

    private static final String LOGIN = "LOGIN";


    public static String getLoginOrNotFromSP(Context context) {
        String login;
        SharedPreferences shared = context.getSharedPreferences(LOGIN, MODE_PRIVATE);

        login = (shared.getString("login", ""));

        return login;
    }

    public static void saveLoginInSP(Context context, SharedPreferences SharedPreferences
            , SharedPreferences.Editor Editor, String login) {
        SharedPreferences = context.getSharedPreferences(LOGIN, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("login",login);

        Editor.commit();
    }

    public static void cleanLogin(Context context,SharedPreferences SharedPreferences,
                                                SharedPreferences.Editor editor) {
        SharedPreferences = context.getSharedPreferences(LOGIN, MODE_PRIVATE);
        editor = SharedPreferences.edit();
        editor.putString("login","");

        editor.commit();
    }

}
