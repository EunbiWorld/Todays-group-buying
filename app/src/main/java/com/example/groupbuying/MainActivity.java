package com.example.groupbuying;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.groupbuying.fragment.BulletinFragment;
import com.example.groupbuying.fragment.HeartFragment;
import com.example.groupbuying.fragment.HomeFragment;
import com.example.groupbuying.fragment.LoginRequestFragment;
import com.example.groupbuying.fragment.SettingFragment;
import com.example.groupbuying.fragment.VideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity"; // 로그 식별자

    private BottomNavigationView bottomNavigationView;
    private boolean isLoggedIn() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser != null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavView);

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
                if (!isLoggedIn()) {
                    selectedFragment = new LoginRequestFragment();
                } else {
                    selectedFragment = new HeartFragment();
                }
            } else if (item.getItemId() == R.id.navigation_setting) {
                if (!isLoggedIn()) {
                    selectedFragment = new LoginRequestFragment();
                } else {
                    selectedFragment = new SettingFragment();
                }
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
    }
}