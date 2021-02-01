package com.fashion.rest.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import static android.support.v4.app.ActivityCompat.requestPermissions;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;

public class CheckPermission {

    private static final int PICK_FROM_GALLERY = 1;
    private static final int PHONE = 102;

    public static boolean checkPermissionMethodToSelectPhoto(Activity activity) {

        try {
            if (ActivityCompat.checkSelfPermission(activity,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(activity
                        , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}
                        , PICK_FROM_GALLERY);

                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkPermissionMethodToPhone(Activity activity) {
        try {
            if (checkSelfPermission(activity,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(activity
                        , new String[]{Manifest.permission.CALL_PHONE}
                        , PHONE);

                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}

