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
 */

@Entity(tableName = "products")
public class Products
{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @NonNull
   // @ColumnInfo(name = "tax")
     @Embedded(prefix = "tax")
    private Tax tax;

    @NonNull
    @ColumnInfo(name = "date_added")
    private String date_added;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "variants")
  //  @Embedded(prefix = "variants")
    @TypeConverters(MyTypeConverters.class)
    private Variants[] variants;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Tax getTax ()
    {
        return tax;
    }

    public void setTax (Tax tax)
    {
        this.tax = tax;
    }

    public String getDate_added ()
    {
        return date_added;
    }

    public void setDate_added (String date_added)
    {
        this.date_added = date_added;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Variants[] getVariants ()
    {
        return variants;
    }

    public void setVariants (Variants[] variants)
    {
        this.variants = variants;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", tax = "+", date_added = "+date_added+", name = "+name+", variants = "+"]";
    }
}