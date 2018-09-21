package heady.com.headyandroidtest.adapter;

/**
 * Created by Kajal on 9/19/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import heady.com.headyandroidtest.MainActivity;
import heady.com.headyandroidtest.R;

import java.util.ArrayList;

import heady.com.headyandroidtest.fragment.navigation.FragmentNavigationManager;
import heady.com.headyandroidtest.model.Products;

public class ProductDataAdapter extends RecyclerView.Adapter<ProductDataAdapter.ViewHolder> {
    private ArrayList<Products> mProductsList;
    private Context context;
    FragmentNavigationManager mNavigationManager;

    public ProductDataAdapter(Context context,ArrayList<Products> products) {
        this.mProductsList = products;
        this.context = context;
        mNavigationManager = FragmentNavigationManager.obtain((MainActivity)context);
    }

    @Override
    public ProductDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductDataAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.tv_android.setText(mProductsList.get(i).getName());
      viewHolder.img_android.setImageDrawable(context.getResources().getDrawable(R.drawable.sample_product));
      viewHolder.img_android.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (mNavigationManager != null) {
                  mNavigationManager.showProductDetailFragment(mProductsList.get(i));
              }
          }
      });
    }

    @Override
    public int getItemCount() {
        return mProductsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_android;
        private ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            tv_android = (TextView)view.findViewById(R.id.tv_android);
            img_android = (ImageView) view.findViewById(R.id.img_android);
        }
    }

}