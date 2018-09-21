package heady.com.headyandroidtest.model;

/**
 * Created by Kajal on 17/09/2018.
 */

public class Category
{
    private Rankings[] rankings;

    private Categories[] categories;

    public Rankings[] getRankings ()
    {
        return rankings;
    }

    public void setRankings (Rankings[] rankings)
    {
        this.rankings = rankings;
    }

    public Categories[] getCategories ()
    {
        return categories;
    }

    public void setCategories (Categories[] categories)
    {
        this.categories = categories;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [rankings = "+rankings+", categories = "+categories+"]";
    }
}
