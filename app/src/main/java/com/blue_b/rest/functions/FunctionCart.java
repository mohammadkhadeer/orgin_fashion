package com.blue_b.rest.functions;

import android.content.Context;
import android.content.SharedPreferences;

import com.blue_b.rest.model.Deal;
import com.blue_b.rest.model.ItemInDB;
import com.blue_b.rest.database.DataBaseInstance;
import com.blue_b.rest.database.Insert;
import com.blue_b.rest.database.Read;
import com.blue_b.rest.sharedPreferences.NumberOfItemInCart;

import java.util.ArrayList;

public class FunctionCart {

    public static void updateNumberOfItemInCartAndInsertToDataBase(Context context, SharedPreferences SharedPreferences, SharedPreferences.Editor Editor, Deal deal, int numberOfItem) {
        //number of item in the cart we save it in SP
        calculateNumberOfItemsOnCart(context,SharedPreferences,Editor,deal,numberOfItem);

        if (checkIfTheItemAlreadyInDataBase(deal,context,numberOfItem) == 1)
        {
            Insert.insertItemToDataBase(deal, DataBaseInstance.getDataBaseInstance(context),numberOfItem);
        }else{
            DataBaseInstance.getDataBaseInstance(context).updateItemNumberInCart(String.valueOf(checkIfTheItemAlreadyInDataBase(deal,context,numberOfItem)),deal.getName());
        }

    }

    private static int checkIfTheItemAlreadyInDataBase(Deal deal,Context context,int numberOfItem) {

        int numberOfItemNow =0;

        ArrayList<ItemInDB> itemInDBArrayL = new ArrayList<ItemInDB>();

       itemInDBArrayL = Read.getAllItemFromCart(context);

        if (itemInDBArrayL.isEmpty()) {
            numberOfItemNow = numberOfItem;
        }
        else {
            int flag = 0;
            for (int i = 0; i < itemInDBArrayL.size(); i++) {
                if (itemInDBArrayL.get(i).getName().equals(deal.getName())) {
                    flag = 1;
                    numberOfItemNow = numberOfItem +itemInDBArrayL.get(i).getNumberOfItemNow();
                }
            }
            if (flag == 0)
            {
                numberOfItemNow = numberOfItem;
            }
        }

        return numberOfItemNow;
    }

    public static void calculateNumberOfItemsOnCart(Context context,SharedPreferences SharedPreferences,SharedPreferences.Editor Editor, Deal deal,int numberOfItem) {
        String numberOfItems= NumberOfItemInCart.getNumberOfItemInCartFromSP(context);
        if (numberOfItems == null || numberOfItems.equals("") || numberOfItems.equals("0"))
        {
            NumberOfItemInCart.saveNumberOfItemsInCartInSP(context,SharedPreferences,Editor,"1");
        }else{
            int x = Integer.parseInt(numberOfItems);
            x= x+numberOfItem;
            NumberOfItemInCart.saveNumberOfItemsInCartInSP(context,SharedPreferences,Editor,String.valueOf(x));
        }
    }

    public static void deleteItemFromCartOnCart(Context context,SharedPreferences SharedPreferences,SharedPreferences.Editor Editor,int numberOfItem) {
        String totalItems= NumberOfItemInCart.getNumberOfItemInCartFromSP(context);
        int x = Integer.parseInt(totalItems);
        x= x-numberOfItem;
        NumberOfItemInCart.saveNumberOfItemsInCartInSP(context,SharedPreferences,Editor,String.valueOf(x));
    }

    public static void deleteItemInCartFromDataBase(Context context, String itemName) {
        //number of item in the cart we save it in SP
        DataBaseInstance.getDataBaseInstance(context).deleteItemFromCart(itemName);
    }

    public static int numberOfItemsOnCartNow(Context context) {
        String numberOfItems= NumberOfItemInCart.getNumberOfItemInCartFromSP(context);
        int numberOfItem = 0;
        if (numberOfItems == null || numberOfItems.equals("") || numberOfItems.equals("0"))
        {
            numberOfItem =0;
        }else{
            numberOfItem = Integer.parseInt(numberOfItems);
        }
        return numberOfItem;
    }

    public static void updateNumberOfItemInCartDataBase(Context context, SharedPreferences SharedPreferences, SharedPreferences.Editor Editor, Deal deal,int numberOfItem,String addOrRemove) {
        //number of item in the cart we save it in SP
        calculateNumberOfItemsOnCart(context,SharedPreferences,Editor,deal,numberOfItem);

        if (checkIfTheItemAlreadyInDataBase(deal,context,numberOfItem) == 1)
        {
            Insert.insertItemToDataBase(deal, DataBaseInstance.getDataBaseInstance(context),numberOfItem);
        }else{
            DataBaseInstance.getDataBaseInstance(context).updateItemNumberInCart(String.valueOf(checkIfTheItemAlreadyInDataBase(deal,context,numberOfItem)),deal.getName());
        }

    }

    public static void calculateNumberOfItemsOnCart(Context context,SharedPreferences SharedPreferences,SharedPreferences.Editor Editor,ItemInDB itemInDB,String addOrRemove,int numberOfItemNow) {
        String numberOfItems= NumberOfItemInCart.getNumberOfItemInCartFromSP(context);
        int x = Integer.parseInt(numberOfItems);
        if (numberOfItemNow != 0)
        {
            if (addOrRemove.equals("add"))
            {
                x= x+1;
                int y = numberOfItemNow+1;
                NumberOfItemInCart.saveNumberOfItemsInCartInSP(context,SharedPreferences,Editor,String.valueOf(x));
                DataBaseInstance.getDataBaseInstance(context).updateItemNumberInCart(String.valueOf(y),itemInDB.getName());
            }else{
                x= x-1;
                DataBaseInstance.getDataBaseInstance(context).updateItemNumberInCart(String.valueOf(numberOfItemNow),itemInDB.getName());
                NumberOfItemInCart.saveNumberOfItemsInCartInSP(context,SharedPreferences,Editor,String.valueOf(x));
            }
        }else{
            x= x-1;
            NumberOfItemInCart.saveNumberOfItemsInCartInSP(context,SharedPreferences,Editor,String.valueOf(x));
            DataBaseInstance.getDataBaseInstance(context).deleteItemFromCart(itemInDB.getName());
        }
    }
}
