package com.fashion.rest.apiURL;

import android.util.Log;

import com.fashion.rest.model.FilterOffersModel;

public class API {
    public static String apiName ;

    public static String apiURLBase(){
        String apiName ="http://46.101.235.217/api/";
        return apiName ;
    }

    public static String apiBase(){
        apiName ="http://46.101.235.217/api/areas/by-country/600814ac731d75ae35f558b3/";
        //apiName ="http://46.101.235.217/api/items/";
        //apiName ="https://jsonplaceholder.typicode.com/";
        return apiName ;
    }

    public static String api1(int x){
        apiName =apiBase();
        //return apiName+"posts"+"/"+x+"/"+"comments" ;
        return apiName+"items"+"/" ;
    }

    public static String apiCites(int x){
        apiName =apiBase();
        return apiName+"posts"+"/"+x+"/"+"comments" ;
    }

    public static String apiNumberOfCountries(){
        String apiName ="http://46.101.235.217/api/countries/count/";
        return apiName;
    }

    public static String apiCountries(){
        String apiName ="http://46.101.235.217/api/countries/";
        return apiName;
    }


    public static String apiCities(String countryId){
        String apiName ="http://46.101.235.217/api/cities/by-country/"+countryId+"/";
        return apiName;
    }

    public static String apiAreas(String countryId){
        String apiName ="http://46.101.235.217/api/areas/by-country/"+countryId+"/";
        return apiName;
    }

    public static String apiCat(){
        String apiName ="";
        apiName =apiURLBase() + "/categories/";
//        Log.i("TAG Cat",apiName);
        return apiName;
    }

    public static String apiHome(){
        String apiName ="http://46.101.235.217/api/homes/";
        return apiName;
    }

    public static String apiItemsHome(String subCategoryID,String category_id){
        String apiName = "http://46.101.235.217/api/items/by-filter/%7B%22from%22%20:%205,%22to%22:55555,%22sub_category%22:%22"+subCategoryID+"%22,%22store%22:null,%22category%22:%22"+category_id+"%22,%22inStock%22:true,%22area%22:null%7D/";
//        Log.i("TAG",apiName);
        return apiName;
    }

    public static String apiItems(String subCategoryID,String category_id){
        //6020da4212f197d167672163    "+subCategoryID+" 6020da0412f197d167672161
        //"http://46.101.235.217/api/items/by-filter/%7B%22from%22%20:%2020,%22to%22:55555,%22sub_category%22:%226020da4212f197d167672163%22,%22category%22:%226020da0412f197d167672161%22,%22area%22:[%2260082119e4afcdaf7e1cb8ad%22]%7D/"
        String apiName ="http://46.101.235.217/api/items/by-filter/%7B%22from%22%20:%2020,%22to%22:55555,%22sub_category%22:%22"+subCategoryID+"%22,%22category%22:%22"+category_id+"%22,%22area%22:[%2260082119e4afcdaf7e1cb8ad%22]%7D/";
//        Log.i("TAG",apiName);
        return apiName;
    }

    public static String apiItemsWithAllFilter(String subCategoryID,String category_id){
        String apiName ="http://46.101.235.217/api/items/by-filter/%7B%22from%22%"+20+":%2020,%22to%22:"+55555555+",%22sub_category%22:%22"+subCategoryID+"%22,%22category%22:%22"+category_id+"%22,%22area%22:[%2260082119e4afcdaf7e1cb8ad%22]%7D/";
//        Log.i("TAG: API",apiName);
        return apiName;
    }

    public static String apiStoreItems(String store_id,String store_area){
        String apiName ="http://46.101.235.217/api/items/by-filter/%7B%22from%22%20:%2020,%22to%22:55555,%22sub_category%22:%226020da4212f197d167672163%22,%22store%22:%22"+store_id+"%22,%22category%22:%226020da0412f197d167672161%22,%22area%22:[%22"+store_area+"%22]%7D/";
        String apiName2 = "http://46.101.235.217/api/items/by-filter/%7B%22from%22%20:%2020,%22to%22:55555,%22store%22:%22"+store_id+"%22,%22area%22:[%22"+store_area+"%22]%7D/";

//        Log.i("TAG: API",apiName2);
        return apiName2;
    }

    public static String apiOffers(String date){
        String apiName ="http://46.101.235.217/api/offers/by-date/"+date+"/";
//        Log.i("TAG offer",apiName);
        return apiName;
    }

    public static String apiOffersWithFilter(FilterOffersModel filterOffersModel){
        String apiName ="http://46.101.235.217/api/offers/by-filter/%7B%22from%22%20:%20"+20+",%22to%22:"+9999+",%22inStock%22:%22true%22,%20%22city%22:null,%22area%22:null%7D/";
        Log.i("TAG offer",apiName);
        return apiName;
    }

    public static String apiBrand(){
        String apiName ="http://46.101.235.217/api/brands/";
//        Log.i("TAG",apiName);
        return apiName;
    }

    public static String getSingleItem(String itemID){
        String apiName ="http://46.101.235.217/api/items/"+itemID+"/";
//        Log.i("TAG",apiName);
        return apiName;
    }

}
