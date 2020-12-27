package com.fashion.rest.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.fashion.rest.R;
import com.fashion.rest.view.activity.mainScreem.MainActivity;

import static com.fashion.rest.sharedPreferences.Language.getLanguageFromSP;
import static com.fashion.rest.sharedPreferences.Language.saveLanguageInSP;
import static com.fashion.rest.sharedPreferences.LoginInfo.getLoginOrNotFromSP;

public class Language extends AppCompatActivity {

    RadioButton radioButtonArabic,radioButtonEnglish;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor2;
    SharedPreferences sharedPreferences2;

    String comeFrom=null;
    LinearLayout linearLayoutArabic,linearLayoutEng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        statusBarColor();
        init();
        //getFromIntent();
        fillRadioButton();
        actionListenerToArabic();
    }

    private void getFromIntent() {
        Bundle bundle = getIntent().getExtras();
        comeFrom =bundle.getString("setting");
    }

    private void fillRadioButton() {
        Drawable d = getResources().getDrawable(R.drawable.language_por);
        if (getLanguageFromSP(this) != null)
        {
            if (getLanguageFromSP(this).equals("ar"))
            {
                radioButtonArabic.setChecked(true);

                linearLayoutArabic.setBackgroundDrawable(d);
            }else{
                radioButtonEnglish.setChecked(true);
                linearLayoutEng.setBackgroundDrawable(d);
            }
        }
    }

    private void actionListenerToArabic() {
        linearLayoutArabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLoginOrNotFromSP(getApplicationContext()) == null || getLoginOrNotFromSP(getApplicationContext()).equals("") || !getLoginOrNotFromSP(getApplicationContext()).equals("1"))
                {
                    Drawable d = getResources().getDrawable(R.drawable.language_por);
                    Drawable dd = getResources().getDrawable(R.drawable.language_without_por);
                    linearLayoutArabic.setBackgroundDrawable(d);
                    linearLayoutEng.setBackgroundDrawable(dd);
                    saveLanguageInSP(getApplicationContext(),sharedPreferences,editor,"ar");

                    Intent intent = new Intent(Language.this, Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
                    finish();
                }else{
                    fun();
                }

            }
        });


        linearLayoutEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLoginOrNotFromSP(getApplicationContext()) == null || getLoginOrNotFromSP(getApplicationContext()).equals("") || !getLoginOrNotFromSP(getApplicationContext()).equals("1"))
                {
                    Drawable d = getResources().getDrawable(R.drawable.language_por);
                    Drawable dd = getResources().getDrawable(R.drawable.language_without_por);
                    linearLayoutEng.setBackgroundDrawable(d);
                    linearLayoutArabic.setBackgroundDrawable(dd);
                    saveLanguageInSP(getApplicationContext(),sharedPreferences,editor,"en");

                    Intent intent = new Intent(Language.this, Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
                    finish();
                }else{
                    Drawable d = getResources().getDrawable(R.drawable.language_por);
                    Drawable dd = getResources().getDrawable(R.drawable.language_without_por);
                    linearLayoutEng.setBackgroundDrawable(d);
                    linearLayoutArabic.setBackgroundDrawable(dd);
                    saveLanguageInSP(getApplicationContext(),sharedPreferences,editor,"en");
                    //Toast.makeText(getApplicationContext(),getLanguageFromSP(getApplicationContext()),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Language.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
                    finish();
                }
            }
        });





        radioButtonArabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLanguageInSP(getApplicationContext(),sharedPreferences,editor,"ar");
                Toast.makeText(getApplicationContext(),getLanguageFromSP(getApplicationContext()),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Language.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
                finish();
            }
        });
        radioButtonEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLanguageInSP(getApplicationContext(),sharedPreferences,editor,"en");
                Toast.makeText(getApplicationContext(),getLanguageFromSP(getApplicationContext()),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Language.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
                finish();
            }
        });    }

    private void fun() {
        Drawable d = getResources().getDrawable(R.drawable.language_por);
        Drawable dd = getResources().getDrawable(R.drawable.language_without_por);
        linearLayoutArabic.setBackgroundDrawable(d);
        linearLayoutEng.setBackgroundDrawable(dd);
        saveLanguageInSP(getApplicationContext(),sharedPreferences,editor,"ar");
//                Toast.makeText(getApplicationContext(),getLanguageFromSP(getApplicationContext()),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Language.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
        finish();
    }

    private void init() {
        radioButtonArabic = (RadioButton) findViewById(R.id.radioButton);
        radioButtonEnglish = (RadioButton) findViewById(R.id.radioButton2);
        linearLayoutArabic = (LinearLayout) findViewById(R.id.arab_lan);
        linearLayoutEng = (LinearLayout) findViewById(R.id.eng_lan);
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}