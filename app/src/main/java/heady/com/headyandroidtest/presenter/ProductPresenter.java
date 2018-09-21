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
 */

public class ProductPresenter {

    private ProductView productView;
    private Context mContext;


    public ProductPresenter(ProductView view,Context ctx) {
        this.productView = view;
        this.mContext = ctx;


    }

    public void showSpinner(boolean state){
        productView.showSpinner(state);
    }

    public void loadProducts() {
        DatabaseTaskRunner runner = new DatabaseTaskRunner();
        runner.execute();
    }

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

    public void getProductAsPerRanking(int[] prodIds){
        DatabaseTaskRunnerForRanking runner = new DatabaseTaskRunnerForRanking();
        runner.execute(prodIds);

    }

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
