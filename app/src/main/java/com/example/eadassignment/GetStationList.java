package com.example.eadassignment;

import java.util.ArrayList;

public class GetStationList {

    private String id,name,city;
    private ArrayList<FuelDetails> fuelDetails;

    public GetStationList(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<FuelDetails> getFuelDetails() {
        return fuelDetails;
    }

    public void setFuelDetails(ArrayList<FuelDetails> fuelDetails) {
        this.fuelDetails = fuelDetails;
    }
}

