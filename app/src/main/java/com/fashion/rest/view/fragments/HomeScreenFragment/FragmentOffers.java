package com.fashion.rest.view.fragments.HomeScreenFragment;


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
import android.widget.Toast;

import com.fashion.rest.R;
import com.fashion.rest.model.Deal;
import com.fashion.rest.presnter.PassObject;
import com.fashion.rest.utils.PaginationListener;
import com.fashion.rest.view.Adapters.AdapterEndlessOffers;
import com.fashion.rest.view.Adapters.AdapterOffers;


import java.util.ArrayList;
import java.util.List;

import static com.fashion.rest.functions.FillItem.fillEndlessItemDepCatArrayL;
import static com.fashion.rest.utils.PaginationListener.PAGE_START;
import static com.fashion.rest.view.categoriesComp.FillType3.fillCase3Item;


public class FragmentOffers extends Fragment{
    View view;
    RecyclerView recyclerView,recyclerViewCat;
    AdapterOffers adapterOffers;
    RecyclerView.LayoutManager layoutManager;
    public ArrayList<Deal> dealsArrayList = new ArrayList<>();
    public ArrayList<Deal> suggestedItemsArrayListTest;
    public ArrayList<Deal> suggestedItemsArrayListDO;

    PassObject passObject;

    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;

    LinearLayoutManager mLayoutManager;
    AdapterEndlessOffers adapterEndlessOffers ;

    int numberOfObjectNow = 0;

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof PassObject) {
//            passObject = (PassObject) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement FragmentAListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        passObject = null;
//    }

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
        actionListenerToRV();
        fillCase3Item(recyclerViewCat,getActivity());

        //createRVSuggested();
        return view;
    }

    private void actionListenerToRV() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Log.i("TAG BAG","isLoading: "+String.valueOf(isLoading));
//                Log.i("TAG BAG","mLayoutManager.findLastCompletelyVisibleItemPosition(): "+String.valueOf(mLayoutManager.findLastCompletelyVisibleItemPosition()));
//                Log.i("TAG BAG","suggestedItemsArrayListDO.size() - 1: "+String.valueOf(suggestedItemsArrayListDO.size() - 1));
//
//                if (mLayoutManager != null)
//                {
//                    Log.i("TAG BAG","mLayoutManager: NOT NOT ~NOT null");
//                }else{
//                    Log.i("TAG BAG","mLayoutManager:Null");
//                }
                if (!isLoading) {

                    if (mLayoutManager != null && mLayoutManager.findLastCompletelyVisibleItemPosition() == suggestedItemsArrayListDO.size() - 1) {
                        //bottom of list!
                        Toast.makeText(getActivity(),"TAG !" +String.valueOf(currentPage)+ " Load more ...",Toast.LENGTH_SHORT).show();

                        new Handler().postDelayed(new Runnable() {

                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void run() {
                                Log.i("TAG BAG","currentPage: "+String.valueOf(currentPage));
                                doApiCall();

                            }
                        }, 2000);
                        currentPage ++;
                        isLoading = true;

                    }
                }
            }
        });

//        recyclerView.addOnScrollListener(new PaginationListener(mLayoutManager) {
//            @Override
//            protected void loadMoreItems() {
//                isLoading = true;
//                currentPage++;
//                Toast.makeText(getActivity(),"TAG !" +String.valueOf(currentPage)+ " Load more ...",Toast.LENGTH_SHORT).show();
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//
//                        doApiCall();
//
//                    }
//                }, 2000);
//            }
//
//            @Override
//            public boolean isLastPage() {
//                return isLastPage;
//            }
//
//            @Override
//            public boolean isLoading() {
//                return isLoading;
//            }
//        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createRV() {
        adapterEndlessOffers = new AdapterEndlessOffers(new ArrayList<Deal>(),getActivity(),"call");
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
        suggestedItemsArrayListTest = fillEndlessItemDepCatArrayL(suggestedItemsArrayListTest,getActivity());
        suggestedItemsArrayListDO = fillEndlessItemDepCatArrayL(suggestedItemsArrayListDO,getActivity());
//        suggestedItemsArrayListTest.addAll(suggestedItemsArrayListDO);

        //fill here
        if (currentPage != PAGE_START) adapterEndlessOffers.removeLoading();
        adapterEndlessOffers.addItems(suggestedItemsArrayListTest);
        if (currentPage < totalPage) {
            adapterEndlessOffers.addLoading();
            isLoading = false;
        } else {
            isLastPage = true;
        }
    }

    private void inti() {
        recyclerView = (RecyclerView) view.findViewById(R.id.offer_RV);
        recyclerViewCat = (RecyclerView) view.findViewById(R.id.type3_RV);

    }

}
