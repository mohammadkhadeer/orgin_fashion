package com.fashion.rest.view.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Brand;
import com.fashion.rest.model.Home;
import com.fashion.rest.model.ItemTest;
import com.fashion.rest.model.Sub_Cat;
import com.fashion.rest.presnter.JsonPlaceHolderApi;
import com.fashion.rest.view.fragments.fragmentHomeMainScreen.FragmentFilter;
import com.fashion.rest.view.fragments.fragmentHomeMainScreen.FragmentResults;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.fashion.rest.apiURL.API.apiURLBase;
import static com.fashion.rest.functions.RetrofitFunctions.getBrand;
import static com.fashion.rest.functions.RetrofitFunctions.getItems;
import static com.fashion.rest.sharedPreferences.Language.getLanguageFromSP;

public class ResultActivity extends AppCompatActivity {

    FragmentResults fragmentResults = new FragmentResults();
    String cat_id,sub_cat_id,sub_cat_name,fromFilter;
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
        setContentView(R.layout.activity_result);

        statusBarColor();
        getInfoFromCat();
        inti();
        fillSubCategoryName();
        getImageBrand();
        changeFont();
        fillResultNumber();
        fillBackImageViewDepLanguage();
        actionListenerToBack();

        handelResultFragment();
    }

    private void fillSubCategoryName() {
        sub_category_name_tv.setText(sub_cat_name);
    }

    private void getImageBrand() {
        intiRetrofit();
        Call<List<Brand>> callHome = jsonPlaceHolderApi.getBrand();
        callHome.enqueue(new Callback<List<Brand>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
                if (!response.isSuccessful())
                { return; }
                List<Brand> itemsList = response.body();
                Picasso.get()
                        .load(apiURLBase()+itemsList.get(0).getFlag().getUrl())
                        .fit()
                        .centerCrop()
                        .into(brand_iv);
            }

            @Override
            public void onFailure(Call<List<Brand>> call, Throwable t) {
                Log.i("TAG","getItemsFromServer  in error");
                Log.i("TAG Error",t.getMessage());
            }
        });

    }

    private void intiRetrofit() {
        retrofit = getBrand();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }


    private void fillResultNumber() {
        String result_text = getResources().getString(R.string.Result_number);
        String result= result_text +" "+ "2";
        result_number_tv.setText(result);
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
        if (getLanguageFromSP(this).equals("en"))
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
        Bundle bundle = getIntent().getExtras();
        sub_cat_id =bundle.getString("sub_cat_id");
        cat_id =bundle.getString("cat_id");
        sub_cat_name =bundle.getString("sub_cat_name");
        fromFilter =bundle.getString("from");

        //sub_catArrayList = (ArrayList<Sub_Cat>) getIntent().getSerializableExtra("sub_cat");
    }

    private void handelResultFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("sub_cat_id",sub_cat_id);
        bundle.putString("cat_id",cat_id);

        //pass price from and rice to and array of area
        if (fromFilter != null && fromFilter.equals("filter"))
        {
            Log.i("TAG",fromFilter);
        }

        fragmentResults.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_result_activity, fragmentResults)
                .commit();
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

}