package com.fashion.rest.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.squareup.picasso.Picasso;

public class FragmentItemGeneralDetails extends Fragment {
    View view;
    TextView title,des,offer;
    String cat_type,cat,offer_link;
    RelativeLayout relativeLayout;
    ImageView offer_image;

    public FragmentItemGeneralDetails(){}


    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            cat = getArguments().getString("cat");
            cat_type = getArguments().getString("cat_type");
            offer_link = getArguments().getString("offer_link");
        }
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_item_general_details, container, false);
        inti();
        changeFont();
        showOffer();
        return view;
    }

    private void showOffer() {
        if (cat_type.equals("offers"))
        {
            relativeLayout.setVisibility(View.VISIBLE);
            fillOfferImage();
        }else {
            relativeLayout.setVisibility(View.GONE);
        }
    }

    private void fillOfferImage() {
        Picasso.get()
                .load(offer_link)
                .fit()
                .centerCrop()
                .into(offer_image);
    }

    private void changeFont() {
        title.setTypeface(Functions.changeFontGeneral(getActivity()));
        des.setTypeface(Functions.changeFontGeneral(getActivity()));
        offer.setTypeface(Functions.changeFontGeneral(getActivity()));
    }

    private void inti() {
        title = (TextView) view.findViewById(R.id.title);
        des = (TextView) view.findViewById(R.id.des);
        offer = (TextView) view.findViewById(R.id.textOffer);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.offer_cont);
        offer_image = (ImageView) view.findViewById(R.id.offer_image);

    }
}
