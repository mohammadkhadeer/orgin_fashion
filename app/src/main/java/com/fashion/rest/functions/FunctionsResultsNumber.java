package com.fashion.rest.functions;


import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.database.DBHelper;
import com.fashion.rest.model.Area;
import com.fashion.rest.model.Categories;
import com.fashion.rest.model.City;
import com.fashion.rest.model.FilterItemsModel;
import com.fashion.rest.model.FilterOffersModel;
import com.fashion.rest.model.Flag;
import com.fashion.rest.model.ItemFavorite;
import com.fashion.rest.model.ItemTest;
import com.fashion.rest.model.Location;
import com.fashion.rest.model.Notification;
import com.fashion.rest.model.Offer;
import com.fashion.rest.model.ReportType;
import com.fashion.rest.model.Store;
import com.fashion.rest.model.Sub_Cat;
import com.fashion.rest.presnter.JsonPlaceHolderApi;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.fashion.rest.database.DataBaseInstance.getDataBaseInstance;
import static com.fashion.rest.functions.FillItem.fillItemsIdInFavTable;
import static com.fashion.rest.functions.RetrofitFunctions.getNumberOfItemsResults;
import static com.fashion.rest.sharedPreferences.Language.getLanguageFromSP;
import static com.fashion.rest.sharedPreferences.NumberOfNotification.getNumberOfItemInCartFromSP;

public class FunctionsResultsNumber {
    static JsonPlaceHolderApi jsonPlaceHolderApiResultItems;
    static Retrofit retrofitResultItems;

    public static void fillNumberOfItemsResult(final Context context, final TextView numberOfResult, FilterItemsModel filterItemsModel, final String from) {
        retrofitResultItems = getNumberOfItemsResults(filterItemsModel);
        jsonPlaceHolderApiResultItems = retrofitResultItems.create(JsonPlaceHolderApi.class);

        jsonPlaceHolderApiResultItems = retrofitResultItems.create(JsonPlaceHolderApi.class);
        Call<Integer> call = jsonPlaceHolderApiResultItems.getNumberOfItems();
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful())
                {
                    Log.i("TAG Error code", String.valueOf(response.code()));
                    return;
                }
                String result;
//                Log.i("TAG Response", response.body().toString());
                if (from.equals("result_activity"))
                {
                    String result_text = context.getResources().getString(R.string.Result_number);
                     result= result_text +" "+ response.body().toString();
                }else{
                    String result_text = context.getResources().getString(R.string.show_results);
                    result= result_text +" "+ response.body().toString();
                }


                numberOfResult.setText(result);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });

    }
}
