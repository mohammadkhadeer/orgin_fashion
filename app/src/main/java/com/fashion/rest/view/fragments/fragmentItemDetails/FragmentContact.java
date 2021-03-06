package com.fashion.rest.view.fragments.fragmentItemDetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.fashion.rest.R;
import com.fashion.rest.model.ItemTest;
import com.fashion.rest.permission.CheckPermission;
import static com.fashion.rest.functions.ItemDetailsFunctions.callAds;
import static com.fashion.rest.functions.ItemDetailsFunctions.openWhatsApp;

public class FragmentContact extends Fragment {

    public FragmentContact() {
    }

    View view;
    RelativeLayout sendMessage, call;
    private static final int PHONE = 102;
    ItemTest itemTest;

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
           // phoneNumber = getArguments().getString("phoneN");
            itemTest = (ItemTest) getArguments().getParcelable("item_object");
        }
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact, container, false);
        inti();
        actionListener();
        return view;
    }

    private void actionListener() {
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAdsHere();
            }
        });
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsApp(itemTest.getStore().getPhone_number(),getActivity());
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void callAdsHere() {
        if (CheckPermission.checkPermissionMethodToPhone(getActivity()) == true) {
            callAds(getActivity(),itemTest.getStore().getPhone_number());
        }else{
            //Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.we_cant),Toast.LENGTH_SHORT).show();
        }
    }

    private void inti() {
        sendMessage = (RelativeLayout) view.findViewById(R.id.contact_send_message);
        call = (RelativeLayout) view.findViewById(R.id.contact_call);
    }

}
