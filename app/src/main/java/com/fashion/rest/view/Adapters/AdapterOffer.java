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

public class AdapterOffer extends RecyclerView.Adapter<AdapterOffer.ViewHolder>{

    private final Context context;
//    List<Offer> mList = new ArrayList<>();
    public ArrayList<Deal> dealsArrayList = new ArrayList<>();

    public AdapterOffer
            (Context context,ArrayList<Deal> dealsArrayL)
                {
                     this.context = context;
                     this.dealsArrayList = dealsArrayL;
                }

    public AdapterOffer.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_offers, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterOffer.ViewHolder holder, final int position) {

        fillImage(context,holder,position);
    }

    private void fillImage(Context context, ViewHolder holder, int position) {
        //product image
        Picasso.get()
                .load(dealsArrayList.get(position).getImage1())
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        RelativeLayout contRL;
        TextView nameTV,desTV,priceTV,oldPrice;
        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image_offer) ;
        }

    }

}