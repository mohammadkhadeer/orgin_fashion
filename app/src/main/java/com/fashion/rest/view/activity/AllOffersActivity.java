package com.fashion.rest.view.activity;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.fashion.rest.R;
import com.fashion.rest.model.Area;
import com.fashion.rest.model.Category;
import com.fashion.rest.model.City;
import com.fashion.rest.model.MultiArea;
import com.fashion.rest.model.Offer;
import com.fashion.rest.presnter.JsonPlaceHolderApi;
import com.fashion.rest.presnter.PassCityAndArea;
import com.fashion.rest.view.Adapters.AdapterCities;
import com.fashion.rest.view.Adapters.AdapterEndlessOffers;
import com.fashion.rest.view.fragments.allOffersFragments.FilterOffers;
import com.fashion.rest.view.fragments.fragmentHomeMainScreen.PopUp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.fashion.rest.functions.FillItem.fillCityArrayL;
import static com.fashion.rest.functions.Functions.getIOs;
import static com.fashion.rest.functions.Functions.loadMoreItem;
import static com.fashion.rest.functions.RetrofitFunctions.getCategories;
import static com.fashion.rest.functions.RetrofitFunctions.getOffers;
import static com.fashion.rest.view.fragments.fragmentHomeMainScreen.FragmentResults.PAGE_START;

public class AllOffersActivity extends AppCompatActivity implements PopUp.PassSelectedAreas, PassCityAndArea {

    FilterOffers filterOffers = new FilterOffers();

    NestedScrollView nested_all_offers;
    RecyclerView recyclerView_all_offers;

    JsonPlaceHolderApi jsonPlaceHolderApiOffers;
    Retrofit retrofitOffers;

    public ArrayList<Offer> dealsArrayList = new ArrayList<>();
    public ArrayList<Offer> suggestedItemsArrayListTest;
    public ArrayList<Offer> suggestedItemsArrayListDO;

    LinearLayoutManager mLayoutManager;
    AdapterEndlessOffers adapterEndlessOffers ;

    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_offers);

        statusBarColor();
        handelFilterOffers();
        inti();
        intiRet();
        createRV();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                actionListenerToNestedScroll();

            }
        }, 2000);
    }

    private void actionListenerToNestedScroll() {
        nested_all_offers.getViewTreeObserver().addOnScrollChangedListener(new
                                                                                  ViewTreeObserver.OnScrollChangedListener() {
                                                                                      @RequiresApi(api = Build.VERSION_CODES.M)
                                                                                      @Override
                                                                                      public void onScrollChanged() {
                                                                                          View view = (View) nested_all_offers.getChildAt(nested_all_offers.getChildCount() - 1);
                                                                                          int diff = (view.getBottom() - (nested_all_offers.getHeight() + nested_all_offers.getScrollY()));
                                                                                          if (diff == 0) {
                                                                                              isLoading = true;
                                                                                              currentPage++;
                                                                                              doApiCall();
                                                                                          }
                                                                                      }
                                                                                  });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createRV() {
        adapterEndlessOffers = new AdapterEndlessOffers(new ArrayList<Offer>(),this,"all_filter");

        recyclerView_all_offers.setHasFixedSize(true);
        recyclerView_all_offers.setNestedScrollingEnabled(false);

        mLayoutManager = new GridLayoutManager(this, 1);
        //mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView_all_offers.setLayoutManager(mLayoutManager);
        recyclerView_all_offers.setAdapter(adapterEndlessOffers);

        doApiCall();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void doApiCall() {
        suggestedItemsArrayListTest = new ArrayList<>();
        int max =suggestedItemsArrayListDO.size() + 8;
        Call<List<Offer>> call = jsonPlaceHolderApiOffers.getOffers(suggestedItemsArrayListDO.size(),max);
        call.enqueue(new Callback<List<Offer>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                if (!response.isSuccessful())
                { return; }
                List<Offer> offerList = response.body();

                for (Offer offer:offerList)
                {
                    suggestedItemsArrayListTest.add(offer);
                    suggestedItemsArrayListDO.add(offer);
                }

                if (currentPage != PAGE_START && suggestedItemsArrayListTest.size()!=0) adapterEndlessOffers.removeLoading();
                if (suggestedItemsArrayListTest.size()!=0)
                {
                    adapterEndlessOffers.addItems(suggestedItemsArrayListTest);
                }
                if (suggestedItemsArrayListTest.size()!=0) {
                    adapterEndlessOffers.addLoading();
                    isLoading = false;
                } else {
                    isLastPage = true;
                }

            }
            @Override
            public void onFailure(Call<List<Offer>> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private void intiRet() {
        retrofitOffers = getOffers(getIOs());
        jsonPlaceHolderApiOffers = retrofitOffers.create(JsonPlaceHolderApi.class);
    }

    private void inti() {
        suggestedItemsArrayListDO = new ArrayList<>();
        suggestedItemsArrayListTest = new ArrayList<>();

        nested_all_offers = (NestedScrollView) findViewById(R.id.nested_all_offers);
        recyclerView_all_offers = (RecyclerView) findViewById(R.id.all_offers_rv);
    }

    private void handelFilterOffers() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_all_offer_filter, filterOffers)
                .commit();
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Override
    public void passSelected(ArrayList<MultiArea> multiAreasArrayList) {
        filterOffers.passSelected(multiAreasArrayList);
    }

    @Override
    public void PassCity(City city) {

    }

    @Override
    public void PassArea(Area area) {

    }

    @Override
    public void PassCat(Category category) {

    }
}