package com.blue_b.rest.view.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blue_b.rest.R;
import com.blue_b.rest.model.ItemTest;
import com.blue_b.rest.model.Offer;
import com.blue_b.rest.utils.BaseViewHolderUser;
import com.blue_b.rest.view.activity.ItemDetails;
import com.blue_b.rest.apiURL.API;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.ButterKnife;


public class AdapterEndlessOffers extends RecyclerView.Adapter<BaseViewHolderUser> {
  private static final int VIEW_TYPE_LOADING = 0;
  private static final int VIEW_TYPE_NORMAL = 1;
  private boolean isLoaderVisible = false;

  private List<Offer> dealItemsList;
  Context context;
  String comeFrom;

  public AdapterEndlessOffers(List<Offer> postItems, Context context, String comeFrom) {
    this.dealItemsList = postItems;
    this.context = context;
    this.comeFrom = comeFrom;
  }

  @NonNull
  @Override
  public BaseViewHolderUser onCreateViewHolder(ViewGroup parent, int viewType) {

    switch (viewType) {
      case VIEW_TYPE_NORMAL:
        if (comeFrom.equals("all_filter"))
          return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_offers_endless_all_offers, parent, false));
        else
          return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_offers_endless, parent, false));
      case VIEW_TYPE_LOADING:
        return new ProgressHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading_h_2, parent, false));
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

  public void addItems(List<Offer> postItems) {
    dealItemsList.addAll(postItems);
    notifyDataSetChanged();
  }

  public void addLoading() {
    isLoaderVisible = true;
    dealItemsList.add(new Offer());
    notifyItemInserted(dealItemsList.size() - 1);
  }

  public void removeLoading() {
    isLoaderVisible = false;
    ////////////here
    int position =-1;
    if (dealItemsList.size() !=0)
       position = dealItemsList.size() - 1;
    Offer item = null;
    if (position !=-1)
      item = getItem(position);

    if (item != null) {
      dealItemsList.remove(position);
      notifyItemRemoved(position);
    }

  }
  public void removeAllOffers() {
    int size = dealItemsList.size();
    dealItemsList.clear();
    notifyItemRangeRemoved(0, size);
  }

  public void clear() {
    dealItemsList.clear();
    notifyDataSetChanged();
  }

  Offer getItem(int position) {
    return dealItemsList.get(position);
  }

  public class ViewHolder extends BaseViewHolderUser {
    ImageView imageView,store_image_offer;
    RelativeLayout coverRL,callRL,cover_offers;
    TextView nameTV,desTV,priceTV,oldPrice,callTV,whatsApp,adapter_offer_price_TV,adapter_offer_old_price_TV;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      nameTV = (TextView) itemView.findViewById(R.id.adapter_type2_name);
      desTV = (TextView) itemView.findViewById(R.id.adapter_type2_des);
//      priceTV = (TextView) itemView.findViewById(R.id.adapter_price_TV);
//      oldPrice = (TextView) itemView.findViewById(R.id.adapter_old_price_TV);
      imageView = (ImageView) itemView.findViewById(R.id.adapter_IV);
      store_image_offer = (ImageView) itemView.findViewById(R.id.store_image_offer);
      coverRL = (RelativeLayout) itemView.findViewById(R.id.cont_rl_offers);
      cover_offers= (RelativeLayout) itemView.findViewById(R.id.cover_offers) ;
      callRL = (RelativeLayout) itemView.findViewById(R.id.adapter_type2_call_rl) ;
      callTV = (TextView) itemView.findViewById(R.id.adapter_type2_call_tv);
      whatsApp = (TextView) itemView.findViewById(R.id.adapter_type2_w_tv);
      adapter_offer_price_TV = (TextView) itemView.findViewById(R.id.adapter_offer_price_TV);
      adapter_offer_old_price_TV = (TextView) itemView.findViewById(R.id.adapter_offer_old_price_TV);
    }

    protected void clear() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onBind(int position) {
      super.onBind(position);

      fillImage(imageView, position, context,store_image_offer);
      fillText(nameTV,position,context,desTV);
      fillPrice(context,position,adapter_offer_price_TV,adapter_offer_old_price_TV);
      changeFont(context,nameTV,desTV,callTV,adapter_offer_price_TV,adapter_offer_old_price_TV);
      //Functions.changeOffersGradientsAndTextColorCases(coverRL,position,context,getObject(position),nameTV,desTV,callRL,callTV,whatsApp,adapter_offer_price_TV,adapter_offer_old_price_TV);
      //Functions.callFunction(callRL,context,getObject(position).getStore().getPhone_number());
      actionListenerToGoShowItemDetails(context, cover_offers, position);

    }

  }

  private void changeFont(Context context, TextView nameTV, TextView desTV, TextView callTV, TextView adapter_offer_price_tv, TextView adapter_offer_old_price_tv) {
    nameTV.setTypeface(com.blue_b.rest.functions.Functions.changeFontGeneral(context));
    desTV.setTypeface(com.blue_b.rest.functions.Functions.changeFontGeneral(context));
    callTV.setTypeface(com.blue_b.rest.functions.Functions.changeFontGeneral(context));
    adapter_offer_price_tv.setTypeface(com.blue_b.rest.functions.Functions.changeFontGeneral(context));
    adapter_offer_old_price_tv.setTypeface(com.blue_b.rest.functions.Functions.changeFontGeneral(context));
  }

  private void fillPrice(Context context, int position, TextView adapter_offer_price_tv, TextView adapter_offer_old_price_tv) {
    adapter_offer_price_tv.setText(String.valueOf(getObject(position).getDiscountPrice()));
    adapter_offer_old_price_tv.setText(String.valueOf(getObject(position).getPrice()));

    adapter_offer_old_price_tv.setPaintFlags(adapter_offer_old_price_tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
  }

  private void fillText(TextView nameTV, int position, Context context, TextView desTV) {
    nameTV.setText(String.valueOf(com.blue_b.rest.functions.Functions.getTextEngOrLocal(context,getObject(position).getStore().getName(),getObject(position).getStore().getName_local())));
    desTV.setText(String.valueOf(com.blue_b.rest.functions.Functions.getTextEngOrLocal(context,getObject(position).getName(),getObject(position).getName_local())));
//    nameTV.setText(String.valueOf(position));
  }

  private void fillImage(ImageView itemImage,
                         int position, Context context, ImageView store_image_offer) {

    Picasso.get()
            .load(API.apiURLBase()+getObject(position).getFlagArrayL().get(0).getUrl())
            .fit()
            .centerCrop()
            .into(itemImage);


    itemImage.setAnimation(AnimationUtils.loadAnimation(context,R.anim.zoom_in));


    Picasso.get()
            .load(API.apiURLBase()+getObject(position).getStore().getFlag().getUrl())
            .fit()
            .centerCrop()
            .into(store_image_offer);

  }

  private Offer getObject(int position){
    Offer item = dealItemsList.get(position);
    return item;
  }


  private void actionListenerToGoShowItemDetails(final Context context, RelativeLayout cardButton, final int position) {
    cardButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ItemTest itemTest = com.blue_b.rest.functions.Functions.convertOfferToItem(getObject(position));
        Bundle bundle = new Bundle();
        Log.i("TAG","in adapter offer brand: "+getObject(position).getBrand());
        Log.i("TAG","in adapter offer brand: "+getObject(position).getPromo_code());
        bundle.putString("offer_link", getObject(position).getBrand());
        bundle.putString("promo_code", getObject(position).getPromo_code());
        bundle.putParcelable("item_object", itemTest);

        Intent intent = new Intent(context, ItemDetails.class);
        intent.putExtras(bundle);
        ((Activity)context).startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
      }
    });
  }


  public class ProgressHolder extends BaseViewHolderUser {
    ProgressBar progressBar;
//    CardView cardView;
//    ImageView shinImageView,imageView,shinImageView2,imageView2,shinImageView3,imageView3
//            ,shinImageView4,imageView4;
//    TextView textViewNoMoreMessage;
//    RelativeLayout relativeLayout,relativeLayout2,relativeLayout3,relativeLayout4,relativeLayoutNoMoreItem,adapter_loading;
    ProgressHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
//      cardView = (CardView) itemView.findViewById(R.id.adapter_show_user_item_cv);
//      relativeLayoutNoMoreItem = (RelativeLayout) itemView.findViewById(R.id.adapter_show_user_item_no_more_cv);
//      textViewNoMoreMessage = (TextView) itemView.findViewById(R.id.adapter_show_user_no_more_tv);
//      shinImageView = (ImageView) itemView.findViewById(R.id.adapter_show_user_item_item_image_shin);
//      imageView = (ImageView) itemView.findViewById(R.id.adapter_show_user_item_item_image_load);
//      relativeLayout = (RelativeLayout) itemView.findViewById(R.id.adapter_show_user_item_item_image_load_rl);
//
//      shinImageView2 = (ImageView) itemView.findViewById(R.id.adapter_show_user_item_item_image_shin2);
//      imageView2 = (ImageView) itemView.findViewById(R.id.adapter_show_user_item_item_image_load2);
//      relativeLayout2 = (RelativeLayout) itemView.findViewById(R.id.adapter_show_user_item_item_image_load_rl2);
//
//      shinImageView3 = (ImageView) itemView.findViewById(R.id.adapter_show_user_item_item_image_shin3);
//      imageView3 = (ImageView) itemView.findViewById(R.id.adapter_show_user_item_item_image_load3);
//      relativeLayout3 = (RelativeLayout) itemView.findViewById(R.id.adapter_show_user_item_item_image_load_rl3);
//
//      shinImageView4 = (ImageView) itemView.findViewById(R.id.adapter_show_user_item_item_image_shin4);
//      imageView4 = (ImageView) itemView.findViewById(R.id.adapter_show_user_item_item_image_load4);
//      relativeLayout4 = (RelativeLayout) itemView.findViewById(R.id.adapter_show_user_item_item_image_load_rl4);
//      adapter_loading = (RelativeLayout) itemView.findViewById(R.id.adapter_loading);
    }

    @Override
    protected void clear() {

    }

    public void onBind(int position) {
      super.onBind(position);
      int a= dealItemsList.size()-1, x = 0,mod=0;
      if (8 == dealItemsList.size())
      {
        x= 0;
        mod = 0;
      }else{
        x= a/8;
        mod = a % 8;
      }
      if (dealItemsList.size() ==1)
      {
//        adapter_loading.setVisibility(View.GONE);
//        relativeLayoutNoMoreItem.setVisibility(View.GONE);
      }else {
        if(mod>0)
        {
          progressBar.setVisibility(View.GONE);
//          adapter_loading.setVisibility(View.GONE);
          // this well show to us no more items
          //relativeLayoutNoMoreItem.setVisibility(View.VISIBLE);
          //changeFont(context);
        }else {
          progressBar.setVisibility(View.VISIBLE);
//          AddShineEffect(relativeLayout, shinImageView);
//          AddShineEffect(relativeLayout2, shinImageView2);
//          AddShineEffect(relativeLayout3, shinImageView3);
//          AddShineEffect(relativeLayout4, shinImageView4);
        }
      }
    }
  }


//  String loadedOrDownloading="downloading";
//
//  private void AddShineEffect(final RelativeLayout father, final ImageView child) {
//    new Handler().postDelayed(new Runnable() {
//
//      @Override
//      public void run() {
//        animationEffect(father,child);
//        if (loadedOrDownloading.equals("downloading"))
//          AddShineEffect(father,child);
//      }
//    }, 400);
//  }
//
//  private void animationEffect(RelativeLayout father, ImageView child) {
//    Animation animation = new TranslateAnimation(0,
//            father.getWidth()+child.getWidth(),0, 0);
//    animation.setDuration(550);
//    animation.setFillAfter(false);
//    animation.setInterpolator(new AccelerateDecelerateInterpolator());
//    child.startAnimation(animation);
//  }

}