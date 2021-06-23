package com.orgin_fashion.rest.view.categoriesComp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orgin_fashion.rest.R;
import com.orgin_fashion.rest.model.FilterItemsModel;
import com.orgin_fashion.rest.model.Home;
import com.orgin_fashion.rest.model.ItemTest;
import com.orgin_fashion.rest.presnter.JsonPlaceHolderApi;
import com.orgin_fashion.rest.view.Adapters.AdapterLoadingType2;
import com.orgin_fashion.rest.view.Adapters.AdapterType2;
import com.orgin_fashion.rest.view.Adapters.SliderAdapter;
import com.orgin_fashion.rest.view.activity.ResultActivity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.orgin_fashion.rest.functions.Functions.getDefultToFilterItemModel;
import static com.orgin_fashion.rest.functions.Functions.getTextEngOrLocal;
import static com.orgin_fashion.rest.functions.RetrofitFunctions.getItems;

public class FillSlider {
    private static SliderAdapter adapter;

    static RecyclerView.LayoutManager layoutManager;
    public static ArrayList<ItemTest> dealsArrayList = new ArrayList<>();
    static JsonPlaceHolderApi jsonPlaceHolderApi2;
    static Retrofit retrofit2;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void fillSliderItemsItem(SliderView sliderView, Context context, TextView catName, RelativeLayout seeAllType2
            , Home home, TextView see_all_tv) {
        fillText(context,catName,home,see_all_tv);
        intiRetrofit(home);
        getItemsFromServer(sliderView,context);
        actionListenerToSeeAll(seeAllType2,context,home);
    }


    private static void getItemsFromServer(final SliderView sliderView, final Context context) {
        Call<List<ItemTest>> callHome = jsonPlaceHolderApi2.getAllItems(0,15);
        callHome.enqueue(new Callback<List<ItemTest>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<ItemTest>> call, Response<List<ItemTest>> response) {
                if (!response.isSuccessful())
                { return; }
                List<ItemTest> itemsList = response.body();

                createSlider(sliderView, context,itemsList);
            }

            @Override
            public void onFailure(Call<List<ItemTest>> call, Throwable t) {
                Log.i("TAG","getItemsFromServer  in error");
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private static void intiRetrofit(Home home) {
//        retrofit2 = getItemHome(home.getSub_cat().getId(),home.getSub_cat().getCategory_id());
        FilterItemsModel filterItemsModel = getDefultToFilterItemModel(home.getSub_cat());
        retrofit2 = getItems(filterItemsModel);
        jsonPlaceHolderApi2 = retrofit2.create(JsonPlaceHolderApi.class);
    }

    private static void fillText(Context context, TextView catName,Home home,TextView see_all_tv) {
        catName.setText(getTextEngOrLocal(context,home.getSub_cat().getName_en(),home.getSub_cat().getName_local()));
        see_all_tv.setText(context.getResources().getString(R.string.see_all));
    }

    private static void actionListenerToSeeAll(RelativeLayout seeAllType2, final Context context, final Home home) {
        seeAllType2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToResultActivity(context,home);
                //Toast.makeText(context,"see all ..",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static void moveToResultActivity(Context context,Home home) {
        FilterItemsModel filterItemsModel = getDefultToFilterItemModel(home.getSub_cat());

        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra("filter_object",filterItemsModel);
        ((Activity)context).startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void createSlider(SliderView sliderView, Context context,List<ItemTest> itemsList) {
        dealsArrayList = new ArrayList<>();
        dealsArrayList.addAll(itemsList);


        adapter = new SliderAdapter(context);
        for (int i=0;i<dealsArrayList.size();i++)
        {
            adapter.addItem(dealsArrayList.get(i));
        }
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(2);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();


    }

}
