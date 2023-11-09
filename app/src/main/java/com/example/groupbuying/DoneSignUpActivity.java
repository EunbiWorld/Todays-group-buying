package com.example.groupbuying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.groupbuying.LoginActivity;
import com.example.groupbuying.MainActivity;
import com.example.groupbuying.R;

public class DoneSignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donesignup);

        //로그인 버튼 클릭시 로그인 화면으로 이동
        Button GoLoginButton = (Button) findViewById(R.id.btn_GoLogin);
        GoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}