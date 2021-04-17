package com.orgin_fashion.rest.apiURL;

import com.orgin_fashion.rest.model.FilterItemsModel;
import com.orgin_fashion.rest.model.FilterOffersModel;

public class API {
    public static String apiURLBase(){
        String apiName ="http://46.101.235.217/api/";
        return apiName ;
    }

    public static String apiRegister(){
        String apiName = "http://46.101.235.217/api/";
        return apiName;
    }

    public static String apiNumberOfCountries(){
        String apiName =apiURLBase()+"countries/count/";
        return apiName;
    }

    public static String apiCountries(){
        String apiName =apiURLBase()+"countries/";
        return apiName;
    }


    public static String apiCities(String countryId){
        String apiName =apiURLBase()+"cities/by-country/"+countryId+"/";
        return apiName;
    }

    public static String apiAreas(String countryId){
        String apiName =apiURLBase()+"areas/by-country/"+countryId+"/";
        return apiName;
    }

    public static String apiCat(){
        String apiName ="";
        apiName =apiURLBase() + "/categories/";
//        Log.i("TAG Cat",apiName);
        return apiName;
    }

    public static String apiHome(){
        String apiName =apiURLBase()+"homes/";
        return apiName;
    }

    public static String apiItemsHome(String subCategoryID,String category_id){
        String apiName = apiURLBase()+"items/by-filter/%7B%22from%22%20:%20"+1+",%22to%22:"+999999+",%22sub_category%22:%22"+subCategoryID+"%22,%22store%22:null,%22category%22:%22"+category_id+"%22,%22inStock%22:true,%22area%22:null%7D/";
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
                    apiName =apiURLBase()+"items/by-filter/%7B%22from%22%20:%20"+filterItemsModel.getFrom()+",%22to%22:"+filterItemsModel.getTo()+",%22sub_category%22:%22"+filterItemsModel.getSub_cat().getId()+"%22,%22store%22:null,%22category%22:%22"+filterItemsModel.getSub_cat().getCategory_id()+"%22,%22inStock%22:true,%20%22city%22:null,%22area%22:null%7D/";
                }else{
                     apiName= apiURLBase()+"items/by-filter/%7B%22from%22%20:%20"+filterItemsModel.getFrom()+",%22to%22:"+filterItemsModel.getTo()+",%22sub_category%22:%22"+filterItemsModel.getSub_cat().getId()+"%22,%22store%22:null,%22category%22:%22"+filterItemsModel.getSub_cat().getCategory_id()+"%22,%22inStock%22:true,%22area%22:null,%22city%22:[%22"+filterItemsModel.getCity().getIdServer()+"%22]%7D/";
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
//                        areas = "%22"+areas+"%22";
                        areas = areas;
                    }
                }
                apiName = "http://46.101.235.217/api/items/by-filter/%7B%22from%22%20:%20"+filterItemsModel.getFrom()+",%22to%22:"+filterItemsModel.getTo()+",%22sub_category%22:%22"+filterItemsModel.getSub_cat().getId()+"%22,%22store%22:null,%22category%22:%22"+filterItemsModel.getSub_cat().getCategory_id()+"%22,%22inStock%22:true,%22area%22:["+areas+"],%22city%22:null%7D/";
//                Log.i("TAG:     API",apiName);
            }
        }else{

        }
