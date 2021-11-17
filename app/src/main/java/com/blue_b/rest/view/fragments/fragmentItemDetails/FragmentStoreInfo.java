package com.blue_b.rest.view.fragments.fragmentItemDetails;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blue_b.rest.R;
import com.blue_b.rest.functions.Functions;
import com.blue_b.rest.model.ItemTest;
import com.blue_b.rest.model.Store;
import com.blue_b.rest.presnter.JsonPlaceHolderApi;
import com.blue_b.rest.view.Adapters.AdapterLoadingType2;
import com.blue_b.rest.view.Adapters.AdapterType2;
import com.blue_b.rest.view.activity.StoreActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.blue_b.rest.apiURL.API.apiURLBase;
import static com.blue_b.rest.functions.Functions.getDefultToFilterItemModel;
import static com.blue_b.rest.functions.Functions.getTextEngOrLocal;
import static com.blue_b.rest.functions.RetrofitFunctions.getStoreItems;

public class FragmentStoreInfo extends Fragment {

    public FragmentStoreInfo() {
    }

    String storeNameStr, storeImage, website_link;
    View view;
    TextView userNameTV,store_address_tv,store_info_more_item,store_info_see_more_tv;
    ImageView userImageIV;
    RelativeLayout fragment_store_info,store_info_see_more_rl;
    LinearLayout store_ll;
    ItemTest itemTest;
    Store store = new Store();
    RecyclerView lodaingRV,recyclerView;

    AdapterType2 adapterType2;
    AdapterLoadingType2 adapterLoadingType2;
    RecyclerView.LayoutManager layoutManager,layoutManagerLoading;
    ArrayList<ItemTest> dealsArrayList = new ArrayList<>();
    JsonPlaceHolderApi jsonPlaceHolderApi;
    Retrofit retrofit;

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            itemTest = (ItemTest) getArguments().getParcelable("item_object");
//            website_link = getArguments().getString("website_link");
            website_link = itemTest.getStore().getWebsite_link();
            storeNameStr = getTextEngOrLocal(getActivity(),itemTest.getStore().getName(),itemTest.getStore().getName_local());
            storeImage = apiURLBase()+itemTest.getStore().getFlag().getUrl();
        }
        super.onAttach(context);
        intiRetrofit();
        //set userName in followID just as init value well need it to insert in
        //fireBase as object after added well updated
    }

    private void inti() {
        store_info_see_more_tv= (TextView) view.findViewById(R.id.store_info_see_more_tv);
        store_info_more_item= (TextView) view.findViewById(R.id.store_info_more_item);
        lodaingRV= (RecyclerView) view.findViewById(R.id.store_info_lodaingRV);
        recyclerView= (RecyclerView) view.findViewById(R.id.store_info_type2_RV);
        store_address_tv= (TextView) view.findViewById(R.id.store_address_tv);
        userImageIV = (ImageView) view.findViewById(R.id.fragment_follow_image_IV);
        userNameTV = (TextView) view.findViewById(R.id.fragment_follow_user_name_TV);
        store_info_see_more_rl = (RelativeLayout) view.findViewById(R.id.store_info_see_more_rl);
        fragment_store_info = (RelativeLayout) view.findViewById(R.id.fragment_store_info);
        store_ll = (LinearLayout) view.findViewById(R.id.store_ll);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_store_info, container, false);
        inti();
        createLoadingRV();
        getItemsFromServer();
        if (website_link.equals(" "))
        {
            fragment_store_info.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeFont();

        fillUserImageAndUserName();
        actionListenerToVisitWebsite();
        actionListenerToStore();
        actionListenerToSeeAll();
    }

    private void actionListenerToSeeAll() {
        store_info_see_more_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                store = itemTest.getStore();
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                intent.putExtra("item_object", itemTest);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
            }
        });
    }

    private  void getItemsFromServer() {
        Call<List<ItemTest>> callHome = jsonPlaceHolderApi.getAllItems(0,15);
        callHome.enqueue(new Callback<List<ItemTest>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<ItemTest>> call, Response<List<ItemTest>> response) {
                if (!response.isSuccessful())
                { return; }
                List<ItemTest> itemsList = response.body();

                hideLoading();
                createRVSuggested(itemsList);
            }

            @Override
            public void onFailure(Call<List<ItemTest>> call, Throwable t) {
                Log.i("TAG","getItemsFromServer  in error");
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createRVSuggested(List<ItemTest> itemsList) {
        dealsArrayList = new ArrayList<>();
        dealsArrayList.addAll(itemsList);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        adapterType2 =new AdapterType2(getActivity()
                ,dealsArrayList,"category");
        recyclerView.setAdapter(adapterType2);
    }

    private void hideLoading() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                lodaingRV.setVisibility(View.GONE);
            }
        }, 500);
    }

    private void intiRetrofit() {
        retrofit = getStoreItems(itemTest.getStore().getId(),itemTest.getStore().getArea());
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    private void createLoadingRV() {
        lodaingRV.setNestedScrollingEnabled(false);
        lodaingRV.setHasFixedSize(true);
        layoutManagerLoading = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        lodaingRV.setLayoutManager(layoutManagerLoading);
        adapterLoadingType2 =new AdapterLoadingType2(getActivity());
        lodaingRV.setAdapter(adapterLoadingType2);
    }

    private void actionListenerToStore() {
        store_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                store = itemTest.getStore();
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                intent.putExtra("item_object", itemTest);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
            }
        });
    }

    private void actionListenerToVisitWebsite() {
        fragment_store_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(website_link));
                startActivity(intent);
            }
        });
    }

    private void fillUserImageAndUserName() {
        Picasso.get()
                .load(storeImage)
                .fit()
                .centerCrop()
                .into(userImageIV);

        userNameTV.setText(getTextEngOrLocal(getActivity(),itemTest.getStore().getName(),itemTest.getStore().getName_local()));
        store_address_tv.setText(itemTest.getStore().getAddress());
//        store_info_more_item.setText(getActivity().getResources().getString(R.string.more_items)+" "+getTextEngOrLocal(getActivity(),itemTest.getStore().getName(),itemTest.getStore().getName_local()));
        store_info_more_item.setText(getTextEngOrLocal(getActivity(),itemTest.getStore().getName(),itemTest.getStore().getName_local())+" "+getActivity().getResources().getString(R.string.more_items));
    }

    private void changeFont() {
        store_info_more_item.setTypeface(Functions.changeFontGeneral(getActivity()));
        store_info_see_more_tv.setTypeface(Functions.changeFontGeneral(getActivity()));
        userNameTV.setTypeface(Functions.changeFontGeneral(getActivity()));
        store_address_tv.setTypeface(Functions.changeFontGeneral(getActivity()));
    }

}
