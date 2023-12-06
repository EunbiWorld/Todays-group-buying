package com.example.groupbuying;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.groupbuying.fragment.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProductInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        Product product = (Product) getIntent().getSerializableExtra("product");
        // 뷰를 찾습니다.
        TextView productNameTextView = findViewById(R.id.product_name_text_view);
        TextView productDescriptionTextView = findViewById(R.id.product_description_text_view);
        TextView productPriceTextView = findViewById(R.id.product_price_text_view);
        TextView productCategoryTextView = findViewById(R.id.product_category_text_view);
        TextView productNumTextView = findViewById(R.id.product_num_text_view);
        ImageView productImageView = findViewById(R.id.product_image_view);

        // 뷰에 상품 정보를 설정합니다.
        productNameTextView.setText(product.getProductName());
        productDescriptionTextView.setText(product.getProductDescription());
        productPriceTextView.setText("가격 : " + product.getPrice() + "원");
        productCategoryTextView.setText("카테고리 : " + product.getCategory());
        productNumTextView.setText("수량 : " + product.getNum());
        Glide.with(this).load(product.getImageUrl()).into(productImageView);

    }

}