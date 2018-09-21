package heady.com.headyandroidtest.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import heady.com.headyandroidtest.model.Rankings;

/**
 * Created by Kajal on 9/17/2018.
 * Dao to handle rankings table database querries
 */

    @Dao
    public interface RankingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(Rankings rankings);

        @Query("DELETE FROM rankings")
        void deleteAll();

        @Query("SELECT * from rankings")
        List<Rankings> getAllRankings();
    }
