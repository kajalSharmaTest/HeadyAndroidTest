package heady.com.headyandroidtest.view;

import java.util.List;

import heady.com.headyandroidtest.model.Categories;

/**
 * Created by Kajal on 17/09/2018.
 * Interface to pass categoried data from presenter to View.
 */

public interface CategoriesView {

    void showCategories(List<Categories> categoris);
    void showSpinner(boolean state);
}
