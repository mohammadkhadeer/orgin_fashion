package com.azez.rest.functions;


import android.content.Context;
import android.graphics.Typeface;

import com.azez.rest.R;
import com.azez.rest.model.Category;

import java.util.ArrayList;

public class Functions {
    public static Typeface changeFontAppName(Context context) {
        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "Pacifico.ttf");
        return typeFace;
    }

    public static Typeface changeFontGeneral(Context context) {
        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "GE_DINAR_ONE_LIGHT.TTF");
        return typeFace;
    }

    public static ArrayList<Category> fillOptionsArrayL(ArrayList<Category> categoryArrayL, Context context) {
        categoryArrayL = new ArrayList<Category>();

        Category category1 = new Category(context.getResources().getString(R.string.creat_meal),R.drawable.m1);
        Category category2 = new Category(context.getResources().getString(R.string.family_meal),R.drawable.m2);
        Category category3 = new Category(context.getResources().getString(R.string.home_meal),R.drawable.m3);
        Category category4 = new Category(context.getResources().getString(R.string.veg_meal),R.drawable.m4);
        Category category5 = new Category(context.getResources().getString(R.string.healthy_food),R.drawable.m5);
        Category category6 = new Category(context.getResources().getString(R.string.drinks),R.drawable.m10);
        Category category7 = new Category(context.getResources().getString(R.string.dessert),R.drawable.m7);
        Category category8 = new Category(context.getResources().getString(R.string.bread),R.drawable.m8);
        Category category9 = new Category(context.getResources().getString(R.string.creat_meal),R.drawable.m9);

        categoryArrayL.add(category1);
        categoryArrayL.add(category2);
        categoryArrayL.add(category3);
        categoryArrayL.add(category4);
        categoryArrayL.add(category5);
        categoryArrayL.add(category6);
        categoryArrayL.add(category7);
        categoryArrayL.add(category8);
        categoryArrayL.add(category9);


        return categoryArrayL;
    }

}
