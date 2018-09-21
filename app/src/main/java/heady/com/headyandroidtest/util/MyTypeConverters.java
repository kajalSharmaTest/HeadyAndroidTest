package heady.com.headyandroidtest.util;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import heady.com.headyandroidtest.model.Products;
import heady.com.headyandroidtest.model.RankingProducts;
import heady.com.headyandroidtest.model.Variants;

/**
 * Created by Kajal on 9/19/2018.
 */

public  class MyTypeConverters {
   static Gson gson = new Gson();



    @TypeConverter
    public static String[] stringToSomeObjectList(String data) {
        if (data == null) {
            return new String[0];
        }

        Type listType = new TypeToken<String[]>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static ArrayList stringToArrayList(String data) {
        if (data == null) {
            return null;
        }

        Type listType = new TypeToken<List<ArrayList>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(Products[] someObjects) {
        return gson.toJson(someObjects);
    }


    @TypeConverter
    public Products[] toProductArray(String products) {
        if (products == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Products[]>() {
        }.getType();
        Products[] product_array = gson.fromJson(products, type);
        return product_array;
    }

    @TypeConverter
    public RankingProducts[] toRankingProductArray(int products) {
        if (products == 0) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<RankingProducts[]>() {
        }.getType();
        RankingProducts[] ranking_product_array = gson.fromJson(String.valueOf(products), type);
        return ranking_product_array;
    }

    @TypeConverter
    public static String someObjectListToString(RankingProducts[] someObjects) {
      //  for(RankingProducts prod : someObjects) {
        if(someObjects == null){
            return null;
        }
           return  gson.toJson(someObjects).trim();
       // }
    }

    @TypeConverter
    public RankingProducts[] toRankingProductArray(String products) {
        if (products == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<RankingProducts[]>() {
        }.getType();
        RankingProducts[] ranking_product_array = gson.fromJson(products, type);
        return ranking_product_array;
    }

    @TypeConverter
    public static int someObjectListToString(RankingProducts someObjects) {
        return Integer.parseInt(gson.toJson(someObjects));
    }
    @TypeConverter
    public Variants[] toVariantArray(String data) {
        if (data == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Variants[]>() {
        }.getType();
        Variants variant[] = gson.fromJson(String.valueOf(data), type);
        return variant;
    }

    @TypeConverter
    public static String someObjectListToString(Variants[] someObjects) {
        return gson.toJson(someObjects);
    }

    @TypeConverter
    public static String someObjectListToString(String[] someObjects) {
        return gson.toJson(someObjects);
    }

//    @TypeConverter
//    public static String someObjectListToString(RankingProducts[] someObjects) {
//        return gson.toJson(someObjects);
//    }
}
