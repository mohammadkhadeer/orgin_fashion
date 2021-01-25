package com.fashion.rest.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fashion.rest.R;
import com.fashion.rest.model.Areas;
import com.fashion.rest.model.City;
import com.fashion.rest.model.Comment;
import com.fashion.rest.model.Post;
import com.fashion.rest.model.Test;
import com.fashion.rest.presnter.JsonPlaceHolderApi;
import com.fashion.rest.view.activity.mainScreem.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.fashion.rest.apiURL.API.api1;
import static com.fashion.rest.apiURL.API.apiBase;
import static com.fashion.rest.sharedPreferences.Language.getLanguageFromSP;
import static com.fashion.rest.sharedPreferences.LoginInfo.getLoginOrNotFromSP;

public class SplashScreen extends AppCompatActivity {

    ArrayList<String> aList = new ArrayList<>();
    ArrayList<Areas> Abu_Dhabi_ArrayList = new ArrayList<>();
    ArrayList<Areas> dubaiArrayList = new ArrayList<>();
    ArrayList<Areas> alInArrayList = new ArrayList<>();
    ArrayList<Areas> Sharjah_ArrayList = new ArrayList<>();
    ArrayList<Areas> ajmanArrayList = new ArrayList<>();
    ArrayList<Areas> rasAlkemaArrayList = new ArrayList<>();
    ArrayList<Areas> umqwinArrayList = new ArrayList<>();
    ArrayList<Areas> fujurahArrayList = new ArrayList<>();
    JsonPlaceHolderApi jsonPlaceHolderApi;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        statusBarColor();
        //loadFromDataBase(this);
        timer();

