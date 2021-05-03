package com.hohuyhoangg.salesmanager18110284.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hohuyhoangg.salesmanager18110284.R;

public class ForgotPassword extends AppCompatActivity {

    Button btnForgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initCreate();
        forgotPassword();
    }

    public void initCreate(){
        btnForgotPassword = (Button) findViewById(R.id.button_forgot_password);
    }

    public void forgotPassword(){
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ForgotPassword.this, ResetPassword.class);
                startActivity(intent);
            }
        });
    }
}