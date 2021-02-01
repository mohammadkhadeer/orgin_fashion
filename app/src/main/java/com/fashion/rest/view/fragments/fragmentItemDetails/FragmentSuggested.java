package com.fashion.rest.view.fragments.fragmentItemDetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Deal;
import com.fashion.rest.view.Adapters.AdapterSet;
import com.fashion.rest.view.activity.CategoryItem;

import java.util.ArrayList;

import static com.fashion.rest.functions.FillItem.fillAllItemDepCatArrayL;


public class FragmentSuggested extends Fragment{
    View view;
    String cat,cat_type;
    SLFullImageAndImagePng slFullImageAndImagePng = new SLFullImageAndImagePng();
    SLOffersCase slOffersCase = new SLOffersCase();

    public FragmentSuggested(){}

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            cat = getArguments().getString("cat");
            cat_type = getArguments().getString("cat_type");
        }
        super.onAttach(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_suggested, container, false);

        checkSuggestedListType();

        return view;
    }

    private void checkSuggestedListType() {
        if (cat_type.equals("png_image"))
        {
            handelSuggestedType();
        }

        if (cat_type.equals("offers"))
        {
            handelOffersType();
        }
        if (cat_type.equals("full_image"))
        {
            handelSuggestedType();
        }
    }

    private void handelOffersType() {
        Bundle bundle = new Bundle();
        bundle.putString("cat", cat);
        bundle.putString("cat_type", cat_type);

        slOffersCase.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_full_image_and_image_png, slOffersCase)
                .commit();
    }

    private void handelSuggestedType() {
        Bundle bundle = new Bundle();
        bundle.putString("cat", cat);
        bundle.putString("cat_type", cat_type);

        slFullImageAndImagePng.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_full_image_and_image_png, slFullImageAndImagePng)
                .commit();
    }

}
