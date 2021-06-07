package com.hohuyhoangg.salesmanager18110284.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.hohuyhoangg.salesmanager18110284.MainActivity;
import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.adapter.CustomSpinnerAdapter;
import com.hohuyhoangg.salesmanager18110284.model.dao.BrandDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.ProductDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.BrandDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.ProductDTO;
import com.hohuyhoangg.salesmanager18110284.utils.Base64Utils;
import com.hohuyhoangg.salesmanager18110284.utils.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AddProduct extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final int SAVE_IMAGE_REQUEST = 100;
    private static final int PICK_IMAGE_REQUEST = 71;
    ImageView imageRegisterProduct;
    ImageButton imageScreen;
    Spinner spinner;
    List<BrandDTO> brandDTOS;
    Button btnAddProduct;
    String idBrand;
    TextInputLayout inputProductName, inputProductOrigin,inputProductDesc, inputQuantity, inputPriceOrigin, inputPriceOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        editor = sharedPreferences.edit();
        spinner = (Spinner) findViewById(R.id.spinner_brand);
        btnAddProduct = (Button) findViewById(R.id.button_add_product);
        imageRegisterProduct = (ImageView) findViewById(R.id.image_product);
        imageScreen = (ImageButton) findViewById(R.id.image_screen_product);
        inputProductName = (TextInputLayout) findViewById(R.id.input_product_name);
        inputProductOrigin = (TextInputLayout) findViewById(R.id.input_product_origin);
        inputProductDesc = (TextInputLayout) findViewById(R.id.input_product_desc);
        inputQuantity = (TextInputLayout) findViewById(R.id.input_product_quantity);
        inputPriceOrigin = (TextInputLayout) findViewById(R.id.input_product_price_origin);
        inputPriceOrder = (TextInputLayout) findViewById(R.id.input_product_price_order);
        imageRegisterListener();
        imageScreenListener();


        brandDTOS = BrandDAO.getInstance().gets();
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(AddProduct.this, R.layout.spinner_item, R.id.textView_item_name, R.id.textView_item_origin, brandDTOS);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                BrandDTO mSelected = (BrandDTO) parent.getItemAtPosition(pos);
                idBrand = mSelected.getBrandId().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = inputProductName.getEditText().getText().toString().trim();
                String productOrigin = inputProductOrigin.getEditText().getText().toString().trim();
                String productDesc = inputProductDesc.getEditText().getText().toString().trim();
                Integer quantity = StringUtils.toInt(inputQuantity.getEditText().getText().toString().trim());
                BigDecimal priceOrigin = StringUtils.toBigDecimal(inputPriceOrigin.getEditText().getText().toString().trim());
                BigDecimal priceOrder = StringUtils.toBigDecimal(inputPriceOrder.getEditText().getText().toString().trim());

                ProductDTO productDTO = new ProductDTO();
                productDTO.setBrandId(StringUtils.toLong(idBrand));
                String savedStatusAccount = sharedPreferences.getString("account", "");
                productDTO.setSellerId(StringUtils.toLong(savedStatusAccount));
                productDTO.setProductName(productName);
                productDTO.setProductRate(0.0);
                productDTO.setProductOrigin(productOrigin);
                productDTO.setProductDesc(productDesc);
                productDTO.setQuantity(quantity);
                productDTO.setPriceOrigin(priceOrigin);
                productDTO.setPriceOrder(priceOrder);
                BitmapDrawable drawable = (BitmapDrawable) imageRegisterProduct.getDrawable();
                Bitmap bitmap = drawable.getBitmap();

                ByteArrayOutputStream bbb = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bbb);
                String image = Base64Utils.bitmapToString(bitmap);
                productDTO.setImage0(image);
                productDTO.setStatus(true);
                if(validateData(productName,productOrigin,productDesc,quantity,priceOrigin,priceOrder)){
                    Intent intent = new Intent(getApplicationContext(), AddProductSuccess.class);
                    intent.putExtra("product", productDTO);
                    startActivity(intent);

                }else {
                    Toast.makeText(getApplication(),"Dữ liệu chưa chính xác!!!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public boolean validateData(String productName, String productOrigin, String productDesc, Integer quantity, BigDecimal priceOrigin, BigDecimal priceOrder){

        if(productName.equals("") || productOrigin.equals("") || productDesc.equals("") || quantity < 0 || priceOrigin.equals("") || priceOrder.equals("")){
            return false;
        }
        return true;
    }
    public void imageRegisterListener() {
        imageRegisterProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
    }

    public void imageScreenListener() {
        imageScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,SAVE_IMAGE_REQUEST);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            Uri filePath;
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageRegisterProduct.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else if (requestCode == SAVE_IMAGE_REQUEST){
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                imageRegisterProduct.setImageBitmap(bp);
            }
        }
    }
}