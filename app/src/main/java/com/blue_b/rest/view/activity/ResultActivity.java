package com.blue_b.rest.view.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blue_b.rest.R;
import com.blue_b.rest.functions.Functions;
import com.blue_b.rest.model.Brand;
import com.blue_b.rest.model.FilterItemsModel;
import com.blue_b.rest.presnter.JsonPlaceHolderApi;
import com.blue_b.rest.view.fragments.fragmentHomeMainScreen.FragmentFilter;
import com.blue_b.rest.view.fragments.fragmentHomeMainScreen.FragmentResults;
import com.blue_b.rest.apiURL.API;
import com.blue_b.rest.functions.FunctionsResultsNumber;
import com.blue_b.rest.functions.RetrofitFunctions;
import com.blue_b.rest.sharedPreferences.Language;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.blue_b.rest.view.activity.mainScreem.MainActivity.setLocale;

public class ResultActivity extends AppCompatActivity {

    FragmentResults fragmentResults = new FragmentResults();
    FilterItemsModel filterItemsModel;
    ImageView backIV,brand_iv;
    RelativeLayout back_rl;
    TextView sub_category_name_tv,result_number_tv;
    FragmentFilter fragmentFilter = new FragmentFilter();
    int filterOnTheTop=0;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale(this);
        setContentView(R.layout.activity_result);

        statusBarColor();
        getInfoFromCat();
        inti();
        intiRetrofit();
        fillSubCategoryName();
        getImageBrand();
        changeFont();
        FunctionsResultsNumber.fillNumberOfItemsResult(this,result_number_tv,filterItemsModel,"result_activity");
        fillBackImageViewDepLanguage();
        actionListenerToBack();

        handelResultFragment();
    }

    private void fillSubCategoryName() {
        sub_category_name_tv.setText(Functions.getTextEngOrLocal(getApplicationContext(),filterItemsModel.getSub_cat().getName_en(),filterItemsModel.getSub_cat().getName_local()));
    }

    private void getImageBrand() {
        Call<Brand> callHome = jsonPlaceHolderApi.getBrandSubCat();
        callHome.enqueue(new Callback<Brand>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<Brand> call, Response<Brand> response) {
                if (!response.isSuccessful())
                { return; }
                Brand itemsList = response.body();
                Picasso.get()
                        .load(API.apiURLBase()+itemsList.getFlag().getUrl())
                        .fit()
                        .centerCrop()
                        .into(brand_iv);
            }

            @Override
            public void onFailure(Call<Brand> call, Throwable t) {
                Log.i("TAG","getItemsFromServer  in error");
                Log.i("TAG Error",t.getMessage());
            }
        });

    }

    private void intiRetrofit() {
        retrofit = RetrofitFunctions.getBrandSubCat(filterItemsModel.getSub_cat().getBrand());
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    private void changeFont() {
        sub_category_name_tv.setTypeface(Functions.changeFontBold(this));
        result_number_tv.setTypeface(Functions.changeFontGeneral(this));
    }

    private void actionListenerToBack() {
        back_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void fillBackImageViewDepLanguage() {
        if (Language.getLanguageFromSP(this).equals("en"))
        {
            backIV.setImageResource(R.drawable.arrow_en);
        } else
        {
            backIV.setImageResource(R.drawable.arrow_ar);
        }
    }

    private void inti() {
        backIV = (ImageView) findViewById(R.id.backIV);
        back_rl = (RelativeLayout) findViewById(R.id.back_rl);
        sub_category_name_tv = (TextView) findViewById(R.id.sub_category_name_tv);
        result_number_tv = (TextView) findViewById(R.id.result_number_tv);
        brand_iv = (ImageView) findViewById(R.id.brand);
    }

    private void getInfoFromCat() {
        filterItemsModel = (FilterItemsModel) getIntent().getParcelableExtra("filter_object");
    }

    private void handelResultFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("sub_cat_id",filterItemsModel.getSub_cat().getId());
        bundle.putString("cat_id",filterItemsModel.getSub_cat().getCategory_id());
        bundle.putParcelable("filter_object",filterItemsModel);

        fragmentResults.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_result_activity, fragmentResults)
                .commit();
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

}