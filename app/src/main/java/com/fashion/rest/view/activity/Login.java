package com.fashion.rest.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.fashion.rest.R;
import com.fashion.rest.view.activity.mainScreem.MainActivity;

import static com.fashion.rest.sharedPreferences.LoginInfo.saveLoginInSP;

public class Login extends AppCompatActivity {

    Button login;
    RelativeLayout fb,g,skip;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        statusBarColor();
        inti();
        actionListenerToLogin();
        actionListenerToFB();
        actionListenerToG();
        actionListenerToSkip();
    }

    private void actionListenerToSkip() {
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToMainScreen();
            }
        });
    }

    private void actionListenerToG() {
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLoginInSP(getApplicationContext(),sharedPreferences,editor,"1");
                moveToMainScreen();
            }
        });
    }

    private void actionListenerToFB() {
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLoginInSP(getApplicationContext(),sharedPreferences,editor,"1");
                moveToMainScreen();
            }
        });
    }

    private void actionListenerToLogin() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLoginInSP(getApplicationContext(),sharedPreferences,editor,"1");
                moveToMainScreen();
            }
        });
    }

    private void moveToMainScreen() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
        finish();
    }

    private void inti() {
        login = (Button) findViewById(R.id.login);
        fb = (RelativeLayout) findViewById(R.id.fb);
        fb = (RelativeLayout) findViewById(R.id.fb);
        g = (RelativeLayout) findViewById(R.id.g);
        skip = (RelativeLayout) findViewById(R.id.skip);
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void moveBack() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}