package com.fashion.rest.view.fragments.cartFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fashion.rest.R;
import com.fashion.rest.model.ItemInDB;
import com.fashion.rest.presnter.UpdateTotalNumber;
import com.fashion.rest.view.Adapters.AdapterCartItem;

import java.util.ArrayList;

import static com.fashion.rest.database.Read.getAllItemFromCart;

public class FragmentCartItem extends Fragment implements AdapterCartItem.PassUpdate {
    View view;
    RecyclerView recyclerView;
    AdapterCartItem adapterCartItem;
    RecyclerView.LayoutManager layoutManager;
    public ArrayList<ItemInDB> itemInCartArrayList = new ArrayList<>();

    UpdateTotalNumber updateTotalNumber;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UpdateTotalNumber) {
            updateTotalNumber = (UpdateTotalNumber) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        updateTotalNumber = null;
    }

    public FragmentCartItem(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_cart_item, container, false);
        inti();
        createRVSuggested();
        return view;
    }

    private void inti() {
        recyclerView = (RecyclerView) view.findViewById(R.id.cart_item_RV);
    }

    private void createRVSuggested() {
        itemInCartArrayList = getAllItemFromCart(getActivity());

//        Log.d("TAG",String.valueOf(itemInCartArrayList.size()));
//
//        for (int i = 0;i < itemInCartArrayList.size();i++)
//        {
//            Log.d("TAG",String.valueOf(itemInCartArrayList.get(i).getId()));
//            Log.d("TAG",String.valueOf(itemInCartArrayList.get(i).getImageID()));
//            Log.d("TAG",String.valueOf(itemInCartArrayList.get(i).getImage()));
//
//            Log.d("TAG",String.valueOf(itemInCartArrayList.get(i).getName()));
//            Log.d("TAG",String.valueOf(itemInCartArrayList.get(i).getDes()));
//            Log.d("TAG",String.valueOf(itemInCartArrayList.get(i).getPrice()));
//
//            Log.d("TAG",String.valueOf(itemInCartArrayList.get(i).getPriceN()));
//            Log.d("TAG",String.valueOf(itemInCartArrayList.get(i).getPriceO()));
//            Log.d("TAG",String.valueOf(itemInCartArrayList.get(i).getNumberOfItemNow()));
//
//
//        }

        recyclerView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        adapterCartItem =new AdapterCartItem(getActivity()
                ,itemInCartArrayList,getActivity().getResources().getString(R.string.set_s),this);
        recyclerView.setAdapter(adapterCartItem);

    }

    @Override
    public void onClicked(boolean change) {
        updateTotalNumber.updateNow(true);
    }
}
