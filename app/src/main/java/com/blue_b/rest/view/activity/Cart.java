package com.blue_b.rest.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.blue_b.rest.R;
import com.blue_b.rest.presnter.UpdateTotalNumber;
import com.blue_b.rest.view.fragments.cartFragment.FragmentCartItem;
import com.blue_b.rest.view.fragments.cartFragment.FragmentTotale;

public class Cart extends AppCompatActivity implements UpdateTotalNumber {

    FragmentCartItem fragmentCartItem = new FragmentCartItem();
    FragmentTotale fragmentTotale = new FragmentTotale();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        statusBarColor();
        handelCartItemFragment();
        handelCartTotalFragment();
    }

    private void handelCartTotalFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_cart_total, fragmentTotale)
                .commit();
    }

    private void handelCartItemFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_cart_item, fragmentCartItem)
                .commit();
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("from cart", "from cart");
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void updateNow(Boolean update) {
        fragmentTotale.updateValue();
    }
}