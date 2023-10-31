package com.example.groupbuying;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

    }
}
