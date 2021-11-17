package com.blue_b.rest.view.fragments.fragmentItemDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
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
import com.blue_b.rest.R;
import com.blue_b.rest.functions.Functions;
import com.blue_b.rest.model.Brand;
import com.blue_b.rest.model.ItemTest;
import com.blue_b.rest.presnter.JsonPlaceHolderApi;
import com.blue_b.rest.view.activity.ReportActivity;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.blue_b.rest.apiURL.API.apiURLBase;
import static com.blue_b.rest.functions.Functions.actionListenerToFav;
import static com.blue_b.rest.functions.Functions.calculatePercentage;
import static com.blue_b.rest.functions.Functions.checkFavOrNot;
import static com.blue_b.rest.functions.Functions.getTextEngOrLocal;
import static com.blue_b.rest.functions.RetrofitFunctions.getBrandSubCat;

public class FragmentItemGeneralDetails extends Fragment {
    View view;
    TextView title,des,offer,promo_code_tv,promo_code_text_tv;
    String cat_type,offer_link,promo_code;
    RelativeLayout relativeLayout,fav_general_details,share_general_details,repo_general_details,rl_shin;
    ImageView offer_image,fav_or_not_general,image_shin;

    public FragmentItemGeneralDetails(){}
    ItemTest itemTest;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    Retrofit retrofit;
    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            cat_type = getArguments().getString("cat_type");
            offer_link = getArguments().getString("offer_link");
            promo_code = getArguments().getString("promo_code");
            itemTest = (ItemTest) getArguments().getParcelable("item_object");
            cat_type = itemTest.getSub_cat().getAppearance();
        }
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_item_general_details, container, false);
        inti();
        fillTitleAndDesAndOfferPercentage();
        //static method to fav image and fac_action listener
        if (cat_type.equals("offers"))
            {
                fav_general_details.setVisibility(View.GONE);
                fav_or_not_general.setVisibility(View.GONE);
            }else {
            fav_general_details.setVisibility(View.VISIBLE);
            fav_or_not_general.setVisibility(View.VISIBLE);
            checkFavOrNot(itemTest.getId(), getActivity(), fav_or_not_general);
            actionListenerToFav(itemTest.getId(), itemTest.getSub_cat().getId(), itemTest.getSub_cat().getCategory_id()
                    , getActivity(), fav_or_not_general, fav_general_details);
        }

        changeFont();
        showOffer();
        actionListenerToReport();
        return view;
    }

    private void actionListenerToReport() {
        repo_general_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReportActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("item_object", itemTest);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
            }
        });
    }

    private void fillTitleAndDesAndOfferPercentage() {
        title.setText(getTextEngOrLocal(getActivity(),itemTest.getName(),itemTest.getName_local()));
        des.setText(getTextEngOrLocal(getActivity(),itemTest.getDescription(),itemTest.getDescription_local()));
        offer.setText(calculatePercentage(itemTest.getPrice(),itemTest.getDiscountPrice())+" "+getActivity().getResources().getString(R.string.offer_4));
    }

    private void showOffer() {
        if (cat_type.equals("offers"))
        {
            rl_shin.setVisibility(View.VISIBLE);
            AddShineEffect(rl_shin,image_shin);
            relativeLayout.setVisibility(View.VISIBLE);
            intiRetrofit(offer_link);
            promo_code_tv.setText(promo_code);
            promo_code_text_tv.setText(getActivity().getResources().getString(R.string.promo_code));
        }else {
            rl_shin.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
            intiRetrofit(itemTest.getSub_cat().getBrand());
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

    private void getImageBrand() {
        Call<Brand> callHome = jsonPlaceHolderApi.getBrandSubCat();
        callHome.enqueue(new Callback<Brand>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<Brand> call, Response<Brand> response) {
                if (!response.isSuccessful())
                { return; }
                Brand itemsList = response.body();
                Picasso.get()
                        .load(apiURLBase()+itemsList.getFlag().getUrl())
                        .fit()
                        .centerCrop()
                        .into(offer_image);
            }

            @Override
            public void onFailure(Call<Brand> call, Throwable t) {
                Log.i("TAG","getItemsFromServer  in error");
                Log.i("TAG Error",t.getMessage());
            }
        });

    }

    private void intiRetrofit(String brandID) {
        retrofit = getBrandSubCat(brandID);
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getImageBrand();
    }

    private void changeFont() {
        title.setTypeface(Functions.changeFontGeneral(getActivity()));
        des.setTypeface(Functions.changeFontGeneral(getActivity()));
        offer.setTypeface(Functions.changeFontGeneral(getActivity()));
        promo_code_text_tv.setTypeface(Functions.changeFontGeneral(getActivity()));
        promo_code_tv.setTypeface(Functions.changeFontBold(getActivity()));
    }

    private void inti() {
        promo_code_text_tv= (TextView) view.findViewById(R.id.promo_code_text_tv);
        promo_code_tv= (TextView) view.findViewById(R.id.promo_code_tv);
        title = (TextView) view.findViewById(R.id.title);
        des = (TextView) view.findViewById(R.id.des);
        offer = (TextView) view.findViewById(R.id.textOffer);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.offer_cont);
        offer_image = (ImageView) view.findViewById(R.id.offer_image);
        fav_or_not_general = (ImageView) view.findViewById(R.id.fav_or_not_general);
        fav_general_details = (RelativeLayout) view.findViewById(R.id.fav_general_details);
        share_general_details = (RelativeLayout) view.findViewById(R.id.share_general_details);
        repo_general_details = (RelativeLayout) view.findViewById(R.id.repo_general_details);
        rl_shin = (RelativeLayout) view.findViewById(R.id.rl_shin);
        image_shin = (ImageView) view.findViewById(R.id.image_shin);
    }
}
