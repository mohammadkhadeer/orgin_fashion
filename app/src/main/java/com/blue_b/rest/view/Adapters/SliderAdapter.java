package com.blue_b.rest.view.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blue_b.rest.apiURL.API;
import com.blue_b.rest.functions.Functions;
import com.blue_b.rest.model.ItemTest;
import com.blue_b.rest.view.activity.ItemDetails;
import com.bumptech.glide.Glide;
import com.blue_b.rest.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends
        SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<ItemTest> mSliderItems = new ArrayList<>();

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public void renewItems(List<ItemTest> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(ItemTest sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        final ItemTest sliderItem = mSliderItems.get(position);

        viewHolder.textViewDescription.setText(sliderItem.getDescription());
        viewHolder.textViewDescription.setTextSize(16);
        viewHolder.textViewDescription.setTextColor(Color.WHITE);
        
        changeFont(viewHolder,context);
        fillItemImage(viewHolder,sliderItem);
        fillStoreImage(viewHolder,sliderItem);

        fillText(viewHolder,context,sliderItem);
        viewHolder.per_adapter_4.setText(Functions.calculatePercentage(sliderItem.getPrice(),sliderItem.getDiscountPrice()));

        actionListenerToItem(viewHolder,sliderItem,context);

    }

    private void fillText(SliderAdapterVH viewHolder, Context context, ItemTest sliderItem) {
        viewHolder.nameTV.setText(Functions.getTextEngOrLocal(context,sliderItem.getName(),sliderItem.getName_local()));
        viewHolder.store_name.setText(Functions.getTextEngOrLocal(context,sliderItem.getStore().getName(),sliderItem.getStore().getName_local()));
        viewHolder.textViewDescription.setText(Functions.getTextEngOrLocal(context,sliderItem.getDescription(),sliderItem.getDescription_local()));
        viewHolder.priceTV.setText(String.valueOf(sliderItem.getDiscountPrice()));
        viewHolder.oldPrice.setText(String.valueOf(sliderItem.getPrice()));
    }

    private void changeFont(SliderAdapterVH viewHolder, Context context) {
        viewHolder.nameTV.setTypeface(Functions.changeFontGeneral(context));
        viewHolder.store_name.setTypeface(Functions.changeFontGeneral(context));
        viewHolder.textViewDescription.setTypeface(Functions.changeFontGeneral(context));
        viewHolder.priceTV.setTypeface(Functions.changeFontGeneral(context));
        viewHolder.oldPrice.setTypeface(Functions.changeFontGeneral(context));
        viewHolder.per_adapter_4.setTypeface(Functions.changeFontGeneral(context));
        viewHolder.oldPrice.setPaintFlags(viewHolder.priceTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void fillStoreImage(SliderAdapterVH viewHolder, ItemTest sliderItem) {
        Glide.with(viewHolder.itemView)
                .load(API.apiURLBase()+sliderItem.getStore().getFlag().getUrl())
                .fitCenter()
                .into(viewHolder.store_im);
    }

    private void fillItemImage(SliderAdapterVH viewHolder, ItemTest sliderItem) {
        Glide.with(viewHolder.itemView)
                .load(API.apiURLBase()+sliderItem.getFlagArrayL().get(0).getUrl())
                .fitCenter()
                .into(viewHolder.imageViewBackground);
    }

    private void actionListenerToItem(SliderAdapterVH viewHolder, final ItemTest sliderItem, final Context context) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemDetails.class);
                intent.putExtra("item_object", sliderItem);
                ((Activity)context).startActivity(intent);
                ((Activity)context).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
            }
        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground,store_im;
        ImageView imageGifContainer;
        TextView textViewDescription;
        TextView nameTV,priceTV,oldPrice,store_name,per_adapter_4;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            store_im = itemView.findViewById(R.id.slider_store_im);
            nameTV = (TextView) itemView.findViewById(R.id.slider_adapter_type4_pName);
            store_name = (TextView) itemView.findViewById(R.id.slider_store_name);
            priceTV = (TextView) itemView.findViewById(R.id.slider_adapter_type4_price_TV);
            per_adapter_4 = (TextView) itemView.findViewById(R.id.slider_per_adapter_4);
            oldPrice = (TextView) itemView.findViewById(R.id.slider_adapter_type4_old_price_TV);

            this.itemView = itemView;
        }
    }

}
