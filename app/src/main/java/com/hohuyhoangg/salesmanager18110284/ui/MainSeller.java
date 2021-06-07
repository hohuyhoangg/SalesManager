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
import android.widget.TextView;

import com.hohuyhoangg.salesmanager18110284.MainActivity;
import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewProductAdapter;
import com.hohuyhoangg.salesmanager18110284.model.dao.ProductDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.ProductDTO;
import com.hohuyhoangg.salesmanager18110284.utils.StringUtils;

import java.util.List;

public class MainSeller extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    RecyclerView recyclerViewProductSeller;
    TextView addProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_seller);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        editor = sharedPreferences.edit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_seller);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerViewProductSeller = (RecyclerView) findViewById(R.id.recyclerviewProduct_seller_id);
        addProduct = (TextView) findViewById(R.id.add_product);
        initProductSeller();
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddProduct.class);
                startActivity(intent);
            }
        });
    }

    public void initProductSeller(){
        String savedStatusAccount = sharedPreferences.getString("account", "");
        List<ProductDTO> products = ProductDAO.getInstance().getListProductBySellerId(StringUtils.toLong(savedStatusAccount));
        RecyclerViewProductAdapter recyclerViewProductAdapter = new RecyclerViewProductAdapter(getApplicationContext(), products);

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewProductSeller.setLayoutManager(layoutManager);
        recyclerViewProductSeller.setHasFixedSize(true);
        recyclerViewProductSeller.setAdapter(recyclerViewProductAdapter);
        recyclerViewProductAdapter.notifyDataSetChanged();
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
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}