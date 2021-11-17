package com.blue_b.rest.view.Adapters;

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
import com.blue_b.rest.R;
import com.blue_b.rest.functions.Functions;
import com.blue_b.rest.model.Categories;
import com.blue_b.rest.model.FilterItemsModel;
import com.blue_b.rest.model.Sub_Cat;
import com.blue_b.rest.view.activity.ResultActivity;
import com.blue_b.rest.view.activity.SubCategory;
import com.blue_b.rest.apiURL.API;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import static com.blue_b.rest.functions.Functions.getDefultToFilterItemModel;

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
                bundle.putString("cat_name", Functions.getTextEngOrLocal(context,categoriesArrayList.get(position).getName(),categoriesArrayList.get(position).getName_local()));

                if (categoriesArrayList.get(position).getSub_catArrayList().size() >1)
                    moveToSubCategory(bundle,categoriesArrayList.get(position).getSub_catArrayList());
                else
                    moveToShowResultActivity(categoriesArrayList.get(position).getSub_catArrayList().get(0));

            }
        });
    }

    private void moveToShowResultActivity(Sub_Cat sub_cat) {
        FilterItemsModel filterItemsModel = Functions.getDefultToFilterItemModel(sub_cat);

        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra("filter_object",filterItemsModel);
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
        holder.nameTV.setText(Functions.getTextEngOrLocal(context,categoriesArrayList.get(position).getName(),categoriesArrayList.get(position).getName_local()));
    }

    private void changeFont(ViewHolder holder, Context context) {
        holder.nameTV.setTypeface(Functions.changeFontGeneral(context));
    }

    private void fillImage(Context context, ViewHolder holder, int position) {
        Picasso.get()
                .load(API.apiURLBase()+"/"+categoriesArrayList.get(position).getFlag().getUrl())
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