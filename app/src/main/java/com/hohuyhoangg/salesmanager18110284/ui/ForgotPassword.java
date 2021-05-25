package com.hohuyhoangg.salesmanager18110284.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.utils.GenerateUtils;
import com.hohuyhoangg.salesmanager18110284.utils.HashUtils;
import com.hohuyhoangg.salesmanager18110284.utils.MailAPI;
import com.hohuyhoangg.salesmanager18110284.utils.MailUtils;

public class ForgotPassword extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button btnForgotPassword;
    TextInputLayout textInputEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initCreate();
        forgotPassword();
    }

    public void initCreate(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        editor = sharedPreferences.edit();
        btnForgotPassword = (Button) findViewById(R.id.button_forgot_password);
        textInputEmail = (TextInputLayout) findViewById(R.id.input_email_or_phone);
    }

    public void forgotPassword(){
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = textInputEmail.getEditText().getText().toString().trim();
                sendMail(mail);
                Intent intent = new Intent(ForgotPassword.this, ResetPassword.class);
                startActivity(intent);
            }
        });
    }
    public void sendMail(String email){
        String otp = GenerateUtils.oneTimePassword(4);
        String sVerify = "OTP: " + otp;
        editor.putString("otpResetPassword", otp);
        editor.putString("email", email);
        editor.commit();
        MailAPI mailAPI = new MailAPI(this,email,"CODE",sVerify);
        mailAPI.execute();
    }
}