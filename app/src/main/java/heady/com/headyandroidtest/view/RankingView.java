package heady.com.headyandroidtest.view;

import java.util.List;

import heady.com.headyandroidtest.model.Rankings;

/**
 * Created by Kajal on 17/09/2018.
 * Interface to pass Rankings data from presenter to View.
 */

public interface RankingView {

    void showRankings(List<Rankings> rankings);
    void showSpinner(boolean state);
}
