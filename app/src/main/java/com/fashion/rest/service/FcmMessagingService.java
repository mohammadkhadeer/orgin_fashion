package com.fashion.rest.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.fashion.rest.R;
import com.fashion.rest.database.DBHelper;
import com.fashion.rest.view.activity.mainScreem.MainActivity;
import com.fashion.rest.view.fragments.fragmentItemDetails.FragmentFullImageSlider;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Map;

import static com.fashion.rest.app.App.CHANNEL_1_ID;
import static com.fashion.rest.app.App.CHANNEL_3_ID;
import static com.fashion.rest.database.DataBaseInstance.getDataBaseInstance;
import static com.fashion.rest.functions.Functions.getTextEngOrLocal;
import static com.fashion.rest.functions.Functions.getTimeStamp;
import static com.fashion.rest.functions.Functions.isAppOnForeground;
import static com.fashion.rest.functions.Functions.splitString;
import static com.fashion.rest.sharedPreferences.LoginInfo.saveTokenInSP;
import static com.fashion.rest.sharedPreferences.NumberOfNotification.getNumberOfItemInCartFromSP;
import static com.fashion.rest.sharedPreferences.NumberOfNotification.saveNumberOfItemsInCartInSP;

public class FcmMessagingService extends FirebaseMessagingService {
    private NotificationManagerCompat notificationManager;
    Bitmap bitmap1;
    ConvertUrlToBitmap convertUrlToBitmap;
    String title,des,channel_id,imageUrl,optional;
    String[] titleArray,desArray;
    DBHelper dbHelper;

    @Override
    public void onNewToken(String new_token) {
        super.onNewToken(new_token);
        Log.e("NEW_TOKEN",new_token);
        Intent intent = new Intent(FcmMessagingService.this, SaveFCMTokenService.class);
        intent.putExtra("TOKEN",new_token);
        saveTokenInSP(FcmMessagingService.this,new_token);
        FcmMessagingService.this.startService(intent);
    }

    @SuppressLint("WrongThread")
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        intiValue();

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            setValue(remoteMessage);
            updateNotOpenNotificationNumber();
            insetNotificationToDB();


            Log.d("TAG", "title_en: " + titleArray[0]);
            Log.d("TAG", "title_local: " + titleArray[1]);
            Log.d("TAG", "des_en: " + desArray[0]);
            Log.d("TAG", "des_Local: " + desArray[1]);

            Log.d("TAG", "imageUrl: " + imageUrl);

            convertUrlToBitmap = (ConvertUrlToBitmap) new ConvertUrlToBitmap().execute(imageUrl);

        }
    }

    private void updateNotOpenNotificationNumber() {
        //check if app in the front ground update the number of notifications
        if (isAppOnForeground(getApplicationContext(),"com.fashion.rest"))
        {
//          ((MainActivity) getApplicationContext()).updateNumberOfNotifications();
        }

        String num = getNumberOfItemInCartFromSP(getApplicationContext());
        if (num != null && !num.isEmpty())
        {
         int x = Integer.parseInt(num);
         int y = x+1;
            saveNumberOfItemsInCartInSP(getApplicationContext(),String.valueOf(y));
        }else{
            saveNumberOfItemsInCartInSP(getApplicationContext(),"1");
        }
    }

    private void insetNotificationToDB() {
        String itemID = "null";
        if (!channel_id.equals("important_message"))
        { itemID = "empty"; }

        dbHelper.insertNotifications(imageUrl,titleArray[0],titleArray[1],desArray[0],desArray[1],channel_id,itemID,getTimeStamp(),"optional","اختياري","0");
    }

    private class ConvertUrlToBitmap extends AsyncTask<String, Long, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                bitmap1 = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                if (channel_id.equals("new_items"))
                {
                    Notification notification = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_1_ID)
                            .setColor(Color.BLUE)
                            .setContentTitle(getTextEngOrLocal(getApplicationContext(),titleArray[0],titleArray[1]))
                            .setContentText(getTextEngOrLocal(getApplicationContext(),desArray[0],desArray[1]))
                            .setLargeIcon(bitmap1)
                            .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(bitmap1)
                                .bigLargeIcon(null))
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .build();

                    notificationManager.notify(123, notification);
                }

                if (channel_id.equals("new_offers"))
                {
                    Notification notification = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_1_ID)
                            .setColor(Color.BLUE)
                            .setContentTitle(getTextEngOrLocal(getApplicationContext(),titleArray[0],titleArray[1]))
                            .setContentText(getTextEngOrLocal(getApplicationContext(),desArray[0],desArray[1]))
                            .setLargeIcon(bitmap1)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .build();

                    notificationManager.notify(12, notification);
                }

                if (channel_id.equals("important_message"))
                {
                    Notification notification = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_3_ID)
                            .setColor(Color.BLUE)
                            .setContentTitle(getTextEngOrLocal(getApplicationContext(),titleArray[0],titleArray[1]))
                            .setContentText(getTextEngOrLocal(getApplicationContext(),desArray[0],desArray[1]))
                            .setLargeIcon(bitmap1)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .build();

                    notificationManager.notify(1, notification);
                }

                return true;
            } catch (Exception e) {
                Log.e("TAG", e.toString());
                return false;
            }
        }
    }

    private void setValue(RemoteMessage remoteMessage) {
        //optional = remoteMessage.getNotification().getTitleLocalizationArgs()
        title = remoteMessage.getNotification().getTitle();
        des = remoteMessage.getNotification().getBody();
        channel_id = remoteMessage.getNotification().getChannelId();
        Uri imageUri = remoteMessage.getNotification().getImageUrl();
        imageUrl = String.valueOf(imageUri);
        titleArray = splitString(title, "@");
        desArray = splitString(des, "@");
    }

    private void intiValue() {
        notificationManager = NotificationManagerCompat.from(this);
        dbHelper = getDataBaseInstance(getApplicationContext());
    }

}
