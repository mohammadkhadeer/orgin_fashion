package com.orgin_fashion.rest.functions;

import android.content.Context;
import android.database.Cursor;

import com.orgin_fashion.rest.model.Area;
import com.orgin_fashion.rest.model.City;
import com.orgin_fashion.rest.model.NotificationModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.orgin_fashion.rest.database.DataBaseInstance.getDataBaseInstance;

public class FillItem {

    public static ArrayList<String> itemsIdInFavorite(Context context) {
        ArrayList<String> listArrayL = new ArrayList<String>();

        Cursor res = getDataBaseInstance(context).descendingFav();

        while (res.moveToNext()) {
            // idOnServer col 1 ... city_en_name col 2 ... city_local_name col 3 ... area_en_name col 4 ... area_local_name col 5
            listArrayL.add(res.getString(1).replace("\n", ""));
        }

        return listArrayL;
    }

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

    public static ArrayList<NotificationModel> fillNotifications(Context context) {
        ArrayList<NotificationModel> notificationModelsArrayL = new ArrayList<NotificationModel>();

        Cursor res = getDataBaseInstance(context).descendingNotifications();

        while (res.moveToNext()) {
            // idOnServer col 1 ... city_en_name col 2 ... city_local_name col 3 ... area_en_name col 4 ... area_local_name col 5
//            Log.i("TAG","Col 1: "+res.getString(1).replace("\n", ""));
//            Log.i("TAG","Col 2: "+res.getString(2).replace("\n", ""));
//            Log.i("TAG","Col 3: "+res.getString(3).replace("\n", ""));
//            Log.i("TAG","Col 4: "+res.getString(4).replace("\n", ""));
//            Log.i("TAG","Col 5: "+res.getString(5).replace("\n", ""));
//            Log.i("TAG","Col 6: "+res.getString(6).replace("\n", ""));
//            Log.i("TAG","Col 7: "+res.getString(7).replace("\n", ""));
//            Log.i("TAG","Col 8: "+res.getString(8).replace("\n", ""));
//            Log.i("TAG","Col 9: "+res.getString(8).replace("\n", ""));
//            Log.i("TAG","Col 10: "+res.getString(10).replace("\n", ""));
//            Log.i("TAG","Col 11: "+res.getString(11).replace("\n", ""));

            notificationModelsArrayL.add(new NotificationModel(
                    res.getString(1).replace("\n", ""),
                    res.getString(2).replace("\n", ""),
                    res.getString(3).replace("\n", ""),
                    res.getString(4).replace("\n", ""),
                    res.getString(5).replace("\n", ""),
                    res.getString(6).replace("\n", ""),
                    res.getString(7).replace("\n", ""),
                    res.getString(8).replace("\n", ""),
                    res.getString(9).replace("\n", ""),
                    res.getString(10).replace("\n", ""),
                    res.getString(11).replace("\n", "")
            ));
        }

        return notificationModelsArrayL;
    }

}
