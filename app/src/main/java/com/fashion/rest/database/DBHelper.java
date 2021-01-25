package com.fashion.rest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
in database not allowed to save object then well
save common item we need to show in fires
well save all type
1.USER_ADS 2.FAVOURITE 3.CALL_ITEMS 4.WATCHED_ITEMS 5.SEARCH_ITEMS
this well save as item type in data base
well use item saved to create suggested items to user depend
item table
item table we saved before user used app can tke its init items
*/

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="fashion.db";

    public static final String TABLE_CART="cart_table";
    public static final String COL_ID="ID";
    public static final String COL_IMAGE_ID="IMAGE_ID";
    public static final String COL_IMAGE_PATH="IMAGE_PATH";
    public static final String COL_NAME="NAME";
    public static final String COL_DES="DES";
    public static final String COL_PRICE="PRICE";
    public static final String COL_PRICE_NEW="PRICE_N";
    public static final String COL_PRICE_OLD="PRICE_O";
    public static final String COL_NUMBER_OF_ITEM_IN_THE_CART="NUMBER_OF_ITEM_IN_THE_CART";


    public static final String TABLE_CITY="city_table";
    public static final String COL_C_ID="ID";
    public static final String CITY_EN="CITY_EN";
    public static final String CITY_LOCAL="CITY_LOCAL";
    public static final String NEIGHBORHOOD_EN="NEIGHBORHOOD_EN";
    public static final String NEIGHBORHOOD_LOCAL="NEIGHBORHOOD_LOCAL";




    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_CART +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IMAGE_ID TEXT" + ",IMAGE_PATH TEXT" + ",NAME TEXT" + ",DES TEXT"  + ",PRICE TEXT"  + ",PRICE_N TEXT"  + ",PRICE_O TEXT" + ",NUMBER_OF_ITEM_IN_THE_CART TEXT)");
        db.execSQL("create table "+TABLE_CITY +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "CITY_EN TEXT" + ",CITY_LOCAL TEXT" + ",NEIGHBORHOOD_EN TEXT" + ",NEIGHBORHOOD_LOCAL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CART);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CITY);

        onCreate(db);
    }

    ///////////////////////insert data//////////////////////////////////////////

    public boolean insertItemToCart(String imageID,String imagePath,String name
            ,String des,String price,String priceN,String priceO,String numberOfItems)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_IMAGE_ID,imageID);
        contentValues.put(COL_IMAGE_PATH,imagePath);
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_DES,des);
        contentValues.put(COL_PRICE,price);
        contentValues.put(COL_PRICE_NEW,priceN);
        contentValues.put(COL_PRICE_OLD,priceO);
        contentValues.put(COL_NUMBER_OF_ITEM_IN_THE_CART,numberOfItems);

        long result= db.insert(TABLE_CART,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertCities(String cityEn,String cityLocal,String neighborhoodEn
            ,String neighborhoodLocal)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CITY_EN,cityEn);
        contentValues.put(CITY_LOCAL,cityLocal);
        contentValues.put(NEIGHBORHOOD_EN,neighborhoodEn);
        contentValues.put(NEIGHBORHOOD_LOCAL,neighborhoodLocal);

        long result= db.insert(TABLE_CITY,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    ////////////////////tester to check if table EXISTS/////////

//    public boolean doesTableExist() {
//        SQLiteDatabase db =this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + TABLE_FOLLOWERS + "'", null);
//
//        if (cursor != null) {
//            if (cursor.getCount() > 0) {
//                cursor.close();
//                return true;
//            }
//            cursor.close();
//        }
//        return false;
//    }


    //////////////////////////to get data /////////////////////



    public Cursor descendingCartItems(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_CART, null, null,
                null, null, null, COL_ID + " DESC", null);
        return cursor;
    }

    public Cursor descendingCities(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_CITY, null, null,
                null, null, null, COL_ID + " DESC", null);
        return cursor;
    }

    //////////////////////////////////////update/////////////////////

    public void updateItemNumberInCart(String numberOfItems,String itemName)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NUMBER_OF_ITEM_IN_THE_CART,numberOfItems);

        db.update(TABLE_CART,contentValues," NAME = ?",new String[] {itemName});

//        String strSQL = "UPDATE cart_table SET NUMBER_OF_ITEM_IN_THE_CART = numberOfItems WHERE NAME = "+ itemName;
//
//        db.execSQL(strSQL);
    }

    //////////////////////////////////////get single object//////////

    public Cursor getItemNumber(String itemName)
    {
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_CART + " WHERE " + COL_NAME + " = ? ", new String[]{itemName});
        cursor.moveToFirst();

        return cursor;
    }


    //////////////////////////////////////delete data "single row" ////////////////

    public Integer deleteItemFromCart(String itemName){
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete(TABLE_CART, " NAME = ?",new String[] {itemName});
    }

    //////////////////////////////////////delete data "All line" ////////////////

    public void deleteAllCities(){
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL("DELETE FROM cart_table"); //delete all rows in a table
        db.close();
    }

    public void deleteAllCartItems(){
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL("DELETE FROM city_table"); //delete all rows in a table
        db.close();
    }

}