//        Log.i("TAG: API",apiName);
        return apiName;
    }

    public static String apiStoreItems(String store_id,String store_area){
        String apiName2 = apiURLBase()+"items/by-filter/%7B%22from%22%20:%2020,%22to%22:55555,%22store%22:%22"+store_id+"%22,%22area%22:[%22"+store_area+"%22]%7D/";

//        Log.i("TAG: API",apiName2);
        return apiName2;
    }

    public static String apiOffersWithFilter(FilterOffersModel filterOffersModel){
        String apiName = null;
        if (filterOffersModel.getCity() == null)
        {
            apiName = apiURLBase()+"offers/by-filter/%7B%22date%22:%20%22"+filterOffersModel.getISO()+"%22,%22from%22%20:%20"+filterOffersModel.getFrom()+",%22to%22:"+filterOffersModel.getTo()+",%22inStock%22:%22true%22,%20%22city%22:null,%22area%22:null%7D/";
        }else{
            if (filterOffersModel.getAreasList().size() == 0)
            {
                apiName = apiURLBase()+"offers/by-filter/%7B%22date%22:%20%22"+filterOffersModel.getISO()+"%22,%22from%22%20:%20"+filterOffersModel.getFrom()+",%22to%22:"+filterOffersModel.getTo()+",%22inStock%22:%22true%22,%20%22city%22:[%22"+filterOffersModel.getCity().getIdServer()+"%22],%22area%22:null%7D/";
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
                        areas = "%22"+areas+"%22";
                    }
                }
                apiName = apiURLBase()+"offers/by-filter/%7B%20%22date%22:%20%22"+filterOffersModel.getISO()+"%22,%22from%22%20:%20"+filterOffersModel.getFrom()+",%22to%22:"+filterOffersModel.getTo()+",%22inStock%22:%22true%22,%20%22city%22:null,%22area%22:["+areas+"]%7D/";
//                apiName = apiURLBase()+"offers/by-filter/%7B%20%22date%22:%20%22"+filterOffersModel.getISO()+"%22,%22from%22%20:%20"+filterOffersModel.getFrom()+",%22to%22:"+filterOffersModel.getTo()+",%22inStock%22:%22true%22,%20%22city%22:[%22"+filterOffersModel.getCity().getIdServer()+"%22],%22area%22:["+areas+"]%7D/";
            }
        }
//        Log.i("TAG",apiName);
        return apiName;
    }

    public static String apiBrand(){
        String apiName =apiURLBase()+"brands/";
//        Log.i("TAG",apiName);
        return apiName;
    }

    public static String getSingleItem(String itemID){
        String apiName =apiURLBase()+"items/"+itemID+"/";
//        Log.i("TAG",apiName);
        return apiName;
    }

    public static String apiNumberOfItems(FilterItemsModel filterItemsModel){
//        String apiName = "http://46.101.235.217/api/items/filter-count/%7B%22from%22%20:%2020,%22to%22:55555,%22sub_category%22:%226020da4212f197d167672163%22,%22store%22:%22601f0ab685d95e5383acfb8e%22,%22category%22:%226020da0412f197d167672161%22,%22inStock%22:true,%22%20area%22:[%2260082119e4afcdaf7e1cb8ad%22],%22city%22:[%2260081837731d75ae35f558be%22]%7D/";
        String apiName=null;
        if (filterItemsModel.getAreasList().size() == 0)
        {
            if (filterItemsModel.getCity() == null)
            {
                apiName =apiURLBase()+"items/filter-count/%7B%22from%22%20:%20"+filterItemsModel.getFrom()+",%22to%22:"+filterItemsModel.getTo()+",%22sub_category%22:%22"+filterItemsModel.getSub_cat().getId()+"%22,%22store%22:null,%22category%22:%22"+filterItemsModel.getSub_cat().getCategory_id()+"%22,%22inStock%22:true,%20%22city%22:null,%22area%22:null%7D/";
            }else{
                apiName= apiURLBase()+"items/filter-count/%7B%22from%22%20:%20"+filterItemsModel.getFrom()+",%22to%22:"+filterItemsModel.getTo()+",%22sub_category%22:%22"+filterItemsModel.getSub_cat().getId()+"%22,%22store%22:null,%22category%22:%22"+filterItemsModel.getSub_cat().getCategory_id()+"%22,%22inStock%22:true,%22area%22:null,%22city%22:[%22"+filterItemsModel.getCity().getIdServer()+"%22]%7D/";
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
            apiName = apiURLBase()+"items/filter-count/%7B%22from%22%20:%20"+filterItemsModel.getFrom()+",%22to%22:"+filterItemsModel.getTo()+",%22sub_category%22:%22"+filterItemsModel.getSub_cat().getId()+"%22,%22store%22:null,%22category%22:%22"+filterItemsModel.getSub_cat().getCategory_id()+"%22,%22inStock%22:true,%22area%22:["+areas+"],%22city%22:null%7D/";
        }
//        Log.i("TAG: API",apiName2);
        return apiName;
    }


    public static String apiBrandSubCategory(String brandID){
        String apiName =apiURLBase()+"brands/"+brandID+"/";
//        Log.i("TAG",apiName);
        return apiName;
    }
}
