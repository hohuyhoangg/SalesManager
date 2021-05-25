package com.hohuyhoangg.salesmanager18110284.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hohuyhoangg.salesmanager18110284.R;
import com.hohuyhoangg.salesmanager18110284.model.dto.BrandDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.NotificationDTO;
import com.hohuyhoangg.salesmanager18110284.utils.Base64Utils;

import java.util.List;

public class RecyclerViewNotificationAdapter extends RecyclerView.Adapter<RecyclerViewNotificationAdapter.MyViewHolder>{
    private Context mContext;
    private List<NotificationDTO> mData;

    public RecyclerViewNotificationAdapter(Context mContext, List<NotificationDTO> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerViewNotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.cardview_item_notification,parent,false);
        return new RecyclerViewNotificationAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewNotificationAdapter.MyViewHolder holder, int position) {

        holder.img_notification_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.tv_notification_title.setText(mData.get(position).getTitle());
        holder.tv_notification_text.setText(mData.get(position).getDescription());
        //set click

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView tv_notification_title,tv_notification_text;
        ImageView img_notification_thumbnail;
        CardView cardView;
        public  MyViewHolder (View itemView){
            super(itemView);

            tv_notification_title = (TextView) itemView.findViewById(R.id.notification_title_id);
            tv_notification_text= (TextView) itemView.findViewById(R.id.notification_text_id);
            img_notification_thumbnail = (ImageView) itemView.findViewById(R.id.notification_img_id);
            cardView = (CardView) itemView.findViewById(R.id.card_view_notification_id);
        }
    }
}
