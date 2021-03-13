package com.fashion.rest.functions;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.fashion.rest.apiURL.API.apiAreas;
import static com.fashion.rest.apiURL.API.apiBrand;
import static com.fashion.rest.apiURL.API.apiCat;
import static com.fashion.rest.apiURL.API.apiCities;
import static com.fashion.rest.apiURL.API.apiCountries;
import static com.fashion.rest.apiURL.API.apiHome;
import static com.fashion.rest.apiURL.API.apiItems;
import static com.fashion.rest.apiURL.API.apiItemsWithAllFilter;
import static com.fashion.rest.apiURL.API.apiNumberOfCountries;
import static com.fashion.rest.apiURL.API.apiOffers;
import static com.fashion.rest.apiURL.API.apiStoreItems;
import static com.fashion.rest.apiURL.API.getSingleItem;

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

    public static Retrofit getCategories() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiCat())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getHome() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiHome())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getItems(String subCategoryID,String category_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiItems(subCategoryID,category_id))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getItemsWithAllFilter(String subCategoryID,String category_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiItemsWithAllFilter(subCategoryID,category_id))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getStoreItems(String storeID,String store_area) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiStoreItems(storeID,store_area))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getOffers(String date) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiOffers(date))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getBrand() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiBrand())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getSingleItemDetails(String ItemID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getSingleItem(ItemID))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
