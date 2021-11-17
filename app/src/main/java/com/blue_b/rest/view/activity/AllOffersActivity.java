package com.blue_b.rest.view.activity;

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
import android.widget.RelativeLayout;

import com.blue_b.rest.R;
import com.blue_b.rest.model.Area;
import com.blue_b.rest.model.Category;
import com.blue_b.rest.model.City;
import com.blue_b.rest.model.FilterOffersModel;
import com.blue_b.rest.model.MultiArea;
import com.blue_b.rest.model.Offer;
import com.blue_b.rest.presnter.JsonPlaceHolderApi;
import com.blue_b.rest.presnter.PassCityAndArea;
import com.blue_b.rest.presnter.PassFilterOffersModel;
import com.blue_b.rest.view.Adapters.AdapterEndlessOffers;
import com.blue_b.rest.view.Adapters.AdapterLoadingVOffers;
import com.blue_b.rest.view.fragments.allOffersFragments.FilterOffers;
import com.blue_b.rest.view.fragments.fragmentHomeMainScreen.PopUp;
import com.blue_b.rest.functions.Functions;
import com.blue_b.rest.functions.RetrofitFunctions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.blue_b.rest.view.activity.mainScreem.MainActivity.setLocale;

public class AllOffersActivity extends AppCompatActivity implements PopUp.PassSelectedAreas, PassCityAndArea, PassFilterOffersModel {

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

    RecyclerView all_offers_loading_rv;
    AdapterLoadingVOffers adapterLoadingVOffers;
    RecyclerView.LayoutManager layoutManagerLoading;
    FilterOffersModel filterOffersModelGlobal;
    public ArrayList<Area> selectedAreaArrayList = new ArrayList<>();

    RelativeLayout no_items;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale(AllOffersActivity.this);
        setContentView(R.layout.activity_all_offers);

        statusBarColor();
        handelFilterOffers();
        inti();
        filterOffersModelGlobal = Functions.getDefultToFilterModel(selectedAreaArrayList);
        intiRet();
        createLoadingRV();
        createRV();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                actionListenerToNestedScroll();

            }
        }, 200);
    }

    private void createLoadingRV() {
        all_offers_loading_rv.setVisibility(View.VISIBLE);
        all_offers_loading_rv.setHasFixedSize(true);

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        all_offers_loading_rv.setLayoutManager(mLayoutManager);

        adapterLoadingVOffers =new AdapterLoadingVOffers(this);
        all_offers_loading_rv.setAdapter(adapterLoadingVOffers);
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
//        Log.i("TAG","Im here out said");
//        Log.i("TAG","suggestedItemsArrayListDO.size(): "+String.valueOf(suggestedItemsArrayListDO.size()));
//        Log.i("TAG","max: "+String.valueOf(max));

        Call<List<Offer>> call = jsonPlaceHolderApiOffers.getOffers(suggestedItemsArrayListDO.size(),max);
        call.enqueue(new Callback<List<Offer>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                if (!response.isSuccessful())
                {
//                    Log.i("TAG","Im here in response not successful");
                    return;
                }
                List<Offer> offerList = response.body();

                for (Offer offer:offerList)
                {
//                    Log.i("TAG","NAME: "+offer.getName());
                    suggestedItemsArrayListTest.add(offer);
                    suggestedItemsArrayListDO.add(offer);
                }

                //hide loading rv
                all_offers_loading_rv.setVisibility(View.GONE);

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
//                Log.i("TAG","after add offers suggestedItemsArrayListDO.size(): "+String.valueOf(suggestedItemsArrayListDO.size()));
                if (suggestedItemsArrayListDO.size() ==0)
                {
//                    Log.i("TAG","insaid if 0");
                    no_items.setVisibility(View.VISIBLE);
                }else{
                    no_items.setVisibility(View.GONE);
                }

            }
            @Override
            public void onFailure(Call<List<Offer>> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private void intiRet() {
        //retrofitOffers = getOffers(getIOs());
        retrofitOffers = RetrofitFunctions.getOffersWithAllFilter(filterOffersModelGlobal);
        jsonPlaceHolderApiOffers = retrofitOffers.create(JsonPlaceHolderApi.class);
    }

    private void inti() {
        suggestedItemsArrayListDO = new ArrayList<>();
        suggestedItemsArrayListTest = new ArrayList<>();

        nested_all_offers = (NestedScrollView) findViewById(R.id.nested_all_offers);
        recyclerView_all_offers = (RecyclerView) findViewById(R.id.all_offers_rv);
        all_offers_loading_rv= (RecyclerView) findViewById(R.id.all_offers_loading_rv);
        no_items = (RelativeLayout) findViewById(R.id.no_items);
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void PassFilterOffersModel(FilterOffersModel filterOffersModel) {
        filterOffersModelGlobal = filterOffersModel;
        intiRet();
        suggestedItemsArrayListDO = new ArrayList<>();
        adapterEndlessOffers.removeAllOffers();
//        Log.i("TAG","Im here in said PassFilterOffersModel");
//        Log.i("TAG","suggestedItemsArrayListDO.size(): "+String.valueOf(suggestedItemsArrayListDO.size()));
        currentPage = PAGE_START;
        createLoadingRV();
        // whene clean the offer list nested scroll will work utmatecly no need to call any thing else




//        if (filterOffersModel.getCity() != null)
//        {
//            Log.i("TAG","City_local: "+filterOffersModel.getCity().getName_local());
//            Log.i("TAG","City_en: "+filterOffersModel.getCity().getName_en());
//            Log.i("TAG","City_id: "+filterOffersModel.getCity().getIdServer());
//        }else{
//            Log.i("TAG","City_object: null");
//        }
//
//        if (filterOffersModel.getAreasList().size() != 0)
//        {
//            for (int i=0;i<filterOffersModel.getAreasList().size();i++)
//            {
//                Log.i("TAG","Area_local: "+filterOffersModel.getAreasList().get(i).getName_local());
//                Log.i("TAG","Area_en: "+filterOffersModel.getAreasList().get(i).getName_en());
//                Log.i("TAG","Area_id: "+filterOffersModel.getAreasList().get(i).getId());
//            }
//
//        }else{
//            Log.i("TAG","AreasList size: zero");
//        }
//
//        Log.i("TAG","price from: "+String.valueOf(filterOffersModel.getFrom()));
//        Log.i("TAG","price to: "+String.valueOf(filterOffersModel.getTo()));


    }
}