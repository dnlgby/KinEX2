package com.example.threadbarzel.network;

import com.example.threadbarzel.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("users/{id}")
    Call<User> getUser(@Path("id") int userId);

}