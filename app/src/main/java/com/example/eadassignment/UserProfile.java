package com.example.eadassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfile extends AppCompatActivity {

    ImageButton backButton;
    TextView profileName,profileuser,profileUserType,profileUserVehicleType;
    String userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userID = "635bd558567ed76c75e11274";
        setContentView(R.layout.user_profile);
        backButton = findViewById(R.id.profileBack);
        profileName = findViewById(R.id.profileName);
        profileuser = findViewById(R.id.profileuser);
        profileUserType = findViewById(R.id.profileUserType);
        profileUserVehicleType = findViewById(R.id.profileUserVehicleType);

        //getUserDetails();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfile.this, FuelStationActivity.class);
                startActivity(intent);
            }
        });

    }

    public void getUserDetails(){
        Call<UserDetails> getUserDetails = ApiClient.getService().userDetails(userID);
        getUserDetails.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                if(response.isSuccessful()){
                    //profileName.setText("Hello "+ response.body().getUsername());
                    profileuser.setText("User Name: "+ response.body().getUsername());
                    profileUserType.setText("User Type: "+response.body().getUserType());
                    profileUserVehicleType.setText("Vehicle Type: "+response.body().getVehicleType());
                }
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {

            }
        });

    }
}
