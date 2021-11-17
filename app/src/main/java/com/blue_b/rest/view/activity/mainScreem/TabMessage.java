package com.blue_b.rest.view.activity.mainScreem;

import com.blue_b.rest.R;

class TabMessage {
    public static String get(int menuItemId, boolean isReselection) {
        String message = "Content for ";

        switch (menuItemId) {
            case R.id.tab_home:
                message += "Home";
                break;

            case R.id.tab_notifications:
                message += "favorites";
                break;
            case R.id.tab_profile:
                message += "friends";
                break;
        }

        if (isReselection) {
            message += " WAS RESELECTED! YAY!";
        }

        return message;
    }

//    public static String get(int menuItemId, boolean isReselection) {
//        String message = "Content for ";
//
//        switch (menuItemId) {
//            case R.id.tab_home:
//                message += "Home";
//                break;
//            case R.id.tab_messages:
//                message += "nearby";
//                break;
//            case R.id.tab_adv:
//                message += "food";
//                break;
//            case R.id.tab_notifications:
//                message += "favorites";
//                break;
//            case R.id.tab_profile:
//                message += "friends";
//                break;
//        }
//
//        if (isReselection) {
//            message += " WAS RESELECTED! YAY!";
//        }
//
//        return message;
//    }
}
