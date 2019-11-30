package com.example.signzygithubapp.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    private String BASE_URL = "https://api.github.com/";

    private static ApiClient mInstance;
    private Retrofit retrofit;


    private ApiClient(){


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    public static synchronized ApiClient getmInstance(){
        if(mInstance==null){
            mInstance = new ApiClient();
        }
        return mInstance;
    }

    public ApiInterface getApi(){

        return retrofit.create(ApiInterface.class);
    }
}
