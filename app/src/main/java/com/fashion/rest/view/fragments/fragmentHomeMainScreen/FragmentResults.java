package com.fashion.rest.view.fragments.fragmentHomeMainScreen;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fashion.rest.R;
import com.fashion.rest.model.Categories;
import com.fashion.rest.model.CustomCategory;
import com.fashion.rest.model.CustomItems;
import com.fashion.rest.model.Deal;
import com.fashion.rest.model.Home;
import com.fashion.rest.model.ItemTest;
import com.fashion.rest.presnter.JsonPlaceHolderApi;
import com.fashion.rest.view.Adapters.AdapterEndlessOffers;
import com.fashion.rest.view.Adapters.AdapterEndlessResult;
import com.fashion.rest.view.activity.mainScreem.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.fashion.rest.functions.FillItem.fillAllItemDepCatArrayL;
import static com.fashion.rest.functions.FillItem.fillEndlessItemDepCatArrayL;
import static com.fashion.rest.functions.FillItem.fillEndlessItemDepCatArrayL2;
import static com.fashion.rest.functions.RetrofitFunctions.getItems;
import static com.fashion.rest.functions.RetrofitFunctions.getItemsWithAllFilter;


public class FragmentResults extends Fragment {
    public ArrayList<CustomItems> dealsArrayList = new ArrayList<>();
    public ArrayList<CustomItems> suggestedItemsArrayListTest;
    public ArrayList<CustomItems> suggestedItemsArrayListDO;

    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 2;
    private boolean isLoading = false;

    LinearLayoutManager mLayoutManager;
    AdapterEndlessResult adapterEndlessResult ;
    static JsonPlaceHolderApi jsonPlaceHolderApi;
    static Retrofit retrofit;

    public FragmentResults() {
    }

    View view;
    RecyclerView results_RV;
    NestedScrollView nestedScrollView;

    String cat_id,sub_cat_id;

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            cat_id = getArguments().getString("cat_id");
            sub_cat_id = getArguments().getString("sub_cat_id");
        }
        super.onAttach(context);
        intiRetrofit();
    }

    private void intiRetrofit() {
        retrofit = getItemsWithAllFilter(sub_cat_id,cat_id);
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_results, container, false);
        inti();
        currentPage = 1;

        createRV();
        //actionListenerToRV();
        actionListenerToNestedScroll();

        return view;
    }

    private void actionListenerToNestedScroll() {
        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(new
                                                                                  ViewTreeObserver.OnScrollChangedListener() {
                                                                                      @RequiresApi(api = Build.VERSION_CODES.M)
                                                                                      @Override
                                                                                      public void onScrollChanged() {
                                                                                          View view = (View) nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
                                                                                          int diff = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView.getScrollY()));
                                                                                          if (diff == 150) {
//                                                                                              new Handler().postDelayed(new Runnable() {
//
//                                                                                                  @RequiresApi(api = Build.VERSION_CODES.M)
//                                                                                                  @Override
//                                                                                                  public void run() {
//
//                                                                                                  }
//                                                                                              }, 2000);

                                                                                              currentPage ++;
                                                                                              isLoading = true;
                                                                                              doApiCall();
                                                                                          }
                                                                                      }
                                                                                  });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createRV() {
        adapterEndlessResult = new AdapterEndlessResult(new ArrayList<CustomItems>(),getActivity(),"call",currentPage);
        results_RV.setHasFixedSize(true);
        results_RV.setNestedScrollingEnabled(false);

        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        //mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        results_RV.setLayoutManager(mLayoutManager);
        results_RV.setAdapter(adapterEndlessResult);
        doApiCall();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void doApiCall() {
        suggestedItemsArrayListTest = new ArrayList<>();
        Call<List<ItemTest>> callHome = jsonPlaceHolderApi.getAllItems(0,16);
        callHome.enqueue(new Callback<List<ItemTest>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<ItemTest>> call, Response<List<ItemTest>> response) {
                if (!response.isSuccessful())
                { return; }
                List<ItemTest> itemsList = response.body();
//                Log.i("TAG","itemsList");
//                Log.i("TAG",String.valueOf(itemsList.size()));

                suggestedItemsArrayListTest.addAll(fillCustomItems(itemsList));
                suggestedItemsArrayListDO.addAll(fillCustomItems(itemsList));

                if (currentPage != PAGE_START && suggestedItemsArrayListTest.size()!=0) adapterEndlessResult.removeLoading();
                if (suggestedItemsArrayListTest.size()!=0)
                {
                    adapterEndlessResult.addItems(suggestedItemsArrayListTest);
                }
                if (suggestedItemsArrayListTest.size()!=0) {
                    adapterEndlessResult.addLoading();
                    isLoading = false;
                } else {
                    isLastPage = true;
                }
            }

            @Override
            public void onFailure(Call<List<ItemTest>> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private ArrayList<CustomItems> fillCustomItems(List<ItemTest> itemsList) {

        ArrayList<CustomItems> customItemsArrayList = new ArrayList<>();
        ArrayList<ItemTest> smallArrayL = new ArrayList<>();
        for (int i =0;i<itemsList.size();i++)
        {
            if(i % 2 == 0) {
                smallArrayL = new ArrayList<>();
                if (i==0)
                {
                    for (int j=0;j<2;j++)
                    {
                        if (i+j < itemsList.size())
                        {
                            smallArrayL.add(itemsList.get(i+j));
                        }
                    }
                    CustomItems customItems = new CustomItems(smallArrayL);
                    customItemsArrayList.add(customItems);
                }else{
                    for (int j=0;j<2;j++)
                    {
                        if (i+j < itemsList.size())
                        {
                            smallArrayL.add(itemsList.get(i+j));
                        }
                    }
                    CustomItems customItems = new CustomItems(smallArrayL);
                    customItemsArrayList.add(customItems);
                }

            }
        }
        return customItemsArrayList;
    }

    private void inti() {
        suggestedItemsArrayListDO = new ArrayList<>();
        suggestedItemsArrayListTest = new ArrayList<>();

        results_RV = (RecyclerView) view.findViewById(R.id.results_RV);
        nestedScrollView = (NestedScrollView) view.findViewById(R.id.results_nested);
    }

}
