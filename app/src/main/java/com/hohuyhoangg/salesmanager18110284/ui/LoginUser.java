package com.hohuyhoangg.salesmanager18110284.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.hohuyhoangg.salesmanager18110284.MainActivity;
import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.controller.LoginUserController;
import com.hohuyhoangg.salesmanager18110284.model.dao.UserDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.UserDTO;
import com.hohuyhoangg.salesmanager18110284.utils.Base64Utils;
import com.hohuyhoangg.salesmanager18110284.utils.HashUtils;

public class LoginUser extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView txtForgotPassword,txtRegisterNow;
    TextInputLayout inputUsername,inputPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        initCreate();
        forgotPassword();
        registerAccount();
        loginListener();
    }



    public void initCreate(){
        txtForgotPassword = (TextView) findViewById(R.id.text_forgot_password);
        txtRegisterNow = (TextView) findViewById(R.id.text_register);
        inputPassword = (TextInputLayout) findViewById(R.id.input_password);
        inputUsername = (TextInputLayout) findViewById(R.id.input_username);
        btnLogin = (Button) findViewById(R.id.button_login);
    }
    public void forgotPassword() {
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(intent);
            }
        });
    }
    public void registerAccount(){
        txtRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterUser.class);
                startActivity(intent);
            }
        });
    }
    public void loginListener(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = inputUsername.getEditText().getText().toString();
                String password = inputPassword.getEditText().getText().toString();
                password = HashUtils.getMd5(password);
                try {
                    Long userId = LoginUserController.getInstance().checkLogin(userName,password);
                    if(userId != 0){
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
                        editor = sharedPreferences.edit();
                        editor.putString("account", userId.toString());
                        //editor.putString("password", password);
                        editor.commit();
                        UserDTO userDTO = UserDAO.getInstance().getById(userId);
                        if(userDTO.getUserType().equals("CUSTOMER")){
                            Toast.makeText(getApplication(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else if(userDTO.getUserType().equals("SELLER")) {
                            Toast.makeText(getApplication(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainSeller.class);
                            startActivity(intent);
                        }
                    }
                    else {
                        Toast.makeText(getApplication(),"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}