package com.example.groupbuying;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.content.Intent;


public class IDLoginActivity extends AppCompatActivity {
    private EditText LoginEmail, LoginPW;
    private Button btn_Login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idlogin);

        LoginEmail = findViewById(R.id.edt_LoginEmail);
        LoginPW = findViewById(R.id.edt_LoginPW);
        btn_Login = findViewById(R.id.btn_DoLogin);

        firebaseAuth = FirebaseAuth.getInstance();

        //로그인 버튼 클릭시 동작
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //입력한 email, password를 문자열로 추출
                String userEmail = LoginEmail.getText().toString();
                String userPassword = LoginPW.getText().toString();

                // Use Firebase Authentication to sign in the user
                firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword)
                        .addOnCompleteListener(IDLoginActivity.this, task -> {
                            if (task.isSuccessful()) {
                                // 로그인 성공
                                Toast.makeText(IDLoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();

                                //로그인 성공시 메인 화면으로 이동
                                Intent intent = new Intent(IDLoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                // 로그인 실패
                                Toast.makeText(IDLoginActivity.this, "로그인 실패. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
