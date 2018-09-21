package heady.com.headyandroidtest.view;

import java.util.List;

import heady.com.headyandroidtest.model.Category;

/**
 * Created by Kajal on 17/09/2018.
 * Interface to pass Category data from presenter to View.
 */

public interface CategoryView {

    void showSpinner(boolean state);
    void showInitialData();
}
