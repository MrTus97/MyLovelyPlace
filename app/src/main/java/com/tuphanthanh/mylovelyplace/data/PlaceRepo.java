package com.tuphanthanh.mylovelyplace.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tuphanthanh.mylovelyplace.data.model.Category;
import com.tuphanthanh.mylovelyplace.data.model.DBUltils;
import com.tuphanthanh.mylovelyplace.data.model.Place;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;

public class PlaceRepo {

    private static PlaceRepo INSTANCE;
    private PlaceSqliteHelper placeSqliteHelper;


    private PlaceRepo(Context context){
        placeSqliteHelper = new PlaceSqliteHelper(context);
    }
    public static PlaceRepo getInstance(Context context){
        return (INSTANCE ==null) ? new PlaceRepo(context):INSTANCE;
    }
    public List<Category> getCategory(){
        List<Category> categoryList = new ArrayList<>();
        SQLiteDatabase database = placeSqliteHelper.getWritableDatabase();
        String[] column = {DBUltils.COLUMN_CATEGORY_ID,DBUltils.COLUMN_CATEGORY_NAME};
        Cursor cursor = database.query(DBUltils.CATEGORY_TBL_NAME,column,null,null,null,null,null);
        if (cursor!=null && cursor.getCount() > 0){
            while (cursor.moveToNext()){
                String categoryId= cursor.getString(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_CATEGORY_ID));
                String categoryName = cursor.getString(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_CATEGORY_NAME));
                categoryList.add(new Category(categoryId,categoryName));
            }
        }
        if (cursor!=null){
            cursor.close();
            database.close();
        }

        return  categoryList;
    }
    public List<Place> getPlaces(String cateID){
        List<Place> places = new ArrayList<>();
        SQLiteDatabase database = placeSqliteHelper.getReadableDatabase();
        String[] column = {
                DBUltils.COLUMN_PLACE_ID,
                DBUltils.COLUMN_PLACE_CATEGORY_ID,
                DBUltils.COLUMN_PLACE_NAME,
                DBUltils.COLUMN_PLACE_ADDRESS,
                DBUltils.COLUMN_PLACE_DESCRIPTION,
                DBUltils.COLUMN_PLACE_IMAGE,
                DBUltils.COLUMN_PLACE_LAT,
                DBUltils.COLUMN_PLACE_LNG
        };
        String selection = DBUltils.COLUMN_PLACE_CATEGORY_ID + "= ?";
        String[] selectionArgs = {cateID};
        Cursor cursor = database.query(DBUltils.PLACE_TBL_NAME,column,selection,selectionArgs,null,null,null);
        if (cursor!=null && cursor.getCount() > 0){
            while (cursor.moveToNext()){
                String placeId = cursor.getString(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_PLACE_ID));
                String categoryID = cursor.getString(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_PLACE_CATEGORY_ID));
                String placeName = cursor.getString(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_PLACE_NAME));
                String placeAddress = cursor.getString(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_PLACE_ADDRESS));
                String placeDescription = cursor.getString(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_PLACE_DESCRIPTION));
                byte[] placeImage = cursor.getBlob(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_PLACE_IMAGE));
                double placeLat = cursor.getDouble(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_PLACE_LAT));
                double placeLng = cursor.getDouble(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_PLACE_LNG));
                Place place = new Place.Builder()
                        .setPlaceId(placeId)
                        .setCategoryId(categoryID)
                        .setPlaceName(placeName)
                        .setPlaceAddress(placeAddress)
                        .setPlaceDecription(placeDescription)
                        .setPlaceImage(placeImage)
                        .setPlaceLat(placeLat)
                        .setPlaceLong(placeLng)
                        .build();
                places.add(place);
            }
        }
        if (cursor!=null){
            cursor.close();
            database.close();
        }

        return places;
    }
    public Place getPlace(String cateId,String plId){
        Place place = null;

        SQLiteDatabase database = placeSqliteHelper.getReadableDatabase();
        String[] column = {
                DBUltils.COLUMN_PLACE_ID,
                DBUltils.COLUMN_PLACE_CATEGORY_ID,
                DBUltils.COLUMN_PLACE_NAME,
                DBUltils.COLUMN_PLACE_ADDRESS,
                DBUltils.COLUMN_PLACE_DESCRIPTION,
                DBUltils.COLUMN_PLACE_IMAGE,
                DBUltils.COLUMN_PLACE_LAT,
                DBUltils.COLUMN_PLACE_LNG
        };
        String selection = DBUltils.COLUMN_CATEGORY_ID + "= ? AND" + DBUltils.COLUMN_PLACE_CATEGORY_ID+"=?";
        String[] selectionArgs = {plId,cateId};
        Cursor cursor = database.query(DBUltils.PLACE_TBL_NAME,column,selection,selectionArgs,null,null,null);
        if (cursor!=null && cursor.getCount() > 0){
        cursor.moveToFirst();
            String placeId = cursor.getString(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_PLACE_ID));
            String categoryID = cursor.getString(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_PLACE_CATEGORY_ID));
            String placeName = cursor.getString(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_PLACE_NAME));
            String placeAddress = cursor.getString(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_PLACE_ADDRESS));
            String placeDescription = cursor.getString(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_PLACE_DESCRIPTION));
            byte[] placeImage = cursor.getBlob(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_PLACE_IMAGE));
            double placeLat = cursor.getDouble(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_PLACE_LAT));
            double placeLng = cursor.getDouble(cursor.getColumnIndexOrThrow(DBUltils.COLUMN_PLACE_LNG));
            place = new Place.Builder()
                    .setPlaceId(placeId)
                    .setCategoryId(categoryID)
                    .setPlaceName(placeName)
                    .setPlaceAddress(placeAddress)
                    .setPlaceDecription(placeDescription)
                    .setPlaceImage(placeImage)
                    .setPlaceLat(placeLat)
                    .setPlaceLong(placeLng)
                    .build();
        }
        if (cursor!=null){
            cursor.close();
            database.close();
        }
        return place;
    }
    public void insert(Place place){
        SQLiteDatabase database = placeSqliteHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBUltils.COLUMN_PLACE_ID,place.getPlaceId());
        contentValues.put(DBUltils.COLUMN_PLACE_CATEGORY_ID,place.getCategoryId());
        contentValues.put(DBUltils.COLUMN_PLACE_NAME,place.getPlaceName());
        contentValues.put(DBUltils.COLUMN_PLACE_ADDRESS,place.getPlaceAddress());
        contentValues.put(DBUltils.COLUMN_PLACE_DESCRIPTION,place.getPlaceDecription());
        contentValues.put(DBUltils.COLUMN_PLACE_IMAGE,place.getPlaceImage());
        contentValues.put(DBUltils.COLUMN_PLACE_LAT,place.getPlaceLat());
        contentValues.put(DBUltils.COLUMN_PLACE_LNG,place.getPlaceLong());
        database.insert(DBUltils.PLACE_TBL_NAME,null,contentValues);
        database.close();
    }
    public void update(Place place){
        SQLiteDatabase database = placeSqliteHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBUltils.COLUMN_PLACE_ID,place.getPlaceId());
        contentValues.put(DBUltils.COLUMN_PLACE_CATEGORY_ID,place.getCategoryId());
        contentValues.put(DBUltils.COLUMN_PLACE_NAME,place.getPlaceName());
        contentValues.put(DBUltils.COLUMN_PLACE_ADDRESS,place.getPlaceAddress());
        contentValues.put(DBUltils.COLUMN_PLACE_DESCRIPTION,place.getPlaceDecription());
        contentValues.put(DBUltils.COLUMN_PLACE_IMAGE,place.getPlaceImage());
        contentValues.put(DBUltils.COLUMN_PLACE_LAT,place.getPlaceLat());
        contentValues.put(DBUltils.COLUMN_PLACE_LNG,place.getPlaceLong());
        String selection = DBUltils.COLUMN_PLACE_ID + "=?";
        String[] selectionArgs = {place.getPlaceId()};
        database.update(DBUltils.PLACE_TBL_NAME,contentValues,selection,selectionArgs);
        database.close();
    }
    public void delete(String plId){
        SQLiteDatabase database = placeSqliteHelper.getWritableDatabase();
        String selection = DBUltils.COLUMN_PLACE_ID + "=?";
        String[] selectionArgs = {plId};
        database.delete(DBUltils.PLACE_TBL_NAME,selection,selectionArgs);
        database.close();
    }
}
