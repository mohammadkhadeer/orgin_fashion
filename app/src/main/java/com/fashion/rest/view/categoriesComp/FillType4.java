package com.fashion.rest.view.categoriesComp;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fashion.rest.model.Deal;
import com.fashion.rest.view.Adapters.AdapterType3;
import com.fashion.rest.view.Adapters.AdapterType4;
import java.util.ArrayList;
import static com.fashion.rest.functions.FillItem.fillEndlessItemDepCatArrayL;
import static com.fashion.rest.functions.Functions.fillSetArrayL;

public class FillType4 {

    static AdapterType4 adapterType4;
    static RecyclerView.LayoutManager layoutManager;
    public static ArrayList<Deal> dealsArrayList = new ArrayList<>();

    public static void fillCase4Item(RecyclerView recyclerView, Context context) {
        createRVSuggested(recyclerView, context);
    }

    public static void createRVSuggested(RecyclerView recyclerView, Context context) {
        dealsArrayList = fillSetArrayL(dealsArrayList,context);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        adapterType4 =new AdapterType4(context
                ,dealsArrayList,"category");
        recyclerView.setAdapter(adapterType4);
    }

}
