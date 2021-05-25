package com.hohuyhoangg.salesmanager18110284.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.model.dao.UserDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.UserDTO;
import com.hohuyhoangg.salesmanager18110284.utils.Base64Utils;
import com.hohuyhoangg.salesmanager18110284.utils.FormatUtils;
import com.hohuyhoangg.salesmanager18110284.utils.StringUtils;

public class RegisterSuccess extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button btnRegisterSuccess;
    TextInputLayout inputOtpVerify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);
        initCreate();
        loginRegister();
    }

    public void initCreate(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        editor = sharedPreferences.edit();
        btnRegisterSuccess = (Button) findViewById(R.id.button_login_register);
        inputOtpVerify = (TextInputLayout) findViewById(R.id.input_otp_verify);
    }

    public void loginRegister(){
        btnRegisterSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otp = inputOtpVerify.getEditText().getText().toString().trim();
                String savedOtp = sharedPreferences.getString("otp", "");
                String savedUserId = sharedPreferences.getString("userRegister", "");
                try {
                    if (savedOtp.equals(otp)) {
                        Toast.makeText(getApplication(),"Mã xác thực chính xác!!!",Toast.LENGTH_SHORT).show();
                        UserDTO user = UserDAO.getInstance().getById(StringUtils.toLong(savedUserId));
                        user.setStatus(true);
                        UserDAO.getInstance().update(user);
                        Intent intent = new Intent(RegisterSuccess.this, LoginUser.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplication(),"Mã xác thực không chính xác!!!",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}