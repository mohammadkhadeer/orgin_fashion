package com.fashion.rest.functions;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.fashion.rest.R;
import com.fashion.rest.model.Category;
import com.fashion.rest.model.Deal;
import com.fashion.rest.model.FastFood;
import com.fashion.rest.model.Notification;
import com.fashion.rest.model.OffersGradientsWithTextColor;
import com.fashion.rest.model.Price;
import com.fashion.rest.model.SubCategory;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.fashion.rest.sharedPreferences.Language.getLanguageFromSP;

public class Functions {
    private static final int PAGE_SIZEH = 8;

    public static int nowNumberOfObject(int x , int y) {
        int z =0;
        if (y ==0)
        {
            z =0;
        }else{
            if (y > PAGE_SIZEH)
            {
                if (x >= y)
                {
                    z =1000;
                }else {
                    int d = y - x;
                    if (d >= PAGE_SIZEH)
                    {
//                        Log.i("TAG","x = x+10");
                        z =PAGE_SIZEH;
                    }else {
//                        Log.i("TAG","x = x+d");
                        z =d;
                    }
                }
            }else {
                z =y;
            }
        }
        return z;
    }

    public static double roundTwoDecimals(double d)
    {
        DecimalFormat twoDForm = new DecimalFormat("#.####");
        return Double.valueOf(twoDForm.format(d));
    }

