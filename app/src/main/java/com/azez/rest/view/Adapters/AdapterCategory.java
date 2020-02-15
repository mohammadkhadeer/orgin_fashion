package com.azez.rest.view.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azez.rest.R;
import com.azez.rest.functions.Functions;
import com.azez.rest.model.Category;


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

        holder.imageView.setBackgroundResource(categoryArrayL.get(position).getImageIDInt());
        holder.textView.setText(categoryArrayL.get(position).getCategoryNameStr());
        holder.textView.setTypeface(Functions.changeFontGeneral(context));
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