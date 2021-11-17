package com.blue_b.rest.view.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blue_b.rest.R;

public class AdapterLoadingVOffers extends RecyclerView.Adapter<AdapterLoadingVOffers.ViewHolder>{

    private final Context context;

    public AdapterLoadingVOffers
            (Context context)
                {
                     this.context = context;
                }

    public AdapterLoadingVOffers.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_offers_endless_loading_all_offers, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterLoadingVOffers.ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            //imageView = (ImageView) itemView.findViewById(R.id.adapter_suggested_to_you_IV) ;
        }

    }

}