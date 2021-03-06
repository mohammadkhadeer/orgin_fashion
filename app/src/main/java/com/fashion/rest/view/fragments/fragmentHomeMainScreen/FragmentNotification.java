package com.fashion.rest.view.fragments.fragmentHomeMainScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fashion.rest.R;
import com.fashion.rest.model.Notification;
import com.fashion.rest.view.Adapters.AdapterNotification;
import java.util.ArrayList;
import static com.fashion.rest.functions.Functions.fillNotificationsArrayL;

public class FragmentNotification extends Fragment {
    View view;
    RecyclerView recyclerView;
    AdapterNotification adapterNotification;
    public ArrayList<Notification> notificationCompsArrayL = new ArrayList<>();

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
        notificationCompsArrayL = fillNotificationsArrayL(getActivity());
        recyclerView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        adapterNotification = new AdapterNotification(getActivity(), notificationCompsArrayL);
        recyclerView.setAdapter(adapterNotification);
    }

    private void init() {
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_notification_rv);
    }
}
