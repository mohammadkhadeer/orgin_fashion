package com.fashion.rest.view.categoriesComp;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.fashion.rest.model.Deal;
import com.fashion.rest.view.Adapters.AdapterEndlessType3;
import com.fashion.rest.view.Adapters.AdapterEndlessType4;

import java.util.ArrayList;

import static com.fashion.rest.functions.FillItem.fillEndlessItemDepCatArrayL;

public class FillType4 {

    static boolean isLoading4 = false;
    public static ArrayList<Deal> dealsArrayList = new ArrayList<>();
    public static ArrayList<Deal> suggestedItemsArrayListTest=new ArrayList<>();
    public static ArrayList<Deal> suggestedItemsArrayListDO=new ArrayList<>();;
    public static final int PAGE_START = 1;


    public static int currentPage4 = PAGE_START;

    public static boolean isLastPage4 = false;
    public static int totalPage4 = 10;

    static LinearLayoutManager mLayoutManager;
    static AdapterEndlessType4 adapterEndlessType4 ;

    public static void fillCase4Item(RecyclerView recyclerView, Context context) {

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

                if (!isLoading4) {
                    Log.i("TAG BAG","After if isLoading: "+String.valueOf(isLoading4));

                    if (mLayoutManager != null && mLayoutManager.findLastCompletelyVisibleItemPosition() == suggestedItemsArrayListDO.size() - 1) {
                        //bottom of list!
                        Toast.makeText(context,"TAG !" +String.valueOf(currentPage4)+ " Load more ...",Toast.LENGTH_SHORT).show();

                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                doApiCall(context);

                            }
                        }, 2000);
                        currentPage4 ++;
                        isLoading4 = true;

                    }
                }
            }
        });

    }

    private static void createRV(RecyclerView recyclerView, Context context) {
        adapterEndlessType4 = new AdapterEndlessType4(new ArrayList<Deal>(),context,"call");
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapterEndlessType4);
        doApiCall(context);
    }

    private static void doApiCall(final Context context) {
        suggestedItemsArrayListTest = new ArrayList<>();
        suggestedItemsArrayListTest = fillEndlessItemDepCatArrayL(suggestedItemsArrayListTest,context);
        suggestedItemsArrayListDO = fillEndlessItemDepCatArrayL(suggestedItemsArrayListDO,context);
        //fill here
        if (currentPage4 != PAGE_START) adapterEndlessType4.removeLoading() ;isLoading4 =false;
        adapterEndlessType4.addItems(suggestedItemsArrayListTest);
        if (currentPage4 < totalPage4) {
            adapterEndlessType4.addLoading();
            isLoading4 = false;
        } else {
            isLastPage4 = true;
        }
    }


}
