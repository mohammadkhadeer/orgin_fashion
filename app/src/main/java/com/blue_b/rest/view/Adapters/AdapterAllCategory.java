package com.blue_b.rest.view.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.blue_b.rest.model.CustomCategory;
import com.blue_b.rest.model.FilterItemsModel;
import com.blue_b.rest.model.Sub_Cat;
import com.blue_b.rest.view.activity.ResultActivity;
import com.blue_b.rest.apiURL.API;
import com.blue_b.rest.view.activity.SubCategory;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import static com.blue_b.rest.functions.Functions.getDefultToFilterItemModel;

public class AdapterAllCategory extends RecyclerView.Adapter<AdapterAllCategory.ViewHolder>{

    private final Context context;
//    List<Offer> mList = new ArrayList<>();
    ArrayList<CustomCategory> allCatArrayL = new ArrayList<>();
    // add to static page
    String color ="2364C2";
    public AdapterAllCategory
            (Context context,ArrayList<CustomCategory> allCatArrayL)
                {
                     this.context = context;
                    this.allCatArrayL = allCatArrayL;
                }

    public AdapterAllCategory.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_custom_category, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterAllCategory.ViewHolder holder, final int position) {

        fillImage(context,holder,position);
        changeFont(holder,context);
        fillText(holder,context,position);
        fillPorder(context,position,holder);
        actionListenerToCard(holder,context,position);
    }

    private void fillPorder(Context context, int position, ViewHolder holder) {
        if (allCatArrayL.get(position).getCustomCategory().size() != 0) {
            if (allCatArrayL.get(position).getCustomCategory().size() == 1) {
                fillFirstPor(context,position,holder);
            }
            if (allCatArrayL.get(position).getCustomCategory().size() == 2) {
                fillFirstPor(context,position,holder);
                fillSectPor(context,position,holder);
            }
            if (allCatArrayL.get(position).getCustomCategory().size() == 3) {
                fillFirstPor(context,position,holder);
                fillSectPor(context,position,holder);
                fillThertPor(context,position,holder);
            }
        }
    }

    private void fillThertPor(Context context, int position, ViewHolder holder) {
        String color_1 = "#"+color;
        int color = Color.parseColor(color_1);

        holder.cat_por2_position3_rl.setBackgroundColor(color);
    }

    private void fillSectPor(Context context, int position, ViewHolder holder) {
        String color_1 = "#"+color;

        int color = Color.parseColor(color_1);

        holder.cat_por1_position2_rl.setBackgroundColor(color);
        holder.cat_por2_position2_rl.setBackgroundColor(color);
    }

    private void fillFirstPor(Context context, int position, ViewHolder holder) {
        String color_1 = "#"+color;

        int color = Color.parseColor(color_1);

        holder.cat_por1_position1_rl.setBackgroundColor(color);
        holder.cat_por2_position1_rl.setBackgroundColor(color);

    }

    private void actionListenerToCard(ViewHolder holder, final Context context, final int position) {
        if (allCatArrayL.get(position).getCustomCategory().size() != 0) {
            if (allCatArrayL.get(position).getCustomCategory().size() == 1) {

                holder.cat_position1_rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("cat_name", Functions.getTextEngOrLocal(context,allCatArrayL.get(position).getCustomCategory().get(0).getName(),allCatArrayL.get(position).getCustomCategory().get(0).getName_local()));

                        if (allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList().size() >1)
                        {
                            moveToSubCategory(bundle,allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList());
                        }
                        else{
                            bundle.putString("sub_cat_id",allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList().get(0).getId());
                            bundle.putString("cat_id",allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList().get(0).getCategory_id());
                            bundle.putString("sub_cat_name", Functions.getTextEngOrLocal(context,allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList().get(0).getName_en()
                                    ,allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList().get(0).getName_local()));

                            moveToShowResultActivity(allCatArrayL.get(position).getCustomCategory().get(0),bundle);
                        }
                    }
                });

            }
            if (allCatArrayL.get(position).getCustomCategory().size() == 2) {

                holder.cat_position1_rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("cat_name", Functions.getTextEngOrLocal(context,allCatArrayL.get(position).getCustomCategory().get(0).getName(),allCatArrayL.get(position).getCustomCategory().get(0).getName_local()));

                        if (allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList().size() >1)
                        {
                            moveToSubCategory(bundle,allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList());
                        }
                        else{
                            bundle.putString("sub_cat_id",allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList().get(0).getId());
                            bundle.putString("cat_id",allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList().get(0).getCategory_id());
                            bundle.putString("sub_cat_name", Functions.getTextEngOrLocal(context,allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList().get(0).getName_en()
                                    ,allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList().get(0).getName_local()));

                            moveToShowResultActivity(allCatArrayL.get(position).getCustomCategory().get(0),bundle);
                        }

                    }
                });

                holder.cat_position2_rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("cat_name", Functions.getTextEngOrLocal(context,allCatArrayL.get(position).getCustomCategory().get(1).getName(),allCatArrayL.get(position).getCustomCategory().get(1).getName_local()));

                        if (allCatArrayL.get(position).getCustomCategory().get(1).getSub_catArrayList().size() >1)
                        {
                            moveToSubCategory(bundle,allCatArrayL.get(position).getCustomCategory().get(1).getSub_catArrayList());
                        } else{
                            bundle.putString("sub_cat_id",allCatArrayL.get(position).getCustomCategory().get(1).getSub_catArrayList().get(0).getId());
                            bundle.putString("cat_id",allCatArrayL.get(position).getCustomCategory().get(1).getSub_catArrayList().get(0).getCategory_id());
                            bundle.putString("sub_cat_name"
                                    , Functions.getTextEngOrLocal(context
                                    ,allCatArrayL.get(position).getCustomCategory().get(1).getSub_catArrayList().get(0).getName_en()
                                    ,allCatArrayL.get(position).getCustomCategory().get(1).getSub_catArrayList().get(0).getName_local()));

                            moveToShowResultActivity(allCatArrayL.get(position).getCustomCategory().get(1),bundle);
                        }

                    }
                });


            }
            if (allCatArrayL.get(position).getCustomCategory().size() == 3) {
                holder.cat_position1_rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("cat_name", Functions.getTextEngOrLocal(context,allCatArrayL.get(position).getCustomCategory().get(0).getName(),allCatArrayL.get(position).getCustomCategory().get(0).getName_local()));

                        if (allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList().size() >1)
                        {
                            moveToSubCategory(bundle,allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList());
                        } else{
                            bundle.putString("sub_cat_id",allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList().get(0).getId());
                            bundle.putString("cat_id",allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList().get(0).getCategory_id());
                            bundle.putString("sub_cat_name", Functions.getTextEngOrLocal(context,allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList().get(0).getName_en()
                                    ,allCatArrayL.get(position).getCustomCategory().get(0).getSub_catArrayList().get(0).getName_local()));

                            moveToShowResultActivity(allCatArrayL.get(position).getCustomCategory().get(0),bundle);
                        }

                    }
                });

                holder.cat_position2_rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("cat_name", Functions.getTextEngOrLocal(context,allCatArrayL.get(position).getCustomCategory().get(1).getName(),allCatArrayL.get(position).getCustomCategory().get(1).getName_local()));

                        if (allCatArrayL.get(position).getCustomCategory().get(1).getSub_catArrayList().size() >1)
                        {
                            moveToSubCategory(bundle,allCatArrayL.get(position).getCustomCategory().get(1).getSub_catArrayList());
                        }else{
                            bundle.putString("sub_cat_id",allCatArrayL.get(position).getCustomCategory().get(1).getSub_catArrayList().get(0).getId());
                            bundle.putString("cat_id",allCatArrayL.get(position).getCustomCategory().get(1).getSub_catArrayList().get(0).getCategory_id());
                            bundle.putString("sub_cat_name"
                                    , Functions.getTextEngOrLocal(context
                                            ,allCatArrayL.get(position).getCustomCategory().get(1).getSub_catArrayList().get(0).getName_en()
                                            ,allCatArrayL.get(position).getCustomCategory().get(1).getSub_catArrayList().get(0).getName_local()));

                            moveToShowResultActivity(allCatArrayL.get(position).getCustomCategory().get(1),bundle);
                        }
                    }
                });

                holder.cat_position3_rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("cat_name", Functions.getTextEngOrLocal(context,allCatArrayL.get(position).getCustomCategory().get(2).getName(),allCatArrayL.get(position).getCustomCategory().get(2).getName_local()));

                        if (allCatArrayL.get(position).getCustomCategory().get(2).getSub_catArrayList().size() >1)
                        {
                            moveToSubCategory(bundle,allCatArrayL.get(position).getCustomCategory().get(2).getSub_catArrayList());
                        }else {
                            bundle.putString("sub_cat_name"
                                    , Functions.getTextEngOrLocal(context
                                            ,allCatArrayL.get(position).getCustomCategory().get(2).getSub_catArrayList().get(0).getName_en()
                                            ,allCatArrayL.get(position).getCustomCategory().get(2).getSub_catArrayList().get(0).getName_local()));
                            moveToShowResultActivity(allCatArrayL.get(position).getCustomCategory().get(2),bundle);
                        }
                    }
                });
            }
        }
    }

    private void moveToShowResultActivity(Categories categories, Bundle bundle) {
//        Sub_Cat subCategory = new Sub_Cat()
        FilterItemsModel filterItemsModel = Functions.getDefultToFilterItemModel(categories.getSub_catArrayList().get(0));

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
        if (allCatArrayL.get(position).getCustomCategory().size() != 0) {
            if (allCatArrayL.get(position).getCustomCategory().size() == 1) {
                holder.custom_category_tv1_position1.setText(Functions.getTextEngOrLocal(context,allCatArrayL.get(position).getCustomCategory().get(0).getName(),allCatArrayL.get(position).getCustomCategory().get(0).getName_local()));
            }
            if (allCatArrayL.get(position).getCustomCategory().size() == 2) {
                holder.custom_category_tv1_position1.setText(Functions.getTextEngOrLocal(context,allCatArrayL.get(position).getCustomCategory().get(0).getName(),allCatArrayL.get(position).getCustomCategory().get(0).getName_local()));
                holder.custom_category_tv2_position2.setText(Functions.getTextEngOrLocal(context,allCatArrayL.get(position).getCustomCategory().get(1).getName(),allCatArrayL.get(position).getCustomCategory().get(1).getName_local()));
            }
            if (allCatArrayL.get(position).getCustomCategory().size() == 3) {
                holder.custom_category_tv1_position1.setText(Functions.getTextEngOrLocal(context,allCatArrayL.get(position).getCustomCategory().get(0).getName(),allCatArrayL.get(position).getCustomCategory().get(0).getName_local()));
                holder.custom_category_tv2_position2.setText(Functions.getTextEngOrLocal(context,allCatArrayL.get(position).getCustomCategory().get(1).getName(),allCatArrayL.get(position).getCustomCategory().get(1).getName_local()));
                holder.custom_category_tv2_position3.setText(Functions.getTextEngOrLocal(context,allCatArrayL.get(position).getCustomCategory().get(2).getName(),allCatArrayL.get(position).getCustomCategory().get(2).getName_local()));
            }
        }
    }

    private void changeFont(ViewHolder holder, Context context) {
        holder.custom_category_tv1_position1.setTypeface(Functions.changeFontGeneral(context));
        holder.custom_category_tv2_position2.setTypeface(Functions.changeFontGeneral(context));
        holder.custom_category_tv2_position3.setTypeface(Functions.changeFontGeneral(context));
    }

    private void fillImage(Context context, ViewHolder holder, int position) {

        if (allCatArrayL.get(position).getCustomCategory().size() != 0) {
            if (allCatArrayL.get(position).getCustomCategory().size() == 1) {
                Picasso.get()
                        .load(API.apiURLBase()+allCatArrayL.get(position).getCustomCategory().get(0).getFlag().getUrl())
                        .fit()
                        .centerCrop()
                        .into(holder.custom_category_image1_position1);
            }
            if (allCatArrayL.get(position).getCustomCategory().size() == 2) {
                Picasso.get()
                        .load(API.apiURLBase()+allCatArrayL.get(position).getCustomCategory().get(0).getFlag().getUrl())
                        .fit()
                        .centerCrop()
                        .into(holder.custom_category_image1_position1);

                Picasso.get()
                        .load(API.apiURLBase()+allCatArrayL.get(position).getCustomCategory().get(1).getFlag().getUrl())
                        .fit()
                        .centerCrop()
                        .into(holder.custom_category_image2_position2);
            }
            if (allCatArrayL.get(position).getCustomCategory().size() == 3) {
                Picasso.get()
                        .load(API.apiURLBase()+allCatArrayL.get(position).getCustomCategory().get(0).getFlag().getUrl())
                        .fit()
                        .centerCrop()
                        .into(holder.custom_category_image1_position1);

                Picasso.get()
                        .load(API.apiURLBase()+allCatArrayL.get(position).getCustomCategory().get(1).getFlag().getUrl())
                        .fit()
                        .centerCrop()
                        .into(holder.custom_category_image2_position2);

                Picasso.get()
                        .load(API.apiURLBase()+allCatArrayL.get(position).getCustomCategory().get(2).getFlag().getUrl())
                        .fit()
                        .centerCrop()
                        .into(holder.custom_category_image3_position3);
            }
        }
    }

    @Override
    public int getItemCount() {
        return allCatArrayL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView custom_category_image1_position1,custom_category_image2_position2,custom_category_image3_position3;
        RelativeLayout cat_position1_rl,cat_position2_rl,cat_position3_rl;
        RelativeLayout cat_por1_position1_rl,cat_por2_position1_rl,cat_por1_position2_rl,cat_por2_position2_rl,cat_por2_position3_rl;
        TextView custom_category_tv1_position1,custom_category_tv2_position2,custom_category_tv2_position3;
        public ViewHolder(View itemView) {
            super(itemView);
            cat_position1_rl = (RelativeLayout) itemView.findViewById(R.id.cat_position1_rl) ;
            cat_position2_rl = (RelativeLayout) itemView.findViewById(R.id.cat_position2_rl) ;
            cat_position3_rl = (RelativeLayout) itemView.findViewById(R.id.cat_position3_rl) ;

            custom_category_image1_position1 = (ImageView) itemView.findViewById(R.id.custom_category_image1_position1) ;
            custom_category_image2_position2 = (ImageView) itemView.findViewById(R.id.custom_category_image2_position2) ;
            custom_category_image3_position3 = (ImageView) itemView.findViewById(R.id.custom_category_image3_position3) ;

            custom_category_tv1_position1 = (TextView) itemView.findViewById(R.id.custom_category_tv1_position1);
            custom_category_tv2_position2 = (TextView) itemView.findViewById(R.id.custom_category_tv2_position2);
            custom_category_tv2_position3 = (TextView) itemView.findViewById(R.id.custom_category_tv2_position3);

            cat_por1_position1_rl = (RelativeLayout) itemView.findViewById(R.id.cat_por1_position1_rl) ;
            cat_por2_position1_rl = (RelativeLayout) itemView.findViewById(R.id.cat_por2_position1_rl) ;

            cat_por1_position2_rl = (RelativeLayout) itemView.findViewById(R.id.cat_por1_position2_rl) ;
            cat_por2_position2_rl = (RelativeLayout) itemView.findViewById(R.id.cat_por2_position2_rl) ;

            cat_por2_position3_rl = (RelativeLayout) itemView.findViewById(R.id.cat_por2_position3_rl) ;

        }

    }

}