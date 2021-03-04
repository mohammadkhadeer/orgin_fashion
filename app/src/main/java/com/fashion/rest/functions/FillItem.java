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

}
