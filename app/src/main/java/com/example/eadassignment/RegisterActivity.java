package com.example.eadassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class RegisterActivity extends AppCompatActivity {

    Spinner spinner;
    TextView textView;
    EditText stationCode,stationName;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);
        spinner = findViewById(R.id.spinner);
        textView = findViewById(R.id.signIn);
        stationCode = findViewById(R.id.stationCode);
        stationName = findViewById(R.id.stationName);
        button = findViewById(R.id.registerBtn);

        ///set the drop down values with visibility
        String[] value = {"User","Station Manager"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(value));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.spinner_list,arrayList);
        Spinner.OnItemSelectedListener listener = new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinner.getSelectedItem().toString().equals("Station Manager")){
                    stationCode.setVisibility(View.VISIBLE);
                    stationName.setVisibility(View.VISIBLE);
                }
                else {
                    stationCode.setVisibility(View.INVISIBLE);
                    stationName.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        };
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(listener);

        ///intent to the sign in page
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        ///intent to the sign in page using register button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
