package com.fashion.rest.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fashion.rest.R;
import com.fashion.rest.database.DBHelper;
import com.fashion.rest.model.Areas;
import com.fashion.rest.model.City;
import com.fashion.rest.model.Countries;
import com.fashion.rest.presnter.JsonPlaceHolderApi;
import com.fashion.rest.view.activity.mainScreem.MainActivity;
import com.fashion.rest.view.fragments.FragmentSelectCountry;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.fashion.rest.apiURL.API.apiURLBase;
import static com.fashion.rest.functions.Functions.getTimeStamp;
import static com.fashion.rest.functions.RetrofitFunctions.getAreasApi;
import static com.fashion.rest.functions.RetrofitFunctions.getCitiesApi;
import static com.fashion.rest.functions.RetrofitFunctions.getCountries;
import static com.fashion.rest.functions.RetrofitFunctions.getNumberOfCountries;
import static com.fashion.rest.sharedPreferences.Country.getCountryFromSP;
import static com.fashion.rest.sharedPreferences.Country.saveCountryInSP;
import static com.fashion.rest.sharedPreferences.Language.getLanguageFromSP;
import static com.fashion.rest.sharedPreferences.LoginInfo.getLoginOrNotFromSP;
import static com.fashion.rest.sharedPreferences.LoginInfo.getTokenFromSP;
import static com.fashion.rest.sharedPreferences.NumberOfNotification.getNumberOfItemInCartFromSP;
import static com.fashion.rest.sharedPreferences.NumberOfNotification.saveNumberOfItemsInCartInSP;

public class SplashScreen extends AppCompatActivity {

