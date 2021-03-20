package com.fashion.rest.view.fragments.allOffersFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Area;
import com.fashion.rest.model.City;
import com.fashion.rest.model.FilterOffersModel;
import com.fashion.rest.model.MultiArea;
import com.fashion.rest.presnter.Filter;
import com.fashion.rest.presnter.PassFilterOffersModel;
import com.fashion.rest.view.Adapters.AdapterAreas;
import com.fashion.rest.view.Adapters.AdapterCities;
import com.fashion.rest.view.Adapters.AdapterSelectedAreas;
import com.fashion.rest.view.fragments.FragmentProfileMenu;
import com.fashion.rest.view.fragments.ProfileDetailsInfo;
import com.fashion.rest.view.fragments.fragmentHomeMainScreen.PopUp;

import java.util.ArrayList;

import static com.fashion.rest.functions.FillItem.fillAreas;
import static com.fashion.rest.functions.FillItem.fillCityArrayL;
import static com.fashion.rest.functions.Functions.getDefultToFilterModel;
import static com.fashion.rest.functions.Functions.getTextEngOrLocal;

public class FilterOffers extends Fragment  implements AdapterCities.PassCity, AdapterAreas.PassArea
        , AdapterSelectedAreas.CancelArea{

    public FilterOffers(){}

    RecyclerView cities_rv;
    public ArrayList<City> citesArrayList = new ArrayList<>();
    AdapterCities adapterCities;
    RecyclerView.LayoutManager layoutManager;

    public ArrayList<Area> areaArrayList = new ArrayList<>();
    RecyclerView area_rv_all_offers_filter;
    AdapterAreas adapterAreas;
    RecyclerView.LayoutManager layoutManagerArea;

    public ArrayList<Area> selectedAreaArrayList = new ArrayList<>();
    RecyclerView selected_areasRV_all_offers_filter;
    AdapterSelectedAreas adapterSelectedAreas;
    RecyclerView.LayoutManager layoutManagerSelectedArea;

    LinearLayout city_name_con_result_all_offers_filter;
    TextView city_name_result_all_offers_filter;
    RelativeLayout cancel_city_all_offers_filter;

    RelativeLayout see_all_areas_all_offers_filter;

    FilterOffersModel filterOffersModel;
    PassFilterOffersModel passFilterOffersModel;

    EditText editText_from,editText_to;

    View view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PassFilterOffersModel) {
            passFilterOffersModel = (PassFilterOffersModel) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        passFilterOffersModel = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all_offers, container, false);
        inti();
        changeFont();
        createCitiesRV();
        actionListeners();
        filterOffersModel = getDefultToFilterModel(selectedAreaArrayList);

        return view;
    }

    private void actionListenerToCancelCity() {
        //remove all selected areas from selected areas rv
        // clear arrayList selected areas
        // pass a new value filter to perant screen
        //1. gone city_name_con_result_all_offers_filter
        //2. re visible city_rv
        //
        cancel_city_all_offers_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedAreaArrayList.size()!=0)
                {
                    adapterSelectedAreas.removeAllArea();
                    selectedAreaArrayList = new ArrayList<>();
                }

                filterOffersModel.setCity(null);
                filterOffersModel.setAreasList(selectedAreaArrayList);
                passFilterOffersModel.PassFilterOffersModel(filterOffersModel);

                city_name_con_result_all_offers_filter.setVisibility(View.GONE);
                cities_rv.setVisibility(View.VISIBLE);
            }
        });
    }

    public void passSelected(ArrayList<MultiArea> multiAreasArrayList) {
        //when user press select button from Pop up
        //case 1 no selected areas add selected areas to selected areas arrayList and
        // create new selected area recycler view and delete selected areas from all areas arrayList
        //case 2 i have selected areas before add a new areas to selected areas arrayList and update selected areas adapter
        // and remove a new selected areas from allAreas arrayList and update allAreas adapter
        if (selectedAreaArrayList.size() == 0) {
            createSelectedAreaCase2RV(multiAreasArrayList);
        } else {
            if (multiAreasArrayList.size() != 0) {
                for (int i = 0; i < multiAreasArrayList.size(); i++) {
                    Area area = new Area(multiAreasArrayList.get(i).getArea_en(), multiAreasArrayList.get(i).getArea_local(),multiAreasArrayList.get(i).getId());
                    selectedAreaArrayList.add(i, area);
                }
                adapterSelectedAreas.notifyDataSetChanged();
            }
        }
        removeSelectedAreaFromAreaArrayListAndAreaAdapterCase2(multiAreasArrayList);
        //pass selected area
        filterOffersModel.setAreasList(selectedAreaArrayList);
        passFilterOffersModel.PassFilterOffersModel(filterOffersModel);
    }

    private void removeSelectedAreaFromAreaArrayListAndAreaAdapterCase2(ArrayList<MultiArea> multiAreasArrayList) {
        if (multiAreasArrayList.size() != 0) {
            for (int i = 0; i < multiAreasArrayList.size(); i++) {
                Area area = new Area(multiAreasArrayList.get(i).getArea_en(), multiAreasArrayList.get(i).getArea_local(), multiAreasArrayList.get(i).getId());
                removeAreaFromAllArrayList(area);
                adapterAreas.removeArea(area);
                adapterAreas.notifyDataSetChanged();
            }
        }
    }

    private void createSelectedAreaCase2RV(ArrayList<MultiArea> multiAreasArrayList) {
        if (multiAreasArrayList.size() != 0) {
            for (int i = 0; i < multiAreasArrayList.size(); i++) {
                Area area = new Area(multiAreasArrayList.get(i).getArea_en(), multiAreasArrayList.get(i).getArea_local(),multiAreasArrayList.get(i).getId());
                selectedAreaArrayList.add(i, area);
            }

            //here
            selected_areasRV_all_offers_filter.setVisibility(View.VISIBLE);
            selected_areasRV_all_offers_filter.setNestedScrollingEnabled(false);
            selected_areasRV_all_offers_filter.setHasFixedSize(true);
            layoutManagerSelectedArea = new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL, false);

            selected_areasRV_all_offers_filter.setLayoutManager(layoutManagerSelectedArea);
            adapterSelectedAreas = new AdapterSelectedAreas(getActivity()
                    , selectedAreaArrayList, this,"all_filter");
            selected_areasRV_all_offers_filter.setAdapter(adapterSelectedAreas);

        }
    }

    private void changeFont() {
        //city_name_result_all_offers_filter.setTypeface(Functions.changeFontGeneral(getActivity()));
        city_name_result_all_offers_filter.setTypeface(Functions.changeFontBold(getActivity()));
    }

    private void actionListeners() {
        actionListenerToCancelCity();
        actionListenerToSeeAllAreas();
        actionListenerToDoneEditTextFrom();
        actionListenerToDoneEditTextTo();
    }

    private void actionListenerToDoneEditTextTo() {
        editText_to.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!editText_to.getText().toString().isEmpty())
                    {
                        filterOffersModel.setTo(Integer.parseInt(editText_to.getText().toString()));
                        passFilterOffersModel.PassFilterOffersModel(filterOffersModel);
                    }
                    else
                    {
                        Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.price_man), Toast.LENGTH_SHORT).show();
                    }

                    return true;
                }
                return false;
            }
        });
    }

    private void actionListenerToDoneEditTextFrom() {
        editText_from.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!editText_from.getText().toString().isEmpty())
                    {
                        filterOffersModel.setFrom(Integer.parseInt(editText_from.getText().toString()));
                        passFilterOffersModel.PassFilterOffersModel(filterOffersModel);
                    }
                    else{
                        Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.price_min), Toast.LENGTH_SHORT).show();
                    }

                    return true;
                }
                return false;
            }
        });
    }

    private void actionListenerToSeeAllAreas() {
        see_all_areas_all_offers_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUp popUp = new PopUp();
                popUp.showDialog(getActivity(), areaArrayList, getActivity());
            }
        });
    }

    private void createCitiesRV() {
        citesArrayList = fillCityArrayL(getActivity());
        cities_rv.setNestedScrollingEnabled(false);
        cities_rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        cities_rv.setLayoutManager(layoutManager);
        adapterCities = new AdapterCities(getActivity()
                , citesArrayList, this,"all_filter");
        cities_rv.setAdapter(adapterCities);
    }

    private void inti() {
        cities_rv = (RecyclerView) view.findViewById(R.id.cities_rv);
        area_rv_all_offers_filter=(RecyclerView) view.findViewById(R.id.area_rv_all_offers_filter);
        selected_areasRV_all_offers_filter=(RecyclerView) view.findViewById(R.id.selected_areasRV_all_offers_filter);
        city_name_con_result_all_offers_filter = (LinearLayout) view.findViewById(R.id.city_name_con_result_all_offers_filter);
        city_name_result_all_offers_filter = (TextView) view.findViewById(R.id.city_name_result_all_offers_filter);
        cancel_city_all_offers_filter = (RelativeLayout) view.findViewById(R.id.cancel_city_all_offers_filter);
        see_all_areas_all_offers_filter = (RelativeLayout) view.findViewById(R.id.see_all_areas_all_offers_filter);
        editText_from = (EditText) view.findViewById(R.id.price_from);
        editText_to = (EditText) view.findViewById(R.id.price_to);
    }

    @Override
    public void onClicked(City city) {
        //1. made a city rv gone
        //2. made all city_name_con_result_all_offers_filter visible
        //3. set a city name in-said city_name_tv show
        //4. fill area_rv
        //5. update filter model
        //
        filterOffersModel.setCity(city);
        passFilterOffersModel.PassFilterOffersModel(filterOffersModel);

        cities_rv.setVisibility(View.GONE);
        city_name_con_result_all_offers_filter.setVisibility(View.VISIBLE);
        city_name_result_all_offers_filter.setText(getTextEngOrLocal(getActivity(),city.getName_en(),city.getName_local()));
        createAllAreasRV(city);
    }

    private void createAllAreasRV(City city) {
        area_rv_all_offers_filter.setVisibility(View.VISIBLE);
        areaArrayList = fillAreas(getActivity(),city.getName_en());
        area_rv_all_offers_filter.setNestedScrollingEnabled(false);
        area_rv_all_offers_filter.setHasFixedSize(true);
        layoutManagerArea = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        area_rv_all_offers_filter.setLayoutManager(layoutManagerArea);
        adapterAreas = new AdapterAreas(getActivity()
                , areaArrayList, this,"all_filter");
        area_rv_all_offers_filter.setAdapter(adapterAreas);
    }

    @Override
    public void onClicked(Area area) {
//        Log.i("TAG","area: "+area.getName_local());
        //when user start choose areas first thing will check if this is a first choose if yes create selected areas recycler view
        //else will start add a new area to selected areas list and updated on adapter
        //also i have to remove a selected area from all area list and adapter
        if (selectedAreaArrayList.size() == 0) {
            createSelectedAreaRV(area);
        } else {
            updateSelectedArea(area);
        }
        removeSelectedAreaFromAreaArrayListAndAreaAdapter(area);
    }

    private void createSelectedAreaRV(Area area) {
        selected_areasRV_all_offers_filter.setVisibility(View.VISIBLE);
        selectedAreaArrayList.add(area);
        selected_areasRV_all_offers_filter.setNestedScrollingEnabled(false);
        selected_areasRV_all_offers_filter.setHasFixedSize(true);
        layoutManagerSelectedArea = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        selected_areasRV_all_offers_filter.setLayoutManager(layoutManagerSelectedArea);
        adapterSelectedAreas = new AdapterSelectedAreas(getActivity()
                , selectedAreaArrayList, this,"all_filter");
        selected_areasRV_all_offers_filter.setAdapter(adapterSelectedAreas);
        //pass selected area
        filterOffersModel.setAreasList(selectedAreaArrayList);
        //
        passFilterOffersModel.PassFilterOffersModel(filterOffersModel);
    }

    private void updateSelectedArea(Area area) {
        selectedAreaArrayList.add(0, area);
        adapterSelectedAreas.notifyDataSetChanged();

        //pass selected area //
        filterOffersModel.setAreasList(selectedAreaArrayList);
        passFilterOffersModel.PassFilterOffersModel(filterOffersModel);
    }

    private void removeSelectedAreaFromAreaArrayListAndAreaAdapter(Area area) {
        removeAreaFromAllArrayList(area);
        adapterAreas.removeArea(area);
        adapterAreas.notifyDataSetChanged();

    }

    private void removeAreaFromAllArrayList(Area area) {
        for (int i = 0; i < areaArrayList.size(); i++) {
            if (area.getName_en().equals(areaArrayList.get(i).getName_en())) {
                areaArrayList.remove(i);
                break;
            }
        }
    }

    @Override
    public void onClickedCancel(Area area) {
        //When user start to cancel area from selected areas i have to check if this is last area in selected areas list if yes i have to remove last position
        // in selected areas list and make a selected areas recycler view gone
        //else i have to detect where is a area user want to remove it and deleted from selected area list and and update selected areas list adapter
        //after that i have to add removed area to all areas list and update all areas adapter
        if (selectedAreaArrayList.size() - 1 == 0) {
            selectedAreaArrayList.remove(0);
            adapterSelectedAreas.notifyDataSetChanged();
            selected_areasRV_all_offers_filter.setVisibility(View.GONE);
        } else {
            removeItemFromArrayList(area);
            adapterSelectedAreas.removeArea(area);
            adapterSelectedAreas.notifyDataSetChanged();
        }
        //add removed area to all areas arrayList
        areaArrayList.add(0, area);
        adapterAreas.notifyDataSetChanged();
        //pass selected area
        filterOffersModel.setAreasList(selectedAreaArrayList);
        passFilterOffersModel.PassFilterOffersModel(filterOffersModel);
    }

    private void removeItemFromArrayList(Area area) {
        for (int i = 0; i < selectedAreaArrayList.size(); i++) {
            if (area.getName_en().equals(selectedAreaArrayList.get(i).getName_en())) {
                selectedAreaArrayList.remove(i);
                break;
            }
        }
    }
}
