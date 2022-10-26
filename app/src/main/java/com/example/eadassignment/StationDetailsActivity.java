package com.example.eadassignment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StationDetailsActivity extends AppCompatActivity {

    ImageButton fuelStation, arrivalTime, departTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_details);

        fuelStation = findViewById(R.id.fuelStation);
        arrivalTime = findViewById(R.id.updateArrivalTime);
        departTime = findViewById(R.id.updateDepartTime);

        arrivalTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StationDetailsActivity.this, "You Updated your Arrival Time", Toast.LENGTH_SHORT).show();
            }
        });

        departTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StationDetailsActivity.this, "You Updated your Depart Time", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
