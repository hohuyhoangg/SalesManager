package com.hohuyhoangg.salesmanager18110284.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.model.dao.UserDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.UserDTO;
import com.hohuyhoangg.salesmanager18110284.utils.Base64Utils;
import com.hohuyhoangg.salesmanager18110284.utils.GenerateUtils;
import com.hohuyhoangg.salesmanager18110284.utils.HashUtils;
import com.hohuyhoangg.salesmanager18110284.utils.MailAPI;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterUser extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final int SAVE_IMAGE_REQUEST = 100;
    private static final int PICK_IMAGE_REQUEST = 71;
    String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    TextView txtLoginNow;
    Button btnRegister;
    ImageView imageRegister;
    ImageButton imageScreen;
    TextInputLayout inputFirstName, inputLastName, inputEmail, inputPhone, inputPassword, inputConfirmPassword;
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
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        editor = sharedPreferences.edit();
        txtLoginNow = (TextView) findViewById(R.id.text_login_now);
        btnRegister = (Button) findViewById(R.id.button_register);
        imageRegister = (ImageView) findViewById(R.id.image_register);
        imageScreen = (ImageButton) findViewById(R.id.image_screen);
        inputFirstName = (TextInputLayout) findViewById(R.id.input_first_name);
        inputLastName = (TextInputLayout) findViewById(R.id.input_last_name);
        inputEmail = (TextInputLayout) findViewById(R.id.input_email);
        inputPhone = (TextInputLayout) findViewById(R.id.input_phone);
        inputPassword = (TextInputLayout) findViewById(R.id.input_password_register);
        inputConfirmPassword = (TextInputLayout) findViewById(R.id.input_password_confirm);
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
                String firstName = inputFirstName.getEditText().getText().toString().trim();
                String lastName = inputLastName.getEditText().getText().toString().trim();
                String email = inputEmail.getEditText().getText().toString().trim();
                String phone = inputPhone.getEditText().getText().toString().trim();
                String password = inputPassword.getEditText().getText().toString().trim();
                String passwordConfirm = inputConfirmPassword.getEditText().getText().toString().trim();

                boolean validateInsert = validateData(firstName,lastName,email,phone,password,passwordConfirm);
                if(validateInsert == true){
                    List<UserDTO> users = UserDAO.getInstance().gets();
                    boolean flag = true;
                    for (UserDTO user: users) {
                        if(user.getEmail().equals(email) || user.getPhoneNumber().equals(phone)){
                            Toast.makeText(getApplication(),"Email hoặc số điện thoại đã được sử dụng!!!",Toast.LENGTH_SHORT).show();
                            flag = false;
                            break;
                        }
                    }
                    if(flag){

                        if (password.equals(passwordConfirm)) {
                            UserDTO userDTO = new UserDTO();

                            userDTO.setFirstName(firstName);
                            userDTO.setLastName(lastName);
                            userDTO.setGender("Male");
                            userDTO.setDateOfBirth("Null");
                            userDTO.setEmail(email);
                            userDTO.setPhoneNumber(phone);

                            password = HashUtils.getMd5(password);
                            userDTO.setPassword(password);
                            userDTO.setStatus(false);

                            BitmapDrawable drawable = (BitmapDrawable) imageRegister.getDrawable();
                            Bitmap bitmap = drawable.getBitmap();

                            ByteArrayOutputStream bbb = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bbb);

                            String image = Base64Utils.bitmapToString(bitmap);
                            userDTO.setImagePath(image);
                            userDTO.setUserType("CUSTOMER");
                            Long userId = UserDAO.getInstance().insert(userDTO);

                            if(userId>0){
                                sendMail(email,userId);
                                Intent intent = new Intent(RegisterUser.this, RegisterSuccess.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplication(),"Tạo tài khoản không thành công!!!",Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplication(),"Mật khẩu không khớp!!!",Toast.LENGTH_SHORT).show();
                        }

                    }

                } else {
                    Toast.makeText(getApplication(),"Vui lòng nhập đúng thông tin!!!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void sendMail(String email, Long userId){
        String otp = GenerateUtils.oneTimePassword(4);
        String sVerify = "OTP: " + otp;
        editor.putString("otp", otp);
        editor.putString("userRegister", userId.toString());
        editor.commit();
        MailAPI mailAPI = new MailAPI(this,email,"CODE",sVerify);
        mailAPI.execute();
    }
    public boolean validateData(String firstName, String lastName, String email, String phone, String password, String confirmPassword){

        if(firstName.equals("") || lastName.equals("") || email.equals("") || phone.equals("") || password.equals("") || confirmPassword.equals("")){
            return false;
        }else {
            Boolean b = email.matches(EMAIL_REGEX);
            if(b.equals(false)){
                return false;
            }
            else if(phone.length()<10){
                return false;
            }
        }
        return true;
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