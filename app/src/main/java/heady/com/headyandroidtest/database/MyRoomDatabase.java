package heady.com.headyandroidtest.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import heady.com.headyandroidtest.model.Categories;
import heady.com.headyandroidtest.model.Products;
import heady.com.headyandroidtest.model.Rankings;

/**
 * Created by Kajal on 9/17/2018.
 */

@Database(entities ={
        Categories.class,
        Products.class,
        Rankings.class}, version = 1,exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {

        public  abstract CategoriesDao categoriesDao();
        public  abstract ProductsDao productsDao();
        public abstract  RankingsDao rankingsDao();

        private static volatile MyRoomDatabase INSTANCE;

        public static MyRoomDatabase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (MyRoomDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                MyRoomDatabase.class, "mdatabase")
                                .build();
                    }
                }
            }
            return INSTANCE;
        }


    }
