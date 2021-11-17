package com.blue_b.rest.presenter;

import static com.blue_b.rest.apiURL.API.apiURLBase;
import static com.blue_b.rest.functions.Functions.checkIfAndroidVBiggerThan9;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginAndUpdateProfile {


    public static void whenLoginCompleteSuccess(String email, String password)
    {
        if (checkIfAndroidVBiggerThan9()) {
            Log.w("TAG", "Message " + "whenLoginCompleteSuccess");

            JSONObject obj = null;
            String token="ANqVF8PzLrCt0sxt7ZqPsuqrPPX0Zi5CXnF699-DmG6txOlpz5iom0Z7JxpKnLWmlBMcYr2YG9C1ef331cOTjsI7OlUVbQwGhvA5CR8ISwqSllvqtBPU83LWCueuwtc4mXNL6QbW7DzEqmdeQdGfCgIXHX8nxC_0NMo4-FkqAkEgE2XbJ0NUe-GdEhNsjBFZI0KWSEBAkaIAiekLjN0pY5znei62mRyayUwlMI0KKtHqOKJ96g3yk2NtsMGqGC8EYeMcBH30Oy_z8M6NirCgjssKj_fTtUdQUq-jc2gRW_JewgjirFzgsS6NeAdx3e0mN-Zawk1qqTy7L0BCO037UA";
            String email_str="raza@gmail.com",pass= "123456";




            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("Email","raza@gmail.com")
                    .addFormDataPart("Password","123456")
                    .build();

            Request request = new Request.Builder()
                    .url("http://192.168.0.115/api/User/Login")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json ")
                    .build();

            //.addHeader("Authorization", "Bearer " + token)


            try {
                Response response = client.newCall(request).execute();
                try {
                    Log.w("TAG", "Response " + response);

                    obj = new JSONObject(response.body().string());
                    JSONObject data = obj.getJSONObject("Result");

                    Log.w("TAG", "Result " + data);
                    //send response by interface

                    //login.whenLoginSuccess(obj,platform,platform_id,photo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    public static void whenLoginCompleteSuccess2(String email, String password)
    {
        JSONObject obj = null;
        String token="ZvlYoUJjIOjECY8m-rh97RTdyQL09SSeTcZKg4j1HqkhZGzY3DptYvNZOEK46dT7y4nAs5kVfowgzrfeanAFkpeqshB7iRuSkAxMTMAn8Y6DvSU2O-LF2RMSymKVI_cro7Hmqhv_fFhGPAsDqZe5nqOqZIwgrq70K8aBqiwysrIy4obhgzEVLONI9wnVUazWBOnMGXSFeN6HWoI6m_7XwkSUk7jVQbZTYxmGbDPipQPcYPYNGtrlKpUTgMNSEvv05PNY3EG4mb4w3OJJBi6J7b4f-c980LI8Yd9hkh4L79l_QMOlZmCYIs8rh6mhkdtFGrg24KY8kvKpUdP6nBeswA";


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("email",email)
                .addFormDataPart("password",password)
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.0.115/api/User/Login")
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            try {
                Log.w("TAG", "Response " + response);

                obj = new JSONObject(response.body().string());
                JSONObject data = obj.getJSONObject("Result");

                Log.w("TAG", "Result " + data);
                //send response by interface

                //login.whenLoginSuccess(obj,platform,platform_id,photo);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public static void updateProfileSuccess(final String userTokenInServer, final UpdateProfile updateProfile
//            , final String areaId, final String area) {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                JSONObject obj = null;
//                OkHttpClient client = new OkHttpClient().newBuilder()
//                        .build();
//                RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                        .addFormDataPart("area_id", areaId)
//                        .addFormDataPart("area", area)
//                        .build();
//                Request request = new Request.Builder()
//                        .url(BASE_API + "/profile")
//                        .method("POST", body)
//                        .addHeader("Accept", "application/json")
//                        .addHeader("Authorization", "Bearer " + userTokenInServer)
//                        .build();
//                try {
//                    Response response = client.newCall(request).execute();
//
//                    try {
//                        Log.w("TAG", "Response " + response);
//
//                        obj = new JSONObject(response.body().string());
//
//                        JSONObject data = obj.getJSONObject("DATA");
//
//                        updateProfile.updateSuccess(obj);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        thread.start();
//    }
}
