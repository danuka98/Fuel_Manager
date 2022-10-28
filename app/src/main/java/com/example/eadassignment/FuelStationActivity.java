package com.example.eadassignment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FuelStationActivity extends AppCompatActivity {

    ListView listView;
    EditText searchText;
    ArrayAdapter<GetStationList> arrayAdapter;
    ArrayAdapter<String> arrayLocalAdapter;
    ProgressBar progressBar;
    ArrayList<GetStationList> arrayList;
    public static final String STATION_ID = "id";
    String[] id;
    String[] tutorials = { "Algorithms", "Data Structures",
            "Languages", "Interview Corner",
            "GATE", "ISRO CS",
            "GATE", "ISRO CS",
            "GATE", "ISRO CS",
            "GATE", "ISRO CS",
            "UGC NET CS", "CS Subjects",
            "Web Technologies" };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fuel_stations);

        listView = findViewById(R.id.fuelList);
        searchText = findViewById(R.id.itemSearch);
        progressBar = findViewById(R.id.loadingList);
        getStationList();
//        progressBar.setVisibility(View.GONE);
//
//        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(tutorials));
//        arrayLocalAdapter = new ArrayAdapter<>(this,R.layout.list_item,arrayList);
//        listView.setAdapter(arrayAdapter);



        //search bar
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    //get station list
    public void getStationList(){
        Call<ArrayList<GetStationList>> getStationListCall = ApiClient.getService().getStationList();
        getStationListCall.enqueue(new Callback<ArrayList<GetStationList>>() {
            @Override
            public void onResponse(Call<ArrayList<GetStationList>> call, Response<ArrayList<GetStationList>> response) {
                if (response.isSuccessful()){
                    arrayList = response.body();
                    for(int i = 0; i<arrayList.size();i++){
                        CustomListAdapter customListAdapter = new CustomListAdapter(arrayList,FuelStationActivity.this,R.layout.list_item);
                        listView.setAdapter(customListAdapter);
                        Log.d("id 0> for>>>>",""+response.body().get(i).getId());

                        progressBar.setVisibility(View.GONE);

                    }
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            Intent intent = new Intent(FuelStationActivity.this, StationDetailsActivity.class);
                            intent.putExtra("id",response.body().get(position).getId());
                            Log.d("id>>>>>",""+response.body().get(position).getId());
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetStationList>> call, Throwable t) {

            }
        });

    }
}
