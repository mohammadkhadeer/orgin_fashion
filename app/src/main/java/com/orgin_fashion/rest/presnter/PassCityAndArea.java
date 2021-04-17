package com.orgin_fashion.rest.presnter;

import com.orgin_fashion.rest.model.Area;
import com.orgin_fashion.rest.model.Category;
import com.orgin_fashion.rest.model.City;

public interface PassCityAndArea {
    void PassCity(City city);
    void PassArea(Area area);
    void PassCat(Category category);
}
