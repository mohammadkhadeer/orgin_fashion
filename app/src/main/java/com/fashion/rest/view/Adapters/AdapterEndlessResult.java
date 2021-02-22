package com.fashion.rest.view.Adapters;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.model.Deal;
import com.fashion.rest.model.ItemTest;
import com.fashion.rest.model.ListItem;
import com.fashion.rest.utils.BaseViewHolderUser;

import java.util.List;

import butterknife.ButterKnife;

import static com.fashion.rest.view.Adapters.resultTupe1.FillResultsType1.fillItemCase1;
import static com.fashion.rest.view.categoriesComp.FillType2.fillCaseItem;
import static com.fashion.rest.view.categoriesComp.FillType4.fillCase4Item;


public class AdapterEndlessResult extends RecyclerView.Adapter<BaseViewHolderUser>{
  private static final int VIEW_TYPE_LOADING = 0;
  private static final int VIEW_TYPE_NORMAL = 1;
  private boolean isLoaderVisible = false;

  private List<ItemTest> dealItemsList;
  Context context;
  String comeFrom;
  int pageNumber;

  public AdapterEndlessResult(List<ItemTest> postItems, Context context, String comeFrom,int pageNumber) {
    this.dealItemsList = postItems;
    this.context = context;
    this.comeFrom = comeFrom;
    this.pageNumber = pageNumber;
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
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_endless_result, parent, false));
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
      return position == dealItemsList.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
    } else {
      return VIEW_TYPE_NORMAL;
    }
  }

  @Override
  public int getItemCount() {
    return dealItemsList == null ? 0 : dealItemsList.size();
  }

  public void addItems(List<ItemTest> postItems) {
    dealItemsList.addAll(postItems);
    notifyDataSetChanged();
  }

  public void addLoading() {
    isLoaderVisible = true;
    dealItemsList.add(new ItemTest());
    notifyItemInserted(dealItemsList.size() - 1);
  }

  public void removeLoading() {
    isLoaderVisible = false;
    ////////////here
    int position = dealItemsList.size() - 1;
    ItemTest deal = getItem(position);
    if (deal != null) {
      dealItemsList.remove(position);
      notifyItemRemoved(position);
    }
  }

  ItemTest getItem(int position) {
    return dealItemsList.get(position);
  }

  public class ViewHolder extends BaseViewHolderUser {
    RelativeLayout cont1,cont2,offerRL;
    ImageView imageView;
    LinearLayout coverRL;
    TextView nameTV,priceTV,oldPrice,offerTV;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);

      cont1 = (RelativeLayout) itemView.findViewById(R.id.container_result_type1_rl);
      cont2 = (RelativeLayout) itemView.findViewById(R.id.container_type4_rl);
      offerRL = (RelativeLayout) itemView.findViewById(R.id.adapter_result_type1_offer_rl);

      offerTV = (TextView) itemView.findViewById(R.id.adapter_result_type1_offer_TV);
      nameTV = (TextView) itemView.findViewById(R.id.adapter_result_type1_name_TV);
      priceTV = (TextView) itemView.findViewById(R.id.adapter_result_type1_price_TV);
      oldPrice = (TextView) itemView.findViewById(R.id.adapter_result_type1_old_price_TV);

      imageView = (ImageView) itemView.findViewById(R.id.adapter_result_type1_IV) ;

      coverRL = (LinearLayout) itemView.findViewById(R.id.cover_adapter_result_type1) ;
    }

    protected void clear() {

    }

    public void onBind(int position) {
      super.onBind(position);

      cont1.setVisibility(View.VISIBLE);
      cont2.setVisibility(View.GONE);

      fillItemCase1(getObject(position),context,imageView,nameTV,priceTV,oldPrice,offerRL,offerTV);
    }
  }

  private ItemTest getObject(int position){
    ItemTest item = dealItemsList.get(position);
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