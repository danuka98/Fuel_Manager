package com.example.eadassignment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    @POST("User/{id}")
    Call<UserDetails> userDetails(@Path("id") String id);

    @POST("Queue")
    Call<ArrivalDepartResponse> arrivalDepart(@Body ArrivalDepartRequest arrivalDepartRequest);

    @GET("Queue")
    Call<ArrivalDepartResponse> queueDetails();

    @GET("FuelStation/{id}")
    Call<GetStationDetails> getStationDetailsByID(@Path("id") String id);

    @GET("FuelStation/get-all")
    Call<ArrayList<GetStationList>> getStationList();
}
