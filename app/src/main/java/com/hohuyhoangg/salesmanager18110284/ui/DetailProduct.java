package com.hohuyhoangg.salesmanager18110284.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewProductAdapter;
import com.hohuyhoangg.salesmanager18110284.model.dao.BrandDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.CartDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.CategoryDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.CategoryProductDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.ProductDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.BrandDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.CartDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.CategoryDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.CategoryProductDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.ProductDTO;
import com.hohuyhoangg.salesmanager18110284.utils.Base64Utils;
import com.hohuyhoangg.salesmanager18110284.utils.StringUtils;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DetailProduct extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    CarouselView carouselViewProduct;
    TextView productName,priceOder,priceOrigin, discounts,productBrand,productOrigin,productDesc,productCategory,productCategoryTitle;
    Button addToCart;
    RecyclerView recyclerviewProductInDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        initCreate();
        init();
    }
    public void init(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        editor = sharedPreferences.edit();
        initRecyclerProductDetail();
        initCVProduct();
        initDataProduct();
        addProductToCart();
    }
    public void initCreate(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        carouselViewProduct = (CarouselView) findViewById(R.id.carouselViewProduct);
        priceOrigin = (TextView) findViewById(R.id.product_price_id_origin);
        priceOrigin.setPaintFlags(priceOrigin.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        priceOder = (TextView) findViewById(R.id.product_price_id_order);
        discounts = (TextView) findViewById(R.id.product_price_id_discount);
        productName = (TextView) findViewById(R.id.product_name_detail);
        productBrand = (TextView) findViewById(R.id.product_brand_detail);
        productOrigin = (TextView) findViewById(R.id.product_origin_detail);
        productCategory = (TextView) findViewById(R.id.product_category_detail);
        productCategoryTitle = (TextView) findViewById(R.id.product_category_detail_title);
        productDesc = (TextView) findViewById(R.id.product_desc_detail);
        addToCart = (Button) findViewById(R.id.add_to_card);
        recyclerviewProductInDetail = (RecyclerView) findViewById(R.id.recyclerviewProduct_detail_id);
    }
    public void initRecyclerProductDetail(){

        Intent intentGet = getIntent();
        ProductDTO product = (ProductDTO) intentGet.getSerializableExtra("product");

        List<ProductDTO> products = ProductDAO.getInstance().getListProductByProductId(product.getProductId());

        for (int i =0; i<products.size() ;i++){
            if(products.get(i).getProductId().equals(product.getProductId())){
                products.remove(i);
                break;
            }
        }
        RecyclerViewProductAdapter recyclerViewProductAdapter = new RecyclerViewProductAdapter(getApplicationContext(), products);

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerviewProductInDetail.setLayoutManager(layoutManager);
        recyclerviewProductInDetail.setHasFixedSize(true);
        recyclerviewProductInDetail.setAdapter(recyclerViewProductAdapter);
        recyclerViewProductAdapter.notifyDataSetChanged();
    }
    public void initDataProduct(){
        Intent intentGet = getIntent();
        ProductDTO product = (ProductDTO) intentGet.getSerializableExtra("product");



        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(product.getPriceOrder());
        String a = str1.substring(1);
        priceOder.setText(a + " vnđ");

        Long price = StringUtils.toLong(product.getPriceOrder().toString()) / 100 + 1000000L;

        String str2 = currencyVN.format(price);
        String b = str2.substring(1);
        priceOrigin.setText(b + " đ");

        Long priceA = (StringUtils.toLong(product.getPriceOrder().toString()) / 100);
        Double discount =  (Double.parseDouble(priceA.toString()) / Double.parseDouble(price.toString())) * 100;
        double roundOff = 100 - Math.round(discount*100)/100;
        discounts.setText("-"+ String.valueOf(roundOff) + "%");
        productName.setText(product.getProductName());
        BrandDTO brandDTO = BrandDAO.getInstance().getById(product.getBrandId());
        productBrand.setText(brandDTO.getBrandName());
        productOrigin.setText(product.getProductOrigin());
        productDesc.setText(product.getProductDesc());

        List<CategoryDTO> categoryDTOS = CategoryDAO.getInstance().getListByProductId(product.getProductId());
        String text="";
        for (CategoryDTO cate: categoryDTOS
             ) {
            text += cate.getTitle() + ", ";
        }
        productCategory.setText(text);
        productCategory.setHeight(40*categoryDTOS.size());
        productCategoryTitle.setHeight(40*categoryDTOS.size());
    }
    public void initCVProduct(){
        List<String> listImage = new ArrayList<>();

        Intent intentGet = getIntent();
        ProductDTO product = (ProductDTO) intentGet.getSerializableExtra("product");

        if (product.getImage1() != null && !product.getImage1().equals(""))
            listImage.add(product.getImage1());
        if (product.getImage2() != null && !product.getImage2().equals(""))
            listImage.add(product.getImage2());
        if (product.getImage3() != null && !product.getImage3().equals(""))
            listImage.add(product.getImage3());
        if (product.getImage4() != null && !product.getImage4().equals(""))
            listImage.add(product.getImage4());
        if (product.getImage0() != null && !product.getImage0().equals(""))
            listImage.add(product.getImage0());
        carouselViewProduct.setPageCount(listImage.size());
        carouselViewProduct.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageBitmap(Base64Utils.stringToBitmap(listImage.get(position)));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        });
        carouselViewProduct.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getApplication(),"HIHI",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void addProductToCart(){
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedStatusAccount = sharedPreferences.getString("account", "");
                Intent intentGet = getIntent();
                ProductDTO product = (ProductDTO) intentGet.getSerializableExtra("product");

                if(savedStatusAccount.equals("0")){
                    Toast.makeText(getApplication(), "Hãy tiến hành đăng nhập để thêm sản phẩm vào giỏ hàng!!!", Toast.LENGTH_SHORT).show();
                } else {
                    List<CartDTO> listProductInCart = CartDAO.getInstance().getByUser(StringUtils.toLong(savedStatusAccount));
                    savedStatusAccount = sharedPreferences.getString("account", "");
                    int flag=0;
                    for (CartDTO cart: listProductInCart) {
                        if(cart.getUserId().equals(StringUtils.toLong(savedStatusAccount)) && cart.getProductId().equals(product.getProductId())){
                            flag = 1;
                            break;
                        }
                    }
                    CartDTO cartDTO = new CartDTO(StringUtils.toLong(savedStatusAccount),product.getProductId(),1);
                    Long countProduct = 0L;
                    if(flag == 0){
                        CartDAO.getInstance().insert(cartDTO);
                        Toast.makeText(getApplication(), "Đã thêm thành công vào giỏ hàng!!!", Toast.LENGTH_SHORT).show();
                    } else {

                        CartDTO cartGetCart = CartDAO.getInstance().getByIdTwo(StringUtils.toLong(savedStatusAccount),product.getProductId());
                        cartDTO.setQuantity(cartGetCart.getQuantity() + 1);

                        countProduct = Integer.toUnsignedLong(CartDAO.getInstance().update(cartDTO));
                    }

                    if(countProduct  != 0 ){
                        Toast.makeText(getApplication(), "Đã thêm thành công vào giỏ hàng!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
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