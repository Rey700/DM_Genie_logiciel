package com.dm_genie_logiciel.fera.api;

import java.io.IOException;

import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FakeCall<T> implements Call<T> {

    private final T data;

    public FakeCall(T data) {
        this.data = data;
    }

    public static <T> FakeCall<T> success(T data) {
        return new FakeCall<>(data);
    }

    @Override
    public Response<T> execute() throws IOException {
        return Response.success(data);
    }

    @Override
    public void enqueue(Callback<T> callback) {
        callback.onResponse(this, Response.success(data));
    }

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public void cancel() {
        // Nothing to cancel in mock
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public Call<T> clone() {
        return new FakeCall<>(data);
    }

    @Override
    public Request request() {
        return new Request.Builder()
                .url("http://mock.api/") // fake URL required by Retrofit
                .build();
    }

    @Override
    public Timeout timeout() {
        return Timeout.NONE; // Retrofit 2.9 method required
    }
}
