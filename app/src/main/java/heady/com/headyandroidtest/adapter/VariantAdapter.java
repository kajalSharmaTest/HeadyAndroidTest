package heady.com.headyandroidtest.adapter;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import heady.com.headyandroidtest.R;
import heady.com.headyandroidtest.model.Variants;

/**
 * Created by Kajal on 9/19/2018.
 */

public class VariantAdapter  extends
            RecyclerView.Adapter<VariantAdapter.MyViewHolder> {

        private List<Variants> variantList;

        /**
         * View holder class
         * */
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView size;
            public TextView color;
            public TextView price;

            public MyViewHolder(View view) {
                super(view);
                color = (TextView) view.findViewById(R.id.color);
                size = (TextView) view.findViewById(R.id.size);
                price = (TextView) view.findViewById(R.id.price);
            }
        }

        public VariantAdapter(List<Variants> variantList) {
            this.variantList = variantList;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Variants variant = variantList.get(position);
            holder.color.setText(getSpannableString("Color :  ",variant.getColor()));
            holder.size.setText(getSpannableString("Size :  ",String.valueOf(variant.getSize())));
            holder.price.setText(getSpannableString("Price :  ",String.valueOf(variant.getPrice())));
        }

        @Override
        public int getItemCount() {
            return variantList.size();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_grid,parent, false);
            return new MyViewHolder(v);
        }

    private SpannableString getSpannableString(String text1, String text2){
        SpannableString ss1=  new SpannableString(text1+text2);
        ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, text1.length(), 0);
        return ss1;
    }
    }
