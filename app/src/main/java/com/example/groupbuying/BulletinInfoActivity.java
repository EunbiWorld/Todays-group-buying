package com.example.groupbuying;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.groupbuying.fragment.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class BulletinInfoActivity extends AppCompatActivity {

    private Button approveButton; // '허용' 버튼 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin_info);

        Bulletin bulletin = (Bulletin) getIntent().getSerializableExtra("bulletin");

        TextView productNameTextView = findViewById(R.id.product_name_text_view);
        TextView productPriceTextView = findViewById(R.id.product_price_text_view);
        TextView productDescriptionTextView = findViewById(R.id.product_description_text_view);
        TextView productCategoryTextView = findViewById(R.id.product_category_text_view);
        ImageView productImageView = findViewById(R.id.product_image_view);

        productNameTextView.setText(bulletin.getProductName());
        productDescriptionTextView.setText(bulletin.getDescription());
        productPriceTextView.setText(bulletin.getPrice() + "원");
        productCategoryTextView.setText(bulletin.getCategory());
        Glide.with(this).load(bulletin.getImageUrl()).into(productImageView);

        approveButton = findViewById(R.id.approveButton); // '허용' 버튼과 연결

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            approveButton.setVisibility(View.GONE);
        } else {
            String uid = currentUser.getUid();
            String adminUid = "5Y8iPYqWzOfBbu8Gk1QMZyMnP0f2"; // 관리자 uid

            if (uid.equals(adminUid)) {
                approveButton.setVisibility(View.VISIBLE); // 관리자일 경우 '허용' 버튼 보이게 설정
            } else {
                approveButton.setVisibility(View.GONE); // 관리자가 아닐 경우 '허용' 버튼 숨기기
            }
        }

        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("bulletins").document(bulletin.getProductName())
                        .update("visible", true)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(BulletinInfoActivity.this, "상품이 승인되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(BulletinInfoActivity.this, "승인 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
