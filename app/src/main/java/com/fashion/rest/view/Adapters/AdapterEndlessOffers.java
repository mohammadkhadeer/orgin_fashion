package com.fashion.rest.view.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Deal;
import com.fashion.rest.utils.BaseViewHolderUser;
import com.fashion.rest.view.activity.ItemDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;


public class AdapterEndlessOffers extends RecyclerView.Adapter<BaseViewHolderUser> {
  private static final int VIEW_TYPE_LOADING = 0;
  private static final int VIEW_TYPE_NORMAL = 1;
  private boolean isLoaderVisible = false;

  private List<Deal> dealItemsList;
  Context context;
  String comeFrom;

  public AdapterEndlessOffers(List<Deal> postItems, Context context, String comeFrom) {
    this.dealItemsList = postItems;
    this.context = context;
    this.comeFrom = comeFrom;
  }

  @NonNull
  @Override
  public BaseViewHolderUser onCreateViewHolder(ViewGroup parent, int viewType) {

    switch (viewType) {
      case VIEW_TYPE_NORMAL:
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_offers_endless, parent, false));
      case VIEW_TYPE_LOADING:
        return new ProgressHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading_h, parent, false));
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
      return position == dealItemsList.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
    } else {
      return VIEW_TYPE_NORMAL;
    }
  }

  @Override
  public int getItemCount() {
    return dealItemsList == null ? 0 : dealItemsList.size();
  }

  public void addItems(List<Deal> postItems) {
    dealItemsList.addAll(postItems);
    notifyDataSetChanged();
  }

  public void addLoading() {
    isLoaderVisible = true;
    dealItemsList.add(new Deal());
    notifyItemInserted(dealItemsList.size() - 1);
  }

  public void removeLoading() {
    isLoaderVisible = false;
    ////////////here
    int position = dealItemsList.size() - 1;
    Deal item = getItem(position);
    if (item != null) {
      dealItemsList.remove(position);
      notifyItemRemoved(position);
    }
  }

  public void clear() {
    dealItemsList.clear();
    notifyDataSetChanged();
  }

  Deal getItem(int position) {
    return dealItemsList.get(position);
  }

  public class ViewHolder extends BaseViewHolderUser {
    ImageView imageView;
    RelativeLayout coverRL;
    TextView nameTV,desTV,priceTV,oldPrice;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      nameTV = (TextView) itemView.findViewById(R.id.adapter_name_TV);
      desTV = (TextView) itemView.findViewById(R.id.adapter_des_TV);
      priceTV = (TextView) itemView.findViewById(R.id.adapter_price_TV);
      oldPrice = (TextView) itemView.findViewById(R.id.adapter_old_price_TV);
      imageView = (ImageView) itemView.findViewById(R.id.adapter_IV) ;
      coverRL = (RelativeLayout) itemView.findViewById(R.id.cover_offers_set) ;
    }

    protected void clear() {

    }

    public void onBind(int position) {
      super.onBind(position);

      fillImage(imageView, position, context);
      changeFont(context);
      actionListenerToGoShowItemDetails(context, coverRL, position);

    }
  }

  private void fillImageView(ImageView itemImage) {
    String noImage = "https://firebasestorage.googleapis.com/v0/b/hala-motor.appspot.com/o/images%2FnoImage.png?alt=media&token=4e02ba52-69dd-447b-9c66-4a26df53a80d";
    Picasso.get()
            .load(noImage)
            .fit()
            .centerCrop()
            .into(itemImage);
  }

  private Deal getObject(int position){
    Deal item = dealItemsList.get(position);
    return item;
  }

  public void removeAt(int position) {
    dealItemsList.remove(position);
    notifyItemRemoved(position);
    notifyItemRangeChanged(position, dealItemsList.size());
  }

  private void actionListenerToGoShowItemDetails(final Context context, RelativeLayout cardButton, final int position) {
    cardButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Bundle bundle = new Bundle();

        bundle.putString("from", "search");
        Intent intent = new Intent(context, ItemDetails.class);
        intent.putExtras(bundle);
        ((Activity) context).startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
      }
    });
  }

  private void fillImage(ImageView itemImage,
                         int position, Context context) {

    Picasso.get()
            .load(getObject(position).getImage())
            .fit()
            .centerCrop()
            .into(itemImage);

  }


  public class ProgressHolder extends BaseViewHolderUser {
    CardView cardView;
    ImageView shinImageView,imageView,shinImageView2,imageView2,shinImageView3,imageView3
            ,shinImageView4,imageView4;
    TextView textViewNoMoreMessage;
    RelativeLayout relativeLayout,relativeLayout2,relativeLayout3,relativeLayout4,relativeLayoutNoMoreItem;
    ProgressHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      cardView = (CardView) itemView.findViewById(R.id.adapter_show_user_item_cv);
      relativeLayoutNoMoreItem = (RelativeLayout) itemView.findViewById(R.id.adapter_show_user_item_no_more_cv);
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
      Log.i("TAN","Size: "+String.valueOf(dealItemsList.size()));
      int a= dealItemsList.size()-1, x = 0,mod=0;
      if (9 == dealItemsList.size())
      {
        x= 0;
        mod = 0;
      }else{
        x= a/8;
        mod = a % 8;
      }
      if (dealItemsList.size() ==1)
      {
        cardView.setVisibility(View.GONE);
        relativeLayoutNoMoreItem.setVisibility(View.GONE);
      }else {
        if(mod>0)
        {
          cardView.setVisibility(View.GONE);
          relativeLayoutNoMoreItem.setVisibility(View.VISIBLE);
          changeFont(context);
        }else {
          AddShineEffect(relativeLayout, shinImageView);
          AddShineEffect(relativeLayout2, shinImageView2);
          AddShineEffect(relativeLayout3, shinImageView3);
          AddShineEffect(relativeLayout4, shinImageView4);
        }
      }
    }
  }
  private void changeFont(Context context) {
//    textView.setTypeface(Functions.changeFontGeneral(context));
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