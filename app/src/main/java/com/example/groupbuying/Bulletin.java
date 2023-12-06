package com.example.groupbuying;

import java.io.Serializable;

public class Bulletin implements Serializable {
    private String productName;
    private String price;
    private String description;
    private String num;
    private String imageUrl;  // 'imageUrl' 필드 추가
    private String category;
    private boolean visible;  // 게시글 승인 상태를 나타내는 필드

    // Firestore에서 사용하기 위한 빈 생성자
    public Bulletin() {
    }

    // 생성자
    public Bulletin(String productName, String price, String description, String num, String imageUri, String category, boolean visible) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.num = num;
        this.imageUrl = imageUri;
        this.category = category;
        this.visible = visible;  // visible 필드 초기화
    }

    // getter와 setter 메서드
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
