package com.hohuyhoangg.salesmanager18110284.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.model.dao.CartDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.CategoryDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.ProductDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.CartDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.CategoryDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.ProductDTO;
import com.hohuyhoangg.salesmanager18110284.ui.Cart;
import com.hohuyhoangg.salesmanager18110284.utils.Base64Utils;
import com.hohuyhoangg.salesmanager18110284.utils.StringUtils;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class RecyclerViewAddCategoryAdapter extends RecyclerView.Adapter<RecyclerViewAddCategoryAdapter.MyViewHolder>{

    private Context mContext;
    private List<CategoryDTO> mData;
    int [] arr;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public RecyclerViewAddCategoryAdapter(Context mContext, List<CategoryDTO> mData, SharedPreferences sharedPreferences, SharedPreferences.Editor editor) {
        this.mContext = mContext;
        this.mData = mData;
        this.sharedPreferences = sharedPreferences;
        this.editor = editor;
    }

    public RecyclerViewAddCategoryAdapter(Context mContext, List<CategoryDTO> mData) {
        this.mContext = mContext;
        this.mData = mData;
        arr = new int[mData.size()];
    }

    @NonNull
    @Override
    public RecyclerViewAddCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        editor = sharedPreferences.edit();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.cardview_item_category_add_product,parent,false);
        return new RecyclerViewAddCategoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAddCategoryAdapter.MyViewHolder holder, int position) {

        List<CategoryDTO> categoryDTOs = CategoryDAO.getInstance().gets();
        holder.tv_category_title.setText(mData.get(position).getTitle());
        holder.checkBoxAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.checkBoxAddProduct.isChecked()){
                    arr[position] = 1;
                } else {
                    arr[position] = 0;
                }
                String stringCategory = "a";
                for(int i = 0; i< arr.length ; i++){
                    if(arr[i] == 1){
                        int a = i+1;
                        stringCategory += a + "a";
                    }
                }
                editor.putString("stringCategory", stringCategory);
                editor.commit();
            }
        });


    }
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView tv_category_title;
        CheckBox checkBoxAddProduct;
        CardView cardView;
        public  MyViewHolder (View itemView){
            super(itemView);

            tv_category_title = (TextView) itemView.findViewById(R.id.category_title_add_product_id);
            checkBoxAddProduct= (CheckBox) itemView.findViewById(R.id.checkbox_add_product);
            cardView = (CardView) itemView.findViewById(R.id.card_view_add_category_id);
        }
    }
}
