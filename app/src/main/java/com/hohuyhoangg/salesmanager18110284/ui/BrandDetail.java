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
import com.hohuyhoangg.salesmanager18110284.model.dao.ProductDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.BrandDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.ProductDTO;
import com.hohuyhoangg.salesmanager18110284.utils.Base64Utils;
import com.hohuyhoangg.salesmanager18110284.utils.StringUtils;

import java.util.List;

public class BrandDetail extends AppCompatActivity {

    TextView brandName,brandOrigin;
    RecyclerView recyclerViewProductBrand;
    ImageView imageViewBrand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_brand_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        brandName = (TextView) findViewById(R.id.brand_name_detail);
        brandOrigin = (TextView) findViewById(R.id.brand_origin_detail);
        recyclerViewProductBrand = (RecyclerView) findViewById(R.id.recyclerviewProduct_detail_brand_id);
        imageViewBrand = (ImageView) findViewById(R.id.image_view_brand);
        Intent intentGet = getIntent();
        String data = intentGet.getExtras().getString("brandId");
        BrandDTO brandDTO = BrandDAO.getInstance().getById(StringUtils.toLong(data));
        brandName.setText(brandDTO.getBrandName());
        brandOrigin.setText(brandDTO.getBrandOrigin());

        Bitmap bitmap = Base64Utils.stringToBitmap(brandDTO.getImagePath());
        imageViewBrand.setImageBitmap(bitmap);

        List<ProductDTO> products = ProductDAO.getInstance().getListProductByBrandId(brandDTO.getBrandId());
        RecyclerViewProductAdapter recyclerViewProductAdapter = new RecyclerViewProductAdapter(getApplicationContext(), products);

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewProductBrand.setLayoutManager(layoutManager);
        recyclerViewProductBrand.setHasFixedSize(true);
        recyclerViewProductBrand.setAdapter(recyclerViewProductAdapter);
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