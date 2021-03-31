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


    public static final String TABLE_NEIGHBORHOOD="neighborhoodEn_table";
    public static final String COL_C_ID="ID";
    public static final String COL_SERVER_ID="COL_SERVER_ID";
    public static final String CITY_EN="CITY_EN";
    public static final String CITY_LOCAL="CITY_LOCAL";
    public static final String NEIGHBORHOOD_EN="NEIGHBORHOOD_EN";
    public static final String NEIGHBORHOOD_LOCAL="NEIGHBORHOOD_LOCAL";

    public static final String TABLE_CITY="city_table";
    public static final String COL_CITY_ID="ID";
    public static final String COL_CITY_SERVER_ID="COL_CITY_SERVER_ID";
    public static final String CITY_NAME_EN="CITY_NAME_EN";
    public static final String CITY_NAME_LOCAL="CITY_NAME_LOCAL";

    public static final String TABLE_FAVORITE="favorite_table";
    public static final String COL_FAVORITE_ID="ID";
    public static final String COL_ITEM_SERVER_ID="COL_ITEM_SERVER_ID";
    public static final String COL_SUB_CAT_ID="COL_SUB_CAT_ID";
    public static final String COL_CATEGORY_ID="COL_CATEGORY_ID";

    public static final String TABLE_NOTIFICATION="notification_table";
    public static final String COL_NOTIFICATION_ID="COL_NOTIFICATION_ID";
    public static final String COL_NOTIFICATION_IMAGE="COL_NOTIFICATION_IMAGE";
    public static final String COL_NOTIFICATION_TITLE_EN="COL_NOTIFICATION_TITLE_EN";
    public static final String COL_NOTIFICATION_TITLE_LOCAL="COL_NOTIFICATION_TITLE_LOCAL";
    public static final String COL_NOTIFICATION_BODY_EN="COL_NOTIFICATION_BODY_EN";
    public static final String COL_NOTIFICATION_BODY_LOCAL="COL_NOTIFICATION_BODY_LOCAL";
    public static final String COL_NOTIFICATION_TYPE="COL_NOTIFICATION_TYPE";
    public static final String COL_NOTIFICATION_ITEM_ID="COL_NOTIFICATION_ITEM_ID";
    public static final String TIME_STAMP="TIME_STAMP";
    public static final String OPTIONAL_EN="OPTIONAL_EN";
    public static final String OPTIONAL_LOCAL="OPTIONAL_LOCAL";
    public static final String COL_NOTIFICATION_OPEN_OR_NOT="COL_NOTIFICATION_OPEN_OR_NOT";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_CART +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IMAGE_ID TEXT" + ",IMAGE_PATH TEXT" + ",NAME TEXT" + ",DES TEXT"  + ",PRICE TEXT"  + ",PRICE_N TEXT"  + ",PRICE_O TEXT" + ",NUMBER_OF_ITEM_IN_THE_CART TEXT)");
        db.execSQL("create table "+TABLE_NEIGHBORHOOD +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "COL_SERVER_ID TEXT" + ",CITY_EN TEXT" + ",CITY_LOCAL TEXT" + ",NEIGHBORHOOD_EN TEXT" + ",NEIGHBORHOOD_LOCAL TEXT)");
        db.execSQL("create table "+TABLE_CITY +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "COL_CITY_SERVER_ID TEXT" + ",CITY_NAME_EN TEXT" +",CITY_NAME_LOCAL TEXT)");
        db.execSQL("create table "+TABLE_FAVORITE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "COL_ITEM_SERVER_ID TEXT" + ",COL_SUB_CAT_ID TEXT" +",COL_CATEGORY_ID TEXT)");
        db.execSQL("create table "+TABLE_NOTIFICATION +" (COL_NOTIFICATION_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "COL_NOTIFICATION_IMAGE TEXT" + ",COL_NOTIFICATION_TITLE_EN TEXT"+ ",COL_NOTIFICATION_TITLE_LOCAL TEXT" +",COL_NOTIFICATION_BODY_EN TEXT" +",COL_NOTIFICATION_BODY_LOCAL TEXT"
                +",COL_NOTIFICATION_TYPE TEXT" +",COL_NOTIFICATION_ITEM_ID TEXT" +",TIME_STAMP TEXT"+",OPTIONAL_EN TEXT"+",OPTIONAL_LOCAL TEXT" +",COL_NOTIFICATION_OPEN_OR_NOT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CART);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NEIGHBORHOOD);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CITY);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_FAVORITE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NOTIFICATION);

        onCreate(db);
    }

    ///////////////////////insert data//////////////////////////////////////////

    public boolean insertNotifications(String image_url,String title_en,String title_local,String body_en
            ,String body_local,String type,String item_id,String timeStamp,String optional_en,String optional_local,String openOrNotYet)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NOTIFICATION_IMAGE,image_url);
        contentValues.put(COL_NOTIFICATION_TITLE_EN,title_en);
        contentValues.put(COL_NOTIFICATION_TITLE_LOCAL,title_local);
        contentValues.put(COL_NOTIFICATION_BODY_EN,body_en);
        contentValues.put(COL_NOTIFICATION_BODY_LOCAL,body_local);
        contentValues.put(COL_NOTIFICATION_TYPE,type);
        contentValues.put(COL_NOTIFICATION_ITEM_ID,item_id);
        contentValues.put(TIME_STAMP,timeStamp);
        contentValues.put(OPTIONAL_EN,optional_en);
        contentValues.put(OPTIONAL_LOCAL,optional_local);
        contentValues.put(COL_NOTIFICATION_OPEN_OR_NOT,openOrNotYet);

        long result= db.insert(TABLE_NOTIFICATION,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

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

    public boolean insertNeighborhood(String idServer,String cityEn,String cityLocal,String neighborhoodEn
            ,String neighborhoodLocal)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_SERVER_ID,idServer);
        contentValues.put(CITY_EN,cityEn);
        contentValues.put(CITY_LOCAL,cityLocal);
        contentValues.put(NEIGHBORHOOD_EN,neighborhoodEn);
        contentValues.put(NEIGHBORHOOD_LOCAL,neighborhoodLocal);

        long result= db.insert(TABLE_NEIGHBORHOOD,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertCities(String idServer,String cityEn,String cityLocal)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_CITY_SERVER_ID,idServer);
        contentValues.put(CITY_NAME_EN,cityEn);
        contentValues.put(CITY_NAME_LOCAL,cityLocal);

        long result= db.insert(TABLE_CITY,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertItemToFav(String item_id,String sub_cat_id,String cat_id)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ITEM_SERVER_ID,item_id);
        contentValues.put(COL_SUB_CAT_ID,sub_cat_id);
        contentValues.put(COL_CATEGORY_ID,cat_id);

        long result= db.insert(TABLE_FAVORITE,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    ////////////////////tester to check if table EXISTS/////////

//    public boolean doesTableExist() {
//        SQLiteDatabase db =this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + TABLE_CITY + "'", null);
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


    public Cursor descendingNotifications(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NOTIFICATION, null, null,
                null, null, null, COL_NOTIFICATION_ID + " DESC", String.valueOf(15));
        return cursor;
    }

    public Cursor descendingCartItems(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_CART, null, null,
                null, null, null, COL_ID + " DESC", null);
        return cursor;
    }

    public Cursor descendingNeighborhood(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NEIGHBORHOOD, null, null,
                null, null, null, COL_C_ID + " DESC", null);
        return cursor;
    }

    public Cursor descendingCities(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_CITY, null, null,
                null, null, null, COL_CITY_ID + " DESC", null);
        return cursor;
    }

    public Cursor descendingFav(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_FAVORITE, null, null,
                null, null, null, COL_FAVORITE_ID + " DESC", null);
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

    public void updateNotificationStatus(String open,String timeStamp)
    {
        //to change from not open to open
        // if open 1 not open 0
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NOTIFICATION_OPEN_OR_NOT,open);

        db.update(TABLE_NOTIFICATION,contentValues," TIME_STAMP = ?",new String[] {timeStamp});
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

    public Integer deleteItemFromFav(String itemID){
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete(TABLE_FAVORITE, " COL_ITEM_SERVER_ID = ?",new String[] {itemID});
    }

    public Integer deleteNotification(String timeStamp){
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete(TABLE_NOTIFICATION, " TIME_STAMP = ?",new String[] {timeStamp});
    }
    //////////////////////////////////////delete data "All line" ////////////////

    public void deleteAllNeighborhood(){
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL("DELETE FROM neighborhoodEn_table"); //delete all rows in a table
        db.close();
    }

    public void deleteAllCities(){
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL("DELETE FROM city_table"); //delete all rows in a table
        db.close();
    }

    public void deleteAllCartItems(){
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL("DELETE FROM cart_table"); //delete all rows in a table
        db.close();
    }

    public void deleteAllFavItems(){
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL("DELETE FROM favorite_table"); //delete all rows in a table
        db.close();
    }

    public void deleteAllNotifications(){
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL("DELETE FROM notification_table"); //delete all rows in a table
        db.close();
    }

}
