package com.example.groupbuying;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupbuying.R;

public class SignUpActivity extends AppCompatActivity {
    private EditText Name, Nickname, Email, Password, CurrentPW, PhoneNum;
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

    }
}