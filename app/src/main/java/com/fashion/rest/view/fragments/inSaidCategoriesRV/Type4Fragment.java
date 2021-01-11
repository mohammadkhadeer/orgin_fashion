package com.fashion.rest.view.fragments.inSaidCategoriesRV;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fashion.rest.R;

public class Type4Fragment extends Fragment {
    View view;

    public Type4Fragment(){}

    String categoryType="Type4  4444 4444 4444 Fragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.type4_fragment, container, false);
        Log.i("CategoryType From Fragment Fragment Fragment",categoryType);

        return view;
    }
}
