package com.fashion.rest.view.fragments.HomeScreenFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Area;
import com.fashion.rest.model.Categories;
import com.fashion.rest.model.FilterOffersModel;
import com.fashion.rest.model.Offer;
import com.fashion.rest.presnter.JsonPlaceHolderApi;
import com.fashion.rest.presnter.PassObject;
import com.fashion.rest.view.Adapters.AdapterEndlessOffers;
import com.fashion.rest.view.Adapters.AdapterLoadingVOffers;
import com.fashion.rest.view.activity.AllCategory;
import com.fashion.rest.view.activity.AllOffersActivity;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.fashion.rest.functions.Functions.getDefultToFilterModel;
import static com.fashion.rest.functions.RetrofitFunctions.getCategories;
import static com.fashion.rest.functions.RetrofitFunctions.getOffersWithAllFilter;
import static com.fashion.rest.view.categoriesComp.FillType3.fillCase3Item;

public class FragmentOffers extends Fragment{
    View view;
    RecyclerView recyclerView,recyclerViewCat;

    public ArrayList<Offer> dealsArrayList = new ArrayList<>();
    public ArrayList<Offer> suggestedItemsArrayListTest;
    public ArrayList<Offer> suggestedItemsArrayListDO;

    PassObject passObject;

    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;

    LinearLayoutManager mLayoutManager;
    AdapterEndlessOffers adapterEndlessOffers ;

    RelativeLayout see_all_cat_rl,see_all_offers_rl;
    TextView see_all_cat_tv,see_all_offers;

    int numberOfObjectNow = 0;
    JsonPlaceHolderApi jsonPlaceHolderApi,jsonPlaceHolderApiOffers;
    Retrofit retrofit,retrofitOffers;
    public ArrayList<Area> selectedAreaArrayList = new ArrayList<>();
    public ArrayList<Categories> categoriesArrayList = new ArrayList<>();
    FilterOffersModel filterOffersModelGlobal;

    RecyclerView all_offers_loading_rv;
    AdapterLoadingVOffers adapterLoadingVOffers;
    LinearLayoutManager layoutManagerLoading;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        filterOffersModelGlobal = getDefultToFilterModel(selectedAreaArrayList);
        intiRet();
        getCategoriesList();
    }

    private void intiRet() {
        retrofit = getCategories();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        retrofitOffers = getOffersWithAllFilter(filterOffersModelGlobal);
        jsonPlaceHolderApiOffers = retrofitOffers.create(JsonPlaceHolderApi.class);
    }

    //fill categories
    private void getCategoriesList() {
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
                fillCase3Item(recyclerViewCat,getActivity(),categoriesArrayList);
            }
            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    public FragmentOffers(){}

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_offers, container, false);
        suggestedItemsArrayListDO = new ArrayList<>();
        suggestedItemsArrayListTest = new ArrayList<>();
        inti();
        createRV();
        changeFont();

        createLoadingRV();

        actionListenerToRV();
        actionListenerToSeeAllCat();

        //createRVSuggested();
        return view;
    }

    private void createLoadingRV() {
        all_offers_loading_rv.setVisibility(View.VISIBLE);

        all_offers_loading_rv.setHasFixedSize(true);
        layoutManagerLoading = new LinearLayoutManager(getActivity());
        layoutManagerLoading.setOrientation(LinearLayoutManager.HORIZONTAL);
        all_offers_loading_rv.setLayoutManager(layoutManagerLoading);

        adapterLoadingVOffers =new AdapterLoadingVOffers(getActivity());
        all_offers_loading_rv.setAdapter(adapterLoadingVOffers);
    }

    private void changeFont() {
        see_all_cat_tv.setTypeface(Functions.changeFontGeneral(getActivity()));
        see_all_offers.setTypeface(Functions.changeFontGeneral(getActivity()));
    }

    private void actionListenerToSeeAllCat() {
        see_all_cat_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAllCatActivity();
            }
        });
        see_all_offers_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAllOffers();
            }
        });
    }

    private void moveToAllOffers() {
        Intent intent = new Intent(getActivity(), AllOffersActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
    }

    private void moveToAllCatActivity() {
        Intent intent = new Intent(getActivity(), AllCategory.class);
        intent.putExtra("categories", categoriesArrayList);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
    }

    private void actionListenerToRV() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isLoading) {

                    if (mLayoutManager != null && mLayoutManager.findLastCompletelyVisibleItemPosition() == suggestedItemsArrayListDO.size() - 1&& suggestedItemsArrayListTest.size() !=0) {
                        doApiCall();
                        currentPage ++;
                        isLoading = true;
                    }
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createRV() {
        adapterEndlessOffers = new AdapterEndlessOffers(new ArrayList<Offer>(),getActivity(),"call");
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapterEndlessOffers);
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
//                    Log.i("TAG",apiURLBase()+offer.getFlagArrayL().get(0).getUrl());
//                    Log.i("TAG",offer.getName());
//                    Log.i("TAG",offer.getDescription());
//                    Log.i("TAG",offer.getName_local());
//                    Log.i("TAG",offer.getDescription_local());
                }

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

            }
            @Override
            public void onFailure(Call<List<Offer>> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private void inti() {
        all_offers_loading_rv= (RecyclerView) view.findViewById(R.id.all_offers_loading_rv);
        recyclerView = (RecyclerView) view.findViewById(R.id.offer_RV);
        recyclerViewCat = (RecyclerView) view.findViewById(R.id.type3_RV);
        see_all_cat_rl = (RelativeLayout) view.findViewById(R.id.see_all_cat_rl);
        see_all_cat_tv = (TextView) view.findViewById(R.id.see_all_cat_tv);
        see_all_offers_rl = (RelativeLayout) view.findViewById(R.id.see_all_offers_rl);
        see_all_offers = (TextView) view.findViewById(R.id.see_all_offers);
    }

}
