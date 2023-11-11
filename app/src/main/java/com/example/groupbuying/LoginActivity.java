package com.example.groupbuying;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //로그인 버튼 클릭시 로그인 화면으로 이동
        Button loginbutton = (Button) findViewById(R.id.GoLoginButton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), IDLoginActivity.class);
                startActivity(intent); // Intent를 시작하여 새로운 화면으로 전환
            }
        });

        //회원가입 버튼 클릭시 회원가입 화면으로 이동
        Button signupbutton = (Button) findViewById(R.id.GoSignupButton);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent); // Intent를 시작하여 새로운 화면으로 전환
            }
        });
    }
}
