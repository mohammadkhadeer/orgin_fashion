package com.fashion.rest.view.fragments.fragmentHomeMainScreen;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Area;
import com.fashion.rest.model.City;
import com.fashion.rest.model.MultiArea;
import com.fashion.rest.presnter.PassCityAndArea;
import com.fashion.rest.view.Adapters.AdapterAreas;
import com.fashion.rest.view.Adapters.AdapterCities;
import com.fashion.rest.view.Adapters.AdapterSelectedAreas;
import com.fashion.rest.view.activity.mainScreem.MainActivity;

import java.util.ArrayList;

import static com.fashion.rest.functions.FillItem.fillAreas;
import static com.fashion.rest.functions.FillItem.fillCityArrayL;


public class FragmentFilter extends Fragment implements AdapterCities.PassCity
        , AdapterAreas.PassArea , AdapterSelectedAreas.CancelArea  {

    public ArrayList<City> citesArrayList = new ArrayList<>();
    AdapterCities adapterCities;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView cityRV;

    public ArrayList<Area> areaArrayList = new ArrayList<>();
    RecyclerView all_areasRV;
    AdapterAreas adapterAreas;
    RecyclerView.LayoutManager layoutManagerArea;

    public ArrayList<Area> selectedAreaArrayList = new ArrayList<>();
    RecyclerView selected_areasRV;
    AdapterSelectedAreas adapterSelectedAreas;
    RecyclerView.LayoutManager layoutManagerSelectedArea;

    public FragmentFilter() {
    }

    View view;
    RelativeLayout showResult,cancel_selected_city_result,cancel_activity_result,see_all_areas_result;
    LinearLayout city_name_con_result;
    TextView city_name_result,title_activity_result,see_all_areas_result_tv;

    PassCityAndArea passCity;

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
//            phoneNumber = getArguments().getString("phoneN");
        }
        super.onAttach(context);
        if (context instanceof PassCityAndArea) {
            passCity = (PassCityAndArea) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        passCity = null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all_filter, container, false);
        inti();
        changeFont();
        createCitiesRV();
        actionListener();

        return view;
    }

    private void createCitiesRV() {
        citesArrayList = fillCityArrayL(getActivity());
        cityRV.setNestedScrollingEnabled(false);
        cityRV.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        cityRV.setLayoutManager(layoutManager);
        adapterCities =new AdapterCities(getActivity()
                ,citesArrayList,this);
        cityRV.setAdapter(adapterCities);
    }

    private void changeFont() {
        city_name_result.setTypeface(Functions.changeFontGeneral(getActivity()));
        title_activity_result.setTypeface(Functions.changeFontGeneral(getActivity()));
        see_all_areas_result_tv.setTypeface(Functions.changeFontGeneral(getActivity()));
//        see_all_areas_result_tv.setPaintFlags(see_all_areas_result_tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void actionListener() {
        actionListenerToShowResult();
        actionListenerToCancelCityButton();
        actionListenerToCancelActivityButton();
        actionListenerToSeeAllAreas();
    }

    private void actionListenerToSeeAllAreas() {
        see_all_areas_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopUp popUp = new PopUp();
                popUp.showDialog(getActivity(), areaArrayList,getActivity());

            }
        });
    }



        private void actionListenerToCancelActivityButton() {
        cancel_activity_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when user cancel a filter fragment i have to back every thing to defult
                cityRV.setVisibility(View.VISIBLE);
                city_name_con_result.setVisibility(View.GONE);
                all_areasRV.setVisibility(View.GONE);
                selectedAreaArrayList = new ArrayList<>();
                selected_areasRV.setVisibility(View.GONE);

                ((MainActivity) getActivity()).removeFilterFragment();
                getActivity().getFragmentManager().popBackStack();
            }
        });
    }

    private void actionListenerToShowResult() {
        showResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).removeFilterFragmentAndShowResult();
                getActivity().getFragmentManager().popBackStack();
            }
        });
    }

    private void actionListenerToCancelCityButton() {
        cancel_selected_city_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityRV.setVisibility(View.VISIBLE);
                city_name_con_result.setVisibility(View.GONE);
                all_areasRV.setVisibility(View.GONE);
                selectedAreaArrayList = new ArrayList<>();
                selected_areasRV.setVisibility(View.GONE);
            }
        });
    }

    private void inti() {
        showResult = (RelativeLayout) view.findViewById(R.id.contact_show_result);
        city_name_con_result = (LinearLayout) view.findViewById(R.id.city_name_con_result);
        cancel_selected_city_result = (RelativeLayout) view.findViewById(R.id.cancel_selected_city_result);
        city_name_result = (TextView) view.findViewById(R.id.city_name_result);
        cityRV = (RecyclerView) view.findViewById(R.id.cityRV);
        all_areasRV = (RecyclerView) view.findViewById(R.id.all_areasRV);
        selected_areasRV = (RecyclerView) view.findViewById(R.id.selected_areasRV);


        see_all_areas_result_tv = (TextView) view.findViewById(R.id.see_all_areas_result_tv);
        title_activity_result = (TextView) view.findViewById(R.id.title_activity_result);
        cancel_activity_result = (RelativeLayout) view.findViewById(R.id.cancel_activity_result);
        see_all_areas_result = (RelativeLayout) view.findViewById(R.id.see_all_areas_result);

    }

    @Override
    public void onClicked(City city) {
        //when user select city must to gone cities list and show to him as a relative layout
        //after that create all areas in this city in "all areas recycler view" under a city relative layout
        cityRV.setVisibility(View.GONE);
        city_name_result.setText(city.getName_en());
        city_name_con_result.setVisibility(View.VISIBLE);
        createAllAreasRV();
    }

    private void createAllAreasRV() {
        all_areasRV.setVisibility(View.VISIBLE);
        areaArrayList = fillAreas(getActivity());
        all_areasRV.setNestedScrollingEnabled(false);
        all_areasRV.setHasFixedSize(true);
        layoutManagerArea = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        all_areasRV.setLayoutManager(layoutManagerArea);
        adapterAreas =new AdapterAreas(getActivity()
                ,areaArrayList,this);
        all_areasRV.setAdapter(adapterAreas);
    }

    @Override
    public void onClicked(Area area) {
        //when user start choose areas first thing will check if this first choose if yes create selected areas recycler view
        //else will start add a new area to selected areas list and updated on adapter
        //also i have to remove a selected area from all area list and adapter
        if (selectedAreaArrayList.size() ==0)
        {
            createSelectedAreaRV(area);
        }else{
            updateSelectedArea(area);
        }
        removeSelectedAreaFromAreaArrayListAndAreaAdapter(area);
    }

    private void removeSelectedAreaFromAreaArrayListAndAreaAdapter(Area area) {
        removeAreaFromAllArrayList(area);
        adapterAreas.removeArea(area);
        adapterAreas.notifyDataSetChanged();

    }

    private void removeAreaFromAllArrayList(Area area) {
        for (int i=0;i<areaArrayList.size();i++)
        {
            if (area.getName_en().equals(areaArrayList.get(i).getName_en()))
            {
                areaArrayList.remove(i);
                break;
            }
        }
    }

    private void updateSelectedArea(Area area) {
        selectedAreaArrayList.add(0, area);
        adapterSelectedAreas.notifyDataSetChanged();
    }

    private void createSelectedAreaRV(Area area) {
        selected_areasRV.setVisibility(View.VISIBLE);
        selectedAreaArrayList.add(area);
        selected_areasRV.setNestedScrollingEnabled(false);
        selected_areasRV.setHasFixedSize(true);
        layoutManagerSelectedArea = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        selected_areasRV.setLayoutManager(layoutManagerSelectedArea);
        adapterSelectedAreas =new AdapterSelectedAreas(getActivity()
                ,selectedAreaArrayList,this);
        selected_areasRV.setAdapter(adapterSelectedAreas);
    }

    @Override
    public void onClickedCancel(Area area) {
        //When user start to cancel area from selected areas i have to check if this is last area in selected areas list if yes i have to remove last position
        // in selected areas list and make a selected areas recycler view gone
        //else i have to detect where is a area user want to remove it and deleted from selected area list and and update selected areas list adapter
        //after that i have to add removed area to all areas list and update all areas adapter
        if (selectedAreaArrayList.size()-1 ==0)
        {
            selectedAreaArrayList.remove(0);
            adapterSelectedAreas.notifyDataSetChanged();
            selected_areasRV.setVisibility(View.GONE);
        }else{
            removeItemFromArrayList(area);
            adapterSelectedAreas.removeArea(area);
            adapterSelectedAreas.notifyDataSetChanged();
        }
        //add removed area to all areas arrayList
        areaArrayList.add(0, area);
        adapterAreas.notifyDataSetChanged();
    }

    private void removeItemFromArrayList(Area area) {
        for (int i=0;i<selectedAreaArrayList.size();i++)
        {
            if (area.getName_en().equals(selectedAreaArrayList.get(i).getName_en()))
            {
                selectedAreaArrayList.remove(i);
                break;
            }
        }
    }


    public void passSelected(ArrayList<MultiArea> multiAreasArrayList) {
        //when user press select button from Pop up
        //case 1 no selected areas add selected areas to selected areas arrayList and
        // create new selected area recycler view and delete selected areas from all areas arrayList
        //case 2 i have selected areas before add a new areas to selected areas arrayList and update selected areas adapter
        // and remove a new selected areas from allAreas arrayList and update allAreas adapter
        if (selectedAreaArrayList.size() ==0)
        {
            createSelectedAreaCase2RV(multiAreasArrayList);
        }else{
            if (multiAreasArrayList.size() != 0)
            {
                for (int i=0;i<multiAreasArrayList.size();i++)
                {
                    Area area = new Area(multiAreasArrayList.get(i).getArea_en(),multiAreasArrayList.get(i).getArea_local());
                    selectedAreaArrayList.add(i,area);
                }
                adapterSelectedAreas.notifyDataSetChanged();
            }
        }
        removeSelectedAreaFromAreaArrayListAndAreaAdapterCase2(multiAreasArrayList);
    }

    private void removeSelectedAreaFromAreaArrayListAndAreaAdapterCase2(ArrayList<MultiArea> multiAreasArrayList) {
        if (multiAreasArrayList.size() != 0)
        {
            for (int i=0;i<multiAreasArrayList.size();i++)
            {
                Area area = new Area(multiAreasArrayList.get(i).getArea_en(),multiAreasArrayList.get(i).getArea_local());
                removeAreaFromAllArrayList(area);
                adapterAreas.removeArea(area);
                adapterAreas.notifyDataSetChanged();
            }
        }
    }

    private void createSelectedAreaCase2RV(ArrayList<MultiArea> multiAreasArrayList) {
        if (multiAreasArrayList.size() != 0)
        {
            for (int i=0;i<multiAreasArrayList.size();i++)
            {
                Area area = new Area(multiAreasArrayList.get(i).getArea_en(),multiAreasArrayList.get(i).getArea_local());
                selectedAreaArrayList.add(i,area);
            }

            //here
            selected_areasRV.setVisibility(View.VISIBLE);
            selected_areasRV.setNestedScrollingEnabled(false);
            selected_areasRV.setHasFixedSize(true);
            layoutManagerSelectedArea = new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL, false);

            selected_areasRV.setLayoutManager(layoutManagerSelectedArea);
            adapterSelectedAreas =new AdapterSelectedAreas(getActivity()
                    ,selectedAreaArrayList,this);
            selected_areasRV.setAdapter(adapterSelectedAreas);

        }
    }
}
