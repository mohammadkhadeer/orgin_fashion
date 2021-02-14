package com.fashion.rest.view.categoriesComp;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.fashion.rest.model.Categories;
import com.fashion.rest.model.City;
import com.fashion.rest.model.Countries;
import com.fashion.rest.model.Deal;
import com.fashion.rest.presnter.JsonPlaceHolderApi;
import com.fashion.rest.view.Adapters.AdapterType3;
import com.fashion.rest.view.activity.SplashScreen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.fashion.rest.apiURL.API.apiCat;
import static com.fashion.rest.apiURL.API.apiURLBase;
import static com.fashion.rest.functions.Functions.fillSetArrayL;
import static com.fashion.rest.functions.RetrofitFunctions.getCategories;
import static com.fashion.rest.functions.RetrofitFunctions.getNumberOfCountries;
import static com.fashion.rest.sharedPreferences.Country.saveCountryInSP;

public class FillType3 {

    static AdapterType3 adapterType3;
    static RecyclerView.LayoutManager layoutManager;
    public static ArrayList<Categories> categoriesArrayList = new ArrayList<>();

    static JsonPlaceHolderApi jsonPlaceHolderApi;
    static Retrofit retrofit;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void fillCase3Item(RecyclerView recyclerView, Context context) {
        intiRet();
        getCategoriesList(recyclerView,context);
    }

    private static void intiRet() {
        retrofit = getCategories();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    private static void getCategoriesList(final RecyclerView recyclerView, final Context context) {
        categoriesArrayList = new ArrayList<>();
        Call<List<Categories>> call = jsonPlaceHolderApi.getCategories();
        call.enqueue(new Callback<List<Categories>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                if (!response.isSuccessful())
                { return; }
                List<Categories> countriesL = response.body();

                for (Categories categories:countriesL)
                {
                    categoriesArrayList.add(categories);
                }
                createRVSuggested(recyclerView, context);
            }
            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void createRVSuggested(RecyclerView recyclerView, Context context) {
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        adapterType3 =new AdapterType3(context
                ,categoriesArrayList,"category");
        recyclerView.setAdapter(adapterType3);
    }

}
