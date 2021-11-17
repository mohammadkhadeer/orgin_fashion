package com.blue_b.rest.view.activity;

import android.graphics.Paint;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blue_b.rest.R;
import com.blue_b.rest.functions.Functions;
import com.blue_b.rest.model.ItemTest;
import com.blue_b.rest.presnter.ImageClicked;
import com.blue_b.rest.view.fragments.FragmentImageSlider;
import com.blue_b.rest.view.fragments.fragmentItemDetails.FragmentItemDetails;
import com.blue_b.rest.view.fragments.fragmentItemDetails.FragmentContact;
import com.blue_b.rest.view.fragments.fragmentItemDetails.FragmentFullImageSlider;
import com.blue_b.rest.apiURL.API;

import java.util.ArrayList;

import static com.blue_b.rest.view.activity.mainScreem.MainActivity.setLocale;

public class ItemDetails extends AppCompatActivity implements ImageClicked {
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appbar;
    TextView itemPriceTV,oldPriceTV,itemNewPriceTV,title;
    LinearLayout show_item_details_header;
    FragmentImageSlider fragmentImageSlider = new FragmentImageSlider();
    FragmentItemDetails fragmentItemDetails = new FragmentItemDetails();
    FragmentContact fragmentContact = new FragmentContact();
    FragmentFullImageSlider fragmentFullImageSlider = new FragmentFullImageSlider();

    String storeNameStr, storeImage, website_link,from,offerLink,promoCode;
    int fullImageOnTheTop =0;

    RelativeLayout fullImageCont;
    ItemTest itemTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale(this);
        setContentView(R.layout.activity_item_details);

        getInfoFromCat();
        inti();
        titleActionBar();
        intiImageSlider();

        ItemDetailsFragment();
        contactFragment();
    }

    private void contactFragment() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("item_object", itemTest);

        fragmentContact.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_contact, fragmentContact)
                .commit();
    }

    private void getInfoFromCat() {
        itemTest = (ItemTest) getIntent().getParcelableExtra("item_object");
        from = getIntent().getStringExtra("from");
        offerLink = getIntent().getStringExtra("offer_link");
        promoCode = getIntent().getStringExtra("promo_code");
    }

    private void ItemDetailsFragment() {
        Log.i("TAG","activity offer_link: "+offerLink);
        Log.i("TAG","activity promo_code: "+promoCode);
        Bundle bundle = new Bundle();
        bundle.putString("cat", "cat");
        bundle.putString("cat_type", "cat_type");
        bundle.putString("storeNameStr", storeNameStr);
        bundle.putString("storeImage", storeImage);
        bundle.putString("website_link", website_link);
        bundle.putString("from", from);
        bundle.putString("offer_link", offerLink);
        bundle.putString("promo_code", promoCode);
        bundle.putParcelable("item_object", itemTest);

        fragmentItemDetails.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_item_details, fragmentItemDetails)
                .commit();
    }

    private void intiImageSlider() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("item_object", itemTest);

        fragmentImageSlider.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.selected_item_details_image_slider_container, fragmentImageSlider);
        transaction.commit();
    }

    private void inti() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.show_item_details_toolbar);
        appbar = (AppBarLayout)findViewById(R.id.show_item_details_app_bar);

        itemPriceTV = (TextView) findViewById(R.id.show_item_details_car_price);
        oldPriceTV = (TextView) findViewById(R.id.show_item_details_car_old_price);
        itemNewPriceTV = (TextView) findViewById(R.id.show_item_details_new_price);
        show_item_details_header = (LinearLayout) findViewById(R.id.show_item_details_header);
        title = (TextView) findViewById(R.id.show_item_details_title);
        fullImageCont = (RelativeLayout) findViewById(R.id.fullImageCont);
    }

    private void titleActionBar() {
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);

        fillPrice();
        title.setText(Functions.getTextEngOrLocal(this,itemTest.getName(),itemTest.getName_local()));
        title.setTypeface(Functions.changeFontGeneral(getApplicationContext()));

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isVisible = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    show_item_details_header.setVisibility(View.VISIBLE);
                    collapsingToolbarLayout.setTitle(Functions.getTextEngOrLocal(getApplicationContext(),itemTest.getName(),itemTest.getName_local()));

                    statusBarColor();
                    isVisible = true;
                } else if (isVisible) {
                    collapsingToolbarLayout.setTitle(" ");
                    show_item_details_header.setVisibility(View.GONE);
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

    private void fillPrice() {
        String priceEdit="1";
        String aed = getResources().getString(R.string.aed);
        if (priceEdit.equals("0"))
        {
            itemPriceTV.setVisibility(View.VISIBLE);
            oldPriceTV.setVisibility(View.GONE);
            itemNewPriceTV.setText(itemTest.getPrice()
                    +" "+aed);
            //itemPriceTV.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
            //set new price empty to stay design
            itemPriceTV.setText("");
            //itemNewPriceTV.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        }else{
            itemPriceTV.setVisibility(View.GONE);
            oldPriceTV.setVisibility(View.VISIBLE);

            oldPriceTV.setText(itemTest.getPrice());
            //change text color
            oldPriceTV.setTextColor(getResources().getColor(R.color.colorWhite));
            //set line above old price
            oldPriceTV.setPaintFlags(itemPriceTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            //change size new price
            //itemNewPriceTV.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

            itemNewPriceTV.setText(itemTest.getDiscountPrice()
                    +" "+aed);
            //fill old price
            itemPriceTV.setText(itemTest.getPrice()
                    +" "+aed);

        }
    }

    @Override
    public void imageClicked(String test) {
        handelFragmentFullImage();
        fullImageCont.setVisibility(View.VISIBLE);
        fullImageOnTheTop =1;
    }


    public void handelFragmentFullImage() {
        ArrayList<String> images = new ArrayList<>();
        for (int i=0;i<itemTest.getFlagArrayL().size();i++)
        {
            images.add(API.apiURLBase()+itemTest.getFlagArrayL().get(i).getUrl());
        }
        //images = fillImgArrayL();

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("allImage", images);


        fragmentFullImageSlider.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_full_image_slider, fragmentFullImageSlider)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (fullImageOnTheTop==1)
        {
            fragmentFullImageSlider.diestroyAsynk();
            fullImageOnTheTop =0;
            fullImageCont.setVisibility(View.GONE);
        }else{
            finish();
        }
    }
}