package com.blue_b.rest.view.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blue_b.rest.R;
import com.blue_b.rest.functions.Functions;
import com.blue_b.rest.model.ItemTest;
import com.blue_b.rest.view.activity.ItemDetails;
import com.blue_b.rest.apiURL.API;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class AdapterType4 extends RecyclerView.Adapter<AdapterType4.ViewHolder>{

    private final Context context;
//    List<Offer> mList = new ArrayList<>();
    ArrayList<ItemTest> dealsArrayL = new ArrayList<>();
    String cat;
    public AdapterType4
            (Context context,ArrayList<ItemTest> dealsArrayL,String cat)
                {
                     this.context = context;
                    this.dealsArrayL = dealsArrayL;
                    this.cat = cat;
                }

    public AdapterType4.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_typ4, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterType4.ViewHolder holder, final int position) {

        fillImage(context,holder,position);
        changeFont(holder,context);
        fillText(holder,context,position);
        actionListenerToCard(holder,context,position);
        holder.per_adapter_4.setText(Functions.calculatePercentage(dealsArrayL.get(position).getPrice(),dealsArrayL.get(position).getDiscountPrice()));
        Functions.checkFavOrNot(dealsArrayL.get(position).getId(),context,holder.fav_or_not);
        Functions.actionListenerToFav(dealsArrayL.get(position).getId(),dealsArrayL.get(position).getSub_cat().getId(),dealsArrayL.get(position).getSub_cat().getCategory_id()
                ,context,holder.fav_or_not,holder.adapter4_v_RL);
    }

    private void actionListenerToCard(ViewHolder holder, final Context context, final int position) {
        holder.coverRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemDetails.class);
                intent.putExtra("item_object", dealsArrayL.get(position));
                ((Activity)context).startActivity(intent);
                ((Activity)context).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
            }
        });
    }

    private void fillText(ViewHolder holder, Context context, int position) {
        holder.nameTV.setText(Functions.getTextEngOrLocal(context,dealsArrayL.get(position).getName(),dealsArrayL.get(position).getName_local()));
        holder.store_name.setText(Functions.getTextEngOrLocal(context,dealsArrayL.get(position).getStore().getName(),dealsArrayL.get(position).getStore().getName_local()));
        //holder.desTV.setText(dealsArrayL.get(position).getDes());
        holder.priceTV.setText(String.valueOf(dealsArrayL.get(position).getDiscountPrice()));
        holder.oldPrice.setText(String.valueOf(dealsArrayL.get(position).getPrice()));
    }

    private void changeFont(ViewHolder holder, Context context) {
        holder.nameTV.setTypeface(Functions.changeFontGeneral(context));
        holder.store_name.setTypeface(Functions.changeFontGeneral(context));
        //holder.desTV.setTypeface(Functions.changeFontGeneral(context));
        holder.priceTV.setTypeface(Functions.changeFontGeneral(context));
        holder.oldPrice.setTypeface(Functions.changeFontGeneral(context));
        holder.per_adapter_4.setTypeface(Functions.changeFontGeneral(context));
        holder.oldPrice.setPaintFlags(holder.priceTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void fillImage(Context context, ViewHolder holder, int position) {
        //product image
        //product image   apiURLBase()+dealsArrayL.get(position).getFlag().getUrl()
        //
        String image = API.apiURLBase()+dealsArrayL.get(position).getFlagArrayL().get(0).getUrl();
        String storeIm = API.apiURLBase()+dealsArrayL.get(position).getStore().getFlag().getUrl();
        //Log.i("TAG",storeIm);

        Picasso.get()
                .load(image)
                .fit()
                .centerCrop()
                .into(holder.imageView);

        Picasso.get()
                .load(storeIm)
                .fit()
                .centerCrop()
                .into(holder.store_im);

    }

    @Override
    public int getItemCount() {
        return dealsArrayL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,store_im,fav_or_not;
        RelativeLayout contRL,coverRL,adapter4_v_RL;
        TextView nameTV,desTV,priceTV,oldPrice,store_name,per_adapter_4;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTV = (TextView) itemView.findViewById(R.id.adapter_type4_pName);
            store_name = (TextView) itemView.findViewById(R.id.store_name);
            //desTV = (TextView) itemView.findViewById(R.id.adapter_suggested_to_you_des_TV);
            priceTV = (TextView) itemView.findViewById(R.id.adapter_type4_price_TV);
            per_adapter_4 = (TextView) itemView.findViewById(R.id.per_adapter_4);
            oldPrice = (TextView) itemView.findViewById(R.id.adapter_type4_old_price_TV);
            imageView = (ImageView) itemView.findViewById(R.id.adapter_type4_price_IV) ;
            fav_or_not = (ImageView) itemView.findViewById(R.id.fav_or_not_adapter4) ;
            store_im = (ImageView) itemView.findViewById(R.id.store_im) ;
            //contRL = (RelativeLayout) itemView.findViewById(R.id.adapter_suggested_to_you_cont) ;
            coverRL = (RelativeLayout) itemView.findViewById(R.id.cover_adapter_type4_set) ;
            adapter4_v_RL = (RelativeLayout) itemView.findViewById(R.id.adapter4_v_RL) ;
            //buttonAddToCart = (RelativeLayout) itemView.findViewById(R.id.adapter_suggested_call_button);
        }

    }

}