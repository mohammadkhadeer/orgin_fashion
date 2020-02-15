package com.azez.rest.view.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

public class AdapterMainList extends RecyclerView.Adapter<AdapterMainList.ViewHolder>{

    private final Context context;
    public ArrayList<String> meanArrayL ;

    public AdapterMainList
            (Context context,ArrayList<String> meanArrayL)
                {
                     this.context = context;
                     this.meanArrayL = meanArrayL;
                }

    public AdapterMainList.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_main, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterMainList.ViewHolder holder, final int position) {

        fillImage(context,holder,position);

    }

    private void fillImage(Context context, ViewHolder holder, int position) {
        //meal image
        Picasso.with(context).load(R.drawable.mean_m)
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return meanArrayL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,resImageView;
        RelativeLayout contRL;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.adapter_meal_mean_IV) ;
            contRL = (RelativeLayout) itemView.findViewById(R.id.adapter_mean_res) ;
        }

    }

}