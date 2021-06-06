package com.hohuyhoangg.salesmanager18110284.ui.nav;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewProductAdapter;
import com.hohuyhoangg.salesmanager18110284.model.dao.ProductDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.UserDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.ProductDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.UserDTO;
import com.hohuyhoangg.salesmanager18110284.ui.LoginSuccess;
import com.hohuyhoangg.salesmanager18110284.ui.LoginUser;
import com.hohuyhoangg.salesmanager18110284.utils.StringUtils;

import java.util.List;

public class AccountFragment extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageButton btnLoginNewAccount;
    TextView textNameAccount;
    Button btnLoginAccount;
    RecyclerView recyclerViewProductAccount;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initCreate();
        View view;
        String savedStatusAccount = sharedPreferences.getString("account", "");
        if(savedStatusAccount.equals("0")) {
            view = inflater.inflate(R.layout.fragment_account_dont_login, container, false);
            btnLoginAccount = (Button) view.findViewById(R.id.button_login_fragment);
            LoginAccountListener();
        } else {
            view = inflater.inflate(R.layout.fragment_account, container, false);

            btnLoginNewAccount = (ImageButton) view.findViewById(R.id.image_button_account);
            textNameAccount = (TextView) view.findViewById(R.id.text_view_name_account);
            recyclerViewProductAccount = (RecyclerView) view.findViewById(R.id.recyclerviewProduct_account_id);
            init();
            LoginNewAccountListener();
        }

        return view;
    }

    public void initCreate() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = sharedPreferences.edit();
//        editor.putString("account", "0");
//        editor.commit();

    }

    public void LoginNewAccountListener() {
        btnLoginNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedStatusAccount = sharedPreferences.getString("account", "");
                if (savedStatusAccount.equals("0")) {
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), LoginSuccess.class);
                    startActivity(intent);
                }

            }
        });
    }
    public void LoginAccountListener() {
        btnLoginAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginUser.class);
                startActivity(intent);
            }
        });
    }
    public void init() {
        String savedStatusAccount = sharedPreferences.getString("account", "");
        if (!savedStatusAccount.equals("0")) {
            UserDTO user = UserDAO.getInstance().getById(StringUtils.toLong(savedStatusAccount));
            if (user != null) {
                String name = user.getLastName() + " " + user.getFirstName();
                textNameAccount.setText(name);

                List<ProductDTO> products = ProductDAO.getInstance().getListProductByUserId(user.getUserId());
                RecyclerViewProductAdapter recyclerViewProductAdapter = new RecyclerViewProductAdapter(getContext(), products);

                GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerViewProductAccount.setLayoutManager(layoutManager);
                recyclerViewProductAccount.setHasFixedSize(true);
                recyclerViewProductAccount.setAdapter(recyclerViewProductAdapter);
                recyclerViewProductAdapter.notifyDataSetChanged();
            }
        }
    }
}