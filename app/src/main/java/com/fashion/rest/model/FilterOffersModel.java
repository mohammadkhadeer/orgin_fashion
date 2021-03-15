package com.fashion.rest.model;

import java.util.ArrayList;

public class FilterOffersModel {
    String ISO;
    City city;
    ArrayList<Area> areasList;
    int from,to;

    public FilterOffersModel(String ISO, City city, ArrayList<Area> areasList, int from, int to) {
        this.ISO = ISO;
        this.city = city;
        this.areasList = areasList;
        this.from = from;
        this.to = to;
    }

    public String getISO() {
        return ISO;
    }

    public void setISO(String ISO) {
        this.ISO = ISO;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public ArrayList<Area> getAreasList() {
        return areasList;
    }

    public void setAreasList(ArrayList<Area> areasList) {
        this.areasList = areasList;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
