package com.tuphanthanh.mylovelyplace.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tuphanthanh.mylovelyplace.data.model.DBUltils;

public class PlaceSqliteHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "PLACE";
    private static final int DB_VERSION = 1;
    private static final String CREATE_PLACE_TABLE_SQL="" +
            "CREATE TABLE "+ DBUltils.PLACE_TBL_NAME +"("
            +DBUltils.COLUMN_PLACE_ID + " "+ DBUltils.TEXT_DATA_TYPE + " " + DBUltils.PRIMARY_KEY + ", "
            +DBUltils.COLUMN_PLACE_CATEGORY_ID + " "+ DBUltils.TEXT_DATA_TYPE + " " + DBUltils.NOT_NULL + ", "
            +DBUltils.COLUMN_PLACE_NAME + " "+ DBUltils.TEXT_DATA_TYPE + " " + DBUltils.NOT_NULL + ", "
            +DBUltils.COLUMN_PLACE_ADDRESS + " "+ DBUltils.TEXT_DATA_TYPE + " " + DBUltils.NOT_NULL + ", "
            +DBUltils.COLUMN_PLACE_DESCRIPTION + " "+ DBUltils.TEXT_DATA_TYPE + " " + DBUltils.NOT_NULL + ", "
            +DBUltils.COLUMN_PLACE_IMAGE + " "+ DBUltils.BLOB_DATA_TYPE + " " + DBUltils.NOT_NULL + ", "
            +DBUltils.COLUMN_PLACE_LAT + " "+ DBUltils.REAL_DATA_TYPE + " " + DBUltils.NOT_NULL + ", "
            +DBUltils.COLUMN_PLACE_LNG + " "+ DBUltils.REAL_DATA_TYPE + " " + DBUltils.NOT_NULL
            + ")";
    private static final String CREATE_CATEGORY_TABLE_SQL =
            "CREATE TABLE " +DBUltils.CATEGORY_TBL_NAME + "("
                    +DBUltils.COLUMN_CATEGORY_ID + " " + DBUltils.TEXT_DATA_TYPE + " " + DBUltils.PRIMARY_KEY +", "
                    +DBUltils.COLUMN_CATEGORY_NAME + " " + DBUltils.TEXT_DATA_TYPE + " " + DBUltils.NOT_NULL

                    +")";
    private static final String INSERT_CATEGORY_SQL=
            "INSERT INTO " + DBUltils.CATEGORY_TBL_NAME + "("+DBUltils.COLUMN_CATEGORY_ID +" , "+DBUltils.COLUMN_CATEGORY_NAME +") VALUES"
                    +"('1','Restaurant')," + "('2','Cinema'),"+"('3','Fashion'),"+"('4','ATM')";

    public PlaceSqliteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public PlaceSqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PLACE_TABLE_SQL);
        sqLiteDatabase.execSQL(CREATE_CATEGORY_TABLE_SQL);
        sqLiteDatabase.execSQL(INSERT_CATEGORY_SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
