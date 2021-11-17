package com.blue_b.rest.view.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blue_b.rest.R;
import com.blue_b.rest.functions.Functions;
import com.blue_b.rest.model.City;

import java.util.ArrayList;

public class AdapterCities extends RecyclerView.Adapter<AdapterCities.ViewHolder>{

    private final Context context;
//    List<Offer> mList = new ArrayList<>();
    ArrayList<City> cities = new ArrayList<>();
    PassCity passCity;
    String from;
    public AdapterCities
            (Context context,ArrayList<City> dealsArrayL,PassCity passCity,String from)
                {
                    this.context = context;
                    this.cities = dealsArrayL;
                    this.passCity = passCity;
                    this.from = from;
                }

    public AdapterCities.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view;
        if (from.equals("all_filter"))
        {
             view = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.adapter_filter_city_and_areas_small, viewGroup, false);
        }else{
             view = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.adapter_filter_city_and_areas, viewGroup, false);
        }

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
        holder.cityOrAreaTV.setText(Functions.getTextEngOrLocal(context,cities.get(position).getName_en(),cities.get(position).getName_local()));
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