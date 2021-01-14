package com.fashion.rest.view.categoriesComp;

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
import com.fashion.rest.model.Deal;

public class Functions {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void changeOffersGradientsAndTextColorCases(RelativeLayout coverRL, int position, Context context, Deal object, TextView textView
            , TextView desTV,RelativeLayout callRL,TextView callTV,TextView whatsApp) {

        if (object.getOffersGradientsWithTextColor().getGradient().equals("roseanna"))
        {
            colorBlackCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
            //this method to mad Gradient dinamec
            setGradients(coverRL,object,context);
        }
        if (object.getOffersGradientsWithTextColor().getGradient().equals("lady_lips"))
        {
            colorWhiteCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.roseanna));
        }
        if (object.getOffersGradientsWithTextColor().getGradient().equals("sexy_blue"))
        {
            colorBlackCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.sexy_blue));
        }
        if (object.getOffersGradientsWithTextColor().getGradient().equals("purple_love"))
        {
            colorWhiteCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.purple_love));
        }
        if (object.getOffersGradientsWithTextColor().getGradient().equals("mauve"))
        {
            colorWhiteCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.mauve));
        }
        if (object.getOffersGradientsWithTextColor().getGradient().equals("frost"))
        {
            colorWhiteCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.frost));
        }
        if (object.getOffersGradientsWithTextColor().getGradient().equals("decent"))
        {
            colorBlackCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.decent));
        }
        if (object.getOffersGradientsWithTextColor().getGradient().equals("aubergine"))
        {
            colorWhiteCollection(textView,desTV,callTV,whatsApp,context,object,callRL);
            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.aubergine));
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static void setGradients(RelativeLayout coverRL, Deal object, Context context) {
        int color = Color.parseColor("#ffc3a0");
        int color2 = Color.parseColor("#ffafbd");
        int color3 = Color.parseColor("#00ffafbd");

        GradientDrawable drawable = new GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP, new int[] {color ,color2,color3
        });
        coverRL.setBackground(drawable);
    }

    public static void callFunction(RelativeLayout callRl, final Context context) {
        callRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Call function",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static void colorBlackCollection(TextView textView, TextView desTV, TextView callTV,TextView whatsApp, Context context, Deal object,RelativeLayout callRL) {
        textView.setTextColor(Color.parseColor(object.getOffersGradientsWithTextColor().getFontColor()));
        desTV.setTextColor(Color.parseColor(object.getOffersGradientsWithTextColor().getFontColor()));
        callTV.setTextColor(Color.parseColor(object.getOffersGradientsWithTextColor().getFontColor()));
        whatsApp.setTextColor(Color.parseColor(object.getOffersGradientsWithTextColor().getFontColor()));
        callRL.setBackground(ContextCompat.getDrawable(context, R.drawable.black_call));
    }

    private static void colorWhiteCollection(TextView textView, TextView desTV, TextView callTV,TextView whatsApp, Context context, Deal object,RelativeLayout callRL) {
        textView.setTextColor(Color.parseColor(object.getOffersGradientsWithTextColor().getFontColor()));
        desTV.setTextColor(Color.parseColor(object.getOffersGradientsWithTextColor().getFontColor()));
        callTV.setTextColor(Color.parseColor(object.getOffersGradientsWithTextColor().getFontColor()));
        whatsApp.setTextColor(Color.parseColor(object.getOffersGradientsWithTextColor().getFontColor()));
        callRL.setBackground(ContextCompat.getDrawable(context, R.drawable.white_call));
    }
}
