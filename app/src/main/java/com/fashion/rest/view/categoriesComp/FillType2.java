package com.fashion.rest.view.categoriesComp;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fashion.rest.model.Deal;
import com.fashion.rest.model.Home;
import com.fashion.rest.model.ItemTest;
import com.fashion.rest.presnter.JsonPlaceHolderApi;
import com.fashion.rest.view.Adapters.AdapterType2;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.fashion.rest.functions.Functions.fillSetArrayL;
import static com.fashion.rest.functions.Functions.getTextEngOrLocal;
import static com.fashion.rest.functions.RetrofitFunctions.getItems;

public class FillType2 {
    static AdapterType2 adapterType2;
    static RecyclerView.LayoutManager layoutManager;
    public static ArrayList<ItemTest> dealsArrayList = new ArrayList<>();
    static JsonPlaceHolderApi jsonPlaceHolderApi2;
    static Retrofit retrofit2;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void fillCaseItem(RecyclerView recyclerView, Context context, TextView catName, RelativeLayout seeAllType2, Home home) {
        fillText(context,catName,home);
        intiRetrofit(home);
        getItemsFromServer(recyclerView,context);
        actionListenerToSeeAll(seeAllType2,context);
    }

    private static void getItemsFromServer(final RecyclerView recyclerView, final Context context) {
        Call<List<ItemTest>> callHome = jsonPlaceHolderApi2.getAllItems(0,15);
        callHome.enqueue(new Callback<List<ItemTest>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<List<ItemTest>> call, Response<List<ItemTest>> response) {
                if (!response.isSuccessful())
                {
                    Log.i("TAG Error code", String.valueOf(response.code()));
                    return;
                }

                List<ItemTest> itemsList = response.body();

                for (int i=0;i<itemsList.size();i++)
                {

                }
                createRVSuggested(recyclerView, context,itemsList);

            }

            @Override
            public void onFailure(Call<List<ItemTest>> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private static void intiRetrofit(Home home) {
        retrofit2 = getItems(home.getSub_cat().getId());
        jsonPlaceHolderApi2 = retrofit2.create(JsonPlaceHolderApi.class);
    }

    private static void fillText(Context context, TextView catName,Home home) {
        catName.setText(getTextEngOrLocal(context,home.getSub_cat().getName_en(),home.getSub_cat().getName_local()));
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
    public static void createRVSuggested(RecyclerView recyclerView, Context context,List<ItemTest> itemsList) {
        dealsArrayList = new ArrayList<>();
        dealsArrayList.addAll(itemsList);
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
