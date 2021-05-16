package com.hohuyhoangg.salesmanager18110284.ui.nav;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.model.dao.UserDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.UserDTO;
import com.hohuyhoangg.salesmanager18110284.ui.LoginSuccess;
import com.hohuyhoangg.salesmanager18110284.ui.LoginUser;
import com.hohuyhoangg.salesmanager18110284.utils.StringUtils;

public class AccountFragment extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageButton btnLoginNewAccount;
    TextView textNameAccount;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initCreate();
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        btnLoginNewAccount = (ImageButton) view.findViewById(R.id.image_button_account);
        textNameAccount = (TextView) view.findViewById(R.id.text_view_name_account);
        init();
        LoginNewAccountListener();
        return view;
    }
    public void initCreate(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = sharedPreferences.edit();
//        editor.putString("account", "0");
//        editor.commit();

    }
    public void LoginNewAccountListener(){
        btnLoginNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedStatusAccount = sharedPreferences.getString("account", "");
                if(savedStatusAccount.equals("0")){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getContext(), LoginSuccess.class);
                    startActivity(intent);
                }

            }
        });
    }
    public void init(){
        String savedStatusAccount = sharedPreferences.getString("account", "");
        if(!savedStatusAccount.equals("0")){
            UserDTO user = UserDAO.getInstance().getById(StringUtils.toLong(savedStatusAccount));
            String name = user.getLastName() + " " + user.getFirstName();
            textNameAccount.setText(name);
        }
    }
}