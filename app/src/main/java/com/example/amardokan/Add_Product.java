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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Add_Product extends AppCompatActivity {
    TextView date;
    EditText productName,productQuantity;
    Button addtoDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__product);
        date = findViewById(R.id.dateId);
        productName = findViewById(R.id.add_productNameId);
        productQuantity = findViewById(R.id.add_productStockId);
        addtoDatabase = findViewById(R.id.add_ButtonId);

        //get current date from mobile
        String dateTime = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        date.setText(dateTime);
        //database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Product");

        //button to add data in firebase database
        addtoDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(productName.getText().toString()) && !TextUtils.isEmpty(productQuantity.getText().toString().trim())){
                    addData();
                }else {
                    Toast.makeText(Add_Product.this, "Error ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Product");
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Upload Data.....");
        progressDialog.show();

        String pName = productName.getText().toString();
        String pValue = productQuantity.getText().toString();
        //ProductData productData = new ProductData(pName,pValue);
        ProductData productData = new ProductData(pName,pValue);

        databaseReference.child(pName).setValue(productData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    productName.setText("");
                    productQuantity.setText("");
                    progressDialog.dismiss();
                    Toast.makeText(Add_Product.this, "Add Successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Add_Product.this, "Failed!!!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });

    }
}
