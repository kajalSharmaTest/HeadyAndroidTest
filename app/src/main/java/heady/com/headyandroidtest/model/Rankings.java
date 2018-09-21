package heady.com.headyandroidtest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import heady.com.headyandroidtest.util.MyTypeConverters;

/**
 * Created by Kajal on 17/09/2018.
 * Model class and Entity to be stored in database as an object.
 * Common object to retrieve Rankings object from server and to be stored in dabase also as object.
 */

@Entity(tableName = "rankings")
public class Rankings
{
    // @Embedded(prefix = "ranking_products")
    @NonNull

    @ColumnInfo(name = "ranking_products")
    @TypeConverters(MyTypeConverters.class)
    private RankingProducts[] products;

    @NonNull
    @ColumnInfo(name = "ranking")
    @PrimaryKey
    private String ranking;

    public RankingProducts[] getProducts ()
    {
        return products;
    }

    public void setProducts (RankingProducts[] products)
    {
        this.products = products;
    }

    public String getRanking ()
    {
        return ranking;
    }

    public void setRanking (String ranking)
    {
        this.ranking = ranking;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [products = "+products+", ranking = "+ranking+"]";
    }
}