package com.blue_b.rest.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.blue_b.rest.R;
import com.blue_b.rest.database.DBHelper;
import com.blue_b.rest.presnter.JsonPlaceHolderApi;
import com.blue_b.rest.view.fragments.FragmentSelectCountry;
import com.blue_b.rest.functions.RetrofitFunctions;
import com.blue_b.rest.sharedPreferences.NumberOfNotification;
import retrofit2.Retrofit;

public class SplashScreen extends AppCompatActivity {

    JsonPlaceHolderApi jsonPlaceHolderApi;
    Retrofit retrofit;
    FragmentSelectCountry fragmentSelectCountry= new FragmentSelectCountry();
    DBHelper dbHelper;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    String welcome_2 ="https://firebasestorage.googleapis.com/v0/b/uae-kandora.appspot.com/o/b-logo-02.png?alt=media&token=7e0de5d1-bb05-42be-9687-8d204a8da81d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        statusBarColor();
        moveToLogin();

//        intiRetrofitAndPlaceHolder();
//        dbHelper = new DBHelper(SplashScreen.this);
//        dbHelper.insertNotifications(welcome_2,"Bagdones","بقدونس","Welcome in bagdones company"
//                ,"اهلا بكم في شركة بقدونس","welcome_screen","empty", Functions.getTimeStamp()
//                ,getResources().getString(R.string.about_us_eng_r),getResources().getString(R.string.about_us_a_r),"0");
//
//        updateNumberOfNotification();

    }

    private void moveToLogin() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
                finish();
            }
        }, 1000);
    }

    private void updateNumberOfNotification() {
        String num = NumberOfNotification.getNumberOfItemInCartFromSP(getApplicationContext());
        if (num != null && !num.isEmpty())
        {
            int x = Integer.parseInt(num);
            int y = x+1;
            NumberOfNotification.saveNumberOfItemsInCartInSP(getApplicationContext(),String.valueOf(y));
        }else{
            NumberOfNotification.saveNumberOfItemsInCartInSP(getApplicationContext(),"1");
        }
    }

    private void intiRetrofitAndPlaceHolder() {
        retrofit = RetrofitFunctions.getNumberOfCountries();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

}
