package com.team10.punchcard.service;

import com.team10.punchcard.service.pojo.LoginRequest;
import com.team10.punchcard.service.pojo.UserRegisterRequest;
import com.team10.punchcard.service.pojo.UserUpdateRequest;
import com.team10.punchcard.unity.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;


/**
 * Created by leasunhy on 5/18/16.
 */
public interface PunchcardService {
    String END_POINT = "http://172.18.42.208:5000/";

    @GET("/me")
    Call<User> getUserInfo();

    @PUT("/me")
    Call<User> updateUser(@Body UserUpdateRequest request);

    @POST("/user")
    Call<User> registerUser(@Body UserRegisterRequest request);

    @GET("/user")
    Call<List<User>> getAllUsers();

    @POST("/login")
    Call<User> login(@Body LoginRequest request);
}

