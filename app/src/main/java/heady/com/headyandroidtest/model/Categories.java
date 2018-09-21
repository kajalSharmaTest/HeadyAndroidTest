package heady.com.headyandroidtest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import heady.com.headyandroidtest.util.MyTypeConverters;

/**
 * Created by Kajal on 17/09/2018.
 */
@Entity(tableName = "categories")
public class Categories
{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "child_categories")
    @TypeConverters(MyTypeConverters.class)
    private String[] child_categories;

    @NonNull
    //@Embedded(prefix = "products")
    @TypeConverters(MyTypeConverters.class)
    @ColumnInfo(name = "products")
    private Products[] products;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String[] getChild_categories ()
    {
        return child_categories;
    }

    public void setChild_categories (String[] child_categories)
    {
        this.child_categories = child_categories;
    }

    public Products[] getProducts ()
    {
        return products;
    }

    public void setProducts (Products[] products)
    {
        this.products = products;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", name = "+name+", child_categories = "+", products = "+products+"]";
    }
}

