package com.example.groupbuying.data;

import com.google.gson.annotations.SerializedName;

//회원가입 요청을 담당하는 클래스
public class SignUpRequest {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
