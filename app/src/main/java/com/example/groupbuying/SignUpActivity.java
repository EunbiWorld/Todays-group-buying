package com.example.groupbuying;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;

public class SignUpActivity extends AppCompatActivity {
    private EditText Name, Nickname, Email, Password, CurrentPW, PhoneNum;
    private FirebaseAuth firebaseAuth;
    private Button Signup;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Name = findViewById(R.id.Sign_UserName);
        Nickname = findViewById(R.id.Sign_Knikname);
        Email = findViewById(R.id.Sign_Email);
        Password = findViewById(R.id.Sign_PW);
        CurrentPW = findViewById(R.id.Sign_ConfirmPW);
        PhoneNum = findViewById(R.id.Sign_PhoneNum);
        Signup = findViewById(R.id.Do_SignUp);

        firebaseAuth = FirebaseAuth.getInstance();


        //뒤로가기 버튼 클릭시 로그인,회원가입 선택 화면으로 이동
        ImageButton BackButton = (ImageButton) findViewById(R.id.btn_back);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // 현재 액티비티를 종료하여 이전 화면으로 돌아갑니다.
            }
        });

        //계정이 있나요? 텍스트 클릭시 회원가입 페이지로 이동
        TextView loginbtn = (TextView) findViewById(R.id.txt_Loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), IDLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();

                // 이메일과 비밀번호로 새 사용자 만들기
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this,
                                new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // 등록 성공
                                    Toast.makeText(SignUpActivity.this, "등록 성공", Toast.LENGTH_SHORT).show();

                                    // You can navigate to the next screen or perform other actions here
                                    Intent intent = new Intent(SignUpActivity.this, DoneSignUpActivity.class);
                                    startActivity(intent);
                                } else {
                                    // 등록 실패
                                    Toast.makeText(SignUpActivity.this, "등록 실패. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}