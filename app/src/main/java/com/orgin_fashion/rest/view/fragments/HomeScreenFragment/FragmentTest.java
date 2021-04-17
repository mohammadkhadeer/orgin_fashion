package com.orgin_fashion.rest.view.fragments.HomeScreenFragment;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orgin_fashion.rest.R;
import com.orgin_fashion.rest.model.Deal;
import com.orgin_fashion.rest.view.Adapters.AdapterEndlessOffers;


import java.util.ArrayList;


public class FragmentTest extends Fragment {
    View view;
    RecyclerView recyclerView;
    boolean isLoading = false;
    public ArrayList<Deal> dealsArrayList = new ArrayList<>();
    public ArrayList<Deal> suggestedItemsArrayListTest;
    public ArrayList<Deal> suggestedItemsArrayListDO;


    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;

    LinearLayoutManager mLayoutManager;
    AdapterEndlessOffers adapterEndlessOffers ;

    public FragmentTest(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_test, container, false);
        suggestedItemsArrayListDO = new ArrayList<>();
        suggestedItemsArrayListTest = new ArrayList<>();

        inti();
//        createRV();
//        actionListenerToRV();


        return view;
    }

//    private void actionListenerToRV() {
//
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
////                Log.i("TAG BAG","isLoading: "+String.valueOf(isLoading));
////                Log.i("TAG BAG","mLayoutManager.findLastCompletelyVisibleItemPosition(): "+String.valueOf(mLayoutManager.findLastCompletelyVisibleItemPosition()));
////                Log.i("TAG BAG","suggestedItemsArrayListDO.size() - 1: "+String.valueOf(suggestedItemsArrayListDO.size() - 1));
////
////                if (mLayoutManager != null)
////                {
////                    Log.i("TAG BAG","mLayoutManager: NOT NOT ~NOT null");
////                }else{
////                    Log.i("TAG BAG","mLayoutManager:Null");
////                }
//                if (!isLoading) {
//                    Log.i("TAG BAG","After if isLoading: "+String.valueOf(isLoading));
//
//                    if (mLayoutManager != null && mLayoutManager.findLastCompletelyVisibleItemPosition() == suggestedItemsArrayListDO.size() - 1) {
//                        //bottom of list!
//                        Toast.makeText(getActivity(),"TAG !" +String.valueOf(currentPage)+ " Load more ...",Toast.LENGTH_SHORT).show();
//
//                        new Handler().postDelayed(new Runnable() {
//
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
//
////        recyclerView.addOnScrollListener(new PaginationListener(mLayoutManager) {
////            @Override
////            protected void loadMoreItems() {
////                isLoading = true;
////                currentPage++;
////                Toast.makeText(getActivity(),"TAG !" +String.valueOf(currentPage)+ " Load more ...",Toast.LENGTH_SHORT).show();
////                new Handler().postDelayed(new Runnable() {
////
////                    @Override
////                    public void run() {
////
////                        doApiCall();
////
////                    }
////                }, 2000);
////            }
////
////            @Override
////            public boolean isLastPage() {
////                return isLastPage;
////            }
////
////            @Override
////            public boolean isLoading() {
////                return isLoading;
////            }
////        });
//    }
//
//    private void createRV() {
//        adapterEndlessOffers = new AdapterEndlessOffers(new ArrayList<Deal>(),getActivity(),"call");
//        recyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(getActivity());
//        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setAdapter(adapterEndlessOffers);
//        doApiCall();
//    }
//
//    private void doApiCall() {
//        suggestedItemsArrayListTest = new ArrayList<>();
//        Log.i("TAG BAG","doApiCall: ");
//        suggestedItemsArrayListTest = fillEndlessItemDepCatArrayL(suggestedItemsArrayListTest,getActivity());
//        suggestedItemsArrayListDO = fillEndlessItemDepCatArrayL(suggestedItemsArrayListDO,getActivity());
////        suggestedItemsArrayListTest.addAll(suggestedItemsArrayListDO);
//
//        //fill here
//        if (currentPage != PAGE_START) adapterEndlessOffers.removeLoading();
//        adapterEndlessOffers.addItems(suggestedItemsArrayListTest);
//        if (currentPage < totalPage) {
//            adapterEndlessOffers.addLoading();
//            isLoading = false;
//        } else {
//            isLastPage = true;
//        }
//    }


    private void inti() {
        recyclerView = (RecyclerView) view.findViewById(R.id.test_RV);
    }

}
