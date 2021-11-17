package com.blue_b.rest.view.categoriesComp;

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

import com.blue_b.rest.R;
import com.blue_b.rest.model.Offer;
import com.blue_b.rest.permission.CheckPermission;
import com.blue_b.rest.functions.ItemDetailsFunctions;

public class Functions {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void changeOffersGradientsAndTextColorCases(RelativeLayout coverRL, int position, Context context, Offer object, TextView textView
            , TextView desTV, RelativeLayout callRL, TextView callTV, TextView whatsApp, TextView dis_price, TextView price) {

        if (object.isBlack())
            colorBlackCollection(textView,desTV,callTV,whatsApp,context,object,callRL,dis_price,price);
        else
            colorWhiteCollection(textView,desTV,callTV,whatsApp,context,object,callRL,dis_price,price);

        setGradients(coverRL,object,context);
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
                    ItemDetailsFunctions.callAds(context,phoneNumber);
                }
            }
        });
    }

    private static void colorBlackCollection(TextView textView, TextView desTV, TextView callTV,TextView whatsApp
            , Context context, Offer object,RelativeLayout callRL, TextView dis_price,TextView price) {
        textView.setTextColor(Color.parseColor("#000000"));
        desTV.setTextColor(Color.parseColor("#000000"));
        callTV.setTextColor(Color.parseColor("#000000"));
        whatsApp.setTextColor(Color.parseColor("#000000"));
        dis_price.setTextColor(Color.parseColor("#000000"));
        price.setTextColor(Color.parseColor("#000000"));
        callRL.setBackground(ContextCompat.getDrawable(context, R.drawable.black_call));
    }

    private static void colorWhiteCollection(TextView textView, TextView desTV, TextView callTV,TextView whatsApp
            , Context context, Offer object,RelativeLayout callRL, TextView dis_price,TextView price) {
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        desTV.setTextColor(Color.parseColor("#FFFFFF"));
        callTV.setTextColor(Color.parseColor("#FFFFFF"));
        whatsApp.setTextColor(Color.parseColor("#FFFFFF"));
        dis_price.setTextColor(Color.parseColor("#FFFFFF"));
        price.setTextColor(Color.parseColor("#FFFFFF"));
        callRL.setBackground(ContextCompat.getDrawable(context, R.drawable.white_call));
    }
}
