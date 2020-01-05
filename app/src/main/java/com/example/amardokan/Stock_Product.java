package com.example.amardokan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Stock_Product extends AppCompatActivity {
    ListView listView;
    DatabaseReference databaseReference;
    List<ProductData> productDataList;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock__product);
        listView = findViewById(R.id.productListId);
        //progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait Loading Data....");

        productDataList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Product");
        progressDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //progressDialog.show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productDataList.clear();
                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()){
                    ProductData productData = productSnapshot.getValue(ProductData.class);
                    productDataList.add(productData);
                }
                ProductListAdapter adapter = new ProductListAdapter(Stock_Product.this,productDataList);
                listView.setAdapter(adapter);
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }
}
