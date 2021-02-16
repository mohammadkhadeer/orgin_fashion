package com.fashion.rest.view.categoriesComp;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fashion.rest.model.Deal;
import com.fashion.rest.model.Home;
import com.fashion.rest.view.Adapters.AdapterType3;
import com.fashion.rest.view.Adapters.AdapterType4;
import java.util.ArrayList;
import static com.fashion.rest.functions.FillItem.fillEndlessItemDepCatArrayL;
import static com.fashion.rest.functions.Functions.fillSetArrayL;
import static com.fashion.rest.functions.Functions.fillSetArrayL2;
import static com.fashion.rest.functions.Functions.getTextEngOrLocal;

public class FillType4 {

    static AdapterType4 adapterType4;
    static RecyclerView.LayoutManager layoutManager;
    public static ArrayList<Deal> dealsArrayList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void fillCase4Item(RecyclerView recyclerView, Context context, TextView catName, RelativeLayout seeAllType4, Home home) {
        fillText(home,catName);
        createRVSuggested(recyclerView, context);
        actionListenerToSeeAll(seeAllType4,context);
    }

    private static void fillText(Home home, TextView catName) {
        catName.setText(getTextEngOrLocal(home.getSub_cat().getName_en(),home.getSub_cat().getName_local()));
    }

    private static void actionListenerToSeeAll(RelativeLayout seeAllType2, final Context context) {
        seeAllType2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"see all ..",Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void createRVSuggested(RecyclerView recyclerView, Context context) {
        dealsArrayList = fillSetArrayL2(dealsArrayList,context);
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
