package com.fashion.rest.view.fragments.HomeScreenFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Deal;
import com.fashion.rest.presnter.PassObject;
import com.fashion.rest.view.Adapters.AdapterSet;
import com.fashion.rest.view.activity.CategoryItem;
import java.util.ArrayList;

public class FragmentNecklaces extends Fragment implements AdapterSet.PassItem {
    View view;
    RecyclerView recyclerView;
    AdapterSet adapterSet;
    RecyclerView.LayoutManager layoutManager;
    public ArrayList<Deal> dealsArrayList = new ArrayList<>();
    TextView headerTV;
    RelativeLayout relativeLayout;

    public FragmentNecklaces(){}

    PassObject passObject;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PassObject) {
            passObject = (PassObject) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        passObject = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_necklaces, container, false);
        inti();
        changeFont();
        actionListenerToSeeAll();
        return view;
    }

    private void changeFont() {
        headerTV.setTypeface(Functions.changeFontCategory(getActivity()));
    }

    private void inti() {
        recyclerView = (RecyclerView) view.findViewById(R.id.set_RV);
        headerTV = (TextView) view.findViewById(R.id.header_TV);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.see_all_rl);
    }

    private void actionListenerToSeeAll() {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("category",getActivity().getResources().getString(R.string.necklaces_s));
                bundle.putString("from","see_all");

                Intent intent = new Intent(getActivity(), CategoryItem.class);
                intent.putExtras(bundle);
                ((Activity)getActivity()).startActivity(intent);
                ((Activity)getActivity()).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
            }
        });
    }

    @Override
    public void onClicked(Deal deal) {
        passObject.PassItemObject(deal);
    }
}
