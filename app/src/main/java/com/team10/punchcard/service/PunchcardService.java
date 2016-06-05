package com.team10.punchcard.service;

import com.team10.punchcard.service.pojo.LoginRequest;
import com.team10.punchcard.service.pojo.UserRegisterRequest;
import com.team10.punchcard.service.pojo.UserUpdateRequest;
import com.team10.punchcard.unity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;


/**
 * Created by leasunhy on 5/18/16.
 */
public interface PunchcardService {
    String END_POINT = "http://ali.leasunhy.com:8000/";

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

    @GET("/leaderboard")
    Call<List<User>> getLeaderboard();
}

