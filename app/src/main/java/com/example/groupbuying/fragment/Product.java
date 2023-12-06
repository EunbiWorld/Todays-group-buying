package com.example.groupbuying.fragment;

import java.io.Serializable;

public class Product implements Serializable {
    private String id;
    private String productName;
    private String productDescription;
    private String price;
    private String category;
    private String description;
    private String num;
    private String imageUrl;  // 이미지 URL을 저장하는 필드 추가

    public Product() {
    }

    public String getId() {
        return id;
    }

    // 문서 ID를 설정하는 setter 메소드
    public void setId(String id) {
        this.id = id;
    }

    // 모든 필드를 인자로 받는 생성자
    public Product(String productName, String productDescription, String price, String category, String description, String num, String imageUrl) {
        this.productName = productName;
        this.price = price;
        this.productDescription = productDescription;
        this.category = category;
        this.description = description;
        this.num = num;
        this.imageUrl = imageUrl;  // 이미지 URL을 받는 생성자 추가
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
    public String getImageUrl() {  // 이미지 URL을 반환하는 getter 추가
        return imageUrl;
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
    public void setImageUrl(String imageUrl) {  // 이미지 URL을 설정하는 setter 추가
        this.imageUrl = imageUrl;
    }
}
