package com.fashion.rest.view.fragments.inSaidCategoriesRV;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fashion.rest.R;


public class MainFragment extends Fragment {
    View view;

    public MainFragment(){}

    String categoryType = "empty";

    Type1Fragment type1Fragment = new Type1Fragment();
    Type2Fragment type2Fragment = new Type2Fragment();
    Type3Fragment type3Fragment = new Type3Fragment();
    Type4Fragment type4Fragment = new Type4Fragment();

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            categoryType = getArguments().getString("type");

        }
        super.onAttach(context);
//        Log.i("CategoryType",categoryType);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.main_fragment, container, false);

        inti();
        handelWhitchCaseMustShow();

        return view;
    }

    private void handelWhitchCaseMustShow() {

    }

    private void inti() {

    }

}
