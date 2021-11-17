package com.blue_b.rest.view.categoriesComp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blue_b.rest.R;
import com.blue_b.rest.model.FilterItemsModel;
import com.blue_b.rest.model.Home;
import com.blue_b.rest.model.ItemTest;
import com.blue_b.rest.presnter.JsonPlaceHolderApi;
import com.blue_b.rest.view.Adapters.AdapterLoadingType2;
import com.blue_b.rest.view.Adapters.AdapterType2;
import com.blue_b.rest.view.activity.ResultActivity;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.blue_b.rest.functions.Functions.getDefultToFilterItemModel;
import static com.blue_b.rest.functions.Functions.getTextEngOrLocal;
import static com.blue_b.rest.functions.RetrofitFunctions.getItems;

public class FillType2 {
    static AdapterType2 adapterType2;
    static AdapterLoadingType2 adapterLoadingType2;
    static RecyclerView.LayoutManager layoutManager,layoutManagerLoading;
    public static ArrayList<ItemTest> dealsArrayList = new ArrayList<>();
    static JsonPlaceHolderApi jsonPlaceHolderApi2;
    static Retrofit retrofit2;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void fillCaseItem(RecyclerView recyclerView, Context context, TextView catName, RelativeLayout seeAllType2
            , Home home,RecyclerView lodaingRV,TextView see_all_tv) {
        createLoadingRV(lodaingRV,context);
        fillText(context,catName,home,see_all_tv);
        intiRetrofit(home);
        getItemsFromServer(recyclerView,context,lodaingRV);
        actionListenerToSeeAll(seeAllType2,context,home);
    }

    private static void createLoadingRV(RecyclerView lodaingRV, Context context) {
        lodaingRV.setNestedScrollingEnabled(false);
        lodaingRV.setHasFixedSize(true);
        layoutManagerLoading = new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false);

        lodaingRV.setLayoutManager(layoutManagerLoading);
        adapterLoadingType2 =new AdapterLoadingType2(context);
        lodaingRV.setAdapter(adapterLoadingType2);
    }

    private static void getItemsFromServer(final RecyclerView recyclerView, final Context context, final RecyclerView loadingRV) {
        Call<List<ItemTest>> callHome = jsonPlaceHolderApi2.getAllItems(0,15);
        callHome.enqueue(new Callback<List<ItemTest>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<ItemTest>> call, Response<List<ItemTest>> response) {
                if (!response.isSuccessful())
                { return; }
                List<ItemTest> itemsList = response.body();

                hideLoading(loadingRV);
                createRVSuggested(recyclerView, context,itemsList);
            }

            @Override
            public void onFailure(Call<List<ItemTest>> call, Throwable t) {
                Log.i("TAG","getItemsFromServer  in error");
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private static void hideLoading(final RecyclerView loadingRV) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                loadingRV.setVisibility(View.GONE);
            }
        }, 500);
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
    public static void createRVSuggested(RecyclerView recyclerView, Context context,List<ItemTest> itemsList) {
        dealsArrayList = new ArrayList<>();
        dealsArrayList.addAll(itemsList);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        adapterType2 =new AdapterType2(context
                ,dealsArrayList,"category");
        recyclerView.setAdapter(adapterType2);
    }

}
