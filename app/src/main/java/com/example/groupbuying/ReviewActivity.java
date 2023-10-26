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

    String[] items1 = {"원룸/빌라/주택", "오피스텔"};
    String[] items2 = {"2018년까지", "2019년까지", "2020년까지", "2021년까지", "2022년까지", "2023년까지"};
    String[] items3 = {"반지하", "1층", "2층", "3층", "4층", "그 이상"};

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
