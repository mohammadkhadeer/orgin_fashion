package com.fashion.rest.functions;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.fashion.rest.R;
import com.fashion.rest.database.DBHelper;
import com.fashion.rest.model.Area;
import com.fashion.rest.model.City;
import com.fashion.rest.model.Deal;
import com.fashion.rest.model.ListItem;
import com.fashion.rest.model.OffersGradientsWithTextColor;
import com.fashion.rest.model.Price;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.fashion.rest.database.DataBaseInstance.getDataBaseInstance;

public class FillItem {

    public static ArrayList<Area> fillAreas(Context context,String city) {
        ArrayList<Area> listArrayL = new ArrayList<Area>();

        Cursor res = getDataBaseInstance(context).descendingNeighborhood();

        while (res.moveToNext()) {
            // idOnServer col 1 ... city_en_name col 2 ... city_local_name col 3 ... area_en_name col 4 ... area_local_name col 5
            if (city.equals(res.getString(2).replace("\n", ""))) {
                listArrayL.add(new Area(res.getString(4).replace("\n", ""),
                        res.getString(5).replace("\n", ""),
                        res.getString(1).replace("\n", "")
                ));
            }
        }
        Collections.sort(listArrayL, new Comparator<Area>() {
            @Override
            public int compare(Area lhs, Area rhs) {
                return lhs.getName_en().compareTo(rhs.getName_en());
            }
        });
        return listArrayL;
    }

    public static ArrayList<City> fillCityArrayL(Context context) {
        ArrayList<City> listArrayL = new ArrayList<City>();
        Cursor res = getDataBaseInstance(context).descendingCities();

        while (res.moveToNext()) {
            // name_en col 2 .... name_local col 3 ... idOnServer 1
//            Log.i("TAG 1",res.getString(1).replace("\n", ""));
//            Log.i("TAG 2",res.getString(2).replace("\n", ""));
//            Log.i("TAG 3",res.getString(3).replace("\n", ""));

            listArrayL.add(new City(res.getString(2).replace("\n", ""),
                    res.getString(3).replace("\n", ""),
                    res.getString(1).replace("\n", "")
            ));
        }
        Collections.reverse(listArrayL);

        return listArrayL;
    }