        retrofit = new Retrofit.Builder()
                .baseUrl(apiBase())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//
//        //getAllDta();
//        //getComment();
        //getAreas();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Log.i("TAG Size Abu_Dhabi", String.valueOf(Abu_Dhabi_ArrayList.size()));
                Log.i("TAG Size Dubai", String.valueOf(dubaiArrayList.size()));
                Log.i("TAG Size Al Ain", String.valueOf(alInArrayList.size()));
                Log.i("TAG Size Sharjah", String.valueOf(Sharjah_ArrayList.size()));
                Log.i("TAG Size Ajman", String.valueOf(ajmanArrayList.size()));
                Log.i("TAG Size Ras Al Khaimah", String.valueOf(rasAlkemaArrayList.size()));
                Log.i("TAG Size Um Al Quwain", String.valueOf(umqwinArrayList.size()));
                Log.i("TAG Size Fujairah", String.valueOf(fujurahArrayList.size()));
            }
        }, 10000);
    }

    private void getAreas() {
        Log.i("TAG getTest", "Im here");

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Areas>> call = jsonPlaceHolderApi.getAreas();
        call.enqueue(new Callback<List<Areas>>() {
            @Override
            public void onResponse(Call<List<Areas>> call, Response<List<Areas>> response) {
                Log.i("TAG onResponse", "Im here");
                if (!response.isSuccessful())
                {
                    Log.i("TAG Error code", String.valueOf(response.code()));
                    return;
                }

                List<Areas> areasL = response.body();
                for (Areas area:areasL)
                {
                    Log.i("TAG response", "Im here");
                    Log.i("TAG response", area.toString());

                    Log.i("TAG area en", String.valueOf(area.getName_en()));
                    Log.i("TAG area en", String.valueOf(area.getName_en()));

                    Log.i("TAG city en", String.valueOf(area.getCity().getName_en()));
                    Log.i("TAG city local", String.valueOf(area.getCity().getName_local())+"\n");

                    if (area.getCity().getName_en().equals("Abu Dhabi"))
                    {
                        City city = new City(area.getCity().getName_en(),area.getCity().getName_local());
                        Areas areaAbuDhabi = new Areas(area.getName_en(),area.getName_local(),city);
                        Abu_Dhabi_ArrayList.add(areaAbuDhabi);
                    }

                    if (area.getCity().getName_en().equals("Dubai"))
                    {
                        City city = new City(area.getCity().getName_en(),area.getCity().getName_local());
                        Areas areaAbuDhabi = new Areas(area.getName_en(),area.getName_local(),city);
                        dubaiArrayList.add(areaAbuDhabi);
                    }

                    if (area.getCity().getName_en().equals("Sharjah"))
                    {
                        City city = new City(area.getCity().getName_en(),area.getCity().getName_local());
                        Areas areaAbuDhabi = new Areas(area.getName_en(),area.getName_local(),city);
                        Sharjah_ArrayList.add(areaAbuDhabi);
                    }

                    if (area.getCity().getName_en().equals("Al Ain"))
                    {
                        City city = new City(area.getCity().getName_en(),area.getCity().getName_local());
                        Areas areaAbuDhabi = new Areas(area.getName_en(),area.getName_local(),city);
                        alInArrayList.add(areaAbuDhabi);
                    }

                    if (area.getCity().getName_en().equals("Ajman"))
                    {
                        City city = new City(area.getCity().getName_en(),area.getCity().getName_local());
                        Areas areaAbuDhabi = new Areas(area.getName_en(),area.getName_local(),city);
                        ajmanArrayList.add(areaAbuDhabi);
                    }

                    if (area.getCity().getName_en().equals("Ras Al Khaimah"))
                    {
                        City city = new City(area.getCity().getName_en(),area.getCity().getName_local());
                        Areas areaAbuDhabi = new Areas(area.getName_en(),area.getName_local(),city);
                        rasAlkemaArrayList.add(areaAbuDhabi);
                    }

                    if (area.getCity().getName_en().equals("Um Al Quwain"))
                    {
                        City city = new City(area.getCity().getName_en(),area.getCity().getName_local());
                        Areas areaAbuDhabi = new Areas(area.getName_en(),area.getName_local(),city);
                        umqwinArrayList.add(areaAbuDhabi);
                    }

                    if (area.getCity().getName_en().equals("Fujairah"))
                    {
                        City city = new City(area.getCity().getName_en(),area.getCity().getName_local());
                        Areas areaAbuDhabi = new Areas(area.getName_en(),area.getName_local(),city);
                        fujurahArrayList.add(areaAbuDhabi);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Areas>> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private void getComment() {
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Comment>> call = jsonPlaceHolderApi.getComment(api1(3));
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful())
                {
                    Log.i("TAG Error code", String.valueOf(response.code()));
                    return;
                }

                List<Comment> comments = response.body();
                for (Comment comment:comments)
                {
                    Log.i("TAG response id", String.valueOf(comment.getId()));
                    Log.i("TAG response userID", String.valueOf(comment.getPostId()));
                    Log.i("TAG response title", String.valueOf(comment.getName()));
                    Log.i("TAG response text", String.valueOf(comment.getText())+"\n");
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private void getAllDta() {
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful())
                {
                    Log.i("TAG Error code", String.valueOf(response.code()));
                    return;
                }

                List<Post> posts = response.body();
                for (Post post:posts)
                {
                    Log.i("TAG response id", String.valueOf(post.getId()));
                    Log.i("TAG response userID", String.valueOf(post.getUserId()));
                    Log.i("TAG response title", String.valueOf(post.getTitle()));
                    Log.i("TAG response text", String.valueOf(post.getText())+"\n");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
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
                    finish();
                }else{
                    if (getLoginOrNotFromSP(getApplicationContext()) == null || getLoginOrNotFromSP(getApplicationContext()).equals("") || !getLoginOrNotFromSP(getApplicationContext()).equals("1"))
                    {
                        Intent intent = new Intent(SplashScreen.this, Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
                        finish();
                    }else {
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
                        finish();
                    }
                }
            }
        }, 1000);
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }


}