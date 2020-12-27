package com.fashion.rest.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.squareup.picasso.Picasso;


public class ProfileDetailsInfo extends Fragment {

    View view;
    RelativeLayout buildTrustRLL;
    CardView userInfoCV;
    ImageView userImageIV;
    TextView userNameTV,editProfileTV;

    public ProfileDetailsInfo(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_details_info, container, false);

        inti();
        changeFont();
        fillUserInfoFromFB();

        return view;
    }

    private void changeFont() {
        userNameTV.setTypeface(Functions.changeFontGeneral(getActivity()));
    }


    private void fillUserInfoFromFB() {
//        userNameTV.setText(userFaceBookInfo.getFirstNameStr());
//        followingTV.setText(String.valueOf(followingArrayList.size()));
        fillImageUser();
    }

    private void fillImageUser() {
        Picasso.get()
                .load(R.drawable.moha)
                .fit()
                .centerCrop()
                .into(userImageIV);
    }

    private void makeUserInfoOn() {
        userInfoCV.setVisibility(View.VISIBLE);
    }

    private void makeUserInfoOff() {
        userInfoCV.setVisibility(View.GONE);
    }


    private void inti() {
        userInfoCV = (CardView) view.findViewById(R.id.profile_details_info_details_CV);
        userImageIV = (ImageView) view.findViewById(R.id.profile_details_user_image_IV);
        userNameTV = (TextView) view.findViewById(R.id.profile_details_user_name_TV);
        editProfileTV = (TextView) view.findViewById(R.id.profile_details_info_edit_profile_TV);
    }
}