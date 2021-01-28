package com.fashion.rest.apiURL;

public class API {
    public static String apiName ;

    public static String apiURLBase(){
        String apiName ="http://46.101.235.217/api";
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

}
