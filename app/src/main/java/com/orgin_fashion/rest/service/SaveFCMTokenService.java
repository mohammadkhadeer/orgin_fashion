package com.orgin_fashion.rest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.util.Log;

import com.orgin_fashion.rest.model.UserInfo;
import com.orgin_fashion.rest.presnter.JsonPlaceHolderApi;
import com.orgin_fashion.rest.utils.FCM_Device_Tokens;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.orgin_fashion.rest.functions.RetrofitFunctions.insertUserInfo;
import static com.orgin_fashion.rest.sharedPreferences.LoginInfo.saveIDInSP;

public class SaveFCMTokenService extends Service {
    JsonPlaceHolderApi jsonPlaceHolderApi;
    Retrofit retrofit;
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        retrofit = insertUserInfo();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub

        if(intent != null){

            Bundle b = intent.getExtras();

            if(b != null) {
                String token = b.getString("TOKEN");

                sendRegistrationToServer(token);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    private void sendRegistrationToServer(final String token) {
        // Add custom implementation, as needed.

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference fcmDatabaseRef = ref.child("FCM_Device_Tokens").push();

        registerToTopic();
        //save on google firebase
        FCM_Device_Tokens obj = new FCM_Device_Tokens();
        obj.setToken(token);
        fcmDatabaseRef.setValue(obj);
        //save in server
        sendUserTokenToServer(token);

    }

    private void registerToTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic("important_message")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "msg topic scsess important_message";
                        if (!task.isSuccessful()) {
                            msg = "not";
                        }
                        Log.d("TAG important_message", msg);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        FirebaseMessaging.getInstance().subscribeToTopic("new_items")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "msg topic scsess";
                        if (!task.isSuccessful()) {
                            msg = "not";
                        }
                        Log.d("TAG new_items", msg);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        FirebaseMessaging.getInstance().subscribeToTopic("new_offers")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "msg topic scsess";
                        if (!task.isSuccessful()) {
                            msg = "not";
                        }
                        Log.d("TAG new_offers", msg);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void sendUserTokenToServer(String token) {
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        UserInfo userInfo = new UserInfo("empty_id",token,"new_user","empty_pic");
        Call<UserInfo> call = jsonPlaceHolderApi.insertUser(userInfo);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (!response.isSuccessful())
                {
                    Log.i("TAG Error code", String.valueOf(response.code()));
                    return;
                }

                UserInfo userInfoResponse = response.body();
                Log.i("TAG content", String.valueOf(response.toString()));

                String content="";
                saveIDInSP(SaveFCMTokenService.this,userInfoResponse.getServer_id());
                content += "Code: "+response.code();
                content += "id :" + userInfoResponse.getServer_id();
                content += "FullName :" + userInfoResponse.getFullName();
                content += "Token :" + userInfoResponse.getToken();
                content += "Picture :" + userInfoResponse.getPicture();

                Log.i("TAG content", String.valueOf(content));

            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.i("TAG Error onFailure", String.valueOf(t.getMessage()));
            }
        });
    }
}