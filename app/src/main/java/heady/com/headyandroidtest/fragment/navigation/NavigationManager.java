package heady.com.headyandroidtest.fragment.navigation;

import java.util.ArrayList;

import heady.com.headyandroidtest.model.Products;


/**
 * Created by Kajal on 9/17/2018.
 * Interface to maintain fragment navigations. It is implemented in FragmentNavigationManager.class.
 */


public interface NavigationManager {

    void showProductFragment(ArrayList<Products> productList);

    void showProductDetailFragment(Products product);


}
