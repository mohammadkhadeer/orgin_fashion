package com.azez.rest.view.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azez.rest.R;
import com.azez.rest.functions.Functions;
import com.azez.rest.model.Offer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterSuggestedToYou extends RecyclerView.Adapter<AdapterSuggestedToYou.ViewHolder>{

    private final Context context;
    List<Offer> mList = new ArrayList<>();

    public AdapterSuggestedToYou
            (Context context,List<Offer> mList)
                {
                     this.context = context;
                     this.mList = mList;
                }

    public AdapterSuggestedToYou.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_suggested_to_you, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterSuggestedToYou.ViewHolder holder, final int position) {

        fillImage(context,holder,position);
        holder.textView.setText(mList.get(position).getEnNameStr());
        holder.resNameTextView.setText(mList.get(position).getRestaurantsNameStr());
        holder.resNameTextView.setTypeface(Functions.changeFontGeneral(context));
        holder.textViewPrice.setText(String.valueOf(mList.get(position).getPriceInt()) + "  AED");
    }

    private void fillImage(Context context, ViewHolder holder, int position) {
        //meal image
        Picasso.with(context).load(mList.get(position).getImagePathStr())
                .fit()
                .centerCrop()
                .into(holder.imageView);
        //res image
        Picasso.with(context).load(mList.get(position).getRestaurantsImageStr())
                .fit()
                .centerCrop()
                .into(holder.resImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,resImageView;
        RelativeLayout contRL;
        TextView textView,resNameTextView,textViewPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.adapter_suggested_to_you_meal_name_TV);
            resNameTextView = (TextView) itemView.findViewById(R.id.adapter_suggested_to_you_res_name_IV);
            textViewPrice = (TextView) itemView.findViewById(R.id.adapter_suggested_to_you_meal_price_TV);
            imageView = (ImageView) itemView.findViewById(R.id.adapter_suggested_to_you_IV) ;
            resImageView = (ImageView) itemView.findViewById(R.id.adapter_suggested_to_you_res_image_IV) ;
            contRL = (RelativeLayout) itemView.findViewById(R.id.adapter_suggested_to_you_cont) ;
        }

    }

}