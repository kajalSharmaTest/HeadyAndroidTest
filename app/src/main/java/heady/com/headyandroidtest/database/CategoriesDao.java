package heady.com.headyandroidtest.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import heady.com.headyandroidtest.model.Categories;
import heady.com.headyandroidtest.model.Products;

/**
 * Created by Kajal on 9/17/2018.
 */


@Dao
public interface CategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Categories categories);

    @Query("DELETE FROM categories")
    void deleteAll();

    @Query("SELECT * from categories ORDER BY id ASC")
    List<Categories> getAllCategories();

    @Query("SELECT * from categories WHERE id = :catId ORDER BY id ASC")
    List<Categories> getProductsByCatId(int catId);


}
