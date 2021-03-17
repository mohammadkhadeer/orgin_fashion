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

public class SplashScreen extends AppCompatActivity {

    JsonPlaceHolderApi jsonPlaceHolderApi,jsonPlaceHolderApiCountries,jsonPlaceHolderApiCities,jsonPlaceHolderApiAreas;
    Retrofit retrofit,retrofitCountries,retrofitCities,retrofitAreas;
    FragmentSelectCountry fragmentSelectCountry= new FragmentSelectCountry();
    DBHelper dbHelper;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    String welcome_image = "https://firebasestorage.googleapis.com/v0/b/hala-motor-8ff46.appspot.com/o/welcome.jpg?alt=media&token=bb1fa9bf-e789-4b47-996a-10509cc946ea";
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
            dbHelper.insertNotifications(welcome_image,"M+","المرعبين المحدودة","Welcome in monsters company"
                    ,"اهلا بكم في شركة المربين المحدودة","welcome_screen","empty",getTimeStamp(),getOptionalEng(),getOptionalLocal(),"0");
            getCountriesDe();
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

    public String getOptionalEng() {
        String x = optional_en + "\n"+ "\n" +"We try to create fucking app where can handel almost famous people to can get at least 10 K JOD"+ "                " +
                "                                                               " +
                "                        "
                +"Now i will try to set any text just to make a screen as scroll screen"+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               "+
                "line 1 any text "+ "                                               ";
        return x;
    }

    public String getOptionalLocal() {
        String x = "اهلا وسهلا بكم في شركة المرعبين المحدودة" + "\n"+ "\n" +"والله سلامتك بحنحاول نعمل تطبيق للمشاهير اقل اشي متوقعين انه نبيع عشر نسخ بشهر"+ "                " +
                "                                                               " +
                "                        "
                +"وهسا زي ما شفت لما كان الحكي انقليزي بدي احاول احط اي حكي مشان تصير الشاشة سكرول"+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+
                "اي حكي مشان تصير سكرول "+ "                                               "+

                "و اخيرا انا انا ابريق الشاي"+ "                                               ";
        return x;
    }

}

//    private void getComment() {
//        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
//        Call<List<Comment>> call = jsonPlaceHolderApi.getComment(api1(3));
//        call.enqueue(new Callback<List<Comment>>() {
//            @Override 544496417
//            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
//                if (!response.isSuccessful())
//                {
//                    Log.i("TAG Error code", String.valueOf(response.code()));
//                    return;
//                }
//
//                List<Comment> comments = response.body();
//                for (Comment comment:comments)
//                {
//                    Log.i("TAG response id", String.valueOf(comment.getId()));
//                    Log.i("TAG response userID", String.valueOf(comment.getPostId()));
//                    Log.i("TAG response title", String.valueOf(comment.getName()));
//                    Log.i("TAG response text", String.valueOf(comment.getText())+"\n");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Comment>> call, Throwable t) {
//                Log.i("TAG Error",t.getMessage());
//            }
//        });
//    }
//
//    private void getAllDta() {
//        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
//        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                if (!response.isSuccessful())
//                {
//                    Log.i("TAG Error code", String.valueOf(response.code()));
//                    return;
//                }
//
//                List<Post> posts = response.body();
//                for (Post post:posts)
//                {
//                    Log.i("TAG response id", String.valueOf(post.getId()));
//                    Log.i("TAG response userID", String.valueOf(post.getUserId()));
//                    Log.i("TAG response title", String.valueOf(post.getTitle()));
//                    Log.i("TAG response text", String.valueOf(post.getText())+"\n");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                Log.i("TAG Error",t.getMessage());
//            }
//        });
//    }