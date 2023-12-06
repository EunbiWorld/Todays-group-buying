package com.example.groupbuying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class ReviewActivity extends AppCompatActivity {
    
    AutoCompleteTextView residenceTypes;
    AutoCompleteTextView residenceYears;
    AutoCompleteTextView floorLevels;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        getSupportActionBar().hide();

        // 상단바 제목 설정
        getSupportActionBar().setTitle("리뷰 작성");

        // 취소 버튼 클릭 이벤트 처리
        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 메인 페이지로 이동하는 인텐트 생성
                Intent intent = new Intent(ReviewActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 기존의 액티비티 스택을 모두 제거
                startActivity(intent);
                finish(); // 현재 액티비티 종료
            }
        });

        // 다음 버튼 클릭 이벤트 처리
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
