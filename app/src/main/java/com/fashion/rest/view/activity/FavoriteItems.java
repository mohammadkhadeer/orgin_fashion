package com.fashion.rest.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fashion.rest.R;
import com.fashion.rest.database.DBHelper;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.CustomItems;
import com.fashion.rest.model.Flag;
import com.fashion.rest.model.ItemFavorite;
import com.fashion.rest.model.ItemTest;
import com.fashion.rest.model.Store;
import com.fashion.rest.presnter.JsonPlaceHolderApi;
import com.fashion.rest.view.Adapters.AdapterEndlessFavorite;
import com.fashion.rest.view.Adapters.AdapterEndlessResult;
import com.fashion.rest.view.activity.mainScreem.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.fashion.rest.apiURL.API.apiURLBase;
import static com.fashion.rest.functions.FillItem.itemsIdInFavorite;
import static com.fashion.rest.functions.Functions.getNewItems;
import static com.fashion.rest.functions.Functions.itemFav;
import static com.fashion.rest.functions.Functions.loadMoreItem;
import static com.fashion.rest.functions.RetrofitFunctions.getItemsWithAllFilter;
import static com.fashion.rest.functions.RetrofitFunctions.getSingleItemDetails;
import static com.fashion.rest.sharedPreferences.Language.getLanguageFromSP;
import static com.fashion.rest.sharedPreferences.LoginInfo.getLoginOrNotFromSP;
import static com.fashion.rest.view.fragments.fragmentHomeMainScreen.FragmentResults.PAGE_START;

public class FavoriteItems extends AppCompatActivity {

    ArrayList<String> itemsId = new ArrayList<>();
    public ArrayList<ItemFavorite> itemFavoriteArrayList = new ArrayList<>();
    public ArrayList<ItemFavorite> itemFavoriteLoadMoreArrayList = new ArrayList<>();

    public ArrayList<String> newItemsMustLoaded= new ArrayList<>();
    JsonPlaceHolderApi jsonPlaceHolderApi;
    Retrofit retrofit;
    RecyclerView results_RV;
    NestedScrollView nestedScrollView;
    AdapterEndlessFavorite adapterEndlessFavorite;
    LinearLayoutManager mLayoutManager;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private boolean isLoading = false;

    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_items);

        statusBarColor();
        actionBarTitle();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        DBHelper dbHelper= new DBHelper(getApplicationContext());
