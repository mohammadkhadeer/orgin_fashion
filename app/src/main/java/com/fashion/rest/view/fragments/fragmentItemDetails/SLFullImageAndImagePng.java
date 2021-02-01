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
import com.fashion.rest.view.Adapters.AdapterType2;
import com.fashion.rest.view.Adapters.AdapterType4;
import com.fashion.rest.view.activity.CategoryItem;

import java.util.ArrayList;

import static com.fashion.rest.functions.FillItem.fillAllItemDepCatArrayL;
import static com.fashion.rest.functions.Functions.fillSetArrayL;
import static com.fashion.rest.functions.Functions.fillSetArrayL2;


public class SLFullImageAndImagePng extends Fragment {
    View view;
    RecyclerView recyclerView;
    TextView headerTV,full_image_and_see_all_TV;
    RelativeLayout relativeLayout;
    String cat,cat_type;

    public SLFullImageAndImagePng(){}

    AdapterType2 adapterType2;
    RecyclerView.LayoutManager layoutManager;
    public ArrayList<Deal> dealsArrayList = new ArrayList<>();

    AdapterType4 adapterType4;
    RecyclerView.LayoutManager layoutManagerFull;
    public ArrayList<Deal> dealsFullArrayList = new ArrayList<>();

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
        view= inflater.inflate(R.layout.fragment_full_image_and_image_png, container, false);
        inti();
        changeFont();

        if (cat_type.equals("png_image"))
            createPngCase();
        if (cat_type.equals("full_image"))
            createFullImageCase();

        actionListenerToSeeAll();
        return view;
    }

    private void createFullImageCase() {
        dealsFullArrayList = fillSetArrayL2(dealsArrayList,getActivity());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        layoutManagerFull = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManagerFull);
        adapterType4 =new AdapterType4(getActivity()
                ,dealsFullArrayList,"category");
        recyclerView.setAdapter(adapterType4);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createPngCase() {
        dealsArrayList = fillSetArrayL(dealsArrayList,getActivity());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        adapterType2 =new AdapterType2(getActivity()
                ,dealsArrayList,"category");
        recyclerView.setAdapter(adapterType2);
    }

    private void actionListenerToSeeAll() {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                //bundle.putString("category",getActivity().getResources().getString(R.string.set));
                bundle.putString("category",getActivity().getResources().getString(R.string.set));
                bundle.putString("from","see_all");

                Intent intent = new Intent(getActivity(), CategoryItem.class);
                intent.putExtras(bundle);
                ((Activity)getActivity()).startActivity(intent);
                ((Activity)getActivity()).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
            }
        });
    }

    private void changeFont() {
        headerTV.setTypeface(Functions.changeFontGeneral(getActivity()));
        full_image_and_see_all_TV.setTypeface(Functions.changeFontGeneral(getActivity()));
    }

    private void inti() {
        recyclerView = (RecyclerView) view.findViewById(R.id.suggested_RV);
        headerTV = (TextView) view.findViewById(R.id.suggested_TV);
        full_image_and_see_all_TV = (TextView) view.findViewById(R.id.full_image_and_see_all_TV);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.see_all_suggested_rl);
    }

}
