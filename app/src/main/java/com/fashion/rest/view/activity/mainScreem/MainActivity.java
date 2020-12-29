package com.fashion.rest.view.activity.mainScreem;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Deal;
import com.fashion.rest.presnter.PassObject;
import com.fashion.rest.view.activity.Cart;
import com.fashion.rest.view.fragments.fragmentHomeMainScreen.FragmentHomeScreen;
import com.fashion.rest.view.fragments.fragmentHomeMainScreen.FragmentNotification;
import com.fashion.rest.view.fragments.fragmentHomeMainScreen.FragmentProfile;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.Locale;

import static com.fashion.rest.functions.FunctionCart.numberOfItemsOnCartNow;
import static com.fashion.rest.functions.FunctionCart.updateNumberOfItemInCartAndInsertToDataBase;
import static com.fashion.rest.sharedPreferences.Language.getLanguageFromSP;

public class MainActivity extends AppCompatActivity implements PassObject {
    private TextView appNameTV;
    BottomBar bottomBar;
    EditText searchEdt;

    final Fragment fragmentHome = new FragmentHomeScreen();
    final Fragment fragmentNotification = new FragmentNotification();
    final Fragment fragmentProfile = new FragmentProfile();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentHome;

    BottomBarTab homeBBT, notificationsBBT,profileBBT;
    private BottomBarTab[] bbtArr = {homeBBT, notificationsBBT, profileBBT};
    private String[] bbtArrStr = {"homeBBT", "notificationsBBT", "profileBBT"};
    String lastFragmentStr;

    SharedPreferences SharedPreferences;
    SharedPreferences.Editor Editor;
    RelativeLayout cartRL;

    private static final int UPDATE_CART_FROM_MAIN_SCREEN = 100;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale(MainActivity.this);
        setContentView(R.layout.activity_main);

        statusBarColor();
        inti();
        //cleanCart(getApplicationContext(),SharedPreferences,Editor);
        changeAppNameFontType();
        changeGeneralFontType();
        BottomBarMenu();
        //writeOnDataBase();
        //readFromDataBase();
        moveBetweenFragment();
        actionListenerToCart();

    }

    private void actionListenerToCart() {
            cartRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Cart.class);
                    startActivityForResult(intent , UPDATE_CART_FROM_MAIN_SCREEN);
                    overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
                }
            });
    }

    public static void setLocale(Activity context) {
        Locale locale;
        //Log.e("Lan",session.getLanguage());
        locale = new Locale(getLanguageFromSP(context));
        Configuration config = new Configuration(context.getResources().getConfiguration());
        Locale.setDefault(locale);
        config.setLocale(locale);

        context.getBaseContext().getResources().updateConfiguration(config,
                context.getBaseContext().getResources().getDisplayMetrics());
    }

    private void moveBetweenFragment() {
        fm.beginTransaction().add(R.id.main_container, fragmentProfile, "3").hide(fragmentProfile).commit();
        fm.beginTransaction().add(R.id.main_container, fragmentNotification, "2").hide(fragmentNotification).commit();
        fm.beginTransaction().add(R.id.main_container,fragmentHome, "1").commit();
    }

    private void changeGeneralFontType() {
        searchEdt.setTypeface(Functions.changeFontGeneral(getApplicationContext()));
    }

    private void changeAppNameFontType() {
        appNameTV.setTypeface(Functions.changeFontGeneral(getApplicationContext()));
    }


    private void inti() {
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        appNameTV =(TextView) findViewById(R.id.app_name_tv);
        searchEdt = (EditText) findViewById(R.id.searchEdt);
        cartRL = (RelativeLayout) findViewById(R.id.main_screen_cart_rl);
    }

    private void BottomBarMenu() {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switchFragment(tabId);
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_profile)
                {
                    //moveToAddItem();
                }
            }
        });

        intiBBT();

        int num =6;
        if(num >5)
        notificationsBBT.setBadgeCount(num);
    }

    private void intiBBT() {
        homeBBT = bottomBar.getTabWithId(R.id.tab_home);
        notificationsBBT = bottomBar.getTabWithId(R.id.tab_notifications);
        profileBBT = bottomBar.getTabWithId(R.id.tab_profile);
    }

    private boolean switchFragment(int tabId) {
        switch (tabId) {
            case R.id.tab_home:
                fm.beginTransaction().hide(active).show(fragmentHome).commit();
                active = fragmentHome;
                lastFragmentStr= "fragmentHome";
                return true;

            case R.id.tab_notifications:
                fm.beginTransaction().hide(active).show(fragmentNotification).commit();
                active = fragmentNotification;
                lastFragmentStr= "fragmentNotification";
                return true;

            case R.id.tab_profile:
                fm.beginTransaction().hide(active).show(fragmentProfile).commit();
                active = fragmentProfile;
                lastFragmentStr= "fragmentProfile";
                return true;
        }
        return false;
    }

    private void resetOtherBBTToDefault(String bbtStr,BottomBarTab bbt) {
        for (int i=0;i<bbtArrStr.length;i++)
        {
            if (!bbtArrStr[i].equals(bbtStr))
            {
                Log.i("TAGEQ",bbtArrStr[i]);
                //bbt.performClick(false);
                //bbtArr[i].isSelected();
                bbtArr[i].callOnClick();
            }
        }
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    @Override
    public void PassItemObject(Deal deal) {
        updateNumberOfItemInCartAndInsertToDataBase(getApplicationContext(),SharedPreferences,Editor,deal,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_CART_FROM_MAIN_SCREEN && resultCode == RESULT_OK && data != null) {

        }
    }
}
