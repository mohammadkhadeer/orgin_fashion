package com.fashion.rest.presnter;


import com.fashion.rest.model.ItemFilterModel;

public interface Filter {

    void onFilterClick(ItemFilterModel filterModel, String filterType);

    void onFilterCancel();

    void onFilterCityCancel(Boolean cancel);

    void onFilterNeighborhoodCancel(Boolean cancel);

    //CityModel
}
