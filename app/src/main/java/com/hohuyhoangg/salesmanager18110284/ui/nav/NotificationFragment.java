package com.hohuyhoangg.salesmanager18110284.ui.nav;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewBrandAdapter;
import com.hohuyhoangg.salesmanager18110284.adapter.RecyclerViewNotificationAdapter;
import com.hohuyhoangg.salesmanager18110284.model.dao.BrandDAO;
import com.hohuyhoangg.salesmanager18110284.model.dao.NotificationDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.BrandDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.NotificationDTO;

import java.util.List;

public class NotificationFragment extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    List<NotificationDTO> notifications;
    RecyclerView recyclerViewNotification;
    TextView textNotification;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerViewNotification = (RecyclerView) view.findViewById(R.id.recyclerviewNotification_id);
        textNotification = (TextView) view.findViewById(R.id.text_notification);
        init();
        return view;
    }
    public void init(){
        initData();
    }
    public void initData(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = sharedPreferences.edit();
        String savedStatusAccount = sharedPreferences.getString("account", "");
        if (!savedStatusAccount.equals("0")) {
            textNotification.setVisibility(View.GONE);
            notifications = NotificationDAO.getInstance().initData();
            RecyclerViewNotificationAdapter recyclerViewNotificationAdapter = new RecyclerViewNotificationAdapter(getContext(), notifications);

            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerViewNotification.setLayoutManager(layoutManager);
            recyclerViewNotification.setHasFixedSize(true);
            recyclerViewNotification.setAdapter(recyclerViewNotificationAdapter);
            recyclerViewNotificationAdapter.notifyDataSetChanged();
        }
        else {
            textNotification.setText("Xin hãy đăng nhập để xem thông báo");
        }


    }
}