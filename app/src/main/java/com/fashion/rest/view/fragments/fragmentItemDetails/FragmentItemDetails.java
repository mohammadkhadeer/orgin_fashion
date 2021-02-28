package com.fashion.rest.view.fragments.fragmentItemDetails;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fashion.rest.R;
import com.fashion.rest.model.ItemTest;

public class FragmentItemDetails extends Fragment {
    View view;
    FragmentItemGeneralDetails fragmentItemGeneralDetails = new FragmentItemGeneralDetails();
    FragmentSuggested fragmentSuggested = new FragmentSuggested();
    FragmentStoreInfo fragmentStoreInfo = new FragmentStoreInfo();
    FragmentLocation fragmentLocation = new FragmentLocation();

    public FragmentItemDetails(){}
    String cat_type;
    String storeNameStr, storeImage, website_link,from;
    ItemTest itemTest;

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            cat_type = getArguments().getString("cat_type");
            storeNameStr = getArguments().getString("storeNameStr");
            storeImage = getArguments().getString("storeImage");
            website_link = getArguments().getString("website_link");
            from = getArguments().getString("from");
            itemTest = (ItemTest) getArguments().getParcelable("item_object");
        }
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_item_details, container, false);
        fragmentGeneralDetails();
        handelFragmentLocation();
        fragmentSuggestedDetails();
        fragmentStoreDetails();
        return view;
    }

    private void handelFragmentLocation() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("item_object", itemTest);


        fragmentLocation.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_location, fragmentLocation)
                .commit();
    }

    private void fragmentStoreDetails() {
        Bundle bundle = new Bundle();
        bundle.putString("website_link", website_link);
        bundle.putParcelable("item_object", itemTest);

        fragmentStoreInfo.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_store_details, fragmentStoreInfo)
                .commit();
    }

    private void fragmentGeneralDetails() {
        String offer_link = "https://firebasestorage.googleapis.com/v0/b/restaurant-31ab3.appspot.com/o/55.jpeg?alt=media&token=76fda7fb-1b1a-44e0-af9d-fad2cd341761";
        Bundle bundle = new Bundle();
        bundle.putString("cat_type", cat_type);
        bundle.putString("offer_link", offer_link);
        bundle.putParcelable("item_object", itemTest);

        fragmentItemGeneralDetails.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_item_general_details, fragmentItemGeneralDetails)
                .commit();
    }

    private void fragmentSuggestedDetails() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("item_object", itemTest);
        bundle.putString("from", from);

        fragmentSuggested.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_suggested, fragmentSuggested)
                .commit();
    }
}
