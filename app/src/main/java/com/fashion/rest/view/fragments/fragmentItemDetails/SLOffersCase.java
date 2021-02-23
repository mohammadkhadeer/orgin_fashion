package com.fashion.rest.view.fragments.fragmentItemDetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Deal;
import com.fashion.rest.model.Offer;
import com.fashion.rest.presnter.JsonPlaceHolderApi;
import com.fashion.rest.view.Adapters.AdapterEndlessOffers;
import com.fashion.rest.view.Adapters.AdapterType2;
import com.fashion.rest.view.Adapters.AdapterType4;
import com.fashion.rest.view.activity.CategoryItem;
import com.fashion.rest.view.activity.ResultActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.fashion.rest.functions.FillItem.fillEndlessItemDepCatArrayL;
import static com.fashion.rest.functions.Functions.fillSetArrayL;
import static com.fashion.rest.functions.Functions.fillSetArrayL2;
import static com.fashion.rest.functions.Functions.getIOs;
import static com.fashion.rest.functions.RetrofitFunctions.getCategories;
import static com.fashion.rest.functions.RetrofitFunctions.getOffers;


public class SLOffersCase extends Fragment {
    View view;
    RecyclerView recyclerView;
    TextView headerTV,full_image_and_see_all_TV;
    RelativeLayout relativeLayout;
    String cat,cat_type;
    public ArrayList<Offer> suggestedItemsArrayListTest;
    public ArrayList<Offer> suggestedItemsArrayListDO;

    public SLOffersCase(){}

    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;

    LinearLayoutManager mLayoutManager;
    AdapterEndlessOffers adapterEndlessOffers ;

    JsonPlaceHolderApi jsonPlaceHolderApiOffers;
    Retrofit retrofitOffers;

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            cat = getArguments().getString("cat");
            cat_type = getArguments().getString("cat_type");
        }
        super.onAttach(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_full_image_and_image_png, container, false);
        suggestedItemsArrayListDO = new ArrayList<>();
        suggestedItemsArrayListTest = new ArrayList<>();

        inti();
        intiRet();
        changeFont();

        createOffersCase();
        actionListenerToRV();

        actionListenerToSeeAll();
        return view;
    }

    private void intiRet() {
        retrofitOffers = getOffers(getIOs());
        jsonPlaceHolderApiOffers = retrofitOffers.create(JsonPlaceHolderApi.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createOffersCase() {
        adapterEndlessOffers = new AdapterEndlessOffers(new ArrayList<Offer>(),getActivity(),"call");
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapterEndlessOffers);
        doApiCall();
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

                    if (mLayoutManager != null && mLayoutManager.findLastCompletelyVisibleItemPosition() == suggestedItemsArrayListDO.size() - 1 && suggestedItemsArrayListTest.size() !=0) {
                        //bottom of list!
                        Toast.makeText(getActivity(),"TAG !" +String.valueOf(currentPage)+ " Load more ...",Toast.LENGTH_SHORT).show();

//                        new Handler().postDelayed(new Runnable() {
//
//                            @RequiresApi(api = Build.VERSION_CODES.M)
//                            @Override
//                            public void run() {
//                                Log.i("TAG BAG","currentPage: "+String.valueOf(currentPage));
//
//
//                            }
//                        }, 2000);

                        doApiCall();
                        currentPage ++;
                        isLoading = true;

                    }
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void doApiCall() {
        suggestedItemsArrayListTest = new ArrayList<>();
        Call<List<Offer>> call = jsonPlaceHolderApiOffers.getOffers(suggestedItemsArrayListTest.size(),16);
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

    private void actionListenerToSeeAll() {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                //bundle.putString("category",getActivity().getResources().getString(R.string.set));
//                bundle.putString("category",getActivity().getResources().getString(R.string.set));
//                bundle.putString("from","see_all");

                Intent intent = new Intent(getActivity(), ResultActivity.class);
                //intent.putExtras(bundle);
                ((Activity)getActivity()).startActivity(intent);
                ((Activity)getActivity()).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
            }
        });
    }

    private void changeFont() {
        headerTV.setTypeface(Functions.changeFontGeneral(getActivity()));
        full_image_and_see_all_TV.setTypeface(Functions.changeFontGeneral(getActivity()));
    }

    private void inti() {
        recyclerView = (RecyclerView) view.findViewById(R.id.suggested_RV);
        headerTV = (TextView) view.findViewById(R.id.suggested_TV);
        full_image_and_see_all_TV = (TextView) view.findViewById(R.id.full_image_and_see_all_TV);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.see_all_suggested_rl);
    }

}
