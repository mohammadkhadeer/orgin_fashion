package com.fashion.rest.functions;


import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fashion.rest.R;
import com.fashion.rest.database.DBHelper;
import com.fashion.rest.model.Category;
import com.fashion.rest.model.Deal;
import com.fashion.rest.model.FastFood;
import com.fashion.rest.model.Flag;
import com.fashion.rest.model.ItemFavorite;
import com.fashion.rest.model.ItemTest;
import com.fashion.rest.model.Location;
import com.fashion.rest.model.Notification;
import com.fashion.rest.model.Offer;
import com.fashion.rest.model.OffersGradientsWithTextColor;
import com.fashion.rest.model.Price;
import com.fashion.rest.model.ReportType;
import com.fashion.rest.model.Store;
import com.fashion.rest.model.SubCategory;
import com.fashion.rest.model.Sub_Cat;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static com.fashion.rest.database.DataBaseInstance.getDataBaseInstance;
import static com.fashion.rest.functions.FillItem.fillItemsIdInFavTable;
import static com.fashion.rest.sharedPreferences.Language.getLanguageFromSP;
import static com.fashion.rest.sharedPreferences.NumberOfNotification.getNumberOfItemInCartFromSP;
import static com.fashion.rest.sharedPreferences.NumberOfNotification.saveNumberOfItemsInCartInSP;

public class Functions {

    public static String[] splitString(String textStr,String signal) {
        final String[] stringAfterSplit = textStr.split(signal);;

        return stringAfterSplit;
    }

    public static String getTimeStamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String format = simpleDateFormat.format(new Date());

