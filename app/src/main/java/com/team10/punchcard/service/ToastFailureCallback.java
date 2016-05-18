package com.team10.punchcard.service;

import android.support.design.widget.Snackbar;
import android.view.View;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by leasunhy on 5/18/16.
 */
public abstract class ToastFailureCallback<T> implements Callback<T> {
    private View view;
    private Throwable error;

    public ToastFailureCallback(View view) {
        this.view = view;
        this.error = new UnknownError("Unknown Error");
    }

    public ToastFailureCallback(View view, Throwable throwable) {
        this.view = view;
        this.error = throwable;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (!response.isSuccessful()) {
            onFailure(call, error);
            return;
        }
        onSuccess(call, response);
    }

    protected abstract void onSuccess(Call<T> call, Response<T> response);

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Snackbar.make(view, "ERROR: " + t.getMessage(), Snackbar.LENGTH_LONG).show();
    }
}
