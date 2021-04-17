package com.orgin_fashion.rest.presnter;


import com.orgin_fashion.rest.model.ItemFilterModel;

public interface Filter {

    void onFilterClick(ItemFilterModel filterModel, String filterType);

    void onFilterCancel();

    void onFilterCityCancel(Boolean cancel);

    void onFilterNeighborhoodCancel(Boolean cancel);

    //CityModel
}
