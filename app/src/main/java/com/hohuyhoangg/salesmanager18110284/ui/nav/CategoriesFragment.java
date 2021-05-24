package com.hohuyhoangg.salesmanager18110284.ui.nav;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewBrandAdapter;
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewCategoryAdapter;
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewCategoryAdapterCategoryFragment;
import com.hohuyhoangg.salesmanager18110284.model.dao.BrandDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.CategoryDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.BrandDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.CategoryDTO;

import java.util.List;

public class CategoriesFragment extends Fragment {

    List<BrandDTO> brands;
    List<CategoryDTO> categories;
    RecyclerView recyclerViewCategoryCat,recyclerViewBrandCat;
    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        recyclerViewCategoryCat = (RecyclerView) view.findViewById(R.id.recyclerviewCategoryCategory_id);
        recyclerViewBrandCat = (RecyclerView) view.findViewById(R.id.recyclerviewBrandCategory_id);
        init();
        return view;
    }
    public void init(){
        initBrand();
        initCategory();
    }
    public void initBrand(){
        brands = BrandDAO.getInstance().gets();

        RecyclerViewBrandAdapter recyclerViewBrandAdapter = new RecyclerViewBrandAdapter(getContext(), brands);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewBrandCat.setLayoutManager(layoutManager);
        recyclerViewBrandCat.setHasFixedSize(true);
        recyclerViewBrandCat.setAdapter(recyclerViewBrandAdapter);
        recyclerViewBrandAdapter.notifyDataSetChanged();
    }
    public void initCategory(){
        categories = CategoryDAO.getInstance().gets();
        RecyclerViewCategoryAdapterCategoryFragment recyclerViewCategoryAdapter = new RecyclerViewCategoryAdapterCategoryFragment(getContext(), categories);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewCategoryCat.setLayoutManager(layoutManager);
        recyclerViewCategoryCat.setHasFixedSize(true);
        recyclerViewCategoryCat.setAdapter(recyclerViewCategoryAdapter);
        recyclerViewCategoryAdapter.notifyDataSetChanged();
    }
}