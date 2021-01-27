package com.fashion.rest.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fashion.rest.R;
import com.fashion.rest.view.fragments.fragmentHomeMainScreen.FragmentResults;

public class ResultActivity extends AppCompatActivity {

    FragmentResults fragmentResults = new FragmentResults();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        handelResultFragment();
    }

    private void handelResultFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_result_activity, fragmentResults)
                .commit();
    }

}