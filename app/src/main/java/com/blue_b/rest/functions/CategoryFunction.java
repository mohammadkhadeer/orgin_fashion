package com.blue_b.rest.functions;

import android.content.Context;

import com.blue_b.rest.R;
import com.blue_b.rest.model.ItemFilterModel;
import com.blue_b.rest.model.ItemSelectedFilterModel;

import java.util.ArrayList;

public class CategoryFunction {

    public static ItemSelectedFilterModel fillCatObject(String cat, Context context) {
        ItemSelectedFilterModel itemSelectedFilterModel = new ItemSelectedFilterModel(cat,fillCatS(cat,context),"cat");

        return itemSelectedFilterModel;
    }

    public static String fillCatS(String cat, Context context) {
        String cats = "";
        if (cat.equals("Sets"))
        {
            cats = context.getResources().getString(R.string.set_s);
        }

        if (cat.equals("Bracelets"))
        {
            cats = context.getResources().getString(R.string.bracelets_s);
        }

        if (cat.equals("Necklaces"))
        {
            cats = context.getResources().getString(R.string.necklaces_s);
        }

        if (cat.equals("Rings"))
        {
            cats = context.getResources().getString(R.string.rings_s);
        }

        if (cat.equals("Anklet"))
        {
            cats = context.getResources().getString(R.string.foot_anklet_s);
        }

        if (cat.equals("Earring"))
        {
            cats = context.getResources().getString(R.string.earring_s);
        }


        return cats;
    }

    public static ArrayList<ItemFilterModel> fillFromPrice(Context context, String category)
    {

        ArrayList<ItemFilterModel> priceArrayL = new ArrayList<>();

        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.ra_50),context.getResources().getString(R.string.ra_50_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.ra_100),context.getResources().getString(R.string.ra_100_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.ra_150),context.getResources().getString(R.string.ra_200_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.ra_200),context.getResources().getString(R.string.ra_200_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.ra_250),context.getResources().getString(R.string.ra_250_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.ra_300),context.getResources().getString(R.string.ra_300_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.ra_350),context.getResources().getString(R.string.ra_350_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.ra_400),context.getResources().getString(R.string.ra_400_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.ra_450),context.getResources().getString(R.string.ra_450_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.ra_500),context.getResources().getString(R.string.ra_500_s)));

        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_1000),context.getResources().getString(R.string.t_1000_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_5000),context.getResources().getString(R.string.t_5000_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_10000),context.getResources().getString(R.string.t_10000_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_15000),context.getResources().getString(R.string.t_15000_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_20000),context.getResources().getString(R.string.t_20000_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_25000),context.getResources().getString(R.string.t_25000_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_30000),context.getResources().getString(R.string.t_30000_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_35000),context.getResources().getString(R.string.t_35000_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_40000),context.getResources().getString(R.string.t_40000_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_45000),context.getResources().getString(R.string.t_45000_s)));

        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_50000),context.getResources().getString(R.string.t_50000_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_60000),context.getResources().getString(R.string.t_60000_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_70000),context.getResources().getString(R.string.t_70000_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_80000),context.getResources().getString(R.string.t_80000_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_90000),context.getResources().getString(R.string.t_90000_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_100000),context.getResources().getString(R.string.t_100000_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_150000),context.getResources().getString(R.string.t_150000_s)));
        priceArrayL.add(new ItemFilterModel(context.getResources().getString(R.string.t_200000),context.getResources().getString(R.string.t_200000_s)));

        return priceArrayL;
    }

}
