package com.fashion.rest.view.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.fashion.rest.model.Category;
import com.fashion.rest.view.activity.CategoryItem;


import java.util.ArrayList;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder>{

    private final Context context;
    public ArrayList<Category> categoryArrayL ;

    public AdapterCategory
            (Context context,ArrayList<Category> categoryArrayL)
                {
                     this.context = context;
                     this.categoryArrayL = categoryArrayL;
                    notifyDataSetChanged();
                }

    public AdapterCategory.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_category, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterCategory.ViewHolder holder, final int position) {

        changeFont(holder,context);
        holder.imageView.setBackgroundResource(categoryArrayL.get(position).getImageIDInt());
        holder.textView.setText(categoryArrayL.get(position).getCategoryNameStr());
        actionListenerToImageV(holder,context,position);
    }

    private void actionListenerToImageV(ViewHolder holder, final Context context, final int position) {
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context,"here in  "+categoryArrayL.get(position).getCategoryNameStr(),Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("category",categoryArrayL.get(position).getCategoryNameStr());
                bundle.putString("from","cat");

                Intent intent = new Intent(context, CategoryItem.class);
                intent.putExtras(bundle);
                ((Activity)context).startActivity(intent);
                ((Activity)context).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
            }
        });
    }

    private void changeFont(ViewHolder holder, Context context) {
        holder.textView.setTypeface(Functions.changeFontCategory(context));
    }


    @Override
    public int getItemCount() {
        return categoryArrayL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        RelativeLayout radioRL;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.adapter_category_NameTV);
            imageView = (ImageView) itemView.findViewById(R.id.adapter_category_image_category) ;
        }

    }
}