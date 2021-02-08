package com.fashion.rest.view.activity;

import android.graphics.Paint;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.presnter.ImageClicked;
import com.fashion.rest.view.Adapters.SlidingImage_Adapter;
import com.fashion.rest.view.fragments.FragmentAddToCart;
import com.fashion.rest.view.fragments.FragmentImageSlider;
import com.fashion.rest.view.fragments.FragmentItemDetails;
import com.fashion.rest.view.fragments.fragmentItemDetails.FragmentContact;
import com.fashion.rest.view.fragments.fragmentItemDetails.FragmentFullImageSlider;

import java.util.ArrayList;

import static com.fashion.rest.functions.Functions.fillImgArrayL;

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

    String itemID,itemName,cat,cat_type,from;
    String storeNameStr, storeImage, website_link;
    int fullImageOnTheTop =0;

    RelativeLayout fullImageCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        getInfoFromCat();
        inti();
        titleActionBar();
        intiImageSlider();
        handelFragmentFullImage();
        ItemDetailsFragment();
        contactFragment();

    }

    private void contactFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_contact, fragmentContact)
                .commit();
    }

    private void getInfoFromCat() {
        Bundle bundle = getIntent().getExtras();
        itemID =bundle.getString("itemID");
        itemName =bundle.getString("itemName");
        cat =bundle.getString("cat");
        cat_type =bundle.getString("cat_type");
        from =bundle.getString("from");
    }

    private void ItemDetailsFragment() {
        storeNameStr = "Lacoste";
        storeImage = "https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/lacoste-logo.png?alt=media&token=3f7d0317-11d6-4b6d-9763-4c3e3eec08e4";
        website_link = "https://www.farfetch.com/ae/shopping/men/lacoste/items.aspx?utm_source=google&utm_medium=cpc&utm_keywordid=123323324&pid=google_search&af_channel=Search&c=658279425&af_c_id=658279425&af_siteid=&af_keywords=kwd-88551300&af_adset_id=35964967809&af_ad_id=492268062138&af_sub1=123323324&is_retargeting=true";
        Bundle bundle = new Bundle();
        bundle.putString("cat", cat);
        bundle.putString("cat_type", cat_type);
        bundle.putString("storeNameStr", storeNameStr);
        bundle.putString("storeImage", storeImage);
        bundle.putString("website_link", website_link);

        fragmentItemDetails.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_item_details, fragmentItemDetails)
                .commit();
    }

    private void intiImageSlider() {
        Bundle bundle = new Bundle();
        bundle.putString("itemName", "Bracelets name");
        bundle.putString("cat", "Bracelets");

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
        title.setText(getResources().getString(R.string.item_name));
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
                    collapsingToolbarLayout.setTitle(getResources().getString(R.string.item_name));

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
        if (priceEdit.equals("0"))
        {
            itemPriceTV.setVisibility(View.VISIBLE);
            oldPriceTV.setVisibility(View.GONE);
            itemNewPriceTV.setText("123"
                    +" "+getResources().getString(R.string.jod));
            //itemPriceTV.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
            //set new price empty to stay design
            itemPriceTV.setText("");
            //itemNewPriceTV.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        }else{
            itemPriceTV.setVisibility(View.GONE);
            oldPriceTV.setVisibility(View.VISIBLE);

            oldPriceTV.setText("123");
            //change text color
            oldPriceTV.setTextColor(getResources().getColor(R.color.colorWhite));
            //set line above old price
            oldPriceTV.setPaintFlags(itemPriceTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            //change size new price
            //itemNewPriceTV.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

            itemNewPriceTV.setText("99"
                    +" "+getResources().getString(R.string.jod));
            //fill old price
            itemPriceTV.setText("250"
                    +" "+getResources().getString(R.string.jod));

        }
    }

    @Override
    public void imageClicked(String test) {
        fullImageCont.setVisibility(View.VISIBLE);
        fullImageOnTheTop =1;
    }


    public void handelFragmentFullImage() {
        ArrayList<String> images = new ArrayList<>();
        images = fillImgArrayL();

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
            fullImageOnTheTop =0;
            fullImageCont.setVisibility(View.GONE);
        }else{
            fragmentFullImageSlider.diestroyAsynk();
            finish();
        }
    }
}