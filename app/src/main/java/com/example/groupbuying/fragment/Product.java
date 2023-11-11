package com.example.groupbuying.fragment;

public class Product {
    private String productName;
    private String productDescription;
    private double productPrice;

    // 기본 생성자
    public Product() {
    }

    // 모든 필드를 인자로 받는 생성자
    public Product(String productName, String productDescription, double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }

    // getter
    public String getProductName() {
        return productName;
    }
    public double getProductPrice() {
        return productPrice;
    }
    public String getProductDescription() {
        return productDescription;
    }

    // setter
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
