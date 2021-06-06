package com.hohuyhoangg.salesmanager18110284.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hohuyhoangg.salesmanager18110284.MainActivity;
import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.model.dao.BillDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.BillDetailDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.CartDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.DiscountDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.ProductDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.BillDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.BillDetailDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.CartDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.DiscountDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.ProductDTO;
import com.hohuyhoangg.salesmanager18110284.utils.StringUtils;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class Order extends AppCompatActivity {

    TextView changeAddress,feeAllOrder,feeShipOrder,feeDiscountOrder,feeOrderAllEnd;
    Long feeShip = 0L, feeDiscount = 0L, feeAll = 0L, feeAllEnd = 0L;
    RadioButton rbDeliverySave, rbDeliveryExpress;
    EditText etDiscount;
    Button saveDiscount,order;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    List<CartDTO> carts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        changeAddress = (TextView) findViewById(R.id.address_change_in_order);
        feeOrderAllEnd = (TextView) findViewById(R.id.order_fee_all);
        feeAllOrder = (TextView) findViewById(R.id.fee_all_order);
        feeShipOrder = (TextView) findViewById(R.id.fee_delivery_order);
        feeDiscountOrder = (TextView) findViewById(R.id.fee_discount_order);
        rbDeliverySave = (RadioButton) findViewById(R.id.rdDeliverySave);
        rbDeliveryExpress = (RadioButton) findViewById(R.id.rdDeliveryExpress);
        saveDiscount = (Button) findViewById(R.id.button_save_discount);
        order = (Button) findViewById(R.id.order_money);
        etDiscount = (EditText) findViewById(R.id.edittext_discount_save);
        initCreate();
        init();

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void initCreate() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        editor = sharedPreferences.edit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_order);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void init() {
        changeAddressListener();
        initData();
        orderListener();
    }
    public void changeAddressListener(){
        changeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),Address.class);

                startActivity(intent);
            }
        });
    }
    public void initData(){
        String savedStatusAccount = sharedPreferences.getString("account", "");
        carts = CartDAO.getInstance().getByUser(StringUtils.toLong(savedStatusAccount));
        Long feeAllTemp = 0L;

        for (CartDTO cart : carts) {
            Long productId = cart.getProductId();
            ProductDTO product = ProductDAO.getInstance().getById(productId);

            String feeItem = product.getPriceOrder().toString();

            Long feeItems = StringUtils.toLong(feeItem);
            Long item = feeItems/100;
            feeAllTemp += item * cart.getQuantity();
        }
        feeAll = feeAllTemp;

        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(feeAllTemp);
        String a = str1.substring(1);

        feeAllOrder.setText("Tạm tính: " + a + " đ");
        feeShip = 50000L;
        String str2 = currencyVN.format(feeShip);
        String b = str2.substring(1);
        feeShipOrder.setText("Phí vận chuyển: " + b + " đ");

        feeAllEnd = feeAllTemp + 50000L;
        String str3 = currencyVN.format(feeAllEnd);
        String c = str3.substring(1);
        feeOrderAllEnd.setText("Thành tiền: " + c + " đ");

        rbDeliverySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbDeliverySave.isChecked()) {
                    feeShip = 25000L;
                } else {
                    feeShip = 50000L;
                }
                String str4 = currencyVN.format(feeShip);
                String d = str4.substring(1);
                feeShipOrder.setText("Phí vận chuyển: " + d + " đ");
                feeAllEnd = feeAll - feeDiscount + feeShip;
                String str5 = currencyVN.format(feeAllEnd);
                String e = str5.substring(1);
                feeOrderAllEnd.setText("Thành tiền: " + e + " đ");
            }
        });
        rbDeliveryExpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbDeliverySave.isChecked()) {
                    feeShip = 25000L;
                } else {
                    feeShip = 50000L;
                }
                String str6 = currencyVN.format(feeShip);
                String f = str6.substring(1);
                feeShipOrder.setText("Phí vận chuyển: " + f + " đ");

                feeAllEnd = feeAll - feeDiscount + feeShip;
                String str7 = currencyVN.format(feeAllEnd);
                String g = str7.substring(1);
                feeOrderAllEnd.setText("Thành tiền: " + g + " đ");
            }
        });
        feeDiscount = 0L;
        String str8 = currencyVN.format(feeDiscount);
        String h = str8.substring(1);
        feeDiscountOrder.setText("Khuyến mãi: " + h + " đ");

        saveDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String discountText = etDiscount.getText().toString();
                List<DiscountDTO> discounts = DiscountDAO.getInstance().getInitData();
                feeDiscount = 0L;
                for (DiscountDTO discount: discounts) {
                    if(discountText.equalsIgnoreCase(discount.getCode())){
                        feeDiscount = discount.getDiscountMoney();
                        break;
                    }
                }
                String str9 = currencyVN.format(feeDiscount);
                String q = str9.substring(1);
                feeDiscountOrder.setText("Khuyến mãi: " + q + " đ");

                feeAllEnd = feeAll - feeDiscount + feeShip;
                String str10 = currencyVN.format(feeAllEnd);
                String f1 = str10.substring(1);
                feeOrderAllEnd.setText("Thành tiền: " + f1 + " đ");
            }
        });

    }

    public void orderListener(){
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedStatusAccount = sharedPreferences.getString("account", "");
                carts = CartDAO.getInstance().getByUser(StringUtils.toLong(savedStatusAccount));

                if(savedStatusAccount.equals("0")){
                    Toast.makeText(getApplication(), "Vui lòng đăng nhập để thanh toán.", Toast.LENGTH_SHORT).show();
                } else {
                    BillDTO billDTO = new BillDTO();
                    billDTO.setUserId(StringUtils.toLong(savedStatusAccount));
                    billDTO.setPrice(feeAllEnd.toString());
                    billDTO.setPriceProduct(feeAll.toString());
                    billDTO.setPriceDelivery(feeShip.toString());
                    billDTO.setPriceDiscount(feeDiscount.toString());

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDateTime now = LocalDateTime.now();

                    billDTO.setDate(dtf.format(now).toString());

                    Long billId = BillDAO.getInstance().insert(billDTO);

                    if(billId > 0){
                        for (CartDTO cart: carts) {
                            BillDetailDTO billDetailDTO = new BillDetailDTO();
                            billDetailDTO.setBillId(billId);
                            Long productId = cart.getProductId();
                            billDetailDTO.setProductId(productId);

                            billDetailDTO.setQuantity(cart.getQuantity());

                            BillDetailDAO.getInstance().insert(billDetailDTO);

                            CartDAO.getInstance().deleteItem(cart);
                        }
                        Toast.makeText(getApplication(), "Thanh toán thành công.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplication(), OrderSuccess.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplication(), "Thanh toán không thành công.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}