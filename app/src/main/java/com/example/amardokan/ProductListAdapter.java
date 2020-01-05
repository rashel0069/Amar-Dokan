package com.example.amardokan;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ProductListAdapter extends ArrayAdapter {
    private Activity context;
    private List<ProductData> productDataList;

    public ProductListAdapter(Activity context, List<ProductData> productData) {
        super(context, R.layout.list_layout, productData);
        this.context = context;
        this.productDataList = productData;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textProductName = listViewItem.findViewById(R.id.productnameID);
        TextView textProductValue = listViewItem.findViewById(R.id.productastockId);

        ProductData productData = productDataList.get(position);

        textProductName.setText(productData.getProductName());
        textProductValue.setText(productData.getProductAvailable());

        return listViewItem;

    }
}
