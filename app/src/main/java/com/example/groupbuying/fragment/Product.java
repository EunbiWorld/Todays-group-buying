package com.example.groupbuying.fragment;

public class Product {
    private String productName;
    private String productDescription;
    private String price;
    private String category;
    private String description;
    private String num;

    // 기본 생성자
    public Product() {
    }

    // 모든 필드를 인자로 받는 생성자
    public Product(String productName, String productDescription, String price, String category, String description, String num) {
        this.productName = productName;
        this.price = price;
        this.productDescription = productDescription;
        this.category = category;
        this.description = description;
        this.num = num;
    }

    // getter
    public String getProductName() {
        return productName;
    }
    public String getPrice() {
        return price;
    }
    public String getProductDescription() {
        return productDescription;
    }
    public String getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }
    public String getNum() {
        return num;
    }

    // setter
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setNum(String num) {
        this.num = num;
    }
}
