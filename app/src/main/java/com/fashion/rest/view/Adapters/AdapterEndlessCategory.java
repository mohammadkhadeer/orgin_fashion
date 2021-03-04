package com.fashion.rest.view.Adapters;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

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

import com.fashion.rest.R;
import com.fashion.rest.model.Deal;
import com.fashion.rest.model.Home;
import com.fashion.rest.utils.BaseViewHolderUser;

import java.util.List;

import butterknife.ButterKnife;

import static com.fashion.rest.functions.Functions.getTextEngOrLocal;
import static com.fashion.rest.view.categoriesComp.FillType2.fillCaseItem;
import static com.fashion.rest.view.categoriesComp.FillType4.fillCase4Item;


public class AdapterEndlessCategory extends RecyclerView.Adapter<BaseViewHolderUser>{
  private static final int VIEW_TYPE_LOADING = 0;
  private static final int VIEW_TYPE_NORMAL = 1;
  private boolean isLoaderVisible = false;

  private List<Home> homeItemsList;
  Context context;
  String comeFrom;

  public AdapterEndlessCategory(List<Home> homeItemsList, Context context, String comeFrom) {
    this.homeItemsList = homeItemsList;
    this.context = context;
    this.comeFrom = comeFrom;
  }

  public interface PassItem {
    void onClicked(Deal deal);
  }

  @NonNull
  @Override
  public BaseViewHolderUser onCreateViewHolder(ViewGroup parent, int viewType) {

    switch (viewType) {
      case VIEW_TYPE_NORMAL:
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_offers_category, parent, false));
      case VIEW_TYPE_LOADING:
        return new ProgressHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading_category_endless, parent, false));
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
      return position == homeItemsList.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
    } else {
      return VIEW_TYPE_NORMAL;
    }
  }

  @Override
  public int getItemCount() {
    return homeItemsList == null ? 0 : homeItemsList.size();
  }

  public void addItems(List<Home> postItems) {
    homeItemsList.addAll(postItems);
    notifyDataSetChanged();
  }

  public void addLoading() {
    isLoaderVisible = true;
    homeItemsList.add(new Home());
    notifyItemInserted(homeItemsList.size() - 1);
  }

  public void removeLoading() {
    isLoaderVisible = false;
    ////////////here
    int position = homeItemsList.size() - 1;
    Home item = getItem(position);
    if (item != null) {
      homeItemsList.remove(position);
      notifyItemRemoved(position);
    }
  }

  Home getItem(int position) {
    return homeItemsList.get(position);
  }

  public class ViewHolder extends BaseViewHolderUser {
    ImageView imageView;
    RelativeLayout coverRL,cont2,cont4,see_all_type2_rl,see_all_type4_rl;
    TextView nameTV,type2_cat_name_TV,oldPrice,type4_cat_name_TV;
    RecyclerView recyclerViewT2,recyclerViewT4,lodaingRVT2,lodaingRVT4;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);


//      imageView = (ImageView) itemView.findViewById(R.id.type1_IV) ;
      nameTV = (TextView) itemView.findViewById(R.id.adapter_name_TV);
      type2_cat_name_TV = (TextView) itemView.findViewById(R.id.type2_cat_name_TV);
      type4_cat_name_TV = (TextView) itemView.findViewById(R.id.type4_cat_name_TV);
//      oldPrice = (TextView) itemView.findViewById(R.id.adapter_old_price_TV);
      coverRL = (RelativeLayout) itemView.findViewById(R.id.cover_typ1);

      cont2 = (RelativeLayout) itemView.findViewById(R.id.container_type2_rl);
      cont4 = (RelativeLayout) itemView.findViewById(R.id.container_type4_rl);
      see_all_type2_rl = (RelativeLayout) itemView.findViewById(R.id.see_all_type2_rl);
      see_all_type4_rl = (RelativeLayout) itemView.findViewById(R.id.see_all_type4_rl);

      recyclerViewT2 = (RecyclerView) itemView.findViewById(R.id.type2_RV);
      lodaingRVT2 = (RecyclerView) itemView.findViewById(R.id.lodaingRV);

      lodaingRVT4 = (RecyclerView) itemView.findViewById(R.id.lodaingRVT4);
      recyclerViewT4 = (RecyclerView) itemView.findViewById(R.id.type4_RV);
    }

    protected void clear() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onBind(int position) {
      super.onBind(position);

      if (getObject(position).getSub_cat().getAppearance().equals("1"))
      {
            cont2.setVisibility(View.VISIBLE);
            cont4.setVisibility(View.GONE);
            fillCaseItem(recyclerViewT2,context,type2_cat_name_TV,see_all_type2_rl,homeItemsList.get(position),lodaingRVT2);
      }

      if (getObject(position).getSub_cat().getAppearance().equals("2"))
      {
            cont2.setVisibility(View.GONE);
            cont4.setVisibility(View.VISIBLE);
            fillCase4Item(recyclerViewT4,context,type4_cat_name_TV,see_all_type4_rl,homeItemsList.get(position),lodaingRVT4);
      }

    }

  }

  private void fillText(TextView nameTV, int position, Context context) {
    nameTV.setText(getTextEngOrLocal(context,getObject(position).getSub_cat().getName_en(),getObject(position).getSub_cat().getName_local()));
//    nameTV.setText(String.valueOf(position));
  }

  private Home getObject(int position){
    Home item = homeItemsList.get(position);
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
      int a= homeItemsList.size()-1, x = 0,mod=0;
      if (4 == homeItemsList.size())
      {
        x= 0;
        mod = 0;
      }else{
        x= a/4;
        mod = a % 4;
      }
      if (homeItemsList.size() ==1)
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