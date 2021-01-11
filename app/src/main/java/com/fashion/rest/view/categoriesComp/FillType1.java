package com.fashion.rest.view.categoriesComp;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.fashion.rest.R;
import com.fashion.rest.model.Deal;
import com.fashion.rest.model.ListItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FillType1 {

    public static void fill(ImageView imageView, TextView nameTV, RelativeLayout coverRL, ListItem item, Context context) {

        //fillImage(imageView, item, context);
        fillText(nameTV,item,context);
        //changeFont(context);
        actionListenerToGoShowItemDetails(context, coverRL, item);
    }

    private static void fillText(TextView nameTV, ListItem item, Context context) {
        nameTV.setText(String.valueOf(item.getListType()));
//    nameTV.setText(String.valueOf(position));
    }

    private static void fillImage(ImageView itemImage,
                                  Deal item, Context context) {

        Picasso.get()
                .load(item.getImage())
                .fit()
                .centerCrop()
                .into(itemImage);

    }

    private static void actionListenerToGoShowItemDetails(final Context context, RelativeLayout cardButton, final ListItem item) {
        cardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"here" + item.getListType(),Toast.LENGTH_SHORT).show();
//        Bundle bundle = new Bundle();
//
//        bundle.putString("from", "search");
//        Intent intent = new Intent(context, ItemDetails.class);
//        intent.putExtras(bundle);
//        ((Activity) context).startActivity(intent);
//        ((Activity) context).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
            }
        });
    }

}
