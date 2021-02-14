package com.fashion.rest.view.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Area;
import com.fashion.rest.model.SubCategory;
import com.fashion.rest.model.Sub_Cat;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.fashion.rest.apiURL.API.apiURLBase;
import static com.fashion.rest.functions.Functions.getTextEngOrLocal;

public class AdapterSubCategorySeeAll extends RecyclerView.Adapter<AdapterSubCategorySeeAll.ViewHolder>{

    private final Context context;
//    List<Offer> mList = new ArrayList<>();
    ArrayList<Sub_Cat> sub_catArrayList = new ArrayList<>();
    PassSubCategory passSubCat;
    public AdapterSubCategorySeeAll
            (Context context,ArrayList<Sub_Cat> sub_catArrayList,PassSubCategory passSubCat)
                {
                    this.context = context;
                    this.sub_catArrayList = sub_catArrayList;
                    this.passSubCat = passSubCat;
                }

    public AdapterSubCategorySeeAll.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_sub_cat_see_all, viewGroup, false);
        return new ViewHolder(view);
    }

    public void removeArea(Area area) {
        for (int i=0;i<sub_catArrayList.size();i++)
        {
            if (area.getName_en().equals(sub_catArrayList.get(i).getName_en()))
            {
                sub_catArrayList.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }

    @Override
    public void onBindViewHolder(final AdapterSubCategorySeeAll.ViewHolder holder, final int position) {

        changeFont(holder,context);
        fillText(holder,context,position);
        actionListenerToAddToCart(holder,context,position);
        fillALineBtweenItems(context,holder,position);
        fillImage(context,holder,position);
    }

    private void fillImage(Context context, ViewHolder holder, int position) {
        Picasso.get()
                .load(apiURLBase()+"/"+sub_catArrayList.get(position).getFlag().getUrl())
                .fit()
                .centerCrop()
                .into(holder.sub_cat_image);
    }

    private void fillALineBtweenItems(Context context,ViewHolder holder, int position) {
        if (position != sub_catArrayList.size()-1)
        {
            holder.adapter_car_options_split_TV.setBackgroundColor(ContextCompat.getColor(context, R.color.colorBlack2));
        }
    }

    private void actionListenerToAddToCart(ViewHolder holder, final Context context, final int position) {
        holder.cover_relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passSubCat.onClickedSubCategory(sub_catArrayList.get(position));
            }
        });
    }


    private void fillText(ViewHolder holder, Context context, int position) {
        holder.cityOrAreaTV.setText(getTextEngOrLocal(sub_catArrayList.get(position).getName_en(),sub_catArrayList.get(position).getName_local()));
    }

    private void changeFont(ViewHolder holder, Context context) {
        holder.cityOrAreaTV.setTypeface(Functions.changeFontGeneral(context));
    }

    @Override
    public int getItemCount() {
        return sub_catArrayList.size();
    }

    public interface PassSubCategory {
        void onClickedSubCategory(Sub_Cat subCategory);
    }

    public void filterList(ArrayList<Sub_Cat> filterdNames) {
        this.sub_catArrayList = filterdNames;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cityOrAreaTV;
        RelativeLayout cover_relativeLayout,adapter_car_options_split_TV;
        ImageView sub_cat_image;
        @SuppressLint("WrongViewCast")
        public ViewHolder(View itemView) {
            super(itemView);
            cityOrAreaTV = (TextView) itemView.findViewById(R.id.adapter_city_and_areas_text_view);
            cover_relativeLayout = (RelativeLayout) itemView.findViewById(R.id.adapter_city_and_areas_coverRL);
            adapter_car_options_split_TV = (RelativeLayout) itemView.findViewById(R.id.adapter_car_options_split_TV);
            sub_cat_image = (ImageView) itemView.findViewById(R.id.sub_cat_image);
        }
    }
}