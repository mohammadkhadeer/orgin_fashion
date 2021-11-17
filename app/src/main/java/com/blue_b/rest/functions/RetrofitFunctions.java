package com.blue_b.rest.functions;

import com.blue_b.rest.model.FilterItemsModel;
import com.blue_b.rest.model.FilterOffersModel;
import com.blue_b.rest.apiURL.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFunctions {

    public static Retrofit getNumberOfCountries() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.apiNumberOfCountries())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getCountries() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.apiCountries())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getCitiesApi(String countryId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.apiCities(countryId))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getAreasApi(String countryId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.apiAreas(countryId))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getCategories() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.apiCat())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getHome() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.apiHome())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getItemHome(String subCategoryID,String category_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.apiItemsHome(subCategoryID,category_id))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getItems(FilterItemsModel filterItemsModel) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.apiItemsWithAllFilter(filterItemsModel))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getStoreItems(String storeID,String store_area) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.apiStoreItems(storeID,store_area))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getOffersWithAllFilter(FilterOffersModel filterOffersModel) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.apiOffersWithFilter(filterOffersModel))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getBrand() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.apiBrand())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getBrandSubCat(String brandID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.apiBrandSubCategory(brandID))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getSingleItemDetails(String ItemID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.getSingleItem(ItemID))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit insertUserInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.apiRegister())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit getNumberOfItemsResults(FilterItemsModel filterItemsModel) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.apiNumberOfItems(filterItemsModel))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
