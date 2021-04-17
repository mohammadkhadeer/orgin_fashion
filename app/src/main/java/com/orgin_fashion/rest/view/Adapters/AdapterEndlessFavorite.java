package com.orgin_fashion.rest.view.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orgin_fashion.rest.R;
import com.orgin_fashion.rest.database.DBHelper;
import com.orgin_fashion.rest.functions.Functions;
import com.orgin_fashion.rest.model.Deal;
import com.orgin_fashion.rest.model.ItemTest;
import com.orgin_fashion.rest.utils.BaseViewHolderUser;
import com.orgin_fashion.rest.model.ItemFavorite;
import com.orgin_fashion.rest.view.activity.ItemDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;

import static com.orgin_fashion.rest.apiURL.API.apiURLBase;
import static com.orgin_fashion.rest.database.DataBaseInstance.getDataBaseInstance;
import static com.orgin_fashion.rest.functions.Functions.calculatePercentage;
import static com.orgin_fashion.rest.functions.Functions.getTextEngOrLocal;
import static com.orgin_fashion.rest.functions.Functions.itemFav;

public class AdapterEndlessFavorite extends RecyclerView.Adapter<BaseViewHolderUser>{
  private static final int VIEW_TYPE_LOADING = 0;
  private static final int VIEW_TYPE_NORMAL = 1;
  private boolean isLoaderVisible = false;

  private List<ItemFavorite> favoriteItemsList;
  Context context;
  DBHelper dbHelper;
  public AdapterEndlessFavorite(List<ItemFavorite> favoriteItemsList, Context context) {
    this.favoriteItemsList = favoriteItemsList;
    this.context = context;
  }

  public interface PassItem {
    void onClicked(Deal deal);
  }

