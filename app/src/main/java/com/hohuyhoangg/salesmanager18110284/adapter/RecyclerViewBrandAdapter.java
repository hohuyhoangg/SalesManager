package com.hohuyhoangg.salesmanager18110284.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.model.dto.BrandDTO;
import com.hohuyhoangg.salesmanager18110284.ui.BrandDetail;
import com.hohuyhoangg.salesmanager18110284.utils.Base64Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class RecyclerViewBrandAdapter extends RecyclerView.Adapter<RecyclerViewBrandAdapter.MyViewHolder>{
    private Context mContext;
    private List<BrandDTO> mData;

    public RecyclerViewBrandAdapter(Context mContext, List<BrandDTO> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerViewBrandAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.cardview_item_brand,parent,false);
        return new RecyclerViewBrandAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBrandAdapter.MyViewHolder holder, int position) {

        holder.tv_brand_title.setText(mData.get(position).getBrandName());
        Bitmap bitmap = Base64Utils.stringToBitmap(mData.get(position).getImagePath());
        holder.img_brand_thumbnail.setImageBitmap(bitmap);
        //set click

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BrandDetail.class);
                intent.putExtra("brandId", mData.get(position).getBrandId().toString().trim());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView tv_brand_title;
        ImageView img_brand_thumbnail;
        CardView cardView;
        public  MyViewHolder (View itemView){
            super(itemView);

            tv_brand_title = (TextView) itemView.findViewById(R.id.brand_title_id);
            img_brand_thumbnail = (ImageView) itemView.findViewById(R.id.brand_img_id);
            cardView = (CardView) itemView.findViewById(R.id.card_view_brand_detail_id);
        }
    }
}
