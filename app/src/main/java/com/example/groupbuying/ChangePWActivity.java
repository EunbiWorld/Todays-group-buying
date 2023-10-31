package com.example.groupbuying;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePWActivity extends AppCompatActivity {
    private EditText CurrentPW, NewPW;
    private Button ChangePW;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepwd);

        CurrentPW = findViewById(R.id.Current_PW);
        NewPW = findViewById(R.id.enter_NewPW);
        ChangePW = findViewById(R.id.btn_ChangePW);
    }
}
