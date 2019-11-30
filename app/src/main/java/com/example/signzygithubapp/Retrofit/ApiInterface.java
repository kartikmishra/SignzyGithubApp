package com.example.signzygithubapp.Retrofit;

import com.example.signzygithubapp.Model.ReposModel;
import com.example.signzygithubapp.Model.User;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiInterface {
    @GET("users/{userName}")
    Call<User> getUser(@Path("userName") String name);

    @GET("users/{userName}/repos")
    Call<List<ReposModel>> getReposList(@Path("userName") String userName);

}