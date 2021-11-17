package com.blue_b.rest.view.fragments.HomeScreenFragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blue_b.rest.R;
import com.blue_b.rest.model.Home;
import com.blue_b.rest.model.ListItem;
import com.blue_b.rest.presnter.JsonPlaceHolderApi;
import com.blue_b.rest.presnter.PassObject;
import com.blue_b.rest.view.Adapters.AdapterEndlessCategory;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.blue_b.rest.functions.RetrofitFunctions.getHome;

public class FragmentCategory extends Fragment{
    View view;
    RecyclerView recyclerView;
    public ArrayList<ListItem> dealsArrayList = new ArrayList<>();
    public ArrayList<Home> suggestedItemsArrayListTest;
    public ArrayList<Home> suggestedItemsArrayListDO;
    PassObject passObject;
    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 50000;
    private boolean isLoading = false;
    GridLayoutManager mLayoutManager;
    AdapterEndlessCategory adapterEndlessCategory ;
    int controler;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    Retrofit retrofit;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PassObject) {
            passObject = (PassObject) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
        retrofit = getHome();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        passObject = null;
    }

    public FragmentCategory(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_category, container, false);
        suggestedItemsArrayListDO = new ArrayList<>();
        suggestedItemsArrayListTest = new ArrayList<>();

        inti();
        createRV();

        return view;
    }

    public void loadMore(){
        if (controler ==0 )
        {
            isLoading = true;
            currentPage++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // set this number coz when scroll don nested scroll call the method twice
                    doApiCall();

                }
            }, 100);
        }
        controler =1;
        handelControler();
    }

    private void handelControler() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // set this number coz when scroll don nested scroll call the method twice
                controler =0;

            }
        }, 150);
    }

    private void createRV() {
        adapterEndlessCategory = new AdapterEndlessCategory(new ArrayList<Home>(),getActivity(),"call");
        recyclerView.setHasFixedSize(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapterEndlessCategory);
        doApiCall();
    }

    private void doApiCall() {
        //here
        suggestedItemsArrayListTest = new ArrayList<>();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        int max = suggestedItemsArrayListDO.size() + 4;
        Call<List<Home>> callHome = jsonPlaceHolderApi.getHome(suggestedItemsArrayListDO.size(),max);
        callHome.enqueue(new Callback<List<Home>>() {
            @Override
            public void onResponse(Call<List<Home>> call, Response<List<Home>> response) {
                if (!response.isSuccessful())
                {
                    Log.i("TAG Error code", String.valueOf(response.code()));
                    return;
                }

                List<Home> homeList = response.body();

                for (Home home:homeList)
                {
                    suggestedItemsArrayListTest.add(home);
                    suggestedItemsArrayListDO.add(home);
                }

                if (currentPage != PAGE_START && suggestedItemsArrayListTest.size()!=0) adapterEndlessCategory.removeLoading();
                if (suggestedItemsArrayListTest.size()!=0)
                {
                    adapterEndlessCategory.addItems(suggestedItemsArrayListTest);
                }
                if (suggestedItemsArrayListTest.size()!=0) {
                    adapterEndlessCategory.addLoading();
                    isLoading = false;
                } else {
                    isLastPage = true;
                }

//                // 3'er el if tb3at el pagenation kolha 7dd el braket
//                Log.i("TAG",String.valueOf(suggestedItemsArrayListTest.size()));
//                if (currentPage != PAGE_START) adapterEndlessCategory.removeLoading();
//                adapterEndlessCategory.addItems(suggestedItemsArrayListTest);
//                if (currentPage < totalPage) {
//                    adapterEndlessCategory.addLoading();
//                    isLoading = false;
//                } else {
//                    isLastPage = true;
//                }

            }

            @Override
            public void onFailure(Call<List<Home>> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });

    }

    private void inti() {
        recyclerView = (RecyclerView) view.findViewById(R.id.category_RV);
    }

}
