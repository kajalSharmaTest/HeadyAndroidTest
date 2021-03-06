package heady.com.headyandroidtest;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import heady.com.headyandroidtest.adapter.CustomExpandableListAdapter;
import heady.com.headyandroidtest.fragment.navigation.FragmentNavigationManager;
import heady.com.headyandroidtest.fragment.navigation.NavigationManager;
import heady.com.headyandroidtest.model.Categories;
import heady.com.headyandroidtest.model.Products;
import heady.com.headyandroidtest.model.RankingProducts;
import heady.com.headyandroidtest.model.Rankings;
import heady.com.headyandroidtest.presenter.CategoriesPresenter;
import heady.com.headyandroidtest.presenter.CategoryPresenter;
import heady.com.headyandroidtest.presenter.ProductPresenter;
import heady.com.headyandroidtest.presenter.RankingPresenter;
import heady.com.headyandroidtest.view.CategoriesView;
import heady.com.headyandroidtest.view.CategoryView;
import heady.com.headyandroidtest.view.ProductView;
import heady.com.headyandroidtest.view.RankingView;


public class MainActivity extends AppCompatActivity implements CategoryView,CategoriesView,ProductView,RankingView {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private String[] items;
    ProgressBar mProgress;
    private ProductView mProductView;

    private ExpandableListView mExpandableListView;
    private ExpandableListAdapter mExpandableListAdapter;
    private List<String> mExpandableListTitle;
    private NavigationManager mNavigationManager;

