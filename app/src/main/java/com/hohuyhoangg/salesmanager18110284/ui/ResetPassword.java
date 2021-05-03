package com.hohuyhoangg.salesmanager18110284.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hohuyhoangg.salesmanager18110284.R;

public class ResetPassword extends AppCompatActivity {

    Button btnResetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        initCreate();
        resetPassword();
    }

    public void initCreate(){
        btnResetPassword = (Button) findViewById(R.id.button_reset_password);
    }

    public void resetPassword(){
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResetPassword.this, ResetPasswordSuccess.class);
                startActivity(intent);
            }
        });
    }
}