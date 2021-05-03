package com.hohuyhoangg.salesmanager18110284.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.hohuyhoangg.salesmanager18110284.R;

public class LoginUser extends AppCompatActivity {

    TextView txtForgotPassword,txtRegisterNow;
    TextInputLayout inputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        initCreate();
        forgotPassword();
        registerAccount();
    }



    public void initCreate(){
        txtForgotPassword = (TextView) findViewById(R.id.text_forgot_password);
        txtRegisterNow = (TextView) findViewById(R.id.text_register);
        inputPassword = (TextInputLayout) findViewById(R.id.input_password);
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
}