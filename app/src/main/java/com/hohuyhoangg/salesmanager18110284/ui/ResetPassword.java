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
import com.hohuyhoangg.salesmanager18110284.utils.GenerateUtils;
import com.hohuyhoangg.salesmanager18110284.utils.HashUtils;
import com.hohuyhoangg.salesmanager18110284.utils.MailUtils;
import com.hohuyhoangg.salesmanager18110284.utils.StringUtils;

public class ResetPassword extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button btnResetPassword;
    TextInputLayout inputOtpReset, inputNewPass, inputNewPassConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        initCreate();
        resetPassword();
    }

    public void initCreate(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        editor = sharedPreferences.edit();
        inputOtpReset = (TextInputLayout) findViewById(R.id.input_code);
        inputNewPass = (TextInputLayout) findViewById(R.id.input_password_reset);
        inputNewPassConfirm = (TextInputLayout) findViewById(R.id.input_password_reset_confirm);
        btnResetPassword = (Button) findViewById(R.id.button_reset_password);
    }

    public void resetPassword(){
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String savedOtp = sharedPreferences.getString("otpResetPassword", "");
                String savedEmail = sharedPreferences.getString("email", "");
                try {
                    if (savedOtp.equals(inputOtpReset.getEditText().getText().toString().trim())) {

                        if(inputNewPass.getEditText().getText().toString().equals(inputNewPassConfirm.getEditText().getText().toString())){

                            UserDTO user = UserDAO.getInstance().getByEmail(savedEmail);


                            user.setPassword(HashUtils.getMd5(inputNewPass.getEditText().getText().toString().trim()));
                            UserDAO.getInstance().update(user);
                            Intent intent = new Intent(ResetPassword.this, ResetPasswordSuccess.class);
                            startActivity(intent);
                            Toast.makeText(getApplication(),"Thay đổi mật khẩu thành công!!!",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplication(),"Mật khẩu không khớp!!!",Toast.LENGTH_SHORT).show();
                        }
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