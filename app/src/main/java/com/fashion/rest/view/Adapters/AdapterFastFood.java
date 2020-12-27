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
import com.fashion.rest.model.FastFood;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterFastFood extends RecyclerView.Adapter<AdapterFastFood.ViewHolder>{

    private final Context context;
    public ArrayList<FastFood> fastFoodArrayL ;

    public AdapterFastFood
            (Context context,ArrayList<FastFood> fastFoodArrayL)
                {
                     this.context = context;
                     this.fastFoodArrayL = fastFoodArrayL;
                }

    public AdapterFastFood.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_fast_food, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterFastFood.ViewHolder holder, final int position) {

        fillImage(context,holder,position);
        holder.textViewPrandName.setText(fastFoodArrayL.get(position).getRestNameStr());
        holder.textViewTimeDel.setText(fastFoodArrayL.get(position).getTimeDel());

    }

    private void fillImage(Context context, ViewHolder holder, int position) {
        //meal image
        Picasso.get()
                .load(fastFoodArrayL.get(position).getImageInt())
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return fastFoodArrayL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTimeDel,textViewPrandName;
        RelativeLayout contRL;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.adapter_fast_food_IV) ;
            textViewTimeDel = (TextView) itemView.findViewById(R.id.adapter_fast_food_time_delIV) ;
            textViewPrandName = (TextView) itemView.findViewById(R.id.adapter_fast_food_TV) ;

        }

    }

}