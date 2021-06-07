package com.hohuyhoangg.salesmanager18110284.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hohuyhoangg.salesmanager18110284.model.dto.BrandDTO;

import java.util.List;

public class CustomSpinnerAdapter  extends BaseAdapter {

    private LayoutInflater flater;
    private List<BrandDTO> dtoList;
    private int listItemLayoutResource;
    private int textViewItemName;
    private int textViewItemOrigin;

    public CustomSpinnerAdapter(Activity context, int listItemLayoutResource,
                         int textViewItemName, int textViewItemOrigin,
                         List<BrandDTO> list) {
        this.listItemLayoutResource = listItemLayoutResource;

        this.textViewItemName = textViewItemName;
        this.textViewItemOrigin = textViewItemOrigin;
        this.dtoList = list;
        this.flater = context.getLayoutInflater();
    }
    @Override
    public int getCount() {
        if(this.dtoList == null)  {
            return 0;
        }
        return this.dtoList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.dtoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        BrandDTO brandDTO = (BrandDTO) this.getItem(position);
        return brandDTO.getBrandId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BrandDTO brandDTO = (BrandDTO) getItem(position);

        View rowView = this.flater.inflate(this.listItemLayoutResource, null,true);


        TextView textViewItemName = (TextView) rowView.findViewById(this.textViewItemName);
        textViewItemName.setText(brandDTO.getBrandName());

        TextView textViewItemOrigin = (TextView) rowView.findViewById(this.textViewItemOrigin);
        textViewItemOrigin.setText(brandDTO.getBrandOrigin());

        return rowView;
    }
}
