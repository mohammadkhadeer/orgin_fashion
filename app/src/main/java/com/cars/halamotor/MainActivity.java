package com.cars.halamotor;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
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
    private TextView messageView;
    BottomBar bottomBar;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inti();
        BottomBarMenu();
        writeOnDataBase();
        readFromDataBase();


    }

    private void readFromDataBase() {
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //ItemDetails user = dataSnapshot.getValue(ItemDetails.class);
                Log.i("TAG",dataSnapshot.toString());
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
        messageView = (TextView) findViewById(R.id.messageView);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
    }

    private void BottomBarMenu() {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                messageView.setText(TabMessage.get(tabId, false));
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getApplicationContext(), TabMessage.get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });

        BottomBarTab messages = bottomBar.getTabWithId(R.id.tab_messages);
        BottomBarTab notifications = bottomBar.getTabWithId(R.id.tab_notifications);
        messages.setBadgeCount(13);
        notifications.setBadgeCount(1);
    }
}
