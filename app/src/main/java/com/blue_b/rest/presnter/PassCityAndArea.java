package com.blue_b.rest.presnter;

import com.blue_b.rest.model.Area;
import com.blue_b.rest.model.Category;
import com.blue_b.rest.model.City;

public interface PassCityAndArea {
    void PassCity(City city);
    void PassArea(Area area);
    void PassCat(Category category);
}
