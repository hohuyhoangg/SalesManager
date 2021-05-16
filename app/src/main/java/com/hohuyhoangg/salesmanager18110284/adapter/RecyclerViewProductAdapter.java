package com.hohuyhoangg.salesmanager18110284.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.model.dto.CategoryDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.ProductDTO;

import java.util.List;

public class RecyclerViewProductAdapter extends RecyclerView.Adapter<RecyclerViewProductAdapter.MyViewHolder> {

    private Context mContext;
    private List<ProductDTO> mData;

    public RecyclerViewProductAdapter(Context mContext, List<ProductDTO> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.cardview_item_product,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_product_title.setText(mData.get(position).getProductName());
        holder.tv_product_price.setText(mData.get(position).getPriceOrder().toString());
        //set click

        holder.cardViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"hehehhe", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView tv_product_title;
        TextView tv_product_price;
        ImageView img_product_thumbnail;
        CardView cardViewProduct;
        public  MyViewHolder (View itemView){
            super(itemView);

            tv_product_title = (TextView) itemView.findViewById(R.id.product_title_id);
            tv_product_price = (TextView) itemView.findViewById(R.id.product_price_id);
            img_product_thumbnail = (ImageView) itemView.findViewById(R.id.product_img_id);
            cardViewProduct = (CardView) itemView.findViewById(R.id.cardview_product_id);
        }
    }

}
