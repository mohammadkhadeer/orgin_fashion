package com.fashion.rest.apiURL;

import android.util.Log;

import com.fashion.rest.model.FilterItemsModel;
import com.fashion.rest.model.FilterOffersModel;

public class API {
    public static String apiName ;

    public static String apiURLBase(){
        String apiName ="http://46.101.235.217/api/";
        return apiName ;
    }

    public static String apiRegister(){
        String apiName = "http://46.101.235.217/api/";
        return apiName;
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
        String apiName = "http://46.101.235.217/api/items/by-filter/%7B%22from%22%20:%20"+1+",%22to%22:"+999999+",%22sub_category%22:%22"+subCategoryID+"%22,%22store%22:null,%22category%22:%22"+category_id+"%22,%22inStock%22:true,%22area%22:null%7D/";
//        Log.i("TAG apiName1",apiName);
        return apiName;
    }

    public static String apiItemsWithAllFilter(FilterItemsModel filterItemsModel){
        String apiName = null;
        if (filterItemsModel.getStore() == null)
        {
            if (filterItemsModel.getAreasList().size() == 0)
            {
                if (filterItemsModel.getCity() == null)
                {
                    apiName ="http://46.101.235.217/api/items/by-filter/%7B%22from%22%20:%20"+filterItemsModel.getFrom()+",%22to%22:"+filterItemsModel.getTo()+",%22sub_category%22:%22"+filterItemsModel.getSub_cat().getId()+"%22,%22store%22:null,%22category%22:%22"+filterItemsModel.getSub_cat().getCategory_id()+"%22,%22inStock%22:true,%20%22city%22:null,%22area%22:null%7D/";
                }else{
                     apiName= "http://46.101.235.217/api/items/by-filter/%7B%22from%22%20:%20"+filterItemsModel.getFrom()+",%22to%22:"+filterItemsModel.getTo()+",%22sub_category%22:%22"+filterItemsModel.getSub_cat().getId()+"%22,%22store%22:null,%22category%22:%22"+filterItemsModel.getSub_cat().getCategory_id()+"%22,%22inStock%22:true,%22area%22:null,%22city%22:[%22"+filterItemsModel.getCity().getIdServer()+"%22]%7D/";
                }
            }else{
                String areas = "";
                int flag= 0;
                if (filterItemsModel.getAreasList().size() == 1)
                {
                    areas ="%22"+filterItemsModel.getAreasList().get(0).getId()+"%22";
                }else{
                    for (int i = 0;i<filterItemsModel.getAreasList().size();i++)
                    {
                        flag =1;
                        if (i ==0)
                        {
                            areas=filterItemsModel.getAreasList().get(0).getId();
                        }else{
                            areas =areas+","+filterItemsModel.getAreasList().get(i).getId();
                        }

                    }
                    if (flag ==1)
                    {
                        areas = "%22"+areas+"%22";
                    }
                }
                apiName = "http://46.101.235.217/api/items/by-filter/%7B%22from%22%20:%20"+filterItemsModel.getFrom()+",%22to%22:"+filterItemsModel.getTo()+",%22sub_category%22:%22"+filterItemsModel.getSub_cat().getId()+"%22,%22store%22:null,%22category%22:%22"+filterItemsModel.getSub_cat().getCategory_id()+"%22,%22inStock%22:true,%22%20area%22:["+areas+"],%22city%22:[%22"+filterItemsModel.getCity().getIdServer()+"%22]%7D/";
//                Log.i("TAG: API",apiName);
            }
        }else{

        }
//        Log.i("TAG: API",apiName);
        return apiName;
    }

    public static String apiStoreItems(String store_id,String store_area){
        String apiName ="http://46.101.235.217/api/items/by-filter/%7B%22from%22%20:%2020,%22to%22:55555,%22sub_category%22:%226020da4212f197d167672163%22,%22store%22:%22"+store_id+"%22,%22category%22:%226020da0412f197d167672161%22,%22area%22:[%22"+store_area+"%22]%7D/";
        String apiName2 = "http://46.101.235.217/api/items/by-filter/%7B%22from%22%20:%2020,%22to%22:55555,%22store%22:%22"+store_id+"%22,%22area%22:[%22"+store_area+"%22]%7D/";

//        Log.i("TAG: API",apiName2);
        return apiName2;
    }

    public static String apiOffersWithFilter(FilterOffersModel filterOffersModel){
        String apiName = null;
        if (filterOffersModel.getCity() == null)
        {
            apiName = "http://46.101.235.217/api/offers/by-filter/%7B%22date%22:%20%22"+filterOffersModel.getISO()+"%22,%22from%22%20:%20"+filterOffersModel.getFrom()+",%22to%22:"+filterOffersModel.getTo()+",%22inStock%22:%22true%22,%20%22city%22:null,%22area%22:null%7D/";
        }else{
            if (filterOffersModel.getAreasList().size() == 0)
            {
                apiName = "http://46.101.235.217/api/offers/by-filter/%7B%22date%22:%20%22"+filterOffersModel.getISO()+"%22,%22from%22%20:%20"+filterOffersModel.getFrom()+",%22to%22:"+filterOffersModel.getTo()+",%22inStock%22:%22true%22,%20%22city%22:[%22"+filterOffersModel.getCity().getIdServer()+"%22],%22area%22:null%7D/";
//                Log.i("TAG offer city: ",apiName);

            }else{
                String areas = "";
                int flag= 0;
                if (filterOffersModel.getAreasList().size() == 1)
                {
                    areas ="%22"+filterOffersModel.getAreasList().get(0).getId()+"%22";
                }else{
                    for (int i = 0;i<filterOffersModel.getAreasList().size();i++)
                    {
                        flag =1;
                        if (i ==0)
                        {
                            areas=filterOffersModel.getAreasList().get(0).getId();
                        }else{
                            areas =areas+"%22,%22"+filterOffersModel.getAreasList().get(i).getId();
                        }

                    }
                    if (flag ==1)
                    {
                        Log.i("TAG","Areas: "+areas);
                        areas = "%22"+areas+"%22";
                        Log.i("TAG","Areas after %22: "+areas);
                    }
                }
                apiName = "http://46.101.235.217/api/offers/by-filter/%7B%20%22date%22:%20%22"+filterOffersModel.getISO()+"%22,%22from%22%20:%20"+filterOffersModel.getFrom()+",%22to%22:"+filterOffersModel.getTo()+",%22inStock%22:%22true%22,%20%22city%22:[%22"+filterOffersModel.getCity().getIdServer()+"%22],%22area%22:["+areas+"]%7D/";
            }
        }
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
