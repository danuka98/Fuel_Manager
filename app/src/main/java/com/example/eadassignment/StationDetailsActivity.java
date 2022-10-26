package com.example.eadassignment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StationDetailsActivity extends AppCompatActivity {

    ImageButton fuelStation, arrivalTime, departTime;
    TextView arrivalText,departText,waitingTime;
    String currentArrivalTime,currentDepartTime;
    Date time1,time2;
    long difference;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm a");
    int days,hours,min;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_details);

        fuelStation = findViewById(R.id.fuelStation);
        arrivalTime = findViewById(R.id.updateArrivalTime);
        departTime = findViewById(R.id.updateDepartTime);
        arrivalText = findViewById(R.id.arrivalTime);
        departText = findViewById(R.id.departTime);
        waitingTime = findViewById(R.id.waitingTime);

        arrivalTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentArrivalTime = new SimpleDateFormat("HH:mm a", Locale.getDefault()).format(new Date());
                Log.d("Arrival",""+currentArrivalTime);
                arrivalText.setText(currentArrivalTime);

                try {
                    time1 = simpleDateFormat.parse(currentArrivalTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Toast.makeText(StationDetailsActivity.this, "You Updated your Arrival Time", Toast.LENGTH_SHORT).show();
            }
        });

        departTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDepartTime = new SimpleDateFormat("HH:mm a", Locale.getDefault()).format(new Date());
                Log.d("Depart",""+currentDepartTime);
                departText.setText(currentDepartTime);

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

    }
}
