package com.fashion.rest.functions;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.fashion.rest.R;
import com.fashion.rest.model.Deal;
import com.fashion.rest.model.ListItem;
import com.fashion.rest.model.OffersGradientsWithTextColor;
import com.fashion.rest.model.Price;

import java.util.ArrayList;

public class FillItem {

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static ArrayList<Deal> fillEndlessItemDepCatArrayL(ArrayList<Deal> dealArrayL, Context context) {
        //ArrayList<Deal> dealArrayL = new ArrayList<Deal>();
        String image = "https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/meal_image%2Fms.jpg?alt=media&token=9d6bb813-8dce-4785-ae83-c1a9e268b3f2";

        for (int i=0;i<8;i++)
        {
            OffersGradientsWithTextColor offersGradientsWithTextColor = null;
            if (i==0)
            {
                offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna",context.getColor(R.color.colorBlack));
            }
            if (i==1)
            {
                offersGradientsWithTextColor = new OffersGradientsWithTextColor("lady_lips",context.getColor(R.color.colorBlack));
            }
            if (i==2)
            {
                offersGradientsWithTextColor = new OffersGradientsWithTextColor("sexy_blue",context.getColor(R.color.colorBlack));
            }
            if (i==3)
            {
                offersGradientsWithTextColor = new OffersGradientsWithTextColor("purple_love",context.getColor(R.color.colorWhite));
            }
            if (i==4)
            {
                offersGradientsWithTextColor = new OffersGradientsWithTextColor("mauve",context.getColor(R.color.colorWhite));
            }
            if (i==5)
            {
                offersGradientsWithTextColor = new OffersGradientsWithTextColor("frost",context.getColor(R.color.colorWhite));
            }
            if (i==6)
            {
                offersGradientsWithTextColor = new OffersGradientsWithTextColor("decent",context.getColor(R.color.colorBlack));
            }
            if (i==7)
            {
                offersGradientsWithTextColor = new OffersGradientsWithTextColor("aubergine",context.getColor(R.color.colorWhite));
            }
            
            
            int x = 10*dealArrayL.size();
            Price price1 = new Price(x,99,180);
            
            dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));

        }
        //Log.i("TAG TAG TAG TAG",String.valueOf(dealArrayL.size()));

        return dealArrayL;
    }

    public static ArrayList<ListItem> fillEndlessItemListArrayL(ArrayList<ListItem> dealArrayL,Context context) {
        ArrayList<ListItem> listArrayL = new ArrayList<ListItem>();

        ArrayList<Integer> CPArrayListT1 = new ArrayList<>();
        ArrayList<Integer> CPArrayListT2 = new ArrayList<>();
        ArrayList<Integer> CPArrayListT3 = new ArrayList<>();
        ArrayList<Integer> CPArrayListT4 = new ArrayList<>();

        CPArrayListT1.add(0,1);
        CPArrayListT2.add(0,1);
        CPArrayListT3.add(0,1);
        CPArrayListT4.add(0,1);

        ArrayList<ArrayList<Deal>> ItemT1ArrayListT1 = new ArrayList<>();
        ArrayList<ArrayList<Deal>> ItemT2ArrayListT2 = new ArrayList<>();
        ArrayList<ArrayList<Deal>> ItemT3ArrayListT3 = new ArrayList<>();
        ArrayList<ArrayList<Deal>> ItemT4ArrayListT4 = new ArrayList<>();

        listArrayL.add(new ListItem("type1",CPArrayListT1,CPArrayListT2,CPArrayListT3,CPArrayListT4,ItemT1ArrayListT1,ItemT2ArrayListT2,ItemT3ArrayListT3,ItemT4ArrayListT4));
        listArrayL.add(new ListItem("type2",CPArrayListT1,CPArrayListT2,CPArrayListT3,CPArrayListT4,ItemT1ArrayListT1,ItemT2ArrayListT2,ItemT3ArrayListT3,ItemT4ArrayListT4));
        listArrayL.add(new ListItem("type3",CPArrayListT1,CPArrayListT2,CPArrayListT3,CPArrayListT4,ItemT1ArrayListT1,ItemT2ArrayListT2,ItemT3ArrayListT3,ItemT4ArrayListT4));
        listArrayL.add(new ListItem("type4",CPArrayListT1,CPArrayListT2,CPArrayListT3,CPArrayListT4,ItemT1ArrayListT1,ItemT2ArrayListT2,ItemT3ArrayListT3,ItemT4ArrayListT4));


        return listArrayL;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public static ArrayList<Deal> fillAllItemDepCatArrayL(String cat, Context context) {
        ArrayList<Deal> dealArrayL = new ArrayList<Deal>();
        String image = "https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/meal_image%2Fms.jpg?alt=media&token=9d6bb813-8dce-4785-ae83-c1a9e268b3f2";

        if (cat.equals("Sets"))
        {
            OffersGradientsWithTextColor offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna",context.getColor(R.color.colorBlack));

            Price price1 = new Price(180,99,180);
            Price price2 = new Price(170,99,170);
            Price price3 = new Price(160,99,160);

            Deal deal1 = new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor);
            Deal deal2 = new Deal(R.drawable.s3,image,context.getResources().getString(R.string.Embellished),context.getResources().getString(R.string.Embellished_des),price2,offersGradientsWithTextColor);
            Deal deal3 = new Deal(R.drawable.s1,image,context.getResources().getString(R.string.Jewellery),context.getResources().getString(R.string.Jewellery_des),price3,offersGradientsWithTextColor);

            dealArrayL.add(deal1);
            dealArrayL.add(deal2);
            dealArrayL.add(deal3);

            dealArrayL.add(deal2);
            dealArrayL.add(deal3);
            dealArrayL.add(deal1);
        }

        if (cat.equals("Bracelets"))
        {
            OffersGradientsWithTextColor offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna",context.getColor(R.color.colorBlack));

            Price price1 = new Price(550,499,550);
            Price price2 = new Price(650,599,650);
            Price price3 = new Price(750,699,750);

            Deal deal1 = new Deal(R.drawable.b1,image,context.getResources().getString(R.string.Bracelet_Bangles),context.getResources().getString(R.string.Bracelet_Bangles_des),price1,offersGradientsWithTextColor);
            Deal deal2 = new Deal(R.drawable.b2,image,context.getResources().getString(R.string.Bangles),context.getResources().getString(R.string.Bangles_des),price2,offersGradientsWithTextColor);
            Deal deal3 = new Deal(R.drawable.b3,image,context.getResources().getString(R.string.Bracelet),context.getResources().getString(R.string.Bracelet_des),price3,offersGradientsWithTextColor);

            dealArrayL.add(deal1);
            dealArrayL.add(deal2);
            dealArrayL.add(deal3);

            dealArrayL.add(deal2);
            dealArrayL.add(deal3);
            dealArrayL.add(deal1);
        }

        if (cat.equals("Necklaces"))
        {
            OffersGradientsWithTextColor offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna",context.getColor(R.color.colorBlack));

            Price price1 = new Price(950,499,550);
            Price price2 = new Price(1050,599,650);
            Price price3 = new Price(1150,699,750);

            Deal deal1 = new Deal(R.drawable.n3,image,context.getResources().getString(R.string.Retro),context.getResources().getString(R.string.Retro_des),price1,offersGradientsWithTextColor);
            Deal deal2 = new Deal(R.drawable.n2,image,context.getResources().getString(R.string.Retro2),context.getResources().getString(R.string.Retro_de2s),price2,offersGradientsWithTextColor);
            Deal deal3 = new Deal(R.drawable.n1,image,context.getResources().getString(R.string.Retro3),context.getResources().getString(R.string.Retro_des3),price3,offersGradientsWithTextColor);

            dealArrayL.add(deal1);
            dealArrayL.add(deal2);
            dealArrayL.add(deal3);

            dealArrayL.add(deal2);
            dealArrayL.add(deal3);
            dealArrayL.add(deal1);
        }

        if (cat.equals("Rings"))
        {
            OffersGradientsWithTextColor offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna",context.getColor(R.color.colorBlack));

            Price price1 = new Price(80,49,80);
            Price price2 = new Price(90,79,90);
            Price price3 = new Price(100,89,100);

            Deal deal1 = new Deal(R.drawable.r1,image,context.getResources().getString(R.string.Sapphire),context.getResources().getString(R.string.Sapphire_des),price1,offersGradientsWithTextColor);
            Deal deal2 = new Deal(R.drawable.r2,image,context.getResources().getString(R.string.Band_Ring),context.getResources().getString(R.string.Band_Ring_des),price2,offersGradientsWithTextColor);
            Deal deal3 = new Deal(R.drawable.r3,image,context.getResources().getString(R.string.Statement),context.getResources().getString(R.string.Statement_des),price3,offersGradientsWithTextColor);

            dealArrayL.add(deal1);
            dealArrayL.add(deal2);
            dealArrayL.add(deal3);

            dealArrayL.add(deal2);
            dealArrayL.add(deal3);
            dealArrayL.add(deal1);
        }

        if (cat.equals("Anklet"))
        {
            OffersGradientsWithTextColor offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna",context.getColor(R.color.colorBlack));

            Price price1 = new Price(140,99,140);
            Price price2 = new Price(190,149,190);
            Price price3 = new Price(230,199,230);

            Deal deal1 = new Deal(R.drawable.an1,image,context.getResources().getString(R.string.Bohemian),context.getResources().getString(R.string.Bohemian_des),price1,offersGradientsWithTextColor);
            Deal deal2 = new Deal(R.drawable.an2,image,context.getResources().getString(R.string.jewelry_),context.getResources().getString(R.string.jewelry__des),price2,offersGradientsWithTextColor);
            Deal deal3 = new Deal(R.drawable.an3,image,context.getResources().getString(R.string.Statement),context.getResources().getString(R.string.Statement_des),price3,offersGradientsWithTextColor);

            dealArrayL.add(deal1);
            dealArrayL.add(deal2);
            dealArrayL.add(deal3);

            dealArrayL.add(deal2);
            dealArrayL.add(deal3);
            dealArrayL.add(deal1);
        }

        if (cat.equals("Earrings"))
        {
            OffersGradientsWithTextColor offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna",context.getColor(R.color.colorBlack));

            Price price1 = new Price(125,99,125);
            Price price2 = new Price(235,179,235);
            Price price3 = new Price(345,299,345);

            Deal deal1 = new Deal(R.drawable.er1,image,context.getResources().getString(R.string.Women_Drop),context.getResources().getString(R.string.Women_Drop_des),price1,offersGradientsWithTextColor);
            Deal deal2 = new Deal(R.drawable.er2,image,context.getResources().getString(R.string.Earrings),context.getResources().getString(R.string.Earrings_des),price2,offersGradientsWithTextColor);
            Deal deal3 = new Deal(R.drawable.er3,image,context.getResources().getString(R.string.Statement_e),context.getResources().getString(R.string.Statement_e_des),price3,offersGradientsWithTextColor);

            dealArrayL.add(deal1);
            dealArrayL.add(deal2);
            dealArrayL.add(deal3);

            dealArrayL.add(deal2);
            dealArrayL.add(deal3);
            dealArrayL.add(deal1);
        }


        return dealArrayL;
    }
}
