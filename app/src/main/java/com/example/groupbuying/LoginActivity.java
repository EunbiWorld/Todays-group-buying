package com.example.groupbuying;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 3000; // 3초

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 3초 후에 MainActivity로 전환
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);


        //로그인 버튼 클릭시 로그인 화면으로 이동
        Button loginbutton = (Button) findViewById(R.id.GoLoginButton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), IDLoginActivity.class);
            }
        });


        //회원가입 버튼 클릭시 회원가입 화면으로 이동
        Button signupbutton = (Button) findViewById(R.id.GoSignupButton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
            }
        });
    }
}
