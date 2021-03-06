package heady.com.headyandroidtest.model;

/**
 * Created by Kajal on 17/09/2018.
 * Model class and Entity to be stored in database as an object.
 *  Object to retrieve Tax object from server.
 */

public class Tax
{
    private String name;

    private String value;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", value = "+value+"]";
    }
}