    private Map<String, List<String>> mExpandableListData;
    private CategoryPresenter mCategoryPresenter;
    private CategoriesPresenter mCategoriesPresenter;
    private ProductPresenter mProductPresenter;
    private RankingPresenter mRankingPresenter;
    private List<Categories> mCategories ;
    private List<Rankings> mRankings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        mProgress = (ProgressBar)findViewById(R.id.progress);
        mProductView = this;
        mExpandableListView = (ExpandableListView) findViewById(R.id.navList);
        mNavigationManager = FragmentNavigationManager.obtain(this);
        mCategoryPresenter = new CategoryPresenter(this,this);
        mCategoriesPresenter = new CategoriesPresenter(this,this);
        mProductPresenter = new ProductPresenter(this,this);
        mRankingPresenter = new RankingPresenter(this,this);
        mCategoryPresenter.showSpinner(true);
        mCategoryPresenter.loadCategory();
        LayoutInflater inflater = getLayoutInflater();
        View listHeaderView = inflater.inflate(R.layout.nav_header, null, false);
        mExpandableListView.addHeaderView(listHeaderView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.drawer_icon);
        Toolbar actionBarToolbar = (Toolbar)this.findViewById(R.id.action_bar);
        if (actionBarToolbar != null)
            actionBarToolbar.setTitleTextColor(Color.RED);
    }

        @Override
        public void onBackPressed() {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
            else {
                int fragments = getSupportFragmentManager().getBackStackEntryCount();
                if (fragments == 1) {
                    finish();
                } else {
                    if (getFragmentManager().getBackStackEntryCount() > 1) {
                        getFragmentManager().popBackStack();
                    } else {
                        super.onBackPressed();
                    }
                }
            }
        }

    /*
     * initially display all the products loaded from server and stored in DB ,
     * later the user can select as per ranking and categories.
     */
    private void selectFirstItemAsDefault(ArrayList<Products> productList) {
        if (mNavigationManager != null) {
            mNavigationManager.showProductFragment(productList);
            getSupportActionBar().setTitle("Products");
        }
    }

    /*
      method to initialise drawer ititial items (Category , Rankings
      (Not the sub items that will be loaded later from server))
    */

    private void initItems() {
        items = getResources().getStringArray(R.array.drawer_menu_items);
    }

    /*
      method to load initial content on app launch
    */
    private void loadInitialData(){
        mCategoryPresenter.showSpinner(true);
        mCategoriesPresenter.loadCategories();
        mRankingPresenter.loadRankings();
        mProductPresenter.loadProducts();
    }

    /*
     * setting the drawer menu adapter and its click listeners to close and expand drawer
     */
    private void addDrawerItems() {
        mExpandableListAdapter = new CustomExpandableListAdapter(this, mExpandableListTitle, mExpandableListData);
        mExpandableListView.setAdapter(mExpandableListAdapter);
        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });

    /*
     * setting the drawer element click listener to load and display the products as per user selection.
     */
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String selectedItem = ((List) (mExpandableListData.get(mExpandableListTitle.get(groupPosition))))
                    .get(childPosition).toString();

                if (items[0].equals(mExpandableListTitle.get(groupPosition))) {
                    int catId = Integer.parseInt(((Categories)mCategories.get(childPosition)).getId());
                    mCategoriesPresenter.getProductForCategory(mProductView,catId);
                } else if (items[1].equals(mExpandableListTitle.get(groupPosition))) {
                    mProductPresenter.getProductAsPerRanking(getRankingProductsIds(childPosition));
                } else {
                    throw new IllegalArgumentException("Not supported fragment type");
                }

                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    /*
     * setting the drawer element eith child elements loaded from DB.
     */
    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
     * show products loaded from database .
     * @Param : products : list of products loaded from DB.
     */
    @Override
    public void showProducts(List<Products> products) {
        System.out.println("productsList:::"+products.size());
        ArrayList<Products> prodList = new ArrayList<>(products.size());
        prodList.addAll(products);
        selectFirstItemAsDefault(prodList);
        mCategoryPresenter.showSpinner(false);

    }

    /*
     * show categories as drawer elements loaded from database .
     * @Param : categories: list of categoried loaded from DB.
     */
    @Override
    public void showCategories(List<Categories> categories) {
        mCategories = categories;
        System.out.println("categories:::"+mCategories.size());
    }

    /*
    * set the visibility of spinner while loading content from server and data is not available to display.
    * @Param : state: visibility state of spinner.
    */
    @Override
    public void showSpinner(boolean state) {
      if(state){
          mProgress.setVisibility(View.VISIBLE);
      } else {
          mProgress.setVisibility(View.GONE);
      }
    }

    /*
    * show initial content loaded from database .
    */
    @Override
    public void showInitialData() {
        initItems();
        loadInitialData();
    }

    /*
    * show rankings as drawer elements loaded from database .
    */
    @Override
    public void showRankings(List<Rankings> rankings ) {
       mRankings = rankings;
        System.out.println("rankings:::"+mRankings.size());
        mExpandableListData = getExpandableListData();
        mExpandableListTitle = new ArrayList(mExpandableListData.keySet());
        addDrawerItems();
        setupDrawer();
    }

    /*
    * maintain categories and rankings with respective sub elements to be displayed in drawer .
    * @Return : expandableListData : Map of drawer element and sub element values.
    */
    private Map<String, List<String>> getExpandableListData() {
        Map<String, List<String>> expandableListData = new TreeMap<>();
        if(mCategories !=null && mRankings !=null)

    {
        List<String> categorieslistContent = new ArrayList();
        List<String> rankingslistContent = new ArrayList();


        List<String> drawerItems = Arrays.asList(this.getResources().getStringArray(R.array.drawer_menu_items));

        for (int i = 0; i < mCategories.size(); i++) {
            categorieslistContent.add(mCategories.get(i).getName());
        }
        for (int i = 0; i < mRankings.size(); i++) {
            rankingslistContent.add(customizeStatement(mRankings.get(i).getRanking()));
        }

        expandableListData.put(drawerItems.get(0), categorieslistContent);
        expandableListData.put(drawerItems.get(1), rankingslistContent);

    }
        return expandableListData;
    }

    /*
     * get product id out of ranking selected by user from drawer.
     * product id will be used to get all product details from DB.
     * @param : childPosition : position of ranking selected from drawer.
     * @Return : productIds : array of product ids in the relected ranking.
     */
    private int[] getRankingProductsIds(int childPosition){
        RankingProducts[] rankingProductsList =((Rankings)mRankings.get(childPosition)).getProducts();
        int[] productIds = new int[rankingProductsList.length];
        for(int i=0;i<rankingProductsList.length;i++){
            productIds[i] = Integer.parseInt(rankingProductsList[i].getId());
        }
        return productIds;
    }

    /*
     * customize text to display first character in capital and rest in lower case.
     * Because ranking elements are not properly customised . So applied this methos on ranking elements.
     * @param : data : reanking text received from server and stored in database.
     * Return: customes string (eg "Most OrdeRed" will be customised as "Most ordered")
     */
    private String customizeStatement(String data){
            String firstLetter = data.substring(0,1).toUpperCase();
            String restLetters = data.substring(1).toLowerCase();
            return firstLetter + restLetters;
        }

}
