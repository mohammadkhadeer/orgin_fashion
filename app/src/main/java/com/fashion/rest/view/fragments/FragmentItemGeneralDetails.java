package com.fashion.rest.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;

public class FragmentItemGeneralDetails extends Fragment {
    View view;
    TextView title,des,offer,head1,text11,text12,text13,head2,text21,text22,text23,
            text31,text32,text33,text41,text42,text43,text511,text512,text521,text522;

    public FragmentItemGeneralDetails(){}


    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
//            categoryStr = getArguments().getString("category");
        }
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_item_general_details, container, false);
        inti();
        changeFont();
        return view;
    }

    private void changeFont() {
        //title.setTypeface(Functions.changeFontGeneral(getActivity()));
        //des.setTypeface(Functions.changeFontGeneral(getActivity()));
        //offer.setTypeface(Functions.changeFontGeneral(getActivity()));
        head1.setTypeface(Functions.changeFontGeneral(getActivity()));
        text11.setTypeface(Functions.changeFontGeneral(getActivity()));
        text12.setTypeface(Functions.changeFontGeneral(getActivity()));
        text13.setTypeface(Functions.changeFontGeneral(getActivity()));
        head2.setTypeface(Functions.changeFontGeneral(getActivity()));
        text21.setTypeface(Functions.changeFontGeneral(getActivity()));
        text22.setTypeface(Functions.changeFontGeneral(getActivity()));
        text23.setTypeface(Functions.changeFontGeneral(getActivity()));

        text31.setTypeface(Functions.changeFontGeneral(getActivity()));
        text32.setTypeface(Functions.changeFontGeneral(getActivity()));
        text33.setTypeface(Functions.changeFontGeneral(getActivity()));
        text41.setTypeface(Functions.changeFontGeneral(getActivity()));
        text42.setTypeface(Functions.changeFontGeneral(getActivity()));
        text43.setTypeface(Functions.changeFontGeneral(getActivity()));
        text511.setTypeface(Functions.changeFontGeneral(getActivity()));
        text512.setTypeface(Functions.changeFontGeneral(getActivity()));
        text521.setTypeface(Functions.changeFontGeneral(getActivity()));
        text522.setTypeface(Functions.changeFontGeneral(getActivity()));
    }

    private void inti() {
        title = (TextView) view.findViewById(R.id.title);
        des = (TextView) view.findViewById(R.id.des);
        offer = (TextView) view.findViewById(R.id.textOffer);
        head1 = (TextView) view.findViewById(R.id.header1);
        text11 = (TextView) view.findViewById(R.id.text11);
        text12 = (TextView) view.findViewById(R.id.text12);
        text13 = (TextView) view.findViewById(R.id.text13);
        head2 = (TextView) view.findViewById(R.id.header1);
        text21 = (TextView) view.findViewById(R.id.text21);
        text22 = (TextView) view.findViewById(R.id.text22);
        text23 = (TextView) view.findViewById(R.id.text23);
        text31 = (TextView) view.findViewById(R.id.text31);
        text32 = (TextView) view.findViewById(R.id.text32);
        text33 = (TextView) view.findViewById(R.id.text33);
        text41 = (TextView) view.findViewById(R.id.text41);
        text42 = (TextView) view.findViewById(R.id.text42);
        text43 = (TextView) view.findViewById(R.id.text43);
        text511 = (TextView) view.findViewById(R.id.text511);
        text512 = (TextView) view.findViewById(R.id.text512);
        text521 = (TextView) view.findViewById(R.id.text521);
        text522 = (TextView) view.findViewById(R.id.text522);
    }
}
