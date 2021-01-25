package com.fashion.rest.apiURL;

public class API {
    public static String apiName ;

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

}
