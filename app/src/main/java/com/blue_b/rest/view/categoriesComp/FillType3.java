package com.blue_b.rest.view.categoriesComp;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.blue_b.rest.model.Categories;
import com.blue_b.rest.view.Adapters.AdapterType3;
import java.util.ArrayList;

public class FillType3 {

    static AdapterType3 adapterType3;
    static RecyclerView.LayoutManager layoutManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void fillCase3Item(RecyclerView recyclerView, Context context,ArrayList<Categories> categoriesArrayList) {

        createRVSuggested(recyclerView, context,categoriesArrayList);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void createRVSuggested(RecyclerView recyclerView, Context context,ArrayList<Categories> categoriesArrayList) {
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        adapterType3 =new AdapterType3(context
                ,categoriesArrayList,"category");
        recyclerView.setAdapter(adapterType3);
    }

}
