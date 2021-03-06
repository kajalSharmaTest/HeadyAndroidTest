package heady.com.headyandroidtest.presenter;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import heady.com.headyandroidtest.database.MyRoomDatabase;
import heady.com.headyandroidtest.model.Categories;
import heady.com.headyandroidtest.model.Category;
import heady.com.headyandroidtest.model.Products;
import heady.com.headyandroidtest.model.Rankings;
import heady.com.headyandroidtest.network.ApiService;
import heady.com.headyandroidtest.view.CategoryView;
import heady.com.headyandroidtest.view.ProductView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kajal on 17/09/2018.
 * Presenter to load data from Sever and store them into database.
 */

public class CategoryPresenter {

    private CategoryView categoryView;
    private ApiService apiService;
    private Context mContext;

    /*
    * Intialise CategoryPresenter instance and interface
    * @Param view : view through which to display the data in UI.
    * @Param ctx : activity instance passed from mainActivity class
    */
    public CategoryPresenter(CategoryView view, Context ctx) {
        this.categoryView = view;
        this.mContext = ctx;

        if (this.apiService == null) {
            this.apiService = new ApiService();
        }
    }

    /*
     * display or hide the spinner on UI as per state value (T/F)
     * @Param state : boolean value to set spinner visibilty
     */
    public void showSpinner(boolean state){
        categoryView.showSpinner(state);
    }

    /*
     * method to load data from server and store the in database.
     */
    public void loadCategory() {
        apiService
                .getAPI()
                .getResults()
                .enqueue(new Callback<Category>() {
                    @Override
                    public void onResponse(Call<Category> call, Response<Category> response) {
                        Category data = response.body();

                        if (data != null && data.getCategories() != null && data.getRankings() !=null) {
                            Category category = new Category();
                            category.setCategories(data.getCategories());
                            category.setRankings(data.getRankings());
                            DatabaseTaskRunner runner = new DatabaseTaskRunner();
                            runner.execute(category);
                        }
                    }

                    @Override
                    public void onFailure(Call<Category> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /*
     * AsyncTask to insert the data loaded from server in database.
     */
    private class DatabaseTaskRunner extends AsyncTask<Category, String, String> {


        @Override
        protected String doInBackground(Category... params) {
           insertIntoDB(params[0]);
           return "";
        }
        @Override
        protected void onPostExecute(String result) {
            System.out.println("DB result::"+result);
            categoryView.showInitialData();
        }
    }

     private boolean insertIntoDB(Category category) {
        try {
            Categories[] categories = category.getCategories();
            Rankings[] rankings = category.getRankings();
            MyRoomDatabase myRoomDatabase = MyRoomDatabase.getDatabase(this.mContext);
            for (int i = 0; i < categories.length; i++) {
                myRoomDatabase.categoriesDao().insert(categories[i]);
                Products[] products = categories[i].getProducts();
                for (int j = 0; j < products.length; j++) {
                    myRoomDatabase.productsDao().insert(products[j]);
                }
            }
            for (int i = 0; i < rankings.length; i++) {
                myRoomDatabase.rankingsDao().insert(rankings[i]);
            }
            for (int i = 0; i < rankings.length; i++) {
                myRoomDatabase.rankingsDao().insert(rankings[i]);
            }
        } catch(Exception e){
            e.printStackTrace();
            return false;

        }
        return true;
     }

}
