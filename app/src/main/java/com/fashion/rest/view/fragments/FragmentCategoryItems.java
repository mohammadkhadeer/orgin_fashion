package com.fashion.rest.view.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fashion.rest.R;
import com.fashion.rest.model.Deal;
import com.fashion.rest.view.Adapters.AdapterCategoryItems;
import java.util.ArrayList;

public class FragmentCategoryItems extends Fragment {
    View view;
    RecyclerView recyclerView_items;
    AdapterCategoryItems adapterCategoryItems;
    public ArrayList<Deal> dealsArrayList = new ArrayList<>();
    String categoryStr;

    public FragmentCategoryItems(){}

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            categoryStr = getArguments().getString("category");
        }
        super.onAttach(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_category_item, container, false);
        inti();
        createItems();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createItems() {
//        dealsArrayList = fillAllItemDepCatArrayL(categoryStr,getActivity());
//        recyclerView_items.setHasFixedSize(true);
//        recyclerView_items.setNestedScrollingEnabled(false);
//        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
//        recyclerView_items.setLayoutManager(mLayoutManager);
//        adapterCategoryItems =new AdapterCategoryItems(getActivity()
//                ,dealsArrayList);
//        recyclerView_items.setAdapter(adapterCategoryItems);
    }

    private void inti() {
        recyclerView_items= (RecyclerView) view.findViewById(R.id.items_RV);
    }
}
