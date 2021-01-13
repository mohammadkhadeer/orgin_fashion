package com.fashion.rest.view.categoriesComp;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.model.Deal;
import com.fashion.rest.model.OffersGradientsWithTextColor;

public class Functions {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void changeOffersGradientsAndTextColorCases(RelativeLayout coverRL, int position, Context context, Deal object, TextView textView) {

        if (object.getOffersGradientsWithTextColor().getGradient().equals("roseanna"))
        {
            textView.setTextColor(context.getResources().getColor(R.color.colorBlack));
            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.roseanna));
        }
        if (object.getOffersGradientsWithTextColor().getGradient().equals("lady_lips"))
        {
            textView.setTextColor(context.getResources().getColor(R.color.colorBlack));
            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.lady_lips));
        }
        if (object.getOffersGradientsWithTextColor().getGradient().equals("sexy_blue"))
        {
            textView.setTextColor(context.getResources().getColor(R.color.colorBlack));
            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.sexy_blue));
        }
        if (object.getOffersGradientsWithTextColor().getGradient().equals("purple_love"))
        {
            textView.setTextColor(context.getResources().getColor(R.color.colorWhite));
            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.purple_love));
        }
        if (object.getOffersGradientsWithTextColor().getGradient().equals("mauve"))
        {
            textView.setTextColor(context.getResources().getColor(R.color.colorWhite));
            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.mauve));
        }
        if (object.getOffersGradientsWithTextColor().getGradient().equals("frost"))
        {
            textView.setTextColor(context.getResources().getColor(R.color.colorWhite));
            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.frost));
        }
        if (object.getOffersGradientsWithTextColor().getGradient().equals("decent"))
        {
            textView.setTextColor(context.getResources().getColor(R.color.colorBlack));
            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.decent));
        }
        if (object.getOffersGradientsWithTextColor().getGradient().equals("aubergine"))
        {
            textView.setTextColor(context.getResources().getColor(R.color.colorWhite));
            coverRL.setBackground(ContextCompat.getDrawable(context, R.drawable.aubergine));
        }

    }
}
