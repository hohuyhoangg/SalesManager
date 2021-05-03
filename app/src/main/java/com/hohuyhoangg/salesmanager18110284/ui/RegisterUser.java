package com.hohuyhoangg.salesmanager18110284.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hohuyhoangg.salesmanager18110284.R;

import java.io.File;
import java.io.IOException;

public class RegisterUser extends AppCompatActivity {

    private static final int SAVE_IMAGE_REQUEST = 100;
    private static final int PICK_IMAGE_REQUEST = 71;
    TextView txtLoginNow;
    Button btnRegister;
    ImageView imageRegister;
    ImageButton imageScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        initCreate();
        loginNowListener();
        imageRegisterListener();
        ImageScreenListener();
        registerNowListener();

    }

    public void initCreate() {
        txtLoginNow = (TextView) findViewById(R.id.text_login_now);
        btnRegister = (Button) findViewById(R.id.button_register);
        imageRegister = (ImageView) findViewById(R.id.image_register);
        imageScreen = (ImageButton) findViewById(R.id.image_screen);
    }
    public void loginNowListener() {
        txtLoginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginUser.class);
                startActivity(intent);
            }
        });
    }

    public void registerNowListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterUser.this, RegisterSuccess.class);
                startActivity(intent);
            }
        });
    }

    public void imageRegisterListener() {
        imageRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
    }

    public void ImageScreenListener() {
        imageScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,SAVE_IMAGE_REQUEST);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            Uri filePath;
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageRegister.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else if (requestCode == SAVE_IMAGE_REQUEST){
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                imageRegister.setImageBitmap(bp);
            }
        }
    }
}