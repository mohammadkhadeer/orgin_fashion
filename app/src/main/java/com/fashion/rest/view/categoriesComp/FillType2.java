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

import java.util.ArrayList;

import static com.fashion.rest.functions.FillItem.fillEndlessItemDepCatArrayL;

public class FillType2 {

    public static ArrayList<Deal> dealsArrayList = new ArrayList<>();
    public static ArrayList<Deal> suggestedItemsArrayListTest=new ArrayList<>();
    public static ArrayList<Deal> suggestedItemsArrayListDO=new ArrayList<>();;
    public static final int PAGE_START = 1;


    public static int currentPage2 = PAGE_START;
    static boolean isLoading2 = false;
    public static boolean isLastPage2 = false;

    public static int totalPage = 10;

    static LinearLayoutManager mLayoutManager;
    static AdapterEndlessType2 adapterEndlessType2 ;

    public static void fillCaseItem(RecyclerView recyclerView, Context context) {

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

                if (!isLoading2) {
                    Log.i("TAG BAG","After if isLoading: "+String.valueOf(isLoading2));

                    if (mLayoutManager != null && mLayoutManager.findLastCompletelyVisibleItemPosition() == suggestedItemsArrayListDO.size() - 1) {
                        //bottom of list!
                        Toast.makeText(context,"TAG !" +String.valueOf(currentPage2)+ " Load more ...",Toast.LENGTH_SHORT).show();

                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                doApiCall(context);

                            }
                        }, 2000);
                        currentPage2 ++;
                        isLoading2 = true;

                    }
                }
            }
        });

    }

    private static void createRV(RecyclerView recyclerView, Context context) {
        adapterEndlessType2 = new AdapterEndlessType2(new ArrayList<Deal>(),context,"call");
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapterEndlessType2);
        doApiCall(context);
    }

    private static void doApiCall(final Context context) {
        suggestedItemsArrayListTest = new ArrayList<>();
        suggestedItemsArrayListTest = fillEndlessItemDepCatArrayL(suggestedItemsArrayListTest,context);
        suggestedItemsArrayListDO = fillEndlessItemDepCatArrayL(suggestedItemsArrayListDO,context);
        //fill here
        if (currentPage2 != PAGE_START) adapterEndlessType2.removeLoading() ;isLoading2 =false;
        adapterEndlessType2.addItems(suggestedItemsArrayListTest);
        if (currentPage2 < totalPage) {
            adapterEndlessType2.addLoading();
            isLoading2 = false;
        } else {
            isLastPage2 = true;
        }
    }


}
