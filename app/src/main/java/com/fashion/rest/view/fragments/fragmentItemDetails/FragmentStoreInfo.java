package com.fashion.rest.view.fragments.fragmentItemDetails;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.fashion.rest.model.Store;
import com.fashion.rest.view.activity.StoreActivity;
import com.squareup.picasso.Picasso;

import static com.fashion.rest.apiURL.API.apiURLBase;
import static com.fashion.rest.functions.Functions.getTextEngOrLocal;

public class FragmentStoreInfo extends Fragment {

    public FragmentStoreInfo() {
    }

    String storeNameStr, storeImage, website_link;
    View view;
    TextView userNameTV;
    ImageView userImageIV;
    RelativeLayout fragment_store_info;
    LinearLayout store_ll;
    ItemTest itemTest;
    Store store = new Store();

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            itemTest = (ItemTest) getArguments().getParcelable("item_object");
//            website_link = getArguments().getString("website_link");
            website_link = itemTest.getStore().getWebsite_link();
            storeNameStr = getTextEngOrLocal(getActivity(),itemTest.getStore().getName(),itemTest.getStore().getName_local());
            storeImage = apiURLBase()+itemTest.getStore().getFlag().getUrl();
        }
        super.onAttach(context);
        //set userName in followID just as init value well need it to insert in
        //fireBase as object after added well updated
    }

    private void inti() {
        userImageIV = (ImageView) view.findViewById(R.id.fragment_follow_image_IV);
        userNameTV = (TextView) view.findViewById(R.id.fragment_follow_user_name_TV);
        fragment_store_info = (RelativeLayout) view.findViewById(R.id.fragment_store_info);
        store_ll = (LinearLayout) view.findViewById(R.id.store_ll);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_store_info, container, false);
        inti();
        if (website_link.equals(" "))
        {
            fragment_store_info.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeFont();

        fillUserImageAndUserName();
        actionListenerToVisitWebsite();
        actionListenerToStore();
    }

    private void actionListenerToStore() {
        store_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                store = itemTest.getStore();
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                intent.putExtra("item_object", itemTest);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
            }
        });
    }

    private void actionListenerToVisitWebsite() {
        fragment_store_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(website_link));
                startActivity(intent);
            }
        });
    }

    private void fillUserImageAndUserName() {
        Picasso.get()
                .load(storeImage)
                .fit()
                .centerCrop()
                .into(userImageIV);

        userNameTV.setText(storeNameStr);
    }

    private void changeFont() {
        userNameTV.setTypeface(Functions.changeFontGeneral(getActivity()));
    }

}