    JsonPlaceHolderApi jsonPlaceHolderApi,jsonPlaceHolderApiCountries,jsonPlaceHolderApiCities,jsonPlaceHolderApiAreas;
    Retrofit retrofit,retrofitCountries,retrofitCities,retrofitAreas;
    FragmentSelectCountry fragmentSelectCountry= new FragmentSelectCountry();
    DBHelper dbHelper;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    String welcome_image = "https://firebasestorage.googleapis.com/v0/b/uae-kandora.appspot.com/o/arabes_logo.jpeg?alt=media&token=cc61497d-565a-4154-9f34-cae0a9c436a4";
    String welcome_2 ="https://firebasestorage.googleapis.com/v0/b/uae-kandora.appspot.com/o/welcome.jpg?alt=media&token=c9cf32bf-5e83-465e-91e9-4446317b0ea3";
    String optional_en = "Welcome in monsters company";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        statusBarColor();
        intiRetrofitAndPlaceHolder();
        dbHelper = new DBHelper(SplashScreen.this);
        if (getCountryFromSP(this) != null && !getCountryFromSP(this).isEmpty())
        {
            timer();
        }else{
            //insert welcome notification
            dbHelper.insertNotifications(welcome_2,"M+","M+","Welcome in M+ company"
                    ,"اهلا بكم في شركة M+","welcome_screen","empty",getTimeStamp()
                    ,getResources().getString(R.string.about_us_eng_r),getResources().getString(R.string.about_us_a_r),"0");

//            dbHelper.insertNotifications(welcome_image,"arabesc digital","اربيسك لتكنولوجيا","Welcome in Arabesc digital company"
//                    ,"اهلا بكم في شركة اربيسك للتكنولوجيا","welcome_screen","empty"
//                    ,getTimeStamp(),getResources().getString(R.string.about_us_eng),getResources().getString(R.string.about_us_ar),"0");

            updateNumberOfNotification();
            getCountriesDe();
        }
    }

    private void updateNumberOfNotification() {
        String num = getNumberOfItemInCartFromSP(getApplicationContext());
        if (num != null && !num.isEmpty())
        {
            int x = Integer.parseInt(num);
            int y = x+1;
            saveNumberOfItemsInCartInSP(getApplicationContext(),String.valueOf(y));
        }else{
            saveNumberOfItemsInCartInSP(getApplicationContext(),"1");
        }
    }

    private void intiRetrofitAndPlaceHolder() {
        retrofit = getNumberOfCountries();
        retrofitCountries = getCountries();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        jsonPlaceHolderApiCountries= retrofitCountries.create(JsonPlaceHolderApi.class);

    }

    private void getCountriesDe() {
        jsonPlaceHolderApiCountries = retrofitCountries.create(JsonPlaceHolderApi.class);
        Call<List<Countries>> call = jsonPlaceHolderApiCountries.getCountries();
        call.enqueue(new Callback<List<Countries>>() {
            @Override
            public void onResponse(Call<List<Countries>> call, Response<List<Countries>> response) {
                if (!response.isSuccessful())
                { return; }
                List<Countries> countriesL = response.body();
                if (countriesL.size() >1)
                {
                    handelOffersFragment();

                }else{
                    // save country info in SP
                    saveCountryInSP(SplashScreen.this,sharedPreferences,editor,countriesL.get(0).getName_en(),countriesL.get(0).getName_local()
                            ,countriesL.get(0).getId(),apiURLBase()+countriesL.get(0).getFlag().getUrl());

                    intiRetrofitAndPlaceHolder2(countriesL.get(0));
                    getCities();
                }
            }
            @Override
            public void onFailure(Call<List<Countries>> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private void intiRetrofitAndPlaceHolder2(Countries countries) {
        retrofitCities = getCitiesApi(countries.getId());
        jsonPlaceHolderApiCities = retrofitCities.create(JsonPlaceHolderApi.class);

        retrofitAreas = getAreasApi(countries.getId());
        jsonPlaceHolderApiAreas = retrofitAreas.create(JsonPlaceHolderApi.class);
    }

    private void getCities() {
        Call<List<City>> call = jsonPlaceHolderApiCities.getCities();
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                if (!response.isSuccessful())
                { return; }
                List<City> citiesL = response.body();
                for (City city:citiesL)
                {
                    // save cities info in DP
                    dbHelper.insertCities(city.getIdServer(),city.getName_en(),city.getName_local());
                }
                getAreas();
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private void getAreas() {
        Call<List<Areas>> call = jsonPlaceHolderApiAreas.getAreas();
        call.enqueue(new Callback<List<Areas>>() {
            @Override
            public void onResponse(Call<List<Areas>> call, Response<List<Areas>> response) {
                if (!response.isSuccessful())
                {
                    Log.i("TAG Error code", String.valueOf(response.code()));
                    return;
                }
                List<Areas> areasL = response.body();
                for (Areas area:areasL)
                {
                    // save areas info in DP
                    dbHelper.insertNeighborhood(area.getIdServer(),area.getCity().getName_en(),area.getCity().getName_local(),area.getName_en(),area.getName_local());
                }
                timer();
            }
            @Override
            public void onFailure(Call<List<Areas>> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private void timer() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (getLanguageFromSP(getApplicationContext()) == null || getLanguageFromSP(getApplicationContext()).equals(""))
                {
                    Intent intent = new Intent(SplashScreen.this, com.fashion.rest.view.activity.Language.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
                    finishAffinity();
                }else{
                    if (getLoginOrNotFromSP(getApplicationContext()) == null || getLoginOrNotFromSP(getApplicationContext()).equals("") || !getLoginOrNotFromSP(getApplicationContext()).equals("1"))
                    {
                        Intent intent = new Intent(SplashScreen.this, Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("from", "splash");
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
                        finishAffinity();
                    }else {
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
                        finishAffinity();
                    }
                }
            }
        }, 1000);
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void handelOffersFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("from", "splash");
        fragmentSelectCountry.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_countries, fragmentSelectCountry)
                .commit();
    }

    public void getNumberOfCountriesInt() {
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<Integer> call = jsonPlaceHolderApi.getNumberOfCount();
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.i("TAG onResponse", "Im here");
                if (!response.isSuccessful())
                {
                    Log.i("TAG Error code", String.valueOf(response.code()));
                    return;
                }
                Log.i("TAG Response", response.body().toString());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

}
