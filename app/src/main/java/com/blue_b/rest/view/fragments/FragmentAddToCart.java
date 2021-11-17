package com.blue_b.rest.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.blue_b.rest.R;

public class FragmentAddToCart extends Fragment {

    public FragmentAddToCart() {
    }

    View view;
    RelativeLayout sendMessage, call;
    String phoneNumber,itemID,category;

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
//            phoneNumber = getArguments().getString("phoneN");
//            itemID = getArguments().getString("itemID");
//            category = getArguments().getString("category");

        }
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_to_cart, container, false);
        inti();

        return view;
    }


    private void inti() {
        call = (RelativeLayout) view.findViewById(R.id.contact_call);
    }

}
