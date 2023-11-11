package com.example.groupbuying;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.widget.TextView;
import com.example.groupbuying.fragment.BulletinFragment;
import com.example.groupbuying.fragment.HeartFragment;
import com.example.groupbuying.fragment.HomeFragment;
import com.example.groupbuying.fragment.SettingFragment;
import com.example.groupbuying.fragment.VideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity"; // 로그 식별자

    private BottomNavigationView bottomNavigationView;
//    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private CollectionReference productsRef = db.collection("products");
//    private TextView productNameTextView;
//    private TextView priceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavView);
//        pdname = findViewById(R.id.pdname);
//        pdprice = findViewById(R.id.pdprice);

        // 초기 선택 항목 설정
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        // BottomNavigationView 아이템 선택 리스너 설정
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.navigation_video) {
                selectedFragment = new VideoFragment();
            } else if (item.getItemId() == R.id.navigation_bulletin) {
                selectedFragment = new BulletinFragment();
            } else if (item.getItemId() == R.id.navigation_home) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.navigation_heart) {
                selectedFragment = new HeartFragment();
            } else if (item.getItemId() == R.id.navigation_setting) {
                selectedFragment = new SettingFragment();
            }

            // Fragment를 교체
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, selectedFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }

            return true;
        });

//        // 현재 테스트용입니다.
//        // Firestore 경로를 설정합니다.
//        String productPath = "products/Fruit/apple/5YOX6UTkuotk4J37XRP8"; // 원하는 제품 경로로 변경해주세요.
//
//        // 경로에 해당하는 데이터 가져오기
//        db.document(productPath)
//                .get()
//                .addOnSuccessListener(documentSnapshot -> {
//                    if (documentSnapshot.exists()) {
//                        // 데이터가 존재하면 데이터를 추출하고 UI에 표시할 수 있습니다.
//                        String productName = documentSnapshot.getString("name");
//                        double price = documentSnapshot.getDouble("price");
//                        pd_name.setText("Product Name: " + productName);
//                        pd_price.setText("Price: " + String.valueOf(price));
//                    } else {
//                        Log.d(TAG, "Document does not exist");
//                    }
//                })
//                .addOnFailureListener(e -> {
//                    Log.e(TAG, "Error fetching data: " + e.getMessage());
//                });
    }
}
