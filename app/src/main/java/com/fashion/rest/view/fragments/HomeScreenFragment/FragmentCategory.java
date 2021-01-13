package com.fashion.rest.view.fragments.HomeScreenFragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fashion.rest.R;
import com.fashion.rest.model.Deal;
import com.fashion.rest.model.ListItem;
import com.fashion.rest.presnter.PassObject;
import com.fashion.rest.view.Adapters.AdapterEndlessCategory;
import com.fashion.rest.view.Adapters.AdapterOffers;
import com.fashion.rest.view.Adapters.AdapterType2;
import java.util.ArrayList;
import static com.fashion.rest.functions.FillItem.fillEndlessItemListArrayL;

public class FragmentCategory extends Fragment{
    View view;
    RecyclerView recyclerView;
    AdapterOffers adapterOffers;
    RecyclerView.LayoutManager layoutManager;
    public ArrayList<ListItem> dealsArrayList = new ArrayList<>();
    public ArrayList<ListItem> suggestedItemsArrayListTest;
    public ArrayList<ListItem> suggestedItemsArrayListDO;

    PassObject passObject;

    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;

    GridLayoutManager mLayoutManager;
    AdapterEndlessCategory adapterEndlessCategory ;

    int controler;

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

    public FragmentCategory(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_category, container, false);
        suggestedItemsArrayListDO = new ArrayList<>();
        suggestedItemsArrayListTest = new ArrayList<>();

        inti();
        createRV();
        //actionListenerToRV();

        //createRVSuggested();
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
            }, 1000);
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
        }, 1500);
    }

    private void createRV() {
        adapterEndlessCategory = new AdapterEndlessCategory(new ArrayList<ListItem>(),getActivity(),"call");
        recyclerView.setHasFixedSize(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapterEndlessCategory);
        doApiCall();
    }

    private void doApiCall() {
        suggestedItemsArrayListTest = new ArrayList<>();
        suggestedItemsArrayListTest = fillEndlessItemListArrayL(suggestedItemsArrayListTest,getActivity());
        suggestedItemsArrayListDO = fillEndlessItemListArrayL(suggestedItemsArrayListDO,getActivity());
//        suggestedItemsArrayListTest.addAll(suggestedItemsArrayListDO);

        //fill here
        if (currentPage != PAGE_START) adapterEndlessCategory.removeLoading();
        adapterEndlessCategory.addItems(suggestedItemsArrayListTest);
        if (currentPage < totalPage) {
            adapterEndlessCategory.addLoading();
            isLoading = false;
        } else {
            isLastPage = true;
        }
    }

    private void inti() {
        recyclerView = (RecyclerView) view.findViewById(R.id.category_RV);
    }

}
