package com.hohuyhoangg.salesmanager18110284.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.hohuyhoangg.salesmanager18110284.model.dao.CartDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.ProductDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.CartDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.ProductDTO;
import com.hohuyhoangg.salesmanager18110284.ui.Cart;
import com.hohuyhoangg.salesmanager18110284.ui.LoginSuccess;
import com.hohuyhoangg.salesmanager18110284.utils.Base64Utils;
import com.hohuyhoangg.salesmanager18110284.utils.StringUtils;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class RecyclerViewCartAdapter extends RecyclerView.Adapter<RecyclerViewCartAdapter.MyViewHolder>{
    private Context mContext;
    private List<CartDTO> mData;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public RecyclerViewCartAdapter(Context mContext, List<CartDTO> mData, SharedPreferences sharedPreferences, SharedPreferences.Editor editor) {
        this.mContext = mContext;
        this.mData = mData;
        this.sharedPreferences = sharedPreferences;
        this.editor = editor;
    }

    public RecyclerViewCartAdapter(Context mContext, List<CartDTO> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerViewCartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        editor = sharedPreferences.edit();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.cardview_item_cart,parent,false);
        return new RecyclerViewCartAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewCartAdapter.MyViewHolder holder, int position) {

        ProductDTO product = ProductDAO.getInstance().getById(mData.get(position).getProductId());
        holder.tv_cart_title.setText(product.getProductName());

        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(product.getPriceOrder());
        String a = str1.substring(1);

        holder.tv_cart_price.setText(a + " đ");
        holder.tv_cart_quantity.setText(mData.get(position).getQuantity().toString());
        Bitmap bitmap = Base64Utils.stringToBitmap(product.getImage0());
        holder.img_product_card_thumbnail.setImageBitmap(bitmap);

        String savedStatusAccount = sharedPreferences.getString("account", "");
        CartDTO cartGetCart = CartDAO.getInstance().getByIdTwo(StringUtils.toLong(savedStatusAccount),product.getProductId());

        int countAdd = Integer.parseInt(holder.tv_cart_quantity.getText().toString());
        holder.tv_cart_add_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countAdd <3 ){
                    cartGetCart.setQuantity(cartGetCart.getQuantity() + 1);

                    CartDAO.getInstance().update(cartGetCart);
                    CartDTO cartGetCartNew = CartDAO.getInstance().getByIdTwo(StringUtils.toLong(savedStatusAccount),product.getProductId());
                    holder.tv_cart_quantity.setText(cartGetCartNew.getQuantity().toString());
                    Intent intent = new Intent(mContext, Cart.class);
                    mContext.startActivity(intent);
                }else if (countAdd == 3){
                    Toast.makeText(mContext, "Chỉ mua được tối đa 3 sản phẩm!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        int countSub = Integer.parseInt(holder.tv_cart_quantity.getText().toString());
        holder.tv_cart_sub_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countSub> 1){
                    cartGetCart.setQuantity(cartGetCart.getQuantity() - 1);

                    CartDAO.getInstance().update(cartGetCart);
                    CartDTO cartGetCartNew = CartDAO.getInstance().getByIdTwo(StringUtils.toLong(savedStatusAccount),product.getProductId());
                    holder.tv_cart_quantity.setText(cartGetCartNew.getQuantity().toString());
                    Intent intent = new Intent(mContext, Cart.class);
                    mContext.startActivity(intent);
                }else if(countSub == 1){
                    Toast.makeText(mContext, "Sản phẩm phải có ít nhất 1 sản phẩm!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.tv_cart_cancel_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartDAO.getInstance().deleteItem(cartGetCart);
                Toast.makeText(mContext, "Bạn đã xóa 1 sản phẩm ra khỏi giỏ hàng!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, Cart.class);
                mContext.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView tv_cart_title,tv_cart_price,tv_cart_quantity,tv_cart_sub_quantity,tv_cart_add_quantity,tv_cart_cancel_item;
        ImageView img_product_card_thumbnail;
        CardView cardView;
        public  MyViewHolder (View itemView){
            super(itemView);

            tv_cart_title = (TextView) itemView.findViewById(R.id.product_cart_title_id);
            tv_cart_price= (TextView) itemView.findViewById(R.id.product_cart_price_id);
            tv_cart_quantity= (TextView) itemView.findViewById(R.id.product_cart_quantity_id);
            tv_cart_sub_quantity= (TextView) itemView.findViewById(R.id.product_cart_quantity_sub);
            tv_cart_add_quantity= (TextView) itemView.findViewById(R.id.product_cart_quantity_add);
            tv_cart_cancel_item= (TextView) itemView.findViewById(R.id.product_cart_cancel_item);
            img_product_card_thumbnail = (ImageView) itemView.findViewById(R.id.product_cart_img_id);
            cardView = (CardView) itemView.findViewById(R.id.card_view_cart_id);
        }
    }
}
