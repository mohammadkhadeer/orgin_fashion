package com.fashion.rest.view.fragments.fragmentHomeMainScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fashion.rest.R;
import com.fashion.rest.view.fragments.FragmentProfileMenu;
import com.fashion.rest.view.fragments.ProfileDetailsInfo;

public class FragmentProfile extends Fragment {

    public FragmentProfile(){}

    ProfileDetailsInfo profileDetailsInfo = new ProfileDetailsInfo();
    FragmentProfileMenu fragmentProfileMenu = new FragmentProfileMenu();

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        FragmentProfileDetails();
        FragmentProfileMenu();

        return view;
    }

    private void FragmentProfileDetails() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_profile_details_info, profileDetailsInfo)
                .commit();
    }

    private void FragmentProfileMenu() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_profile_menu, fragmentProfileMenu)
                .commit();
    }
}
