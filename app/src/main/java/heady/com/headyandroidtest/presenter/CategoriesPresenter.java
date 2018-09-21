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
 * Presenter to reteive data from DB and pass it to view interface to be displayed through activity
 */

public class CategoriesPresenter {

    private CategoriesView categoriesView;
    private ProductView prodView;
    private Context mContext;

    /*
     * Intialise CategoriesPresenter instance and interface
     * @Param view : view through which to display the data in UI.
     * @Param ctx : activity instance passed from mainActivity class
     */
    public CategoriesPresenter(CategoriesView view, Context ctx) {
        this.categoriesView = view;
        this.mContext = ctx;
    }

    public void showSpinner(boolean state) {
        categoriesView.showSpinner(state);
    }

    /*
     * initialise AsyncTask to retrieve all categories from DB stored in Categories table
     */
    public void loadCategories() {
        DatabaseTaskRunner runner = new DatabaseTaskRunner();
        runner.execute();
    }

    /*
     * initialise AsyncTask to retrieve Products based on categoryId from DB stored in Categories table.
     */
    public void getProductForCategory(ProductView prodView,int catId){
       this.prodView = prodView;
        DatabaseTaskRunnerForCategory runner = new DatabaseTaskRunnerForCategory();
        runner.execute(catId);

    }


    /*
     * AsyncTask to retrieve all categories from DB stored in Categories table.
     */
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

    /*
     * AsyncTask to retrieve Products based on categoryId from DB stored in Categories table.
     */
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
