package com.fashion.rest.presnter;

import com.fashion.rest.model.Area;
import com.fashion.rest.model.Category;
import com.fashion.rest.model.City;
import com.fashion.rest.model.Deal;
import com.fashion.rest.model.SubCategory;

public interface PassCityAndArea {
    void PassCity(City city);
    void PassArea(Area area);
    void PassCat(Category category);
}
