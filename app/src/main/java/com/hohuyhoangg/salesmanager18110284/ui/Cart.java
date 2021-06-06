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
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewCartAdapter;
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewProductAdapter;
import com.hohuyhoangg.salesmanager18110284.model.dao.CartDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.ProductDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.CartDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.CategoryDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.ProductDTO;
import com.hohuyhoangg.salesmanager18110284.utils.StringUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    List<CartDTO> carts;
    RecyclerView recyclerViewCart;
    TextView cartProductCount,cartFeeAll;
    Button order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initCreate();
        init();

    }
    public void init(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        editor = sharedPreferences.edit();
        initRecyclerViewCart();
        order();
    }
    public void initCreate(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cart);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerViewCart = (RecyclerView) findViewById(R.id.recyclerview_cart_id);
        cartProductCount = (TextView) findViewById(R.id.cart_title_count);
        cartFeeAll = (TextView) findViewById(R.id.cart_fee_all);
        order = (Button) findViewById(R.id.go_to_order);
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
    public void initRecyclerViewCart(){
        String savedStatusAccount = sharedPreferences.getString("account", "");
        carts = CartDAO.getInstance().getByUser(StringUtils.toLong(savedStatusAccount));
        cartProductCount.setText("Giỏ hàng (" + carts.size() + ")");
        Long feeAll = 0L;

        for (CartDTO cart : carts) {
            Long productId = cart.getProductId();
            ProductDTO product = ProductDAO.getInstance().getById(productId);

            String feeItem = product.getPriceOrder().toString();

            Long feeItems = StringUtils.toLong(feeItem);
            Long item = feeItems/100;
            feeAll += item * cart.getQuantity();
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(feeAll);
        String a = str1.substring(1);
        cartFeeAll.setText("Thành tiền: " + a + " đ");
        RecyclerViewCartAdapter recyclerViewCartAdapter = new RecyclerViewCartAdapter(getApplication(), carts);

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewCart.setLayoutManager(layoutManager);
        recyclerViewCart.setHasFixedSize(true);
        recyclerViewCart.setAdapter(recyclerViewCartAdapter);
        recyclerViewCartAdapter.notifyDataSetChanged();
    }
    public void order(){
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedStatusAccount = sharedPreferences.getString("account", "");
                carts = CartDAO.getInstance().getByUser(StringUtils.toLong(savedStatusAccount));
                if(carts.size() > 0){
                    Intent intent = new Intent(getApplication(), Order.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Giỏ hàng đang trống.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}