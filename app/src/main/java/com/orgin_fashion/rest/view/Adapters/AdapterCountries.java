package com.orgin_fashion.rest.view.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orgin_fashion.rest.R;
import com.orgin_fashion.rest.functions.Functions;
import com.orgin_fashion.rest.model.Countries;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class AdapterCountries extends RecyclerView.Adapter<AdapterCountries.ViewHolder>{

    private final Context context;
    ArrayList<Countries> countriesArrayL = new ArrayList<>();
    PassCountry passCountry;
    public AdapterCountries
            (Context context,ArrayList<Countries> countriesArrayL,PassCountry passCountry)
                {
                     this.context = context;
                    this.countriesArrayL = countriesArrayL;
                    this.passCountry = passCountry;
                }

    public AdapterCountries.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_countries, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterCountries.ViewHolder holder, final int position) {

        fillImage(context,holder,position);
        changeFont(holder,context);
        fillText(holder,context,position);
        actionListenerToCard(holder,context,position);
    }

    private void actionListenerToCard(ViewHolder holder, final Context context, final int position) {
        holder.countries_cover_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passCountry.onClicked(countriesArrayL.get(position));
            }
        });
    }

    private void fillText(ViewHolder holder, Context context, int position) {
        holder.nameTV.setText(countriesArrayL.get(position).getName_en());

    }

    private void changeFont(ViewHolder holder, Context context) {
        holder.nameTV.setTypeface(Functions.changeFontGeneral(context));
    }

    private void fillImage(Context context, ViewHolder holder, int position) {
        //product image
        Picasso.get()
                .load(countriesArrayL.get(position).getFlag().getUrl())
                .fit()
                .centerCrop()
                .into(holder.country_image);

    }

    @Override
    public int getItemCount() {
        return countriesArrayL.size();
    }

    public interface PassCountry {
        void onClicked(Countries country);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView country_image;
        RelativeLayout countries_cover_rl;
        TextView nameTV;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTV = (TextView) itemView.findViewById(R.id.country_id);
            country_image = (ImageView) itemView.findViewById(R.id.country_image) ;
            countries_cover_rl = (RelativeLayout) itemView.findViewById(R.id.countries_cover_rl);
        }

    }

}