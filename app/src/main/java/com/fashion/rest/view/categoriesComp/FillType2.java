package com.fashion.rest.view.categoriesComp;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.fashion.rest.model.Deal;
import com.fashion.rest.view.Adapters.AdapterType2;
import java.util.ArrayList;
import static com.fashion.rest.functions.Functions.fillSetArrayL;

public class FillType2 {
    static AdapterType2 adapterType2;
    static RecyclerView.LayoutManager layoutManager;
    public static ArrayList<Deal> dealsArrayList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void fillCaseItem(RecyclerView recyclerView, Context context) {
        createRVSuggested(recyclerView, context);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void createRVSuggested(RecyclerView recyclerView, Context context) {
        dealsArrayList = fillSetArrayL(dealsArrayList,context);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        adapterType2 =new AdapterType2(context
                ,dealsArrayList,"category");
        recyclerView.setAdapter(adapterType2);
    }

}
