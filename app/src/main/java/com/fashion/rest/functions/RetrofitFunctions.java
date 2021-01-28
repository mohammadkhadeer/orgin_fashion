package com.fashion.rest.functions;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.fashion.rest.apiURL.API.apiAreas;
import static com.fashion.rest.apiURL.API.apiCities;
import static com.fashion.rest.apiURL.API.apiCountries;
import static com.fashion.rest.apiURL.API.apiNumberOfCountries;

public class RetrofitFunctions {

    public static Retrofit getNumberOfCountries() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiNumberOfCountries())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getCountries() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiCountries())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getCitiesApi(String countryId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiCities(countryId))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getAreasApi(String countryId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiAreas(countryId))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
