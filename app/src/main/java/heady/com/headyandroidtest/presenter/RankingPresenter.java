package heady.com.headyandroidtest.presenter;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import heady.com.headyandroidtest.database.MyRoomDatabase;
import heady.com.headyandroidtest.model.Categories;
import heady.com.headyandroidtest.model.Rankings;
import heady.com.headyandroidtest.view.RankingView;

/**
 * Created by Kajal on 17/09/2018.
 * Presenter to reteive data from DB and pass it to view interface to be displayed through activity
 */

public class RankingPresenter {

    private RankingView rankingView;
    private Context mContext;

    /*
     * Intialise RankingPresenter instance and interface
     * @Param view : view through which to display the data in UI.
     * @Param ctx : activity instance passed from mainActivity class
     */
    public RankingPresenter(RankingView view, Context ctx) {
        this.rankingView = view;
        this.mContext = ctx;
    }

    public void showSpinner(boolean state){
        rankingView.showSpinner(state);
    }

    /*
     * initialise AsyncTask to retrieve all rankings from DB stored in Categories table
     */
    public void loadRankings() {
     DatabaseTaskRunner runner = new DatabaseTaskRunner();
        runner.execute();
    }

    /*
     * AsyncTask to retrieve all Rankings from DB stored in Rankings table.
     */
    private class DatabaseTaskRunner extends AsyncTask<Void, Void, List<Rankings>> {


        @Override
        protected void onPostExecute(List<Rankings> rankings) {
            rankingView.showRankings(rankings);
        }

        @Override
        protected List<Rankings> doInBackground(Void... voids) {
            MyRoomDatabase myRoomDatabase = MyRoomDatabase.getDatabase(mContext);
            return myRoomDatabase.rankingsDao().getAllRankings();
        }
    }
}
