package com.hohuyhoangg.salesmanager18110284.ui.nav;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewBrandAdapter;
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewCategoryAdapter;
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewProductAdapter;
import com.hohuyhoangg.salesmanager18110284.model.dao.BrandDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.CategoryDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.ProductDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.BrandDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.CategoryDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.ProductDTO;
import com.hohuyhoangg.salesmanager18110284.ui.Cart;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

public class HomeFragment extends Fragment {

    CarouselView carouselView;
    List<CategoryDTO> categories;
    List<ProductDTO> products;
    List<BrandDTO> brands;
    RecyclerView recyclerViewCategory,recyclerViewProduct,recyclerViewBrand;
    ImageButton cartHome;
    EditText searchProduct;
    TextView textBrand, textCategory;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        recyclerViewCategory = (RecyclerView) view.findViewById(R.id.recyclerviewCategory_id);
        recyclerViewProduct = (RecyclerView) view.findViewById(R.id.recyclerviewProduct_id);
        recyclerViewBrand = (RecyclerView) view.findViewById(R.id.recyclerviewBrand_id);
        cartHome = (ImageButton) view.findViewById(R.id.image_button_cart_home);
        searchProduct = (EditText) view.findViewById(R.id.edit_text_search_product);
        textBrand = (TextView) view.findViewById(R.id.text_brand);
        textCategory = (TextView) view.findViewById(R.id.text_category);
        initCarouseView();
        initRecyclerViewBrand();
        categories = CategoryDAO.getInstance().gets();
        initRecyclerViewCategory(categories);
        products = ProductDAO.getInstance().gets();
        initRecyclerViewProduct(products);
        cartHomeListener();
        searchProductListener();
        return view;
    }
    public void searchProductListener(){
        searchProduct.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (searchProduct.getLeft() - searchProduct.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {
                        String searchText = searchProduct.getText().toString().trim();
                        if(!searchText.equals("")){
                            textBrand.setVisibility(View.GONE);
                            textCategory.setVisibility(View.GONE);
                            recyclerViewBrand.setVisibility(View.GONE);
                            recyclerViewCategory.setVisibility(View.GONE);
                            products = ProductDAO.getInstance().searchLike(searchText);
                            initRecyclerViewProduct(products);
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }
    public void initCarouseView(){
        int[] imgCarouselView = new int[]{R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,R.drawable.i5,R.drawable.i6};
        carouselView.setPageCount(imgCarouselView.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(imgCarouselView[position]);
            }
        });
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                textBrand.setVisibility(View.VISIBLE);
                textCategory.setVisibility(View.VISIBLE);
                recyclerViewBrand.setVisibility(View.VISIBLE);
                recyclerViewCategory.setVisibility(View.VISIBLE);
                products = ProductDAO.getInstance().gets();
                initRecyclerViewProduct(products);
            }
        });
    }
    public void initRecyclerViewCategory(List<CategoryDTO> categories){
        RecyclerViewCategoryAdapter recyclerViewCategoryAdapter = new RecyclerViewCategoryAdapter(getContext(), categories);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setHasFixedSize(true);
        recyclerViewCategory.setAdapter(recyclerViewCategoryAdapter);
        recyclerViewCategoryAdapter.notifyDataSetChanged();
    }
    public void initRecyclerViewProduct(List<ProductDTO> products){

        RecyclerViewProductAdapter recyclerViewProductAdapter = new RecyclerViewProductAdapter(getContext(), products);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewProduct.setLayoutManager(layoutManager);
        recyclerViewProduct.setHasFixedSize(true);
        recyclerViewProduct.setAdapter(recyclerViewProductAdapter);
        recyclerViewProductAdapter.notifyDataSetChanged();
    }
    public void initRecyclerViewBrand(){
        brands = BrandDAO.getInstance().gets();


        RecyclerViewBrandAdapter recyclerViewBrandAdapter = new RecyclerViewBrandAdapter(getContext(), brands);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewBrand.setLayoutManager(layoutManager);
        recyclerViewBrand.setHasFixedSize(true);
        recyclerViewBrand.setAdapter(recyclerViewBrandAdapter);
        recyclerViewBrandAdapter.notifyDataSetChanged();
    }
    public void cartHomeListener(){
        cartHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Cart.class);
                startActivity(intent);
            }
        });
    }
}