package com.blue_b.rest.view.fragments.inSaidCategoriesRV;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blue_b.rest.R;

public class Type2Fragment extends Fragment {
    View view;

    public Type2Fragment(){}

    String categoryType="Type 2222 2222 2222 2222 Fragment";

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            //categoryType = getArguments().getString("type");

        }
        super.onAttach(context);
        //Log.i("CategoryType",categoryType);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.type2_fragment, container, false);

        Log.i("CategoryType From Fragment Fragment Fragment",categoryType);

        return view;
    }

}
