package com.fashion.rest.view.fragments.fragmentItemDetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Deal;
import com.fashion.rest.view.Adapters.AdapterEndlessOffers;
import com.fashion.rest.view.Adapters.AdapterType2;
import com.fashion.rest.view.Adapters.AdapterType4;
import com.fashion.rest.view.activity.CategoryItem;
import com.fashion.rest.view.activity.ResultActivity;

import java.util.ArrayList;

import static com.fashion.rest.functions.FillItem.fillEndlessItemDepCatArrayL;
import static com.fashion.rest.functions.Functions.fillSetArrayL;
import static com.fashion.rest.functions.Functions.fillSetArrayL2;


public class SLOffersCase extends Fragment {
    View view;
    RecyclerView recyclerView;
    TextView headerTV,full_image_and_see_all_TV;
    RelativeLayout relativeLayout;
    String cat,cat_type;
    public ArrayList<Deal> suggestedItemsArrayListTest;
    public ArrayList<Deal> suggestedItemsArrayListDO;

    public SLOffersCase(){}

    public static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;

    LinearLayoutManager mLayoutManager;
    AdapterEndlessOffers adapterEndlessOffers ;

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
        suggestedItemsArrayListDO = new ArrayList<>();
        suggestedItemsArrayListTest = new ArrayList<>();

        inti();
        changeFont();

        createOffersCase();
        actionListenerToRV();

        actionListenerToSeeAll();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createOffersCase() {
        adapterEndlessOffers = new AdapterEndlessOffers(new ArrayList<Deal>(),getActivity(),"call");
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapterEndlessOffers);
        doApiCall();
    }

    private void actionListenerToRV() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isLoading) {

                    if (mLayoutManager != null && mLayoutManager.findLastCompletelyVisibleItemPosition() == suggestedItemsArrayListDO.size() - 1) {
                        //bottom of list!
                        Toast.makeText(getActivity(),"TAG !" +String.valueOf(currentPage)+ " Load more ...",Toast.LENGTH_SHORT).show();

                        new Handler().postDelayed(new Runnable() {

                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void run() {
                                Log.i("TAG BAG","currentPage: "+String.valueOf(currentPage));
                                doApiCall();

                            }
                        }, 2000);
                        currentPage ++;
                        isLoading = true;

                    }
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void doApiCall() {
        suggestedItemsArrayListTest = new ArrayList<>();
        suggestedItemsArrayListTest = fillEndlessItemDepCatArrayL(suggestedItemsArrayListTest,getActivity());
        suggestedItemsArrayListDO = fillEndlessItemDepCatArrayL(suggestedItemsArrayListDO,getActivity());
//        suggestedItemsArrayListTest.addAll(suggestedItemsArrayListDO);

        //fill here
        if (currentPage != PAGE_START) adapterEndlessOffers.removeLoading();
        adapterEndlessOffers.addItems(suggestedItemsArrayListTest);
        if (currentPage < totalPage) {
            adapterEndlessOffers.addLoading();
            isLoading = false;
        } else {
            isLastPage = true;
        }
    }

    private void actionListenerToSeeAll() {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                //bundle.putString("category",getActivity().getResources().getString(R.string.set));
//                bundle.putString("category",getActivity().getResources().getString(R.string.set));
//                bundle.putString("from","see_all");

                Intent intent = new Intent(getActivity(), ResultActivity.class);
                //intent.putExtras(bundle);
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
