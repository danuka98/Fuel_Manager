package com.example.eadassignment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("User")
    Call<RegisterResponse> registerUser(@Body RegisterModel registerModel);
}