    public static ArrayList<String> fillItemsIdInFavTable(Context context) {
        ArrayList<String> item_id_ArrayL = new ArrayList<String>();
        Cursor res = getDataBaseInstance(context).descendingFav();
        while (res.moveToNext()) {
            item_id_ArrayL.add(res.getString(1).replace("\n", ""));
        }
        return item_id_ArrayL;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static ArrayList<Deal> fillEndlessItemDepCatArrayL2(ArrayList<Deal> dealArrayL, Context context,int xxx) {
        //ArrayList<Deal> dealArrayL = new ArrayList<Deal>();
        String image0 = "https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/5.jpg?alt=media&token=62e84817-bf98-427a-9574-62f3bdde6e63";
        String image1 = "https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/meal_image%2Fms.jpg?alt=media&token=9d6bb813-8dce-4785-ae83-c1a9e268b3f2";
        String image2 ="https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/123.jpg?alt=media&token=0c082f00-3ea9-47c5-8f1f-62b0bc90a9aa";
        String image ="https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/1234.webp?alt=media&token=be6f0a3d-7ecd-4cc3-a8a6-2c5acccab08d";

        if (xxx==1)
        {
            for (int i=0;i<24;i++)
            {
                int x = 10*dealArrayL.size();
                Price price1 = new Price(x,99,180);

                OffersGradientsWithTextColor offersGradientsWithTextColor = null;
                if (i==0)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("mauve","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==1)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("frost","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==2)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("sexy_blue","#000000");
                    dealArrayL.add(new Deal(R.drawable.s2,image2,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==3)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("purple_love","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image0,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==4)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna","#000000");
                    dealArrayL.add(new Deal(R.drawable.s2,image0,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==5)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("lady_lips","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image0,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==6)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("decent","#000000");
                    dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==7)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("aubergine","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }



                if (i==8)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("mauve","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==9)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("frost","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==10)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("sexy_blue","#000000");
                    dealArrayL.add(new Deal(R.drawable.s2,image2,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==11)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("purple_love","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image0,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==12)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna","#000000");
                    dealArrayL.add(new Deal(R.drawable.s2,image0,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==13)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("lady_lips","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image0,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==14)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("decent","#000000");
                    dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==15)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("aubergine","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }


                if (i==16)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("mauve","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==17)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("frost","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==18)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("sexy_blue","#000000");
                    dealArrayL.add(new Deal(R.drawable.s2,image2,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==19)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("purple_love","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image0,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==20)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna","#000000");
                    dealArrayL.add(new Deal(R.drawable.s2,image0,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==21)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("lady_lips","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image0,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==22)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("decent","#000000");
                    dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==23)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("aubergine","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }

                //dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));

            }
        }else{
            for (int i=0;i<1;i++)
            {
                int x = 20*dealArrayL.size();
                Price price1 = new Price(x,99,180);

                OffersGradientsWithTextColor offersGradientsWithTextColor = null;
                if (i==0)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("mauve","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==1)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("frost","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==2)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("sexy_blue","#000000");
                    dealArrayL.add(new Deal(R.drawable.s2,image2,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }
                if (i==3)
                {
                    offersGradientsWithTextColor = new OffersGradientsWithTextColor("purple_love","#FFFFFF");
                    dealArrayL.add(new Deal(R.drawable.s2,image0,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
                }

                //dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));

            }
        }

        //Log.i("TAG TAG TAG TAG",String.valueOf(dealArrayL.size()));

        return dealArrayL;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static ArrayList<Deal> fillEndlessItemDepCatArrayL(ArrayList<Deal> dealArrayL, Context context) {
        //ArrayList<Deal> dealArrayL = new ArrayList<Deal>();
        String image0 = "https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/5.jpg?alt=media&token=62e84817-bf98-427a-9574-62f3bdde6e63";
        String image1 = "https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/meal_image%2Fms.jpg?alt=media&token=9d6bb813-8dce-4785-ae83-c1a9e268b3f2";
        String image2 ="https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/123.jpg?alt=media&token=0c082f00-3ea9-47c5-8f1f-62b0bc90a9aa";
        String image ="https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/1234.webp?alt=media&token=be6f0a3d-7ecd-4cc3-a8a6-2c5acccab08d";

        for (int i=0;i<8;i++)
        {
            int x = 10*dealArrayL.size();
            Price price1 = new Price(x,99,180);

            OffersGradientsWithTextColor offersGradientsWithTextColor = null;
            if (i==0)
            {
                offersGradientsWithTextColor = new OffersGradientsWithTextColor("mauve","#FFFFFF");
                dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
            }
            if (i==1)
            {
                offersGradientsWithTextColor = new OffersGradientsWithTextColor("frost","#FFFFFF");
                dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
            }
            if (i==2)
            {
                offersGradientsWithTextColor = new OffersGradientsWithTextColor("sexy_blue","#000000");
                dealArrayL.add(new Deal(R.drawable.s2,image2,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
            }
            if (i==3)
            {
                offersGradientsWithTextColor = new OffersGradientsWithTextColor("purple_love","#FFFFFF");
                dealArrayL.add(new Deal(R.drawable.s2,image0,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
            }
            if (i==4)
            {
                offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna","#000000");
                dealArrayL.add(new Deal(R.drawable.s2,image0,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
            }
            if (i==5)
            {
                offersGradientsWithTextColor = new OffersGradientsWithTextColor("lady_lips","#FFFFFF");
                dealArrayL.add(new Deal(R.drawable.s2,image0,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
            }
            if (i==6)
            {
                offersGradientsWithTextColor = new OffersGradientsWithTextColor("decent","#000000");
                dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
            }
            if (i==7)
            {
                offersGradientsWithTextColor = new OffersGradientsWithTextColor("aubergine","#FFFFFF");
                dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));
            }

            //dealArrayL.add(new Deal(R.drawable.s2,image,context.getResources().getString(R.string.QiaoKai),context.getResources().getString(R.string.QiaoKai_des),price1,offersGradientsWithTextColor));

        }
        //Log.i("TAG TAG TAG TAG",String.valueOf(dealArrayL.size()));

        return dealArrayL;
    }

    public static ArrayList<ListItem> fillEndlessItemListArrayL(ArrayList<ListItem> dealArrayL,Context context) {
        ArrayList<ListItem> listArrayL = new ArrayList<ListItem>();


        listArrayL.add(new ListItem("type2"));
        listArrayL.add(new ListItem("type4"));
        listArrayL.add(new ListItem("type4"));
        listArrayL.add(new ListItem("type2"));


        return listArrayL;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public static ArrayList<Deal> fillAllItemDepCatArrayL(String cat, Context context) {
        ArrayList<Deal> dealArrayL = new ArrayList<Deal>();
        String image = "https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/meal_image%2Fms.jpg?alt=media&token=9d6bb813-8dce-4785-ae83-c1a9e268b3f2";

        if (cat.equals("Sets"))
        {
            OffersGradientsWithTextColor offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna","#000000");

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
            OffersGradientsWithTextColor offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna","#000000");

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
            OffersGradientsWithTextColor offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna","#000000");

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
            OffersGradientsWithTextColor offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna","#000000");

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
            OffersGradientsWithTextColor offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna","#000000");

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
            OffersGradientsWithTextColor offersGradientsWithTextColor = new OffersGradientsWithTextColor("roseanna","#000000");

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
