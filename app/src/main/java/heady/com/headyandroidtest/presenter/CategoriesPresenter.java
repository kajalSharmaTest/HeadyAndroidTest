package heady.com.headyandroidtest.presenter;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Arrays;
import java.util.List;

import heady.com.headyandroidtest.database.MyRoomDatabase;
import heady.com.headyandroidtest.model.Categories;
import heady.com.headyandroidtest.model.Products;
import heady.com.headyandroidtest.view.CategoriesView;
import heady.com.headyandroidtest.view.ProductView;

/**
 * Created by Kajal on 17/09/2018.
 */

public class CategoriesPresenter {

    private CategoriesView categoriesView;
    private ProductView prodView;
    private Context mContext;

    public CategoriesPresenter(CategoriesView view, Context ctx) {
        this.categoriesView = view;
        this.mContext = ctx;
    }

    public void showSpinner(boolean state) {
        categoriesView.showSpinner(state);
    }

    public void loadCategories() {
        DatabaseTaskRunner runner = new DatabaseTaskRunner();
        runner.execute();
    }

    public void getProductForCategory(ProductView prodView,int catId){
       this.prodView = prodView;
        DatabaseTaskRunnerForCategory runner = new DatabaseTaskRunnerForCategory();
        runner.execute(catId);

    }



    private class DatabaseTaskRunner extends AsyncTask<Void, Void, List<Categories>> {


        @Override
        protected void onPostExecute(List<Categories> categories) {
            categoriesView.showCategories(categories);
        }

        @Override
        protected List<Categories> doInBackground(Void... voids) {
            MyRoomDatabase myRoomDatabase = MyRoomDatabase.getDatabase(mContext);
            return myRoomDatabase.categoriesDao().getAllCategories();
        }
    }

    private class DatabaseTaskRunnerForCategory extends AsyncTask<Integer, Void, List<Products>> {


        @Override
        protected void onPostExecute(List<Products> products) {
            prodView.showProducts(products);
        }

        @Override
        protected List<Products> doInBackground(Integer... catId) {
            MyRoomDatabase myRoomDatabase = MyRoomDatabase.getDatabase(mContext);
           List<Categories> categoriesList =  myRoomDatabase.categoriesDao().getProductsByCatId(catId[0]);
           return Arrays.asList(categoriesList.get(0).getProducts());
        }
    }


}
