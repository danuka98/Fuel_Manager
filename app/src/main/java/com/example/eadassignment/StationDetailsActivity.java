package com.example.eadassignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StationDetailsActivity extends AppCompatActivity {

    ImageButton fuelStation, arrivalTime, departTime, profileIcon;
    TextView arrivalText,departText,waitingTime,stationName,city, fuelAvailable,length,bikeLength;
    String userName,currentArrivalTime,currentDepartTime;
    Date time1,time2;
    long difference;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm a");
    int days,hours,min;
    ProgressBar progressBar;
    String getId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_details);
        userName="Danuka";

        fuelStation = findViewById(R.id.fuelStation);
        arrivalTime = findViewById(R.id.updateArrivalTime);
        departTime = findViewById(R.id.updateDepartTime);
        arrivalText = findViewById(R.id.arrivalTime);
        departText = findViewById(R.id.departTime);
        waitingTime = findViewById(R.id.waitingTime);
        stationName = findViewById(R.id.stationName);
        city = findViewById(R.id.city);
        progressBar = findViewById(R.id.loadingPro);
        profileIcon = findViewById(R.id.profileIcon);
        fuelAvailable = findViewById(R.id.fuelAvailable);
        length = findViewById(R.id.fuellength);
        //bikeLength = findViewById(R.id.bikelength);

        Intent intent = getIntent();
        getId = intent.getStringExtra("id");
        getFuelStationDetails();
        getFuelLength();

        //arrival time calculation
        arrivalTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentArrivalTime = new SimpleDateFormat("HH:mm a", Locale.getDefault()).format(new Date());
                Log.d("Arrival",""+currentArrivalTime);

                saveArrivalTime(createArrivalRequest());
                try {
                    time1 = simpleDateFormat.parse(currentArrivalTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Toast.makeText(StationDetailsActivity.this, "You Updated your Arrival Time", Toast.LENGTH_SHORT).show();
            }
        });

        //depart time calculation
        departTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDepartTime = new SimpleDateFormat("HH:mm a", Locale.getDefault()).format(new Date());
                Log.d("Depart",""+currentDepartTime);

                saveDepartTime(createDepartRequest());
                try {
                    time2 = simpleDateFormat.parse(currentDepartTime);
                    difference = time1.getTime() - time2.getTime();
                    days = (int) (difference / (1000 * 60 * 60 * 24));
                    hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
                    min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
                    hours = (hours < 0 ? -hours : hours);
                    Log.i("======= Hours"," :: "+hours);
                    Log.i("======= diff"," :: "+difference);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Toast.makeText(StationDetailsActivity.this, "You Updated your Depart Time", Toast.LENGTH_SHORT).show();
            }
        });

        //intent to list of fuel station
        fuelStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stationIntent = new Intent(StationDetailsActivity.this, FuelStationActivity.class);
                startActivity(stationIntent);
            }
        });

        //intent to user profile
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(StationDetailsActivity.this, UserProfile.class);
                startActivity(profileIntent);
            }
        });
    }

    //Arrival time requesting
    public ArrivalDepartRequest createArrivalRequest(){
        ArrivalDepartRequest arrivalDepartRequest = new ArrivalDepartRequest();

        arrivalDepartRequest.setArrivalTime(currentArrivalTime);
        arrivalDepartRequest.setUsername(userName);
        arrivalDepartRequest.setStationID(getId);
        return arrivalDepartRequest;
    }

    //Depart time requesting
    public ArrivalDepartRequest createDepartRequest(){
        ArrivalDepartRequest arrivalDepartRequest = new ArrivalDepartRequest();

        arrivalDepartRequest.setDepartTime(currentDepartTime);
        arrivalDepartRequest.setUsername(userName);
        arrivalDepartRequest.setStationID(getId);
        return arrivalDepartRequest;
    }

    //Arrival time Response
    public void saveArrivalTime(ArrivalDepartRequest arrivalDepartRequest){

        Call<ArrivalDepartResponse> arrivalDepartResponseCall = ApiClient.getService().arrivalDepart(arrivalDepartRequest);
        arrivalDepartResponseCall.enqueue(new Callback<ArrivalDepartResponse>() {
            @Override
            public void onResponse(Call<ArrivalDepartResponse> call, Response<ArrivalDepartResponse> response) {
                if (response.isSuccessful()){
                    arrivalText.setText(response.body().getArrivalTime());
                    Toast.makeText(StationDetailsActivity.this, "Arrival TIme Update Saved", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(StationDetailsActivity.this, "Arrival TIme Request failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrivalDepartResponse> call, Throwable t) {
                Toast.makeText(StationDetailsActivity.this, "Arrival TIme Request failed " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Depart time response
    public void saveDepartTime(ArrivalDepartRequest arrivalDepartRequest){

        Call<ArrivalDepartResponse> arrivalDepartResponseCall = ApiClient.getService().arrivalDepart(arrivalDepartRequest);
        arrivalDepartResponseCall.enqueue(new Callback<ArrivalDepartResponse>() {
            @Override
            public void onResponse(Call<ArrivalDepartResponse> call, Response<ArrivalDepartResponse> response) {
                if (response.isSuccessful()){
                    departText.setText(response.body().getDepartTime());
                    Toast.makeText(StationDetailsActivity.this, "Depart TIme Update Saved", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(StationDetailsActivity.this, "Depart TIme Request failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrivalDepartResponse> call, Throwable t) {
                Toast.makeText(StationDetailsActivity.this, "Depart TIme Request failed " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //get the station details
    public void getFuelStationDetails(){
        Log.d("Each Stattion >>>> ",getId);

        Call<GetStationDetails> getStationDetailsCall = ApiClient.getService().getStationDetailsByID(getId);
        getStationDetailsCall.enqueue(new Callback<GetStationDetails>() {
            @Override
            public void onResponse(Call<GetStationDetails> call, Response<GetStationDetails> response) {
                if (response.isSuccessful()){
                    Log.d("FUEL","Details >> " +response.body());
                    stationName.setText(response.body().getName());
                    city.setText(response.body().getCity());
                    for (int i = 0;i < response.body().getFuelDetails().size();i++){
                        fuelAvailable.setText(response.body().getFuelDetails().get(i).getFuelType()+" Available");
                    }
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<GetStationDetails> call, Throwable t) {
                Log.d("FUEL","Details >> "+t.getLocalizedMessage());
            }
        });


    }

    public void getFuelLength(){
        Call<ArrivalDepartResponse> queueDetails = ApiClient.getService().queueDetails();
        queueDetails.enqueue(new Callback<ArrivalDepartResponse>() {
            @Override
            public void onResponse(Call<ArrivalDepartResponse> call, Response<ArrivalDepartResponse> response) {
                if (response.isSuccessful()){
                    length.setText(response.body().getVehicleType().length());
                }
            }

            @Override
            public void onFailure(Call<ArrivalDepartResponse> call, Throwable t) {

            }
        });

    }

}
