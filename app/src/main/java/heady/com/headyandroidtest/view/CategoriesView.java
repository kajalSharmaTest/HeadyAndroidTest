package heady.com.headyandroidtest.view;

import java.util.List;

import heady.com.headyandroidtest.model.Categories;

/**
 * Created by Kajal on 17/09/2018.
 */

public interface CategoriesView {

    void showCategories(List<Categories> categoris);
    void showSpinner(boolean state);
}
