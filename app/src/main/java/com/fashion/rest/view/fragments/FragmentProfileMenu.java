package com.fashion.rest.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.view.activity.FavoriteItems;
import com.fashion.rest.view.activity.Setting;

public class FragmentProfileMenu extends Fragment {

    RelativeLayout recentVRL,recentSRL,favouriteRL
            ,helpRL,contactRL,aboutRL
            ,settingRL,instgramRL;
    TextView recentVTV,recentSTV,favouriteTV
            ,helpTV,contactTV,aboutTV
            ,settingTV;

    View view;

    public FragmentProfileMenu(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile_menu, container, false);

        inti();
//        actionListener();
        changeFont();
        actionListenerToInsta();
        actionListenerToSetting();
        actionListenerToFavoriteItems();

        return view;
    }

    private void actionListenerToFavoriteItems() {
        favouriteRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FavoriteItems.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
            }
        });
    }

    private void actionListenerToSetting() {
        settingRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("from","setting");

                Intent intent = new Intent(getActivity(), Setting.class);
//                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_up, R.anim.no_animation);
            }
        });
    }

    private void actionListenerToInsta() {
        instgramRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInsta();
            }
        });
    }

    private void openInsta() {
        try {
            //abukhadeer92
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://instagram.com/_u/" + "AlsartawiJewelry"));
            intent.setPackage("com.instagram.android");
            startActivity(intent);
        }
        catch (android.content.ActivityNotFoundException anfe)
        {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/" + "AlsartawiJewelry")));
        }
    }

    private void changeFont() {
        recentVTV.setTypeface(Functions.changeFontGeneral(getActivity()));
        recentSTV.setTypeface(Functions.changeFontGeneral(getActivity()));
        favouriteTV.setTypeface(Functions.changeFontGeneral(getActivity()));
        helpTV.setTypeface(Functions.changeFontGeneral(getActivity()));
        contactTV.setTypeface(Functions.changeFontGeneral(getActivity()));
        aboutTV.setTypeface(Functions.changeFontGeneral(getActivity()));
        settingTV.setTypeface(Functions.changeFontGeneral(getActivity()));
    }

//    private void actionListener() {
//        actionListenerToResarchAds();
//        actionListenerToFavourite();
//        actionListenerRecentViewedAds();
////        actionListenerToContactUs();
////        actionListenerAboutTheApp();
//    }

//    private void actionListenerAboutTheApp() {
//        aboutRL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                moveToAboutTheApp();
//            }
//        });
//    }

//    private void moveToAboutTheApp() {
//        Intent intent = new Intent(getActivity(), AboutUs.class);
//        startActivity(intent);
//        getActivity().overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
//    }

//    private void actionListenerToContactUs() {
//        contactRL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                moveToContactUsActivity();
//            }
//        });
//    }

//    private void moveToContactUsActivity() {
//        Intent intent = new Intent(getActivity(), ContactUs.class);
//        startActivity(intent);
//        getActivity().overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
//    }


//    private void actionListenerToResarchAds() {
//        recentSRL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                moveToFCSActivity("search");
//            }
//        });
//    }
//
//    private void actionListenerRecentViewedAds() {
//        recentVRL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                moveToFCSActivity("seen");
//            }
//        });
//    }
//
//    private void actionListenerToFavourite() {
//        favouriteRL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                moveToFCSActivity("favorite");
//            }
//        });
//    }

//    private void moveToFCSActivity(String fcsTypeStr) {
//        Bundle bundle = new Bundle();
//        bundle.putString("fcsTypeStr",fcsTypeStr);
//
//        Intent intent = new Intent(getActivity(), ShowFCS.class);
//        intent.putExtras(bundle);
//        startActivity(intent);
//        getActivity().overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
//    }

    private void inti() {
        recentVRL = (RelativeLayout) view.findViewById(R.id.fragment_profile_recent_v_RL);
        recentSRL = (RelativeLayout) view.findViewById(R.id.fragment_profile_recent_RL);
        favouriteRL = (RelativeLayout) view.findViewById(R.id.fragment_profile_favourite_RL);
        helpRL = (RelativeLayout) view.findViewById(R.id.fragment_profile_help_RL);
        contactRL = (RelativeLayout) view.findViewById(R.id.fragment_profile_contact_us_RL);
        aboutRL = (RelativeLayout) view.findViewById(R.id.fragment_profile_about_app_RL);
        settingRL = (RelativeLayout) view.findViewById(R.id.fragment_profile_setting_RL);
        instgramRL = (RelativeLayout) view.findViewById(R.id.contact_us_activity_insta_rl);


        recentVTV = (TextView) view.findViewById(R.id.fragment_profile_recent_v_TV);
        recentSTV = (TextView) view.findViewById(R.id.fragment_profile_recent_search_TV);
        favouriteTV = (TextView) view.findViewById(R.id.fragment_profile_favourite_TV);

        helpTV = (TextView) view.findViewById(R.id.fragment_profile_help_TV);
        contactTV = (TextView) view.findViewById(R.id.fragment_profile_contact_us_TV);
        aboutTV = (TextView) view.findViewById(R.id.fragment_profile_about_app_TV);
        settingTV = (TextView) view.findViewById(R.id.fragment_profile_setting_TV);

    }

}
