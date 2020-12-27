package com.fashion.rest.view.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.model.Deal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterSuggestedToYou extends RecyclerView.Adapter<AdapterSuggestedToYou.ViewHolder>{

    private final Context context;
//    List<Offer> mList = new ArrayList<>();
    ArrayList<Deal> dealsArrayL = new ArrayList<>();
    public AdapterSuggestedToYou
            (Context context,ArrayList<Deal> dealsArrayL)
                {
                     this.context = context;
                     this.dealsArrayL = dealsArrayL;
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
//        changeFont(holder,context);
//        fillText(holder,context,position);
    }

//    private void fillText(ViewHolder holder, Context context, int position) {
//        holder.nameTV.setText(dealsArrayL.get(position).getName());
//        holder.desTV.setText(dealsArrayL.get(position).getDes());
//        holder.priceTV.setText(String.valueOf(dealsArrayL.get(position).getPrice().getNewPrice()));
//        holder.oldPrice.setText(String.valueOf(dealsArrayL.get(position).getPrice().getOldPrice()));
//    }
//
//    private void changeFont(ViewHolder holder, Context context) {
//        holder.nameTV.setTypeface(Functions.changeFontCategory(context));
//        holder.desTV.setTypeface(Functions.changeFontGeneral(context));
//        holder.priceTV.setTypeface(Functions.changeFontGeneral(context));
//        holder.oldPrice.setTypeface(Functions.changeFontGeneral(context));
//        holder.oldPrice.setPaintFlags(holder.priceTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//    }

    private void fillImage(Context context, ViewHolder holder, int position) {
        //product image
        Picasso.get()
                .load(dealsArrayL.get(position).getImage1())
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return dealsArrayL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        RelativeLayout contRL;
        TextView nameTV,desTV,priceTV,oldPrice;
        public ViewHolder(View itemView) {
            super(itemView);
//            nameTV = (TextView) itemView.findViewById(R.id.adapter_suggested_to_you_name_TV);
//            desTV = (TextView) itemView.findViewById(R.id.adapter_suggested_to_you_des_TV);
//            priceTV = (TextView) itemView.findViewById(R.id.adapter_suggested_to_you_meal_price_TV);
//            oldPrice = (TextView) itemView.findViewById(R.id.adapter_suggested_to_you_meal_old_price_TV);
            imageView = (ImageView) itemView.findViewById(R.id.adapter_suggested_to_you_IV) ;
//            contRL = (RelativeLayout) itemView.findViewById(R.id.adapter_suggested_to_you_cont) ;
        }

    }

}