    public static Typeface changeFontAppName(Context context) {
        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "Pacifico.ttf");
        return typeFace;
    }

    public static Typeface changeFontGeneral(Context context) {
        Typeface typeFace =null;
        if (getLanguageFromSP(context) != null && getLanguageFromSP(context).equals("ar")) {
            typeFace = Typeface.createFromAsset(context.getAssets(), "GE_DINAR_ONE_LIGHT.TTF");
        }else{
            typeFace = Typeface.createFromAsset(context.getAssets(), "NTAILU.TTF");
        }
        return typeFace;
    }

    public static Typeface changeFontCategory(Context context) {
        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "Orbitron.TTF");
        return typeFace;
    }

    public static Typeface changeFontOrbitronBold(Context context) {
        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "OrbitronBold.TTF");
        return typeFace;
    }

    public static Typeface changeFontToArabic(Context context) {
        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "GE_DINAR_ONE_LIGHT.TTF");
        return typeFace;
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

    public static ArrayList<SubCategory> fillSubCatArrayL(Context context) {
        ArrayList<SubCategory> subCategoryArrayL = new ArrayList<SubCategory>();

        SubCategory cat1 = new SubCategory("SubCategory 1","category 2"," "," "," "," ");
        SubCategory cat2 = new SubCategory("SubCategory 2","category 2"," "," "," "," ");
        SubCategory cat3 = new SubCategory("SubCategory 3","category 2"," "," "," "," ");
        SubCategory cat4 = new SubCategory("SubCategory 4","category 2"," "," "," "," ");
        SubCategory cat5 = new SubCategory("SubCategory 5","category 2"," "," "," "," ");
        SubCategory cat6 = new SubCategory("SubCategory 6","category 2"," "," "," "," ");
        SubCategory cat7 = new SubCategory("SubCategory 7","category 2"," "," "," "," ");
        SubCategory cat8 = new SubCategory("SubCategory 8","category 2"," "," "," "," ");
        SubCategory cat9 = new SubCategory("SubCategory 9","category 2"," "," "," "," ");
        SubCategory cat10 = new SubCategory("SubCategory 10","category 2"," "," "," "," ");


        subCategoryArrayL.add(cat1);
        subCategoryArrayL.add(cat2);
        subCategoryArrayL.add(cat3);
        subCategoryArrayL.add(cat4);
        subCategoryArrayL.add(cat5);
        subCategoryArrayL.add(cat6);
        subCategoryArrayL.add(cat7);
        subCategoryArrayL.add(cat8);
        subCategoryArrayL.add(cat9);
        subCategoryArrayL.add(cat10);

        return subCategoryArrayL;
    }

    public static ArrayList<Category> fillCatArrayL(Context context) {
        ArrayList<Category> categoryArrayL = new ArrayList<Category>();

        Category cat1 = new Category("category 1","category 2"," "," "," "," ");
        Category cat2 = new Category("category 2","category 2"," "," "," "," ");
        Category cat3 = new Category("category 3","category 2"," "," "," "," ");
        Category cat4 = new Category("category 4","category 2"," "," "," "," ");
        Category cat5 = new Category("category 5","category 2"," "," "," "," ");
        Category cat6 = new Category("category 6","category 2"," "," "," "," ");
        Category cat7 = new Category("category 7","category 2"," "," "," "," ");
        Category cat8 = new Category("category 8","category 2"," "," "," "," ");
        Category cat9 = new Category("category 9","category 2"," "," "," "," ");
        Category cat10 = new Category("category 10","category 2"," "," "," "," ");


        categoryArrayL.add(cat1);
        categoryArrayL.add(cat2);
        categoryArrayL.add(cat3);
        categoryArrayL.add(cat4);
        categoryArrayL.add(cat5);
        categoryArrayL.add(cat6);
        categoryArrayL.add(cat7);
        categoryArrayL.add(cat8);
        categoryArrayL.add(cat9);
        categoryArrayL.add(cat10);

        return categoryArrayL;
    }

    public static ArrayList<FastFood> fillFastFoodArrayL(ArrayList<FastFood> fastFoodArrayL, Context context) {
        fastFoodArrayL = new ArrayList<FastFood>();

        FastFood fastFood1 = new FastFood(context.getResources().getString(R.string.bg),"15",R.drawable.burger_king);
        FastFood fastFood2 = new FastFood(context.getResources().getString(R.string.macdonalds),"15",R.drawable.mcdonalds);
        FastFood fastFood3 = new FastFood(context.getResources().getString(R.string.hardees),"15",R.drawable.hardess);
        FastFood fastFood7 = new FastFood(context.getResources().getString(R.string.dominos),"15",R.drawable.dom);
        FastFood fastFood4 = new FastFood(context.getResources().getString(R.string.kfc),"15",R.drawable.kfc);
        FastFood fastFood5 = new FastFood(context.getResources().getString(R.string.pizza_h),"15",R.drawable.pizz_h);
        FastFood fastFood6 = new FastFood(context.getResources().getString(R.string.subway),"15",R.drawable.subway);

        fastFoodArrayL.add(fastFood1);
        fastFoodArrayL.add(fastFood2);
        fastFoodArrayL.add(fastFood3);
        fastFoodArrayL.add(fastFood4);
        fastFoodArrayL.add(fastFood5);
        fastFoodArrayL.add(fastFood6);

        return fastFoodArrayL;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public static ArrayList<Deal> fillSetArrayL(ArrayList<Deal> dealArrayL, Context context) {
        dealArrayL = new ArrayList<Deal>();

        OffersGradientsWithTextColor offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna","#000000");

        String image ="https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/1234.webp?alt=media&token=be6f0a3d-7ecd-4cc3-a8a6-2c5acccab08d";
        Price price1 = new Price(180,99,180);
        Price price2 = new Price(170,99,170);
        Price price3 = new Price(160,99,160);

        Deal deal1 = new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor);
        Deal deal2 = new Deal(R.drawable.s3,image,context.getResources().getString(R.string.Embellished),context.getResources().getString(R.string.Embellished_des),price2,offersGradientsWithTextColor);
        Deal deal3 = new Deal(R.drawable.s1,image,context.getResources().getString(R.string.Jewellery),context.getResources().getString(R.string.Jewellery_des),price3,offersGradientsWithTextColor);

        dealArrayL.add(deal1);
        dealArrayL.add(deal2);
        dealArrayL.add(deal3);
        dealArrayL.add(deal1);
        dealArrayL.add(deal2);
        dealArrayL.add(deal3);
        dealArrayL.add(deal1);
        dealArrayL.add(deal2);
        dealArrayL.add(deal3);
        dealArrayL.add(deal1);
        dealArrayL.add(deal2);
        dealArrayL.add(deal3);
        dealArrayL.add(deal1);
        dealArrayL.add(deal2);
        dealArrayL.add(deal3);

        return dealArrayL;
    }


    public static ArrayList<Deal> fillSetArrayL2(ArrayList<Deal> dealArrayL, Context context) {
        dealArrayL = new ArrayList<Deal>();

        OffersGradientsWithTextColor offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna","#000000");

        String image ="https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/6.jpg?alt=media&token=78cb86eb-9226-4709-a36d-9e4a1e7c82b5";
        String image1 ="https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/Screen%20Shot%202021-01-16%20at%2010.08.13%20AM.png?alt=media&token=127194a2-fe0a-4c2e-bb92-948e61b29349";

        Price price1 = new Price(180,99,180);
        Price price2 = new Price(170,99,170);
        Price price3 = new Price(160,99,160);

        Deal deal1 = new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor);
        Deal deal2 = new Deal(R.drawable.s3,image,context.getResources().getString(R.string.Embellished),context.getResources().getString(R.string.Embellished_des),price2,offersGradientsWithTextColor);
        Deal deal3 = new Deal(R.drawable.s1,image,context.getResources().getString(R.string.Jewellery),context.getResources().getString(R.string.Jewellery_des),price3,offersGradientsWithTextColor);

        dealArrayL.add(deal1);
        dealArrayL.add(deal2);
        dealArrayL.add(deal3);
        dealArrayL.add(deal1);
        dealArrayL.add(deal2);
        dealArrayL.add(deal3);
        dealArrayL.add(deal1);
        dealArrayL.add(deal2);
        dealArrayL.add(deal3);
        dealArrayL.add(deal1);
        dealArrayL.add(deal2);
        dealArrayL.add(deal3);
        dealArrayL.add(deal1);
        dealArrayL.add(deal2);
        dealArrayL.add(deal3);

        return dealArrayL;
    }


    public static ArrayList<String> fillImgArrayL() {
        ArrayList<String> ImgArrayL = new ArrayList<String>();

        String image = "https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/meal_image%2Fms.jpg?alt=media&token=9d6bb813-8dce-4785-ae83-c1a9e268b3f2";
        String image1 ="https://firebasestorage.googleapis.com/v0/b/test-c434c.appspot.com/o/Screen%20Shot%202020-10-22%20at%2012.28.40%20PM.pn" +
                "g?alt=media&token=9bb719a5-ccae-492f-9091-db5a0c559465";
        String image2 = "https://firebasestorage.googleapis.com/v0/b/test-c434c.appspot.com/o/Screen%20Shot%202020-10-22%20at%2012.29" +
                ".01%20PM.png?alt=media&token=9c0f6a72-5fdd-4151-a37b-fba462b8fd16";
        String image3 = "https://firebasestorage.googleapis.com/v0/b/test-c434c.appspot.com/o/Screen%20Shot%202020-10-22%20at%2012.28.16%20PM.png?alt=media&token=16b5e6ff-8ab9-4af7-b764-3eeb44159bcf";
        String image4 = "https://firebasestorage.googleapis.com/v0/b/test-c434c.appspot.com/o/Screen%20Shot%202020-10-22%20at%2012.29.12%20PM.png?alt=media&token=88b6f628-4878-4e05-8f89-c876c3fc4205";
        ImgArrayL.add(image1);
        ImgArrayL.add(image3);
        ImgArrayL.add(image4);

        return ImgArrayL;
    }

    public static ArrayList<Notification> fillNotificationsArrayL(Context context) {
        ArrayList<Notification> notificationArrayL = new ArrayList<Notification>();

        String image1 ="https://firebasestorage.googleapis.com/v0/b/test-c434c.appspot.com/o/Screen%20Shot%202020-10-22%20at%2012.28.40%20PM.pn" +
                "g?alt=media&token=9bb719a5-ccae-492f-9091-db5a0c559465";

        Notification notification = new Notification(
                "1",context.getResources().getString(R.string.title1),context.getResources().getString(R.string.title1_des),image1,"test","test","18/10/2020","Bracelets"
        );

        Notification notification2 = new Notification(
                "1",context.getResources().getString(R.string.title2),context.getResources().getString(R.string.title2_des),image1,"test","test","18/10/2020","Bracelets"
        );

        Notification notification3 = new Notification(
                "0",context.getResources().getString(R.string.title3),context.getResources().getString(R.string.title3_des),image1,"test","test","18/10/2020","Bracelets"
        );

        Notification notification4 = new Notification(
                "0",context.getResources().getString(R.string.title4),context.getResources().getString(R.string.title4_des),image1,"test","test","18/10/2020","Bracelets"
        );

        Notification notification5 = new Notification(
                "1",context.getResources().getString(R.string.title5),context.getResources().getString(R.string.title5_des),image1,"test","test","18/10/2020","Bracelets"
        );
        Notification notification6 = new Notification(
                "1",context.getResources().getString(R.string.title6),context.getResources().getString(R.string.title6_des),image1,"test","test","18/10/2020","Bracelets"
        );


        notificationArrayL.add(notification);
        notificationArrayL.add(notification2);
        notificationArrayL.add(notification3);
        notificationArrayL.add(notification4);
        notificationArrayL.add(notification5);
        notificationArrayL.add(notification6);

        return notificationArrayL;
    }

    public static Typeface changeFontBold(Context context) {
        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "NTAILUB.TTF");
        return typeFace;
    }
}
