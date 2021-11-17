package com.blue_b.rest.model;

public class ItemSelectedFilterModel {
    String filter,filterS,filterType;

    public ItemSelectedFilterModel(String filter, String filterS, String filterType) {
        this.filter = filter;
        this.filterS = filterS;
        this.filterType = filterType;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getFilterS() {
        return filterS;
    }

    public void setFilterS(String filterS) {
        this.filterS = filterS;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }
}
