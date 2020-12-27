package com.fashion.rest.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fashion.rest.R;

public class FragmentItemDetails extends Fragment {
    View view;
    FragmentItemGeneralDetails fragmentItemGeneralDetails = new FragmentItemGeneralDetails();
    FragmentSuggested fragmentSuggested = new FragmentSuggested();
    public FragmentItemDetails(){}
    String categoryStr;

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            categoryStr = getArguments().getString("cat");
        }
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_item_details, container, false);
        fragmentGeneralDetails();
        fragmentSuggestedDetails();

        return view;
    }

    private void fragmentGeneralDetails() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_item_general_details, fragmentItemGeneralDetails)
                .commit();
    }

    private void fragmentSuggestedDetails() {
        Bundle bundle = new Bundle();
        bundle.putString("cat", categoryStr);

        fragmentSuggested.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_suggested, fragmentSuggested)
                .commit();
    }
}
