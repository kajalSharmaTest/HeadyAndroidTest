package heady.com.headyandroidtest.fragment.navigation;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import heady.com.headyandroidtest.BuildConfig;
import heady.com.headyandroidtest.MainActivity;
import heady.com.headyandroidtest.R;
import heady.com.headyandroidtest.fragment.ProductDetailFragment;
import heady.com.headyandroidtest.fragment.ProductFragment;
import heady.com.headyandroidtest.model.Products;



/**
 * Created by Kajal on 9/17/2018.
 * Fragment Navigation mainager to handle all fragments transitions at common place.
 * implements NavigationManager to maintian common fragment navigations
 */

public class FragmentNavigationManager implements NavigationManager {

    private static FragmentNavigationManager sInstance;

    private FragmentManager mFragmentManager;
    private MainActivity mActivity;

    public static FragmentNavigationManager obtain(MainActivity activity) {
        if (sInstance == null) {
            sInstance = new FragmentNavigationManager();
        }
        sInstance.configure(activity);
        return sInstance;
    }

    private void configure(MainActivity activity) {
        mActivity = activity;
        mFragmentManager = mActivity.getSupportFragmentManager();
    }

    @Override
    public void showProductFragment(ArrayList<Products> productList) {
        showFragment(ProductFragment.newInstance(productList), false);
    }

    @Override
    public void showProductDetailFragment(Products product) {
        showFragment(ProductDetailFragment.newInstance(product), false);
    }

    private void showFragment(Fragment fragment, boolean allowStateLoss) {
        try {
            FragmentManager fm = mFragmentManager;

            @SuppressLint("CommitTransaction")
            FragmentTransaction ft = fm.beginTransaction()
                    .replace(R.id.container, fragment);

            ft.addToBackStack("DETAIL_FRAG");

            if (allowStateLoss || !BuildConfig.DEBUG) {
                ft.commitAllowingStateLoss();
            } else {
                ft.commit();
            }

            fm.executePendingTransactions();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
