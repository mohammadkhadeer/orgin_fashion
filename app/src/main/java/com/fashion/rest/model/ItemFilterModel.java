package com.fashion.rest.model;

public class ItemFilterModel {
    String filter,filterS;

    public ItemFilterModel(String filter, String filterS) {
        this.filter = filter;
        this.filterS = filterS;
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
}
