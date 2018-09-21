package heady.com.headyandroidtest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Kajal on 17/09/2018.
 * Model class and Entity to be stored in database as an object.
 * Common object to retrieve RankingProducts object from server and to be stored in dabase also as object.
 */

@Entity(tableName = "ranking_products")
public class RankingProducts {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    String id;

    @NonNull
    @ColumnInfo(name = "view_count")
    String view_count;

    @NonNull
    @ColumnInfo(name = "order_count")
    String order_count;

    @NonNull
    @ColumnInfo(name = "shares")
    String shares;

    public String getView_count() {
        return view_count;
    }

    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

    public String getOrder_count() {
        return order_count;
    }

    public void setOrder_count(String order_count) {
        this.order_count = order_count;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }

    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

}
