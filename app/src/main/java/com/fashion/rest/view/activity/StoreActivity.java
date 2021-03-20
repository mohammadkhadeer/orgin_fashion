package com.fashion.rest.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.FilterItemsModel;
import com.fashion.rest.model.ItemTest;
import com.fashion.rest.model.Store;
import com.fashion.rest.view.fragments.fragmentHomeMainScreen.FragmentResults;
import com.fashion.rest.view.fragments.fragmentItemDetails.FragmentContact;
import com.squareup.picasso.Picasso;

import static com.fashion.rest.apiURL.API.apiURLBase;
import static com.fashion.rest.functions.Functions.getDefultToFilterItemModel;
import static com.fashion.rest.functions.Functions.getTextEngOrLocal;
import static com.fashion.rest.sharedPreferences.Language.getLanguageFromSP;

public class StoreActivity extends AppCompatActivity {

    //Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appbar;
    FilterItemsModel filterItemsModel;
    ImageView imageView,storeImage;
    //TextView store_name_tv;//store_details_title;
    //LinearLayout store_details_header_ll;
    TextView store_name,store_address;
    RelativeLayout view_in_google_maps_rl;

    Store store;
    ItemTest itemTest;

    FragmentResults fragmentResults = new FragmentResults();
    FragmentContact fragmentContact = new FragmentContact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        getInfoFromCat();
        inti();
        changeFont();
        titleActionBar();
        fillImageAndSroewName();
        handelResultFragment();
        actionListenerToGoogleMaps();
        contactFragment();
    }

    private void contactFragment() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("item_object", itemTest);
        bundle.putParcelable("store", store);

        fragmentContact.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_contact_store, fragmentContact)
                .commit();
    }

    private void actionListenerToGoogleMaps() {
        view_in_google_maps_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "geo:"+store.getLocationsArrayL().get(0).getLatitude()+","+store.getLocationsArrayL().get(0).getLongitude()+"\"";


                Uri gmmIntentUri = Uri.parse(link);

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
    }

    private void changeFont() {
        store_name.setTypeface(Functions.changeFontGeneral(getApplicationContext()));
        store_address.setTypeface(Functions.changeFontGeneral(getApplicationContext()));
    }

    private void handelResultFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("sub_cat_id",itemTest.getSub_cat().getId());
        bundle.putString("cat_id",itemTest.getSub_cat().getCategory_id());
        bundle.putString("store_id",store.getId());
        bundle.putString("store_area",store.getArea());

        fragmentResults.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.store_items, fragmentResults)
                .commit();
    }

    private void titleActionBar() {
//        toolbar.setTitle(" ");
//        setSupportActionBar(toolbar);
//
//        store_details_title.setText(getTextEngOrLocal(this,store.getName(),store.getName_local()));
//        store_details_title.setTypeface(Functions.changeFontGeneral(getApplicationContext()));

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isVisible = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    //store_details_header_ll.setVisibility(View.VISIBLE);
                    collapsingToolbarLayout.setTitle(getTextEngOrLocal(getApplicationContext(),store.getName(),store.getName_local()));

                    statusBarColor();
                    isVisible = true;
                } else if (isVisible) {
                    collapsingToolbarLayout.setTitle(" ");
                    //store_details_header_ll.setVisibility(View.GONE);
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    isVisible = false;
                }
            }
        });
    }

    private void statusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorRed));
        }
    }


    private void fillImageAndSroewName() {
        Picasso.get()
                .load(apiURLBase()+store.getCover().getUrl())
                .fit()
                .centerCrop()
                .into(imageView);

        Picasso.get()
                .load(apiURLBase()+store.getFlag().getUrl())
                .fit()
                .centerCrop()
                .into(storeImage);

        store_name.setText(getTextEngOrLocal(this,store.getName(),store.getName_local()));
        store_address.setText(store.getAddress());
    }

    private void inti() {
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.show_item_details_toolbar);
        appbar = (AppBarLayout)findViewById(R.id.show_item_details_app_bar);

        imageView = (ImageView) findViewById(R.id.store_cover_img);
        storeImage = (ImageView) findViewById(R.id.storeImage);

        //store_name_tv = (TextView) findViewById(R.id.store_name_tv);
        //store_details_header_ll = (LinearLayout) findViewById(R.id.store_details_header_ll);
        //store_details_title = (TextView) findViewById(R.id.store_details_title);
        store_name = (TextView) findViewById(R.id.store_name);
        store_address = (TextView) findViewById(R.id.store_address);
        view_in_google_maps_rl = (RelativeLayout) findViewById(R.id.view_in_google_maps_rl);

    }

    private void getInfoFromCat() {
        itemTest = (ItemTest) getIntent().getParcelableExtra("item_object");
        store = itemTest.getStore();
        filterItemsModel = getDefultToFilterItemModel(itemTest.getSub_cat(),store);
    }
}