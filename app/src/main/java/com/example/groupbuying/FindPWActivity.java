package com.example.groupbuying;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class FindPWActivity extends AppCompatActivity {
    private EditText Email, EnterCode;
    private Button SendCode, CheckCode;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpwd);

        Email = findViewById(R.id.find_Email);
        EnterCode = findViewById(R.id.find_EnterCode);
        SendCode = findViewById(R.id.send_FindCode);
        CheckCode = findViewById(R.id.find_CheckCode);


        //뒤로가기 버튼 클릭시 로그인,회원가입 선택 화면으로 이동
        ImageButton BackButton = (ImageButton) findViewById(R.id.btn_back);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // 현재 액티비티를 종료하여 이전 화면으로 돌아갑니다.
            }
        });

    }
}
