package com.hohuyhoangg.salesmanager18110284.ui.nav;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    CarouselView carouselView;
    List<CategoryDTO> categories;
    List<ProductDTO> products;
    List<BrandDTO> brands;
    RecyclerView recyclerViewCategory,recyclerViewProduct,recyclerViewBrand;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        recyclerViewCategory = (RecyclerView) view.findViewById(R.id.recyclerviewCategory_id);
        recyclerViewProduct = (RecyclerView) view.findViewById(R.id.recyclerviewProduct_id);
        recyclerViewBrand = (RecyclerView) view.findViewById(R.id.recyclerviewBrand_id);
        initCarouseView();
        initRecyclerViewBrand();
        initRecyclerViewCategory();
        initRecyclerViewProduct();
        return view;
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
                Toast.makeText(getActivity(),"HIHI",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void initRecyclerViewCategory(){
        categories = CategoryDAO.getInstance().gets();
        RecyclerViewCategoryAdapter recyclerViewCategoryAdapter = new RecyclerViewCategoryAdapter(getContext(), categories);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setHasFixedSize(true);
        recyclerViewCategory.setAdapter(recyclerViewCategoryAdapter);
        recyclerViewCategoryAdapter.notifyDataSetChanged();
    }
    public void initRecyclerViewProduct(){
        products = ProductDAO.getInstance().gets();
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
}