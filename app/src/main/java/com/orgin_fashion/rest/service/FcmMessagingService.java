package com.orgin_fashion.rest.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.orgin_fashion.rest.R;
import com.orgin_fashion.rest.database.DBHelper;
import com.orgin_fashion.rest.model.NotificationModel;
import com.orgin_fashion.rest.presenter.NotificationListener;
import com.orgin_fashion.rest.view.activity.AboutUs;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.net.URL;
import java.util.Map;

import static com.orgin_fashion.rest.app.App.CHANNEL_1_ID;
import static com.orgin_fashion.rest.app.App.CHANNEL_3_ID;
import static com.orgin_fashion.rest.database.DataBaseInstance.getDataBaseInstance;
import static com.orgin_fashion.rest.functions.Functions.getTextEngOrLocal;
import static com.orgin_fashion.rest.functions.Functions.getTimeStamp;
import static com.orgin_fashion.rest.functions.Functions.isAppOnForeground;
import static com.orgin_fashion.rest.functions.Functions.splitString;
import static com.orgin_fashion.rest.sharedPreferences.LoginInfo.saveTokenInSP;
import static com.orgin_fashion.rest.sharedPreferences.NumberOfNotification.getNumberOfItemInCartFromSP;
import static com.orgin_fashion.rest.sharedPreferences.NumberOfNotification.saveNumberOfItemsInCartInSP;

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
        super.onMessageReceived(remoteMessage);
        intiValue();
        Log.i("TAG", "onMessageReceived: ");
//        if (remoteMessage.getData() != null)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
//                sendNotificationAPI26(remoteMessage);
//            else
//                sendNotificationAPI(remoteMessage);
//
//
//
//         Check if message contains a notification payload.
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

        }else{
            Log.i("TAG", "onMessageReceived: remoteMessage.getNotification() == null");
        }
    }




    private void sendNotificationAPI(RemoteMessage remoteMessage) {
        Map<String,String> data = remoteMessage.getData();

        String title = data.get("title");
        String contact = data.get("des");


    }




    private void sendNotificationAPI26(RemoteMessage remoteMessage) {
        Map<String,String> data = remoteMessage.getData();

        //cb2PcyO1I6Q:APA91bHSJOqgwgHKvPAg6pqztuu84l_3zpBhJ8UrxwaZHOZU-ukgdWTo-D0Pz7EvMStqJFh5NCaqBgF5rUCYshHX5qw3_k585rjT_CG3nBkIkF3Q7hyXUNWJd1atmilhyn_XB4s5WjAD
        String title = data.get("title");
        String contact = data.get("des");
        Log.i("TAG title: ", title);
        Log.i("TAG des" , contact);

        Intent resultIntent = new Intent(getApplicationContext(), AboutUs.class);
        resultIntent.putExtra("item_object", notificationModel);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_3_ID)
                .setColor(Color.BLUE)
                .setContentTitle(title)
                .setContentText(contact)
                .setLargeIcon(bitmap1)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        notificationManager.notify(1, notification);
        //notificationListener.ready(1);
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
