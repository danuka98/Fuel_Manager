package com.example.eadassignment;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import androidx.navigation.ui.AppBarConfiguration;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        progressBar = findViewById(R.id.progressBar);

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent registerIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(registerIntent);
            }
        };
        handler.postDelayed(runnable,1500);
    }
}