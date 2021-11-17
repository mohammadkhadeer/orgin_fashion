package com.blue_b.rest.view.fragments.fragmentHomeMainScreen;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.RelativeLayout;

import com.blue_b.rest.R;
import com.blue_b.rest.model.CustomItems;
import com.blue_b.rest.model.FilterItemsModel;
import com.blue_b.rest.model.ItemTest;
import com.blue_b.rest.presnter.JsonPlaceHolderApi;
import com.blue_b.rest.view.Adapters.AdapterEndlessResult;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import static com.blue_b.rest.functions.RetrofitFunctions.getItems;
import static com.blue_b.rest.functions.RetrofitFunctions.getStoreItems;

public class FragmentResults extends Fragment {
    public ArrayList<CustomItems> dealsArrayList = new ArrayList<>();
    public ArrayList<CustomItems> suggestedItemsArrayListTest;
    public ArrayList<CustomItems> suggestedItemsArrayListDO;

    public ArrayList<ItemTest> toPagnationParpos= new ArrayList<>();;

    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 2;
    private boolean isLoading = false;

    LinearLayoutManager mLayoutManager;
    AdapterEndlessResult adapterEndlessResult ;
    static JsonPlaceHolderApi jsonPlaceHolderApi;
    static Retrofit retrofit;
    private FilterItemsModel filterItemsModel;

    public FragmentResults() {
    }

    View view;
    RecyclerView results_RV;
    NestedScrollView nestedScrollView;

    String cat_id,sub_cat_id,store_id,store_area;
    RelativeLayout no_items;

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            cat_id = getArguments().getString("cat_id");
            sub_cat_id = getArguments().getString("sub_cat_id");
            store_id = getArguments().getString("store_id");
            store_area = getArguments().getString("store_area");

            filterItemsModel = (FilterItemsModel) getArguments().getParcelable("filter_object");
        }
        super.onAttach(context);

        if (store_id != null || store_id == " ")
        {
            retrofit = getStoreItems(store_id,store_area);
        }else{
            retrofit = getItems(filterItemsModel);
//            retrofit = getItemsWithAllFilter(sub_cat_id,cat_id);
        }
        intiPlaceHolder();
    }

    private void intiPlaceHolder() {
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
                                                                                          if (diff == 0) {
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
    int stopLoading = 0;
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void doApiCall() {
        suggestedItemsArrayListTest = new ArrayList<>();
        int max = toPagnationParpos.size()+8;
//        Log.i("TAG","toPagnationParpos: "+String.valueOf(toPagnationParpos.size()));
//        Log.i("TAG","max: "+String.valueOf(max));
        Call<List<ItemTest>> callHome = jsonPlaceHolderApi.getAllItems(toPagnationParpos.size(),max);
        callHome.enqueue(new Callback<List<ItemTest>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<ItemTest>> call, Response<List<ItemTest>> response) {
                if (!response.isSuccessful())
                { return; }
                List<ItemTest> itemsList = response.body();
//                Log.i("TAG","itemsList");

                suggestedItemsArrayListTest.addAll(fillCustomItems(itemsList));
                suggestedItemsArrayListDO.addAll(fillCustomItems(itemsList));
                // i well use this to can handel pagenation in right way cos when use
                //suggestedItemsArrayListDO well not give right number cos i redesign the list to contean 2 items in same way
                toPagnationParpos.addAll(itemsList);
//                Log.i("TAG","toPagnationParpos: "+String.valueOf(toPagnationParpos.size()));

                if (currentPage != PAGE_START && suggestedItemsArrayListTest.size()!=0) adapterEndlessResult.removeLoading();
                if (suggestedItemsArrayListTest.size()!=0)
                {
                    adapterEndlessResult.addItems(suggestedItemsArrayListTest);
                }
                if (itemsList.size()!=0) {
                    if (8 ==itemsList.size())
                    {
                        adapterEndlessResult.addLoading();
                        isLoading = false;
                    }else{
                        isLoading = false;
                    }

                } else {
                    isLastPage = true;
//                    if (stopLoading ==0 && toPagnationParpos.size() >=8)
//                    {
//                        adapterEndlessResult.stopLoading(stopLoading);
//                        Log.i("TAG","im here");
//                        stopLoading =1;
//
//                    }
                }

                //no items
                if (suggestedItemsArrayListDO.size() ==0)
                {
//                    Log.i("TAG","insaid if 0");
                    no_items.setVisibility(View.VISIBLE);
                }else{
                    no_items.setVisibility(View.GONE);
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
        no_items = (RelativeLayout) view.findViewById(R.id.no_items);
    }

}
