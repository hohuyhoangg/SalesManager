package com.hohuyhoangg.salesmanager18110284.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hohuyhoangg.salesmanager18110284.MainActivity;
import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewAddCategoryAdapter;
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewCartAdapter;
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewProductAdapter;
import com.hohuyhoangg.salesmanager18110284.model.dao.CategoryDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.CategoryProductDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.ProductDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.CategoryDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.CategoryProductDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.ProductDTO;
import com.hohuyhoangg.salesmanager18110284.utils.StringUtils;

import java.util.List;


public class AddProductSuccess extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    RecyclerView recyclerViewProductCategory;
    Button addProductDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_success);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        editor = sharedPreferences.edit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add_product);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerViewProductCategory = (RecyclerView) findViewById(R.id.recyclerviewProduct_category_add_id);
        addProductDone = (Button) findViewById(R.id.add_product_to_db);
        init();
    }
    public void init(){
        List<CategoryDTO> categoryDTOS = CategoryDAO.getInstance().gets();
        RecyclerViewAddCategoryAdapter recyclerViewProductAdapter = new RecyclerViewAddCategoryAdapter(getApplicationContext(), categoryDTOS);

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewProductCategory.setLayoutManager(layoutManager);
        recyclerViewProductCategory.setHasFixedSize(true);
        recyclerViewProductCategory.setAdapter(recyclerViewProductAdapter);
        recyclerViewProductAdapter.notifyDataSetChanged();

        addProductDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGet = getIntent();
                ProductDTO product = (ProductDTO) intentGet.getSerializableExtra("product");
                Long productId = ProductDAO.getInstance().insert(product);

                String stringCategory = sharedPreferences.getString("stringCategory", "");

                String[] parts = stringCategory.split("a");

                if(productId > 0){
                    for (int i =0; i<parts.length;i++){
                        if(!parts[i].equals("")){
                            CategoryProductDTO categoryProductDTO = new CategoryProductDTO();
                            categoryProductDTO.setProductId(productId);
                            categoryProductDTO.setCategoryId(StringUtils.toLong(parts[i]));
                            CategoryProductDAO.getInstance().insert(categoryProductDTO);
                        }
                    }
                    editor.putString("stringCategory", "");
                    editor.commit();
                    Toast.makeText(getApplication(),"Thêm thành công!!!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainSeller.class);
                    startActivity(intent);
                }

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cancel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {

        }
        return super.onOptionsItemSelected(item);
    }
}