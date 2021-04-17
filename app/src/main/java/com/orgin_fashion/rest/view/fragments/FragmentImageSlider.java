package com.orgin_fashion.rest.view.fragments;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
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
import com.orgin_fashion.rest.model.ItemTest;
import com.orgin_fashion.rest.model.SlidImage;
import com.orgin_fashion.rest.presnter.ImageClicked;
import com.orgin_fashion.rest.view.Adapters.SlidingImage_Adapter;
import java.util.ArrayList;
import static com.orgin_fashion.rest.apiURL.API.apiURLBase;
import static com.orgin_fashion.rest.functions.Functions.getTextEngOrLocal;

public class FragmentImageSlider extends Fragment implements SlidingImage_Adapter.ImageClicked{

    public FragmentImageSlider(){}

    View view;
    String loadedOrDownloading="downloading";

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<SlidImage> imageModelArrayList;
    private ArrayList<String> images;
    ImageView imageView,shinImageView;
    String price="250",priceE="1",newPrice="199",itemIV,cat;
    RelativeLayout relativeLayout;

    TextView itemPriceTV,oldPriceTV,itemNewPriceTV;
    ImageClicked imageClickedP;
    ItemTest itemTest;

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            itemTest = (ItemTest) getArguments().getParcelable("item_object");
            itemIV = getTextEngOrLocal(getActivity(),itemTest.getName(),itemTest.getName_local());
            cat = getTextEngOrLocal(getActivity(),itemTest.getSub_cat().getName_en(),itemTest.getSub_cat().getName_local());

//            itemIV = getArguments().getString("itemName");
            cat = getArguments().getString("cat");
            priceE = "1";
            price = itemTest.getPrice();
            newPrice = itemTest.getDiscountPrice();

        }
        super.onAttach(context);
        if (context instanceof ImageClicked) {
            imageClickedP = (ImageClicked) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        imageClickedP = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_image_slider, container, false);
        inti();
        fillPrice();
        AddShineEffect(relativeLayout,shinImageView);

        fillImageList();

        return view;
    }

    private void fillPrice() {
        String aed = getActivity().getResources().getString(R.string.aed);
        if (priceE.equals("0"))
        {
            itemPriceTV.setVisibility(View.VISIBLE);
            oldPriceTV.setVisibility(View.GONE);
            itemNewPriceTV.setText(price
                    +" "+aed+"   ");
            //itemPriceTV.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
            //set new price empty to stay design
            itemPriceTV.setText("");
            //itemNewPriceTV.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        }else{
            itemPriceTV.setVisibility(View.GONE);
            oldPriceTV.setVisibility(View.VISIBLE);

            oldPriceTV.setText(price);
            //change text color
            oldPriceTV.setTextColor(getResources().getColor(R.color.colorWhite));
            //set line above old price
            oldPriceTV.setPaintFlags(itemPriceTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            //change size new price
            //itemNewPriceTV.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

            itemNewPriceTV.setText(newPrice
                    +" "+aed);
            //fill old price
            itemPriceTV.setText(price
                    +" "+aed);

        }
    }

    private void fillImageList() {
        images = new ArrayList<>();
        for (int i=0;i<itemTest.getFlagArrayL().size();i++)
        {
            images.add(apiURLBase()+itemTest.getFlagArrayL().get(i).getUrl());
        }
        //images = fillImgArrayL();
        fillSlider();
    }

    private void fillSlider() {
        imageModelArrayList = new ArrayList<>();
        for (int i =0;i<images.size();i++)
        {
            SlidImage slidImage = new SlidImage(images.get(i),i+1,images.size());
            imageModelArrayList.add(slidImage);
        }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                loadedOrDownloading = "loaded";
                relativeLayout.setVisibility(View.GONE);
            }
        }, 1500);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPager.setAdapter(new SlidingImage_Adapter(getActivity(),imageModelArrayList,this));
        //indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        NUM_PAGES =imageModelArrayList.size();

    }

    private void inti() {
        mPager = (ViewPager) view.findViewById(R.id.pager);
        shinImageView = (ImageView) view.findViewById(R.id.item_image_shin);
        imageView = (ImageView) view.findViewById(R.id.item_image_load);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.item_image_load_rl);
        itemPriceTV = (TextView) view.findViewById(R.id.image_slider_car_price);
        oldPriceTV = (TextView) view.findViewById(R.id.image_slider_car_old_price);
        itemNewPriceTV = (TextView) view.findViewById(R.id.image_slider_new_price);
    }

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

    @Override
    public void onImageClicked(Boolean clicked) {
        //createPopUp(getActivity(),view,images,imageModelArrayList);
        Log.i("TAG","onImageClicked : ");
        imageClickedP.imageClicked("test onImageClicked");
    }
}