        return format;
    }

    public static int notificationNumber(Context context) {
        String num = getNumberOfItemInCartFromSP(context);
        int z=-1;
        if (num != null && !"".equals(num))
        {
            int x = Integer.parseInt(num);
            z=x;
        }

        return z;
    }

    public static String getIOs() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        return nowAsISO;
    }


    public static String getTextEngOrLocal(Context context,String eng,String local) {
//        if (Locale.getDefault().getLanguage().equals("en"))
        if (getLanguageFromSP(context).equals("en"))
            return eng;
            else
                return local;
    }

    public static String calculatePercentage(String number1,String number2) {
        int a = Integer.parseInt(number1);
        int b = Integer.parseInt(number2);
        int x =  a -  b;
        double z = (((double)x/(double)a));
        double y = z*100;
        int xx = (int) y;
        String bb = String.valueOf(xx)+"%";
        return bb;
    }

    public static ItemTest convertOfferToItem(Offer offer) {
        //must to add store info
        String storeNameStr,storeImage,website_link;
        storeNameStr = "Lacoste";
        storeImage = "https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/lacoste-logo.png?alt=media&token=3f7d0317-11d6-4b6d-9763-4c3e3eec08e4";
        website_link = "https://www.farfetch.com/ae/shopping/men/lacoste/items.aspx?utm_source=google&utm_medium=cpc&utm_keywordid=123323324&pid=google_search&af_channel=Search&c=658279425&af_c_id=658279425&af_siteid=&af_keywords=kwd-88551300&af_adset_id=35964967809&af_ad_id=492268062138&af_sub1=123323324&is_retargeting=true";
        Flag flag = new Flag(storeImage);
        Location location = new Location("25.2616938","55.3818661","id");
        ArrayList<Location> locationsArrayL=new ArrayList<>();
        locationsArrayL.add(location);
        Store store = new Store(flag,storeNameStr,"id",storeNameStr,"0582402431",locationsArrayL);
        Sub_Cat sub_cat=new Sub_Cat("name","test","offers","offers","offers",flag);
        ItemTest itemTest = new ItemTest(
                offer.getFlagArrayL()
                ,store
                ,sub_cat
                ,offer.getName(),offer.getName_local(),offer.getDescription(),offer.getDescription_local(),offer.getPrice(),offer.getDiscountPrice(),offer.getItem_id()
        );
        return itemTest;
    }

    public static void checkFavOrNot(String item_id, Context context, ImageView imageView) {
        ArrayList<String> item_id_ArrayL = fillItemsIdInFavTable(context);
        int flag=0;
        for (int i=0;i<item_id_ArrayL.size();i++)
        {
            if (item_id_ArrayL.get(i).equals(item_id)){
                flag =1;
                imageView.setImageResource(R.drawable.ic_favorites);
            }
        }
        if (flag ==0)
            imageView.setImageResource(R.drawable.item_favu);
    }

    public static boolean checkFavOrNotInsertOrDelete(String item_id, Context context) {
        ArrayList<String> item_id_ArrayL = fillItemsIdInFavTable(context);
        boolean x=false;
        int flag =0;
        for (int i=0;i<item_id_ArrayL.size();i++)
        {
            if (item_id_ArrayL.get(i).equals(item_id))
            {
                flag =1;
            }
        }
        if (flag ==0)
            x =false;
        else
            x=true;
        return x;
    }

    public static void actionListenerToFav(final String item_id, final String sub_cat_id, final String cat_id, final Context context, final ImageView imageView, RelativeLayout relativeLayout) {
        final DBHelper db =getDataBaseInstance(context);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.ic_favorites);
                if (checkFavOrNotInsertOrDelete(item_id,context))
                {
                    imageView.setImageResource(R.drawable.item_favu);
                    db.deleteItemFromFav(item_id);
                } else {
                    db.insertItemToFav(item_id,sub_cat_id,cat_id);
                }
            }
        });
    }

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

    public static ArrayList<String> getNewItems(ArrayList<String> allItemsArrayList,ArrayList<String> itemMustLoaded) {
        int z =0;
        ArrayList<String> newItemsToLoad = new ArrayList<>();
        int v = allItemsArrayList.size() - itemMustLoaded.size();
        if (allItemsArrayList.size() ==0 || v==0)
        {
            z =0;
        }else{
            if ((allItemsArrayList.size()-itemMustLoaded.size()) > 8)
            {
                z=8+itemMustLoaded.size();
                for (int i =itemMustLoaded.size();i<z;i++)
                {
                    newItemsToLoad.add(allItemsArrayList.get(i));
                }
            }else{
                z= allItemsArrayList.size()-itemMustLoaded.size();
                for (int i =itemMustLoaded.size();i<allItemsArrayList.size();i++)
                {
                    newItemsToLoad.add(allItemsArrayList.get(i));
                }
            }
        }
        return newItemsToLoad;
    }

    public static int loadMoreItem(int all,int Loaded) {
        int z =0;
        int v = all - Loaded;
        if (all ==0 || v==0)
        {
            z =0;
        }else{
            if ((all-Loaded) > 8)
            {
                z=8;
            }else{
                z= all-Loaded;
            }
        }
        return z;
    }

    public static ItemFavorite itemFav() {
        ArrayList<Flag> flagArrayL = new ArrayList<>();
        String image = null;
        Flag flag = new Flag(image);
        ArrayList<Location> locationsArrayL = new ArrayList<>();
        Store store = new Store(flag," "," "," "," ",locationsArrayL);
        Sub_Cat sub_cat = new Sub_Cat();
        ItemFavorite itemFavorite = new ItemFavorite(flagArrayL,store,sub_cat," "," "," "," "," "," "," ",false,true);
        return itemFavorite;
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

    public static ArrayList<String> fillImgArrayL() {
        ArrayList<String> ImgArrayL = new ArrayList<String>();
        String image ="https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/1234.webp?alt=media&token=be6f0a3d-7ecd-4cc3-a8a6-2c5acccab08d";

        String image1 ="https://firebasestorage.googleapis.com/v0/b/test-c434c.appspot.com/o/Screen%20Shot%202020-10-22%20at%2012.28.40%20PM.pn" +
                "g?alt=media&token=9bb719a5-ccae-492f-9091-db5a0c559465";
        String image2 = "https://firebasestorage.googleapis.com/v0/b/test-c434c.appspot.com/o/Screen%20Shot%202020-10-22%20at%2012.29" +
                ".01%20PM.png?alt=media&token=9c0f6a72-5fdd-4151-a37b-fba462b8fd16";
        String image3 ="https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/123.jpg?alt=media&token=0c082f00-3ea9-47c5-8f1f-62b0bc90a9aa";
        String image4 = "https://firebasestorage.googleapis.com/v0/b/test-c434c.appspot.com/o/Screen%20Shot%202020-10-22%20at%2012.29.12%20PM.png?alt=media&token=88b6f628-4878-4e05-8f89-c876c3fc4205";

        ImgArrayL.add(image);
        ImgArrayL.add(image3);
        ImgArrayL.add(image);

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

    public static ArrayList<ReportType> fillReportArrayL(Context context) {
        ArrayList<ReportType> reportTypesArrayL = new ArrayList<ReportType>();

        reportTypesArrayL.add(new ReportType(context.getResources().getString(R.string.out_of_stock),context.getResources().getString(R.string.out_of_stock_s),R.drawable.out_stock));
        reportTypesArrayL.add(new ReportType(context.getResources().getString(R.string.fack_price),context.getResources().getString(R.string.fack_price_s),R.drawable.fake_price));
        reportTypesArrayL.add(new ReportType(context.getResources().getString(R.string.pad_q),context.getResources().getString(R.string.pad_q_s),R.drawable.pad_q));

        return reportTypesArrayL;
    }

    public static boolean isAppOnForeground(Context context,String appPackageName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = appPackageName;
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                //                Log.e("app",appPackageName);
                return true;
            }
        }
        return false;
    }
}
