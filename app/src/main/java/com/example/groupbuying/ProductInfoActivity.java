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

    // 관리자의 UID를 정의합니다.
    private static final String ADMIN_UID = "5Y8iPYqWzOfBbu8Gk1QMZyMnP0f2";

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

        // '허용' 버튼을 찾습니다.
        Button approveButton = findViewById(R.id.approveButton);

        // 뷰에 상품 정보를 설정합니다.
        productNameTextView.setText(product.getProductName());
        productDescriptionTextView.setText(product.getProductDescription());
        productPriceTextView.setText(product.getPrice() + "원");
        productCategoryTextView.setText(product.getCategory());
        productNumTextView.setText(product.getNum());
        Glide.with(this).load(product.getImageUrl()).into(productImageView);

        // 현재 로그인한 사용자의 정보를 가져옵니다.
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // 로그인한 사용자의 UID를 가져옵니다.
            String uid = currentUser.getUid();

            // 관리자인 경우에만 '허용' 버튼을 보입니다.
            if (uid.equals(ADMIN_UID)) {
                approveButton.setVisibility(View.VISIBLE);

                // '허용' 버튼의 클릭 리스너를 설정합니다.
                approveButton.setOnClickListener(v -> {
                    // 게시글을 허용합니다.
                    approveBulletin(product.getId());
                });
            } else {
                // 관리자가 아닌 경우 '허용' 버튼을 숨깁니다.
                approveButton.setVisibility(View.GONE);
            }
        } else {
            // 로그인하지 않은 경우 '허용' 버튼을 숨깁니다.
            approveButton.setVisibility(View.GONE);
        }
    }

    private void approveBulletin(String bulletinId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("bulletins").document(bulletinId)
                .update("visible", true)
                .addOnSuccessListener(aVoid -> {
                    // 게시글이 성공적으로 허용되었습니다.
                    // 필요한 추가 동작을 여기에 작성하세요.
                })
                .addOnFailureListener(e -> {
                    // 게시글을 허용하는데 실패했습니다.
                    // 실패 사유를 사용자에게 알리거나, 로그를 남기세요.
                });
    }
}