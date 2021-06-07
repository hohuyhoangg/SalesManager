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
import com.hohuyhoangg.salesmanager18110284.model.dto.CategoryDTO;
import com.hohuyhoangg.salesmanager18110284.ui.CategoryDetail;
import com.hohuyhoangg.salesmanager18110284.utils.Base64Utils;

import java.util.List;

public class RecyclerViewCategoryAdapter extends RecyclerView.Adapter<RecyclerViewCategoryAdapter.MyViewHolder> {

    private Context mContext;
    private List<CategoryDTO> mData;

    public RecyclerViewCategoryAdapter(Context mContext, List<CategoryDTO> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.cardview_item_category,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_category_title.setText(mData.get(position).getTitle());
        Bitmap bitmap = Base64Utils.stringToBitmap(mData.get(position).getImagePath());
        holder.img_category_thumbnail.setImageBitmap(bitmap);
        //set click

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CategoryDetail.class);
                intent.putExtra("categoryId", mData.get(position).getCategoryId().toString().trim());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView tv_category_title;
        ImageView img_category_thumbnail;
        CardView cardView;
        public  MyViewHolder (View itemView){
            super(itemView);

            tv_category_title = (TextView) itemView.findViewById(R.id.category_title_id);
            img_category_thumbnail = (ImageView) itemView.findViewById(R.id.category_img_id);
            cardView = (CardView) itemView.findViewById(R.id.card_view_category_id);
        }
    }

}
