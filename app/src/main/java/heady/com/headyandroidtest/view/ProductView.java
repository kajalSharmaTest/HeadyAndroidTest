package heady.com.headyandroidtest.view;

import java.util.List;

import heady.com.headyandroidtest.model.Products;

/**
 * Created by Kajal on 17/09/2018.
 * Interface to pass Product data from presenter to View.
 */

public interface ProductView {

    void showProducts(List<Products> products);
    void showSpinner(boolean state);
}

