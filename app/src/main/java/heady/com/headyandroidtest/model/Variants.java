package heady.com.headyandroidtest.model;

/**
 * Created by Kajal on 17/09/2018.
 * Model class and Entity to be stored in database as an object.
 * Common object to retrieve Variants object from server .
 */

public class Variants
{
    private String id;

    private String price;

    private String color;

    private String size;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getColor ()
    {
        return color;
    }

    public void setColor (String color)
    {
        this.color = color;
    }

    public String getSize ()
    {
        return size;
    }

    public void setSize (String size)
    {
        this.size = size;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", price = "+price+", color = "+color+", size = "+size+"]";
    }
}