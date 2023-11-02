package com.example.groupbuying.network;

import com.example.groupbuying.data.SignUpRequest;
import com.example.groupbuying.data.SignupData;
import com.example.groupbuying.data.LoginRequest;
import com.example.groupbuying.data.LoginData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public abstract class ServiceApi {
    //test
    @POST("/user/login")
    abstract Call<SignUpRequest> userLogin(@Body LoginData data);

    @POST("/user/join")
    abstract Call<LoginRequest> userJoin(@Body SignupData data);
}
