package com.cars.halamotor.view.mainScreem;

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
import android.widget.Toast;

import com.cars.halamotor.R;
import com.cars.halamotor.functions.Functions;
import com.cars.halamotor.view.addItem.AddItem;
import com.cars.halamotor.view.fragmentHomeMainScreen.FragmentHomeScreen;
import com.cars.halamotor.view.fragmentHomeMainScreen.FragmentMessage;
import com.cars.halamotor.view.fragmentHomeMainScreen.FragmentNotification;
import com.cars.halamotor.view.fragmentHomeMainScreen.FragmentProfile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    private TextView appNameTV;
    DatabaseReference mDatabase;
    BottomBar bottomBar;
    EditText searchEdt;

    final Fragment fragmentHome = new FragmentHomeScreen();
    final Fragment fragmentMessage = new FragmentMessage();
    final Fragment fragmentNotification = new FragmentNotification();
    final Fragment fragmentProfile = new FragmentProfile();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentHome;
    BottomBarTab homeBBT,messagesBBT, notificationsBBT, addItemBBT,profileBBT;
    private BottomBarTab[] bbtArr = {homeBBT, messagesBBT, notificationsBBT, addItemBBT, profileBBT};
    private String[] bbtArrStr = {"homeBBT", "messagesBBT", "notificationsBBT", "addItemBBT", "profileBBT"};
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
        writeOnDataBase();
        readFromDataBase();
        moveBetweenFragment();

    }

    private void moveBetweenFragment() {
        fm.beginTransaction().add(R.id.main_container, fragmentProfile, "4").hide(fragmentProfile).commit();
        fm.beginTransaction().add(R.id.main_container, fragmentNotification, "3").hide(fragmentNotification).commit();
        fm.beginTransaction().add(R.id.main_container, fragmentMessage, "2").hide(fragmentMessage).commit();
        fm.beginTransaction().add(R.id.main_container,fragmentHome, "1").commit();
    }

    private void changeGeneralFontType() {
        searchEdt.setTypeface(Functions.changeFontGeneral(getApplicationContext()));
    }

    private void changeAppNameFontType() {
        appNameTV.setTypeface(Functions.changeFontAppName(getApplicationContext()));
    }

    private void readFromDataBase() {
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //ItemDetails user = dataSnapshot.getValue(ItemDetails.class);
                Log.i("TAG",dataSnapshot.toString());
                ///Applications/App School for Xcode and  iOS 10 Development Free.app/Contents/MacOS
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}

        });
    }

    private void writeOnDataBase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
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
                if (tabId == R.id.tab_adv)
                {
                    moveToAddItem();
                }
                //Toast.makeText(getApplicationContext(), TabMessage.get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });

        intiBBT();

        messagesBBT.setBadgeCount(12);
        int num =2;
        if(num >5)
        notificationsBBT.setBadgeCount(num);
    }

    private void intiBBT() {
        homeBBT = bottomBar.getTabWithId(R.id.tab_home);
        messagesBBT = bottomBar.getTabWithId(R.id.tab_messages);
        notificationsBBT = bottomBar.getTabWithId(R.id.tab_notifications);
        addItemBBT = bottomBar.getTabWithId(R.id.tab_adv);
        profileBBT = bottomBar.getTabWithId(R.id.tab_profile);
    }

    private boolean switchFragment(int tabId) {
        switch (tabId) {
            case R.id.tab_home:
                fm.beginTransaction().hide(active).show(fragmentHome).commit();
                active = fragmentHome;
                lastFragmentStr= "fragmentHome";
                return true;

            case R.id.tab_messages:
                fm.beginTransaction().hide(active).show(fragmentMessage).commit();
                active = fragmentMessage;
                lastFragmentStr= "fragmentMessage";
                return true;

            case R.id.tab_adv:
                checkWhatIsLastFragmentAndKeepItOn();
                moveToAddItem();
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
            case "fragmentMessage":
                fm.beginTransaction().hide(active).show(fragmentMessage).commit();
                active = fragmentMessage;
                keepItPressed(messagesBBT);
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
