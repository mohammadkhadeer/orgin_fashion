package com.fashion.rest.view.fragments.allOffersFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fashion.rest.R;
import com.fashion.rest.view.fragments.FragmentProfileMenu;
import com.fashion.rest.view.fragments.ProfileDetailsInfo;

public class FragmentAllOffer extends Fragment {

    public FragmentAllOffer(){}

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);


        return view;
    }

}
