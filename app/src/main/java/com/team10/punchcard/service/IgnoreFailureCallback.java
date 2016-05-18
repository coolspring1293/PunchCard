package com.team10.punchcard.service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by leasunhy on 5/18/16.
 */
public abstract class IgnoreFailureCallback<T> implements Callback<T> {
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (!response.isSuccessful()) {
            onFailure(call, new UnknownError());
        }
        onSuccess(call, response);
    }

    protected abstract void onSuccess(Call<T> call, Response<T> response);
}
