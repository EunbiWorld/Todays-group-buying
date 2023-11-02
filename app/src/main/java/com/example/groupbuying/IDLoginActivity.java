package com.example.groupbuying;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupbuying.R;

public class IDLoginActivity extends AppCompatActivity {

    private EditText LoginEmail, LoginPW;
    private Button btn_Login;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idlogin);
        //

        LoginEmail = findViewById(R.id.edt_LoginEmail);
        LoginPW = findViewById(R.id.edt_LoginPW);

        btn_Login = findViewById(R.id.btn_DoLogin);

        //로그인 버튼 클릭시 동작
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //입력한 email, password를 문자열로 추출
                String userEmail = LoginEmail.toString();
                String userPassword = LoginPW.toString();

            }
        });
    }
}