  @NonNull
  @Override
  public BaseViewHolderUser onCreateViewHolder(ViewGroup parent, int viewType) {
    dbHelper = getDataBaseInstance(context);
    switch (viewType) {
      case VIEW_TYPE_NORMAL:
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_endless_favorite, parent, false));
      case VIEW_TYPE_LOADING:
        return new ProgressHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading_result_endless, parent, false));
      default:
        return null;
    }
  }

  @Override
  public void onBindViewHolder(@NonNull BaseViewHolderUser holder, int position) {
    holder.onBind(position);
    switch (getItemViewType(position)) {
      case VIEW_TYPE_NORMAL:

      case VIEW_TYPE_LOADING:

      default:
        break;
    }
  }

  @Override
  public int getItemViewType(int position) {
    if (isLoaderVisible) {
      return position == favoriteItemsList.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
    } else {
      return VIEW_TYPE_NORMAL;
    }
  }

  @Override
  public int getItemCount() {
    return favoriteItemsList == null ? 0 : favoriteItemsList.size();
  }

  public void addItems(List<ItemFavorite> postItems) {
    favoriteItemsList.addAll(postItems);
    notifyDataSetChanged();
  }

  public void addLoading() {
    isLoaderVisible = true;
    favoriteItemsList.add(new ItemFavorite());
    notifyItemInserted(favoriteItemsList.size() - 1);
  }

  public void removeLoading() {
    isLoaderVisible = false;
    ////////////here
    int position = favoriteItemsList.size() - 1;
    ItemFavorite deal = getItem(position);
    if (deal != null) {
      favoriteItemsList.remove(position);
      notifyItemRemoved(position);
    }
  }

  ItemFavorite getItem(int position) {
    return favoriteItemsList.get(position);
  }

  public class ViewHolder extends BaseViewHolderUser {
    RelativeLayout item_cover,adapter_fav_rl,adapter_delete_rl
            ,out_stock_cover,deleted_cover,adapter_delete_rl_delete;
    ImageView adapter_fav_item_image,adapter_fav_store_im;
    TextView adapter_fav_name_TV,adapter_fav_price_TV,adapter_fav_old_price_TV,adapter_fav_store_name
            ,adapter_fav_per,out_stock;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);

      item_cover = (RelativeLayout) itemView.findViewById(R.id.item_cover);
      adapter_delete_rl = (RelativeLayout) itemView.findViewById(R.id.adapter_delete_rl);
      out_stock_cover = (RelativeLayout) itemView.findViewById(R.id.out_stock_cover);
      deleted_cover = (RelativeLayout) itemView.findViewById(R.id.deleted_cover);
      adapter_delete_rl_delete = (RelativeLayout) itemView.findViewById(R.id.adapter_delete_rl_delete);

      adapter_fav_item_image = (ImageView) itemView.findViewById(R.id.adapter_fav_item_image) ;
      adapter_fav_store_im = (ImageView) itemView.findViewById(R.id.adapter_fav_store_im) ;

      adapter_fav_name_TV = (TextView) itemView.findViewById(R.id.adapter_fav_name_TV);
      adapter_fav_price_TV = (TextView) itemView.findViewById(R.id.adapter_fav_price_TV);
      adapter_fav_old_price_TV = (TextView) itemView.findViewById(R.id.adapter_fav_old_price_TV);
      out_stock = (TextView) itemView.findViewById(R.id.out_stock);

      adapter_fav_store_name = (TextView) itemView.findViewById(R.id.adapter_fav_store_name);
      adapter_fav_per = (TextView) itemView.findViewById(R.id.adapter_fav_per);

      adapter_fav_rl = (RelativeLayout) itemView.findViewById(R.id.adapter_fav_rl) ;
    }

    protected void clear() {

    }

    public void onBind(int position) {
      super.onBind(position);

      if (getItem(position).isDeleted() == true) {
        deleted_cover.setVisibility(View.VISIBLE);
        actionListenerToDeleteDeletedItem(adapter_delete_rl_delete,context,position);
      }else{
        if (getItem(position).isInStock() == false) {
          out_stock_cover.setVisibility(View.VISIBLE);
        }
        fillImage(adapter_fav_item_image, position, context, adapter_fav_store_im);
        fillText(adapter_fav_name_TV, adapter_fav_store_name, adapter_fav_per, position, context);
        fillPrice(adapter_fav_price_TV, adapter_fav_old_price_TV, context, position);
        adapter_fav_old_price_TV.setPaintFlags(adapter_fav_old_price_TV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        changeFont(adapter_fav_name_TV, adapter_fav_store_name, adapter_fav_per, adapter_fav_price_TV, adapter_fav_old_price_TV, context);
        actionListenerToFavorite(adapter_fav_rl, position, context);
        actionListenerToItem(item_cover, context, position);
        actionListenerToDelete(adapter_delete_rl, context, position);
      }
    }
  }

  private void actionListenerToDeleteDeletedItem(RelativeLayout adapter_delete_rl_delete, Context context, final int position) {
    adapter_delete_rl_delete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dbHelper.deleteItemFromFav(getItem(position).getId());
        removeItem(position);
      }
    });
  }

  private void actionListenerToDelete(RelativeLayout adapter_delete_rl, Context context, final int position) {
    adapter_delete_rl.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dbHelper.deleteItemFromFav(getItem(position).getId());
        removeItem(position);
      }
    });
  }

  private void actionListenerToItem(RelativeLayout item_cover, final Context context, final int position) {
    item_cover.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (getItem(position).isDeleted() == true)
        {}else{
          if (getItem(position).isInStock()){
            ItemTest itemTest = itemFav(getItem(position));

            Intent intent = new Intent(context, ItemDetails.class);
            intent.putExtra("item_object", itemTest);
            ((Activity)context).startActivity(intent);
            ((Activity)context).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
          }
        }

      }
    });
  }

  private void actionListenerToFavorite(RelativeLayout adapter_fav_rl, final int position, final Context context) {
    adapter_fav_rl.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //Toast.makeText(context,getItem(position).getName(),Toast.LENGTH_SHORT).show();
        dbHelper.deleteItemFromFav(getItem(position).getId());
        removeItem(position);
      }
    });
  }

  public void removeItem(int position) {
    ItemFavorite deal = getItem(position);
    if (deal != null) {
      favoriteItemsList.remove(position);
      notifyItemRemoved(position);
    }
  }

  private void changeFont(TextView adapter_fav_name_tv, TextView adapter_fav_store_name, TextView adapter_fav_per
          , TextView adapter_fav_price_tv, TextView adapter_fav_old_price_tv, Context context) {
    adapter_fav_name_tv.setTypeface(Functions.changeFontGeneral(context));
    adapter_fav_store_name.setTypeface(Functions.changeFontGeneral(context));
    adapter_fav_per.setTypeface(Functions.changeFontGeneral(context));
    adapter_fav_price_tv.setTypeface(Functions.changeFontGeneral(context));
    adapter_fav_old_price_tv.setTypeface(Functions.changeFontGeneral(context));
  }


  private void fillPrice(TextView adapter_fav_price_tv, TextView adapter_fav_old_price_tv, Context context, int position) {
    adapter_fav_price_tv.setText(String.valueOf(getItem(position).getDiscountPrice()));
    adapter_fav_old_price_tv.setText(String.valueOf(getItem(position).getPrice()));
  }

  private void fillText(TextView adapter_fav_name_tv, TextView adapter_fav_store_name, TextView adapter_fav_per, int position, Context context) {
    adapter_fav_name_tv.setText(getTextEngOrLocal(context,getObject(position).getName(),getObject(position).getName_local()));
    //change ite to arabic in store object
    adapter_fav_store_name.setText(getTextEngOrLocal(context,getObject(position).getStore().getName(),getObject(position).getStore().getName()));
    adapter_fav_per.setText(calculatePercentage(getObject(position).getPrice(),getObject(position).getDiscountPrice()));
  }

  private void fillImage(ImageView itemImage,
                         int position, Context context, ImageView store_image_offer) {

    Picasso.get()
            .load(apiURLBase()+getObject(position).getFlagArrayL().get(0).getUrl())
            .fit()
            .centerCrop()
            .into(itemImage);

    Picasso.get()
            .load(apiURLBase()+getObject(position).getStore().getFlag().getUrl())
            .fit()
            .centerCrop()
            .into(store_image_offer);

  }

  private ItemFavorite getObject(int position){
    ItemFavorite item = favoriteItemsList.get(position);
    return item;
  }

  public class ProgressHolder extends BaseViewHolderUser {
    RelativeLayout cardView;
    ImageView shinImageView,imageView,shinImageView2,imageView2,shinImageView3,imageView3
            ,shinImageView4,imageView4;
    TextView textViewNoMoreMessage;
    RelativeLayout relativeLayout,relativeLayout2,relativeLayout3,relativeLayout4;
    ProgressHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);

      cardView = (RelativeLayout) itemView.findViewById(R.id.adapter_show_user_item_cv);
      textViewNoMoreMessage = (TextView) itemView.findViewById(R.id.adapter_show_user_no_more_tv);
      shinImageView = (ImageView) itemView.findViewById(R.id.adapter_show_user_item_item_image_shin);
      imageView = (ImageView) itemView.findViewById(R.id.adapter_show_user_item_item_image_load);
      relativeLayout = (RelativeLayout) itemView.findViewById(R.id.adapter_show_user_item_item_image_load_rl);

      shinImageView2 = (ImageView) itemView.findViewById(R.id.adapter_show_user_item_item_image_shin2);
      imageView2 = (ImageView) itemView.findViewById(R.id.adapter_show_user_item_item_image_load2);
      relativeLayout2 = (RelativeLayout) itemView.findViewById(R.id.adapter_show_user_item_item_image_load_rl2);

      shinImageView3 = (ImageView) itemView.findViewById(R.id.adapter_show_user_item_item_image_shin3);
      imageView3 = (ImageView) itemView.findViewById(R.id.adapter_show_user_item_item_image_load3);
      relativeLayout3 = (RelativeLayout) itemView.findViewById(R.id.adapter_show_user_item_item_image_load_rl3);

      shinImageView4 = (ImageView) itemView.findViewById(R.id.adapter_show_user_item_item_image_shin4);
      imageView4 = (ImageView) itemView.findViewById(R.id.adapter_show_user_item_item_image_load4);
      relativeLayout4 = (RelativeLayout) itemView.findViewById(R.id.adapter_show_user_item_item_image_load_rl4);
    }

    @Override
    protected void clear() {

    }

    public void onBind(int position) {
      super.onBind(position);
      int a= favoriteItemsList.size()-1, x = 0,mod=0;
      if (8 == favoriteItemsList.size())
      {
        x= 0;
        mod = 0;
      }else{
        x= a/8;
        mod = a % 8;
      }
      if (favoriteItemsList.size() ==1)
      {
        cardView.setVisibility(View.GONE);
      }else {
        if(mod>0)
        {
          cardView.setVisibility(View.GONE);
        }else {
          AddShineEffect(relativeLayout, shinImageView);
          AddShineEffect(relativeLayout2, shinImageView2);
          AddShineEffect(relativeLayout3, shinImageView3);
          AddShineEffect(relativeLayout4, shinImageView4);
        }
      }
    }
  }

  String loadedOrDownloading="downloading";

  private void AddShineEffect(final RelativeLayout father, final ImageView child) {
    new Handler().postDelayed(new Runnable() {

      @Override
      public void run() {
        animationEffect(father,child);
        if (loadedOrDownloading.equals("downloading"))
          AddShineEffect(father,child);
      }
    }, 400);
  }

  private void animationEffect(RelativeLayout father, ImageView child) {
    Animation animation = new TranslateAnimation(0,
            father.getWidth()+child.getWidth(),0, 0);
    animation.setDuration(550);
    animation.setFillAfter(false);
    animation.setInterpolator(new AccelerateDecelerateInterpolator());
    child.startAnimation(animation);
  }

}