package com.blue_b.rest.database;

import android.content.Context;
import android.database.Cursor;

import com.blue_b.rest.model.ItemInDB;

import java.util.ArrayList;

import static com.blue_b.rest.database.DataBaseInstance.getDataBaseInstance;

public class Read {

    public static ArrayList<ItemInDB> getAllItemFromCart(Context context) {

        ArrayList<ItemInDB> itemInDBArrayL = new ArrayList<ItemInDB>();

        Cursor res = getDataBaseInstance(context).descendingCartItems();

        while (res.moveToNext()) {
//            Log.d("TAG","image id "+res.getString(1).replace("\n", ""));
//            Log.d("TAG","image path "+res.getString(2).replace("\n", ""));
//            Log.d("TAG"," name "+res.getString(3).replace("\n", ""));
//
//            Log.d("TAG"," des "+res.getString(4).replace("\n", ""));
//            Log.d("TAG"," price "+res.getString(5).replace("\n", ""));
//            Log.d("TAG"," price New "+res.getString(6).replace("\n", ""));
//
//            Log.d("TAG"," price old "+res.getString(7).replace("\n", ""));
//            Log.d("TAG"," Number of items "+res.getString(8).replace("\n", ""));
            ItemInDB itemInDB = new ItemInDB(
                    Integer.parseInt(res.getString(1).replace("\n", ""))
                    , Integer.parseInt(res.getString(1).replace("\n", ""))
                    , res.getString(2).replace("\n", "")
                    , res.getString(3).replace("\n", "")
                    , res.getString(4).replace("\n", "")
                    , Double.parseDouble(res.getString(5).replace("\n", ""))
                    , Double.parseDouble(res.getString(6).replace("\n", ""))
                    , Double.parseDouble(res.getString(7).replace("\n", ""))
                    , Integer.parseInt(res.getString(8).replace("\n", ""))
            );
            itemInDBArrayL.add(itemInDB);
        }

        return itemInDBArrayL;
    }
}
