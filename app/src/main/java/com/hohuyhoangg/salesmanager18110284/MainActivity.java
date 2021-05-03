package com.hohuyhoangg.salesmanager18110284;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hohuyhoangg.salesmanager18110284.ui.LoginUser;
import com.hohuyhoangg.salesmanager18110284.ui.RegisterUser;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCreate();
        Login();

    }
    public void initCreate(){
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    public void Login(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LoginUser.class);
                startActivity(intent);
            }
        });
    }
}