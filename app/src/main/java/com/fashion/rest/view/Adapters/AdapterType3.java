package com.fashion.rest.view.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Categories;
import com.fashion.rest.model.Deal;
import com.fashion.rest.model.Sub_Cat;
import com.fashion.rest.view.activity.ItemDetails;
import com.fashion.rest.view.activity.ResultActivity;
import com.fashion.rest.view.activity.SubCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import static com.fashion.rest.apiURL.API.apiURLBase;
import static com.fashion.rest.functions.Functions.getTextEngOrLocal;

public class AdapterType3 extends RecyclerView.Adapter<AdapterType3.ViewHolder>{

    private final Context context;
    ArrayList<Categories> categoriesArrayList = new ArrayList<>();
    String cat;
    public AdapterType3
            (Context context,ArrayList<Categories> categoriesArrayList,String cat)
                {
                     this.context = context;
                    this.categoriesArrayList = categoriesArrayList;
                    this.cat = cat;
                }

    public AdapterType3.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_typ3, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterType3.ViewHolder holder, final int position) {

        fillImage(context,holder,position);
        changeFont(holder,context);
        fillText(holder,context,position);
        actionListenerToCard(holder,context,position);
    }

    private void actionListenerToCard(ViewHolder holder, final Context context, final int position) {
        holder.coverRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("cat_name",getTextEngOrLocal(context,categoriesArrayList.get(position).getName(),categoriesArrayList.get(position).getName_local()));

                if (categoriesArrayList.get(position).getSub_catArrayList().size() >1)
                    moveToSubCategory(bundle,categoriesArrayList.get(position).getSub_catArrayList());
                else
                    moveToShowResultActivity(categoriesArrayList.get(position).getSub_catArrayList().get(0));

            }
        });
    }

    private void moveToShowResultActivity(Sub_Cat sub_cat) {
        Bundle bundle = new Bundle();
        Log.i("TAG sub_cat_id",sub_cat.getId());
        Log.i("TAG cat_id",sub_cat.getCategory_id());
        bundle.putString("sub_cat_id",sub_cat.getId());
        bundle.putString("cat_id",sub_cat.getCategory_id());
        bundle.putString("sub_cat_name",getTextEngOrLocal(context,sub_cat.getName_en(),sub_cat.getName_local()));

        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtras(bundle);
        ((Activity)context).startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
    }

    private void moveToSubCategory(Bundle bundle, ArrayList<Sub_Cat> sub_catArrayList) {
        Intent intent = new Intent(context, SubCategory.class);
        intent.putExtras(bundle);
        intent.putExtra("sub_cat", sub_catArrayList);
        ((Activity)context).startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
    }

    private void fillText(ViewHolder holder, Context context, int position) {
        holder.nameTV.setText(getTextEngOrLocal(context,categoriesArrayList.get(position).getName(),categoriesArrayList.get(position).getName_local()));
    }

    private void changeFont(ViewHolder holder, Context context) {
        holder.nameTV.setTypeface(Functions.changeFontGeneral(context));
    }

    private void fillImage(Context context, ViewHolder holder, int position) {
        Picasso.get()
                .load(apiURLBase()+"/"+categoriesArrayList.get(position).getFlag().getUrl())
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return categoriesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        RelativeLayout coverRL;
        TextView nameTV;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTV = (TextView) itemView.findViewById(R.id.type3_tv);
            imageView = (ImageView) itemView.findViewById(R.id.cat_image) ;
              coverRL = (RelativeLayout) itemView.findViewById(R.id.cover_adapter_set) ;
        }
    }

}