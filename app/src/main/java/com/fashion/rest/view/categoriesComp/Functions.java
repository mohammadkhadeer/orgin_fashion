package com.fashion.rest.view.categoriesComp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.fashion.rest.R;
import com.fashion.rest.model.Offer;
import com.fashion.rest.permission.CheckPermission;

import static com.fashion.rest.functions.ItemDetailsFunctions.callAds;

public class Functions {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void changeOffersGradientsAndTextColorCases(RelativeLayout coverRL, int position, Context context, Offer object, TextView textView
            , TextView desTV, RelativeLayout callRL, TextView callTV, TextView whatsApp) {

        if (object.isBlack())
            colorBlackCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
        else
            colorWhiteCollection(textView,desTV,callTV,whatsApp,context,object,callRL);

        //this method to mad Gradient dinamec
        setGradients(coverRL,object,context);
//        if (object.getOffersGradientsWithTextColor().getGradient().equals("roseanna"))
//        {
//            // add to object text color
//            colorBlackCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
//            //this method to mad Gradient dinamec
//            setGradients(coverRL,object,context);
//        }
//        if (object.getOffersGradientsWithTextColor().getGradient().equals("lady_lips"))
//        {
//            colorWhiteCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
//            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.roseanna));
//        }
//        if (object.getOffersGradientsWithTextColor().getGradient().equals("sexy_blue"))
//        {
//            colorBlackCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
//            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.sexy_blue));
//        }
//        if (object.getOffersGradientsWithTextColor().getGradient().equals("purple_love"))
//        {
//            colorWhiteCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
//            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.purple_love));
//        }
//        if (object.getOffersGradientsWithTextColor().getGradient().equals("mauve"))
//        {
//            colorWhiteCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
//            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.mauve));
//        }
//        if (object.getOffersGradientsWithTextColor().getGradient().equals("frost"))
//        {
//            colorWhiteCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
//            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.frost));
//        }
//        if (object.getOffersGradientsWithTextColor().getGradient().equals("decent"))
//        {
//            colorBlackCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
//            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.decent));
//        }
//        if (object.getOffersGradientsWithTextColor().getGradient().equals("aubergine"))
//        {
//            colorWhiteCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
//            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.aubergine));
//        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static void setGradients(RelativeLayout coverRL, Offer object, Context context) {
        String startColor ="#"+object.getColor();
        String endColor ="#"+object.getSecondaryColor();
        int color = Color.parseColor(startColor);
        int color2 = Color.parseColor(endColor);
        int color3 = Color.parseColor("#00ffafbd");

        GradientDrawable drawable = new GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP, new int[] {color ,color2,color3
        });
        coverRL.setBackground(drawable);
    }

    public static void callFunction(RelativeLayout callRl, final Context context, final String phoneNumber) {
        callRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckPermission.checkPermissionMethodToPhone((Activity) context) == true) {
                    callAds(context,phoneNumber);
                }
            }
        });
    }

    private static void colorBlackCollection(TextView textView, TextView desTV, TextView callTV,TextView whatsApp, Context context, Offer object,RelativeLayout callRL) {
        textView.setTextColor(Color.parseColor("#000000"));
        desTV.setTextColor(Color.parseColor("#000000"));
        callTV.setTextColor(Color.parseColor("#000000"));
        whatsApp.setTextColor(Color.parseColor("#000000"));
        callRL.setBackground(ContextCompat.getDrawable(context, R.drawable.black_call));
    }

    private static void colorWhiteCollection(TextView textView, TextView desTV, TextView callTV,TextView whatsApp, Context context, Offer object,RelativeLayout callRL) {
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        desTV.setTextColor(Color.parseColor("#FFFFFF"));
        callTV.setTextColor(Color.parseColor("#FFFFFF"));
        whatsApp.setTextColor(Color.parseColor("#FFFFFF"));
        callRL.setBackground(ContextCompat.getDrawable(context, R.drawable.white_call));
    }
}
