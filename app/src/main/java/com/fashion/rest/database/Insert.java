package com.fashion.rest.database;

import com.fashion.rest.model.Deal;

public class Insert {

    public static boolean insertItemToDataBase(Deal deal, DBHelper database,int numberOfItem) {
//        Log.d("TAG","deal.getImage1() "+deal.getImage1());
//        Log.d("TAG","deal.getName() "+deal.getName());
//        Log.d("TAG","deal.getDes() "+deal.getDes());
//        Log.d("TAG","deal.getPrice() "+String.valueOf(deal.getPrice().getPrice()));
//        Log.d("TAG","deal.getPrice() "+String.valueOf(deal.getPrice().getNewPrice()));
//        Log.d("TAG","deal.getPrice() "+String.valueOf(deal.getPrice().getOldPrice()));
//        Log.d("TAG","deal.numberOfItem() "+String.valueOf(numberOfItem));

        boolean isInserted = database.insertItemToCart(
                String.valueOf(deal.getImage1())
                ,deal.getImage()
                ,deal.getName()
                ,deal.getDes()
                ,String.valueOf(deal.getPrice().getPrice())
                ,String.valueOf(deal.getPrice().getNewPrice())
                ,String.valueOf(deal.getPrice().getOldPrice())
                ,String.valueOf(numberOfItem)
        );
        return isInserted;
    }
}
