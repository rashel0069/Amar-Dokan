package com.example.amardokan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class Update_Product extends AppCompatActivity {
    EditText editTextSearch;
    TextView preProductName,preProductValue;
    Button buttonSearch;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__product);
        editTextSearch = findViewById(R.id.search_byId);
        preProductName = findViewById(R.id.preview_nameId);
        preProductValue = findViewById(R.id.preview_stockId);
        buttonSearch = findViewById(R.id.search_button);
        //database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Product");

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextSearch.getText().toString().isEmpty()){
                    faceData();
                }else {
                    editTextSearch.setError("Enter product name");
                }
            }
        });
    }

    private void faceData() {
        final String proName = editTextSearch.getText().toString();
        databaseReference.child(proName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    ProductData productData = dataSnapshot.getValue(ProductData.class);
                    preProductName.setText(productData.getProductName());
                    preProductValue.setText(productData.getProductAvailable());
                }else {
                    Toast.makeText(Update_Product.this, "No Product Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Update_Product.this, "No Product Found", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
