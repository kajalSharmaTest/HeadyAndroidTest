package heady.com.headyandroidtest.network;

import heady.com.headyandroidtest.model.Category;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Kajal on 17/09/2018.
 */
public interface ApiInterface {

    static String BASE_URL = "https://stark-spire-93433.herokuapp.com/";

    @GET("json")
    Call<Category> getResults();

}
