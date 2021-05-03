package com.hohuyhoangg.salesmanager18110284.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hohuyhoangg.salesmanager18110284.R;

public class ResetPasswordSuccess extends AppCompatActivity {


    Button btnLoginReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_success);

        initCreate();
        loginReset();
    }

    public void initCreate(){
        btnLoginReset = (Button) findViewById(R.id.button_login_reset);
    }

    public void loginReset(){
        btnLoginReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResetPasswordSuccess.this, LoginUser.class);
                startActivity(intent);
            }
        });
    }
}