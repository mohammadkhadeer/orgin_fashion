package com.fashion.rest.view.categoriesComp;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.fashion.rest.model.Deal;
import com.fashion.rest.view.Adapters.AdapterEndlessType2;
import com.fashion.rest.view.Adapters.AdapterEndlessType3;

import java.util.ArrayList;

import static com.fashion.rest.functions.FillItem.fillEndlessItemDepCatArrayL;

public class FillType3 {

    public static ArrayList<Deal> dealsArrayList = new ArrayList<>();
    public static ArrayList<Deal> suggestedItemsArrayListTest=new ArrayList<>();
    public static ArrayList<Deal> suggestedItemsArrayListDO=new ArrayList<>();;
    public static final int PAGE_START = 1;

    static boolean isLoading3 = false;
    public static int currentPage3 = PAGE_START;
    public static boolean isLastPage3 = false;

    public static int totalPage3 = 10;

    static LinearLayoutManager mLayoutManager;
    static AdapterEndlessType3 adapterEndlessType3 ;

    public static void fillCase3Item(RecyclerView recyclerView, Context context) {

        createRV(recyclerView,context);
        actionListenerToRV(recyclerView,context);
    }

    private static void actionListenerToRV(RecyclerView recyclerView,final Context context) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!isLoading3) {
                    Log.i("TAG BAG","After if isLoading: "+String.valueOf(isLoading3));

                    if (mLayoutManager != null && mLayoutManager.findLastCompletelyVisibleItemPosition() == suggestedItemsArrayListDO.size() - 1) {
                        //bottom of list!
                        Toast.makeText(context,"TAG !" +String.valueOf(currentPage3)+ " Load more ...",Toast.LENGTH_SHORT).show();

                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                doApiCall(context);

                            }
                        }, 2000);
                        currentPage3 ++;
                        isLoading3 = true;

                    }
                }
            }
        });

    }

    private static void createRV(RecyclerView recyclerView, Context context) {
        adapterEndlessType3 = new AdapterEndlessType3(new ArrayList<Deal>(),context,"call");
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapterEndlessType3);
        doApiCall(context);
    }

    private static void doApiCall(final Context context) {
        suggestedItemsArrayListTest = new ArrayList<>();
        suggestedItemsArrayListTest = fillEndlessItemDepCatArrayL(suggestedItemsArrayListTest,context);
        suggestedItemsArrayListDO = fillEndlessItemDepCatArrayL(suggestedItemsArrayListDO,context);
        //fill here
        if (currentPage3 != PAGE_START) adapterEndlessType3.removeLoading() ;isLoading3 =false;
        adapterEndlessType3.addItems(suggestedItemsArrayListTest);
        if (currentPage3 < totalPage3) {
            adapterEndlessType3.addLoading();
            isLoading3 = false;
        } else {
            isLastPage3 = true;
        }
    }


}
