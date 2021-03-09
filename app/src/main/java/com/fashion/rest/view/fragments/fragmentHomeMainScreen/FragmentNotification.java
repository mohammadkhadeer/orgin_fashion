package com.fashion.rest.view.fragments.fragmentHomeMainScreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fashion.rest.R;
import com.fashion.rest.model.Notification;
import com.fashion.rest.model.NotificationModel;
import com.fashion.rest.view.Adapters.AdapterNotification;
import java.util.ArrayList;

import static com.fashion.rest.functions.FillItem.fillNotifications;
import static com.fashion.rest.functions.Functions.fillNotificationsArrayL;

public class FragmentNotification extends Fragment {
    View view;
    RecyclerView recyclerView;
    AdapterNotification adapterNotification;
    public ArrayList<NotificationModel> notificationCompsArrayL = new ArrayList<>();

    private BroadcastReceiver mMyBroadcastReceiver;

    public FragmentNotification(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notification, container, false);
        init();
        createRV();
        return view;
    }

    private void createRV() {
        notificationCompsArrayL = new ArrayList<>();
        notificationCompsArrayL =fillNotifications(getActivity());
        recyclerView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        adapterNotification = new AdapterNotification(getActivity(), notificationCompsArrayL);
        recyclerView.setAdapter(adapterNotification);
    }

    private void init() {
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_notification_rv);
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        mMyBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                // Here you can refresh your listview or other UI
                Toast.makeText(getActivity(), "Receiver", Toast.LENGTH_SHORT).show();
            }
        };
        try {

            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMyBroadcastReceiver,new IntentFilter("your_action"));

        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }}

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMyBroadcastReceiver);
    }
}
