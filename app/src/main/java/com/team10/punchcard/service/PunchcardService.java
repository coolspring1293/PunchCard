package com.team10.punchcard.service;

import com.team10.punchcard.service.pojo.UserRegisterRequest;
import com.team10.punchcard.service.pojo.UserUpdateRequest;
import com.team10.punchcard.unity.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;


/**
 * Created by leasunhy on 5/18/16.
 */
public interface PunchcardService {
    String END_POINT = "http://172.18.42.208:5000/";

    @GET("/user/{username}")
    Call<User> getUserInfo(@Path("username") String username);

    @POST("/user")
    Call<User> registerUser(@Body UserRegisterRequest request);

    @PUT("/user/{username}")
    Call<User> updateUser(@Path("username") String username, @Body UserUpdateRequest request);

    @DELETE("/user/{username}")
    Call<ResponseBody> deleteUser(@Path("username") String username);

    @GET("/user")
    Call<List<User>> getAllUsers();
}

