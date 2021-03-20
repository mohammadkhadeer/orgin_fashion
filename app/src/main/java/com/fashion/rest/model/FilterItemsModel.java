package com.fashion.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class FilterItemsModel implements Parcelable {
    City city;
    Categories selectedCategory;
    Sub_Cat sub_cat;
    ArrayList<Area> areasList;
    int from,to;
    Store store;

    public FilterItemsModel(City city, Categories selectedCategory, Sub_Cat sub_cat, ArrayList<Area> areasList, int from, int to, Store store) {
        this.city = city;
        this.selectedCategory = selectedCategory;
        this.sub_cat = sub_cat;
        this.areasList = areasList;
        this.from = from;
        this.to = to;
        this.store = store;
    }

    protected FilterItemsModel(Parcel in) {
        city = in.readParcelable(City.class.getClassLoader());
        selectedCategory = in.readParcelable(Categories.class.getClassLoader());
        sub_cat = in.readParcelable(Sub_Cat.class.getClassLoader());
        areasList = in.createTypedArrayList(Area.CREATOR);
        from = in.readInt();
        to = in.readInt();
        store = in.readParcelable(Store.class.getClassLoader());
    }

    public static final Creator<FilterItemsModel> CREATOR = new Creator<FilterItemsModel>() {
        @Override
        public FilterItemsModel createFromParcel(Parcel in) {
            return new FilterItemsModel(in);
        }

        @Override
        public FilterItemsModel[] newArray(int size) {
            return new FilterItemsModel[size];
        }
    };

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Categories getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Categories selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public Sub_Cat getSub_cat() {
        return sub_cat;
    }

    public void setSub_cat(Sub_Cat sub_cat) {
        this.sub_cat = sub_cat;
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(city, flags);
        dest.writeParcelable(selectedCategory, flags);
        dest.writeParcelable(sub_cat, flags);
        dest.writeTypedList(areasList);
        dest.writeInt(from);
        dest.writeInt(to);
        dest.writeParcelable(store, flags);
    }
}
