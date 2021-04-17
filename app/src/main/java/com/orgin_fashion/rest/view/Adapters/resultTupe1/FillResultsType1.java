package com.orgin_fashion.rest.view.Adapters.resultTupe1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.orgin_fashion.rest.R;
import com.orgin_fashion.rest.functions.Functions;
import com.orgin_fashion.rest.model.ItemTest;
import com.orgin_fashion.rest.view.activity.ItemDetails;
import com.squareup.picasso.Picasso;

import static com.orgin_fashion.rest.apiURL.API.apiURLBase;
import static com.orgin_fashion.rest.functions.Functions.calculatePercentage;
import static com.orgin_fashion.rest.functions.Functions.getTextEngOrLocal;

public class FillResultsType1 {

    public static void fillItemCase1(ItemTest deal, Context context, ImageView imageView, TextView name, TextView price, TextView oldPrice
            , RelativeLayout offerCover, TextView offerPer, LinearLayout itemCover) {
        fillImage(context,imageView,deal);
        fillTV(deal,name,context);
        fillOffer(offerCover,offerPer,deal);
        fillPrice(price,oldPrice,deal);
        changeFont(context,name,price,oldPrice,offerPer);
        actionListenerToItem(context,itemCover,deal);
    }

    private static void actionListenerToItem(final Context context, LinearLayout itemCover, final ItemTest deal) {
        itemCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemDetails.class);
                intent.putExtra("item_object", deal);
                ((Activity)context).startActivity(intent);
                ((Activity)context).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
            }
        });
    }

    private static void fillPrice(TextView price, TextView oldPrice, ItemTest deal) {
        price.setText(String.valueOf(deal.getDiscountPrice()));
        oldPrice.setText(String.valueOf(deal.getPrice()));
    }

    private static void fillOffer(RelativeLayout offerCover, TextView offerPer,ItemTest deal) {
        int pic = R.drawable.offer;
        offerCover.setBackgroundResource(pic);
        offerPer.setText(calculatePercentage(deal.getPrice(),deal.getDiscountPrice()));
    }

    private static void fillTV(ItemTest itemTest, TextView name,Context context) {
        name.setText(getTextEngOrLocal(context,itemTest.getName(),itemTest.getName_local()));
    }

    private static void fillImage(Context context, ImageView imageView, ItemTest itemTest) {
        //product image

        Picasso.get()
                .load(apiURLBase()+itemTest.getFlagArrayL().get(0).getUrl())
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
