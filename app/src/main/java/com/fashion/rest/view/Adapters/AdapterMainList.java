package com.fashion.rest.view.Adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fashion.rest.R;
import com.fashion.rest.model.FastFood;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.fashion.rest.functions.Functions.fillFastFoodArrayL;

public class AdapterMainList extends RecyclerView.Adapter<AdapterMainList.ViewHolder>{

    private final Context context;
    public ArrayList<String> meanArrayL ;
    public ArrayList<FastFood> fastFoodArrayL = new ArrayList<>();
    AdapterFastFood adapterFastFood;
    RecyclerView.LayoutManager layoutManager;

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
        checkCases(context,holder,position);
        createRV(context,holder,position);

    }

    private void createRV(Context context, ViewHolder holder, int position) {
        fastFoodArrayL = fillFastFoodArrayL(fastFoodArrayL,context);
        holder.recyclerView.setNestedScrollingEnabled(false);
        holder.recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false);

        holder.recyclerView.setLayoutManager(layoutManager);
        adapterFastFood =new AdapterFastFood(context
                ,fastFoodArrayL);
        holder.recyclerView.setAdapter(adapterFastFood);
    }




    private void checkCases(Context context, ViewHolder holder, int position) {
        if (meanArrayL.get(position).equals("res"))
        {
            holder.contRes.setVisibility(View.VISIBLE);
            holder.coverFF.setVisibility(View.GONE);
        }
        if (meanArrayL.get(position).equals("ff"))
        {
            holder.contRes.setVisibility(View.GONE);
            holder.coverFF.setVisibility(View.VISIBLE);
        }
    }

    private void fillImage(Context context, ViewHolder holder, int position) {
        //meal image
        Picasso.get()
                .load(R.drawable.mean_m)
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
        RecyclerView recyclerView;
        RelativeLayout contRes,coverFF;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.adapter_meal_mean_IV) ;
            recyclerView = (RecyclerView) itemView.findViewById(R.id.adapter_brands) ;
            contRes = (RelativeLayout) itemView.findViewById(R.id.adapter_mean_res) ;
            coverFF = (RelativeLayout) itemView.findViewById(R.id.adapter_mean_ff) ;
        }

    }

}