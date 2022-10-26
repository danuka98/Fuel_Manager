package com.example.eadassignment;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://fuel-station-app-backend.herokuapp.com/api/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public  static  UserService getService(){
        UserService userService = getRetrofit().create(UserService.class);

        Log.d("Reg","Reg details " +userService);
        return  userService;
    }
}
