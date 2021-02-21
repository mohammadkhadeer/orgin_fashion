package com.fashion.rest.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.database.DBHelper;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Areas;
import com.fashion.rest.model.City;
import com.fashion.rest.model.Countries;
import com.fashion.rest.model.Deal;
import com.fashion.rest.model.Flag;
import com.fashion.rest.presnter.JsonPlaceHolderApi;
import com.fashion.rest.presnter.PassCityAndArea;
import com.fashion.rest.presnter.PassObject;
import com.fashion.rest.view.Adapters.AdapterCountries;
import com.fashion.rest.view.Adapters.AdapterSet;
import com.fashion.rest.view.Adapters.AdapterSubCategorySeeAll;
import com.fashion.rest.view.activity.CategoryItem;
import com.fashion.rest.view.activity.Login;
import com.fashion.rest.view.activity.SplashScreen;
import com.fashion.rest.view.activity.mainScreem.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.fashion.rest.apiURL.API.apiURLBase;
import static com.fashion.rest.functions.RetrofitFunctions.getAreasApi;
import static com.fashion.rest.functions.RetrofitFunctions.getCitiesApi;
import static com.fashion.rest.functions.RetrofitFunctions.getCountries;
import static com.fashion.rest.functions.RetrofitFunctions.getNumberOfCountries;
import static com.fashion.rest.sharedPreferences.Country.saveCountryInSP;
import static com.fashion.rest.sharedPreferences.Language.getLanguageFromSP;
import static com.fashion.rest.sharedPreferences.LoginInfo.getLoginOrNotFromSP;

public class FragmentSelectCountry extends Fragment implements AdapterCountries.PassCountry{
    View view;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterCountries adapterCountries;
    public ArrayList<Countries> countriesArrayList = new ArrayList<>();

    public FragmentSelectCountry(){}
    JsonPlaceHolderApi jsonPlaceHolderApiCountries,jsonPlaceHolderApiCities,jsonPlaceHolderApiAreas;
    Retrofit retrofitCountries,retrofitCities,retrofitAreas;

    DBHelper dbHelper;

    ProgressBar progressBar;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    String whereFrom;
    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            whereFrom = getArguments().getString("from");
        }
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_select_country, container, false);
        inti();
        intiRetrofitAndPlaceHolder();
        getCountriesList();
        return view;
    }

    private void getCountriesList() {
        jsonPlaceHolderApiCountries = retrofitCountries.create(JsonPlaceHolderApi.class);
        Call<List<Countries>> call = jsonPlaceHolderApiCountries.getCountries();
        call.enqueue(new Callback<List<Countries>>() {
            @Override
            public void onResponse(Call<List<Countries>> call, Response<List<Countries>> response) {
                if (!response.isSuccessful())
                {
                    return;
                }
                List<Countries> countriesL = response.body();

                for (Countries country:countriesL)
                {
                    Flag flag = new Flag(apiURLBase()+country.getFlag().getUrl());
                    countriesArrayList.add(new Countries(country.getName_en(),country.getName_local(),country.getId(),flag));
                }
                countriesRV();
            }
            @Override
            public void onFailure(Call<List<Countries>> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private void countriesRV() {
        //countriesArrayList
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        adapterCountries = new AdapterCountries(getActivity(), countriesArrayList,this);
        recyclerView.setAdapter(adapterCountries);
    }

    private void inti() {
        dbHelper = new DBHelper(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.countries_RV);
        progressBar = (ProgressBar) view.findViewById(R.id.countries_pb);
    }

    private void intiRetrofitAndPlaceHolder() {
        retrofitCountries = getCountries();

        jsonPlaceHolderApiCountries= retrofitCountries.create(JsonPlaceHolderApi.class);

    }

    @Override
    public void onClicked(Countries country) {
        progressBar.setVisibility(View.VISIBLE);
        //save country info in SP
        if (whereFrom.equals("splash"))
        {
            saveCountryInSP(getActivity(),sharedPreferences,editor,country.getName_en(),country.getName_local(),country.getId(),apiURLBase()+country.getFlag().getUrl());
            intiRetrofitAndPlaceHolder2(country);
            getCities();
        }else{
            dbHelper.deleteAllCities();
            dbHelper.deleteAllNeighborhood();
            saveCountryInSP(getActivity(),sharedPreferences,editor,country.getName_en(),country.getName_local(),country.getId(),apiURLBase()+country.getFlag().getUrl());
            intiRetrofitAndPlaceHolder2(country);
            getCities();
        }
    }

    private void intiRetrofitAndPlaceHolder2(Countries country) {
        retrofitCities = getCitiesApi(country.getId());
        jsonPlaceHolderApiCities = retrofitCities.create(JsonPlaceHolderApi.class);

        retrofitAreas = getAreasApi(country.getId());
        jsonPlaceHolderApiAreas = retrofitAreas.create(JsonPlaceHolderApi.class);
    }

    private void getCities() {
        Call<List<City>> call = jsonPlaceHolderApiCities.getCities();
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                if (!response.isSuccessful())
                { return; }
                List<City> citiesL = response.body();
                for (City city:citiesL)
                {
                    // save cities info in DP
                    dbHelper.insertCities(city.getIdServer(),city.getName_en(),city.getName_local());
                }
                getAreas();
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private void getAreas() {
        Call<List<Areas>> call = jsonPlaceHolderApiAreas.getAreas();
        call.enqueue(new Callback<List<Areas>>() {
            @Override
            public void onResponse(Call<List<Areas>> call, Response<List<Areas>> response) {
                if (!response.isSuccessful())
                { return; }
                List<Areas> areasL = response.body();
                for (Areas area:areasL)
                {
                    // save areas info in DP
                    dbHelper.insertNeighborhood(area.getIdServer(),area.getCity().getName_en(),area.getCity().getName_local(),area.getName_en(),area.getName_local());
                }
                timer();
            }

            @Override
            public void onFailure(Call<List<Areas>> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private void timer() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (getLanguageFromSP(getActivity()) == null || getLanguageFromSP(getActivity()).equals(""))
                {
                    Intent intent = new Intent(getActivity(), com.fashion.rest.view.activity.Language.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
                    getActivity().finish();
                }else{
                    if (getLoginOrNotFromSP(getActivity()) == null || getLoginOrNotFromSP(getActivity()).equals("") || !getLoginOrNotFromSP(getActivity()).equals("1"))
                    {
                        Intent intent = new Intent(getActivity(), Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
                        getActivity().finish();
                    }else {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
                        getActivity().finish();
                    }
                }
            }
        }, 1000);
    }

}
