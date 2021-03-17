package com.fashion.rest.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class LoginInfo {

    private static final String LOGIN = "LOGIN";

    static SharedPreferences SharedPreferences;
    static SharedPreferences.Editor Editor;

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

    public static void saveTokenInSP(Context context, String token) {
        SharedPreferences = context.getSharedPreferences(LOGIN, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("token",token);

        Editor.commit();
    }

    public static String getTokenFromSP(Context context) {
        String token;
        SharedPreferences shared = context.getSharedPreferences(LOGIN, MODE_PRIVATE);

        token = (shared.getString("token", ""));

        return token;
    }

    public static void cleanLogin(Context context) {
        SharedPreferences = context.getSharedPreferences(LOGIN, MODE_PRIVATE);
        Editor = SharedPreferences.edit();
        Editor.putString("login","");
        Editor.putString("name","");
        Editor.putString("email","");
        Editor.putString("photo","");
        Editor.putString("type","");

        Editor.commit();
    }

    public static void saveLoginInfoInSP(Context context,String name,String email,String photo,String type) {
        SharedPreferences = context.getSharedPreferences(LOGIN, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("name",name);
        Editor.putString("email",email);
        Editor.putString("photo",photo);
        Editor.putString("type",type);

        Editor.commit();
    }

    public static String getNameFromSP(Context context) {
        String login;
        SharedPreferences shared = context.getSharedPreferences(LOGIN, MODE_PRIVATE);

        login = (shared.getString("name", ""));

        return login;
    }

    public static String getEmailFromSP(Context context) {
        String login;
        SharedPreferences shared = context.getSharedPreferences(LOGIN, MODE_PRIVATE);

        login = (shared.getString("email", ""));

        return login;
    }

    public static String getPhotoFromSP(Context context) {
        String login;
        SharedPreferences shared = context.getSharedPreferences(LOGIN, MODE_PRIVATE);

        login = (shared.getString("photo", ""));

        return login;
    }

    public static String getTypeFromSP(Context context) {
        String login;
        SharedPreferences shared = context.getSharedPreferences(LOGIN, MODE_PRIVATE);

        login = (shared.getString("type", ""));

        return login;
    }

}
