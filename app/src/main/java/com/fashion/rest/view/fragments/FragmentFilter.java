package com.fashion.rest.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fashion.rest.R;
import com.fashion.rest.model.ItemFilterModel;
import com.fashion.rest.model.ItemSelectedFilterModel;
import com.fashion.rest.presnter.Filter;
import com.fashion.rest.view.Adapters.AdapterFiltersItem;
import com.fashion.rest.view.Adapters.AdapterSelectedFilters;

import java.util.ArrayList;

import static com.fashion.rest.functions.CategoryFunction.fillCatObject;
import static com.fashion.rest.functions.CategoryFunction.fillFromPrice;

public class FragmentFilter extends Fragment implements AdapterSelectedFilters.PassCancelItem,AdapterFiltersItem.PassFilter{
    View view;
    RecyclerView recyclerView_selectedFilter,recyclerView_filterNow;
    ArrayList<ItemSelectedFilterModel> selectedFilterArrayL = new ArrayList<ItemSelectedFilterModel>();
    ArrayList<ItemSelectedFilterModel> itemTypeFromFilterAdapter = new ArrayList<ItemSelectedFilterModel>();

    int numberOfSelectedFilter=0;
    RecyclerView.LayoutManager layoutManagerSelected,layoutManagerSuggested;
    AdapterSelectedFilters adapterSelectedFilters;
    Filter filter;
    String categoryStr;
    AdapterFiltersItem adapterFiltersItem;
    ArrayList<ItemFilterModel> filterItemsArrayL = new ArrayList<ItemFilterModel>();

    public FragmentFilter(){}


    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            categoryStr = getArguments().getString("category");
        }
        super.onAttach(context);
        if (context instanceof Filter) {
            filter = (Filter) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        filter = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_filter, container, false);
        inti();
        createSelectedFilter();
        createFilterNow();
        return view;
    }

    private void createFilterNow() {
        filterItemsArrayL = fillFromPrice(getActivity(),categoryStr);
        recyclerView_filterNow.setHasFixedSize(true);
        layoutManagerSuggested = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView_filterNow.setLayoutManager(layoutManagerSuggested);
        adapterFiltersItem =new AdapterFiltersItem(getActivity()
                , filterItemsArrayL,"category",this);
        recyclerView_filterNow.setAdapter(adapterFiltersItem);
    }

    private void createSelectedFilter() {
        fillSelected();
        recyclerView_selectedFilter.setHasFixedSize(true);
        layoutManagerSelected = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView_selectedFilter.setLayoutManager(layoutManagerSelected);
        adapterSelectedFilters =new AdapterSelectedFilters(getActivity()
                ,selectedFilterArrayL,"main_screen",this);
        recyclerView_selectedFilter.setAdapter(adapterSelectedFilters);
    }

    private void fillSelected() {
        if (numberOfSelectedFilter==0)
        {
            selectedFilterArrayL = new ArrayList<ItemSelectedFilterModel>();
            selectedFilterArrayL.add(fillCatObject(categoryStr,getActivity()));
        }
    }

    private void inti() {
        recyclerView_selectedFilter = (RecyclerView) view.findViewById(R.id.selected_filter_RV);
        recyclerView_filterNow = (RecyclerView) view.findViewById(R.id.filter_now_RV);
    }

    @Override
    public void onFilterClicked(ItemSelectedFilterModel itemSelectedFilterModel) {
        numberOfSelectedFilter =numberOfSelectedFilter-1;
        //itemTypeFromFilterAdapter = itemSelectedFilterModel.getFilterS();
        if (numberOfSelectedFilter==0)
        {
            itemTypeFromFilterAdapter.clear();
            selectedFilterArrayL.remove(selectedFilterArrayL.size()-1);
            selectedFilterArrayL.add(new ItemSelectedFilterModel(getActivity().getResources().getString(R.string.all),getActivity().getResources().getString(R.string.all),"empty" ));
            adapterSelectedFilters.notifyDataSetChanged();
        }else{
            itemTypeFromFilterAdapter.remove(itemTypeFromFilterAdapter.size()-1);

            selectedFilterArrayL.remove(selectedFilterArrayL.size()-1);
            adapterSelectedFilters.notifyDataSetChanged();
        }
        filter.onFilterCancel();
        //createFilterTowRV();
    }

    @Override
    public void onFilterClicked(ItemFilterModel itemFilterModel) {

    }
}
