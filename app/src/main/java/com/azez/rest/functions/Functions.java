package com.azez.rest.functions;


import android.content.Context;
import android.graphics.Typeface;

import com.azez.rest.R;
import com.azez.rest.model.Category;
import com.azez.rest.model.FastFood;

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

    public static ArrayList<String> fillMeanArrayL(ArrayList<String> meanArrayL, Context context) {
        meanArrayL = new ArrayList<String>();


        meanArrayL.add("res");
        meanArrayL.add("res");
        meanArrayL.add("res");
        meanArrayL.add("ff");
        meanArrayL.add("res");
        meanArrayL.add("res");
        meanArrayL.add("res");
        meanArrayL.add("res");
        meanArrayL.add("res");
        meanArrayL.add("res");
        meanArrayL.add("res");
        meanArrayL.add("res");

        return meanArrayL;
    }


    public static ArrayList<FastFood> fillFastFoodArrayL(ArrayList<FastFood> fastFoodArrayL, Context context) {
        fastFoodArrayL = new ArrayList<FastFood>();

        FastFood fastFood1 = new FastFood(context.getResources().getString(R.string.bg),"15",R.drawable.m1);
        FastFood fastFood2 = new FastFood(context.getResources().getString(R.string.macdonalds),"15",R.drawable.m1);
        FastFood fastFood3 = new FastFood(context.getResources().getString(R.string.hardees),"15",R.drawable.m1);
        FastFood fastFood4 = new FastFood(context.getResources().getString(R.string.macdonalds),"15",R.drawable.m1);
        FastFood fastFood5 = new FastFood(context.getResources().getString(R.string.pizza_h),"15",R.drawable.m1);
        FastFood fastFood6 = new FastFood(context.getResources().getString(R.string.subway),"15",R.drawable.m1);

        fastFoodArrayL.add(fastFood1);
        fastFoodArrayL.add(fastFood2);
        fastFoodArrayL.add(fastFood3);
        fastFoodArrayL.add(fastFood4);
        fastFoodArrayL.add(fastFood5);
        fastFoodArrayL.add(fastFood6);

        return fastFoodArrayL;
    }

}
