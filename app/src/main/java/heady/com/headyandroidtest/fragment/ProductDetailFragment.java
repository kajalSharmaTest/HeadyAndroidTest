package heady.com.headyandroidtest.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;

import heady.com.headyandroidtest.R;
import heady.com.headyandroidtest.adapter.VariantAdapter;
import heady.com.headyandroidtest.model.Products;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailFragment extends Fragment {

 private static Products mProduct;
    public ProductDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment ProductDetailFragment.
     */
    public static ProductDetailFragment newInstance(Products product) {
        ProductDetailFragment fragmentComedy = new ProductDetailFragment();
        mProduct = product;
        return fragmentComedy;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView prodName = (TextView)view.findViewById(R.id.detail_prod_name);
        TextView prodDate = (TextView)view.findViewById(R.id.detail_prod_added);
        TextView prodTax = (TextView)view.findViewById(R.id.detail_prod_tax);
        TextView prodTaxVal = (TextView)view.findViewById(R.id.detail_prod_value);
       RecyclerView rv_variants = (RecyclerView)view.findViewById(R.id.variants);

       prodName.setText(mProduct.getName());
        prodDate.setText(getSpannableString("Date Added :  ",mProduct.getDate_added().split("T")[0]));
        prodTax.setText(getSpannableString("Tax :  ",mProduct.getTax().getName()));
        prodTaxVal.setText(getSpannableString("Tax Value :",mProduct.getTax().getValue()));
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_variants.setLayoutManager(llm);
        VariantAdapter adapter = new VariantAdapter(Arrays.asList(mProduct.getVariants()));
        rv_variants.setAdapter(adapter);
    }

    private SpannableString getSpannableString(String text1, String text2){
        SpannableString ss1=  new SpannableString(text1+text2);
        ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, text1.length(), 0);
        return ss1;
    }
}
