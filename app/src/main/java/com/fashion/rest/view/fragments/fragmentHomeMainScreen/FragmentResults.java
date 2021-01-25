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
import com.fashion.rest.model.Deal;
import com.fashion.rest.view.Adapters.AdapterEndlessOffers;
import com.fashion.rest.view.Adapters.AdapterEndlessResult;
import com.fashion.rest.view.activity.mainScreem.MainActivity;

import java.util.ArrayList;

import static com.fashion.rest.functions.FillItem.fillAllItemDepCatArrayL;
import static com.fashion.rest.functions.FillItem.fillEndlessItemDepCatArrayL;
import static com.fashion.rest.functions.FillItem.fillEndlessItemDepCatArrayL2;


public class FragmentResults extends Fragment {
    public ArrayList<Deal> dealsArrayList = new ArrayList<>();
    public ArrayList<Deal> suggestedItemsArrayListTest;
    public ArrayList<Deal> suggestedItemsArrayListDO;

    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 2;
    private boolean isLoading = false;

    LinearLayoutManager mLayoutManager;
    AdapterEndlessResult adapterEndlessResult ;

    public FragmentResults() {
    }

    View view;
    RecyclerView results_RV;
    NestedScrollView nestedScrollView;

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
//            phoneNumber = getArguments().getString("phoneN");
        }
        super.onAttach(context);
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
                                                                                      @Override
                                                                                      public void onScrollChanged() {
                                                                                          View view = (View) nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
                                                                                          int diff = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView.getScrollY()));
                                                                                          if (diff == 0) {
                                                                                              new Handler().postDelayed(new Runnable() {

                                                                                                  @RequiresApi(api = Build.VERSION_CODES.M)
                                                                                                  @Override
                                                                                                  public void run() {
                                                                                                      Log.i("TAG BAG","currentPage: "+String.valueOf(currentPage));
                                                                                                      currentPage ++;
                                                                                                      isLoading = true;
                                                                                                      doApiCall();
                                                                                                  }
                                                                                              }, 2000);

                                                                                          }
                                                                                      }
                                                                                  });
    }

//    private void actionListenerToRV() {
//        results_RV.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (!isLoading) {
//
//                    if (mLayoutManager != null && mLayoutManager.findLastCompletelyVisibleItemPosition() == suggestedItemsArrayListDO.size() - 1) {
//                        //bottom of list!
//                        Toast.makeText(getActivity(),"TAG !" +String.valueOf(currentPage)+ " Load more ...",Toast.LENGTH_SHORT).show();
//
//                        new Handler().postDelayed(new Runnable() {
//
//                            @RequiresApi(api = Build.VERSION_CODES.M)
//                            @Override
//                            public void run() {
//                                Log.i("TAG BAG","currentPage: "+String.valueOf(currentPage));
//                                doApiCall();
//
//                            }
//                        }, 2000);
//                        currentPage ++;
//                        isLoading = true;
//
//                    }
//                }
//            }
//        });
//    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createRV() {
        adapterEndlessResult = new AdapterEndlessResult(new ArrayList<Deal>(),getActivity(),"call",currentPage);
        results_RV.setHasFixedSize(true);
        results_RV.setNestedScrollingEnabled(false);

        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        //mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        results_RV.setLayoutManager(mLayoutManager);
        results_RV.setAdapter(adapterEndlessResult);
        doApiCall();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void doApiCall() {
        suggestedItemsArrayListTest = new ArrayList<>();
        suggestedItemsArrayListTest = fillEndlessItemDepCatArrayL2(suggestedItemsArrayListTest,getActivity(),currentPage);
        suggestedItemsArrayListDO = fillEndlessItemDepCatArrayL2(suggestedItemsArrayListDO,getActivity(),currentPage);
//        suggestedItemsArrayListTest.addAll(suggestedItemsArrayListDO);

        //fill here
        if (currentPage != PAGE_START) adapterEndlessResult.removeLoading();
        adapterEndlessResult.addItems(suggestedItemsArrayListTest);
        if (currentPage < totalPage) {
            adapterEndlessResult.addLoading();
            isLoading = false;
        } else {
            isLastPage = true;
        }
    }

    private void inti() {
        suggestedItemsArrayListDO = new ArrayList<>();
        suggestedItemsArrayListTest = new ArrayList<>();

        results_RV = (RecyclerView) view.findViewById(R.id.results_RV);
        nestedScrollView = (NestedScrollView) view.findViewById(R.id.results_nested);
    }

}
