package com.hohuyhoangg.salesmanager18110284.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewProductAdapter;
import com.hohuyhoangg.salesmanager18110284.model.dao.BrandDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.CategoryDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.ProductDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.BrandDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.CategoryDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.ProductDTO;
import com.hohuyhoangg.salesmanager18110284.utils.Base64Utils;
import com.hohuyhoangg.salesmanager18110284.utils.StringUtils;

import java.util.List;

public class CategoryDetail extends AppCompatActivity {

    TextView categoryName;
    RecyclerView recyclerViewProductCategory;
    ImageView imageViewCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_category_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        categoryName = (TextView) findViewById(R.id.category_detail_name);
        recyclerViewProductCategory = (RecyclerView) findViewById(R.id.recyclerviewProduct_detail_category_id);
        imageViewCategory = (ImageView) findViewById(R.id.image_view_category);
        Intent intentGet = getIntent();
        String data = intentGet.getExtras().getString("categoryId");
        CategoryDTO categoryDTO = CategoryDAO.getInstance().getById(StringUtils.toLong(data));
        categoryName.setText(categoryDTO.getTitle());

        Bitmap bitmap = Base64Utils.stringToBitmap(categoryDTO.getImagePath());
        imageViewCategory.setImageBitmap(bitmap);

        List<ProductDTO> products = ProductDAO.getInstance().getListProductByCategoryId(categoryDTO.getCategoryId());

        RecyclerViewProductAdapter recyclerViewProductAdapter = new RecyclerViewProductAdapter(getApplicationContext(), products);

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewProductCategory.setLayoutManager(layoutManager);
        recyclerViewProductCategory.setHasFixedSize(true);
        recyclerViewProductCategory.setAdapter(recyclerViewProductAdapter);
        recyclerViewProductAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==android.R.id.home)
        {
            finish();
            return true;
        }
        else if(id == R.id.main_cart_icon)
        {
            Intent intent = new Intent(getApplication(), Cart.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}