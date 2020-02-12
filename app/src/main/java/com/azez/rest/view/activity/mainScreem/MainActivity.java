package com.azez.rest.view.activity.mainScreem;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import com.azez.rest.R;
import com.azez.rest.functions.Functions;
import com.azez.rest.model.Offer;
import com.azez.rest.view.activity.addItem.AddItem;
import com.azez.rest.view.fragmentHomeMainScreen.FragmentHomeScreen;
import com.azez.rest.view.fragmentHomeMainScreen.FragmentNotification;
import com.azez.rest.view.fragmentHomeMainScreen.FragmentProfile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.azez.rest.firebase.FireBaseLinkes.getOffers;

public class MainActivity extends AppCompatActivity {
    private TextView appNameTV;
    DatabaseReference mDatabase;
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


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusBarColor();
        inti();
        changeAppNameFontType();
        changeGeneralFontType();
        BottomBarMenu();
        //writeOnDataBase();
        readFromDataBase();
        moveBetweenFragment();

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
        appNameTV.setTypeface(Functions.changeFontAppName(getApplicationContext()));
    }

    private void readFromDataBase() {
        mList = getOffers(mList);
    }


    List<Offer> mList = new ArrayList<>();


    private void writeOnDataBase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("menu").child("category").child("offers").child("mail2");
        String imagePath = "https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/meal_image%2Fmandy.jpg?alt=media&token=5607fdb4-ad53-4089-bfea-e0592d9099e1";

        String arDesStr = "المنسف الاردني مع الجميد الكركي الاصلي";
        String enDesStr = "Jordanian mansf with wight famed";
        String arNameStr = "المنسف الاردني";
        String enNameStr = "Jordanian mansf";
        String timeStampStr = "4534545453";
        int newPriceInt = 120;

        Offer offer = new Offer(imagePath,"category","test"
                ,1,newPriceInt,timeStampStr,110
                ,arNameStr,enNameStr,arDesStr,enDesStr,"subCategory"
                                ,"petra","10:00 AM"
                ,"12:00 PM","Doubai"
                ,"Barsha1","lattude,lengtude","test");

        myRef.setValue(offer);
    }

    private void inti() {
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        appNameTV =(TextView) findViewById(R.id.app_name_tv);
        searchEdt = (EditText) findViewById(R.id.searchEdt);
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
                    moveToAddItem();
                }
            }
        });

        intiBBT();

        int num =2;
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

    private void checkWhatIsLastFragmentAndKeepItOn() {
        Log.i("TAG",lastFragmentStr);
        switch (lastFragmentStr) {
            case "fragmentHome":
                fm.beginTransaction().hide(active).show(fragmentHome).commit();
                active = fragmentHome;
                keepItPressed(homeBBT);
                break;

            case "fragmentNotification":
                fm.beginTransaction().hide(active).show(fragmentNotification).commit();
                active = fragmentNotification;
                keepItPressed(notificationsBBT);
                break;

            case "fragmentProfile":
                fm.beginTransaction().hide(active).show(fragmentProfile).commit();
                active = fragmentProfile;
                keepItPressed(profileBBT);
                break;

        }
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

    private void keepItPressed(BottomBarTab bbt) {
        //bbt.performClick();
         bbt.callOnClick();
         bbt.removeBadge();

    }

    private void moveToAddItem() {
        Intent intent = new Intent(MainActivity.this, AddItem.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up, R.anim.no_animation);
    }

    private void statusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorRed));
        }
    }
}
