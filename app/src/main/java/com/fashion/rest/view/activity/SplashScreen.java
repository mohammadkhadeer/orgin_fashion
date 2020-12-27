package com.fashion.rest.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fashion.rest.R;
import com.fashion.rest.view.activity.mainScreem.MainActivity;

import static com.fashion.rest.sharedPreferences.Language.getLanguageFromSP;
import static com.fashion.rest.sharedPreferences.LoginInfo.getLoginOrNotFromSP;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        statusBarColor();
        timer();
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