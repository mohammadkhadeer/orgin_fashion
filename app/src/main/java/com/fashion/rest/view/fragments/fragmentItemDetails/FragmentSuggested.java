package com.fashion.rest.view.fragments.fragmentItemDetails;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.FilterItemsModel;
import com.fashion.rest.model.ItemTest;
import com.fashion.rest.presnter.JsonPlaceHolderApi;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.fashion.rest.functions.Functions.getDefultToFilterItemModel;
import static com.fashion.rest.functions.Functions.getTextEngOrLocal;
import static com.fashion.rest.functions.RetrofitFunctions.getItemHome;
import static com.fashion.rest.functions.RetrofitFunctions.getItems;

public class FragmentSuggested extends Fragment{
    View view;
    String cat,cat_type;
    SLFullImageAndImagePng slFullImageAndImagePng = new SLFullImageAndImagePng();
    SLOffersCase slOffersCase = new SLOffersCase();
    ArrayList<ItemTest> itemsArrayList = new ArrayList<>();
    TextView no_more_items;
    RelativeLayout cont_no_more_items_rl;

    public FragmentSuggested(){}
    ItemTest itemTest;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    Retrofit retrofit;

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            itemTest = (ItemTest) getArguments().getParcelable("item_object");
            cat_type = itemTest.getSub_cat().getAppearance();
        }
        super.onAttach(context);
        intiRetrofit();
        if (cat_type.equals("offers"))
        {
            handelOffersType();
        } else {
            getItemsFromServer();
        }
    }

    private void intiRetrofit() {
//        retrofit = getItemHome(itemTest.getSub_cat().getId(),itemTest.getSub_cat().getCategory_id());
        FilterItemsModel filterItemsModel = getDefultToFilterItemModel(itemTest.getSub_cat());
        retrofit = getItems(filterItemsModel);
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_suggested, container, false);
        inti();
        changeFont();
        if (cat_type.equals("offers"))
            cont_no_more_items_rl.setVisibility(View.GONE);
        return view;
    }

    private void changeFont() {
        no_more_items.setTypeface(Functions.changeFontBold(getActivity()));
    }

    private void inti() {
        no_more_items = (TextView) view.findViewById(R.id.no_more_items_sf);
        cont_no_more_items_rl = (RelativeLayout) view.findViewById(R.id.cont_no_more_items_rl_sf);
    }

    private void getItemsFromServer() {
        itemsArrayList= new ArrayList<>();
        Call<List<ItemTest>> callHome = jsonPlaceHolderApi.getAllItems(0,16);
        callHome.enqueue(new Callback<List<ItemTest>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<ItemTest>> call, Response<List<ItemTest>> response) {
                if (!response.isSuccessful())
                { return; }
                List<ItemTest> itemsList = response.body();
                itemsArrayList.addAll(itemsList);

                for (int i=0;i<itemsArrayList.size();i++)
                {
                    if (itemsList.get(i).getName().equals(itemTest.getName()))
                    {
                        itemsArrayList.remove(i);
                    }
                }
                if (itemsArrayList.size() !=0)
                {
                    checkSuggestedListType();
                }else{
                    cont_no_more_items_rl.setVisibility(View.VISIBLE);
                    fillNoMoreItems();
                }
            }

            @Override
            public void onFailure(Call<List<ItemTest>> call, Throwable t) {
                Log.i("TAG","getItemsFromServer  in error");
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private void fillNoMoreItems() {
        no_more_items.setText(getActivity().getResources().getString(R.string.no_more_items)+ getTextEngOrLocal(getActivity(),itemTest.getName(),itemTest.getName_local()));
    }

    private void checkSuggestedListType() {
        if (cat_type.equals("1"))
        {
            handelSuggestedType();
        }

        if (cat_type.equals("offers"))
        {
            handelOffersType();
        }
        if (cat_type.equals("2"))
        {
            handelSuggestedType();
        }
    }

    private void handelOffersType() {
        Bundle bundle = new Bundle();
        bundle.putString("cat", cat);
        bundle.putString("cat_type", cat_type);
        bundle.putParcelable("item_object", itemTest);

        slOffersCase.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_full_image_and_image_png, slOffersCase)
                .commit();
    }

    private void handelSuggestedType() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("item_object", itemTest);
        bundle.putParcelableArrayList("items_list", itemsArrayList);

        slFullImageAndImagePng.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_full_image_and_image_png, slFullImageAndImagePng)
                .commit();
    }

}
