package com.fashion.rest.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.fashion.rest.R;
import com.fashion.rest.database.DBHelper;
import com.fashion.rest.model.NotificationModel;
import com.fashion.rest.presenter.NotificationListener;
import com.fashion.rest.presnter.Filter;
import com.fashion.rest.view.activity.AboutUs;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.net.URL;
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
    String[] titleArray,desArray,optionalArray,optionalAndDesArray;
    DBHelper dbHelper;
    NotificationModel notificationModel;
    NotificationListener notificationListener;

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

            Log.d("TAG", "channel_id: " + channel_id);
            Log.d("TAG", "title_en: " + titleArray[0]);
            Log.d("TAG", "title_local: " + titleArray[1]);
//            Log.d("TAG", "des_en: " + desArray[0]);
//            Log.d("TAG", "des_Local: " + desArray[1]);
//            Log.d("TAG", "optional_en: " + optionalArray[0]);
//            Log.d("TAG", "optional_Local: " + optionalArray[1]);
//
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

        notificationModel = new NotificationModel(
                imageUrl,titleArray[0],titleArray[1],desArray[0].replace("\n", "")
                ,desArray[1].replace("\n", ""),channel_id,itemID,getTimeStamp()
                ,optionalArray[0].replace("\n", "")
                ,optionalArray[1].replace("\n", ""),"0"
        );

        dbHelper.insertNotifications(imageUrl,titleArray[0],titleArray[1],desArray[0].replace("\n", "")
                ,desArray[1].replace("\n", ""),channel_id,itemID,getTimeStamp()
                ,optionalArray[0].replace("\n", "")
                ,optionalArray[1].replace("\n", ""),"0");
    }

    private class ConvertUrlToBitmap extends AsyncTask<String, Long, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                bitmap1 = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                Log.d("TAG", "channel_id: " + channel_id);

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
                    Intent resultIntent = new Intent(getApplicationContext(), AboutUs.class);
                    resultIntent.putExtra("item_object", notificationModel);
                    resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                    Notification notification = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_3_ID)
                            .setColor(Color.BLUE)
                            .setContentTitle(getTextEngOrLocal(getApplicationContext(),titleArray[0],titleArray[1]))
                            .setContentText(getTextEngOrLocal(getApplicationContext(),desArray[0],desArray[1]))
                            .setLargeIcon(bitmap1)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .build();

                    notificationManager.notify(1, notification);
                    notificationListener.ready(1);

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
        if (des.contains("&"))
        {
            optionalAndDesArray = splitString(des, "&");
            desArray = splitString(optionalAndDesArray[0], "@");
            optionalArray = splitString(optionalAndDesArray[1], "#");
        }else{
            desArray = splitString(des, "@");
            optionalArray[0] = "optional";
            optionalArray[1] = "اختياري";
        }
    }

    private void intiValue() {
        notificationManager = NotificationManagerCompat.from(this);
        dbHelper = getDataBaseInstance(getApplicationContext());
    }

}
