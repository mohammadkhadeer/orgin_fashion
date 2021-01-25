package com.fashion.rest.view.Adapters.resultTupe1;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Deal;
import com.fashion.rest.view.Adapters.AdapterSet;
import com.fashion.rest.view.Adapters.AdapterType2;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.fashion.rest.functions.Functions.fillSetArrayL;

public class FillResultsType1 {

    public static void fillItemCase1(Deal deal,Context context,ImageView imageView,TextView name,TextView price,TextView oldPrice,RelativeLayout offerCover,TextView offerPer) {
        fillImage(context,imageView,deal);
        fillTV(deal,name);
        fillOffer(offerCover,offerPer);
        fillPrice(price,oldPrice,deal);
        changeFont(context,name,price,oldPrice,offerPer);
    }

    private static void fillPrice(TextView price, TextView oldPrice, Deal deal) {
        price.setText(String.valueOf(deal.getPrice().getPrice()));
        oldPrice.setText(String.valueOf(deal.getPrice().getOldPrice()));
    }

    private static void fillOffer(RelativeLayout offerCover, TextView offerPer) {
        int pic = R.drawable.offer;
        offerCover.setBackgroundResource(pic);
        offerPer.setText("40%");
    }

    private static void fillTV(Deal deal, TextView name) {
        name.setText(String.valueOf(deal.getName()));
    }

    private static void fillImage(Context context, ImageView imageView, Deal deal) {
        //product image
        Picasso.get()
                .load(deal.getImage())
                .fit()
                .centerCrop()
                .into(imageView);

    }

    private static void changeFont(Context context,TextView name,TextView price,TextView oldPrice,TextView offerPer) {
        name.setTypeface(Functions.changeFontGeneral(context));
        price.setTypeface(Functions.changeFontGeneral(context));
        oldPrice.setTypeface(Functions.changeFontGeneral(context));
        offerPer.setTypeface(Functions.changeFontBold(context));
        oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
