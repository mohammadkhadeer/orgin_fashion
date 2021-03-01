package com.fashion.rest.view.fragments.fragmentItemDetails;

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
import com.fashion.rest.model.ItemTest;
import com.squareup.picasso.Picasso;

import static com.fashion.rest.functions.Functions.actionListenerToFav;
import static com.fashion.rest.functions.Functions.calculatePercentage;
import static com.fashion.rest.functions.Functions.checkFavOrNot;
import static com.fashion.rest.functions.Functions.getTextEngOrLocal;

public class FragmentItemGeneralDetails extends Fragment {
    View view;
    TextView title,des,offer;
    String cat_type,offer_link;
    RelativeLayout relativeLayout,fav_general_details,share_general_details,repo_general_details;
    ImageView offer_image,fav_or_not_general;

    public FragmentItemGeneralDetails(){}
    ItemTest itemTest;

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            cat_type = getArguments().getString("cat_type");
            offer_link = getArguments().getString("offer_link");
            itemTest = (ItemTest) getArguments().getParcelable("item_object");
        }
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_item_general_details, container, false);
        inti();
        fillTitleAndDesAndOfferPercentage();
        //static method to fav image and fac_action listener
        checkFavOrNot(itemTest.getId(),getActivity(),fav_or_not_general);
        actionListenerToFav(itemTest.getId(),itemTest.getSub_cat().getId(),itemTest.getSub_cat().getCategory_id()
                ,getActivity(),fav_or_not_general,fav_general_details);

        changeFont();
        showOffer();
        return view;
    }

    private void fillTitleAndDesAndOfferPercentage() {
        title.setText(getTextEngOrLocal(getActivity(),itemTest.getName(),itemTest.getName_local()));
        des.setText(getTextEngOrLocal(getActivity(),itemTest.getDescription(),itemTest.getDescription_local()));
        offer.setText(calculatePercentage(itemTest.getPrice(),itemTest.getDiscountPrice())+" "+getActivity().getResources().getString(R.string.offer_4));
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
        fav_or_not_general = (ImageView) view.findViewById(R.id.fav_or_not_general);
        fav_general_details = (RelativeLayout) view.findViewById(R.id.fav_general_details);
        share_general_details = (RelativeLayout) view.findViewById(R.id.share_general_details);
        repo_general_details = (RelativeLayout) view.findViewById(R.id.repo_general_details);

    }
}
