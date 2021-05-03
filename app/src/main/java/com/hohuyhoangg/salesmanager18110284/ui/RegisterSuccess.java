package com.hohuyhoangg.salesmanager18110284.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hohuyhoangg.salesmanager18110284.R;

public class RegisterSuccess extends AppCompatActivity {

    Button btnRegisterSuccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);
        initCreate();
        loginRegister();
    }

    public void initCreate(){
        btnRegisterSuccess = (Button) findViewById(R.id.button_login_register);
    }

    public void loginRegister(){
        btnRegisterSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterSuccess.this, LoginUser.class);
                startActivity(intent);
            }
        });
    }
}