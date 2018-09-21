package heady.com.headyandroidtest.presenter;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Arrays;
import java.util.List;

import heady.com.headyandroidtest.database.MyRoomDatabase;
import heady.com.headyandroidtest.model.Categories;
import heady.com.headyandroidtest.model.Products;
import heady.com.headyandroidtest.view.ProductView;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Kajal on 17/09/2018.
 * Presenter to reteive data from DB and pass it to view interface to be displayed through activity
 */

public class ProductPresenter {

    private ProductView productView;
    private Context mContext;


    /*
     * Intialise ProductPresenter instance and interface
     * @Param view : view through which to display the data in UI.
     * @Param ctx : activity instance passed from mainActivity class
     */
    public ProductPresenter(ProductView view,Context ctx) {
        this.productView = view;
        this.mContext = ctx;


    }

    public void showSpinner(boolean state){
        productView.showSpinner(state);
    }

    /*
     * initialise AsyncTask to retrieve all products from DB stored in Products table
     */
    public void loadProducts() {
        DatabaseTaskRunner runner = new DatabaseTaskRunner();
        runner.execute();
    }

    /*
    * AsyncTask to retrieve all products from DB stored in Products table.
    */
    private class DatabaseTaskRunner extends AsyncTask<Void, Void, List<Products>> {


        @Override
        protected void onPostExecute(List<Products> products) {
            productView.showProducts(products);
        }

        @Override
        protected List<Products> doInBackground(Void... voids) {
            MyRoomDatabase myRoomDatabase = MyRoomDatabase.getDatabase(mContext);
            return myRoomDatabase.productsDao().getAllProducts();
        }
    }

    /*
    * initialise AsyncTask to retrieve all products based on prodIds from DB stored in Products table
    */
    public void getProductAsPerRanking(int[] prodIds){
        DatabaseTaskRunnerForRanking runner = new DatabaseTaskRunnerForRanking();
        runner.execute(prodIds);

    }

    /*
   * AsyncTask to retrieve all products as per product_id captured from ranking from DB stored in Products table.
   * @Param : list of productIds
   */
    private class DatabaseTaskRunnerForRanking extends AsyncTask<int[], Void, List<Products>> {


        @Override
        protected void onPostExecute(List<Products> products) {
            productView.showProducts(products);
        }

        @Override
        protected List<Products> doInBackground(int[]... catId) {
            MyRoomDatabase myRoomDatabase = MyRoomDatabase.getDatabase(mContext);
           List<Products> productsList =  myRoomDatabase.productsDao().getProductsByRanking(catId[0]);
           return productsList;
        }
    }

}
