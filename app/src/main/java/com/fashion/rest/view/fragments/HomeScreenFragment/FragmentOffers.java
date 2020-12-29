package com.fashion.rest.view.fragments.HomeScreenFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Deal;
import com.fashion.rest.presnter.PassObject;
import com.fashion.rest.utils.PaginationListener;
import com.fashion.rest.view.Adapters.AdapterEndlessOffers;
import com.fashion.rest.view.Adapters.AdapterOffers;

import java.util.ArrayList;
import java.util.List;

import static com.fashion.rest.functions.FillItem.fillEndlessItemDepCatArrayL;
import static com.fashion.rest.functions.Functions.fillSetArrayL;
import static com.fashion.rest.functions.Functions.nowNumberOfObject;
import static com.fashion.rest.utils.PaginationListener.PAGE_START;

public class FragmentOffers extends Fragment implements AdapterOffers.PassItem {
    View view;
    RecyclerView recyclerView;
    AdapterOffers adapterOffers;
    RecyclerView.LayoutManager layoutManager;
    public ArrayList<Deal> dealsArrayList = new ArrayList<>();
    public List<Deal> suggestedItemsArrayListTest;
    public List<Deal> suggestedItemsArrayListDO;

    PassObject passObject;

    private int currentPage = PAGE_START;
    private int totalPage = 10;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    LinearLayoutManager mLayoutManager;
    AdapterEndlessOffers adapterEndlessOffers ;
    int numberOfObjectNow = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PassObject) {
            passObject = (PassObject) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        passObject = null;
    }

    public FragmentOffers(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_offers, container, false);
        inti();
        createRV();
        actionListenerToRV();
        //createRVSuggested();
        return view;
    }

//    private void changeFont() {
//        headerTV.setTypeface(Functions.changeFontCategory(getActivity()));
//    }

    private void actionListenerToRV() {
        recyclerView.addOnScrollListener(new PaginationListener(mLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                doApiCall();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    private void createRV() {
        adapterEndlessOffers = new AdapterEndlessOffers(new ArrayList<Deal>(),getActivity(),"call");
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapterEndlessOffers);
        doApiCall();
    }

    private void doApiCall() {
        suggestedItemsArrayListDO = new ArrayList<>();
        suggestedItemsArrayListTest = fillEndlessItemDepCatArrayL(getActivity());
        suggestedItemsArrayListDO.addAll(suggestedItemsArrayListTest);
        //fill here
        if (currentPage != PAGE_START) adapterEndlessOffers.removeLoading();
        adapterEndlessOffers.addItems(suggestedItemsArrayListDO);
        if (currentPage < totalPage) {
            adapterEndlessOffers.addLoading();
        } else {
            isLastPage = true;
        }
    }

    private void inti() {
        recyclerView = (RecyclerView) view.findViewById(R.id.offer_RV);
    }

    private void createRVSuggested() {
        dealsArrayList = fillSetArrayL(dealsArrayList,getActivity());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        adapterOffers =new AdapterOffers(getActivity()
                ,dealsArrayList,getActivity().getResources().getString(R.string.set_s),this);
        recyclerView.setAdapter(adapterOffers);
    }

    @Override
    public void onClicked(Deal deal) {
        passObject.PassItemObject(deal);
    }
}
