package com.azez.rest.view.fragmentHomeMainScreen;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.azez.rest.R;
import com.azez.rest.functions.Functions;
import com.azez.rest.model.Category;
import com.azez.rest.model.Offer;
import com.azez.rest.view.Adapters.AdapterCategory;
import com.azez.rest.view.Adapters.AdapterMainList;
import com.azez.rest.view.Adapters.AdapterSuggestedToYou;

import java.util.ArrayList;
import java.util.List;

import static com.azez.rest.firebase.FireBaseLinkes.getOffers;
import static com.azez.rest.functions.Functions.fillMeanArrayL;
import static com.azez.rest.functions.Functions.fillOptionsArrayL;

public class FragmentHomeScreen extends Fragment{

    View view;
    List<Offer> mList = new ArrayList<>();
    public ArrayList<Category> categoryArrayL = new ArrayList<>();

    public ArrayList<String> meanList = new ArrayList<>();

    TextView suggestedTV;
    RecyclerView suggestedRV,categoryRV,meanRV;
    AdapterSuggestedToYou adapterSuggestedToYou;
    AdapterMainList adapterMainList;
    AdapterCategory adapterCategory;
    RecyclerView.LayoutManager layoutManagerSuggested,layoutManagerCategory;
    ProgressBar progressBar;

    public FragmentHomeScreen(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        init();
        changeFont();
        readFromDataBase();
        createCategoryRV();
        createMeanL();

        return view;
    }

    private void createMeanL() {
        meanList =fillMeanArrayL(meanList,getActivity());
        meanRV.setHasFixedSize(true);
        meanRV.setNestedScrollingEnabled(false);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        meanRV.setLayoutManager(mLayoutManager);
        adapterMainList = new AdapterMainList(getActivity(), meanList);
        meanRV.setAdapter(adapterMainList);
    }

    private void createCategoryRV() {
        categoryArrayL = fillOptionsArrayL(categoryArrayL,getActivity());
        categoryRV.setNestedScrollingEnabled(false);
        categoryRV.setHasFixedSize(true);
        layoutManagerSuggested = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        categoryRV.setLayoutManager(layoutManagerSuggested);
        adapterCategory =new AdapterCategory(getActivity()
                ,categoryArrayL);
        categoryRV.setAdapter(adapterCategory);
    }

    private void init() {
        meanRV = (RecyclerView) view.findViewById(R.id.fragment_home_mean_RV);
        suggestedRV = (RecyclerView) view.findViewById(R.id.fragment_home_suggested_RV);
        categoryRV = (RecyclerView) view.findViewById(R.id.fragment_home_category_RV);
        suggestedTV = view.findViewById(R.id.fragment_haom_suggested_TV);
        progressBar = view.findViewById(R.id.fragment_haom_suggested_PB);
    }

    private void readFromDataBase() {
        mList = getOffers(mList);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                createRVSuggested();

            }
        }, 1700);

    }

    private void createRVSuggested() {
        suggestedRV.setNestedScrollingEnabled(false);
        suggestedRV.setHasFixedSize(true);
        layoutManagerSuggested = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        suggestedRV.setLayoutManager(layoutManagerSuggested);
        adapterSuggestedToYou =new AdapterSuggestedToYou(getActivity()
                ,mList);
        suggestedRV.setAdapter(adapterSuggestedToYou);

        progressBar.setVisibility(View.GONE);
    }

    private void changeFont() {
        suggestedTV.setTypeface(Functions.changeFontGeneral(getActivity()));
    }
}
