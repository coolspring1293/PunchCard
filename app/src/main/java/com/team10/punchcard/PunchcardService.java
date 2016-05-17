package com.team10.punchcard;

import com.team10.punchcard.unity.User;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by leasunhy on 5/18/16.
 */
public interface PunchcardService {
    @GET("json/index.php")
    Call<User> getUserInfo(@Query("username") String username);
}
