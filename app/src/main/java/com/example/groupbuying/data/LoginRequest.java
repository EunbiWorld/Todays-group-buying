package com.example.groupbuying.data;

import com.google.gson.annotations.SerializedName;

//로그인 요청을 담당하는 클래스
public class LoginRequest {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("userId")
    private int userId;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getUserId() {
        return userId;
    }
}
