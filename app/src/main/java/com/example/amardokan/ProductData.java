package com.example.amardokan;

public class ProductData {
    String productName;
    String productAvailable;

    public ProductData(){
        //empty class
    }

    public ProductData(String productName, String productAvailable) {
        this.productName = productName;
        this.productAvailable = productAvailable;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductAvailable() {
        return productAvailable;
    }
}
