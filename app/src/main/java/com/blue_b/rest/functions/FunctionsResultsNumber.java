package com.blue_b.rest.functions;


import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.blue_b.rest.R;
import com.blue_b.rest.model.FilterItemsModel;
import com.blue_b.rest.presnter.JsonPlaceHolderApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FunctionsResultsNumber {
    static JsonPlaceHolderApi jsonPlaceHolderApiResultItems;
    static Retrofit retrofitResultItems;

    public static void fillNumberOfItemsResult(final Context context, final TextView numberOfResult, FilterItemsModel filterItemsModel, final String from) {
        retrofitResultItems = RetrofitFunctions.getNumberOfItemsResults(filterItemsModel);
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
