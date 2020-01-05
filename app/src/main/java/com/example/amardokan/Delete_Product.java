package com.example.amardokan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Delete_Product extends AppCompatActivity {
    EditText deleteText;
    TextView deleteName,deleteQuantity;
    Button del_search,confirmDelete;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__product);
        deleteText = findViewById(R.id.del_search_byId);
        deleteName = findViewById(R.id.del_preview_nameId);
        deleteQuantity = findViewById(R.id.del_preview_stockId);
        del_search = findViewById(R.id.del_search_button);
        confirmDelete = findViewById(R.id.delete_prdoductId);

        databaseReference = FirebaseDatabase.getInstance().getReference("Product");
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Wait.....");

        del_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!deleteText.getText().toString().isEmpty()){
                    progressDialog.show();
                    findData();
                }else {
                    deleteText.setError("Error");
                    progressDialog.dismiss();
                }
            }
        });
        confirmDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                if (!TextUtils.isEmpty(deleteText.getText().toString())){
                    String proNames = deleteText.getText().toString();
                    deleteData(proNames);
                }else {
                    deleteText.setError("Enter product name");
                    deleteName.setText("");
                    deleteQuantity.setText("0");
                    confirmDelete.setVisibility(View.GONE);
                    progressDialog.dismiss();
                }
            }
        });
    }

    private void deleteData(String proNames) {
        databaseReference.child(proNames).removeValue();
        Toast.makeText(this, "Product Delete Successfully", Toast.LENGTH_SHORT).show();
        deleteName.setText("");
        deleteQuantity.setText("0");
        confirmDelete.setVisibility(View.GONE);
        progressDialog.dismiss();
    }

    private void findData() {
        final String pName = deleteText.getText().toString();
        databaseReference.child(pName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    ProductData productData = dataSnapshot.getValue(ProductData.class);
                    deleteName.setText(productData.getProductName());
                    deleteQuantity.setText(productData.getProductAvailable());
                    confirmDelete.setVisibility(View.VISIBLE);
                    progressDialog.dismiss();
                }else {
                    Toast.makeText(Delete_Product.this, "No Product Found", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Delete_Product.this, "No Product Found", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
