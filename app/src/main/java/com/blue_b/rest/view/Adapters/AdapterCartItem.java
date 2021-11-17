package com.blue_b.rest.view.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.blue_b.rest.model.ItemInDB;
import com.blue_b.rest.functions.FunctionCart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.blue_b.rest.functions.FunctionCart.calculateNumberOfItemsOnCart;

public class AdapterCartItem extends RecyclerView.Adapter<AdapterCartItem.ViewHolder>{

    android.content.SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private final Context context;
//    List<Offer> mList = new ArrayList<>();
    ArrayList<ItemInDB> itemInDBArrayL = new ArrayList<>();
    String cat;
    PassUpdate passUpdate;

    public AdapterCartItem
            (Context context,ArrayList<ItemInDB> itemInDBArrayL,String cat,PassUpdate passUpdate)
                {
                     this.context = context;
                    this.itemInDBArrayL = itemInDBArrayL;
                    this.cat = cat;
                    this.passUpdate = passUpdate;
                }

    public AdapterCartItem.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_item_in_db, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterCartItem.ViewHolder holder, final int position) {
        fillImage(context,holder,position);
        changeFont(holder,context);
        fillText(holder,context,position);
        actionListenerToDelete(holder,context,position);
        actionListenerToRemove(holder,context,position);
        actionListenerToAdd(holder,context,position);
    }

    private void actionListenerToAdd(final ViewHolder holder, final Context context, final int position) {
        holder.plusIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionCart.calculateNumberOfItemsOnCart(context,sharedPreferences,editor,itemInDBArrayL.get(position),"add",Integer.parseInt(holder.numberOfItem.getText().toString()));
                int x = Integer.parseInt(holder.numberOfItem.getText().toString()) +1;
                holder.numberOfItem.setText(String.valueOf(x));
                passUpdate.onClicked(true);
            }
        });
    }

    private void actionListenerToRemove(final ViewHolder holder, final Context context, final int position) {
        holder.removeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(holder.numberOfItem.getText().toString()) -1;
                holder.numberOfItem.setText(String.valueOf(x));
                FunctionCart.calculateNumberOfItemsOnCart(context,sharedPreferences,editor,itemInDBArrayL.get(position),"remove",Integer.parseInt(holder.numberOfItem.getText().toString()));
                passUpdate.onClicked(true);
                if (x == 0)
                {
                    removeItemFromList(position);
                }
            }
        });
    }


    private void actionListenerToDelete(ViewHolder holder, final Context context, final int position) {
        holder.deleteButtonRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionCart.deleteItemFromCartOnCart(context,sharedPreferences,editor,itemInDBArrayL.get(position).getNumberOfItemNow());
                FunctionCart.deleteItemInCartFromDataBase(context,itemInDBArrayL.get(position).getName());
                removeItemFromList(position);
                passUpdate.onClicked(true);
            }
        });
    }

    private void fillText(ViewHolder holder, Context context, int position) {
        holder.nameTV.setText(itemInDBArrayL.get(position).getName());
        holder.desTV.setText(itemInDBArrayL.get(position).getDes());
        holder.priceTV.setText(String.valueOf(itemInDBArrayL.get(position).getPriceN()));
        holder.oldPrice.setText(String.valueOf(itemInDBArrayL.get(position).getPriceO()));
        holder.numberOfItem.setText(String.valueOf(itemInDBArrayL.get(position).getNumberOfItemNow()));
    }

    private void changeFont(ViewHolder holder, Context context) {
        holder.nameTV.setTypeface(Functions.changeFontGeneral(context));
        holder.desTV.setTypeface(Functions.changeFontGeneral(context));
        holder.priceTV.setTypeface(Functions.changeFontGeneral(context));
        holder.oldPrice.setTypeface(Functions.changeFontGeneral(context));
        holder.numberOfItem.setTypeface(Functions.changeFontGeneral(context));
        holder.oldPrice.setPaintFlags(holder.priceTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void fillImage(Context context, ViewHolder holder, int position) {
        //product image
        Picasso.get()
                .load(itemInDBArrayL.get(position).getImageID())
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    public void removeItemFromList(int position) {
        itemInDBArrayL.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, itemInDBArrayL.size());
    }

    @Override
    public int getItemCount() {
        return itemInDBArrayL.size();
    }

    public interface PassUpdate {
        void onClicked(boolean change);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,removeIV,plusIV;
        RelativeLayout contRL,deleteButtonRL;
        TextView nameTV,desTV,priceTV,oldPrice,numberOfItem;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTV = (TextView) itemView.findViewById(R.id.adapter_cart_name_TV);
            desTV = (TextView) itemView.findViewById(R.id.adapter_cart_des_TV);
            priceTV = (TextView) itemView.findViewById(R.id.adapter_cart_price_TV);
            oldPrice = (TextView) itemView.findViewById(R.id.adapter_cart_old_price_TV);
            numberOfItem = (TextView) itemView.findViewById(R.id.adapter_cart_number_of_item);
            imageView = (ImageView) itemView.findViewById(R.id.adapter_cart_IV) ;
            contRL = (RelativeLayout) itemView.findViewById(R.id.cover_adapter_cart_rl) ;
            deleteButtonRL = (RelativeLayout) itemView.findViewById(R.id.adapter_cart_delete);
            removeIV = (ImageView) itemView.findViewById(R.id.remove_one_from_item);
            plusIV = (ImageView) itemView.findViewById(R.id.add_one_from_item);
        }

    }

}