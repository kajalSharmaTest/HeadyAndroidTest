package heady.com.headyandroidtest.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import heady.com.headyandroidtest.model.Products;

/**
 * Created by Kajal on 9/17/2018.
 * Dao to handle Products table database querries
 */

    @Dao
    public interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(Products products);

        @Query("DELETE FROM products")
        void deleteAll();

        @Query("SELECT * from products")
        List<Products> getAllProducts();

        @Query("SELECT * from products WHERE id IN(:prodIds)")
       List<Products> getProductsByRanking(int[] prodIds);
    }