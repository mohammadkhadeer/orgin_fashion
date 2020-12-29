package com.fashion.rest.view.fragments.fragmentHomeMainScreen;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Category;
import com.fashion.rest.model.Deal;
import com.fashion.rest.model.Offer;
import com.fashion.rest.view.Adapters.AdapterCategory;
import com.fashion.rest.view.Adapters.AdapterMainList;
import com.fashion.rest.view.Adapters.AdapterOffer;
import com.fashion.rest.view.Adapters.AdapterSuggestedToYou;
import com.fashion.rest.view.fragments.HomeScreenFragment.FragmentBracelets;
import com.fashion.rest.view.fragments.HomeScreenFragment.FragmentEarring;
import com.fashion.rest.view.fragments.HomeScreenFragment.FragmentFootAnklet;
import com.fashion.rest.view.fragments.HomeScreenFragment.FragmentNecklaces;
import com.fashion.rest.view.fragments.HomeScreenFragment.FragmentOffers;
import com.fashion.rest.view.fragments.HomeScreenFragment.FragmentRings;
import com.fashion.rest.view.fragments.HomeScreenFragment.FragmentSet;
import com.fashion.rest.view.fragments.HomeScreenFragment.FragmentTest;

import java.util.ArrayList;
import java.util.List;

import static com.fashion.rest.functions.Functions.fillOffersArrayL;
import static com.fashion.rest.functions.Functions.fillOptionsArrayL;

public class FragmentHomeScreen extends Fragment{

    View view;
    List<Offer> mList = new ArrayList<>();
    public ArrayList<Category> categoryArrayL = new ArrayList<>();

    public ArrayList<String> meanList = new ArrayList<>();

    public ArrayList<Deal> dealsArrayList = new ArrayList<>();

    TextView suggestedTV;
    RecyclerView suggestedRV,categoryRV,meanRV;
    AdapterSuggestedToYou adapterSuggestedToYou;
    AdapterMainList adapterMainList;
    AdapterOffer adapterOffer;
    AdapterCategory adapterCategory;
    RecyclerView.LayoutManager layoutManagerSuggested,layoutManagerCategory;
    FragmentSet fragmentSet = new FragmentSet();
    FragmentBracelets fragmentBracelets = new FragmentBracelets();
    FragmentNecklaces fragmentNecklaces = new FragmentNecklaces();
    FragmentRings fragmentRings = new FragmentRings();
    FragmentFootAnklet fragmentFootAnklet = new FragmentFootAnklet();
    FragmentEarring fragmentEarring = new FragmentEarring();
    FragmentOffers fragmentOffers = new FragmentOffers();
    FragmentTest fragmentTest = new FragmentTest();

    public FragmentHomeScreen(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        init();
        changeFont();
        handelTestFragment();
        //handelOffersFragment();
        //readFromDataBase();
//        createRVSuggested();
//        createCategoryRV();
//        //createMeanL();
//        setFragment();
//        BraceletsFragment();
//        NecklacesFragment();
//        RingsFragment();
//        FootAnkletFragment();
//        EarringFragment();

        return view;
    }

    private void handelTestFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_test, fragmentTest)
                .commit();
    }

    private void handelOffersFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_offers, fragmentOffers)
                .commit();
    }

    private void EarringFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_earring, fragmentEarring)
                .commit();
    }

    private void FootAnkletFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_foot_anklet, fragmentFootAnklet)
                .commit();
    }

    private void RingsFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_rings, fragmentRings)
                .commit();
    }

    private void NecklacesFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_necklaces, fragmentNecklaces)
                .commit();
    }

    private void BraceletsFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_bracelets, fragmentBracelets)
                .commit();
    }

    private void setFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_set, fragmentSet)
                .commit();
    }

//    private void createMeanL() {
//        meanList =fillMeanArrayL(meanList,getActivity());
//        meanRV.setHasFixedSize(true);
//        meanRV.setNestedScrollingEnabled(false);
//        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
//        meanRV.setLayoutManager(mLayoutManager);
//        adapterMainList = new AdapterMainList(getActivity(), meanList);
//        meanRV.setAdapter(adapterMainList);
//    }

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
//        meanRV = (RecyclerView) view.findViewById(R.id.fragment_home_mean_RV);
        suggestedRV = (RecyclerView) view.findViewById(R.id.fragment_home_suggested_RV);
        categoryRV = (RecyclerView) view.findViewById(R.id.fragment_home_category_RV);
        suggestedTV = view.findViewById(R.id.fragment_haom_suggested_TV);
    }

//    private void readFromDataBase() {
//        mList = getOffers(mList);
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//
//
//            }
//        }, 1700);
//
//    }

    private void createRVSuggested() {
        dealsArrayList = fillOffersArrayL(dealsArrayList,getActivity());


        suggestedRV.setNestedScrollingEnabled(false);
        suggestedRV.setHasFixedSize(true);
        layoutManagerSuggested = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        suggestedRV.setLayoutManager(layoutManagerSuggested);
        adapterSuggestedToYou =new AdapterSuggestedToYou(getActivity()
                ,dealsArrayList);
        suggestedRV.setAdapter(adapterSuggestedToYou);
    }

    private void changeFont() {
        suggestedTV.setTypeface(Functions.changeFontGeneral(getActivity()));
    }
}
