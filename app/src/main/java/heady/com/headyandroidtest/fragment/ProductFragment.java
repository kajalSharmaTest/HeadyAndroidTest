package heady.com.headyandroidtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import heady.com.headyandroidtest.R;
import heady.com.headyandroidtest.adapter.ProductDataAdapter;
import heady.com.headyandroidtest.model.Products;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {

    private static ArrayList<Products> mProductList;

    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment ProductFragment.
     */
    public static ProductFragment newInstance(ArrayList<Products> productList) {
        ProductFragment fragmentAction = new ProductFragment();
        mProductList = productList;
        return fragmentAction;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout ll_prod_list =(LinearLayout)view.findViewById(R.id.prod_list_container);
        LinearLayout ll_no_prod =(LinearLayout)view.findViewById(R.id.no_prod_container);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.card_recycler_view);
        if(mProductList.size() >0) {
            ll_prod_list.setVisibility(View.VISIBLE);
            ll_no_prod.setVisibility(View.GONE);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerView.setLayoutManager(layoutManager);
            ProductDataAdapter adapter = new ProductDataAdapter(getActivity(), mProductList);
            recyclerView.setAdapter(adapter);
        } else {
            ll_prod_list.setVisibility(View.GONE);
            ll_no_prod.setVisibility(View.VISIBLE);
        }
    }
}
