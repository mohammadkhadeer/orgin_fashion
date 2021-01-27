package com.fashion.rest.view.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Deal;
import com.fashion.rest.view.activity.ItemDetails;
import com.fashion.rest.view.activity.SubCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterType3 extends RecyclerView.Adapter<AdapterType3.ViewHolder>{

    private final Context context;
//    List<Offer> mList = new ArrayList<>();
    ArrayList<Deal> dealsArrayL = new ArrayList<>();
    String cat;
    public AdapterType3
            (Context context,ArrayList<Deal> dealsArrayL,String cat)
                {
                     this.context = context;
                    this.dealsArrayL = dealsArrayL;
                    this.cat = cat;
                }

    public AdapterType3.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_typ3, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterType3.ViewHolder holder, final int position) {

        fillImage(context,holder,position);
        changeFont(holder,context);
        fillText(holder,context,position);
        actionListenerToCard(holder,context,position);
    }

    private void actionListenerToCard(ViewHolder holder, final Context context, final int position) {
        holder.coverRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("cat_name",cat);

                Intent intent = new Intent(context, SubCategory.class);
                intent.putExtras(bundle);
                ((Activity)context).startActivity(intent);
                ((Activity)context).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
            }
        });
    }

    private void fillText(ViewHolder holder, Context context, int position) {
        holder.nameTV.setText(dealsArrayL.get(position).getName());
//        holder.desTV.setText(dealsArrayL.get(position).getDes());
//        holder.priceTV.setText(String.valueOf(dealsArrayL.get(position).getPrice().getNewPrice()));
//        holder.oldPrice.setText(String.valueOf(dealsArrayL.get(position).getPrice().getOldPrice()));
    }

    private void changeFont(ViewHolder holder, Context context) {
        holder.nameTV.setTypeface(Functions.changeFontGeneral(context));
//        holder.desTV.setTypeface(Functions.changeFontGeneral(context));
//        holder.priceTV.setTypeface(Functions.changeFontGeneral(context));
//        holder.oldPrice.setTypeface(Functions.changeFontGeneral(context));
//        holder.oldPrice.setPaintFlags(holder.priceTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void fillImage(Context context, ViewHolder holder, int position) {
        //product image
//        Picasso.get()
//                .load(dealsArrayL.get(position).getImage1())
//                .fit()
//                .centerCrop()
//                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return dealsArrayL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        RelativeLayout contRL,coverRL,buttonAddToCart;
        TextView nameTV,desTV,priceTV,oldPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTV = (TextView) itemView.findViewById(R.id.type3_tv);
//            desTV = (TextView) itemView.findViewById(R.id.adapter_suggested_to_you_des_TV);
//            priceTV = (TextView) itemView.findViewById(R.id.adapter_suggested_to_you_meal_price_TV);
//            oldPrice = (TextView) itemView.findViewById(R.id.adapter_suggested_to_you_meal_old_price_TV);
//            imageView = (ImageView) itemView.findViewById(R.id.adapter_suggested_to_you_IV) ;
//            contRL = (RelativeLayout) itemView.findViewById(R.id.adapter_suggested_to_you_cont) ;
              coverRL = (RelativeLayout) itemView.findViewById(R.id.cover_adapter_set) ;
//            buttonAddToCart = (RelativeLayout) itemView.findViewById(R.id.adapter_suggested_call_button);
        }

    }

}