package com.fashion.rest.functions;

import com.fashion.rest.model.FilterItemsModel;
import com.fashion.rest.model.FilterOffersModel;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.fashion.rest.apiURL.API.apiAreas;
import static com.fashion.rest.apiURL.API.apiBrand;
import static com.fashion.rest.apiURL.API.apiCat;
import static com.fashion.rest.apiURL.API.apiCities;
import static com.fashion.rest.apiURL.API.apiCountries;
import static com.fashion.rest.apiURL.API.apiHome;
import static com.fashion.rest.apiURL.API.apiItemsHome;
import static com.fashion.rest.apiURL.API.apiItemsWithAllFilter;
import static com.fashion.rest.apiURL.API.apiNumberOfCountries;
import static com.fashion.rest.apiURL.API.apiOffersWithFilter;
import static com.fashion.rest.apiURL.API.apiRegister;
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

    public static Retrofit getItemHome(String subCategoryID,String category_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiItemsHome(subCategoryID,category_id))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getItems(FilterItemsModel filterItemsModel) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiItemsWithAllFilter(filterItemsModel))
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

    public static Retrofit getOffersWithAllFilter(FilterOffersModel filterOffersModel) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiOffersWithFilter(filterOffersModel))
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

    public static Retrofit insertUserInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiRegister())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
