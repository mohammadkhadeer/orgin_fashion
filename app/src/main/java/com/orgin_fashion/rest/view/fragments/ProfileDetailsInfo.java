package com.orgin_fashion.rest.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.orgin_fashion.rest.R;
import com.orgin_fashion.rest.functions.Functions;
import com.orgin_fashion.rest.view.activity.Login;
import com.squareup.picasso.Picasso;

import static com.orgin_fashion.rest.sharedPreferences.LoginInfo.getEmailFromSP;
import static com.orgin_fashion.rest.sharedPreferences.LoginInfo.getLoginOrNotFromSP;
import static com.orgin_fashion.rest.sharedPreferences.LoginInfo.getNameFromSP;
import static com.orgin_fashion.rest.sharedPreferences.LoginInfo.getPhotoFromSP;
import static com.orgin_fashion.rest.sharedPreferences.LoginInfo.getTypeFromSP;

public class ProfileDetailsInfo extends Fragment {

    View view;
    RelativeLayout profile_details_login_rl;
    CardView userInfoCV;
    ImageView userImageIV;

    CardView profile_details_info_login,profile_details_info_details_CV;
    ImageView image_view,login_type_iv;
    TextView profile_details_app_name_TV,login_tv,profile_details_user_name_TV,profile_details_user_email;
    String welcome_image = "https://firebasestorage.googleapis.com/v0/b/uae-kandora.appspot.com/o/arabes_logo.jpeg?alt=media&token=cc61497d-565a-4154-9f34-cae0a9c436a4";

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
        profile_details_user_name_TV.setTypeface(Functions.changeFontGeneral(getActivity()));
        login_tv.setTypeface(Functions.changeFontGeneral(getActivity()));
        profile_details_app_name_TV.setTypeface(Functions.changeFontGeneral(getActivity()));
    }


    private void fillUserInfoFromFB() {
//        userNameTV.setText(userFaceBookInfo.getFirstNameStr());
//        followingTV.setText(String.valueOf(followingArrayList.size()));
        if (getLoginOrNotFromSP(getActivity()).isEmpty())
        {
            profile_details_info_details_CV.setVisibility(View.GONE);
            profile_details_info_login.setVisibility(View.VISIBLE);
            fillPleaseLogin();
            actionListenerToProfileLogin();
        }else{
            profile_details_info_details_CV.setVisibility(View.VISIBLE);
            profile_details_info_login.setVisibility(View.GONE);
            fillUserInfo();
        }
    }

    private void actionListenerToProfileLogin() {
        profile_details_login_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login.class);
                intent.putExtra("from", "login");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
            }
        });
    }

    private void fillUserInfo() {
        Picasso.get()
                .load(getPhotoFromSP(getActivity()))
                .fit()
                .centerCrop()
                .into(userImageIV);

        profile_details_user_name_TV.setText(getNameFromSP(getActivity()));
        profile_details_user_email.setText(getEmailFromSP(getActivity()));
        if (getTypeFromSP(getActivity()).equals("google"))
        {
            Picasso.get()
                    .load(R.drawable.g)
                    .fit()
                    .centerCrop()
                    .into(login_type_iv);
        }else{
            Picasso.get()
                    .load(R.drawable.fb_logo)
                    .fit()
                    .centerCrop()
                    .into(login_type_iv);
        }
    }

    private void fillPleaseLogin() {
        String fashion = "https://firebasestorage.googleapis.com/v0/b/hala-motor-8ff46.appspot.com/o/1111.png?alt=media&token=ef9408df-a9a2-4890-8130-65ad843c0f9e";
        String bagdones = "https://firebasestorage.googleapis.com/v0/b/uae-kandora.appspot.com/o/b-logo-02.png?alt=media&token=7e0de5d1-bb05-42be-9687-8d204a8da81d";

        Picasso.get()
                .load(bagdones)
                .fit()
                .centerCrop()
                .into(image_view);
        profile_details_app_name_TV.setText(getActivity().getResources().getString(R.string.app_name));
    }

    private void makeUserInfoOn() {
        userInfoCV.setVisibility(View.VISIBLE);
    }

    private void makeUserInfoOff() {
        userInfoCV.setVisibility(View.GONE);
    }


    private void inti() {
        userInfoCV = (CardView) view.findViewById(R.id.profile_details_info_details_CV);
        profile_details_info_login = (CardView) view.findViewById(R.id.profile_details_info_login);
        profile_details_info_details_CV= (CardView) view.findViewById(R.id.profile_details_info_details_CV);

        image_view = (ImageView) view.findViewById(R.id.image_view);
        login_type_iv= (ImageView) view.findViewById(R.id.login_type_iv);
        userImageIV = (ImageView) view.findViewById(R.id.profile_details_user_image_IV);
        profile_details_app_name_TV= (TextView) view.findViewById(R.id.profile_details_app_name_TV);
        login_tv= (TextView) view.findViewById(R.id.login_tv);
        profile_details_user_name_TV= (TextView) view.findViewById(R.id.profile_details_user_name_TV);
        profile_details_user_email= (TextView) view.findViewById(R.id.profile_details_user_email);
        profile_details_login_rl = (RelativeLayout) view.findViewById(R.id.profile_details_login_rl);
    }
}