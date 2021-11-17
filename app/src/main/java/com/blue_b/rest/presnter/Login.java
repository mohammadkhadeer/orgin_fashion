package com.blue_b.rest.presnter;

import com.blue_b.rest.apiURL.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login {

    public static Retrofit LoginFun() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.115/api/User/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
