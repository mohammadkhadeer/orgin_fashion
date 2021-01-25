package com.fashion.rest.view.Adapters;

import android.annotation.SuppressLint;
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
import com.fashion.rest.model.City;
import com.fashion.rest.model.Deal;
import com.fashion.rest.view.activity.ItemDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterCities extends RecyclerView.Adapter<AdapterCities.ViewHolder>{

    private final Context context;
//    List<Offer> mList = new ArrayList<>();
    ArrayList<City> cities = new ArrayList<>();
    PassCity passCity;
    public AdapterCities
            (Context context,ArrayList<City> dealsArrayL,PassCity passCity)
                {
                    this.context = context;
                    this.cities = dealsArrayL;
                    this.passCity = passCity;
                }

    public AdapterCities.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_filter_city_and_areas, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterCities.ViewHolder holder, final int position) {

        changeFont(holder,context);
        fillText(holder,context,position);
        actionListenerToAddToCart(holder,context,position);
    }

    private void actionListenerToAddToCart(ViewHolder holder, final Context context, final int position) {
        holder.cover_relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passCity.onClicked(cities.get(position));
            }
        });
    }


    private void fillText(ViewHolder holder, Context context, int position) {
        holder.cityOrAreaTV.setText(cities.get(position).getName_en());
    }

    private void changeFont(ViewHolder holder, Context context) {
        holder.cityOrAreaTV.setTypeface(Functions.changeFontGeneral(context));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public interface PassCity {
        void onClicked(City city);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cityOrAreaTV;
        RelativeLayout cover_relativeLayout;
        @SuppressLint("WrongViewCast")
        public ViewHolder(View itemView) {
            super(itemView);
            cityOrAreaTV = (TextView) itemView.findViewById(R.id.adapter_city_and_areas_text_view);
            cover_relativeLayout = (RelativeLayout) itemView.findViewById(R.id.adapter_city_and_areas_coverRL);
        }
    }
}