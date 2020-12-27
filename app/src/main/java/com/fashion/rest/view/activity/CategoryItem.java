package com.fashion.rest.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fashion.rest.R;
import com.fashion.rest.model.ItemFilterModel;
import com.fashion.rest.presnter.Filter;
import com.fashion.rest.view.fragments.FragmentCategoryItems;
import com.fashion.rest.view.fragments.FragmentFilter;

public class CategoryItem extends AppCompatActivity implements Filter {

    String categoryStr,whereCome;
    FragmentFilter fragmentFilter = new FragmentFilter();
    FragmentCategoryItems fragmentCategoryItems = new FragmentCategoryItems();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_item);

        statusBarColor();
        getInfoFromCat();
        ItemsFragment();
        FilterFragment();
    }

    private void ItemsFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("category", categoryStr);

        fragmentCategoryItems.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_filter2, fragmentCategoryItems)
                .commit();
    }

    private void FilterFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("category", categoryStr);

        fragmentFilter.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_filter, fragmentFilter)
                .commit();
    }

    private void getInfoFromCat() {
        Bundle bundle = getIntent().getExtras();
        categoryStr =bundle.getString("category");
        whereCome =bundle.getString("from");
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Override
    public void onFilterClick(ItemFilterModel filterModel, String filterType) {

    }

    @Override
    public void onFilterCancel() {

    }

    @Override
    public void onFilterCityCancel(Boolean cancel) {

    }

    @Override
    public void onFilterNeighborhoodCancel(Boolean cancel) {

    }
}