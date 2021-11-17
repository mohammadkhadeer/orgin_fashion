package com.blue_b.rest.functions;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class RegistrationFunction {

    public static String testFirstName(String firstName) {
        if (firstName.isEmpty() || firstName.length()<3
                || firstName.length()>10)
        {
            if (firstName.isEmpty())
            {
                return "empty";
            }
            if (firstName.length()<3)
            {
                return "small";
            }
            if (firstName.length()>10)
            {
                return "big";
            }
        }
        return "done";
    }

    public static String testLength(String name) {
        String result="done";
        if (name.length()<3)
        {result = "small";}
        if (name.length()>10)
        {result = "big";}
        return result;
    }

    public static String checkIfUserNameAndPasswordEmpty
            (String userName,String password) {
        String result="done";
        if (userName.isEmpty() && password.isEmpty())
        {
            result = "unAndPE";
            return result;
        }else{
            if (userName.isEmpty() || password.isEmpty())
            {
                if (userName.isEmpty())
                {
                    result = "unE";
                    return result;
                }
                if (password.isEmpty())
                {
                    result = "pE";
                    return result;
                }
            }
        }
        return result;
    }

    public static String checkIfFirstNameAndLastNameEmpty
            (String firstName,String LastName) {
        String result="done";
        if (firstName.isEmpty() && LastName.isEmpty())
        {
            result = "fnAndLnE";
            return result;
        }else{
            if (firstName.isEmpty() || LastName.isEmpty())
            {
                if (firstName.isEmpty())
                {
                    result = "fnE";
                    return result;
                }
                if (LastName.isEmpty())
                {
                    result = "lnE";
                    return result;
                }
            }
        }
        return result;
    }

    public static String checkIfNameRealName(String firstName) {
        String result="done";
        char[] checkIfUserInsertFakeNameAry = firstName.toCharArray();
        if (firstName.length()>1)
        {
            if (checkIfUserInsertFakeNameAry[0] == checkIfUserInsertFakeNameAry[1])
            {
                result = "notR";
                return result;
            }
        }
        return result;
    }

    public static String checkIfNameHaveNumber(String name) {
        String result="done";
        if (name.matches(".*\\d+.*"))
        {
            result = "haveN";
            return result;
        }
        return result;
    }

    public static String checkPasswordLength(String password) {
        String result="done";
        if (password.length()<6)
        {
            result = "short";
            return result;
        }
        if (password.length()>10)
        {
            result = "long";
            return result;
        }
        return result;
    }
    public static boolean isOnline(Context context) {
        //to check if connected with internet
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {

            return false;
        }
    }

}
