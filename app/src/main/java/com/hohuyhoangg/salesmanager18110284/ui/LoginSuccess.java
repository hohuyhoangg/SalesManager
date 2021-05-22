package com.hohuyhoangg.salesmanager18110284.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.hohuyhoangg.salesmanager18110284.MainActivity;
import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.model.dao.UserDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.UserDTO;
import com.hohuyhoangg.salesmanager18110284.ui.nav.AccountFragment;
import com.hohuyhoangg.salesmanager18110284.utils.Base64Utils;
import com.hohuyhoangg.salesmanager18110284.utils.HashUtils;
import com.hohuyhoangg.salesmanager18110284.utils.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class LoginSuccess extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final int SAVE_IMAGE_REQUEST = 100;
    private static final int PICK_IMAGE_REQUEST = 71;
    private DatePickerDialog datePickerDialog;
    TextInputLayout inputDateOfBirth, inputPasswordOld, inputPasswordNew, inputPasswordConfirm, inputFirsNameLogin, inputLastNameLogin, inputEmailLogin, inputPhoneLogin;
    TextView nameAccount;
    CheckBox checkBoxChangePassword;
    ImageButton logoutLogin, imageScreenLogin;
    RadioButton maleLogin, femaleLogin;
    ImageView imageViewLogin;
    Button saveChangesLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        initCreate();
        init();
    }

    private void init() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        editor = sharedPreferences.edit();
        initData();
        checkChangePassword();
        logoutListener();
        imageRegisterListener();
        ImageScreenListener();
        saveChangeListener();
    }

    public void initCreate() {
        inputDateOfBirth = (TextInputLayout) findViewById(R.id.input_birth_login_success);
        inputPasswordOld = (TextInputLayout) findViewById(R.id.input_old_password_login_success);
        inputPasswordNew = (TextInputLayout) findViewById(R.id.input_new_password_login_success);
        inputPasswordConfirm = (TextInputLayout) findViewById(R.id.input_password_confirm_login_success);
        inputFirsNameLogin = (TextInputLayout) findViewById(R.id.input_first_name_login_success);
        inputLastNameLogin = (TextInputLayout) findViewById(R.id.input_last_name_login_success);
        inputEmailLogin = (TextInputLayout) findViewById(R.id.input_email_login_success);
        inputPhoneLogin = (TextInputLayout) findViewById(R.id.input_phone_login_success);
        checkBoxChangePassword = (CheckBox) findViewById(R.id.checkbox_change_password_login_success);
        logoutLogin = (ImageButton) findViewById(R.id.logout_login_success);
        imageScreenLogin = (ImageButton) findViewById(R.id.image_screen_login_success);
        nameAccount = (TextView) findViewById(R.id.name_login_success);
        maleLogin = (RadioButton) findViewById(R.id.rdBtnMale);
        femaleLogin = (RadioButton) findViewById(R.id.rdBtnFemale);
        imageViewLogin = (ImageView) findViewById(R.id.image_login_success);
        saveChangesLogin = (Button) findViewById(R.id.button_save_change_login_success);
        initDatePicker();
        inputDateOfBirth.getEditText().setText(getTodayDate());
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                inputDateOfBirth.getEditText().setText(date);
            }

        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void checkChangePassword() {
        checkBoxChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxChangePassword.isChecked()) {
                    inputPasswordOld.setVisibility(View.VISIBLE);
                    inputPasswordNew.setVisibility(View.VISIBLE);
                    inputPasswordConfirm.setVisibility(View.VISIBLE);
                } else {
                    inputPasswordOld.setVisibility(View.INVISIBLE);
                    inputPasswordNew.setVisibility(View.INVISIBLE);
                    inputPasswordConfirm.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void logoutListener() {
        logoutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("account", "0");
                editor.commit();
                Toast.makeText(getApplication(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initData() {
        String savedStatusAccount = sharedPreferences.getString("account", "");
        try {
            if (!savedStatusAccount.equals("0")) {
                UserDTO user = UserDAO.getInstance().getById(StringUtils.toLong(savedStatusAccount));
                nameAccount.setText(user.getLastName() + " " + user.getFirstName());
                inputFirsNameLogin.getEditText().setText(user.getFirstName());
                inputLastNameLogin.getEditText().setText(user.getLastName());
                String gender = user.getGender();
                if (gender.equals("Male")) {
                    maleLogin.setChecked(true);
                } else {
                    femaleLogin.setChecked(true);
                }
                inputDateOfBirth.getEditText().setText(user.getDateOfBirth());
                inputEmailLogin.getEditText().setText(user.getEmail());
                inputPhoneLogin.getEditText().setText(user.getPhoneNumber());
                imageViewLogin.setImageBitmap(Base64Utils.stringToBitmap(user.getImagePath()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void imageRegisterListener() {
        imageViewLogin.setOnClickListener(new View.OnClickListener() {
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
        imageScreenLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, SAVE_IMAGE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Uri filePath;
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageViewLogin.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == SAVE_IMAGE_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                imageViewLogin.setImageBitmap(bp);
            }
        }
    }

    public void saveChangeListener() {
        saveChangesLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedStatusAccount = sharedPreferences.getString("account", "");
                try {
                    if (!savedStatusAccount.equals("0")) {

                        String firstName = inputFirsNameLogin.getEditText().getText().toString().trim();
                        String lastName = inputLastNameLogin.getEditText().getText().toString().trim();
                        String gender = "";
                        if (maleLogin.isChecked()) {
                            gender = "Male";
                        } else {
                            gender = "Female";
                        }
                        String dateOfBirth = inputDateOfBirth.getEditText().getText().toString().trim();
                        String email = inputEmailLogin.getEditText().getText().toString().trim();
                        String phone = inputPhoneLogin.getEditText().getText().toString().trim();

                        BitmapDrawable drawable = (BitmapDrawable) imageViewLogin.getDrawable();
                        Bitmap bitmap = drawable.getBitmap();

                        ByteArrayOutputStream bbb = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bbb);

                        String image = Base64Utils.bitmapToString(bitmap);



                        UserDTO user = UserDAO.getInstance().getById(StringUtils.toLong(savedStatusAccount));

                        user.setFirstName(firstName);
                        user.setLastName(lastName);
                        user.setGender(gender);
                        user.setDateOfBirth(dateOfBirth);
                        user.setEmail(email);
                        user.setPhoneNumber(phone);
                        user.setImagePath(image);
                        if (checkBoxChangePassword.isChecked()) {
                            String oldPassword = inputPasswordOld.getEditText().getText().toString().trim();
                            String newPassword = inputPasswordNew.getEditText().getText().toString().trim();
                            String confirmPassword = inputPasswordConfirm.getEditText().getText().toString().trim();

                            String oldPasswordMd5 = HashUtils.getMd5(oldPassword);
                            if (oldPasswordMd5.equals(user.getPassword())) {
                                if (newPassword.equals(confirmPassword)) {
                                    String newPasswordMd5 = HashUtils.getMd5(newPassword);
                                    user.setPassword(newPasswordMd5);
                                    if (oldPassword.equals(newPassword)) {
                                        Toast.makeText(getApplication(), "Mật khẩu đã được sử dụng!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        int result = UserDAO.getInstance().update(user);
                                        if (result != 0) {
                                            editor.putString("account", "0");
                                            editor.commit();
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                            Toast.makeText(getApplication(), "Thay đổi thông tin thành công!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplication(), "Thay đổi thông tin thất bại!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {
                                    Toast.makeText(getApplication(), "Mật khẩu mới không khớp!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplication(), "Mật khẩu cũ không chính xác!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            int result = UserDAO.getInstance().update(user);
                            if (result != 0) {
                                Toast.makeText(getApplication(), "Thay đổi thông tin thành công!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplication(), "Thay đổi thông tin thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        }

                    } else {
                        Toast.makeText(getApplication(), "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}