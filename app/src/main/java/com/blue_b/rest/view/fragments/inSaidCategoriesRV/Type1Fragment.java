package com.blue_b.rest.view.fragments.inSaidCategoriesRV;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.blue_b.rest.R;

public class Type1Fragment extends Fragment {
    View view;

    public Type1Fragment(){}

    String categoryType="Type 11111 1111 1111 1111 Fragment";
    TextView textView;

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

        view= inflater.inflate(R.layout.type1_fragment, container, false);
        inti();
        textView.setText("TYPE 1111111111");
        Log.i("CategoryType From Fragment Fragment Fragment",categoryType);
        return view;
    }

    private void inti() {
        textView = (TextView) view.findViewById(R.id.type1_tv);
    }

}
