package com.team10.punchcard.service;

import com.team10.punchcard.service.pojo.WordResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by leasunhy on 5/18/16.
 */
public interface ShanbayService {
        String END_POINT = "https://api.shanbay.com";

@GET("/bdc/search/")
Call<WordResponse> getWord(@Query("word") String word);
        }