//        dbHelper.deleteAllFavItems();

        inti();
        changeFont();
        getItemsIdFromDtaBase();

        fillItemIdPag();

        createRV();

        actionListenerToNestedScroll();
        checkIfNeedLoadMore();
    }

    private void changeFont() {
        textView.setTypeface(Functions.changeFontGeneral(this));
    }

    private void fillItemIdPag() {
        newItemsMustLoaded = getNewItems(itemsId,newItemsMustLoaded);
        if (newItemsMustLoaded.size() != 0)
        {
            for (int i =0;i<newItemsMustLoaded.size();i++)
            {
                intiRetrofit(newItemsMustLoaded.get(i));
                doApiCall();
            }
        }else{
            progressBar.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }
    }

    private void checkIfNeedLoadMore() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                //Toast.makeText(getApplicationContext(),getResources().getString(R.string.finish_loading),Toast.LENGTH_SHORT).show();
                if (currentPage != PAGE_START ) adapterEndlessFavorite.removeLoading();
                if (itemFavoriteLoadMoreArrayList.size()!=0)
                {
                    adapterEndlessFavorite.addItems(itemFavoriteLoadMoreArrayList);
                }

                if (loadMoreItem(itemsId.size(),itemFavoriteArrayList.size()) != 0)
                {
                    adapterEndlessFavorite.addLoading();
                    isLoading = false;
                }
            }
        }, 2000);
    }

    private void actionListenerToNestedScroll() {
        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(new
                                                                                  ViewTreeObserver.OnScrollChangedListener() {
                                                                                      @RequiresApi(api = Build.VERSION_CODES.M)
                                                                                      @Override
                                                                                      public void onScrollChanged() {
                                                                                          View view = (View) nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
                                                                                          int diff = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView.getScrollY()));
                                                                                          if (diff == 0) {
                                                                                              if (loadMoreItem(itemsId.size(),itemFavoriteArrayList.size()) != 0)
                                                                                              {
                                                                                                  //Toast.makeText(getApplicationContext(),getResources().getString(R.string.wait_loading),Toast.LENGTH_SHORT).show();
                                                                                                  currentPage ++;
                                                                                                  isLoading = true;
                                                                                                  fillItemIdPag();
                                                                                                  itemFavoriteLoadMoreArrayList = new ArrayList<>();

                                                                                                  checkIfNeedLoadMore();
                                                                                              }else{
                                                                                                  Toast.makeText(getApplicationContext(),getResources().getString(R.string.no_more_item),Toast.LENGTH_SHORT).show();
                                                                                              }


                                                                                          }
                                                                                      }
                                                                                  });
    }

    private void createRV() {
        adapterEndlessFavorite = new AdapterEndlessFavorite(new ArrayList<ItemFavorite>(),this);
        results_RV.setHasFixedSize(true);
        results_RV.setNestedScrollingEnabled(false);

        mLayoutManager = new GridLayoutManager(this, 1);
        //mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        results_RV.setLayoutManager(mLayoutManager);
        results_RV.setAdapter(adapterEndlessFavorite);
        //doApiCall();
    }

    private void inti() {
        itemFavoriteArrayList = new ArrayList<>();
        itemFavoriteLoadMoreArrayList = new ArrayList<>();
        newItemsMustLoaded = new ArrayList<>();

        textView = (TextView) findViewById(R.id.no_more_item);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        results_RV = (RecyclerView) findViewById(R.id.favorite_RV);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nested_fav);
    }

    private void intiRetrofit(String itemID) {
        retrofit = getSingleItemDetails(itemID);
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    private void doApiCall() {
        Call<ItemFavorite> callHome = jsonPlaceHolderApi.getDetailsItem();
        callHome.enqueue(new Callback<ItemFavorite>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<ItemFavorite> call, Response<ItemFavorite> response) {
                if (!response.isSuccessful())
                {
                    //to handel when item be deleted
                    ItemFavorite itemFavorite = itemFav();
                    itemFavoriteArrayList.add(itemFavorite);
                    itemFavoriteLoadMoreArrayList.add(itemFavorite);
                    return;
                }
                ItemFavorite itemFavorite = response.body();
                itemFavoriteArrayList.add(itemFavorite);
                itemFavoriteLoadMoreArrayList.add(itemFavorite);

            }

            @Override
            public void onFailure(Call<ItemFavorite> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }


    private void getItemsIdFromDtaBase() {
        itemsId = itemsIdInFavorite(this);
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void actionBarTitle() {
        Typeface typeface;
        final ActionBar abar = getSupportActionBar();
        View viewActionBar = getLayoutInflater().inflate(R.layout.actionbar_custom_title_view_centered, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        if (Locale.getDefault().getLanguage().equals("ar")) {
            typeface = Typeface.createFromAsset(getAssets(), "GE_DINAR_ONE_LIGHT.TTF");
        }else{
            typeface = Typeface.createFromAsset(getAssets(), "NTAILU.TTF");
        }
        TextView textviewTitle = (TextView) viewActionBar.findViewById(R.id.actionbar_textview);
        textviewTitle.setTextColor(Color.parseColor("#FF0000"));
        textviewTitle.setText(getResources().getString(R.string.favourite));
        textviewTitle.setTypeface(typeface);
        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);
        abar.setDisplayHomeAsUpEnabled(false);
        abar.setHomeButtonEnabled(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}