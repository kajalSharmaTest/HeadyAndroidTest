package heady.com.headyandroidtest.fragment.navigation;

import java.util.ArrayList;

import heady.com.headyandroidtest.model.Products;


/**
 * Created by Kajal on 9/17/2018.
 */

public interface NavigationManager {

    void showProductFragment(ArrayList<Products> productList);

    void showProductDetailFragment(Products product);


}
