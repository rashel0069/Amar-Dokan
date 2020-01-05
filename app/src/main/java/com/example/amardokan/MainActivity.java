package com.example.amardokan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageButton addPro,updatePro,deletePro,stockPro,infoMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPro = findViewById(R.id.addproductId);
        updatePro = findViewById(R.id.updateproductId);
        deletePro = findViewById(R.id.deleteproductId);
        stockPro = findViewById(R.id.stockproductId);
        infoMy = findViewById(R.id.infoproductId);


        addPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Add_Product.class);
                startActivity(intent);
            }
        });
        updatePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Update_Product.class);
                startActivity(intent);
            }
        });
        deletePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Delete_Product.class);
                startActivity(intent);
            }
        });
        stockPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Stock_Product.class);
                startActivity(intent);
            }
        });
        infoMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Info.class);
                startActivity(intent);
            }
        });
    }

}
