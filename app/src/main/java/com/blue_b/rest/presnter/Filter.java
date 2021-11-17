package com.blue_b.rest.presnter;


import com.blue_b.rest.model.ItemFilterModel;

public interface Filter {

    void onFilterClick(ItemFilterModel filterModel, String filterType);

    void onFilterCancel();

    void onFilterCityCancel(Boolean cancel);

    void onFilterNeighborhoodCancel(Boolean cancel);

    //CityModel
}
