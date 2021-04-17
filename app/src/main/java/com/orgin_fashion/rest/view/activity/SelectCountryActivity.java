package com.orgin_fashion.rest.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.orgin_fashion.rest.R;
import com.orgin_fashion.rest.view.fragments.FragmentSelectCountry;

import static com.orgin_fashion.rest.view.activity.mainScreem.MainActivity.setLocale;

public class SelectCountryActivity extends AppCompatActivity {

    FragmentSelectCountry fragmentSelectCountry = new FragmentSelectCountry();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale(this);
        setContentView(R.layout.activity_select_country);

        statusBarColor();
        handelOffersFragment();
    }
    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void handelOffersFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("from", "activity");
        fragmentSelectCountry.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_countries, fragmentSelectCountry)
                .commit();
    }
}